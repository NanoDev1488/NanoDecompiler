// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.s390x.ErrnoAddressInfo.StringTable
package jnr.constants.platform.linux.s390x;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.linux.s390x.ErrnoAddressInfo;

final class ErrnoAddressInfo_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   ErrnoAddressInfo_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(ErrnoAddressInfo.class);
        var0.put(ErrnoAddressInfo.EAI_ADDRFAMILY, "EAI_ADDRFAMILY");
        var0.put(ErrnoAddressInfo.EAI_AGAIN, "EAI_AGAIN");
        var0.put(ErrnoAddressInfo.EAI_BADFLAGS, "EAI_BADFLAGS");
        var0.put(ErrnoAddressInfo.EAI_FAIL, "EAI_FAIL");
        var0.put(ErrnoAddressInfo.EAI_FAMILY, "EAI_FAMILY");
        var0.put(ErrnoAddressInfo.EAI_MEMORY, "EAI_MEMORY");
        var0.put(ErrnoAddressInfo.EAI_NODATA, "EAI_NODATA");
        var0.put(ErrnoAddressInfo.EAI_NONAME, "EAI_NONAME");
        var0.put(ErrnoAddressInfo.EAI_OVERFLOW, "EAI_OVERFLOW");
        var0.put(ErrnoAddressInfo.EAI_SERVICE, "EAI_SERVICE");
        var0.put(ErrnoAddressInfo.EAI_SOCKTYPE, "EAI_SOCKTYPE");
        var0.put(ErrnoAddressInfo.EAI_SYSTEM, "EAI_SYSTEM");
        return var0;
    }

}