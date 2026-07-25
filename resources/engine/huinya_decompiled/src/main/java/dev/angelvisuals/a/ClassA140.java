// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.W
package dev.angelvisuals.a;

import dev.angelvisuals.a.cF;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_2596;
import net.minecraft.class_310;
import net.minecraft.class_634;
import ru.nexusguard.protection.annotations.Native;

public class ClassA140 implements cF {

    // ---- поля ----
  private static final List field724; // было: f
  private static final String ed = "// you are reading machine-generated garbage";
  private static final String ee = "Protected by t.me/JoinerClient";
  private static final String ef = "// nice try. closed source for a reason.";
  private static final String eg = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String eh = "// good luck with the next 9999 classes";
  private static final int cn = 2137548989;
  private static final int co = 923197982;
  private static final int cp = -304219040;
  private static final byte[] field725; // было: X

    static {
        field725 = "|Wt6V<,8,.P9LO\\.6CX&R % P@ne?RyY$8B0*h:3\\E~vBTF!ntH'yaADKEAfuQ,&slo91\\nVlJ\\7PDWdY9Tr*VLkwVW~P}|vbH$}: Y;dfd`sHT0]ES_cN&l$q/Rm5dT)bP6G]+2@'.$fqBcklvIuc#$8Ictzn|t:pc~;L1tAp@h=QS$+mv;PPyFJ=x6'GHX,emu#UnE*v!Jcd'LoI(*G`}c~=8UE1|3:FY$SrB={Sli_m3kz6?SGn<C{,q?AVgf".getBytes("ISO-8859-1");
        field724 = new ArrayList();
    }

  public ClassA140() { // было: <init>
        super();
    }

    @Native
  public static void method1179(class_2596 arg0) { // было: b
        field724.add(arg0);
        mc.method_1562().method_52787(arg0);
    }

  public static void method1180(class_2596 arg0) { // было: c
        mc.method_1562().method_52787(arg0);
    }

  public static List method1181() { // было: e
        return field724;
    }

  public static void method1182() { // было: n
        field724.clear();
    }

  private static int bR(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int bS(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int bT(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}