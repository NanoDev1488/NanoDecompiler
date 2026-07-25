// исходный (обфусцированный) внутренний класс: jnr.posix.DefaultNativeGroup
package jnr.posix;

import java.util.ArrayList;
import java.util.List;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.StructLayout_Pointer;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_UTF8StringRef;
import jnr.posix.DefaultNativeGroup_Layout;
import jnr.posix.Group;
import jnr.posix.NativeGroup;

public final class DefaultNativeGroup extends NativeGroup implements Group {

    // ---- поля ----
  static final DefaultNativeGroup_Layout layout;
  private final Pointer memory;

    static {
        layout = new DefaultNativeGroup_Layout(Runtime.getSystemRuntime());
    }

   DefaultNativeGroup(Pointer arg0) { // было: <init>
        super(arg0.getRuntime(), layout);
        memory = arg0;
    }

  public String getName() {
        return layout.gr_name.get(memory);
    }

  public String getPassword() {
        return layout.gr_passwd.get(memory);
    }

  public long getGID() {
        return ((long) layout.gr_gid.get(memory));
    }

  public String[] getMembers() {
        ArrayList var1 = new ArrayList();
        Pointer var2 = layout.gr_mem.get(memory);
        int var4 = runtime.addressSize();
        int var5 = 0;
        while (true) {
            Pointer var3 = var2.getPointer(((long) var5));
            if (var3 == null) {
                break;
            }
            var1.add(var3.getString(0L));
            var5 = var5 + var4;
            continue;
        }
        return ((String[]) var1.toArray(new String[var1.size()]));
    }

}