package com.portalClientesPrimadera.Integration.API;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

@Getter
@Setter
public class ApiPurchaseOrderResponse {

    private int responseCode;
    private JSONObject responseBody;
    private String successMessage;

    public ApiPurchaseOrderResponse(int responseCode, JSONObject responseBody) {
        this.responseCode = responseCode;
        this.responseBody = responseBody;

    }

    public ApiPurchaseOrderResponse(int responseCode, JSONObject responseBody, String successMessage) {
        this.responseCode = responseCode;
        this.responseBody = responseBody;
        this.successMessage = successMessage;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public JSONObject getResponseBody() {
        return responseBody;
    }

}
