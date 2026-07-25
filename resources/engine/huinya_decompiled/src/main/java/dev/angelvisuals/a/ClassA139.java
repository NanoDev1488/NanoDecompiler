// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.D
package dev.angelvisuals.a;

import lombok.Generated;
import ru.nexusguard.protection.annotations.Native;

public enum ClassA139 {

    field715(-1515893911 ^ -1515893911),
    field716(-1760170297 ^ -1760170298),
    field717(1419281844 ^ 1419281846),
    field718(-1203160046 ^ -1203160047),
    field719(-1364873252 ^ -1364873256),
    field720(-1957735045 ^ -1957735042),
    field721(19515623 ^ 19515617);

    // ---- поля ----
  private final int aU;
  private static final ClassA139[] field722; // было: a
  private static final String bY = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String bZ = "// nice try. closed source for a reason.";
  private static final String ca = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String cb = "// you are reading machine-generated garbage";
  private static final String cc = "Protected by t.me/JoinerClient";
  private static final int aV = 53529932;
  private static final int aW = 1684939244;
  private static final int aX = -1234439044;
  private static final byte[] field723; // было: E

  public static ClassA139[] method1173() { // было: a
        return ((ClassA139[]) field722.clone());
    }

  public static ClassA139 method1174(String arg0) { // было: a
        return ((ClassA139) Enum.valueOf(ClassA139.class, arg0));
    }

    @Native
  public static ClassA139 method1175(int arg0) { // было: a
        ClassA139[] var1 = method1173();
        int var2 = var1.length;
        int var3 = -315696505 ^ -315696505;
        Object var4;
        while (true) {
            if (var3 >= var2) {
                return field715;
            }
            var4 = var1[var3];
            if (var4.method1176() == arg0) {
                break;
            }
            ++var3;
            continue;
        }
        return ((D) var4);
    }

    @Generated
  private ClassA139(int arg2) { // было: <init>
        aU = arg2;
    }

    @Generated
  public int method1176() { // было: c
        return aU;
    }

  private static ClassA139[] method1177() { // было: b
        ClassA139[] __obj1 = new ClassA139[-921568882 ^ -921568887];
        __obj1[1201132757 ^ 1201132757] = field715;
        __obj1[550972457 ^ 550972456] = field716;
        __obj1[1667421369 ^ 1667421371] = field717;
        __obj1[280167327 ^ 280167324] = field718;
        __obj1[-982881597 ^ -982881593] = field719;
        __obj1[1259208072 ^ 1259208077] = field720;
        __obj1[749013141 ^ 749013139] = field721;
        return __obj1;
    }

  private static ClassA139[] method1178() { // было: c
        ClassA139[] __obj1 = new ClassA139[-974770838 ^ -974770835];
        __obj1[-1763540201 ^ -1763540201] = field715;
        __obj1[-2108200837 ^ -2108200838] = field716;
        __obj1[1701417456 ^ 1701417458] = field717;
        __obj1[-1022789765 ^ -1022789768] = field718;
        __obj1[389411962 ^ 389411966] = field719;
        __obj1[-191898799 ^ -191898796] = field720;
        __obj1[1450460832 ^ 1450460838] = field721;
        return __obj1;
    }

  private static int aM(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int aN(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int aO(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}