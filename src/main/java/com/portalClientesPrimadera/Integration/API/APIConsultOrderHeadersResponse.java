package com.portalClientesPrimadera.Integration.API;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class APIConsultOrderHeadersResponse {

    private Long HeaderId;
    private String SourceTransactionId;
    private String OrderKey;
    private String Message;

    public APIConsultOrderHeadersResponse(Long HeaderId, String SourceTransactionId, String OrderKey) {
        this.HeaderId = HeaderId;
        this.SourceTransactionId = SourceTransactionId;
        this.OrderKey = OrderKey;
    }

}
