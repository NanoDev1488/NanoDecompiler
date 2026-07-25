// исходный (обфусцированный) внутренний класс: jnr.posix.BaseMsgHdr
package jnr.posix;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.provider.MemoryManager;
import jnr.posix.BaseIovec;
import jnr.posix.BaseIovec_Layout;
import jnr.posix.CmsgHdr;
import jnr.posix.MsgHdr;
import jnr.posix.NativePOSIX;
import jnr.posix.SocketMacros;

public abstract class BaseMsgHdr implements MsgHdr {

    // ---- поля ----
  protected final NativePOSIX posix;
  protected final Pointer memory;

  protected BaseMsgHdr(NativePOSIX arg0, StructLayout arg1) { // было: <init>
        super();
        posix = arg0;
        memory = arg0.getRuntime().getMemoryManager().allocateTemporary(arg1.size(), true);
    }

  public void setName(String arg0) {
        if (arg0 != null) {
            byte[] var2 = arg0.getBytes(Charset.forName("US-ASCII"));
            Pointer var3 = Runtime.getSystemRuntime().getMemoryManager().allocateTemporary(var2.length, true);
            var3.put(0L, var2, 0, var2.length);
            setNamePointer(var3);
            setNameLen(var2.length);
            return;
        } else {
            setNamePointer(null);
            setNameLen(0);
            return;
        }
    }

  public String getName() {
        Pointer var1 = getNamePointer();
        if (var1 != null) {
            return var1.getString(0L, getNameLen(), Charset.forName("US-ASCII"));
        } else {
            return null;
        }
    }

  public CmsgHdr allocateControl(int arg0) {
        CmsgHdr[] var2 = allocateControls(new int[]{arg0});
        return ((CmsgHdr) var2[0]);
    }

  public CmsgHdr[] allocateControls(int[] arg0) {
        CmsgHdr[] var2 = new CmsgHdr[arg0.length];
        int var3 = 0;
        int var4 = 0;
        while (var4 < arg0.length) {
            var3 = var3 + posix.socketMacros().CMSG_SPACE(arg0[var4]);
            ++var4;
            continue;
        }
        var4 = posix.getRuntime().getMemoryManager().allocateDirect(var3);
        int var5 = 0;
        int var6 = 0;
        while (var6 < arg0.length) {
            int var7 = posix.socketMacros().CMSG_SPACE(arg0[var6]);
            int var8 = posix.socketMacros().CMSG_LEN(arg0[var6]);
            CmsgHdr var9 = allocateCmsgHdrInternal(posix, var4.slice(((long) var5), ((long) var7)), var8);
            var2[var6] = var9;
            var5 = var5 + var7;
            ++var6;
            continue;
        }
        setControlPointer(var4);
        setControlLen(var3);
        return var2;
    }

  public CmsgHdr[] getControls() {
        int var1 = getControlLen();
        ArrayList var2;
        int var3;
        if (var1 != 0) {
            var2 = new ArrayList();
            var3 = 0;
            Pointer var4 = getControlPointer();
        } else {
            return new CmsgHdr[0];
        }
        while (var3 < var1) {
            CmsgHdr var5 = allocateCmsgHdrInternal(posix, var4.slice(((long) var3)), -1);
            int var3 = var3 + posix.socketMacros().CMSG_SPACE(var5.getLen());
            var2.add(var5);
            continue;
        }
        return ((CmsgHdr[]) var2.toArray(new CmsgHdr[var2.size()]));
    }

  public void setIov(ByteBuffer[] arg0) {
        Pointer var2 = Runtime.getSystemRuntime().getMemoryManager().allocateDirect(BaseIovec.layout.size() * arg0.length);
        int var3 = 0;
        while (var3 < arg0.length) {
            Pointer var4 = var2.slice(((long) (BaseIovec.layout.size() * var3)));
            BaseIovec var5 = new BaseIovec(posix, var4);
            var5.set(((ByteBuffer) arg0[var3]));
            ++var3;
            continue;
        }
        setIovPointer(var2);
        setIovLen(arg0.length);
    }

  public ByteBuffer[] getIov() {
        int var1 = getIovLen();
        ByteBuffer[] var2 = new ByteBuffer[var1];
        Pointer var3 = getIovPointer();
        int var4 = 0;
        while (var4 < var1) {
            Pointer var5 = var3.slice(((long) (BaseIovec.layout.size() * var4)));
            BaseIovec var6 = new BaseIovec(posix, var5);
            var2[var4] = var6.get();
            ++var4;
            continue;
        }
        return var2;
    }

  abstract void setNamePointer(Pointer arg0);

  abstract Pointer getNamePointer();

  abstract void setNameLen(int arg0);

  abstract int getNameLen();

  abstract void setIovPointer(Pointer arg0);

  abstract Pointer getIovPointer();

  abstract int getIovLen();

  abstract void setIovLen(int arg0);

  abstract CmsgHdr allocateCmsgHdrInternal(NativePOSIX arg0, Pointer arg1, int arg2);

  abstract void setControlPointer(Pointer arg0);

  abstract Pointer getControlPointer();

  abstract void setControlLen(int arg0);

}