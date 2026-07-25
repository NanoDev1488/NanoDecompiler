// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.utils.XmlErrorHandlers.XmlErrorHandlerRuntimeException
package org.freedesktop.dbus.utils;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class XmlErrorHandlers_XmlErrorHandlerRuntimeException implements ErrorHandler {

  public XmlErrorHandlers_XmlErrorHandlerRuntimeException() { // было: <init>
        super();
    }

  public void warning(SAXParseException arg0) {
        throw new RuntimeException(arg0);
    }

  public void error(SAXParseException arg0) {
        throw new RuntimeException(arg0);
    }

  public void fatalError(SAXParseException arg0) {
        throw new RuntimeException(arg0);
    }

}