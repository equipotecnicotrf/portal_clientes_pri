package com.portalClientesPrimadera.Integration.API;

import org.json.JSONArray;
import org.json.JSONException;
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

public class APIPostSalesOrderForPrices {

    public static JSONObject postSalesOrderForPrices(Long customerid, Long inventoryitemid) {
        try {
            // URL del API
            String apiUrl = "https://efdg-test.fa.us6.oraclecloud.com//fscmRestApi/priceExecution/documentPrices/priceSalesTransaction";

            // Usuario y contraseña para la autenticación básica
            String username = "INTEGRACION_PRI";
            String password = "Oracle2023*";

            // Crear el cuerpo de la solicitud en formato JSON
            JSONObject requestBody = new JSONObject();

            // Header
            JSONArray headerArray = new JSONArray();
            JSONObject header = new JSONObject();
            header.put("CalculatePricingChargesFlag", true);
            header.put("CalculateTaxFlag", false);
            header.put("HeaderId", 1);
            header.put("SellingBusinessUnitId", 300000192811845L);
            header.put("TransactionTypeCode", "ORA_SALES_ORDER");
            header.put("CustomerId", customerid);//se recibe el PRTY_ID del USER

            // Formatear la fecha actual al formato deseado
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS");
            String currentDate = sdf.format(new Date());

            header.put("TransactionOn", currentDate);//el formato de fecha necesario es: 2023-09-07T10:40:51.88
            headerArray.put(header);

            // Line
            JSONArray lineArray = new JSONArray();
            JSONObject line = new JSONObject();
            line.put("HeaderId", 1);
            line.put("InventoryItemId", inventoryitemid);//se recibe el INVENTORY_ITEM_ID de la tabla ITEMS
            line.put("InventoryOrganizationId", 300000192342222L);
            line.put("LineCategoryCode", "ORDER");
            line.put("LineId", 1001);
            line.put("LineQuantityUOMCode", "zzx");

            JSONObject lineQuantity = new JSONObject();
            lineQuantity.put("Value", 1);
            lineQuantity.put("UomCode", "zzx");

            line.put("LineQuantity", lineQuantity);
            line.put("LineTypeCode", "ORA_BUY");
            lineArray.put(line);

            // PricingServiceParameter
            JSONObject pricingServiceParameter = new JSONObject();
            pricingServiceParameter.put("PricingContext", "SALES");

            requestBody.put("Header", headerArray);
            requestBody.put("Line", lineArray);
            requestBody.put("PricingServiceParameter", pricingServiceParameter);

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

                in.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                // Accede a la matriz ChargeComponent

                //if (jsonResponse.has("ChargeComponent")) {
                JSONArray chargeComponents = jsonResponse.getJSONArray("ChargeComponent");

                if (chargeComponents.length() > 0) {

                    header = jsonResponse.getJSONArray("Header").getJSONObject(0);
                    long customerId = header.getLong("CustomerId");

                    // Accede al objeto "Line" desde el inicio de la respuesta
                    line = jsonResponse.getJSONArray("Line").getJSONObject(0);
                    long inventoryItemId = line.getLong("InventoryItemId");
                    long InventoryOrganizationId = line.getLong("InventoryOrganizationId");

                    for (int i = 0; i < chargeComponents.length(); i++) {
                        JSONObject chargeComponent = chargeComponents.getJSONObject(i);
                        //int chargeComponentId = chargeComponent.getInt("ChargeComponentId");
                        if (chargeComponent.has("PriceElementCode") && chargeComponent.has("PriceElementUsageCode")) {
                            String PriceElementCode = chargeComponent.optString("PriceElementCode");
                            String PriceElementUsageCode = chargeComponent.optString("PriceElementUsageCode");

                            if ("QP_NET_PRICE".equals(PriceElementCode) && "NET_PRICE".equals(PriceElementUsageCode)) {
                                // Accede al valor de ExtendedAmount dentro del objeto encontrado
                                JSONObject unitPrice = chargeComponent.optJSONObject("UnitPrice");

                                if (unitPrice != null) { // Verifica si el objeto unitPrice es nulo
                                    double Value = unitPrice.optDouble("Value");
                                    String currencyCode = unitPrice.optString("CurrencyCode");

                                    JSONObject result = new JSONObject();
                                    result.put("CustomerId", customerId);
                                    result.put("InventoryItemId", inventoryItemId);
                                    result.put("InventoryOrganizationId", InventoryOrganizationId);
                                    result.put("CurrencyCode", currencyCode);
                                    result.put("Value", Value);

                                    // Agregar el objeto 'result' a 'response'
                                    return result;
                                }
                            }
                        }
                    }
                }
                 else {
                    // Devolver un valor predeterminado o manejarlo según tus necesidades
                    return null;
                }
            }             // Cerrar la conexión
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
