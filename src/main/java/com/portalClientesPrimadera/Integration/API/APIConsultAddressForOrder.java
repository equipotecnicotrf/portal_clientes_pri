package com.portalClientesPrimadera.Integration.API;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;
import java.util.Map;

public class APIConsultAddressForOrder {

    public ResponseEntity<APIConsultAddressForOrderResponse[]> getAddressForOrder (
        String sourceTransactionId
        ) throws JsonProcessingException {

        //Crear la primera parte de la URL para el PATH y concatenar la variable
        String apiUrl = "https://efdg-test.fa.us6.oraclecloud.com:443/fscmRestApi/resources/11.13.18.05/salesOrdersForOrderHub/OPS:";
        String FullApiUrl = apiUrl + sourceTransactionId + "/child/shipToCustomer";
        System.out.println(FullApiUrl);

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

        if (response.getStatusCode() == HttpStatus.OK) {
            // Obtener el cuerpo de la respuesta JSON
            String jsonResponse = response.getBody();

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> responseMap = objectMapper.readValue(jsonResponse, new TypeReference<Map<String, Object>>() {});

                // Verifica si el campo "items" existe en la respuesta JSON
                if (responseMap.containsKey("items")) {
                    List<Map<String, Object>> items = (List<Map<String, Object>>) responseMap.get("items");
                    APIConsultAddressForOrderResponse[] responseData = new APIConsultAddressForOrderResponse[items.size()];

                    for (int i = 0; i < items.size(); i++) {
                        Map<String, Object> item = items.get(i);
                        Long siteId = (Long) item.get("SiteId");
                        String address1 = (String) item.get("Address1");
                        String city = (String) item.get("City");
                        String state = (String) item.get("State");
                        responseData[i] = new APIConsultAddressForOrderResponse(siteId, address1, city, state);
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
            APIConsultAddressForOrder apiConsultAddressForOrder = new APIConsultAddressForOrder();

            String SourceTransactionId = "300000946929498"; // Reemplaza con los valores adecuados

            ResponseEntity<APIConsultAddressForOrderResponse[]> response = apiConsultAddressForOrder.getAddressForOrder(SourceTransactionId);

            // Verifica si la respuesta fue exitosa
            if (response.getStatusCode() == HttpStatus.OK) {
                APIConsultAddressForOrderResponse[] responseData = response.getBody();

                // Imprime los datos de responseData
                for (APIConsultAddressForOrderResponse order : responseData) {
                    System.out.println("SiteId: " + order.getSiteId());
                    System.out.println("Address1: " + order.getAddress1());
                    System.out.println("City: " + order.getCity());
                    System.out.println("State: " + order.getState());
                }
            } else {
                System.err.println("Error en la solicitud: " + response.getStatusCode());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }*/

}
