package com.portalClientesPrimadera.repository;
import com.portalClientesPrimadera.Integration.API.ApiPurchaseOrderResponse;
import com.portalClientesPrimadera.Integration.API.PurchaseOrderRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderService {

    ApiPurchaseOrderResponse crearOrden(String consecutive, String buyingPartyName, String transactionType, String paymentTerms, String transactionalCurrencyCode,
                                        String salesperson, String customerPONumber, long customerAccountId, long siteUseId, long partyId, long siteId,
                                        List<PurchaseOrderRequest.LineItem> lineItems);

}
