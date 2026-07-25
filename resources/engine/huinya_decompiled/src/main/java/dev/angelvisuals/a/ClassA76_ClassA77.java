// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.Q.a
package dev.angelvisuals.a;

import dev.angelvisuals.a.ClassA2;
import dev.angelvisuals.a.aH;
import net.minecraft.class_1293;

class ClassA76_ClassA77 {

    // ---- поля ----
   String dy;
   int bP;
   int bQ;
   boolean field268; // было: j
   class_1293 field269; // было: a
   ClassA2 field270; // было: e
  private static final String dz = "// nice try. closed source for a reason.";
  private static final String dA = "// === DO NOT TOUCH ===";
  private static final String dB = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String dC = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String dD = "// reverse-engineering this jar is a waste of time, friend";
  private static final int bR = 1099597932;
  private static final int bS = -1048803652;
  private static final int bT = -2091087128;
  private static final byte[] field271; // было: R

    static {
        field271 = "JewYDqu95did0[t$p8N=CNzy|9vMOo[;{Y*nC!.,4hc*uWT?SPGSP}LG[NiwlHUc)\\a(kZ.~]-BrJ\"\\?hFSwHza|IkYuH_TuJj@0V};dEgl=N[j(A?21d8}^,~k['s/$.=qai_4Kdb?@2ttA\\7SF::#6qoINGoN* ~V43o~EcrF?G-L4Cq9v>(@t1[A>AG,uPh[wJx?Q?}G}UjFoUfSIV0W5VO#\"|j6KoBz%`^]9`D_*l-slrK_pDLVXfV:/SYRJ".getBytes("ISO-8859-1");
    }

   ClassA76_ClassA77(String arg0, int arg1, int arg2, class_1293 arg3) { // было: <init>
        super();
        field270 = new ClassA2(-6135384795824021447L ^ -6135384795824021309L, aH.field21);
        dy = arg0;
        bP = arg1;
        bQ = arg2;
        field268 = 309729180 ^ 309729181;
        field269 = arg3;
    }

  private static int bz(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int bA(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int bB(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}