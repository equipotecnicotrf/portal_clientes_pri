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
                            Long FulfillLineId = (Long) item.get("FulfillLineId");
                            String SourceTransactionNumber = (String) item.get("SourceTransactionNumber");
                            Integer OrderedQuantity = (Integer) item.get("OrderedQuantity");
                            String RequestedShipDate = (String) item.get("RequestedShipDate");
                            Integer UnitListPrice = (Integer) item.get("UnitListPrice");
                            Integer UnitSellingPrice = (Integer) item.get("UnitSellingPrice");

                            responses.add(new APIConsultOrderLinesResponse(
                                    headerId,
                                    orderKey,
                                    FulfillLineId,
                                    SourceTransactionNumber,
                                    OrderedQuantity,
                                    RequestedShipDate,
                                    UnitListPrice,
                                    UnitSellingPrice));
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
