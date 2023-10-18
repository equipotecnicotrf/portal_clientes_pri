package com.portalClientesPrimadera.Integration.API;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portalClientesPrimadera.model.AvailabilityEntity;
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
public class APIAvaibilityInventoryService {

    private final AvailabilityRepository availabilityRepository;

    @Autowired
    public APIAvaibilityInventoryService(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    public ResponseEntity<String> consumirAPIInventario() throws JsonProcessingException {
        // URL de la API
        String apiUrl = "https://efdg-test.fa.us6.oraclecloud.com//fscmRestApi/resources/11.13.18.05/inventoryOnhandBalances?q=OrganizationCode='PRI04';SubinventoryCode='T_PTP';SummaryLevel='Subinventory'&onlyData=true&orderBy=ItemNumber";

        // Autorización básica (usuario:contraseña)
        String username = "INTEGRACION_PRI";
        String password = "Oracle2023*";
        String credentials = username + ":" + password;
        //String authHeader = "Basic " + username + ":" + password;
        String authHeader = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

        // Parámetros de la solicitud
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        /*headers.set("REST-Framework-Version", "7");
        headers.set("Content-Type", "application/json");*/

        Integer limit = 500;
        Integer offset = 0;

        boolean hasMore = true;
        availabilityRepository.deleteAll();

        while (hasMore) {

            //Ful url para el consumo
            String fullUrl = apiUrl + "&limit=" + limit + "&offset=" + offset;

            // Crear una entidad de solicitud con encabezados
            //HttpEntity<String> entity = new HttpEntity<>(headers);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Realizar la solicitud GET a la API
            ResponseEntity<String> response = new RestTemplate().exchange(fullUrl, HttpMethod.GET, entity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                // antes de guardar los datos borra los existentes

                String responseBody = response.getBody();
                ObjectMapper objectMapper = new ObjectMapper();
                ApiInventoryResponse apiResponse = objectMapper.readValue(responseBody, ApiInventoryResponse.class);
                List<itemEntityAPIInventory> items = apiResponse.getItems();

                for (itemEntityAPIInventory i : items) {

                    Long availableToTransact = APIPostAvaibilityByItem.getAvailableToTransact(i.getOrganizationCode(), i.getSubinventoryCode(), i.getItemNumber());
                    AvailabilityEntity availabilityEntity = new AvailabilityEntity();
                    availabilityEntity.setInventory_item_id(i.getInventoryItemId());
                    //availabilityEntity.setQuantity_units(i.getPrimaryQuantity());
                    availabilityEntity.setQuantity_units(availableToTransact);
                    availabilityEntity.setOrganization_id(i.getOrganizationId());
                    availabilityEntity.setOrganization_code(i.getOrganizationCode());

                    availabilityRepository.save(availabilityEntity);
                }

                // Actualizar el offset para la próxima solicitud
                offset += limit;
                // Verificar si hay más datos
                hasMore = apiResponse.hasMore;

                //return ResponseEntity.ok(responseBody);

            } else {
                return ResponseEntity.status(response.getStatusCode()).body("Error en la solicitud: " + response.getStatusCodeValue());
            }
        }
        return ResponseEntity.ok("Proceso terminado");
    }

}