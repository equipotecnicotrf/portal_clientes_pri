package com.portalClientesPrimadera.Integration.API;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderRequest {

    private String consecutive;
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
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LineItem {
        private String productNumber;
        private int orderedQuantity;
        private String orderedUom;

    }

}
