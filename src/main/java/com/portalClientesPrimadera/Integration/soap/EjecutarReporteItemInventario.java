package com.portalClientesPrimadera.Integration.soap;

import com.portalClientesPrimadera.model.DireccionReporte;
import com.portalClientesPrimadera.model.ItemReporte;
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
public class EjecutarReporteItemInventario {

    public List<ItemReporte> ReporteItemsInventario(String path, String usuarioERP, String ClaveERP, String name, String item) {

        List<ItemReporte> listaItemInventario = new ArrayList<ItemReporte>();

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

            //Recibe el nombre del parametro 1
            QName name1QName = new QName("http://xmlns.oracle.com/oxp/service/v2", "name", "v2");
            SOAPElement name1Element = item1Element.addChildElement(name1QName);
            name1Element.addTextNode(name);

            QName values1QName = new QName("http://xmlns.oracle.com/oxp/service/v2", "values", "v2");
            SOAPElement values1Element = item1Element.addChildElement(values1QName);

            //Recibe el valor del parametro 1
            QName item1_1QName = new QName("http://xmlns.oracle.com/oxp/service/v2", "item", "v2");
            SOAPElement item1_1Element = values1Element.addChildElement(item1_1QName);
            item1_1Element.addTextNode(item);

            //Recibe el path del reporte
            QName reportAbsolutePathQName = new QName("http://xmlns.oracle.com/oxp/service/v2", "reportAbsolutePath", "v2");
            SOAPElement reportAbsolutePathElement = reportRequestElement.addChildElement(reportAbsolutePathQName);
            reportAbsolutePathElement.addTextNode(path);

            //Recibe el usuario del ERP
            QName userIDQName = new QName("http://xmlns.oracle.com/oxp/service/v2", "userID", "v2");
            SOAPElement userIDElement = runReportElement.addChildElement(userIDQName);
            userIDElement.addTextNode(usuarioERP);

            //Recibe la clave del usuario
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

                            // Crear una lista para almacenar los datos de los clientes
                            List<ItemReporte> items = new ArrayList<>();

                            // Iterar a través de los elementos <G_1>
                            for (int i = 0; i < g1Elements.getLength(); i++) {
                                Element g1Element = (Element) g1Elements.item(i);
                                // Obtener los valores
                                String inventoryItemId = XmlUtils.getElementValue(g1Element, "INVENTORY_ITEM_ID");
                                String Attribute1 = XmlUtils.getElementValue(g1Element, "ATTRIBUTE1");
                                String Attribute2 = XmlUtils.getElementValue(g1Element, "ATTRIBUTE2");
                                String Attribute3 = XmlUtils.getElementValue(g1Element, "ATTRIBUTE3");
                                String Attribute4 = XmlUtils.getElementValue(g1Element, "ATTRIBUTE4");
                                String Attribute5 = XmlUtils.getElementValue(g1Element, "ATTRIBUTE5");
                                String Attribute6 = XmlUtils.getElementValue(g1Element, "ATTRIBUTE6");
                                String Attribute7 = XmlUtils.getElementValue(g1Element, "ATTRIBUTE7");
                                String Attribute8 = XmlUtils.getElementValue(g1Element, "ATTRIBUTE8");
                                String Attribute9 = XmlUtils.getElementValue(g1Element, "ATTRIBUTE9");
                                String UnitOfMeasure = XmlUtils.getElementValue(g1Element, "UNIT_OF_MEASURE");
                                String itemDescription = XmlUtils.getElementValue(g1Element, "ITEM_DESCRIPTION");
                                String itemDescriptionLong = XmlUtils.getElementValue(g1Element, "ITEM_DESCRIPTION_LONG");
                                String itemNumber = XmlUtils.getElementValue(g1Element, "ITEM_NUMBER");
                                String UOMCode = XmlUtils.getElementValue(g1Element, "UOM_CODE");
                                String OrganizationId = XmlUtils.getElementValue(g1Element, "ORGANIZATION_ID");
                                String inventoryItemStatusCode = XmlUtils.getElementValue(g1Element, "INVENTORY_ITEM_STATUS_CODE");

                                // Crear un objeto Cliente y agregarlo a la lista
                                ItemReporte itemInventario = new ItemReporte(inventoryItemId, itemNumber, itemDescription, itemDescriptionLong, UOMCode, UnitOfMeasure, OrganizationId,
                                        Attribute1, Attribute2, Attribute3, Attribute4, Attribute5, Attribute6, Attribute7, Attribute8, Attribute9,
                                           inventoryItemStatusCode);//Cambiarlo por un objeto itemReporte
                                items.add(itemInventario);
                            }
                            /*System.out.println(direcciones);*/
                            return listaItemInventario = items;



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
        return listaItemInventario;
    }

}
