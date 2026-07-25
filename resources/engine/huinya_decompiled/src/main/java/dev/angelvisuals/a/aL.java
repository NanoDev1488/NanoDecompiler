// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.al
package dev.angelvisuals.a;

import dev.angelvisuals.a.al_ClassA170;
import dev.angelvisuals.a.bp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.class_243;
import net.minecraft.class_4587;

public class al {

    // ---- поля ----
  private static final List field976; // было: m
  private static final String iF = "// number obfuscation: ENABLED (XOR masking)";
  private static final String iG = "// Joiner sees you";
  private static final String iH = "// this jar protected by JoinerObfuscator";
  private static final String iI = "// stop. seriously. go play minecraft instead";
  private static final String iJ = "// this jar protected by JoinerObfuscator";
  private static final int fa = 1724452483;
  private static final int fb = -443088274;
  private static final int fc = -733700144;
  private static final byte[] aQ;

    static {
        aQ = "VQK9kT`^ )WSWJD4q!8\"e!GYuq2$@L~^/'O;Ea`6oJ#~;J>8\">gyG90h7.7%K7Lmt$1qD@hfDlcGa2^LN{D)ach:=EmlsPl!V[3j {>tB]~D(i4*d5EnbR5FA/h<u{dWXQtGJv{a0N_dnX^3 :ev_'((fDijmbAl<rV:c9Ncd/ye|;Hf693-P)gr2!fo~t/NOgvMxtez$5(W!M\"\\qv:L?|}H:*zI;4ai0Kg8\"&D\\4#!3oOb/,9C|6[sigm6,**jZ".getBytes("ISO-8859-1");
        field976 = new ArrayList();
    }

  public al() { // было: <init>
        super();
    }

  public static void method1842(class_243 arg0, class_243 arg1, bp arg2, float arg3, long arg4) { // было: a
        field976.add(new al_ClassA170(arg0, arg1, arg2, arg3, arg4));
    }

  public static void method1843() { // было: M
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #18 // dev.angelvisuals.a.al.m:Ljava/util/List;
        //      3: invokeinterface  #30 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //      8: astore_0
        //      9: aload_0
        //     10: invokeinterface  #26 // java.util.Iterator.hasNext:()Z, count 1
        //     15: ifeq  51 (offset +36)
        //     18: aload_0
        //     19: invokeinterface  #27 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //     24: checkcast  #11 // dev.angelvisuals.a.al$a
        //     27: astore_1
        //     28: aload_1
        //     29: invokevirtual  #21 // dev.angelvisuals.a.al$a.s:()Z
        //     32: ifeq  44 (offset +12)
        //     35: aload_0
        //     36: invokeinterface  #28 // java.util.Iterator.remove:()V, count 1
        //     41: goto  9 (offset -32)
        //     44: aload_1
        //     45: invokevirtual  #22 // dev.angelvisuals.a.al$a.u:()V
        //     48: goto  9 (offset -39)
        //     51: return
    }

  public static void method1844(class_4587 arg0) { // было: b
        Iterator var1 = field976.iterator();
        while (var1.hasNext()) {
            al_ClassA170 var2 = ((al_ClassA170) var1.next());
            var2.method1841(arg0);
            continue;
        }
    }

  private static int ew(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ex(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ey(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}