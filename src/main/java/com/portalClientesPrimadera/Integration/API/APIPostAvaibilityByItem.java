package com.portalClientesPrimadera.Integration.API;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class APIPostAvaibilityByItem {

    public static Long getAvailableToTransact(String OrganizationCode, String Subinventory, String ItemNumber ) {
        try {
            // URL del API
            String apiUrl = "https://efdg-test.fa.us6.oraclecloud.com/fscmRestApi/resources/11.13.18.05/availableQuantityDetails";

            // Usuario y contraseña para la autenticación básica
            String username = "INTEGRACION_PRI";
            String password = "Oracle2023*";

            // Parámetros en el cuerpo de la solicitud
            JSONObject requestBody = new JSONObject();
            requestBody.put("OrganizationCode", OrganizationCode);
            requestBody.put("Subinventory", Subinventory);
            requestBody.put("ItemNumber", ItemNumber);

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

            // Crear el cuerpo de la solicitud
            //String requestBody = "organizationCode=" + organizationCode + "&subinventory=" + subinventory + "&itemNumber=" + itemNumber;

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
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();

                // Analizar la respuesta JSON
                JSONObject jsonResponse = new JSONObject(response.toString());

                // Obtener el valor de "AvailableToTransact"
                Long availableToTransact = jsonResponse.getLong("AvailableToTransact");

                // Imprimir la respuesta
               // System.out.println("Respuesta del servidor: " + response.toString());
               // System.out.println("Available to transact is: " + availableToTransact);

                return availableToTransact;
            } else {
                System.out.println("La solicitud no fue exitosa. Código de respuesta: " + responseCode);
            }

            // Cerrar la conexión
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (long) -1;
    }

}
