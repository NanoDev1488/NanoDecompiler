// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.messages.constants.MessageType
package org.freedesktop.dbus.messages.constants;

public final class MessageType {

    // ---- поля ----
  public static final byte METHOD_CALL = 1;
  public static final byte METHOD_RETURN = 2;
  public static final byte ERROR = 3;
  public static final byte SIGNAL = 4;

  private MessageType() { // было: <init>
        super();
    }

}