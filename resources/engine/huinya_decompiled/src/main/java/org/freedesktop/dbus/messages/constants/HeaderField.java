// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.messages.constants.HeaderField
package org.freedesktop.dbus.messages.constants;

public final class HeaderField {

    // ---- поля ----
  public static final int MAX_FIELDS = 10;
  public static final byte PATH = 1;
  public static final byte INTERFACE = 2;
  public static final byte MEMBER = 3;
  public static final byte ERROR_NAME = 4;
  public static final byte REPLY_SERIAL = 5;
  public static final byte DESTINATION = 6;
  public static final byte SENDER = 7;
  public static final byte SIGNATURE = 8;
  public static final byte UNIX_FDS = 9;

  private HeaderField() { // было: <init>
        super();
    }

}