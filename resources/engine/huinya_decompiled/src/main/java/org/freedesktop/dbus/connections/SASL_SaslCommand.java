// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.SASL.SaslCommand
package org.freedesktop.dbus.connections;

public enum SASL_SaslCommand {

    AUTH,
    DATA,
    REJECTED,
    OK,
    BEGIN,
    CANCEL,
    ERROR,
    NEGOTIATE_UNIX_FD,
    AGREE_UNIX_FD;

  private SASL_SaslCommand() { // было: <init>
        // (пустое тело)
    }

}