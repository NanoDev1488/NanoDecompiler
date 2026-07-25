// исходный (обфусцированный) внутренний класс: com.kenai.jffi.NativeMethod
package com.kenai.jffi;

public final class NativeMethod {

    // ---- поля ----
  final long function;
  final String name;
  final String signature;

  public NativeMethod(long arg0, String arg1, String arg2) { // было: <init>
        super();
        function = arg0;
        name = arg1;
        signature = arg2;
    }

}