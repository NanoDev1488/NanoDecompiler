// исходный (обфусцированный) внутренний класс: com.kenai.jffi.CallContextCache.CallContextRef
package com.kenai.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.CallContextCache_Signature;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

final class CallContextCache_CallContextRef extends SoftReference {

    // ---- поля ----
  final CallContextCache_Signature signature;

  public CallContextCache_CallContextRef(CallContextCache_Signature arg0, CallContext arg1, ReferenceQueue arg2) { // было: <init>
        super(arg1, arg2);
        signature = arg0;
    }

}