// исходный (обфусцированный) внутренний класс: jnr.unixsocket.BindHandler
package jnr.unixsocket;

import java.net.SocketAddress;
import java.nio.channels.AlreadyBoundException;
import java.nio.channels.UnsupportedAddressTypeException;
import java.util.concurrent.atomic.AtomicBoolean;
import jnr.unixsocket.Common;
import jnr.unixsocket.UnixSocketAddress;

final class BindHandler {

    // ---- поля ----
  private final AtomicBoolean bound;

   BindHandler(boolean arg0) { // было: <init>
        super();
        bound = new AtomicBoolean(arg0);
    }

   boolean isBound() {
        return bound.get();
    }

  synchronized UnixSocketAddress bind(int arg0, SocketAddress arg1) {
        if (null == arg1) {
            if (!bound.get()) {
                UnixSocketAddress var3 = Common.bind(arg0, ((UnixSocketAddress) arg1));
                bound.set(true);
                return var3;
            } else {
                throw new AlreadyBoundException();
            }
        } else {
            if (arg1 instanceof UnixSocketAddress) {
                if (!bound.get()) {
                    UnixSocketAddress var3 = Common.bind(arg0, ((UnixSocketAddress) arg1));
                    bound.set(true);
                    return var3;
                } else {
                    throw new AlreadyBoundException();
                }
            } else {
                throw new UnsupportedAddressTypeException();
            }
        }
    }

}