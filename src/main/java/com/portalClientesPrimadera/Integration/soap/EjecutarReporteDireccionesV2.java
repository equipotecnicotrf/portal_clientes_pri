package com.portalClientesPrimadera.Integration.soap;

import com.portalClientesPrimadera.model.DireccionReporte;
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
public class EjecutarReporteDireccionesV2 {

    public List<DireccionReporte> ReporteDirecciones(String path, String usuarioERP, String ClaveERP, String name, String item, String name2, String item2 ) {

        List<DireccionReporte> listaDirecciones = new ArrayList<DireccionReporte>();

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

            QName runReportQName = new QName("http://xmlns.oracle.com/oxp/service/v2", "runReport", "v2");
            SOAPElement runReportElement = soapBody.addChildElement(runReportQName);

            QName reportRequestQName = new QName("http://xmlns.oracle.com/oxp/service/v2", "reportRequest", "v2");
            SOAPElement reportRequestElement = runReportElement.addChildElement(reportRequestQName);

            QName reportParameterNameValuesQName = new QName("http://xmlns.oracle.com/oxp/service/v2", "parameterNameValues", "v2");
            SOAPElement reportParameterNameValuesElement = reportRequestElement.addChildElement(reportParameterNameValuesQName);

            QName listOfParamNameValuesQName = new QName("http://xmlns.oracle.com/oxp/service/v2", "listOfParamNameValues", "v2");
            SOAPElement listOfParamNameValuesElement = reportParameterNameValuesElement.addChildElement(listOfParamNameValuesQName);

            QName item1QName = new QName("http://xmlns.oracle.com/oxp/service/v2", "item", "v2");
            SOAPElement item1Element = listOfParamNameValuesElement.addChildElement(item1QName);

            QName name1QName = new QName("http://xmlns.oracle.com/oxp/service/v2", "name", "v2");
            SOAPElement name1Element = item1Element.addChildElement(name1QName);
            name1Element.addTextNode(name);

            QName values1QName = new QName("http://xmlns.oracle.com/oxp/service/v2", "values", "v2");
            SOAPElement values1Element = item1Element.addChildElement(values1QName);

            QName item1_1QName = new QName("http://xmlns.oracle.com/oxp/service/v2", "item", "v2");
            SOAPElement item1_1Element = values1Element.addChildElement(item1_1QName);
            item1_1Element.addTextNode(item);

            QName item2QName = new QName("http://xmlns.oracle.com/oxp/service/v2", "item", "v2");
            SOAPElement item2Element = listOfParamNameValuesElement.addChildElement(item2QName);

            QName name2QName = new QName("http://xmlns.oracle.com/oxp/service/v2", "name", "v2");
            SOAPElement name2Element = item2Element.addChildElement(name2QName);
            name2Element.addTextNode(name2);

            QName values2QName = new QName("http://xmlns.oracle.com/oxp/service/v2", "values", "v2");
            SOAPElement values2Element = item2Element.addChildElement(values2QName);

            QName item2_1QName = new QName("http://xmlns.oracle.com/oxp/service/v2", "item", "v2");
            SOAPElement item2_1Element = values2Element.addChildElement(item2_1QName);
            item2_1Element.addTextNode(item2);

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
                        NodeList g1Elements = document.getDocumentElement().getElementsByTagName("G_1");
                        // Verificar que haya al menos un elemento <G_1>
                        if (g1Elements.getLength() > 0) {
                            // Obtener el primer elemento <G_1>
                            Element g1Element = (Element) g1Elements.item(0);
                            // Obtener todos los elementos <G_4> dentro de <G_1>
                            NodeList g4Elements = g1Element.getElementsByTagName("G_4");

                            // Crear una lista para almacenar los datos de los clientes
                            List<DireccionReporte> direcciones = new ArrayList<>();

                            // Iterar a través de los elementos <G_1>
                            for (int i = 0; i < g4Elements.getLength(); i++) {
                                Element g4Element = (Element) g4Elements.item(i);
                                // Obtener los valores
                                String partySiteUse = XmlUtils.getElementValue(g4Element, "PARTY_SITE_USE");
                                String siteUseId = XmlUtils.getElementValue(g4Element, "SITE_USE_ID");
                                String codVendedor = XmlUtils.getElementValue(g4Element, "COD_VENDEDOR");
                                String nameVendedor = XmlUtils.getElementValue(g4Element, "NAME_VENDEDOR");
                                String siteUseCode = XmlUtils.getElementValue(g4Element, "SITE_USE_CODE");
                                String address1 = XmlUtils.getElementValue(g4Element, "ADDRESS1");
                                String city = XmlUtils.getElementValue(g4Element, "CITY");
                                String state = XmlUtils.getElementValue(g4Element, "STATE");
                                String country = XmlUtils.getElementValue(g4Element, "COUNTRY");
                                String custAccountId = XmlUtils.getElementValue(g4Element, "CUST_ACCOUNT_ID");
                                String partySiteId = XmlUtils.getElementValue(g4Element, "PARTY_SITE_ID");

                                // Crear un objeto Cliente y agregarlo a la lista
                                DireccionReporte direccion = new DireccionReporte(partySiteUse, siteUseId, codVendedor, nameVendedor, siteUseCode, address1, city, state, country, custAccountId, partySiteId);//Cambiarlo por un objeto DireccionReporte
                                direcciones.add(direccion);
                            }
                            /*System.out.println(direcciones);*/
                            return listaDirecciones = direcciones;

                        }else {
                            /*System.out.println("No se encontraron elementos <G_1> en el XML.");//borrar despues de pruebas*/
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                   /* System.out.println("Elemento reportBytes no encontrado en la respuesta.");//Cambiar, se debe manejar de otra manera con un mensaje para el front*/
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDirecciones;
    }

}
