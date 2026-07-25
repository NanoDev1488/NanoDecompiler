// исходный (обфусцированный) внутренний класс: com.kenai.jffi.DirectClosureBuffer.NativeWordIO
package com.kenai.jffi;

import com.kenai.jffi.DirectClosureBuffer_Anon1;
import com.kenai.jffi.DirectClosureBuffer_NativeWordIO32;
import com.kenai.jffi.DirectClosureBuffer_NativeWordIO64;
import com.kenai.jffi.Platform;

abstract class DirectClosureBuffer_NativeWordIO {

  private DirectClosureBuffer_NativeWordIO() { // было: <init>
        super();
    }

  public static final DirectClosureBuffer_NativeWordIO getInstance() {
        return Platform.getPlatform().addressSize() != 32 ? DirectClosureBuffer_NativeWordIO64.INSTANCE : DirectClosureBuffer_NativeWordIO32.INSTANCE;
    }

  abstract void put(long arg0, int arg1);

  abstract int get(long arg0);

   DirectClosureBuffer_NativeWordIO(DirectClosureBuffer_Anon1 arg0) { // было: <init>
        this();
    }

}