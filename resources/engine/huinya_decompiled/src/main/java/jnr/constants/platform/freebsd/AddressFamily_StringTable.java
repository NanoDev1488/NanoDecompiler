// исходный (обфусцированный) внутренний класс: jnr.constants.platform.freebsd.AddressFamily.StringTable
package jnr.constants.platform.freebsd;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.freebsd.AddressFamily;

final class AddressFamily_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   AddressFamily_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(AddressFamily.class);
        var0.put(AddressFamily.AF_UNSPEC, "AF_UNSPEC");
        var0.put(AddressFamily.AF_LOCAL, "AF_LOCAL");
        var0.put(AddressFamily.AF_UNIX, "AF_UNIX");
        var0.put(AddressFamily.AF_INET, "AF_INET");
        var0.put(AddressFamily.AF_IMPLINK, "AF_IMPLINK");
        var0.put(AddressFamily.AF_PUP, "AF_PUP");
        var0.put(AddressFamily.AF_CHAOS, "AF_CHAOS");
        var0.put(AddressFamily.AF_ISO, "AF_ISO");
        var0.put(AddressFamily.AF_OSI, "AF_OSI");
        var0.put(AddressFamily.AF_ECMA, "AF_ECMA");
        var0.put(AddressFamily.AF_DATAKIT, "AF_DATAKIT");
        var0.put(AddressFamily.AF_CCITT, "AF_CCITT");
        var0.put(AddressFamily.AF_SNA, "AF_SNA");
        var0.put(AddressFamily.AF_DECnet, "AF_DECnet");
        var0.put(AddressFamily.AF_DLI, "AF_DLI");
        var0.put(AddressFamily.AF_LAT, "AF_LAT");
        var0.put(AddressFamily.AF_HYLINK, "AF_HYLINK");
        var0.put(AddressFamily.AF_APPLETALK, "AF_APPLETALK");
        var0.put(AddressFamily.AF_ROUTE, "AF_ROUTE");
        var0.put(AddressFamily.AF_LINK, "AF_LINK");
        var0.put(AddressFamily.pseudo_AF_XTP, "pseudo_AF_XTP");
        var0.put(AddressFamily.AF_COIP, "AF_COIP");
        var0.put(AddressFamily.AF_CNT, "AF_CNT");
        var0.put(AddressFamily.pseudo_AF_RTIP, "pseudo_AF_RTIP");
        var0.put(AddressFamily.AF_IPX, "AF_IPX");
        var0.put(AddressFamily.AF_SIP, "AF_SIP");
        var0.put(AddressFamily.pseudo_AF_PIP, "pseudo_AF_PIP");
        var0.put(AddressFamily.AF_ISDN, "AF_ISDN");
        var0.put(AddressFamily.AF_E164, "AF_E164");
        var0.put(AddressFamily.pseudo_AF_KEY, "pseudo_AF_KEY");
        var0.put(AddressFamily.AF_INET6, "AF_INET6");
        var0.put(AddressFamily.AF_NATM, "AF_NATM");
        var0.put(AddressFamily.AF_NETBIOS, "AF_NETBIOS");
        var0.put(AddressFamily.AF_ATM, "AF_ATM");
        var0.put(AddressFamily.pseudo_AF_HDRCMPLT, "pseudo_AF_HDRCMPLT");
        var0.put(AddressFamily.AF_NETGRAPH, "AF_NETGRAPH");
        var0.put(AddressFamily.AF_MAX, "AF_MAX");
        return var0;
    }

}