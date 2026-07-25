// исходный (обфусцированный) внутренний класс: com.kenai.jffi.HeapInvocationBuffer.Encoder.SingletonHolder
package com.kenai.jffi;

import com.kenai.jffi.HeapInvocationBuffer_ArrayIO;
import com.kenai.jffi.HeapInvocationBuffer_DefaultEncoder;
import com.kenai.jffi.HeapInvocationBuffer_Encoder;

class HeapInvocationBuffer_Encoder_SingletonHolder {

    // ---- поля ----
  static final HeapInvocationBuffer_Encoder INSTANCE;

    static {
        INSTANCE = new HeapInvocationBuffer_DefaultEncoder(HeapInvocationBuffer_ArrayIO.getInstance());
    }

  private HeapInvocationBuffer_Encoder_SingletonHolder() { // было: <init>
        super();
    }

}