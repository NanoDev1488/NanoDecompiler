// исходный (обфусцированный) внутренний класс: jnr.constants.platform.darwin.AddressInfo.StringTable
package jnr.constants.platform.darwin;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.darwin.AddressInfo;

final class AddressInfo_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   AddressInfo_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(AddressInfo.class);
        var0.put(AddressInfo.AI_PASSIVE, "AI_PASSIVE");
        var0.put(AddressInfo.AI_CANONNAME, "AI_CANONNAME");
        var0.put(AddressInfo.AI_NUMERICHOST, "AI_NUMERICHOST");
        var0.put(AddressInfo.AI_NUMERICSERV, "AI_NUMERICSERV");
        var0.put(AddressInfo.AI_MASK, "AI_MASK");
        var0.put(AddressInfo.AI_ALL, "AI_ALL");
        var0.put(AddressInfo.AI_V4MAPPED_CFG, "AI_V4MAPPED_CFG");
        var0.put(AddressInfo.AI_ADDRCONFIG, "AI_ADDRCONFIG");
        var0.put(AddressInfo.AI_V4MAPPED, "AI_V4MAPPED");
        var0.put(AddressInfo.AI_DEFAULT, "AI_DEFAULT");
        return var0;
    }

}