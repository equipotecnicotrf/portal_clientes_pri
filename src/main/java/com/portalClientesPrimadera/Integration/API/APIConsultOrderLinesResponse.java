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
    private String SourceTransactionId;
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
    private Long SiteId;
    private String Address1;
    private String City;
    private String State;
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
            String SourceTransactionId,
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
            Long SiteId,
            String Address1,
            String City,
            String State,

            List <LineDetail> lineDetails
            ){
        this.HeaderId = HeaderId;
        this.OrderKey = OrderKey;
        this.SourceTransactionId = SourceTransactionId;
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
        this.SiteId = SiteId;
        this.Address1 = Address1;
        this.City = City;
        this.State = State;
        this.lineDetails = lineDetails;
    }

}