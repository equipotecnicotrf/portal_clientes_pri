package com.portalClientesPrimadera.Integration.API;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@RestController
@RequestMapping("/api/v1")
public class ConsumirAPIInventario {

    @GetMapping("/consumir-api")
    public ResponseEntity<String> consumirApi() {
        // URL de la API
        String apiUrl = "https://efdg-test.fa.us6.oraclecloud.com//fscmRestApi/resources/11.13.18.05/inventoryOnhandBalances";
        // Parametros adicionales
        String q = "OrganizationCode='PRI04'&q=SubinventoryCode='T_PTP'&q=SummaryLevel='Subinventory'";
        String onlyData = "true";
        String orderBy = "ItemNumber";
        String limit = "500";
        String offset = "0";
        //Ful url para el consumo
        String fullUrl = apiUrl + "?q=" + q + "&onlyData=" + onlyData + "&orderBy=" + orderBy + "&limit=" + limit + "&offset=" + offset;

        // Autorización básica (usuario:contraseña)
        String username = "INTEGRACION_PRI";
        String password = "Oracle2023*";
        String credentials = username + ":" + password;
        //String authHeader = "Basic " + username + ":" + password;
        String authHeader = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

        // Parámetros de la solicitud
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.set("REST-Framework-Version", "7");
        headers.set("Content-Type", "application/json");

        // Crear una entidad de solicitud con encabezados
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Realizar la solicitud GET a la API
        ResponseEntity<String> response = new RestTemplate().exchange(fullUrl, HttpMethod.GET, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            return ResponseEntity.ok(responseBody);
        } else {
            return ResponseEntity.status(response.getStatusCode()).body("Error en la solicitud: " + response.getStatusCodeValue());
        }
    }

}
