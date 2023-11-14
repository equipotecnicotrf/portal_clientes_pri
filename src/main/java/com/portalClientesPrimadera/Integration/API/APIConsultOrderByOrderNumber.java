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
public class APIConsultOrderByOrderNumber {

    public ResponseEntity<APIConsultOrderHeadersResponse[]> getOrderByNumberOrder(String orderNumber, Long buyingPartyId) throws JsonProcessingException {

        String apiUrl = "https://efdg-test.fa.us6.oraclecloud.com/fscmRestApi/resources/11.13.18.05/salesOrdersForOrderHub?";
        int limit = 500;
        int offset = 0;
        String q = "&q=OrderNumber=" + orderNumber + ";BuyingPartyId=" + buyingPartyId;

        //Armar la URL completa para consumir el API
        String FullApiUrl = apiUrl + "limit=" + limit + "&offset=" + offset + q;
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

}
