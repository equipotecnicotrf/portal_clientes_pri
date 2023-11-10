package com.portalClientesPrimadera.controller;

import com.portalClientesPrimadera.Integration.API.ApiPurchaseOrderResponse;
import com.portalClientesPrimadera.Integration.API.PurchaseOrderRequest;
import com.portalClientesPrimadera.repository.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class APIPurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    @Autowired
    public APIPurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @PostMapping("/crearOrden")
    public ResponseEntity<String> crearOrden(@RequestBody PurchaseOrderRequest orderRequest) {
        ApiPurchaseOrderResponse apiPurchaseOrderResponse = purchaseOrderService.crearOrden(
                orderRequest.getConsecutive(),
                orderRequest.getBuyingPartyName(),
                orderRequest.getTransactionType(),
                orderRequest.getPaymentTerms(),
                orderRequest.getTransactionalCurrencyCode(),
                orderRequest.getSalesperson(),
                orderRequest.getCustomerPONumber(),
                orderRequest.getCustomerAccountId(),
                //orderRequest.getSiteUseId(),
                orderRequest.getPartyId(),
                orderRequest.getSiteId(),
                orderRequest.getLineItems());

        if (apiPurchaseOrderResponse.getResponseCode() == 200 || apiPurchaseOrderResponse.getResponseCode() == 201) {
            // Éxito: Puedes devolver una respuesta exitosa personalizada si lo deseas.
            String successMessage = apiPurchaseOrderResponse.getSuccessMessage();
            return ResponseEntity.ok(successMessage);
        } else {
            // Error: Devuelve el código de respuesta y la respuesta del cuerpo en un formato adecuado.
            return ResponseEntity.status(apiPurchaseOrderResponse.getResponseCode()).body(apiPurchaseOrderResponse.getResponseBody().toString());
        }
    }

}
