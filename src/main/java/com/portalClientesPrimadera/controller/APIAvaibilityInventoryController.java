package com.portalClientesPrimadera.controller;

import com.portalClientesPrimadera.Integration.API.APIAvaibilityInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class APIAvaibilityInventoryController {

    private final APIAvaibilityInventoryService apiAvaibilityInventoryService;

    @Autowired
    public APIAvaibilityInventoryController(APIAvaibilityInventoryService apiAvaibilityInventoryService) {
        this.apiAvaibilityInventoryService = apiAvaibilityInventoryService;
    }

    @GetMapping("/APIDisponibilidadInventario")
    public ResponseEntity<String> consumirApi() {
        try {
            return apiAvaibilityInventoryService.consumirAPIInventario();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la solicitud: " + e.getMessage());
        }
    }

}
