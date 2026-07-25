// исходный (обфусцированный) внутренний класс: jnr.unixsocket.UnixSocket.UnselectableByteChannel
package jnr.unixsocket;

import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import jnr.unixsocket.UnixSocketChannel;

final class UnixSocket_UnselectableByteChannel implements ReadableByteChannel, WritableByteChannel {

    // ---- поля ----
  private final UnixSocketChannel channel;

   UnixSocket_UnselectableByteChannel(UnixSocketChannel arg0) { // было: <init>
        super();
        channel = arg0;
    }

  public int write(ByteBuffer arg0) {
        return channel.write(arg0);
    }

  public int read(ByteBuffer arg0) {
        return channel.read(arg0);
    }

  public boolean isOpen() {
        return channel.isOpen();
    }

  public void close() {
        channel.close();
    }

}