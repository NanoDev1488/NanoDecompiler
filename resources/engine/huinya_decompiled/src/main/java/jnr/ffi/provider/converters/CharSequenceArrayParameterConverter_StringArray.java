// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.CharSequenceArrayParameterConverter.StringArray
package jnr.ffi.provider.converters;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.provider.InAccessibleMemoryIO;

final class CharSequenceArrayParameterConverter_StringArray extends InAccessibleMemoryIO {

    // ---- поля ----
  private final Pointer memory;
  private List stringMemory;
  private final Charset charset;

  private CharSequenceArrayParameterConverter_StringArray(Runtime arg0, Pointer arg1, int arg2) { // было: <init>
        super(arg0, arg1.address(), arg1.isDirect());
        charset = Charset.defaultCharset();
        memory = arg1;
        stringMemory = new ArrayList(arg2);
    }

   String get(int arg0) {
        Pointer var2 = memory.getPointer(((long) (arg0 * getRuntime().addressSize())));
        return var2 == null ? null : var2.getString(0L);
    }

   void put(int arg0, CharSequence arg1) {
        if (arg1 != null) {
            ByteBuffer var3 = charset.encode(CharBuffer.wrap(arg1));
            Pointer var4 = Memory.allocateDirect(getRuntime(), var3.remaining() + 4, true);
            var4.put(0L, var3.array(), 0, var3.remaining());
            stringMemory.add(arg0, var4);
            memory.putPointer(((long) (arg0 * getRuntime().addressSize())), var4);
        } else {
            memory.putAddress(((long) (arg0 * getRuntime().addressSize())), 0L);
            stringMemory.add(arg0, null);
        }
    }

  public long size() {
        return memory.size();
    }

  static CharSequenceArrayParameterConverter_StringArray allocate(Runtime arg0, int arg1) {
        Pointer var2 = Memory.allocateDirect(arg0, arg1 * arg0.addressSize());
        return new CharSequenceArrayParameterConverter_StringArray(arg0, var2, arg1);
    }

}