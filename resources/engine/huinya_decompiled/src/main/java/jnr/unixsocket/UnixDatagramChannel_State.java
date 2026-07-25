// исходный (обфусцированный) внутренний класс: jnr.unixsocket.UnixDatagramChannel.State
package jnr.unixsocket;

enum UnixDatagramChannel_State {

    UNINITIALIZED,
    CONNECTED,
    IDLE;

  private UnixDatagramChannel_State() { // было: <init>
        // (пустое тело)
    }

}