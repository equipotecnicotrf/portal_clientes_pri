package com.portalClientesPrimadera.Integration.API;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PurchaseOrderRequest {

    private String buyingPartyName;
    private String transactionType;
    private String paymentTerms;
    private String transactionalCurrencyCode;
    private String salesperson;
    private String customerPONumber;
    private Long customerAccountId;
    private Long siteUseId;
    private Long partyId;
    private Long siteId;
    private List<LineItem> lineItems;


    @Getter
    @Setter
    public class LineItem {
        private String productNumber;
        private int orderedQuantity;

    }

}
