package com.portalClientesPrimadera.Integration.API;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
public class APIConsultOrderLines {

    public ResponseEntity<APIConsultOrderLinesResponse[][]> getOrdersLinesAndHeadres(
            //En la función se recibe un Array de APIConsultOrderHeadersResponse
            APIConsultOrderHeadersResponse[] headers
    ) throws JsonProcessingException {
        //Se crea un List para guardar y retornar todas los pedidos y sus lineas
        List<List<APIConsultOrderLinesResponse>> allResponses = new ArrayList<>();

        //Se declaran las variables para armar la URL de la API
        String apiUrl1 = "https://efdg-test.fa.us6.oraclecloud.com:443/fscmRestApi/resources/11.13.18.05/salesOrdersForOrderHub/";
        String apiUrl2 = "/child/lines?";
        String expand = "lineDetails";
        int limit = 500;
        int offset = 0;
        Boolean onlyData = true;
        String orderBy = "LineId";

        //Se inicia la iteración del Array de APIConsultOrderHeadersResponse
        for (APIConsultOrderHeadersResponse header : headers){
            //Se toma el OrderKey de cada elemento para tomarlo como variable e iterarlo en el consumo de la API
            String OrderKey = header.getOrderKey();
            String sourceTransactionId = header.getSourceTransactionId();
            Long siteId = null;
            String address1 = null;
            String city = null;
            String state = null;

            //Llamar los datos de direccion
            APIConsultAddressForOrder apiConsultAddressForOrder = new APIConsultAddressForOrder();
            ResponseEntity<APIConsultAddressForOrderResponse[]> addressResponse = apiConsultAddressForOrder.getAddressForOrder(sourceTransactionId);

            if (addressResponse.getStatusCode() == HttpStatus.OK) {
                APIConsultAddressForOrderResponse[] responseData = addressResponse.getBody();

                // Verificar si responseData no es nulo y tiene al menos un elemento
                if (responseData != null && responseData.length > 0) {
                    // Obtener los valores del primer elemento del array (puedes ajustar según tus necesidades)
                    APIConsultAddressForOrderResponse order = responseData[0];

                    // Asignar valores a las variables
                    siteId = order.getSiteId();
                    address1 = order.getAddress1();
                    city = order.getCity();
                    state = order.getState();

                } else {
                    // Manejar el caso en que responseData está vacío
                    System.err.println("La respuesta de la dirección está vacía");
                }
            } else {
                System.err.println("Error en la solicitud: " + addressResponse.getStatusCode());
            }

            // Se almacena cada respuesta en una lista
            List<APIConsultOrderLinesResponse> responses = new ArrayList<>();

            //Se arma la URL completa de la API
            String FullApiUrl = apiUrl1 + OrderKey + apiUrl2 + "expand=" + expand + "&limit=" + limit + "&offset=" + offset + "&onlyData=" + onlyData + "&orderBy=" + orderBy;
            System.out.println(FullApiUrl);

            // Autorización básica (usuario:contraseña)
            String username = "INTEGRACION_PRI";
            String password = "Oracle2023*";
            String credentials = username + ":" + password;
            String authHeader = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

            // Parámetros de la solicitud
            HttpHeaders headersAPI = new HttpHeaders();
            headersAPI.set("Authorization", authHeader);
            headersAPI.set("Content-Type", "application/json");

            // Crear una entidad de solicitud con encabezados
            HttpEntity<String> entity = new HttpEntity<>(headersAPI);

            // Realizar la solicitud GET a la API
            ResponseEntity<String> response = new RestTemplate().exchange(FullApiUrl, HttpMethod.GET, entity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {

                String jsonResponse = response.getBody();

                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    Map<String, Object> responseMap = objectMapper.readValue(jsonResponse, new TypeReference<Map<String, Object>>() {});

                    // Verifica si el campo "items" existe en la respuesta JSON
                    if (responseMap.containsKey("items")) {
                        List<Map<String, Object>> items = (List<Map<String, Object>>) responseMap.get("items");
                        //APIConsultOrderLinesResponse[] responseData = new APIConsultOrderLinesResponse[items.size()];

                        for (int i = 0; i < items.size(); i++) {
                            Map<String, Object> item = items.get(i);
                            Long headerId = header.getHeaderId();
                            String orderKey = OrderKey;
                            String SourceTransactionId = sourceTransactionId;
                            Long FulfillLineId = (Long) item.get("FulfillLineId");
                            String FulfillLineNumber = (String) item.get("FulfillLineNumber");
                            String SourceTransactionLineNumber = (String) item.get("SourceTransactionLineNumber");
                            String SourceTransactionNumber = (String) item.get("SourceTransactionNumber");
                            Long ProductId = (Long) item.get("ProductId");
                            String ProductNumber = (String) item.get("ProductNumber");
                            String ProductDescription = (String) item.get("ProductDescription");
                            Integer OrderedQuantity = (Integer) item.get("OrderedQuantity");
                            String RequestedShipDate = (String) item.get("RequestedShipDate");
                            String ActualShipDate = (String) item.get("ActualShipDate");
                            Double UnitListPrice = (Double) item.get("UnitListPrice");
                            Double UnitSellingPrice = (Double) item.get("UnitSellingPrice");
                            Boolean OnHoldFlag = (Boolean) item.get("OnHoldFlag");
                            String StatusCode = (String) item.get("StatusCode");
                            String Status = (String) item.get("Status");
                            Long SiteId = siteId;
                            String Address1 = address1;
                            String City = city;
                            String State = state;

                            APIConsultOrderLinesResponse apiResponse = new APIConsultOrderLinesResponse();
                            List<APIConsultOrderLinesResponse.LineDetail> lineDetails = new ArrayList<>();
                            if (item.get("lineDetails")  != null) {
                                List<Map<String, Object>> lineDetailsData = (List<Map<String, Object>>) item.get("lineDetails");
                                for (Map<String, Object> lineDetailData : lineDetailsData) {
                                    String billOfLadingNumber = (String) lineDetailData.get("BillOfLadingNumber");
                                    String billingTransactionNumber = (String) lineDetailData.get("BillingTransactionNumber");
                                    String billingTransactionDate = (String) lineDetailData.get("BillingTransactionDate");

                                    APIConsultOrderLinesResponse.LineDetail lineDetail = apiResponse.new LineDetail(billOfLadingNumber, billingTransactionNumber, billingTransactionDate);
                                    lineDetails.add(lineDetail);
                                }
                                // Agregar la lista de lineDetails a la instancia de APIConsultOrderLinesResponse
                                apiResponse.setLineDetails(lineDetails);
                            }else {
                                // Si "lineDetails" está vacío, simplemente crea una lista vacía
                                apiResponse.setLineDetails(new ArrayList<>());
                            }

                            responses.add(new APIConsultOrderLinesResponse(
                                    headerId,
                                    orderKey,
                                    SourceTransactionId,
                                    FulfillLineId,
                                    FulfillLineNumber,
                                    SourceTransactionLineNumber,
                                    SourceTransactionNumber,
                                    ProductId,
                                    ProductNumber,
                                    ProductDescription,
                                    OrderedQuantity,
                                    RequestedShipDate,
                                    ActualShipDate,
                                    UnitListPrice,
                                    UnitSellingPrice,
                                    OnHoldFlag,
                                    StatusCode,
                                    Status,
                                    SiteId,
                                    Address1,
                                    City,
                                    State,
                                    lineDetails
                                    ));
                        }
                        // Agregar las respuestas recopiladas a allResponses
                        allResponses.add(responses);
                    }else {
                        // Manejar el caso en que "items" no está presente en la respuesta JSON
                        System.err.println("El campo 'items' no está presente en la respuesta JSON");
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
                    }
                }catch (Exception e) {
                    // Manejar cualquier error de deserialización
                    e.printStackTrace(); // Puedes registrar el error si es necesario
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
                }
            } else {
                // Manejar errores de solicitud, por ejemplo, si no se puede acceder a la API
                return ResponseEntity.status(response.getStatusCode()).body(null);
            }
        }
        // Convertir la lista de listas en un array bidimensional
        APIConsultOrderLinesResponse[][] result = new APIConsultOrderLinesResponse[allResponses.size()][];
        for (int i = 0; i < allResponses.size(); i++) {
            result[i] = allResponses.get(i).toArray(new APIConsultOrderLinesResponse[0]);
        }

        return ResponseEntity.ok(result);
    }

   /* public static void main(String[] args) {
        try {
            APIConsultOrderHeaders apiConsultOrder = new APIConsultOrderHeaders();

            Long buyingPartyId = 100000051076712L;
            String transactionTypeCode = "NACIONAL";
            String creationDate = "2023-09-01";
            String statusCode = "OPEN";

            ResponseEntity<APIConsultOrderHeadersResponse[]> headersResponse = apiConsultOrder.getOrdersHeaders(
                    buyingPartyId,
                    transactionTypeCode,
                    creationDate,
                    statusCode
            );

            if (headersResponse.getStatusCode() == HttpStatus.OK) {
                APIConsultOrderHeadersResponse[] headers = headersResponse.getBody();

                if (headers.length > 0) {
                    // Llamar a la clase APIConsultOrderLines y pasar los encabezados para obtener las líneas
                    APIConsultOrderLines apiConsultOrderLines = new APIConsultOrderLines();
                    ResponseEntity<APIConsultOrderLinesResponse[][]> linesResponse = apiConsultOrderLines.getOrdersLinesAndHeadres(headers);

                    if (linesResponse.getStatusCode() == HttpStatus.OK) {
                        APIConsultOrderLinesResponse[][] lines = linesResponse.getBody();

                        for (int i = 0; i < lines.length; i++) {
                            APIConsultOrderHeadersResponse header = headers[i];
                            APIConsultOrderLinesResponse[] linesForHeader = lines[i];

                            System.out.println("Encabezado para OrderKey: " + header.getOrderKey());

                            if (linesForHeader != null) {
                                // Iterar a través de las líneas de este encabezado
                                for (APIConsultOrderLinesResponse line : linesForHeader) {
                                    System.out.println("Linea ID: " + line.getFulfillLineId());
                                    System.out.println("Source Transaction Number: " + line.getSourceTransactionNumber());
                                    System.out.println("Ordered Quantity: " + line.getOrderedQuantity());
                                    System.out.println("Requested Ship Date: " + line.getRequestedShipDate());
                                    System.out.println("Unit List Price: " + line.getUnitListPrice());
                                    System.out.println("Unit Selling Price: " + line.getUnitSellingPrice());
                                    System.out.println();
                                }
                            } else {
                                System.err.println("No se encontraron líneas para este encabezado.");
                            }
                        }
                    } else {
                        System.err.println("Error en la consulta de líneas: " + linesResponse.getStatusCode());
                    }
                } else {
                    System.err.println("No se encontraron encabezados para consultar líneas.");
                }
            } else {
                System.err.println("Error en la consulta de encabezados: " + headersResponse.getStatusCode());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }*/
}
