package com.portalClientesPrimadera.Integration.API;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class APIPostCreatePurchaseOrder {

    public static JSONObject postCreatePurchaseOrder(
            String buyingPartyName,
            String transactionType ,
            String paymentTerms,
            String transactionalCurrencyCode,
            String salesperson,
            String customerPONumber,
            Long customerAccountId,
            Long siteUseId,
            Long partyId,
            Long siteId){

        //Variable temporal para el consecutivo
        String consecutivo = "PCP0011";
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

            JSONArray linesArray = new JSONArray();
            JSONObject lines1 = new JSONObject();
            lines1.put("SourceTransactionLineId", "1");//Se debe reibir
            lines1.put("SourceTransactionLineNumber", "1");//Se debe reibir
            lines1.put("SourceTransactionScheduleId", "1");//Se debe reibir
            lines1.put("SourceScheduleNumber", "1");//Se debe reibir
            lines1.put("TransactionCategoryCode", "ORDER");//Se debe reibir
            lines1.put("RequestedShipDate", currentDate);
            lines1.put("Salesperson", "Territorio 3");//Se debe reibir
            lines1.put("TransactionLineType", "Buy");//Se debe reibir
            lines1.put("ProductNumber", "32130110");//Se debe reibir
            lines1.put("OrderedQuantity", 5);//Se debe reibir
            lines1.put("OrderedUOM", "UN");//Se debe reibir
            linesArray.put(lines1);

            JSONObject lines2 = new JSONObject();
            lines2.put("SourceTransactionLineId", "2");//Se debe reibir
            lines2.put("SourceTransactionLineNumber", "2");//Se debe reibir
            lines2.put("SourceTransactionScheduleId", "2");//Se debe reibir
            lines2.put("SourceScheduleNumber", "2");//Se debe reibir
            lines2.put("TransactionCategoryCode", "ORDER");//Se debe reibir
            lines2.put("RequestedShipDate", currentDate);
            lines2.put("Salesperson", "Territorio 3");//Se debe reibir
            lines2.put("TransactionLineType", "Buy");//Se debe reibir
            lines2.put("ProductNumber", "32130110");//Se debe reibir
            lines2.put("OrderedQuantity", 5);//Se debe reibir
            lines2.put("OrderedUOM", "UN");//Se debe reibir
            linesArray.put(lines2);

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
                return null;
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args){

        JSONObject response = postCreatePurchaseOrder(
                "MADEMAX S.A.S.",
                "LOCAL ORDER",
                "PRI_CRED_30D",
                "COP",
                "Territorio 3",
                "OC:190923",
                100000051076786L,
                300000266668414L,
                100000051076712L,
                300000266668411L);

        if (response != null) {
            System.out.println("Respuesta exitosa: " + response);
        } else {
            System.out.println("Solicitud no se puedo completar");
        }

    }


}
