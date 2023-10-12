package com.portalClientesPrimadera.Integration.API;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portalClientesPrimadera.model.AvailabilityEntity;
import com.portalClientesPrimadera.model.itemEntityAPIInventory;
import com.portalClientesPrimadera.repository.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;

@Service
public class APIDisponibilidadInventarioService {

    private final AvailabilityRepository availabilityRepository;

    @Autowired
    public APIDisponibilidadInventarioService(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    public ResponseEntity<String> consumirAPIInventario() throws JsonProcessingException {
        // URL de la API
        String apiUrl = "https://efdg-test.fa.us6.oraclecloud.com//fscmRestApi/resources/11.13.18.05/inventoryOnhandBalances?q=OrganizationCode='PRI04'&q=SubinventoryCode='T_PTP'&q=SummaryLevel='Subinventory'&onlyData=true&orderBy=ItemNumber&limit=5&offset=0";
        // Parametros adicionales
        String q = "OrganizationCode='PRI04'&q=SubinventoryCode='T_PTP'&q=SummaryLevel='Subinventory'";
        String onlyData = "true";
        String orderBy = "ItemNumber";
        String limit = "5";
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
        ResponseEntity<String> response = new RestTemplate().exchange(apiUrl, HttpMethod.GET, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();

            ObjectMapper objectMapper = new ObjectMapper();

            ApiInventoryResponse apiResponse = objectMapper.readValue(responseBody, ApiInventoryResponse.class);
            List<itemEntityAPIInventory> items = apiResponse.getItems();

            for (itemEntityAPIInventory i: items){
                AvailabilityEntity availabilityEntity = new AvailabilityEntity();
                availabilityEntity.setInventory_item_id(i.getInventoryItemId());
                availabilityEntity.setQuantity_units(i.getPrimaryQuantity());
                availabilityEntity.setOrganization_id(i.getOrganizationId());
                availabilityEntity.setOrganization_code(i.getOrganizationCode());

                availabilityRepository.saveAndFlush(availabilityEntity);
            }

            return ResponseEntity.ok(responseBody);

        } else {
            return ResponseEntity.status(response.getStatusCode()).body("Error en la solicitud: " + response.getStatusCodeValue());
        }
    }

}