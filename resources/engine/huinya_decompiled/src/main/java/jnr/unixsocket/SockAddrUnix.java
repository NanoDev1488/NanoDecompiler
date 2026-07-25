// исходный (обфусцированный) внутренний класс: jnr.unixsocket.SockAddrUnix
package jnr.unixsocket;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import jnr.constants.platform.ProtocolFamily;
import jnr.ffi.Platform;
import jnr.ffi.Platform_OS;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.Struct_NumberField;
import jnr.ffi.Struct_UTF8String;
import jnr.unixsocket.SockAddrUnix_BSDSockAddrUnix;
import jnr.unixsocket.SockAddrUnix_DefaultSockAddrUnix;

abstract class SockAddrUnix extends Struct {

    // ---- поля ----
  private static transient Platform_OS currentOS;
  public static final int ADDR_LENGTH = 108;
  public static final int HEADER_LENGTH = 2;
  private String cachedPath;

    static {
        currentOS = Platform.getNativePlatform().getOS();
    }

  protected abstract Struct_UTF8String getPathField();

  protected abstract Struct_NumberField getFamilyField();

   SockAddrUnix() { // было: <init>
        super(Runtime.getSystemRuntime());
    }

  final void setFamily(ProtocolFamily arg0) {
        getFamilyField().set(Integer.valueOf(arg0.intValue()));
    }

  final ProtocolFamily getFamily() {
        return ProtocolFamily.valueOf(((long) getFamilyField().intValue()));
    }

   void setPath(String arg0) {
        cachedPath = arg0;
        getPathField().set(cachedPath);
    }

   void updatePath(int arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #22 // jnr.unixsocket.SockAddrUnix.currentOS:Ljnr/ffi/Platform$OS;
        //      3: getstatic  #20 // jnr.ffi.Platform$OS.LINUX:Ljnr/ffi/Platform$OS;
        //      6: if_acmpne  33 (offset +27)
        //      9: aload_0
        //     10: iload_1
        //     11: iconst_2
        //     12: if_icmpne  20 (offset +8)
        //     15: ldc  #3 // ''
        //     17: goto  27 (offset +10)
        //     20: aload_0
        //     21: iload_1
        //     22: iconst_2
        //     23: isub
        //     24: invokevirtual  #45 // jnr.unixsocket.SockAddrUnix.getPath:(I)Ljava/lang/String;
        //     27: putfield  #21 // jnr.unixsocket.SockAddrUnix.cachedPath:Ljava/lang/String;
        //     30: goto  96 (offset +66)
        //     33: aload_0
        //     34: aload_0
        //     35: invokevirtual  #46 // jnr.unixsocket.SockAddrUnix.getPathField:()Ljnr/ffi/Struct$UTF8String;
        //     38: invokevirtual  #39 // jnr.ffi.Struct$UTF8String.get:()Ljava/lang/String;
        //     41: putfield  #21 // jnr.unixsocket.SockAddrUnix.cachedPath:Ljava/lang/String;
        //     44: iload_1
        //     45: iconst_2
        //     46: isub
        //     47: istore_2
        //     48: iload_2
        //     49: ifgt  61 (offset +12)
        //     52: aload_0
        //     53: ldc  #3 // ''
        //     55: putfield  #21 // jnr.unixsocket.SockAddrUnix.cachedPath:Ljava/lang/String;
        //     58: goto  96 (offset +38)
        //     61: iload_2
        //     62: aload_0
        //     63: invokevirtual  #46 // jnr.unixsocket.SockAddrUnix.getPathField:()Ljnr/ffi/Struct$UTF8String;
        //     66: invokevirtual  #41 // jnr.ffi.Struct$UTF8String.length:()I
        //     69: if_icmpge  96 (offset +27)
        //     72: iload_2
        //     73: aload_0
        //     74: getfield  #21 // jnr.unixsocket.SockAddrUnix.cachedPath:Ljava/lang/String;
        //     77: invokevirtual  #25 // java.lang.String.length:()I
        //     80: if_icmpge  96 (offset +16)
        //     83: aload_0
        //     84: aload_0
        //     85: getfield  #21 // jnr.unixsocket.SockAddrUnix.cachedPath:Ljava/lang/String;
        //     88: iconst_0
        //     89: iload_2
        //     90: invokevirtual  #26 // java.lang.String.substring:(II)Ljava/lang/String;
        //     93: putfield  #21 // jnr.unixsocket.SockAddrUnix.cachedPath:Ljava/lang/String;
        //     96: return
    }

  final String getPath() {
        if (null == cachedPath) {
            cachedPath = getPathField().get();
        }
        return cachedPath;
    }

  final String getPath(int arg0) {
        Struct_UTF8String var2 = getPathField();
        byte[] var3 = new byte[var2.length()];
        var2.getMemory().get(var2.offset(), var3, 0, arg0);
        if (0 != var3[0]) {
            --arg0;
        }
        return new String(Arrays.copyOf(var3, arg0), StandardCharsets.UTF_8);
    }

   int getMaximumLength() {
        return 2 + getPathField().length();
    }

   int length() {
        if (currentOS != Platform_OS.LINUX) {
            return 2 + strlen(getPathField());
        } else {
            if (null == cachedPath) {
                return 2 + strlen(getPathField());
            } else {
                return 2 + cachedPath.length();
            }
        }
    }

   int getHeaderLength() {
        return 2;
    }

  static SockAddrUnix create() {
        return !Platform.getNativePlatform().isBSD() ? new SockAddrUnix_DefaultSockAddrUnix() : new SockAddrUnix_BSDSockAddrUnix();
    }

  private static final int strlen(Struct_UTF8String arg0) {
        int var1 = arg0.getMemory().indexOf(arg0.offset(), 0);
        return var1 < 0 ? arg0.length() : var1;
    }

}