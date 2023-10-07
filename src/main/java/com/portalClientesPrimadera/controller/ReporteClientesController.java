package com.portalClientesPrimadera.controller;

import com.portalClientesPrimadera.Integration.soap.EjecutarReporte4_4;
import com.portalClientesPrimadera.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1")
public class ReporteClientesController {

    @Autowired
    private EjecutarReporte4_4 ejecutarReporte44;

    @GetMapping("/reporteClientes")
    public List<Cliente> obtenClientes() {
        return ejecutarReporte44.ReporteClientes("/Custom/PRIMADERA/PORTAL_CLIENTES/Rpt_listado_cliente.xdo",
                "INTEGRACION_PRI", "Oracle2023*");
    }

    @GetMapping("/reporteClientes/filtrar")
    public List<Cliente> filtrarCliente(@RequestParam String palabraClave) {

        List<Cliente> listaClientes = ejecutarReporte44.ReporteClientes(
                "/Custom/PRIMADERA/PORTAL_CLIENTES/Rpt_listado_cliente.xdo", "INTEGRACION_PRI", "Oracle2023*");

        List<Cliente> clientesFiltrados = new ArrayList<>();

        for (Cliente cliente : listaClientes) {
            if (cliente.getAccountName().toLowerCase().contains(palabraClave.toLowerCase())) {
                clientesFiltrados.add(cliente);
            }
        }

        return clientesFiltrados;
    }

}
