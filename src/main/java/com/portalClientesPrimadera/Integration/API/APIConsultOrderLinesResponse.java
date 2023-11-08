package com.portalClientesPrimadera.Integration.API;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class APIConsultOrderLinesResponse {

    private Long HeaderId;
    private String OrderKey;
    private Long FulfillLineId;
    private String FulfillLineNumber;
    private String SourceTransactionLineNumber;
    private String SourceTransactionNumber;
    private String ProductDescription;
    private Integer OrderedQuantity;
    private String RequestedShipDate;
    private String ActualShipDate;
    private Integer UnitListPrice;
    private Integer UnitSellingPrice;
    private Boolean OnHoldFlag;
    private String StatusCode;
    private String Status;
    private String Message;
    private List<LineDetail> lineDetails;

    @Getter
    @Setter
    @NoArgsConstructor
    public class LineDetail {
        private String BillOfLadingNumber;
        private String BillingTransactionNumber;

        public LineDetail (String BillOfLadingNumber, String BillingTransactionNumber){
            this.BillOfLadingNumber = BillOfLadingNumber;
            this.BillingTransactionNumber = BillingTransactionNumber;
        }
    }

    public APIConsultOrderLinesResponse (
            Long HeaderId,
            String OrderKey,
            Long FulfillLineId,
            String FulfillLineNumber,
            String SourceTransactionLineNumber,
            String SourceTransactionNumber,
            String ProductDescription,
            Integer OrderedQuantity,
            String RequestedShipDate,
            String ActualShipDate,
            Integer UnitListPrice,
            Integer UnitSellingPrice,
            Boolean OnHoldFlag,
            String StatusCode,
            String Status,
            List <LineDetail> lineDetails
            ){
        this.HeaderId = HeaderId;
        this.OrderKey = OrderKey;
        this.FulfillLineId = FulfillLineId;
        this.FulfillLineNumber = FulfillLineNumber;
        this.SourceTransactionLineNumber = SourceTransactionLineNumber;
        this.SourceTransactionNumber = SourceTransactionNumber;
        this.ProductDescription = ProductDescription;
        this.OrderedQuantity = OrderedQuantity;
        this.RequestedShipDate = RequestedShipDate;
        this.ActualShipDate = ActualShipDate;
        this.UnitListPrice = UnitListPrice;
        this.UnitSellingPrice = UnitSellingPrice;
        this.OnHoldFlag = OnHoldFlag;
        this.StatusCode = StatusCode;
        this.Status = Status;
        this.lineDetails = lineDetails;
    }

}
