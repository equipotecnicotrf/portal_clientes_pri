package com.portalClientesPrimadera.Integration.API;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class APIConsultOrderLinesResponse {

    private Long HeaderId;
    private String OrderKey;
    private Long FulfillLineId;
    private String SourceTransactionNumber;
    private Integer OrderedQuantity;
    private String RequestedShipDate;
    private Integer UnitListPrice;
    private Integer UnitSellingPrice;
    private String Message;

    public APIConsultOrderLinesResponse (
            Long HeaderId,
            String OrderKey,
            Long FulfillLineId,
            String SourceTransactionNumber,
            Integer OrderedQuantity,
            String RequestedShipDate,
            Integer UnitListPrice,
            Integer UnitSellingPrice
            ){
        this.HeaderId = HeaderId;
        this.OrderKey = OrderKey;
        this.FulfillLineId = FulfillLineId;
        this.SourceTransactionNumber = SourceTransactionNumber;
        this.OrderedQuantity = OrderedQuantity;
        this.RequestedShipDate = RequestedShipDate;
        this.UnitListPrice = UnitListPrice;
        this.UnitSellingPrice = UnitSellingPrice;
    }

}
