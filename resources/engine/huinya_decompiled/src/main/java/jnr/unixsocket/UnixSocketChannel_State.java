// исходный (обфусцированный) внутренний класс: jnr.unixsocket.UnixSocketChannel.State
package jnr.unixsocket;

enum UnixSocketChannel_State {

    UNINITIALIZED,
    CONNECTED,
    IDLE,
    CONNECTING;

  private UnixSocketChannel_State() { // было: <init>
        // (пустое тело)
    }

}