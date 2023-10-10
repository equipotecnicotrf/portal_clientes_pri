package com.portalClientesPrimadera.Integration.API;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;

public class ConsultaInventarioAPI {

    public static void main(String[] args){
        try {
            //URL servicio API
            String apiURL = "https://efdg-test.fa.us6.oraclecloud.com//fscmRestApi/resources/11.13.18.05/inventoryOnhandBalances";

            // Parametros de autenticacion basica
            String usuario = "INTEGRACION_PRI";
            String contraseña = "Oracle2023*";

            //Codificar credenciales
            String credencialesBase64 = Base64.getEncoder().encodeToString((usuario + ":" + contraseña).getBytes());

            //Parametros de la URL
            String q = "OrganizationCode='PRI04'&q=SubinventoryCode='T_PTP'&q=SummaryLevel='Subinventory";
            String onlyData = "true";
            String orderBy = "ItemNumber";
            String limit = "500";
            String offset = "0";

            //Codificar los parametros
            /*q = URLEncoder.encode(q, "UTF-8");
            onlyData = URLEncoder.encode(onlyData, "UTF-8");
            orderBy = URLEncoder.encode(orderBy, "UTF-8");
            limit = URLEncoder.encode(limit, "UTF-8");
            offset = URLEncoder.encode(offset, "UTF-8");*/

            //Construir la URL con los parametros
            apiURL = apiURL + "?q=" + q + "&onlyData=" + onlyData + "&orderBY=" + orderBy +"&limit=" + limit + "&offset=" + offset;

            //Crear una URL
            URL url = new URL(apiURL);

            // Abrir una conexión HttpURLConnection
           HttpURLConnection connection = (HttpURLConnection) url.openConnection();

           //Configurar la solicitud GET
            connection.setRequestMethod("GET");

            //Agragar encabezados adicionales
            connection.setRequestProperty("REST-Framework-Version", "7");
            connection.setRequestProperty("Content-Type", "application/json");

            //Agregar encabezado de autenticación basica
            connection.setRequestProperty("Authorization", "Basic" + credencialesBase64);

            //Obtener la respuesta del servicio API
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK){
                //Leer la respuesta del serivicio
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                //Imprimir respuesta
                System.out.println("Respuesta del servicio API");
                System.out.println(response.toString());

            }else{
                System.out.println("Error al realizar la solicitud. Codigo de respuesta: " + responseCode);
            }
            //Cerrar conexión
            connection.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}



