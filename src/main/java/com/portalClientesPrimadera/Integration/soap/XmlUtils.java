package com.portalClientesPrimadera.Integration.soap;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XmlUtils {

    // Método para obtener el contenido de un elemento si existe
    public static String getElementValue(Element parentElement, String tagName) {
        NodeList nodeList = parentElement.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        } else {
            return "";
        }
    }

}
