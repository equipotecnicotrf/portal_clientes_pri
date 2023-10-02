package com.portalClientesPrimadera.controller;

import com.portalClientesPrimadera.Integration.soap.EjecutarReporte4_4;
import com.portalClientesPrimadera.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins =  "http://localhost:5173")
@RestController
@RequestMapping("/api/v1")
public class ReporteClientesController {

    @Autowired
    private EjecutarReporte4_4 ejecutarReporte44;

    @GetMapping("/reporteClientes")
    public List<Cliente> obtenClientes() {
        return ejecutarReporte44.ReporteClientes("/Custom/PRIMADERA/PORTAL_CLIENTES/Rpt_listado_cliente.xdo", "INTEGRACION_PRI", "Oracle2023*");
    }




}
