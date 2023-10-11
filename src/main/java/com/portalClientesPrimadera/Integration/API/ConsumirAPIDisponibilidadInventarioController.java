package com.portalClientesPrimadera.Integration.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ConsumirAPIDisponibilidadInventarioController {

    private final APIDisponibilidadInventarioService apiDisponibilidadInventarioService;

    @Autowired
    public ConsumirAPIDisponibilidadInventarioController(APIDisponibilidadInventarioService apiDisponibilidadInventarioService) {
        this.apiDisponibilidadInventarioService = apiDisponibilidadInventarioService;
    }

    @GetMapping("/APIDisponibilidadInventario")
    public ResponseEntity<String> consumirApi() {
        try {
            return apiDisponibilidadInventarioService.consumirAPIInventario();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la solicitud: " + e.getMessage());
        }
    }

}
