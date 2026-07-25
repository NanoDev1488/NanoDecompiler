// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.transports.TransportBuilder.SaslAuthMode
package org.freedesktop.dbus.connections.transports;

public enum TransportBuilder_SaslAuthMode {

    AUTH_ANONYMOUS(4),
    AUTH_COOKIE(2),
    AUTH_EXTERNAL(1);

    // ---- поля ----
  private final int authMode;

  private TransportBuilder_SaslAuthMode(int arg2) { // было: <init>
        authMode = arg2;
    }

  public int getAuthMode() {
        return authMode;
    }

}