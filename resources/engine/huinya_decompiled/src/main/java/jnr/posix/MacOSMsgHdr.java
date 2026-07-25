// исходный (обфусцированный) внутренний класс: jnr.posix.MacOSMsgHdr
package jnr.posix;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.StructLayout_Pointer;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_socklen_t;
import jnr.posix.BaseIovec;
import jnr.posix.BaseIovec_Layout;
import jnr.posix.BaseMsgHdr;
import jnr.posix.CmsgHdr;
import jnr.posix.MacOSCmsgHdr;
import jnr.posix.MacOSMsgHdr_Layout;
import jnr.posix.NativePOSIX;

class MacOSMsgHdr extends BaseMsgHdr {

    // ---- поля ----
  private static final MacOSMsgHdr_Layout layout;

    static {
        layout = new MacOSMsgHdr_Layout(Runtime.getSystemRuntime());
    }

  protected MacOSMsgHdr(NativePOSIX arg0) { // было: <init>
        super(arg0, layout);
        setName(null);
    }

   CmsgHdr allocateCmsgHdrInternal(NativePOSIX arg0, Pointer arg1, int arg2) {
        if (arg2 <= 0) {
            return new MacOSCmsgHdr(arg0, arg1);
        } else {
            return new MacOSCmsgHdr(arg0, arg1, arg2);
        }
    }

   void setControlPointer(Pointer arg0) {
        layout.msg_control.set(memory, arg0);
    }

   void setControlLen(int arg0) {
        layout.msg_controllen.set(memory, ((long) arg0));
    }

  public String toString() {
        StringBuffer var1 = new StringBuffer();
        var1.append("msghdr {\n");
        var1.append("  msg_name=").append(getName()).append(",\n");
        var1.append("  msg_namelen=").append(getNameLen()).append(",\n");
        var1.append("  msg_iov=[\n");
        Pointer var2 = layout.msg_iov.get(memory);
        int var3 = getIovLen();
        int var4 = 0;
        Pointer var5;
        while (var4 < var3) {
            var5 = var2.slice(((long) (var4 * BaseIovec.layout.size())));
            var1.append(new BaseIovec(posix, var5).toString("    "));
            if (var4 >= var3 - 1) {
                var1.append("\n");
            } else {
                var1.append(",\n");
            }
            ++var4;
            continue;
        }
        var1.append("  ],\n");
        var1.append("  msg_control=[\n");
        var4 = getControls();
        int var5 = 0;
        while (var5 < var4.length) {
            var1.append((((MacOSCmsgHdr) var4[var5])).toString("    "));
            if (var5 >= var4.length - 1) {
                var1.append("\n");
            } else {
                var1.append(",\n");
            }
            ++var5;
            continue;
        }
        var1.append("  ],\n");
        var1.append("  msg_controllen=").append(layout.msg_controllen.get(memory)).append("\n");
        var1.append("  msg_iovlen=").append(getIovLen()).append(",\n");
        var1.append("  msg_flags=").append(getFlags()).append(",\n");
        var1.append("}");
        return var1.toString();
    }

   void setNamePointer(Pointer arg0) {
        layout.msg_name.set(memory, arg0);
    }

   Pointer getNamePointer() {
        return layout.msg_name.get(memory);
    }

   void setNameLen(int arg0) {
        layout.msg_namelen.set(memory, ((long) arg0));
    }

   int getNameLen() {
        return ((int) layout.msg_namelen.get(memory));
    }

   void setIovPointer(Pointer arg0) {
        layout.msg_iov.set(memory, arg0);
    }

   Pointer getIovPointer() {
        return layout.msg_iov.get(memory);
    }

   void setIovLen(int arg0) {
        layout.msg_iovlen.set(memory, arg0);
    }

   int getIovLen() {
        return layout.msg_iovlen.get(memory);
    }

   Pointer getControlPointer() {
        return layout.msg_control.get(memory);
    }

  public int getControlLen() {
        return ((int) layout.msg_controllen.get(memory));
    }

  public void setFlags(int arg0) {
        layout.msg_flags.set(memory, arg0);
    }

  public int getFlags() {
        return layout.msg_flags.get(memory);
    }

}