// исходный (обфусцированный) внутренний класс: jnr.enxio.channels.Native
package jnr.enxio.channels;

import java.nio.ByteBuffer;
import jnr.constants.platform.Errno;
import jnr.enxio.channels.NativeException;
import jnr.enxio.channels.Native_LibC;
import jnr.enxio.channels.Native_SingletonHolder;
import jnr.ffi.LastError;
import jnr.ffi.Runtime;

public final class Native {

  public Native() { // было: <init>
        super();
    }

  static Native_LibC libc() {
        return Native_SingletonHolder.libc;
    }

  static Runtime getRuntime() {
        return Native_SingletonHolder.runtime;
    }

  public static int close(int arg0) {
        int var1;
        do {
            var1 = libc().close(arg0);
            if (var1 >= 0) {
                break;
            }
        } while (Errno.EINTR.equals(getLastError()));
        if (var1 >= 0) {
            return var1;
        } else {
            String var2 = String.format("Error closing fd %d: %s", new Object[]{Integer.valueOf(arg0), getLastErrorString()});
            throw new NativeException(var2, getLastError());
        }
    }

  public static int read(int arg0, ByteBuffer arg1) {
        if (arg1 == null) {
            throw new NullPointerException("Destination buffer cannot be null");
        }
        int var2;
        if (!arg1.isReadOnly()) {
            do {
                var2 = libc().read(arg0, arg1, ((long) arg1.remaining()));
                if (var2 >= 0) {
                    break;
                }
            } while (Errno.EINTR.equals(getLastError()));
        } else {
            throw new IllegalArgumentException("Read-only buffer");
        }
        if (var2 > 0) {
            arg1.position(arg1.position() + var2);
        }
        return var2;
    }

  public static int write(int arg0, ByteBuffer arg1) {
        int var2;
        if (arg1 != null) {
            do {
                var2 = libc().write(arg0, arg1, ((long) arg1.remaining()));
                if (var2 >= 0) {
                    break;
                }
            } while (Errno.EINTR.equals(getLastError()));
        } else {
            throw new NullPointerException("Source buffer cannot be null");
        }
        if (var2 > 0) {
            arg1.position(arg1.position() + var2);
        }
        return var2;
    }

  public static void setBlocking(int arg0, boolean arg1) {
        int var2 = libc().fcntl(arg0, Native_LibC.F_GETFL, 0);
        var2 = !arg1 ? var2 | Native_LibC.O_NONBLOCK : var2 & (Native_LibC.O_NONBLOCK ^ -1);
        libc().fcntl(arg0, Native_LibC.F_SETFL, var2);
    }

  public static int shutdown(int arg0, int arg1) {
        return libc().shutdown(arg0, arg1);
    }

  public static String getLastErrorString() {
        return libc().strerror(LastError.getLastError(getRuntime()));
    }

  public static Errno getLastError() {
        return Errno.valueOf(((long) LastError.getLastError(getRuntime())));
    }

}