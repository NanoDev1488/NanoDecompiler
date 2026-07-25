// исходный (обфусцированный) внутренний класс: com.kenai.jffi.ClosurePool.Magazine.Slot
package com.kenai.jffi;

import com.kenai.jffi.ClosurePool_Magazine;
import com.kenai.jffi.ClosurePool_Proxy;
import com.kenai.jffi.MemoryIO;

final class ClosurePool_Magazine_Slot {

    // ---- поля ----
  final long handle;
  final long codeAddress;
  final ClosurePool_Proxy proxy;
  volatile boolean autorelease;

  public ClosurePool_Magazine_Slot(long arg0, ClosurePool_Proxy arg1) { // было: <init>
        super();
        handle = arg0;
        proxy = arg1;
        autorelease = true;
        codeAddress = ClosurePool_Magazine.access$100().getAddress(arg0);
    }

}