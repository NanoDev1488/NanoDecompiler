// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.utils.XmlUtil
package org.freedesktop.dbus.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.freedesktop.dbus.utils.Util;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class XmlUtil {

  private XmlUtil() { // было: <init>
        super();
    }

  public static boolean isElementType(Node arg0) {
        return arg0 instanceof Element;
    }

  public static Element toElement(Node arg0) {
        if (!isElementType(arg0)) {
            return null;
        } else {
            return ((Element) arg0);
        }
    }

  public static NodeList applyXpathExpressionToDocument(String arg0, Node arg1) {
        XPathFactory var2 = XPathFactory.newInstance();
        XPath var3 = var2.newXPath();
        Object var4 = null;
        try {
            var4 = var3.compile(arg0);
        } catch (XPathExpressionException var5) {
            throw new IOException(var5);
        }
    }

  public static Document parseXmlString(String arg0, boolean arg1, boolean arg2) {
        Document __stk1;
        DocumentBuilderFactory var3 = DocumentBuilderFactory.newInstance();
        var3.setNamespaceAware(arg2);
        var3.setValidating(arg1);
        try {
            var3.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            __stk1 = var3.newDocumentBuilder().parse(new ByteArrayInputStream(arg0.getBytes(StandardCharsets.UTF_8)));
        } catch (IOException var4) {
            throw var4;
        } catch (Exception e2) {
            Throwable var4 = e2;
            throw new IOException("Failed to parse " + Util.abbreviate(arg0, 500), var4);
        }
    }

  public static List convertToElementList(NodeList arg0) {
        ArrayList var1 = new ArrayList();
        int var2 = 0;
        while (var2 < arg0.getLength()) {
            Element var3 = ((Element) arg0.item(var2));
            var1.add(var3);
            ++var2;
            continue;
        }
        return var1;
    }

  public static Map convertToAttributeMap(NamedNodeMap arg0) {
        LinkedHashMap var1 = new LinkedHashMap();
        int var2 = 0;
        while (var2 < arg0.getLength()) {
            Node var3 = arg0.item(var2);
            var1.put(var3.getNodeName(), var3.getNodeValue());
            ++var2;
            continue;
        }
        return var1;
    }

}