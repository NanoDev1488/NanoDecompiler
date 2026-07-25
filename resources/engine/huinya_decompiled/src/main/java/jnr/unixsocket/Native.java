// исходный (обфусцированный) внутренний класс: jnr.unixsocket.Native
package jnr.unixsocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import jnr.constants.platform.Errno;
import jnr.constants.platform.ProtocolFamily;
import jnr.constants.platform.Sock;
import jnr.constants.platform.SocketLevel;
import jnr.constants.platform.SocketOption;
import jnr.ffi.LastError;
import jnr.ffi.LibraryLoader;
import jnr.ffi.Platform;
import jnr.ffi.Platform_OS;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.Struct_SignedLong;
import jnr.ffi.byref.IntByReference;
import jnr.posix.DefaultNativeTimeval;
import jnr.unixsocket.Native_LibC;
import jnr.unixsocket.SockAddrUnix;

class Native {

    // ---- поля ----
  static final String[] libnames;
  static final Native_LibC INSTANCE;

    static {
        String[] __stk3;
        if (Platform.getNativePlatform().getOS() != Platform_OS.SOLARIS) {
            __stk3 = new String[]{Platform.getNativePlatform().getStandardCLibraryName()};
        } else {
            __stk3 = new String[]{"socket", "nsl", Platform.getNativePlatform().getStandardCLibraryName()};
        }
        libnames = __stk3;
        LibraryLoader var0 = LibraryLoader.create(Native_LibC.class);
        String[] var1 = libnames;
        int var2 = var1.length;
        int var3 = 0;
        while (var3 < var2) {
            Object var4 = var1[var3];
            var0.library(((String) var4));
            ++var3;
            continue;
        }
        INSTANCE = ((Native_LibC) var0.load());
    }

   Native() { // было: <init>
        super();
    }

  static final Native_LibC libsocket() {
        return INSTANCE;
    }

  static final Native_LibC libc() {
        return INSTANCE;
    }

  static int socket(ProtocolFamily arg0, Sock arg1, int arg2) {
        int var3 = libsocket().socket(arg0.intValue(), arg1.intValue(), arg2);
        if (var3 >= 0) {
            return var3;
        } else {
            throw new IOException(getLastErrorString());
        }
    }

  static int socketpair(ProtocolFamily arg0, Sock arg1, int arg2, int[] arg3) {
        if (libsocket().socketpair(arg0.intValue(), arg1.intValue(), arg2, arg3) >= 0) {
            return 0;
        } else {
            throw new IOException(new StringBuilder().append("socketpair(2) failed ").append(getLastErrorString()).toString());
        }
    }

  static int listen(int arg0, int arg1) {
        return libsocket().listen(arg0, arg1);
    }

  static int bind(int arg0, SockAddrUnix arg1, int arg2) {
        return libsocket().bind(arg0, arg1, arg2);
    }

  static int accept(int arg0, SockAddrUnix arg1, IntByReference arg2) {
        return libsocket().accept(arg0, arg1, arg2);
    }

  static int connect(int arg0, SockAddrUnix arg1, int arg2) {
        return libsocket().connect(arg0, arg1, arg2);
    }

  static String getLastErrorString() {
        return strerror(LastError.getLastError(Runtime.getSystemRuntime()));
    }

  static Errno getLastError() {
        return Errno.valueOf(((long) LastError.getLastError(Runtime.getSystemRuntime())));
    }

  static String strerror(int arg0) {
        return libc().strerror(arg0);
    }

  public static void setBlocking(int arg0, boolean arg1) {
        int var2 = libc().fcntl(arg0, Native_LibC.F_GETFL, 0);
        var2 = !arg1 ? var2 | Native_LibC.O_NONBLOCK : var2 & (Native_LibC.O_NONBLOCK ^ -1);
        libc().fcntl(arg0, Native_LibC.F_SETFL, var2);
    }

  public static int setsockopt(int arg0, SocketLevel arg1, SocketOption arg2, boolean arg3) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: iload_0
        //      1: aload_1
        //      2: aload_2
        //      3: iload_3
        //      4: ifeq  11 (offset +7)
        //      7: iconst_1
        //      8: goto  12 (offset +4)
        //     11: iconst_0
        //     12: invokestatic  #90 // jnr.unixsocket.Native.setsockopt:(ILjnr/constants/platform/SocketLevel;Ljnr/constants/platform/SocketOption;I)I
        //     15: ireturn
    }

  public static int setsockopt(int arg0, SocketLevel arg1, SocketOption arg2, int arg3) {
        if (arg2 == SocketOption.SO_RCVTIMEO) {
            DefaultNativeTimeval var4 = new DefaultNativeTimeval(Runtime.getSystemRuntime());
            var4.setTime(new long[]{((long) (arg3 / 1000)), ((long) arg3) % 1000L * 1000L});
            return libsocket().setsockopt(arg0, arg1.intValue(), arg2.intValue(), var4, DefaultNativeTimeval.size(var4));
        } else {
            if (arg2 != SocketOption.SO_SNDTIMEO) {
                ByteBuffer var4 = ByteBuffer.allocate(4);
                var4.order(ByteOrder.nativeOrder());
                var4.putInt(arg3).flip();
                return libsocket().setsockopt(arg0, arg1.intValue(), arg2.intValue(), var4, var4.remaining());
            } else {
                DefaultNativeTimeval var4 = new DefaultNativeTimeval(Runtime.getSystemRuntime());
                long[] __obj1 = new long[]{((long) (arg3 / 1000)), ((long) arg3) % 1000L * 1000L};
                __obj1[0] = ((long) (arg3 / 1000));
                __obj1[1] = ((long) arg3) % 1000L * 1000L;
                var4.setTime(new long[]{((long) (arg3 / 1000)), ((long) arg3) % 1000L * 1000L});
                return libsocket().setsockopt(arg0, arg1.intValue(), arg2.intValue(), var4, DefaultNativeTimeval.size(var4));
            }
        }
    }

  public static int getsockopt(int arg0, SocketLevel arg1, int arg2) {
        if (arg2 == SocketOption.SO_RCVTIMEO.intValue()) {
            DefaultNativeTimeval var4 = new DefaultNativeTimeval(Runtime.getSystemRuntime());
            IntByReference var3 = new IntByReference(DefaultNativeTimeval.size(var4));
            libsocket().getsockopt(arg0, arg1.intValue(), arg2, var4, var3);
            return var4.tv_sec.intValue() * 1000 + var4.tv_usec.intValue() / 1000;
        } else {
            if (arg2 != SocketOption.SO_SNDTIMEO.intValue()) {
                ByteBuffer var4 = ByteBuffer.allocate(4);
                var4.order(ByteOrder.nativeOrder());
                IntByReference var3 = new IntByReference(4);
                libsocket().getsockopt(arg0, arg1.intValue(), arg2, var4, var3);
                return var4.getInt();
            } else {
                DefaultNativeTimeval var4 = new DefaultNativeTimeval(Runtime.getSystemRuntime());
                IntByReference var3 = new IntByReference(DefaultNativeTimeval.size(var4));
                libsocket().getsockopt(arg0, arg1.intValue(), arg2, var4, var3);
                return var4.tv_sec.intValue() * 1000 + var4.tv_usec.intValue() / 1000;
            }
        }
    }

  public static int getsockopt(int arg0, SocketLevel arg1, SocketOption arg2, Struct arg3) {
        Pointer var4 = Struct.getMemory(arg3);
        IntByReference var5 = new IntByReference(Struct.size(arg3));
        ByteBuffer var6 = ByteBuffer.wrap(((byte[]) var4.array()));
        return libsocket().getsockopt(arg0, arg1.intValue(), arg2.intValue(), var6, var5);
    }

  public static boolean getboolsockopt(int arg0, SocketLevel arg1, int arg2) {
        return getsockopt(arg0, arg1, arg2) != 0;
    }

  public static int sendto(int arg0, ByteBuffer arg1, SockAddrUnix arg2, int arg3) {
        int var4;
        if (arg1 != null) {
            do {
                var4 = libsocket().sendto(arg0, arg1, ((long) arg1.remaining()), 0, arg2, arg3);
                if (var4 >= 0) {
                    break;
                }
            } while (Errno.EINTR.equals(getLastError()));
        } else {
            throw new IllegalArgumentException("Source buffer cannot be null");
        }
        if (var4 > 0) {
            arg1.position(arg1.position() + var4);
        }
        return var4;
    }

  public static int recvfrom(int arg0, ByteBuffer arg1, SockAddrUnix arg2) {
        IntByReference __stk1;
        if (arg1 == null) {
            throw new IllegalArgumentException("Destination buffer cannot be null");
        }
        if (!arg1.isReadOnly()) {
            __stk1 = null != arg2 ? new IntByReference(arg2.getMaximumLength()) : null;
        } else {
            throw new IllegalArgumentException("Read-only buffer");
        }
        Object var3 = __stk1;
        int var4;
        do {
            var4 = libsocket().recvfrom(arg0, arg1, ((long) arg1.remaining()), 0, arg2, ((IntByReference) var3));
            if (var4 >= 0) {
                break;
            }
        } while (Errno.EINTR.equals(getLastError()));
        if (var4 > 0) {
            arg1.position(arg1.position() + var4);
        }
        return var4;
    }

}