package com.portalClientesPrimadera.Integration.API;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class APIConsultAddressForOrderResponse {

    private Long SiteId;
    private String Address1;
    private String City;
    private String State;


}
