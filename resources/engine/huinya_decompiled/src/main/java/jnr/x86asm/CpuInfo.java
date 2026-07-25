// исходный (обфусцированный) внутренний класс: jnr.x86asm.CpuInfo
package jnr.x86asm;

import jnr.x86asm.CpuInfo_Vendor;

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