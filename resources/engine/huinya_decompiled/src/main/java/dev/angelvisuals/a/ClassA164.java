// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.A
package dev.angelvisuals.a;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.angelvisuals.a.dq;
import net.minecraft.class_1041;
import net.minecraft.class_276;
import net.minecraft.class_310;

public class ClassA164 extends class_276 implements dq {

    // ---- поля ----
  private boolean field920; // было: f
  private static final String bI = "// === DO NOT TOUCH ===";
  private static final String bJ = "// you are reading machine-generated garbage";
  private static final String bK = "// number obfuscation: ENABLED (XOR masking)";
  private static final String bL = "// stop. seriously. go play minecraft instead";
  private static final String bM = "// nice try. closed source for a reason.";
  private static final int aL = -518038508;
  private static final int aM = -1970822231;
  private static final int aN = -2055716255;
  private static final byte[] field921; // было: B

    static {
        field921 = "Hxpa#<`7$F R:rOcbj3@Q4j*<FfP=S^L0^!HV'xJL6acTsEvJ}~{'V|=\"N}Wv4K<Y6-RsY1%lR+pTAOT)>CXZWF/:9:#lKMT^2?ib?Z%6.0Gd~xX87Br_#bNb~,!c!@cRwV>CdbR^|oX]5sJyN|s@'[8?H9U8@w4cqBI7svT;;pSo+KLpMUnTerV=QA:rB]Cif$q|vm0Svc*Fox=}UoML*t_.=NXq`]+?oc]{p>?0c=uO_'~6(nSO`{A?>:]]s{1".getBytes("ISO-8859-1");
    }

  public ClassA164(boolean arg0) { // было: <init>
        super(arg0);
    }

  public ClassA164(int arg0, int arg1, boolean arg2) { // было: <init>
        super(arg2);
        method_1234(arg0, arg1);
    }

  public ClassA164 method1721() { // было: a
        field920 = -1677886433 ^ -1677886434;
        RenderSystem.recordRenderCall(() -> method1727());
        return this;
    }

  public void method1722(int arg0) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: aload_0
        //      2: getfield  #42 // dev.angelvisuals.a.A.f:Z
        //      5: ifeq  16 (offset +11)
        //      8: ldc  #17 // 1098927266
        //     10: ldc  #18 // 1098934947
        //     12: ixor
        //     13: goto  17 (offset +4)
        //     16: iload_1
        //     17: invokespecial  #65 // net.minecraft.class_276.method_58226:(I)V
        //     20: return
    }

  private void method1723() { // было: g
        if (method1726()) {
            method_1231(Math.max(mw.method_4480(), 772175817 ^ 772175816), Math.max(mw.method_4507(), -93654209 ^ -93654210));
        }
    }

  public void method1724(boolean arg0) { // было: d
        method1723();
        if (arg0) {
            method_1230();
        }
        method_1235(834445597 ^ 834445597);
    }

  public void setup() {
        method1724(495280256 ^ 495280257);
    }

  public void stop() {
        method_1240();
        mc.method_1522().method_1235(1285363448 ^ 1285363448);
    }

  public void method1725() { // было: h
        method_1240();
        mc.method_1522().method_1235(1877648500 ^ 1877648501);
    }

  private boolean method1726() { // было: n
        return field_1482 != mw.method_4480() ? -514053874 ^ -514053873 : field_1481 == mw.method_4507() ? 1558634324 ^ 1558634324 : -514053874 ^ -514053873;
    }

  private void method1727() { // было: i
        method1722(-1898097894 ^ -1898089189);
    }

  private static int aD(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int aE(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int aF(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}