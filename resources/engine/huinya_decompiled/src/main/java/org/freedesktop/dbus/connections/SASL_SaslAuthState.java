// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.SASL.SaslAuthState
package org.freedesktop.dbus.connections;

enum SASL_SaslAuthState {

    INITIAL_STATE,
    WAIT_DATA,
    WAIT_OK,
    WAIT_REJECT,
    WAIT_AUTH,
    WAIT_BEGIN,
    NEGOTIATE_UNIX_FD,
    FINISHED,
    FAILED;

  private SASL_SaslAuthState() { // было: <init>
        // (пустое тело)
    }

}