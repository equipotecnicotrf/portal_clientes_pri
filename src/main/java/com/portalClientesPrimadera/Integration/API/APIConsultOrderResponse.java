package com.portalClientesPrimadera.Integration.API;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class APIConsultOrderResponse {

    private Long HeaderId;
    private String OrderKey;
    private String Message;

    public APIConsultOrderResponse(Long HeaderId, String OrderKey) {
        this.HeaderId = HeaderId;
        this.OrderKey = OrderKey;
    }

}
