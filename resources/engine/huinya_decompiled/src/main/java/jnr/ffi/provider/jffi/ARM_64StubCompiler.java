// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ARM_64StubCompiler
package jnr.ffi.provider.jffi;

import com.kenai.jffi.Function;
import jnr.a64asm.Register;
import jnr.ffi.CallingConvention;
import jnr.ffi.Runtime;
import jnr.ffi.provider.ParameterType;
import jnr.ffi.provider.ResultType;
import jnr.ffi.provider.jffi.AbstractA64StubCompiler;

final class ARM_64StubCompiler extends AbstractA64StubCompiler {

    // ---- поля ----
  static final Register[] srcRegisters32;
  static final Register[] srcRegisters64;
  static final Register[] dstRegisters32;
  static final Register[] dstRegisters64;

    static {
        srcRegisters32 = new Register[]{Register.gpw(2), Register.gpw(3), Register.gpw(4), Register.gpw(5), Register.gpw(6), Register.gpw(7)};
        Register[] __obj2 = new Register[6];
        __obj2[0] = Register.gpb(2);
        __obj2[1] = Register.gpb(3);
        __obj2[2] = Register.gpb(4);
        __obj2[3] = Register.gpb(5);
        __obj2[4] = Register.gpb(6);
        __obj2[5] = Register.gpb(7);
        srcRegisters64 = __obj2;
        Register[] __obj3 = new Register[8];
        __obj3[0] = Register.gpw(0);
        __obj3[1] = Register.gpw(1);
        __obj3[2] = Register.gpw(2);
        __obj3[3] = Register.gpw(3);
        __obj3[4] = Register.gpw(4);
        __obj3[5] = Register.gpw(5);
        __obj3[6] = Register.gpw(6);
        __obj3[7] = Register.gpw(7);
        dstRegisters32 = __obj3;
        Register[] __obj4 = new Register[8];
        __obj4[0] = Register.gpb(0);
        __obj4[1] = Register.gpb(1);
        __obj4[2] = Register.gpb(2);
        __obj4[3] = Register.gpb(3);
        __obj4[4] = Register.gpb(4);
        __obj4[5] = Register.gpb(5);
        __obj4[6] = Register.gpb(6);
        __obj4[7] = Register.gpb(7);
        dstRegisters64 = __obj4;
    }

   ARM_64StubCompiler(Runtime arg0) { // было: <init>
        super(arg0);
    }

   boolean canCompile(ResultType arg0, ParameterType[] arg1, CallingConvention arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_3
        //      1: getstatic  #41 // jnr.ffi.CallingConvention.DEFAULT:Ljnr/ffi/CallingConvention;
        //      4: if_acmpeq  9 (offset +5)
        //      7: iconst_0
        //      8: ireturn
        //      9: getstatic  #48 // jnr.ffi.provider.jffi.ARM_64StubCompiler$1.$SwitchMap$jnr$ffi$NativeType:[I
        //     12: aload_1
        //     13: invokevirtual  #82 // jnr.ffi.provider.ResultType.getNativeType:()Ljnr/ffi/NativeType;
        //     16: invokevirtual  #80 // jnr.ffi.NativeType.ordinal:()I
        //     19: iaload
        //     20: tableswitch  default->95, 1->92, 2->92, 3->92, 4->92, 5->92, 6->92, 7->92, 8->92, 9->92, 10->92, 11->92, 12->92, 13->92, 14->92
        //     92: goto  97 (offset +5)
        //     95: iconst_0
        //     96: ireturn
        //     97: iconst_0
        //     98: istore  4
        //    100: iconst_0
        //    101: istore  5
        //    103: aload_2
        //    104: astore  6
        //    106: aload  6
        //    108: arraylength
        //    109: istore  7
        //    111: iconst_0
        //    112: istore  8
        //    114: iload  8
        //    116: iload  7
        //    118: if_icmpge  228 (offset +110)
        //    121: aload  6
        //    123: iload  8
        //    125: aaload
        //    126: astore  9
        //    128: getstatic  #48 // jnr.ffi.provider.jffi.ARM_64StubCompiler$1.$SwitchMap$jnr$ffi$NativeType:[I
        //    131: aload  9
        //    133: invokevirtual  #81 // jnr.ffi.provider.ParameterType.getNativeType:()Ljnr/ffi/NativeType;
        //    136: invokevirtual  #80 // jnr.ffi.NativeType.ordinal:()I
        //    139: iaload
        //    140: tableswitch  default->220, 2->208, 3->208, 4->208, 5->208, 6->208, 7->208, 8->208, 9->208, 10->208, 11->208, 12->214, 13->214, 14->208
        //    208: iinc  5, 1
        //    211: goto  222 (offset +11)
        //    214: iinc  4, 1
        //    217: goto  222 (offset +5)
        //    220: iconst_0
        //    221: ireturn
        //    222: iinc  8, 1
        //    225: goto  114 (offset -111)
        //    228: iload  5
        //    230: bipush  6
        //    232: if_icmpgt  246 (offset +14)
        //    235: iload  4
        //    237: bipush  8
        //    239: if_icmpgt  246 (offset +7)
        //    242: iconst_1
        //    243: goto  247 (offset +4)
        //    246: iconst_0
        //    247: ireturn
    }

  final void compile(Function arg0, String arg1, ResultType arg2, ParameterType[] arg3, Class arg4, Class[] arg5, CallingConvention arg6, boolean arg7) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: new  #11 // jnr.a64asm.Assembler_A64
        //      3: dup
        //      4: getstatic  #40 // jnr.a64asm.CPU_A64.A64:Ljnr/a64asm/CPU_A64;
        //      7: invokespecial  #52 // jnr.a64asm.Assembler_A64.<init>:(Ljnr/a64asm/CPU_A64;)V
        //     10: astore  9
        //     12: aload  4
        //     14: invokestatic  #84 // jnr.ffi.provider.jffi.ARM_64StubCompiler.iCount:([Ljnr/ffi/provider/ParameterType;)I
        //     17: istore  10
        //     19: aload  4
        //     21: invokestatic  #83 // jnr.ffi.provider.jffi.ARM_64StubCompiler.fCount:([Ljnr/ffi/provider/ParameterType;)I
        //     24: istore  11
        //     26: new  #16 // jnr.a64asm.Pre_index
        //     29: dup
        //     30: bipush  31
        //     32: invokestatic  #77 // jnr.a64asm.Register.gpb:(I)Ljnr/a64asm/Register;
        //     35: ldc2_w  #28 // -32L
        //     38: invokestatic  #73 // jnr.a64asm.Immediate.imm:(J)Ljnr/a64asm/Immediate;
        //     41: invokespecial  #76 // jnr.a64asm.Pre_index.<init>:(Ljnr/a64asm/Register;Ljnr/a64asm/Immediate;)V
        //     44: astore  12
        //     46: aload  9
        //     48: bipush  29
        //     50: invokestatic  #77 // jnr.a64asm.Register.gpb:(I)Ljnr/a64asm/Register;
        //     53: bipush  30
        //     55: invokestatic  #77 // jnr.a64asm.Register.gpb:(I)Ljnr/a64asm/Register;
        //     58: aload  12
        //     60: invokevirtual  #65 // jnr.a64asm.Assembler_A64.stp:(Ljnr/a64asm/Register;Ljnr/a64asm/Register;Ljnr/a64asm/Pre_index;)V
        //     63: aload  9
        //     65: bipush  29
        //     67: invokestatic  #77 // jnr.a64asm.Register.gpb:(I)Ljnr/a64asm/Register;
        //     70: bipush  31
        //     72: invokestatic  #77 // jnr.a64asm.Register.gpb:(I)Ljnr/a64asm/Register;
        //     75: invokevirtual  #62 // jnr.a64asm.Assembler_A64.mov:(Ljnr/a64asm/Register;Ljnr/a64asm/Register;)V
        //     78: iload  8
        //     80: ifne  87 (offset +7)
        //     83: iconst_1
        //     84: goto  88 (offset +4)
        //     87: iconst_0
        //     88: iload  10
        //     90: bipush  6
        //     92: if_icmpgt  99 (offset +7)
        //     95: iconst_1
        //     96: goto  100 (offset +4)
        //     99: iconst_0
        //    100: iand
        //    101: iload  11
        //    103: bipush  8
        //    105: if_icmpgt  112 (offset +7)
        //    108: iconst_1
        //    109: goto  113 (offset +4)
        //    112: iconst_0
        //    113: iand
        //    114: istore  13
        //    116: getstatic  #48 // jnr.ffi.provider.jffi.ARM_64StubCompiler$1.$SwitchMap$jnr$ffi$NativeType:[I
        //    119: aload_3
        //    120: invokevirtual  #82 // jnr.ffi.provider.ResultType.getNativeType:()Ljnr/ffi/NativeType;
        //    123: invokevirtual  #80 // jnr.ffi.NativeType.ordinal:()I
        //    126: iaload
        //    127: tableswitch  default->279, 1->276, 2->279, 3->279, 4->279, 5->279, 6->192, 7->192, 8->279, 9->279, 10->213, 11->213, 12->234, 13->255
        //    192: iload  13
        //    194: getstatic  #38 // java.lang.Integer.TYPE:Ljava/lang/Class;
        //    197: aload  5
        //    199: if_acmpne  206 (offset +7)
        //    202: iconst_1
        //    203: goto  207 (offset +4)
        //    206: iconst_0
        //    207: iand
        //    208: istore  13
        //    210: goto  282 (offset +72)
        //    213: iload  13
        //    215: getstatic  #39 // java.lang.Long.TYPE:Ljava/lang/Class;
        //    218: aload  5
        //    220: if_acmpne  227 (offset +7)
        //    223: iconst_1
        //    224: goto  228 (offset +4)
        //    227: iconst_0
        //    228: iand
        //    229: istore  13
        //    231: goto  282 (offset +51)
        //    234: iload  13
        //    236: getstatic  #37 // java.lang.Float.TYPE:Ljava/lang/Class;
        //    239: aload  5
        //    241: if_acmpne  248 (offset +7)
        //    244: iconst_1
        //    245: goto  249 (offset +4)
        //    248: iconst_0
        //    249: iand
        //    250: istore  13
        //    252: goto  282 (offset +30)
        //    255: iload  13
        //    257: getstatic  #36 // java.lang.Double.TYPE:Ljava/lang/Class;
        //    260: aload  5
        //    262: if_acmpne  269 (offset +7)
        //    265: iconst_1
        //    266: goto  270 (offset +4)
        //    269: iconst_0
        //    270: iand
        //    271: istore  13
        //    273: goto  282 (offset +9)
        //    276: goto  282 (offset +6)
        //    279: iconst_0
        //    280: istore  13
        //    282: iconst_0
        //    283: istore  14
        //    285: iload  14
        //    287: iload  10
        //    289: bipush  6
        //    291: invokestatic  #51 // java.lang.Math.min:(II)I
        //    294: if_icmpge  597 (offset +303)
        //    297: getstatic  #48 // jnr.ffi.provider.jffi.ARM_64StubCompiler$1.$SwitchMap$jnr$ffi$NativeType:[I
        //    300: aload  4
        //    302: iload  14
        //    304: aaload
        //    305: invokevirtual  #81 // jnr.ffi.provider.ParameterType.getNativeType:()Ljnr/ffi/NativeType;
        //    308: invokevirtual  #80 // jnr.ffi.NativeType.ordinal:()I
        //    311: iaload
        //    312: tableswitch  default->574, 2->352, 3->389, 4->426, 5->463, 6->500, 7->537
        //    352: aload  9
        //    354: getstatic  #46 // jnr.ffi.provider.jffi.ARM_64StubCompiler.srcRegisters64:[Ljnr/a64asm/Register;
        //    357: iload  14
        //    359: aaload
        //    360: getstatic  #45 // jnr.ffi.provider.jffi.ARM_64StubCompiler.srcRegisters32:[Ljnr/a64asm/Register;
        //    363: iload  14
        //    365: aaload
        //    366: invokevirtual  #67 // jnr.a64asm.Assembler_A64.sxtb:(Ljnr/a64asm/Register;Ljnr/a64asm/Register;)V
        //    369: aload  9
        //    371: getstatic  #43 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters64:[Ljnr/a64asm/Register;
        //    374: iload  14
        //    376: aaload
        //    377: getstatic  #46 // jnr.ffi.provider.jffi.ARM_64StubCompiler.srcRegisters64:[Ljnr/a64asm/Register;
        //    380: iload  14
        //    382: aaload
        //    383: invokevirtual  #62 // jnr.a64asm.Assembler_A64.mov:(Ljnr/a64asm/Register;Ljnr/a64asm/Register;)V
        //    386: goto  591 (offset +205)
        //    389: aload  9
        //    391: getstatic  #46 // jnr.ffi.provider.jffi.ARM_64StubCompiler.srcRegisters64:[Ljnr/a64asm/Register;
        //    394: iload  14
        //    396: aaload
        //    397: getstatic  #45 // jnr.ffi.provider.jffi.ARM_64StubCompiler.srcRegisters32:[Ljnr/a64asm/Register;
        //    400: iload  14
        //    402: aaload
        //    403: invokevirtual  #70 // jnr.a64asm.Assembler_A64.uxtb:(Ljnr/a64asm/Register;Ljnr/a64asm/Register;)V
        //    406: aload  9
        //    408: getstatic  #43 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters64:[Ljnr/a64asm/Register;
        //    411: iload  14
        //    413: aaload
        //    414: getstatic  #46 // jnr.ffi.provider.jffi.ARM_64StubCompiler.srcRegisters64:[Ljnr/a64asm/Register;
        //    417: iload  14
        //    419: aaload
        //    420: invokevirtual  #62 // jnr.a64asm.Assembler_A64.mov:(Ljnr/a64asm/Register;Ljnr/a64asm/Register;)V
        //    423: goto  591 (offset +168)
        //    426: aload  9
        //    428: getstatic  #46 // jnr.ffi.provider.jffi.ARM_64StubCompiler.srcRegisters64:[Ljnr/a64asm/Register;
        //    431: iload  14
        //    433: aaload
        //    434: getstatic  #45 // jnr.ffi.provider.jffi.ARM_64StubCompiler.srcRegisters32:[Ljnr/a64asm/Register;
        //    437: iload  14
        //    439: aaload
        //    440: invokevirtual  #68 // jnr.a64asm.Assembler_A64.sxth:(Ljnr/a64asm/Register;Ljnr/a64asm/Register;)V
        //    443: aload  9
        //    445: getstatic  #43 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters64:[Ljnr/a64asm/Register;
        //    448: iload  14
        //    450: aaload
        //    451: getstatic  #46 // jnr.ffi.provider.jffi.ARM_64StubCompiler.srcRegisters64:[Ljnr/a64asm/Register;
        //    454: iload  14
        //    456: aaload
        //    457: invokevirtual  #62 // jnr.a64asm.Assembler_A64.mov:(Ljnr/a64asm/Register;Ljnr/a64asm/Register;)V
        //    460: goto  591 (offset +131)
        //    463: aload  9
        //    465: getstatic  #46 // jnr.ffi.provider.jffi.ARM_64StubCompiler.srcRegisters64:[Ljnr/a64asm/Register;
        //    468: iload  14
        //    470: aaload
        //    471: getstatic  #45 // jnr.ffi.provider.jffi.ARM_64StubCompiler.srcRegisters32:[Ljnr/a64asm/Register;
        //    474: iload  14
        //    476: aaload
        //    477: invokevirtual  #71 // jnr.a64asm.Assembler_A64.uxth:(Ljnr/a64asm/Register;Ljnr/a64asm/Register;)V
        //    480: aload  9
        //    482: getstatic  #43 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters64:[Ljnr/a64asm/Register;
        //    485: iload  14
        //    487: aaload
        //    488: getstatic  #46 // jnr.ffi.provider.jffi.ARM_64StubCompiler.srcRegisters64:[Ljnr/a64asm/Register;
        //    491: iload  14
        //    493: aaload
        //    494: invokevirtual  #62 // jnr.a64asm.Assembler_A64.mov:(Ljnr/a64asm/Register;Ljnr/a64asm/Register;)V
        //    497: goto  591 (offset +94)
        //    500: aload  9
        //    502: getstatic  #46 // jnr.ffi.provider.jffi.ARM_64StubCompiler.srcRegisters64:[Ljnr/a64asm/Register;
        //    505: iload  14
        //    507: aaload
        //    508: getstatic  #45 // jnr.ffi.provider.jffi.ARM_64StubCompiler.srcRegisters32:[Ljnr/a64asm/Register;
        //    511: iload  14
        //    513: aaload
        //    514: invokevirtual  #69 // jnr.a64asm.Assembler_A64.sxtw:(Ljnr/a64asm/Register;Ljnr/a64asm/Register;)V
        //    517: aload  9
        //    519: getstatic  #43 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters64:[Ljnr/a64asm/Register;
        //    522: iload  14
        //    524: aaload
        //    525: getstatic  #46 // jnr.ffi.provider.jffi.ARM_64StubCompiler.srcRegisters64:[Ljnr/a64asm/Register;
        //    528: iload  14
        //    530: aaload
        //    531: invokevirtual  #62 // jnr.a64asm.Assembler_A64.mov:(Ljnr/a64asm/Register;Ljnr/a64asm/Register;)V
        //    534: goto  591 (offset +57)
        //    537: aload  9
        //    539: getstatic  #46 // jnr.ffi.provider.jffi.ARM_64StubCompiler.srcRegisters64:[Ljnr/a64asm/Register;
        //    542: iload  14
        //    544: aaload
        //    545: getstatic  #45 // jnr.ffi.provider.jffi.ARM_64StubCompiler.srcRegisters32:[Ljnr/a64asm/Register;
        //    548: iload  14
        //    550: aaload
        //    551: invokevirtual  #72 // jnr.a64asm.Assembler_A64.uxtw:(Ljnr/a64asm/Register;Ljnr/a64asm/Register;)V
        //    554: aload  9
        //    556: getstatic  #43 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters64:[Ljnr/a64asm/Register;
        //    559: iload  14
        //    561: aaload
        //    562: getstatic  #46 // jnr.ffi.provider.jffi.ARM_64StubCompiler.srcRegisters64:[Ljnr/a64asm/Register;
        //    565: iload  14
        //    567: aaload
        //    568: invokevirtual  #62 // jnr.a64asm.Assembler_A64.mov:(Ljnr/a64asm/Register;Ljnr/a64asm/Register;)V
        //    571: goto  591 (offset +20)
        //    574: aload  9
        //    576: getstatic  #43 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters64:[Ljnr/a64asm/Register;
        //    579: iload  14
        //    581: aaload
        //    582: getstatic  #46 // jnr.ffi.provider.jffi.ARM_64StubCompiler.srcRegisters64:[Ljnr/a64asm/Register;
        //    585: iload  14
        //    587: aaload
        //    588: invokevirtual  #62 // jnr.a64asm.Assembler_A64.mov:(Ljnr/a64asm/Register;Ljnr/a64asm/Register;)V
        //    591: iinc  14, 1
        //    594: goto  285 (offset -309)
        //    597: iload  10
        //    599: bipush  6
        //    601: if_icmple  614 (offset +13)
        //    604: new  #6 // java.lang.IllegalArgumentException
        //    607: dup
        //    608: ldc  #2 // 'integer argument count > 6'
        //    610: invokespecial  #50 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    613: athrow
        //    614: iload  11
        //    616: bipush  8
        //    618: if_icmple  631 (offset +13)
        //    621: new  #6 // java.lang.IllegalArgumentException
        //    624: dup
        //    625: ldc  #1 // 'float argument count > 8'
        //    627: invokespecial  #50 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    630: athrow
        //    631: new  #14 // jnr.a64asm.Offset
        //    634: dup
        //    635: bipush  29
        //    637: invokestatic  #77 // jnr.a64asm.Register.gpb:(I)Ljnr/a64asm/Register;
        //    640: ldc2_w  #30 // 16L
        //    643: invokestatic  #73 // jnr.a64asm.Immediate.imm:(J)Ljnr/a64asm/Immediate;
        //    646: invokespecial  #74 // jnr.a64asm.Offset.<init>:(Ljnr/a64asm/Register;Ljnr/a64asm/Immediate;)V
        //    649: astore  14
        //    651: aload_1
        //    652: invokevirtual  #49 // com.kenai.jffi.Function.getFunctionAddress:()J
        //    655: lstore  15
        //    657: lload  15
        //    659: ldc2_w  #34 // 65535L
        //    662: land
        //    663: l2i
        //    664: i2s
        //    665: istore  17
        //    667: aload  9
        //    669: bipush  9
        //    671: invokestatic  #77 // jnr.a64asm.Register.gpb:(I)Ljnr/a64asm/Register;
        //    674: iload  17
        //    676: i2l
        //    677: invokestatic  #73 // jnr.a64asm.Immediate.imm:(J)Ljnr/a64asm/Immediate;
        //    680: invokevirtual  #61 // jnr.a64asm.Assembler_A64.mov:(Ljnr/a64asm/Register;Ljnr/a64asm/Immediate;)V
        //    683: iconst_1
        //    684: istore  19
        //    686: iload  19
        //    688: iconst_4
        //    689: if_icmpge  747 (offset +58)
        //    692: new  #18 // jnr.a64asm.Shift
        //    695: dup
        //    696: iconst_1
        //    697: bipush  16
        //    699: iload  19
        //    701: imul
        //    702: invokespecial  #79 // jnr.a64asm.Shift.<init>:(II)V
        //    705: astore  18
        //    707: lload  15
        //    709: bipush  16
        //    711: iload  19
        //    713: imul
        //    714: lshr
        //    715: ldc2_w  #34 // 65535L
        //    718: land
        //    719: l2i
        //    720: i2s
        //    721: istore  17
        //    723: aload  9
        //    725: bipush  9
        //    727: invokestatic  #77 // jnr.a64asm.Register.gpb:(I)Ljnr/a64asm/Register;
        //    730: iload  17
        //    732: i2l
        //    733: invokestatic  #73 // jnr.a64asm.Immediate.imm:(J)Ljnr/a64asm/Immediate;
        //    736: aload  18
        //    738: invokevirtual  #63 // jnr.a64asm.Assembler_A64.movk:(Ljnr/a64asm/Register;Ljnr/a64asm/Immediate;Ljnr/a64asm/Shift;)V
        //    741: iinc  19, 1
        //    744: goto  686 (offset -58)
        //    747: aload  9
        //    749: bipush  9
        //    751: invokestatic  #77 // jnr.a64asm.Register.gpb:(I)Ljnr/a64asm/Register;
        //    754: invokevirtual  #53 // jnr.a64asm.Assembler_A64.blr:(Ljnr/a64asm/Register;)V
        //    757: iload  8
        //    759: ifeq  1072 (offset +313)
        //    762: getstatic  #48 // jnr.ffi.provider.jffi.ARM_64StubCompiler$1.$SwitchMap$jnr$ffi$NativeType:[I
        //    765: aload_3
        //    766: invokevirtual  #82 // jnr.ffi.provider.ResultType.getNativeType:()Ljnr/ffi/NativeType;
        //    769: invokevirtual  #80 // jnr.ffi.NativeType.ordinal:()I
        //    772: iaload
        //    773: lookupswitch  default->795, 1->792
        //    792: goto  807 (offset +15)
        //    795: aload  9
        //    797: getstatic  #43 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters64:[Ljnr/a64asm/Register;
        //    800: iconst_0
        //    801: aaload
        //    802: aload  14
        //    804: invokevirtual  #66 // jnr.a64asm.Assembler_A64.str:(Ljnr/a64asm/Register;Ljnr/a64asm/Offset;)V
        //    807: getstatic  #44 // jnr.ffi.provider.jffi.ARM_64StubCompiler.errnoFunctionAddress:J
        //    810: lstore  15
        //    812: lload  15
        //    814: ldc2_w  #34 // 65535L
        //    817: land
        //    818: l2i
        //    819: i2s
        //    820: istore  17
        //    822: aload  9
        //    824: bipush  9
        //    826: invokestatic  #77 // jnr.a64asm.Register.gpb:(I)Ljnr/a64asm/Register;
        //    829: iload  17
        //    831: i2l
        //    832: invokestatic  #73 // jnr.a64asm.Immediate.imm:(J)Ljnr/a64asm/Immediate;
        //    835: invokevirtual  #61 // jnr.a64asm.Assembler_A64.mov:(Ljnr/a64asm/Register;Ljnr/a64asm/Immediate;)V
        //    838: iconst_1
        //    839: istore  19
        //    841: iload  19
        //    843: iconst_4
        //    844: if_icmpge  902 (offset +58)
        //    847: new  #18 // jnr.a64asm.Shift
        //    850: dup
        //    851: iconst_1
        //    852: bipush  16
        //    854: iload  19
        //    856: imul
        //    857: invokespecial  #79 // jnr.a64asm.Shift.<init>:(II)V
        //    860: astore  18
        //    862: lload  15
        //    864: bipush  16
        //    866: iload  19
        //    868: imul
        //    869: lshr
        //    870: ldc2_w  #34 // 65535L
        //    873: land
        //    874: l2i
        //    875: i2s
        //    876: istore  17
        //    878: aload  9
        //    880: bipush  9
        //    882: invokestatic  #77 // jnr.a64asm.Register.gpb:(I)Ljnr/a64asm/Register;
        //    885: iload  17
        //    887: i2l
        //    888: invokestatic  #73 // jnr.a64asm.Immediate.imm:(J)Ljnr/a64asm/Immediate;
        //    891: aload  18
        //    893: invokevirtual  #63 // jnr.a64asm.Assembler_A64.movk:(Ljnr/a64asm/Register;Ljnr/a64asm/Immediate;Ljnr/a64asm/Shift;)V
        //    896: iinc  19, 1
        //    899: goto  841 (offset -58)
        //    902: aload  9
        //    904: bipush  9
        //    906: invokestatic  #77 // jnr.a64asm.Register.gpb:(I)Ljnr/a64asm/Register;
        //    909: invokevirtual  #53 // jnr.a64asm.Assembler_A64.blr:(Ljnr/a64asm/Register;)V
        //    912: getstatic  #48 // jnr.ffi.provider.jffi.ARM_64StubCompiler$1.$SwitchMap$jnr$ffi$NativeType:[I
        //    915: aload_3
        //    916: invokevirtual  #82 // jnr.ffi.provider.ResultType.getNativeType:()Ljnr/ffi/NativeType;
        //    919: invokevirtual  #80 // jnr.ffi.NativeType.ordinal:()I
        //    922: iaload
        //    923: tableswitch  default->1057, 1->964, 2->967, 3->982, 4->997, 5->1012, 6->1027, 7->1042
        //    964: goto  1225 (offset +261)
        //    967: aload  9
        //    969: getstatic  #43 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters64:[Ljnr/a64asm/Register;
        //    972: iconst_0
        //    973: aaload
        //    974: aload  14
        //    976: invokevirtual  #58 // jnr.a64asm.Assembler_A64.ldrsb:(Ljnr/a64asm/Register;Ljnr/a64asm/Offset;)V
        //    979: goto  1225 (offset +246)
        //    982: aload  9
        //    984: getstatic  #43 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters64:[Ljnr/a64asm/Register;
        //    987: iconst_0
        //    988: aaload
        //    989: aload  14
        //    991: invokevirtual  #56 // jnr.a64asm.Assembler_A64.ldrb:(Ljnr/a64asm/Register;Ljnr/a64asm/Offset;)V
        //    994: goto  1225 (offset +231)
        //    997: aload  9
        //    999: getstatic  #43 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters64:[Ljnr/a64asm/Register;
        //   1002: iconst_0
        //   1003: aaload
        //   1004: aload  14
        //   1006: invokevirtual  #59 // jnr.a64asm.Assembler_A64.ldrsh:(Ljnr/a64asm/Register;Ljnr/a64asm/Offset;)V
        //   1009: goto  1225 (offset +216)
        //   1012: aload  9
        //   1014: getstatic  #43 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters64:[Ljnr/a64asm/Register;
        //   1017: iconst_0
        //   1018: aaload
        //   1019: aload  14
        //   1021: invokevirtual  #57 // jnr.a64asm.Assembler_A64.ldrh:(Ljnr/a64asm/Register;Ljnr/a64asm/Offset;)V
        //   1024: goto  1225 (offset +201)
        //   1027: aload  9
        //   1029: getstatic  #43 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters64:[Ljnr/a64asm/Register;
        //   1032: iconst_0
        //   1033: aaload
        //   1034: aload  14
        //   1036: invokevirtual  #60 // jnr.a64asm.Assembler_A64.ldrsw:(Ljnr/a64asm/Register;Ljnr/a64asm/Offset;)V
        //   1039: goto  1225 (offset +186)
        //   1042: aload  9
        //   1044: getstatic  #43 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters64:[Ljnr/a64asm/Register;
        //   1047: iconst_0
        //   1048: aaload
        //   1049: aload  14
        //   1051: invokevirtual  #55 // jnr.a64asm.Assembler_A64.ldr:(Ljnr/a64asm/Register;Ljnr/a64asm/Offset;)V
        //   1054: goto  1225 (offset +171)
        //   1057: aload  9
        //   1059: getstatic  #43 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters64:[Ljnr/a64asm/Register;
        //   1062: iconst_0
        //   1063: aaload
        //   1064: aload  14
        //   1066: invokevirtual  #55 // jnr.a64asm.Assembler_A64.ldr:(Ljnr/a64asm/Register;Ljnr/a64asm/Offset;)V
        //   1069: goto  1225 (offset +156)
        //   1072: getstatic  #48 // jnr.ffi.provider.jffi.ARM_64StubCompiler$1.$SwitchMap$jnr$ffi$NativeType:[I
        //   1075: aload_3
        //   1076: invokevirtual  #82 // jnr.ffi.provider.ResultType.getNativeType:()Ljnr/ffi/NativeType;
        //   1079: invokevirtual  #80 // jnr.ffi.NativeType.ordinal:()I
        //   1082: iaload
        //   1083: tableswitch  default->1225, 2->1120, 3->1138, 4->1156, 5->1174, 6->1192, 7->1210
        //   1120: aload  9
        //   1122: getstatic  #43 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters64:[Ljnr/a64asm/Register;
        //   1125: iconst_0
        //   1126: aaload
        //   1127: getstatic  #42 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters32:[Ljnr/a64asm/Register;
        //   1130: iconst_0
        //   1131: aaload
        //   1132: invokevirtual  #67 // jnr.a64asm.Assembler_A64.sxtb:(Ljnr/a64asm/Register;Ljnr/a64asm/Register;)V
        //   1135: goto  1225 (offset +90)
        //   1138: aload  9
        //   1140: getstatic  #43 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters64:[Ljnr/a64asm/Register;
        //   1143: iconst_0
        //   1144: aaload
        //   1145: getstatic  #42 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters32:[Ljnr/a64asm/Register;
        //   1148: iconst_0
        //   1149: aaload
        //   1150: invokevirtual  #70 // jnr.a64asm.Assembler_A64.uxtb:(Ljnr/a64asm/Register;Ljnr/a64asm/Register;)V
        //   1153: goto  1225 (offset +72)
        //   1156: aload  9
        //   1158: getstatic  #43 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters64:[Ljnr/a64asm/Register;
        //   1161: iconst_0
        //   1162: aaload
        //   1163: getstatic  #42 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters32:[Ljnr/a64asm/Register;
        //   1166: iconst_0
        //   1167: aaload
        //   1168: invokevirtual  #68 // jnr.a64asm.Assembler_A64.sxth:(Ljnr/a64asm/Register;Ljnr/a64asm/Register;)V
        //   1171: goto  1225 (offset +54)
        //   1174: aload  9
        //   1176: getstatic  #43 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters64:[Ljnr/a64asm/Register;
        //   1179: iconst_0
        //   1180: aaload
        //   1181: getstatic  #42 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters32:[Ljnr/a64asm/Register;
        //   1184: iconst_0
        //   1185: aaload
        //   1186: invokevirtual  #71 // jnr.a64asm.Assembler_A64.uxth:(Ljnr/a64asm/Register;Ljnr/a64asm/Register;)V
        //   1189: goto  1225 (offset +36)
        //   1192: aload  9
        //   1194: getstatic  #43 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters64:[Ljnr/a64asm/Register;
        //   1197: iconst_0
        //   1198: aaload
        //   1199: getstatic  #42 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters32:[Ljnr/a64asm/Register;
        //   1202: iconst_0
        //   1203: aaload
        //   1204: invokevirtual  #69 // jnr.a64asm.Assembler_A64.sxtw:(Ljnr/a64asm/Register;Ljnr/a64asm/Register;)V
        //   1207: goto  1225 (offset +18)
        //   1210: aload  9
        //   1212: getstatic  #43 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters64:[Ljnr/a64asm/Register;
        //   1215: iconst_0
        //   1216: aaload
        //   1217: getstatic  #42 // jnr.ffi.provider.jffi.ARM_64StubCompiler.dstRegisters32:[Ljnr/a64asm/Register;
        //   1220: iconst_0
        //   1221: aaload
        //   1222: invokevirtual  #72 // jnr.a64asm.Assembler_A64.uxtw:(Ljnr/a64asm/Register;Ljnr/a64asm/Register;)V
        //   1225: new  #15 // jnr.a64asm.Post_index
        //   1228: dup
        //   1229: bipush  31
        //   1231: invokestatic  #77 // jnr.a64asm.Register.gpb:(I)Ljnr/a64asm/Register;
        //   1234: ldc2_w  #32 // 32L
        //   1237: invokestatic  #73 // jnr.a64asm.Immediate.imm:(J)Ljnr/a64asm/Immediate;
        //   1240: invokespecial  #75 // jnr.a64asm.Post_index.<init>:(Ljnr/a64asm/Register;Ljnr/a64asm/Immediate;)V
        //   1243: astore  20
        //   1245: aload  9
        //   1247: bipush  29
        //   1249: invokestatic  #77 // jnr.a64asm.Register.gpb:(I)Ljnr/a64asm/Register;
        //   1252: bipush  30
        //   1254: invokestatic  #77 // jnr.a64asm.Register.gpb:(I)Ljnr/a64asm/Register;
        //   1257: aload  20
        //   1259: invokevirtual  #54 // jnr.a64asm.Assembler_A64.ldp:(Ljnr/a64asm/Register;Ljnr/a64asm/Register;Ljnr/a64asm/Post_index;)V
        //   1262: aload  9
        //   1264: aconst_null
        //   1265: checkcast  #17 // jnr.a64asm.Register
        //   1268: invokevirtual  #64 // jnr.a64asm.Assembler_A64.ret:(Ljnr/a64asm/Register;)V
        //   1271: aload_0
        //   1272: getfield  #47 // jnr.ffi.provider.jffi.ARM_64StubCompiler.stubs_A64:Ljava/util/List;
        //   1275: new  #26 // jnr.ffi.provider.jffi.AbstractA64StubCompiler$Stub
        //   1278: dup
        //   1279: aload_2
        //   1280: aload  5
        //   1282: aload  6
        //   1284: invokestatic  #87 // jnr.ffi.provider.jffi.CodegenUtils.sig:(Ljava/lang/Class;[Ljava/lang/Class;)Ljava/lang/String;
        //   1287: aload  9
        //   1289: invokespecial  #86 // jnr.ffi.provider.jffi.AbstractA64StubCompiler$Stub.<init>:(Ljava/lang/String;Ljava/lang/String;Ljnr/a64asm/Assembler_A64;)V
        //   1292: invokeinterface  #88 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //   1297: pop
        //   1298: return
    }

  static int fCount(ParameterType[] arg0) {
        int var1 = 0;
        ParameterType[] var2 = arg0;
        int var3 = var2.length;
        int var4 = 0;
        while (var4 < var3) {
            Object var5 = var2[var4];
            switch (var5.getNativeType()) {
                case FLOAT:
                case DOUBLE:
                    ++var1;
                default:
            }
            ++var4;
            continue;
        }
        return var1;
    }

  static int iCount(ParameterType[] arg0) {
        int var1 = 0;
        ParameterType[] var2 = arg0;
        int var3 = var2.length;
        int var4 = 0;
        while (var4 < var3) {
            Object var5 = var2[var4];
            switch (var5.getNativeType()) {
                case SCHAR:
                case UCHAR:
                case SSHORT:
                case USHORT:
                case SINT:
                case UINT:
                case SLONG:
                case ULONG:
                case SLONGLONG:
                case ULONGLONG:
                case ADDRESS:
                    ++var1;
                default:
                case 12:
                case 13:
            }
            ++var4;
            continue;
        }
        return var1;
    }

}