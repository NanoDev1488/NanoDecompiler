// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.bo
package dev.angelvisuals.a;

import dev.angelvisuals.AngelVisuals;
import java.io.File;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.function.Supplier;
import lombok.Generated;

public class bo {

    // ---- поля ----
  private Collection field158; // было: a
  private final String mI;
  private final String mJ;
  private final Type field159; // было: a
  private final Supplier field160; // было: b
  private static final String mK = "// good luck with the next 9999 classes";
  private static final String mL = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String mM = "// === DO NOT TOUCH ===";
  private static final String mN = "// you are reading machine-generated garbage";
  private static final String mO = "// Joiner sees you";
  private static final int hw = 338779018;
  private static final int hx = -1823103867;
  private static final int hy = -598207004;
  private static final byte[] bC;

    static {
        bC = "cJc3v-%?rF7bb[(GcrUG\"Qj)Fj5YJ2!a.B:dAdMz\"yk&S,yy\"ud|+T5Z;Q5G|rylXUc+5a[T^|$lIc1Z}]u?z4;wwoR{#r\\^K<e=u0n]d^E0M<Vn w;BX\"qr\\Z!3 L0Xlk>9?B&C=>=f.'3uvO-'-yP^^%u<jU,^T&kp?Zxz&R.:!1Rje.RgeTCGen5})DuvT=:T!}VwX;J6NAD;4]e.xa~Y7LO\\+r%XD&OK>3<xL?oZNA8`Pk/4c&Z^V<<(G>V$".getBytes("ISO-8859-1");
    }

  public bo(String arg0, String arg1, Type arg2, Supplier arg3) { // было: <init>
        super();
        mI = arg0;
        mJ = arg1;
        field159 = arg2;
        field160 = arg3;
        File var5 = new File(AngelVisuals.DIRECTORY, arg0);
        if (var5.exists()) {
            af();
        } else {
            try {
                var5.createNewFile();
                field158 = ((Collection) arg3.get());
            } catch (Exception var6) {
                field158 = ((Collection) arg3.get());
            }
        }
    }

  public void ae() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: new  #11 // com.google.gson.Gson
        //      3: dup
        //      4: invokespecial  #37 // com.google.gson.Gson.<init>:()V
        //      7: astore_1
        //      8: aload_1
        //      9: aload_0
        //     10: getfield  #31 // dev.angelvisuals.a.bo.a:Ljava/util/Collection;
        //     13: invokevirtual  #40 // com.google.gson.Gson.toJson:(Ljava/lang/Object;)Ljava/lang/String;
        //     16: astore_2
        //     17: new  #18 // java.io.FileWriter
        //     20: dup
        //     21: new  #16 // java.io.File
        //     24: dup
        //     25: getstatic  #29 // dev.angelvisuals.AngelVisuals.DIRECTORY:Ljava/io/File;
        //     28: aload_0
        //     29: getfield  #34 // dev.angelvisuals.a.bo.mI:Ljava/lang/String;
        //     32: invokespecial  #47 // java.io.File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //     35: invokespecial  #51 // java.io.FileWriter.<init>:(Ljava/io/File;)V
        //     38: astore_3
        //     39: aload_3
        //     40: aload_0
        //     41: getfield  #35 // dev.angelvisuals.a.bo.mJ:Ljava/lang/String;
        //     44: invokevirtual  #58 // java.lang.String.isEmpty:()Z
        //     47: ifeq  54 (offset +7)
        //     50: aload_2
        //     51: goto  71 (offset +20)
        //     54: invokestatic  #61 // java.util.Base64.getEncoder:()Ljava/util/Base64$Encoder;
        //     57: aload_2
        //     58: invokevirtual  #56 // java.lang.String.getBytes:()[B
        //     61: aload_0
        //     62: getfield  #35 // dev.angelvisuals.a.bo.mJ:Ljava/lang/String;
        //     65: invokestatic  #43 // dev.angelvisuals.a.dy.b:([BLjava/lang/String;)[B
        //     68: invokevirtual  #63 // java.util.Base64$Encoder.encodeToString:([B)Ljava/lang/String;
        //     71: invokevirtual  #53 // java.io.FileWriter.write:(Ljava/lang/String;)V
        //     74: goto  98 (offset +24)
        //     77: astore  4
        //     79: aload_3
        //     80: invokevirtual  #52 // java.io.FileWriter.close:()V
        //     83: goto  95 (offset +12)
        //     86: astore  5
        //     88: aload  4
        //     90: aload  5
        //     92: invokevirtual  #59 // java.lang.Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //     95: aload  4
        //     97: athrow
        //     98: aload_3
        //     99: invokevirtual  #52 // java.io.FileWriter.close:()V
        //    102: goto  106 (offset +4)
        //    105: astore_3
        //    106: return
        //       Exception table:
        //         from 39 to 74 target 77 type java.lang.Throwable
        //         from 79 to 83 target 86 type java.lang.Throwable
        //         from 17 to 102 target 105 type java.lang.Exception
    }

  public void af() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: new  #15 // java.io.BufferedReader
        //      3: dup
        //      4: new  #17 // java.io.FileReader
        //      7: dup
        //      8: new  #16 // java.io.File
        //     11: dup
        //     12: getstatic  #29 // dev.angelvisuals.AngelVisuals.DIRECTORY:Ljava/io/File;
        //     15: aload_0
        //     16: getfield  #34 // dev.angelvisuals.a.bo.mI:Ljava/lang/String;
        //     19: invokespecial  #47 // java.io.File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //     22: invokespecial  #50 // java.io.FileReader.<init>:(Ljava/io/File;)V
        //     25: invokespecial  #44 // java.io.BufferedReader.<init>:(Ljava/io/Reader;)V
        //     28: astore_1
        //     29: new  #11 // com.google.gson.Gson
        //     32: dup
        //     33: invokespecial  #37 // com.google.gson.Gson.<init>:()V
        //     36: astore_2
        //     37: aload_0
        //     38: getfield  #35 // dev.angelvisuals.a.bo.mJ:Ljava/lang/String;
        //     41: invokevirtual  #58 // java.lang.String.isEmpty:()Z
        //     44: ifne  136 (offset +92)
        //     47: aload_1
        //     48: invokevirtual  #46 // java.io.BufferedReader.readLine:()Ljava/lang/String;
        //     51: astore_3
        //     52: aload_3
        //     53: ifnull  117 (offset +64)
        //     56: aload_3
        //     57: invokevirtual  #58 // java.lang.String.isEmpty:()Z
        //     60: ifne  117 (offset +57)
        //     63: invokestatic  #60 // java.util.Base64.getDecoder:()Ljava/util/Base64$Decoder;
        //     66: aload_3
        //     67: invokevirtual  #62 // java.util.Base64$Decoder.decode:(Ljava/lang/String;)[B
        //     70: astore  4
        //     72: aload  4
        //     74: aload_0
        //     75: getfield  #35 // dev.angelvisuals.a.bo.mJ:Ljava/lang/String;
        //     78: invokestatic  #42 // dev.angelvisuals.a.dy.a:([BLjava/lang/String;)[B
        //     81: astore  5
        //     83: new  #21 // java.lang.String
        //     86: dup
        //     87: aload  5
        //     89: getstatic  #36 // java.nio.charset.StandardCharsets.UTF_8:Ljava/nio/charset/Charset;
        //     92: invokespecial  #55 // java.lang.String.<init>:([BLjava/nio/charset/Charset;)V
        //     95: astore  6
        //     97: aload_0
        //     98: aload_2
        //     99: aload  6
        //    101: aload_0
        //    102: getfield  #30 // dev.angelvisuals.a.bo.a:Ljava/lang/reflect/Type;
        //    105: invokevirtual  #39 // com.google.gson.Gson.fromJson:(Ljava/lang/String;Ljava/lang/reflect/Type;)Ljava/lang/Object;
        //    108: checkcast  #27 // java.util.Collection
        //    111: putfield  #31 // dev.angelvisuals.a.bo.a:Ljava/util/Collection;
        //    114: goto  133 (offset +19)
        //    117: aload_0
        //    118: aload_0
        //    119: getfield  #32 // dev.angelvisuals.a.bo.b:Ljava/util/function/Supplier;
        //    122: invokeinterface  #66 // java.util.function.Supplier.get:()Ljava/lang/Object;, count 1
        //    127: checkcast  #27 // java.util.Collection
        //    130: putfield  #31 // dev.angelvisuals.a.bo.a:Ljava/util/Collection;
        //    133: goto  152 (offset +19)
        //    136: aload_0
        //    137: aload_2
        //    138: aload_1
        //    139: aload_0
        //    140: getfield  #30 // dev.angelvisuals.a.bo.a:Ljava/lang/reflect/Type;
        //    143: invokevirtual  #38 // com.google.gson.Gson.fromJson:(Ljava/io/Reader;Ljava/lang/reflect/Type;)Ljava/lang/Object;
        //    146: checkcast  #27 // java.util.Collection
        //    149: putfield  #31 // dev.angelvisuals.a.bo.a:Ljava/util/Collection;
        //    152: aload_0
        //    153: getfield  #31 // dev.angelvisuals.a.bo.a:Ljava/util/Collection;
        //    156: ifnonnull  175 (offset +19)
        //    159: aload_0
        //    160: aload_0
        //    161: getfield  #32 // dev.angelvisuals.a.bo.b:Ljava/util/function/Supplier;
        //    164: invokeinterface  #66 // java.util.function.Supplier.get:()Ljava/lang/Object;, count 1
        //    169: checkcast  #27 // java.util.Collection
        //    172: putfield  #31 // dev.angelvisuals.a.bo.a:Ljava/util/Collection;
        //    175: goto  194 (offset +19)
        //    178: astore_2
        //    179: aload_1
        //    180: invokevirtual  #45 // java.io.BufferedReader.close:()V
        //    183: goto  192 (offset +9)
        //    186: astore_3
        //    187: aload_2
        //    188: aload_3
        //    189: invokevirtual  #59 // java.lang.Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //    192: aload_2
        //    193: athrow
        //    194: aload_1
        //    195: invokevirtual  #45 // java.io.BufferedReader.close:()V
        //    198: goto  218 (offset +20)
        //    201: astore_1
        //    202: aload_0
        //    203: aload_0
        //    204: getfield  #32 // dev.angelvisuals.a.bo.b:Ljava/util/function/Supplier;
        //    207: invokeinterface  #66 // java.util.function.Supplier.get:()Ljava/lang/Object;, count 1
        //    212: checkcast  #27 // java.util.Collection
        //    215: putfield  #31 // dev.angelvisuals.a.bo.a:Ljava/util/Collection;
        //    218: return
        //       Exception table:
        //         from 29 to 175 target 178 type java.lang.Throwable
        //         from 179 to 183 target 186 type java.lang.Throwable
        //         from 0 to 198 target 201 type java.lang.Exception
    }

  public void method342(Object arg0) { // было: b
        field158.add(arg0);
    }

  public void method343(Object arg0) { // было: c
        field158.remove(arg0);
    }

    @Generated
  public Collection method344() { // было: b
        return field158;
    }

  private static int gG(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int gH(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int gI(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}