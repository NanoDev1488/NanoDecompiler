// исходный (обфусцированный) внутренний класс: jnr.unixsocket.Common
package jnr.unixsocket;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.HashMap;
import java.util.Map;
import jnr.constants.platform.ProtocolFamily;
import jnr.constants.platform.SocketLevel;
import jnr.constants.platform.SocketOption;
import jnr.ffi.Platform;
import jnr.ffi.Platform_OS;
import jnr.ffi.byref.IntByReference;
import jnr.unixsocket.Credentials;
import jnr.unixsocket.Native;
import jnr.unixsocket.Native_LibC;
import jnr.unixsocket.SockAddrUnix;
import jnr.unixsocket.UnixSocketAddress;
import jnr.unixsocket.UnixSocketOptions;

final class Common {

    // ---- поля ----
  private static Platform_OS currentOS;
  private static final Map wMap;
  private static final Map rMap;

    static {
        currentOS = Platform.getNativePlatform().getOS();
        wMap = new HashMap();
        rMap = new HashMap();
        wMap.put(UnixSocketOptions.SO_RCVBUF, SocketOption.SO_RCVBUF);
        wMap.put(UnixSocketOptions.SO_SNDBUF, SocketOption.SO_SNDBUF);
        wMap.put(UnixSocketOptions.SO_RCVTIMEO, SocketOption.SO_RCVTIMEO);
        wMap.put(UnixSocketOptions.SO_SNDTIMEO, SocketOption.SO_SNDTIMEO);
        wMap.put(UnixSocketOptions.SO_KEEPALIVE, SocketOption.SO_KEEPALIVE);
        wMap.put(UnixSocketOptions.SO_PASSCRED, SocketOption.SO_PASSCRED);
        rMap.putAll(wMap);
        rMap.put(UnixSocketOptions.SO_PEERCRED, SocketOption.SO_PEERCRED);
    }

  private Common() { // было: <init>
        super();
    }

  static UnixSocketAddress bind(int arg0, UnixSocketAddress arg1) {
        SockAddrUnix var2;
        if (null != arg1) {
            var2 = arg1.getStruct();
        } else {
            var2 = SockAddrUnix.create();
            var2.setFamily(ProtocolFamily.PF_UNIX);
            if (currentOS != Platform_OS.LINUX) {
                File var3 = Files.createTempFile("jnr-unixsocket-tmp", ".sock", new FileAttribute[0]).toFile();
                var3.deleteOnExit();
                var3.delete();
                var2.setPath(var3.getPath());
            } else {
                var2.setPath("");
            }
        }
        if (Native.bind(arg0, var2, var2.length()) >= 0) {
            return getsockname(arg0);
        } else {
            throw new IOException(Native.getLastErrorString());
        }
    }

  static UnixSocketAddress getsockname(int arg0) {
        UnixSocketAddress var1 = new UnixSocketAddress();
        SockAddrUnix var2 = var1.getStruct();
        IntByReference var3 = new IntByReference(var2.getMaximumLength());
        if (Native.libc().getsockname(arg0, var2, var3) >= 0) {
            var2.updatePath((((Integer) var3.getValue())).intValue());
            return var1;
        } else {
            throw new Error(Native.getLastErrorString());
        }
    }

  static UnixSocketAddress getpeername(int arg0) {
        UnixSocketAddress var1 = new UnixSocketAddress();
        SockAddrUnix var2 = var1.getStruct();
        IntByReference var3 = new IntByReference(var2.getMaximumLength());
        if (Native.libc().getpeername(arg0, var2, var3) >= 0) {
            var2.updatePath((((Integer) var3.getValue())).intValue());
            return var1;
        } else {
            throw new Error(Native.getLastErrorString());
        }
    }

  static Object getSocketOption(int arg0, java.net.SocketOption arg1) {
        SocketOption var2 = ((SocketOption) rMap.get(arg1));
        if (null != var2) {
            Class var3 = arg1.type();
            if (var3 != Credentials.class) {
                if (var3 != Integer.class) {
                    return Boolean.valueOf(Native.getboolsockopt(arg0, SocketLevel.SOL_SOCKET, var2.intValue()));
                } else {
                    return Integer.valueOf(Native.getsockopt(arg0, SocketLevel.SOL_SOCKET, var2.intValue()));
                }
            } else {
                return Credentials.getCredentials(arg0);
            }
        } else {
            throw new AssertionError("Option not found");
        }
    }

  static void setSocketOption(int arg0, java.net.SocketOption arg1, Object arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aconst_null
        //      1: aload_2
        //      2: if_acmpne  15 (offset +13)
        //      5: new  #15 // java.lang.IllegalArgumentException
        //      8: dup
        //      9: ldc  #3 // 'Invalid option value'
        //     11: invokespecial  #65 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //     14: athrow
        //     15: getstatic  #49 // jnr.unixsocket.Common.wMap:Ljava/util/Map;
        //     18: aload_1
        //     19: invokeinterface  #94 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     24: checkcast  #26 // jnr.constants.platform.SocketOption
        //     27: astore_3
        //     28: aconst_null
        //     29: aload_3
        //     30: if_acmpne  43 (offset +13)
        //     33: new  #12 // java.lang.AssertionError
        //     36: dup
        //     37: ldc  #7 // 'Option not found or not writable'
        //     39: invokespecial  #61 // java.lang.AssertionError.<init>:(Ljava/lang/Object;)V
        //     42: athrow
        //     43: aload_1
        //     44: invokeinterface  #92 // java.net.SocketOption.type:()Ljava/lang/Class;, count 1
        //     49: astore  4
        //     51: aload  4
        //     53: ldc  #16 // java.lang.Integer
        //     55: if_acmpeq  75 (offset +20)
        //     58: aload  4
        //     60: ldc  #13 // java.lang.Boolean
        //     62: if_acmpeq  75 (offset +13)
        //     65: new  #12 // java.lang.AssertionError
        //     68: dup
        //     69: ldc  #8 // 'Unsupported option type'
        //     71: invokespecial  #61 // java.lang.AssertionError.<init>:(Ljava/lang/Object;)V
        //     74: athrow
        //     75: aload  4
        //     77: ldc  #16 // java.lang.Integer
        //     79: if_acmpne  94 (offset +15)
        //     82: aload_2
        //     83: checkcast  #16 // java.lang.Integer
        //     86: invokevirtual  #66 // java.lang.Integer.intValue:()I
        //     89: istore  5
        //     91: goto  111 (offset +20)
        //     94: aload_2
        //     95: checkcast  #13 // java.lang.Boolean
        //     98: invokevirtual  #62 // java.lang.Boolean.booleanValue:()Z
        //    101: ifeq  108 (offset +7)
        //    104: iconst_1
        //    105: goto  109 (offset +4)
        //    108: iconst_0
        //    109: istore  5
        //    111: aload_1
        //    112: getstatic  #53 // jnr.unixsocket.UnixSocketOptions.SO_RCVBUF:Ljava/net/SocketOption;
        //    115: if_acmpeq  125 (offset +10)
        //    118: aload_1
        //    119: getstatic  #55 // jnr.unixsocket.UnixSocketOptions.SO_SNDBUF:Ljava/net/SocketOption;
        //    122: if_acmpne  149 (offset +27)
        //    125: aload_2
        //    126: checkcast  #16 // java.lang.Integer
        //    129: invokevirtual  #66 // java.lang.Integer.intValue:()I
        //    132: istore  6
        //    134: iload  6
        //    136: ifge  149 (offset +13)
        //    139: new  #15 // java.lang.IllegalArgumentException
        //    142: dup
        //    143: ldc  #4 // 'Invalid send/receive buffer size'
        //    145: invokespecial  #65 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    148: athrow
        //    149: aload_1
        //    150: getstatic  #54 // jnr.unixsocket.UnixSocketOptions.SO_RCVTIMEO:Ljava/net/SocketOption;
        //    153: if_acmpeq  163 (offset +10)
        //    156: aload_1
        //    157: getstatic  #56 // jnr.unixsocket.UnixSocketOptions.SO_SNDTIMEO:Ljava/net/SocketOption;
        //    160: if_acmpne  187 (offset +27)
        //    163: aload_2
        //    164: checkcast  #16 // java.lang.Integer
        //    167: invokevirtual  #66 // java.lang.Integer.intValue:()I
        //    170: istore  6
        //    172: iload  6
        //    174: ifge  187 (offset +13)
        //    177: new  #15 // java.lang.IllegalArgumentException
        //    180: dup
        //    181: ldc  #5 // 'Invalid send/receive timeout'
        //    183: invokespecial  #65 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    186: athrow
        //    187: iconst_0
        //    188: iload_0
        //    189: getstatic  #38 // jnr.constants.platform.SocketLevel.SOL_SOCKET:Ljnr/constants/platform/SocketLevel;
        //    192: aload_3
        //    193: iload  5
        //    195: invokestatic  #83 // jnr.unixsocket.Native.setsockopt:(ILjnr/constants/platform/SocketLevel;Ljnr/constants/platform/SocketOption;I)I
        //    198: if_icmpeq  212 (offset +14)
        //    201: new  #11 // java.io.IOException
        //    204: dup
        //    205: invokestatic  #79 // jnr.unixsocket.Native.getLastErrorString:()Ljava/lang/String;
        //    208: invokespecial  #60 // java.io.IOException.<init>:(Ljava/lang/String;)V
        //    211: athrow
        //    212: return
    }

}