package com.portalClientesPrimadera.Integration.API;
import com.portalClientesPrimadera.repository.PurchaseOrderService;
import com.portalClientesPrimadera.repository.UsersRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class PurchaseOrderServiceImplementation implements PurchaseOrderService {

    @Autowired
    private UsersRepository usersRepository;

        @Override
        public ApiPurchaseOrderResponse crearOrden(
                String consecutive,
                String buyingPartyName,
                String transactionType,
                String paymentTerms,
                String transactionalCurrencyCode,
                String salesperson,
                String customerPONumber,
                long customerAccountId,
                //long siteUseId,
                long partyId,
                long siteId,
                List<PurchaseOrderRequest.LineItem> lineItems) {

            //Variable para poenr la hora
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS");
            String currentDate = sdf.format(new Date());

            try{
                // Crear el cuerpo de la solicitud en formato JSON
                JSONObject requestBody = new JSONObject();

                //Adicionar al requestBody los parametros que no son Arrays
                requestBody.put("SourceTransactionNumber", consecutive);
                requestBody.put("SourceTransactionSystem", "OPS");
                requestBody.put("SourceTransactionId", consecutive);
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
                billToCustomer.put("CustomerAccountId", customerAccountId);
                billToCustomer.put("ContactId", "");//Se debe reibir
               //billToCustomer.put("SiteUseId", siteUseId);//Se debe reibir
                billToCustomerArray.put(billToCustomer);
                System.out.println(billToCustomerArray);

                //Crear un Array para el campo que lleva varios datos
                JSONArray shipToCustomerArray = new JSONArray();
                JSONObject shipToCustomer = new JSONObject();
                shipToCustomer.put("PartyId", partyId);//Se debe reibir
                shipToCustomer.put("ContactId", "");
                shipToCustomer.put("SiteId", siteId);//Se debe reibir
                shipToCustomerArray.put(shipToCustomer);
                System.out.println(shipToCustomerArray);

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
                    line.put("OrderedUOM", lineItem.getOrderedUom());
                    linesArray.put(line);

                    variableParaLineasDePedido = variableParaLineasDePedido + 1;
                }

                //Colocar los Arrays al requestBody
                requestBody.put("billToCustomer", billToCustomerArray);
                requestBody.put("shipToCustomer", shipToCustomerArray);
                requestBody.put("lines", linesArray);
                System.out.println(requestBody);

                // URL del API
                String apiUrl = "https://efdg.fa.us6.oraclecloud.com:443/fscmRestApi/resources/11.13.18.05/salesOrdersForOrderHub?onlyData=true";

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
                    String orderNumber = jsonResponse.getString("OrderNumber"); // Obtener el valor de "OrderNumber"
                    String successMessage = "Orden creada con éxito. Número de orden: " + orderNumber;
                    return new ApiPurchaseOrderResponse(responseCode, jsonResponse, successMessage);

                } else {
                    JSONObject errorResponse = new JSONObject();
                    errorResponse.put("ResponseCode", responseCode);
                    return new ApiPurchaseOrderResponse(responseCode, errorResponse);
                }

            }catch (Exception e) {
                e.printStackTrace();
                JSONObject errorResponse = new JSONObject();
                try {
                    errorResponse.put("message", "Error en el servidor.");
                } catch (JSONException ex) {
                    throw new RuntimeException(ex);
                }
                return new ApiPurchaseOrderResponse(500, errorResponse); // Puedes elegir un código de respuesta adecuado.
            }

        }

}
