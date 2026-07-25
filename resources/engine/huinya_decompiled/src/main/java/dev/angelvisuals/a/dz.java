// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.dz
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.events.Event;

public class dz implements Event {

    // ---- поля ----
  private static final String Ep = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String Eq = "// === DO NOT TOUCH ===";
  private static final String Er = "// every class watermarked, every string encrypted, every number xored";
  private static final String Es = "// Joiner sees you";
  private static final String Et = "// class hierarchy hashing: ENABLED";
  private static final int rO = 1289236304;
  private static final int rP = 164103027;
  private static final int rQ = -1511614314;
  private static final byte[] eH;

    static {
        eH = "&-W_$P`C(I1lCAN5b+x\"Pn6S%6${/RY0#AHnG,\"y\\iAp0eg(,,_5QU<M+OTLYn+QN\"33l2}E/\"9~y'n)[_u]H@uC6GcHX)7O1*XdV$v5uoY#Ec[3?PI8Y!c~A[FCtKcU)3\"6UTEXP2?]$big'<0BA)ngxZn/0J+>d9YfQ-';F1$(TG1phPrWw-7KUZE5gEXh|>}zq3$f!4#0$(-~-4tv=jAMRs|(oK\"YK^:~-%~Vts9}lG(Zmm<n5/8B]#;O$@?C".getBytes("ISO-8859-1");
    }

  public dz() { // было: <init>
        super();
    }

  private static int pT(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int pU(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int pV(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}