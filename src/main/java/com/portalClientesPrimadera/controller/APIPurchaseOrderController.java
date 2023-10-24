package com.portalClientesPrimadera.controller;

import com.portalClientesPrimadera.Integration.API.APIPostCreatePurchaseOrderV2;
import com.portalClientesPrimadera.Integration.API.PurchaseOrderRequest;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class APIPurchaseOrderController {

    @PostMapping("/postCreatePurchaseOrder")
    public JSONObject postCreatePurchaseOrder(@RequestBody PurchaseOrderRequest request) {
        // Llama a la API con los datos del objeto request
        JSONObject result = APIPostCreatePurchaseOrderV2.postCreatePurchaseOrder(request);

        return result;
    }

}
