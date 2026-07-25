// исходный (обфусцированный) внутренний класс: com.kenai.jnr.x86asm.CpuInfo
package com.kenai.jnr.x86asm;

import com.kenai.jnr.x86asm.CpuInfo_Vendor;

@Deprecated
public class CpuInfo {

    // ---- поля ----
  final CpuInfo_Vendor vendor;
  final int family;
  public static final CpuInfo GENERIC;

    static {
        GENERIC = new CpuInfo(CpuInfo_Vendor.GENERIC, 0);
    }

  public CpuInfo(CpuInfo_Vendor arg0, int arg1) { // было: <init>
        super();
        vendor = arg0;
        family = arg1;
    }

}