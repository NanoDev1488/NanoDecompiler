// исходный (обфусцированный) внутренний класс: com.kenai.jffi.DirectClosureBuffer.NativeWordIO32
package com.kenai.jffi;

import com.kenai.jffi.DirectClosureBuffer_NativeWordIO;
import com.kenai.jffi.MemoryIO;

final class DirectClosureBuffer_NativeWordIO32 extends DirectClosureBuffer_NativeWordIO {

    // ---- поля ----
  private static final MemoryIO IO;
  static final DirectClosureBuffer_NativeWordIO INSTANCE;

    static {
        IO = MemoryIO.getInstance();
        INSTANCE = new DirectClosureBuffer_NativeWordIO32();
    }

  private DirectClosureBuffer_NativeWordIO32() { // было: <init>
        super(null);
    }

   void put(long arg0, int arg1) {
        IO.putInt(arg0, arg1);
    }

   int get(long arg0) {
        return IO.getInt(arg0);
    }

}