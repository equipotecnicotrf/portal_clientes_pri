package com.portalClientesPrimadera.Integration.soap;

public class EjecutarReportes{
     public static void ReporteClientes(String path, String usuarioERP, String ClaveERP){
         try {
                String send = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v2=\"http://xmlns.oracle.com/oxp/service/v2\">\n" +
                     "<soapenv:Header/>\n" +
                     "<soapenv:Body>\n" +
                     "<v2:runReport>\n" +
                     "<v2:reportRequest>\n" +
                     "<v2:reportAbsolutePath>"+path+"</v2:reportAbsolutePath>\n" +
                     "</v2:reportRequest>\n" +
                     "<v2:userID>"+usuarioERP+"</v2:userID>\n" +
                     "<v2:password>"+ClaveERP+"</v2:password>\n" +
                     "</v2:runReport>\n" +
                     "</soapenv:Body>\n" +
                     "</soapenv:Envelope>";
                     

         }catch (Exception e) {
             e.printStackTrace();
         }
     }

}
