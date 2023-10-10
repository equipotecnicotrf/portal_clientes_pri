package com.portalClientesPrimadera.controller;

import com.portalClientesPrimadera.Integration.soap.EjecutarReporteDireccionesV2;
import com.portalClientesPrimadera.model.DireccionReporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ReporteDireccionesController {

    @Autowired
    private EjecutarReporteDireccionesV2 ejecutarReporteDireccionesV2;

    @GetMapping("/reporteDirecciones/filtrar")
    public List<DireccionReporte> listDirecciones(@RequestParam String custAccountID) {
        return ejecutarReporteDireccionesV2.ReporteDirecciones(
                "/Custom/PRIMADERA/PORTAL_CLIENTES/Rpt_MaestroClientes.xdo", "INTEGRACION_PRI", "Oracle2023*",
                "Compañia", "PRI_UNIDAD_NEGOCIO", "P_CUENTA", custAccountID);

    }

}
