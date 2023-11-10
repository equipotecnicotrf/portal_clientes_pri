package com.portalClientesPrimadera.Integration.API;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
public class APIConsultOrderHeaders {

    public ResponseEntity<APIConsultOrderHeadersResponse[]> getOrdersHeaders(
            Long BuyingPartyId,
            String TransactionTypeCode,
            String StatusCode,
            String CreationDateFrom,
            String CreationDateTo

        ) throws JsonProcessingException {

        //Crear las variables necesarias para la consulta
        Boolean OpenFlag;
        String creationDateFrom = CreationDateFrom;
        String creationDateTo = CreationDateTo;
        int limit = 500;
        int offset = 0;
        //Crear la primera parte de la URL para el PATH
        String apiUrl = "https://efdg-test.fa.us6.oraclecloud.com/fscmRestApi/resources/11.13.18.05/salesOrdersForOrderHub?q=";

        //Validar el tipo de StatusCode para asignar el valor de OpenFlag, caso de que no se un StatusCode valido se retorna error
        if ("OPEN".equals(StatusCode) || "PARTIALLY_CLOSE".equals(StatusCode)) {
            OpenFlag = true;
        } else if ("CLOSED".equals(StatusCode) || "CANCELED".equals(StatusCode)){
            OpenFlag = false;
        } else {
            // El StatusCode no es válido, establece un mensaje de error en errorMessage
            String errorMessage = "El StatusCode no es válido";
            APIConsultOrderHeadersResponse errorResponse = new APIConsultOrderHeadersResponse(null, null, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIConsultOrderHeadersResponse[]{errorResponse});
        }

        //Armar la segunda parte del path para la URL con las variables recibidas en la función
        String q = "BuyingPartyId=" + BuyingPartyId
                + ";SubmittedFlag=true;TransactionTypeCode=" + TransactionTypeCode
                + ";OpenFlag=" + OpenFlag
                + ";StatusCode=" + StatusCode
                + ";CreationDate<=" + creationDateTo
                + ";CreationDate>=" + creationDateFrom;


        //Crear la URL completa para el consumo API
        String FullApiUrl = apiUrl + q + "&limit=" + limit + "&offset=" + offset;
        System.out.println(FullApiUrl);

        // Autorización básica (usuario:contraseña)
        String username = "INTEGRACION_PRI";
        String password = "Oracle2023*";
        String credentials = username + ":" + password;
        //String authHeader = "Basic " + username + ":" + password;
        String authHeader = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

        // Parámetros de la solicitud
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.set("Content-Type", "application/json");

        // Crear una entidad de solicitud con encabezados
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Realizar la solicitud GET a la API
        ResponseEntity<String> response = new RestTemplate().exchange(FullApiUrl, HttpMethod.GET, entity, String.class);

        // Verificar que la respuesta sea exitosa (código de estado 200)
        if (response.getStatusCode() == HttpStatus.OK) {
            // Obtener el cuerpo de la respuesta JSON
            String jsonResponse = response.getBody();

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> responseMap = objectMapper.readValue(jsonResponse, new TypeReference<Map<String, Object>>() {});

                // Verifica si el campo "items" existe en la respuesta JSON
                if (responseMap.containsKey("items")) {
                    List<Map<String, Object>> items = (List<Map<String, Object>>) responseMap.get("items");
                    APIConsultOrderHeadersResponse[] responseData = new APIConsultOrderHeadersResponse[items.size()];

                    for (int i = 0; i < items.size(); i++) {
                        Map<String, Object> item = items.get(i);
                        Long headerId = (Long) item.get("HeaderId");
                        String sourceTransactionId = (String) item.get("SourceTransactionId");
                        String orderKey = (String) item.get("OrderKey");
                        responseData[i] = new APIConsultOrderHeadersResponse(headerId, sourceTransactionId, orderKey);
                    }

                    return ResponseEntity.ok(responseData);
                } else {
                    // Manejar el caso en que "items" no está presente en la respuesta JSON
                    System.err.println("El campo 'items' no está presente en la respuesta JSON");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
                }
            } catch (Exception e) {
                // Manejar cualquier error de deserialización
                e.printStackTrace(); // Puedes registrar el error si es necesario
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } else {
            // Manejar errores de solicitud, por ejemplo, si no se puede acceder a la API
            return ResponseEntity.status(response.getStatusCode()).body(null);
        }
    }

    /*public static void main(String[] args){
        try {
            APIConsultOrderHeaders apiConsultOrderHeaders = new APIConsultOrderHeaders();
            Long buyingPartyId = 100000051076712L; // Reemplaza con los valores adecuados
            String transactionTypeCode = "NACIONAL"; // Reemplaza con los valores adecuados
            String creationDate = "2023-09-01"; // Reemplaza con los valores adecuados
            String statusCode = "OPEN"; // Reemplaza con los valores adecuados

            ResponseEntity<APIConsultOrderHeadersResponse[]> response = apiConsultOrderHeaders.getOrdersHeaders(buyingPartyId, transactionTypeCode, creationDate, statusCode);

            // Verifica si la respuesta fue exitosa
            if (response.getStatusCode() == HttpStatus.OK) {
                APIConsultOrderHeadersResponse[] responseData = response.getBody();

                // Imprime los datos de responseData
                for (APIConsultOrderHeadersResponse order : responseData) {
                    System.out.println("HeaderId: " + order.getHeaderId());
                    System.out.println("OrderKey: " + order.getOrderKey());
                    System.out.println();
                }
            } else {
                System.err.println("Error en la solicitud: " + response.getStatusCode());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }*/
}
