// исходный (обфусцированный) внутренний класс: jnr.a64asm.CpuInfo
package jnr.a64asm;

import jnr.a64asm.CpuInfo_Vendor;

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