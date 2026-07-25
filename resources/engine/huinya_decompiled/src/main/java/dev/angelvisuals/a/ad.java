// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.aD
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.be;
import dev.angelvisuals.a.dq;
import net.minecraft.class_1041;
import net.minecraft.class_284;
import net.minecraft.class_290;
import net.minecraft.class_2960;

public class aD extends be implements dq {

    // ---- поля ----
  private class_284 field938; // было: a
  private class_284 field939; // было: b
  private class_284 field940; // было: c
  private class_284 field941; // было: d
  private class_284 field942; // было: e
  private static final String ia = "// you are reading machine-generated garbage";
  private static final String ib = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String ic = "// this jar protected by JoinerObfuscator";
  private static final String id = "// this jar protected by JoinerObfuscator";
  private static final String ie = "// flow obfuscation: ENABLED";
  private static final int eH = -819116261;
  private static final int eI = -130484805;
  private static final int eJ = -95198886;
  private static final byte[] aK;

    static {
        aK = "7#WK<rk#%b,kM&%w@G*7j*-Ok9q8lR!7=0TRf5*298^nyMyOL%s0:$PPht:\\5nHDM3*jsBGDA| Y#1K3q h$oQrZ|!~<L^onkx8YdalB/TJgA1%)t@ #;nC(*K{tN3$$&*xbDzMDmQC!=OgmPat&kbRCbLaj2HWxuAVWi@O\\&`4}(/IjbUCarNs.h4a6KbDBmHOaXy nU/! :UV^\\kS_T=,0cK8hvGF.)!fF0?h.pRPTy'bnaK7*]ZL<)~C8Pt9A".getBytes("ISO-8859-1");
    }

  public aD(class_2960 arg0) { // было: <init>
        super(arg0, class_290.field_1575);
    }

  public void method1771(float arg0) { // было: n
        field939.method_1251(arg0);
        field938.method_1255(1.0f / ((float) mw.method_4480()), 1.0f / ((float) mw.method_4507()));
        field940.method_1251(1.0f);
        field941.method_1251(0.0f);
        field942.method_1249(1.0f, 1.0f, 1.0f);
    }

  protected void method1772() { // было: H
        field938 = a(Decryptor.method1945(XorDecoder.method1946("ÛËò§àÑëÌÓîÓëàß ôàç­Ç¨", -892686590 ^ 1600720794)));
        field939 = a(Decryptor.method1945(XorDecoder.method1946("éµ/£öµ\u0006ùÀgç£ný.ºðËb", -1852385987 ^ -832543498)));
        field940 = a(Decryptor.method1945(XorDecoder.method1946("1p¾Å5PÃì2gÆá@G·Ê q¡Ó#|Ëº", -741080424 ^ 1411530731)));
        field941 = a(Decryptor.method1945(XorDecoder.method1946("3ç²v(Ú°l7ÃW\u0012ñ©gNùÎT\u0015ìØ;", -1513378955 ^ -1557258228)));
        field942 = a(Decryptor.method1945(XorDecoder.method1946("WóôS|ÔÑSkÕÌAQÜû\u0003XÀj-ï\r", 1433305213 ^ 1708202086)));
        super.method1769();
    }

  private static int ee(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ef(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int eg(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}