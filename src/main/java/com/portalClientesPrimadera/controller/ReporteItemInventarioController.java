package com.portalClientesPrimadera.controller;

import com.portalClientesPrimadera.Integration.soap.EjecutarReporteItemInventario;
import com.portalClientesPrimadera.Integration.soap.ItemService;
import com.portalClientesPrimadera.model.ItemReporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ReporteItemInventarioController {

    @Autowired
    private EjecutarReporteItemInventario ejecutarReporteItemInventario;

    @Autowired
    private ItemService itemService;

    @GetMapping("/ ")
    public List<ItemReporte> listarItemsInventario() {
        List<ItemReporte> items =
                ejecutarReporteItemInventario.ReporteItemsInventario(
                        "/Custom/PRIMADERA/PORTAL_CLIENTES/Rpte_Maestra_Articulos.xdo",
                        "INTEGRACION_PRI",
                        "Oracle2023*",
                        "P_ORG",
                        "PRI04");

        itemService.guardarItems(items);

        return items;
    }
}
