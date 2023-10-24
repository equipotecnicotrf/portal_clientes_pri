package com.portalClientesPrimadera.Integration.API;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class APIPostCreatePurchaseOrderV2 {

    public static JSONObject postCreatePurchaseOrder(PurchaseOrderRequest request){
        String buyingPartyName = request.getBuyingPartyName();
        String transactionType = request.getTransactionType();
        String paymentTerms = request.getPaymentTerms();
        String transactionalCurrencyCode = request.getTransactionalCurrencyCode();
        String salesperson = request.getSalesperson();
        String customerPONumber = request.getCustomerPONumber();
        Long customerAccountId = request.getCustomerAccountId();
        Long siteUseId = request.getSiteUseId();
        Long partyId = request.getPartyId();
        Long siteId = request.getSiteId();
        List<PurchaseOrderRequest.LineItem> lineItems = request.getLineItems();

        //Variable temporal para el consecutivo
        String consecutivo = "PCP012";
        //Variable para poenr la hora
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS");
        String currentDate = sdf.format(new Date());

        try{
            // Crear el cuerpo de la solicitud en formato JSON
            JSONObject requestBody = new JSONObject();

            //Adicionar al requestBody los parametros que no son Arrays
            requestBody.put("SourceTransactionNumber", consecutivo);
            requestBody.put("SourceTransactionSystem", "OPS");
            requestBody.put("SourceTransactionId", consecutivo);
            requestBody.put("BusinessUnitName", "PRI_UNIDAD_NEGOCIO");
            requestBody.put("BuyingPartyName", buyingPartyName);//Se debe recibir
            requestBody.put("TransactionType", transactionType);//Se debe recibir
            requestBody.put("RequestedShipDate", currentDate);
            requestBody.put("RequestedFulfillmentOrganizationCode", "PRI04");
            requestBody.put("PaymentTerms", paymentTerms);//Se debe recibir
            requestBody.put("TransactionalCurrencyCode", transactionalCurrencyCode);//Se debe recibir
            requestBody.put("RequestingBusinessUnitName", "PRI_UNIDAD_NEGOCIO");
            requestBody.put("FreezePriceFlag", false);
            requestBody.put("FreezeShippingChargeFlag", false);
            requestBody.put("FreezeTaxFlag", false);
            requestBody.put("SubmittedFlag", false);
            requestBody.put("PartialShipAllowedFlag", true);
            requestBody.put("ShipsetFlag", false);
            requestBody.put("SourceTransactionRevisionNumber", 1);
            requestBody.put("Salesperson", salesperson);//Se debe recibir
            requestBody.put("CustomerPONumber", customerPONumber);//Se debe recibir

            //Crear un Array para el campo que lleva varios datos
            JSONArray billToCustomerArray = new JSONArray();
            JSONObject billToCustomer = new JSONObject();
            billToCustomer.put("CustomerAccountId", customerAccountId);//Se debe reibir
            billToCustomer.put("SiteUseId", siteUseId);//Se debe reibir
            billToCustomerArray.put(billToCustomer);

            //Crear un Array para el campo que lleva varios datos
            JSONArray shipToCustomerArray = new JSONArray();
            JSONObject shipToCustomer = new JSONObject();
            shipToCustomer.put("PartyId", partyId);//Se debe reibir
            shipToCustomer.put("SiteId", siteId);//Se debe reibir
            shipToCustomerArray.put(shipToCustomer);

            int variableParaLineasDePedido = 1;

            JSONArray linesArray = new JSONArray();
            for (PurchaseOrderRequest.LineItem lineItem : lineItems) {
                JSONObject line = new JSONObject();
                line.put("SourceTransactionLineId", String.valueOf(variableParaLineasDePedido));
                line.put("SourceTransactionLineNumber", String.valueOf(variableParaLineasDePedido));
                line.put("SourceTransactionScheduleId", String.valueOf(variableParaLineasDePedido));
                line.put("SourceScheduleNumber", String.valueOf(variableParaLineasDePedido));
                line.put("TransactionCategoryCode", "ORDER");
                line.put("RequestedShipDate", currentDate);
                line.put("Salesperson", salesperson);
                line.put("TransactionLineType", "Buy");
                line.put("ProductNumber", lineItem.getProductNumber());
                line.put("OrderedQuantity", lineItem.getOrderedQuantity());
                line.put("OrderedUOM", "UN");
                linesArray.put(line);

                variableParaLineasDePedido = variableParaLineasDePedido + 1;
            }

            //Colocar los Arrays al requestBody
            requestBody.put("billToCustomer", billToCustomerArray);
            requestBody.put("shipToCustomer", shipToCustomerArray);
            requestBody.put("lines", linesArray);

            // URL del API
            String apiUrl = "https://efdg-test.fa.us6.oraclecloud.com:443/fscmRestApi/resources/11.13.18.05/salesOrdersForOrderHub?onlyData=true";

            // Usuario y contraseña para la autenticación básica
            String username = "INTEGRACION_PRI";
            String password = "Oracle2023*";

            // Crear la URL
            URL url = new URL(apiUrl);

            // Abrir una conexión HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurar encabezados
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Autenticación básica
            String authString = username + ":" + password;
            String encodedAuthString = Base64.getEncoder().encodeToString(authString.getBytes());
            connection.setRequestProperty("Authorization", "Basic " + encodedAuthString);

            // Configurar el tipo de contenido
            connection.setRequestProperty("Content-Type", "application/json");

            // Obtener un flujo de salida para escribir los datos en el cuerpo de la solicitud
            OutputStream os = connection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            osw.write(requestBody.toString());
            osw.flush();
            osw.close();

            // Obtener la respuesta del servidor
            int responseCode = connection.getResponseCode();

            if (responseCode == 200 || responseCode == 201) {
                // Leer la respuesta del servidor
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                JSONObject jsonResponse = new JSONObject(response.toString());
                return jsonResponse;

            } else {
                JSONObject errorResponse = new JSONObject();
                errorResponse.put("ResponseCode", responseCode);
                return errorResponse;
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        // Crear un JSON de ejemplo
        String jsonExample = " {\n" +
                "    \"buyingPartyName\": \"MADEMAX S.A.S.\",\n" +
                "    \"transactionType\": \"LOCAL ORDER\",\n" +
                "    \"paymentTerms\": \"PRI_CRED_30D\",\n" +
                "    \"transactionalCurrencyCode\": \"COP\",\n" +
                "    \"salesperson\": \"Territorio 3\",\n" +
                "    \"customerPONumber\": \"OC:190923\",\n" +
                "    \"customerAccountId\": 100000051076786,\n" +
                "    \"siteUseId\": 300000266668414,\n" +
                "    \"partyId\": 100000051076712,\n" +
                "    \"siteId\": 300000266668411,\n" +
                "    \"lineItems\": [\n" +
                "        {\n" +
                "            \"ProductNumber\": \"32130110\",\n" +
                "            \"OrderedQuantity\": 8\n" +
                "        },\n" +
                "        {\n" +
                "            \"ProductNumber\": \"32130110\",\n" +
                "            \"OrderedQuantity\": 3\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        // Parsear el JSON de ejemplo
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            PurchaseOrderRequest request = objectMapper.readValue(jsonExample, PurchaseOrderRequest.class);
            JSONObject response = postCreatePurchaseOrder(request);

            if (response != null) {
                System.out.println("Respuesta exitosa: " + response);
            } else {
                System.out.println("Solicitud no se pudo completar.");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


}
