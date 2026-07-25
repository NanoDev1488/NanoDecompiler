// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.S
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventTarget;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.ClassA24;
import dev.angelvisuals.a.bA;
import dev.angelvisuals.a.bI;
import dev.angelvisuals.a.cK;

@bI(name = "AspectRatio", a = "RENDER", I = "Изменяет соотношение сторон экрана")
public class ClassA96 extends cK {

    // ---- поля ----
  public static final ClassA96 field373; // было: a
  private final bA field374; // было: l
  private static final String dJ = "// reverse-engineering this jar is a waste of time, friend";
  private static final String dK = "// stop. seriously. go play minecraft instead";
  private static final String dL = "// flow obfuscation: ENABLED";
  private static final String dM = "// this jar protected by JoinerObfuscator";
  private static final String dN = "// flow obfuscation: ENABLED";
  private static final int ca = -416699538;
  private static final int cb = 1786785514;
  private static final int cc = 1386109201;
  private static final byte[] field375; // было: T

    static {
        field375 = "&DN]PX%N>VJb6cwRAsk3R:6r'jy/T(MS@|O9@?aW0eD7 L~jt\\NwK3$;F;sces%]?}n_mFRfGnqYo'p|de2.KB,pgBtNiJ=Jb(@;[qPoY{bJ7a?ZA (TD9,/%xy*OH| E+ShRnCLsZ|h:Tx$,\"9(q6]CIbwp j]<Hc9M#_Cf.$[SFF2t{k}5~I^<CP#?hw8`VLiPOwO7NSeZ@[5\\q1KE}:c%{D]u#v/WF0x7v$Q8;$V -#,D<[5Npn>bLIK@6U*\\".getBytes("ISO-8859-1");
        field373 = new ClassA96();
    }

  private ClassA96() { // было: <init>
        super();
        field374 = new bA(Decryptor.method1945(XorDecoder.method1946("\u001dûl+Évjî£D*¢®Z.©µo7Ê Y ©ô\u0007jóg\u0017À®]\u000c×¤AmÓ\u0008", 829588027 ^ 78977122)), 1.7799999713897705f, 0.5f, 4.0f, 0.009999999776482582f);
    }

    @EventTarget
  private void method754(ClassA24 arg0) { // было: a
        arg0.method296(field374.bp());
        arg0.setCancelled(426461892 ^ 426461893);
    }

  private static int bF(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int bG(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int bH(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}