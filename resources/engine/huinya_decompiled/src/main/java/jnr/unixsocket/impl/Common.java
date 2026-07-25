// исходный (обфусцированный) внутренний класс: jnr.unixsocket.impl.Common
package jnr.unixsocket.impl;

import java.nio.ByteBuffer;
import jnr.constants.platform.Errno;
import jnr.enxio.channels.Native;
import jnr.enxio.channels.NativeException;

final class Common {

    // ---- поля ----
  private int _fd;

   Common(int arg0) { // было: <init>
        super();
        _fd = -1;
        _fd = arg0;
    }

   void setFD(int arg0) {
        _fd = arg0;
    }

   int getFD() {
        return _fd;
    }

   int read(ByteBuffer arg0) {
        ByteBuffer var2 = ByteBuffer.allocate(arg0.remaining());
        int var3 = Native.read(_fd, var2);
        var2.flip();
        arg0.put(var2);
        switch (var3) {
            case 0:
                return -1;
            case -1:
                Errno var4 = Native.getLastError();
                switch (var4) {
                    case EAGAIN:
                    case EWOULDBLOCK:
                        return 0;
                    default:
                        throw new NativeException(Native.getLastErrorString(), var4);
                }
            default:
                return var3;
        }
    }

   long read(ByteBuffer[] arg0, int arg1, int arg2) {
        long var4 = 0L;
        int var6 = 0;
        long var8;
        while (true) {
            if (var6 >= arg2) {
                return var4;
            }
            Object var7 = arg0[arg1 + var6];
            var8 = ((long) read(((ByteBuffer) var7)));
            if (var8 == -1L) {
                break;
            }
            var4 = var4 + var8;
            ++var6;
            continue;
        }
        return var8;
    }

   int write(ByteBuffer arg0) {
        int var2 = arg0.remaining();
        ByteBuffer var3 = ByteBuffer.allocate(var2);
        var3.put(arg0);
        var3.position(0);
        int var4 = Native.write(_fd, var3);
        if (var4 >= 0) {
            if (var4 < var2) {
                arg0.position(arg0.position() - (var2 - var4));
            }
            return var4;
        }
        Errno var5 = Native.getLastError();
        switch (var5) {
            case EAGAIN:
            case EWOULDBLOCK:
                arg0.position(arg0.position() - var2);
                return 0;
            default:
                throw new NativeException(Native.getLastErrorString(), var5);
        }
    }

   long write(ByteBuffer[] arg0, int arg1, int arg2) {
        long var4 = 0L;
        int var6 = arg1;
        while (var6 < arg2) {
            Object var7 = arg0[var6];
            int var8 = var7.remaining();
            int var9 = 0;
            do {
                int var10 = write(((ByteBuffer) var7));
                var9 = var9 + var10;
                if (var10 == 0) {
                    break;
                }
            } while (var9 != var8);
            var4 = var4 + ((long) var9);
            if (var9 >= var8) {
                ++var6;
                continue;
            } else {
                break;
            }
        }
        return var4;
    }

}