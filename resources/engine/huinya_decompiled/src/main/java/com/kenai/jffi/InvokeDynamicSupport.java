// исходный (обфусцированный) внутренний класс: com.kenai.jffi.InvokeDynamicSupport
package com.kenai.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.InvokeDynamicSupport_Invoker;

public final class InvokeDynamicSupport {

  private InvokeDynamicSupport() { // было: <init>
        super();
    }

  public static InvokeDynamicSupport_Invoker getFastNumericInvoker(CallContext arg0, long arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: invokestatic  #36 // com.kenai.jffi.Platform.getPlatform:()Lcom/kenai/jffi/Platform;
        //      3: invokevirtual  #35 // com.kenai.jffi.Platform.getCPU:()Lcom/kenai/jffi/Platform$CPU;
        //      6: astore_3
        //      7: aload_0
        //      8: invokevirtual  #31 // com.kenai.jffi.CallContext.getReturnType:()Lcom/kenai/jffi/Type;
        //     11: instanceof  #13 // com.kenai.jffi.Type$Builtin
        //     14: ifne  19 (offset +5)
        //     17: aconst_null
        //     18: areturn
        //     19: aload_0
        //     20: getfield  #21 // com.kenai.jffi.CallContext.flags:I
        //     23: iconst_1
        //     24: iand
        //     25: ifeq  30 (offset +5)
        //     28: aconst_null
        //     29: areturn
        //     30: aload_0
        //     31: invokevirtual  #29 // com.kenai.jffi.CallContext.getParameterCount:()I
        //     34: bipush  6
        //     36: if_icmple  41 (offset +5)
        //     39: aconst_null
        //     40: areturn
        //     41: iconst_0
        //     42: istore  4
        //     44: iconst_0
        //     45: istore  5
        //     47: aload_0
        //     48: invokevirtual  #31 // com.kenai.jffi.CallContext.getReturnType:()Lcom/kenai/jffi/Type;
        //     51: invokevirtual  #37 // com.kenai.jffi.Type.type:()I
        //     54: tableswitch  default->199, 0->193, 1->199, 2->199, 3->199, 4->199, 5->128, 6->128, 7->128, 8->128, 9->128, 10->128, 11->185, 12->185, 13->191, 14->150
        //    128: iconst_1
        //    129: istore  4
        //    131: aload_3
        //    132: getfield  #25 // com.kenai.jffi.Platform$CPU.dataModel:I
        //    135: bipush  64
        //    137: if_icmpne  144 (offset +7)
        //    140: iconst_1
        //    141: goto  145 (offset +4)
        //    144: iconst_0
        //    145: istore  5
        //    147: goto  199 (offset +52)
        //    150: aload_3
        //    151: getfield  #25 // com.kenai.jffi.Platform$CPU.dataModel:I
        //    154: bipush  32
        //    156: if_icmpne  163 (offset +7)
        //    159: iconst_1
        //    160: goto  164 (offset +4)
        //    163: iconst_0
        //    164: istore  4
        //    166: aload_3
        //    167: getfield  #25 // com.kenai.jffi.Platform$CPU.dataModel:I
        //    170: bipush  64
        //    172: if_icmpne  179 (offset +7)
        //    175: iconst_1
        //    176: goto  180 (offset +4)
        //    179: iconst_0
        //    180: istore  5
        //    182: goto  199 (offset +17)
        //    185: iconst_1
        //    186: istore  5
        //    188: goto  199 (offset +11)
        //    191: aconst_null
        //    192: areturn
        //    193: iconst_1
        //    194: dup
        //    195: istore  5
        //    197: istore  4
        //    199: iload  4
        //    201: aload_3
        //    202: getstatic  #23 // com.kenai.jffi.Platform$CPU.I386:Lcom/kenai/jffi/Platform$CPU;
        //    205: if_acmpeq  215 (offset +10)
        //    208: aload_3
        //    209: getstatic  #24 // com.kenai.jffi.Platform$CPU.X86_64:Lcom/kenai/jffi/Platform$CPU;
        //    212: if_acmpne  219 (offset +7)
        //    215: iconst_1
        //    216: goto  220 (offset +4)
        //    219: iconst_0
        //    220: iand
        //    221: istore  4
        //    223: iload  5
        //    225: aload_3
        //    226: getstatic  #23 // com.kenai.jffi.Platform$CPU.I386:Lcom/kenai/jffi/Platform$CPU;
        //    229: if_acmpeq  239 (offset +10)
        //    232: aload_3
        //    233: getstatic  #24 // com.kenai.jffi.Platform$CPU.X86_64:Lcom/kenai/jffi/Platform$CPU;
        //    236: if_acmpne  243 (offset +7)
        //    239: iconst_1
        //    240: goto  244 (offset +4)
        //    243: iconst_0
        //    244: iand
        //    245: istore  5
        //    247: iconst_0
        //    248: istore  6
        //    250: iload  6
        //    252: aload_0
        //    253: invokevirtual  #29 // com.kenai.jffi.CallContext.getParameterCount:()I
        //    256: if_icmpge  431 (offset +175)
        //    259: iload  4
        //    261: ifne  269 (offset +8)
        //    264: iload  5
        //    266: ifeq  431 (offset +165)
        //    269: aload_0
        //    270: iload  6
        //    272: invokevirtual  #30 // com.kenai.jffi.CallContext.getParameterType:(I)Lcom/kenai/jffi/Type;
        //    275: instanceof  #13 // com.kenai.jffi.Type$Builtin
        //    278: ifne  283 (offset +5)
        //    281: aconst_null
        //    282: areturn
        //    283: aload_0
        //    284: iload  6
        //    286: invokevirtual  #30 // com.kenai.jffi.CallContext.getParameterType:(I)Lcom/kenai/jffi/Type;
        //    289: invokevirtual  #37 // com.kenai.jffi.Type.type:()I
        //    292: tableswitch  default->419, 5->348, 6->348, 7->348, 8->348, 9->348, 10->348, 11->370, 12->370, 13->417, 14->376
        //    348: iload  5
        //    350: aload_3
        //    351: getfield  #25 // com.kenai.jffi.Platform$CPU.dataModel:I
        //    354: bipush  64
        //    356: if_icmpne  363 (offset +7)
        //    359: iconst_1
        //    360: goto  364 (offset +4)
        //    363: iconst_0
        //    364: iand
        //    365: istore  5
        //    367: goto  425 (offset +58)
        //    370: iconst_0
        //    371: istore  4
        //    373: goto  425 (offset +52)
        //    376: iload  4
        //    378: aload_3
        //    379: getfield  #25 // com.kenai.jffi.Platform$CPU.dataModel:I
        //    382: bipush  32
        //    384: if_icmpne  391 (offset +7)
        //    387: iconst_1
        //    388: goto  392 (offset +4)
        //    391: iconst_0
        //    392: iand
        //    393: istore  4
        //    395: iload  5
        //    397: aload_3
        //    398: getfield  #25 // com.kenai.jffi.Platform$CPU.dataModel:I
        //    401: bipush  64
        //    403: if_icmpne  410 (offset +7)
        //    406: iconst_1
        //    407: goto  411 (offset +4)
        //    410: iconst_0
        //    411: iand
        //    412: istore  5
        //    414: goto  425 (offset +11)
        //    417: aconst_null
        //    418: areturn
        //    419: iconst_0
        //    420: dup
        //    421: istore  5
        //    423: istore  4
        //    425: iinc  6, 1
        //    428: goto  250 (offset -178)
        //    431: iload  4
        //    433: ifeq  442 (offset +9)
        //    436: getstatic  #26 // java.lang.Integer.TYPE:Ljava/lang/Class;
        //    439: goto  445 (offset +6)
        //    442: getstatic  #27 // java.lang.Long.TYPE:Ljava/lang/Class;
        //    445: astore  6
        //    447: new  #18 // java.lang.StringBuilder
        //    450: dup
        //    451: invokespecial  #41 // java.lang.StringBuilder.<init>:()V
        //    454: iload  4
        //    456: ifeq  464 (offset +8)
        //    459: ldc  #2 // 'invokeI'
        //    461: goto  476 (offset +15)
        //    464: iload  5
        //    466: ifeq  474 (offset +8)
        //    469: ldc  #3 // 'invokeL'
        //    471: goto  476 (offset +5)
        //    474: ldc  #4 // 'invokeN'
        //    476: invokevirtual  #43 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    479: aload_0
        //    480: invokevirtual  #29 // com.kenai.jffi.CallContext.getParameterCount:()I
        //    483: invokevirtual  #42 // java.lang.StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    486: invokevirtual  #44 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    489: astore  7
        //    491: aload_0
        //    492: getfield  #21 // com.kenai.jffi.CallContext.flags:I
        //    495: iconst_2
        //    496: iand
        //    497: ifeq  532 (offset +35)
        //    500: iload  4
        //    502: ifne  510 (offset +8)
        //    505: iload  5
        //    507: ifeq  532 (offset +25)
        //    510: new  #18 // java.lang.StringBuilder
        //    513: dup
        //    514: invokespecial  #41 // java.lang.StringBuilder.<init>:()V
        //    517: aload  7
        //    519: invokevirtual  #43 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    522: ldc  #1 // 'NoErrno'
        //    524: invokevirtual  #43 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    527: invokevirtual  #44 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    530: astore  7
        //    532: iconst_2
        //    533: aload_0
        //    534: invokevirtual  #29 // com.kenai.jffi.CallContext.getParameterCount:()I
        //    537: iadd
        //    538: anewarray  #14 // java.lang.Class
        //    541: astore  8
        //    543: aload  8
        //    545: iconst_0
        //    546: getstatic  #27 // java.lang.Long.TYPE:Ljava/lang/Class;
        //    549: aastore
        //    550: aload  8
        //    552: iconst_1
        //    553: getstatic  #27 // java.lang.Long.TYPE:Ljava/lang/Class;
        //    556: aastore
        //    557: aload  8
        //    559: iconst_2
        //    560: aload  8
        //    562: arraylength
        //    563: aload  6
        //    565: invokestatic  #45 // java.util.Arrays.fill:([Ljava/lang/Object;IILjava/lang/Object;)V
        //    568: ldc  #6 // com.kenai.jffi.Foreign
        //    570: aload  7
        //    572: aload  8
        //    574: invokevirtual  #38 // java.lang.Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    577: astore  9
        //    579: getstatic  #22 // com.kenai.jffi.InvokeDynamicSupport$JSR292.INSTANCE:Lcom/kenai/jffi/InvokeDynamicSupport$JSR292;
        //    582: astore  10
        //    584: aload  10
        //    586: aload  10
        //    588: aload  9
        //    590: invokevirtual  #34 // com.kenai.jffi.InvokeDynamicSupport$JSR292.unreflect:(Ljava/lang/reflect/Method;)Ljava/lang/Object;
        //    593: iconst_0
        //    594: iconst_2
        //    595: anewarray  #17 // java.lang.Object
        //    598: dup
        //    599: iconst_0
        //    600: aload_0
        //    601: invokevirtual  #28 // com.kenai.jffi.CallContext.getAddress:()J
        //    604: invokestatic  #39 // java.lang.Long.valueOf:(J)Ljava/lang/Long;
        //    607: aastore
        //    608: dup
        //    609: iconst_1
        //    610: lload_1
        //    611: invokestatic  #39 // java.lang.Long.valueOf:(J)Ljava/lang/Long;
        //    614: aastore
        //    615: invokevirtual  #33 // com.kenai.jffi.InvokeDynamicSupport$JSR292.insertArguments:(Ljava/lang/Object;I[Ljava/lang/Object;)Ljava/lang/Object;
        //    618: astore  11
        //    620: new  #8 // com.kenai.jffi.InvokeDynamicSupport$Invoker
        //    623: dup
        //    624: aload  9
        //    626: aload  11
        //    628: invokespecial  #32 // com.kenai.jffi.InvokeDynamicSupport$Invoker.<init>:(Ljava/lang/reflect/Method;Ljava/lang/Object;)V
        //    631: areturn
        //    632: astore  9
        //    634: aconst_null
        //    635: areturn
        //       Exception table:
        //         from 568 to 631 target 632 type java.lang.Throwable
    }

}