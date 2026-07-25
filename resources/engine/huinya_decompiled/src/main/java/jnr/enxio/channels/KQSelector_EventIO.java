// исходный (обфусцированный) внутренний класс: jnr.enxio.channels.KQSelector.EventIO
package jnr.enxio.channels;

import jnr.enxio.channels.KQSelector_EventLayout;
import jnr.enxio.channels.KQSelector_FreeBSD12EventLayout;
import jnr.enxio.channels.KQSelector_LegacyEventLayout;
import jnr.ffi.Platform;
import jnr.ffi.Platform_OS;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.StructLayout_int16_t;
import jnr.ffi.StructLayout_u_int16_t;
import jnr.ffi.StructLayout_uintptr_t;
import jnr.ffi.Type;
import jnr.ffi.TypeAlias;
import jnr.ffi.provider.jffi.NativeRuntime;

final class KQSelector_EventIO {

    // ---- поля ----
  private static final KQSelector_EventIO INSTANCE;
  private final KQSelector_EventLayout layout;
  private final Type uintptr_t;

    static {
        INSTANCE = new KQSelector_EventIO();
    }

  private KQSelector_EventIO() { // было: <init>
        super();
        int var1 = 0;
        if (Platform.getNativePlatform().getOS() == Platform_OS.FREEBSD) {
            String var2 = System.getProperty("os.version");
            if (var2 != null) {
                int var3 = -1;
                char[] var4 = new char[]{32, 95, 45, 43, 46};
                int var5 = var4.length;
                int var6 = 0;
                while (var6 < var5) {
                    char var7 = var4[var6];
                    int var8 = var2.indexOf(var7);
                    if (var8 >= 0) {
                        if (var3 == -1) {
                            var3 = var8;
                        } else {
                            if (var3 > var8) {
                                var3 = var8;
                            }
                        }
                    }
                    ++var6;
                    continue;
                }
                if (var3 >= 0) {
                    var2 = var2.substring(0, var3);
                }
                try {
                    var4 = Integer.parseInt(var2);
                    if (var4 > 11) {
                        var1 = 1;
                    }
                } catch (NumberFormatException e1) {
                    var4 = e1;
                }
            }
        }
        layout = var1 == 0 ? new KQSelector_LegacyEventLayout(NativeRuntime.getSystemRuntime(), null) : new KQSelector_FreeBSD12EventLayout(NativeRuntime.getSystemRuntime(), null);
        uintptr_t = layout.getRuntime().findType(TypeAlias.uintptr_t);
    }

  public static KQSelector_EventIO getInstance() {
        return INSTANCE;
    }

  public final void put(Pointer arg0, int arg1, int arg2, int arg3, int arg4) {
        arg0.putInt(uintptr_t, ((long) (arg1 * layout.size())) + layout.ident.offset(), ((long) arg2));
        arg0.putShort(((long) (arg1 * layout.size())) + layout.filter.offset(), ((short) arg3));
        arg0.putShort(((long) (arg1 * layout.size())) + layout.flags.offset(), ((short) arg4));
    }

  public final int size() {
        return layout.size();
    }

   int getFD(Pointer arg0, int arg1) {
        return ((int) arg0.getInt(uintptr_t, ((long) (arg1 * layout.size())) + layout.ident.offset()));
    }

  public final void putFilter(Pointer arg0, int arg1, int arg2) {
        arg0.putShort(((long) (arg1 * layout.size())) + layout.filter.offset(), ((short) arg2));
    }

  public final int getFilter(Pointer arg0, int arg1) {
        return arg0.getShort(((long) (arg1 * layout.size())) + layout.filter.offset());
    }

  public final void putFlags(Pointer arg0, int arg1, int arg2) {
        arg0.putShort(((long) (arg1 * layout.size())) + layout.flags.offset(), ((short) arg2));
    }

}