// исходный (обфусцированный) внутренний класс: jnr.constants.platform.dragonflybsd.LangInfo.StringTable
package jnr.constants.platform.dragonflybsd;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.dragonflybsd.LangInfo;

final class LangInfo_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   LangInfo_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(LangInfo.class);
        var0.put(LangInfo.CODESET, "CODESET");
        var0.put(LangInfo.D_T_FMT, "D_T_FMT");
        var0.put(LangInfo.D_FMT, "D_FMT");
        var0.put(LangInfo.T_FMT, "T_FMT");
        var0.put(LangInfo.DAY_1, "DAY_1");
        var0.put(LangInfo.DAY_2, "DAY_2");
        var0.put(LangInfo.DAY_3, "DAY_3");
        var0.put(LangInfo.DAY_4, "DAY_4");
        var0.put(LangInfo.DAY_5, "DAY_5");
        var0.put(LangInfo.DAY_6, "DAY_6");
        var0.put(LangInfo.DAY_7, "DAY_7");
        var0.put(LangInfo.ABDAY_1, "ABDAY_1");
        var0.put(LangInfo.ABDAY_2, "ABDAY_2");
        var0.put(LangInfo.ABDAY_3, "ABDAY_3");
        var0.put(LangInfo.ABDAY_4, "ABDAY_4");
        var0.put(LangInfo.ABDAY_5, "ABDAY_5");
        var0.put(LangInfo.ABDAY_6, "ABDAY_6");
        var0.put(LangInfo.ABDAY_7, "ABDAY_7");
        var0.put(LangInfo.MON_1, "MON_1");
        var0.put(LangInfo.MON_2, "MON_2");
        var0.put(LangInfo.MON_3, "MON_3");
        var0.put(LangInfo.MON_4, "MON_4");
        var0.put(LangInfo.MON_5, "MON_5");
        var0.put(LangInfo.MON_6, "MON_6");
        var0.put(LangInfo.MON_7, "MON_7");
        var0.put(LangInfo.MON_8, "MON_8");
        var0.put(LangInfo.MON_9, "MON_9");
        var0.put(LangInfo.MON_10, "MON_10");
        var0.put(LangInfo.MON_11, "MON_11");
        var0.put(LangInfo.MON_12, "MON_12");
        var0.put(LangInfo.ABMON_1, "ABMON_1");
        var0.put(LangInfo.ABMON_2, "ABMON_2");
        var0.put(LangInfo.ABMON_3, "ABMON_3");
        var0.put(LangInfo.ABMON_4, "ABMON_4");
        var0.put(LangInfo.ABMON_5, "ABMON_5");
        var0.put(LangInfo.ABMON_6, "ABMON_6");
        var0.put(LangInfo.ABMON_7, "ABMON_7");
        var0.put(LangInfo.ABMON_8, "ABMON_8");
        var0.put(LangInfo.ABMON_9, "ABMON_9");
        var0.put(LangInfo.ABMON_10, "ABMON_10");
        var0.put(LangInfo.ABMON_11, "ABMON_11");
        var0.put(LangInfo.ABMON_12, "ABMON_12");
        var0.put(LangInfo.RADIXCHAR, "RADIXCHAR");
        var0.put(LangInfo.THOUSEP, "THOUSEP");
        var0.put(LangInfo.YESEXPR, "YESEXPR");
        var0.put(LangInfo.NOEXPR, "NOEXPR");
        var0.put(LangInfo.CRNCYSTR, "CRNCYSTR");
        return var0;
    }

}