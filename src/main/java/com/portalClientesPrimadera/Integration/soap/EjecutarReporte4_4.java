package com.portalClientesPrimadera.Integration.soap;

import com.portalClientesPrimadera.model.Cliente;
import jakarta.xml.soap.*;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class EjecutarReporte4_4 {

    public List<Cliente> ReporteClientes(String path, String usuarioERP, String ClaveERP) {
        List<Cliente> listaClientes = new ArrayList<Cliente>();
        try {
            // Crear una conexión SOAP
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection connection = soapConnectionFactory.createConnection();

            // Crear un mensaje SOAP
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage soapMessage = messageFactory.createMessage();

            // Agregar el encabezado SOAP
            SOAPPart soapPart = soapMessage.getSOAPPart();
            SOAPEnvelope envelope = soapPart.getEnvelope();
            envelope.addNamespaceDeclaration("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");

            // Agregar el cuerpo SOAP
            SOAPBody soapBody = envelope.getBody();

            // Crear elementos SOAP para la solicitud
            QName runReportQName = new QName("http://xmlns.oracle.com/oxp/service/v2", "runReport", "v2");
            SOAPElement runReportElement = soapBody.addChildElement(runReportQName );

            QName reportRequestQName = new QName("http://xmlns.oracle.com/oxp/service/v2", "reportRequest", "v2");
            SOAPElement reportRequestElement = runReportElement.addChildElement(reportRequestQName);

            QName reportAbsolutePathQName = new QName("http://xmlns.oracle.com/oxp/service/v2", "reportAbsolutePath", "v2");
            SOAPElement reportAbsolutePathElement = reportRequestElement.addChildElement(reportAbsolutePathQName);
            reportAbsolutePathElement.addTextNode(path);

            QName userIDQName = new QName("http://xmlns.oracle.com/oxp/service/v2", "userID", "v2");
            SOAPElement userIDElement = runReportElement.addChildElement(userIDQName);
            userIDElement.addTextNode(usuarioERP);

            QName passwordQName = new QName("http://xmlns.oracle.com/oxp/service/v2", "password", "v2");
            SOAPElement passwordElement = runReportElement.addChildElement(passwordQName);
            passwordElement.addTextNode(ClaveERP);

            // Imprimir la solicitud SOAP completa
            System.out.println("Solicitud SOAP:");
            soapMessage.writeTo(System.out);

            // Enviar la solicitud SOAP al servicio web
            String endpointUrl = "https://efdg-test.fa.us6.oraclecloud.com:443/xmlpserver/services/v2/ReportService";
            SOAPMessage response = connection.call(soapMessage, endpointUrl);

            // Procesar la respuesta del servicio web
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            response.writeTo(outputStream);
            String responseXml = outputStream.toString();

            // Imprimir la respuesta en la consola
            System.out.println("Respuesta del servicio web:");
            System.out.println(responseXml);

            // Procesar el contenido de reportBytes
            try {
                SOAPElement reportBytesElement = (SOAPElement) response.getSOAPBody()
                        .getElementsByTagName("reportBytes")
                        .item(0); // Suponiendo que solo haya un elemento reportBytes en la respuesta

                if (reportBytesElement != null) {
                    // Obtener todos los nodos secundarios como una lista de nodos
                    NodeList reportBytesChildren = reportBytesElement.getChildNodes();

                    // Crear un StringBuilder para almacenar todos los datos de los nodos secundarios
                    StringBuilder reportContentBuilder = new StringBuilder();

                    for (int i = 0; i < reportBytesChildren.getLength(); i++) {
                        org.w3c.dom.Node childNode = reportBytesChildren.item(i);

                        // Verificar que el nodo sea un nodo de texto
                        if (childNode.getNodeType() == Node.TEXT_NODE) {
                            // Agregar el contenido de texto al StringBuilder
                            reportContentBuilder.append(childNode.getNodeValue());
                        }
                    }

                    // El contenido completo de reportBytes se encuentra en reportContentBuilder como una cadena
                    String reportContent = reportContentBuilder.toString();

                    byte[] decodedBytes = Base64.getDecoder().decode(reportContent);
                    // Aquí puedes trabajar con reportContent, como guardarla en un archivo o procesarla de alguna otra manera
                    String decodedText = new String(decodedBytes, "UTF-8");

                    /*System.out.println("Respuesta del servicio web:");
                    System.out.println(decodedText);*/

                    try {
                        // Crear un analizador de documentos XML
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();

                        // Convertir la respuesta XML en un objeto Document
                        ByteArrayInputStream input = new ByteArrayInputStream(decodedText.getBytes("UTF-8"));
                        Document document = builder.parse(input);

                        // Obtener todos los elementos <G_1>
                        NodeList g1Elements = document.getElementsByTagName("G_1");

                        // Crear una lista para almacenar los datos de los clientes
                        List<Cliente> clientes = new ArrayList<>();

                        // Iterar a través de los elementos <G_1>
                        for (int i = 0; i < g1Elements.getLength(); i++) {
                            Element g1Element = (Element) g1Elements.item(i);

                            // Obtener los valores de ACCOUNT_NAME, HZ_CUST_ACCOUNT_ID y PARTY_ID
                            String accountName = g1Element.getElementsByTagName("ACCOUNT_NAME").item(0).getTextContent();
                            String custAccountId = g1Element.getElementsByTagName("HZ_CUST_ACCOUNT_ID").item(0).getTextContent();
                            String partyId = g1Element.getElementsByTagName("PARTY_ID").item(0).getTextContent();

                            // Crear un objeto Cliente y agregarlo a la lista
                            Cliente cliente = new Cliente (accountName, custAccountId, partyId);
                            clientes.add(cliente);
                        }

                        return listaClientes = clientes;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    System.out.println("Elemento reportBytes no encontrado en la respuesta.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Cerrar la conexión SOAP
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaClientes;
    }

}
