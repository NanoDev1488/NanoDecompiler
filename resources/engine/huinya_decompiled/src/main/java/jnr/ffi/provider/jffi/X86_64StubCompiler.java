// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.X86_64StubCompiler
package jnr.ffi.provider.jffi;

import com.kenai.jffi.Function;
import jnr.ffi.CallingConvention;
import jnr.ffi.Runtime;
import jnr.ffi.provider.ParameterType;
import jnr.ffi.provider.ResultType;
import jnr.ffi.provider.jffi.AbstractX86StubCompiler;
import jnr.x86asm.Asm;
import jnr.x86asm.Register;

final class X86_64StubCompiler extends AbstractX86StubCompiler {

    // ---- поля ----
  static final Register[] srcRegisters8;
  static final Register[] srcRegisters16;
  static final Register[] srcRegisters32;
  static final Register[] srcRegisters64;
  static final Register[] dstRegisters32;
  static final Register[] dstRegisters64;

    static {
        srcRegisters8 = new Register[]{Asm.dl, Asm.cl, Asm.r8b, Asm.r9b};
        Register[] __obj2 = new Register[4];
        __obj2[0] = Asm.dx;
        __obj2[1] = Asm.cx;
        __obj2[2] = Asm.r8w;
        __obj2[3] = Asm.r9w;
        srcRegisters16 = __obj2;
        Register[] __obj3 = new Register[4];
        __obj3[0] = Asm.edx;
        __obj3[1] = Asm.ecx;
        __obj3[2] = Register.gpr(40);
        __obj3[3] = Register.gpr(41);
        srcRegisters32 = __obj3;
        Register[] __obj4 = new Register[4];
        __obj4[0] = Asm.rdx;
        __obj4[1] = Asm.rcx;
        __obj4[2] = Asm.r8;
        __obj4[3] = Asm.r9;
        srcRegisters64 = __obj4;
        Register[] __obj5 = new Register[6];
        __obj5[0] = Asm.edi;
        __obj5[1] = Asm.esi;
        __obj5[2] = Asm.edx;
        __obj5[3] = Asm.ecx;
        __obj5[4] = Register.gpr(40);
        __obj5[5] = Register.gpr(41);
        dstRegisters32 = __obj5;
        Register[] __obj6 = new Register[6];
        __obj6[0] = Asm.rdi;
        __obj6[1] = Asm.rsi;
        __obj6[2] = Asm.rdx;
        __obj6[3] = Asm.rcx;
        __obj6[4] = Asm.r8;
        __obj6[5] = Asm.r9;
        dstRegisters64 = __obj6;
    }

   X86_64StubCompiler(Runtime arg0) { // было: <init>
        super(arg0);
    }

   boolean canCompile(ResultType arg0, ParameterType[] arg1, CallingConvention arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_3
        //      1: getstatic  #27 // jnr.ffi.CallingConvention.DEFAULT:Ljnr/ffi/CallingConvention;
        //      4: if_acmpeq  9 (offset +5)
        //      7: iconst_0
        //      8: ireturn
        //      9: getstatic  #36 // jnr.ffi.provider.jffi.X86_64StubCompiler$1.$SwitchMap$jnr$ffi$NativeType:[I
        //     12: aload_1
        //     13: invokevirtual  #67 // jnr.ffi.provider.ResultType.getNativeType:()Ljnr/ffi/NativeType;
        //     16: invokevirtual  #65 // jnr.ffi.NativeType.ordinal:()I
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
        //    128: getstatic  #36 // jnr.ffi.provider.jffi.X86_64StubCompiler$1.$SwitchMap$jnr$ffi$NativeType:[I
        //    131: aload  9
        //    133: invokevirtual  #66 // jnr.ffi.provider.ParameterType.getNativeType:()Ljnr/ffi/NativeType;
        //    136: invokevirtual  #65 // jnr.ffi.NativeType.ordinal:()I
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
        //      0: new  #21 // jnr.x86asm.Assembler
        //      3: dup
        //      4: getstatic  #37 // jnr.x86asm.Asm.X86_64:Ljnr/x86asm/CPU;
        //      7: invokespecial  #78 // jnr.x86asm.Assembler.<init>:(Ljnr/x86asm/CPU;)V
        //     10: astore  9
        //     12: aload  4
        //     14: invokestatic  #72 // jnr.ffi.provider.jffi.X86_64StubCompiler.iCount:([Ljnr/ffi/provider/ParameterType;)I
        //     17: istore  10
        //     19: aload  4
        //     21: invokestatic  #71 // jnr.ffi.provider.jffi.X86_64StubCompiler.fCount:([Ljnr/ffi/provider/ParameterType;)I
        //     24: istore  11
        //     26: iload  8
        //     28: ifne  35 (offset +7)
        //     31: iconst_1
        //     32: goto  36 (offset +4)
        //     35: iconst_0
        //     36: iload  10
        //     38: bipush  6
        //     40: if_icmpgt  47 (offset +7)
        //     43: iconst_1
        //     44: goto  48 (offset +4)
        //     47: iconst_0
        //     48: iand
        //     49: iload  11
        //     51: bipush  8
        //     53: if_icmpgt  60 (offset +7)
        //     56: iconst_1
        //     57: goto  61 (offset +4)
        //     60: iconst_0
        //     61: iand
        //     62: istore  12
        //     64: getstatic  #36 // jnr.ffi.provider.jffi.X86_64StubCompiler$1.$SwitchMap$jnr$ffi$NativeType:[I
        //     67: aload_3
        //     68: invokevirtual  #67 // jnr.ffi.provider.ResultType.getNativeType:()Ljnr/ffi/NativeType;
        //     71: invokevirtual  #65 // jnr.ffi.NativeType.ordinal:()I
        //     74: iaload
        //     75: tableswitch  default->227, 1->224, 2->227, 3->227, 4->227, 5->227, 6->140, 7->140, 8->227, 9->227, 10->161, 11->161, 12->182, 13->203
        //    140: iload  12
        //    142: getstatic  #25 // java.lang.Integer.TYPE:Ljava/lang/Class;
        //    145: aload  5
        //    147: if_acmpne  154 (offset +7)
        //    150: iconst_1
        //    151: goto  155 (offset +4)
        //    154: iconst_0
        //    155: iand
        //    156: istore  12
        //    158: goto  230 (offset +72)
        //    161: iload  12
        //    163: getstatic  #26 // java.lang.Long.TYPE:Ljava/lang/Class;
        //    166: aload  5
        //    168: if_acmpne  175 (offset +7)
        //    171: iconst_1
        //    172: goto  176 (offset +4)
        //    175: iconst_0
        //    176: iand
        //    177: istore  12
        //    179: goto  230 (offset +51)
        //    182: iload  12
        //    184: getstatic  #24 // java.lang.Float.TYPE:Ljava/lang/Class;
        //    187: aload  5
        //    189: if_acmpne  196 (offset +7)
        //    192: iconst_1
        //    193: goto  197 (offset +4)
        //    196: iconst_0
        //    197: iand
        //    198: istore  12
        //    200: goto  230 (offset +30)
        //    203: iload  12
        //    205: getstatic  #23 // java.lang.Double.TYPE:Ljava/lang/Class;
        //    208: aload  5
        //    210: if_acmpne  217 (offset +7)
        //    213: iconst_1
        //    214: goto  218 (offset +4)
        //    217: iconst_0
        //    218: iand
        //    219: istore  12
        //    221: goto  230 (offset +9)
        //    224: goto  230 (offset +6)
        //    227: iconst_0
        //    228: istore  12
        //    230: iconst_0
        //    231: istore  13
        //    233: iload  13
        //    235: iload  10
        //    237: iconst_4
        //    238: invokestatic  #64 // java.lang.Math.min:(II)I
        //    241: if_icmpge  439 (offset +198)
        //    244: getstatic  #36 // jnr.ffi.provider.jffi.X86_64StubCompiler$1.$SwitchMap$jnr$ffi$NativeType:[I
        //    247: aload  4
        //    249: iload  13
        //    251: aaload
        //    252: invokevirtual  #66 // jnr.ffi.provider.ParameterType.getNativeType:()Ljnr/ffi/NativeType;
        //    255: invokevirtual  #65 // jnr.ffi.NativeType.ordinal:()I
        //    258: iaload
        //    259: tableswitch  default->416, 2->296, 3->316, 4->336, 5->356, 6->376, 7->396
        //    296: aload  9
        //    298: getstatic  #29 // jnr.ffi.provider.jffi.X86_64StubCompiler.dstRegisters64:[Ljnr/x86asm/Register;
        //    301: iload  13
        //    303: aaload
        //    304: getstatic  #34 // jnr.ffi.provider.jffi.X86_64StubCompiler.srcRegisters8:[Ljnr/x86asm/Register;
        //    307: iload  13
        //    309: aaload
        //    310: invokevirtual  #91 // jnr.x86asm.Assembler.movsx:(Ljnr/x86asm/Register;Ljnr/x86asm/Register;)V
        //    313: goto  433 (offset +120)
        //    316: aload  9
        //    318: getstatic  #29 // jnr.ffi.provider.jffi.X86_64StubCompiler.dstRegisters64:[Ljnr/x86asm/Register;
        //    321: iload  13
        //    323: aaload
        //    324: getstatic  #34 // jnr.ffi.provider.jffi.X86_64StubCompiler.srcRegisters8:[Ljnr/x86asm/Register;
        //    327: iload  13
        //    329: aaload
        //    330: invokevirtual  #95 // jnr.x86asm.Assembler.movzx:(Ljnr/x86asm/Register;Ljnr/x86asm/Register;)V
        //    333: goto  433 (offset +100)
        //    336: aload  9
        //    338: getstatic  #29 // jnr.ffi.provider.jffi.X86_64StubCompiler.dstRegisters64:[Ljnr/x86asm/Register;
        //    341: iload  13
        //    343: aaload
        //    344: getstatic  #31 // jnr.ffi.provider.jffi.X86_64StubCompiler.srcRegisters16:[Ljnr/x86asm/Register;
        //    347: iload  13
        //    349: aaload
        //    350: invokevirtual  #91 // jnr.x86asm.Assembler.movsx:(Ljnr/x86asm/Register;Ljnr/x86asm/Register;)V
        //    353: goto  433 (offset +80)
        //    356: aload  9
        //    358: getstatic  #29 // jnr.ffi.provider.jffi.X86_64StubCompiler.dstRegisters64:[Ljnr/x86asm/Register;
        //    361: iload  13
        //    363: aaload
        //    364: getstatic  #31 // jnr.ffi.provider.jffi.X86_64StubCompiler.srcRegisters16:[Ljnr/x86asm/Register;
        //    367: iload  13
        //    369: aaload
        //    370: invokevirtual  #95 // jnr.x86asm.Assembler.movzx:(Ljnr/x86asm/Register;Ljnr/x86asm/Register;)V
        //    373: goto  433 (offset +60)
        //    376: aload  9
        //    378: getstatic  #29 // jnr.ffi.provider.jffi.X86_64StubCompiler.dstRegisters64:[Ljnr/x86asm/Register;
        //    381: iload  13
        //    383: aaload
        //    384: getstatic  #32 // jnr.ffi.provider.jffi.X86_64StubCompiler.srcRegisters32:[Ljnr/x86asm/Register;
        //    387: iload  13
        //    389: aaload
        //    390: invokevirtual  #93 // jnr.x86asm.Assembler.movsxd:(Ljnr/x86asm/Register;Ljnr/x86asm/Register;)V
        //    393: goto  433 (offset +40)
        //    396: aload  9
        //    398: getstatic  #28 // jnr.ffi.provider.jffi.X86_64StubCompiler.dstRegisters32:[Ljnr/x86asm/Register;
        //    401: iload  13
        //    403: aaload
        //    404: getstatic  #32 // jnr.ffi.provider.jffi.X86_64StubCompiler.srcRegisters32:[Ljnr/x86asm/Register;
        //    407: iload  13
        //    409: aaload
        //    410: invokevirtual  #85 // jnr.x86asm.Assembler.mov:(Ljnr/x86asm/Register;Ljnr/x86asm/Register;)V
        //    413: goto  433 (offset +20)
        //    416: aload  9
        //    418: getstatic  #29 // jnr.ffi.provider.jffi.X86_64StubCompiler.dstRegisters64:[Ljnr/x86asm/Register;
        //    421: iload  13
        //    423: aaload
        //    424: getstatic  #33 // jnr.ffi.provider.jffi.X86_64StubCompiler.srcRegisters64:[Ljnr/x86asm/Register;
        //    427: iload  13
        //    429: aaload
        //    430: invokevirtual  #85 // jnr.x86asm.Assembler.mov:(Ljnr/x86asm/Register;Ljnr/x86asm/Register;)V
        //    433: iinc  13, 1
        //    436: goto  233 (offset -203)
        //    439: iload  10
        //    441: bipush  6
        //    443: if_icmple  456 (offset +13)
        //    446: new  #6 // java.lang.IllegalArgumentException
        //    449: dup
        //    450: ldc  #2 // 'integer argument count > 6'
        //    452: invokespecial  #63 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    455: athrow
        //    456: iconst_4
        //    457: istore  13
        //    459: iload  13
        //    461: iload  10
        //    463: if_icmpge  696 (offset +233)
        //    466: bipush  8
        //    468: iconst_4
        //    469: iload  13
        //    471: isub
        //    472: bipush  8
        //    474: imul
        //    475: iadd
        //    476: istore  14
        //    478: getstatic  #36 // jnr.ffi.provider.jffi.X86_64StubCompiler$1.$SwitchMap$jnr$ffi$NativeType:[I
        //    481: aload  4
        //    483: iload  13
        //    485: aaload
        //    486: invokevirtual  #66 // jnr.ffi.provider.ParameterType.getNativeType:()Ljnr/ffi/NativeType;
        //    489: invokevirtual  #65 // jnr.ffi.NativeType.ordinal:()I
        //    492: iaload
        //    493: tableswitch  default->670, 2->532, 3->555, 4->578, 5->601, 6->624, 7->647
        //    532: aload  9
        //    534: getstatic  #29 // jnr.ffi.provider.jffi.X86_64StubCompiler.dstRegisters64:[Ljnr/x86asm/Register;
        //    537: iload  13
        //    539: aaload
        //    540: getstatic  #60 // jnr.x86asm.Asm.rsp:Ljnr/x86asm/Register;
        //    543: iload  14
        //    545: i2l
        //    546: invokestatic  #73 // jnr.x86asm.Asm.byte_ptr:(Ljnr/x86asm/Register;J)Ljnr/x86asm/Mem;
        //    549: invokevirtual  #90 // jnr.x86asm.Assembler.movsx:(Ljnr/x86asm/Register;Ljnr/x86asm/Mem;)V
        //    552: goto  690 (offset +138)
        //    555: aload  9
        //    557: getstatic  #29 // jnr.ffi.provider.jffi.X86_64StubCompiler.dstRegisters64:[Ljnr/x86asm/Register;
        //    560: iload  13
        //    562: aaload
        //    563: getstatic  #60 // jnr.x86asm.Asm.rsp:Ljnr/x86asm/Register;
        //    566: iload  14
        //    568: i2l
        //    569: invokestatic  #73 // jnr.x86asm.Asm.byte_ptr:(Ljnr/x86asm/Register;J)Ljnr/x86asm/Mem;
        //    572: invokevirtual  #94 // jnr.x86asm.Assembler.movzx:(Ljnr/x86asm/Register;Ljnr/x86asm/Mem;)V
        //    575: goto  690 (offset +115)
        //    578: aload  9
        //    580: getstatic  #29 // jnr.ffi.provider.jffi.X86_64StubCompiler.dstRegisters64:[Ljnr/x86asm/Register;
        //    583: iload  13
        //    585: aaload
        //    586: getstatic  #60 // jnr.x86asm.Asm.rsp:Ljnr/x86asm/Register;
        //    589: iload  14
        //    591: i2l
        //    592: invokestatic  #77 // jnr.x86asm.Asm.word_ptr:(Ljnr/x86asm/Register;J)Ljnr/x86asm/Mem;
        //    595: invokevirtual  #90 // jnr.x86asm.Assembler.movsx:(Ljnr/x86asm/Register;Ljnr/x86asm/Mem;)V
        //    598: goto  690 (offset +92)
        //    601: aload  9
        //    603: getstatic  #29 // jnr.ffi.provider.jffi.X86_64StubCompiler.dstRegisters64:[Ljnr/x86asm/Register;
        //    606: iload  13
        //    608: aaload
        //    609: getstatic  #60 // jnr.x86asm.Asm.rsp:Ljnr/x86asm/Register;
        //    612: iload  14
        //    614: i2l
        //    615: invokestatic  #77 // jnr.x86asm.Asm.word_ptr:(Ljnr/x86asm/Register;J)Ljnr/x86asm/Mem;
        //    618: invokevirtual  #94 // jnr.x86asm.Assembler.movzx:(Ljnr/x86asm/Register;Ljnr/x86asm/Mem;)V
        //    621: goto  690 (offset +69)
        //    624: aload  9
        //    626: getstatic  #29 // jnr.ffi.provider.jffi.X86_64StubCompiler.dstRegisters64:[Ljnr/x86asm/Register;
        //    629: iload  13
        //    631: aaload
        //    632: getstatic  #60 // jnr.x86asm.Asm.rsp:Ljnr/x86asm/Register;
        //    635: iload  14
        //    637: i2l
        //    638: invokestatic  #74 // jnr.x86asm.Asm.dword_ptr:(Ljnr/x86asm/Register;J)Ljnr/x86asm/Mem;
        //    641: invokevirtual  #92 // jnr.x86asm.Assembler.movsxd:(Ljnr/x86asm/Register;Ljnr/x86asm/Mem;)V
        //    644: goto  690 (offset +46)
        //    647: aload  9
        //    649: getstatic  #28 // jnr.ffi.provider.jffi.X86_64StubCompiler.dstRegisters32:[Ljnr/x86asm/Register;
        //    652: iload  13
        //    654: aaload
        //    655: getstatic  #60 // jnr.x86asm.Asm.rsp:Ljnr/x86asm/Register;
        //    658: iload  14
        //    660: i2l
        //    661: invokestatic  #74 // jnr.x86asm.Asm.dword_ptr:(Ljnr/x86asm/Register;J)Ljnr/x86asm/Mem;
        //    664: invokevirtual  #84 // jnr.x86asm.Assembler.mov:(Ljnr/x86asm/Register;Ljnr/x86asm/Mem;)V
        //    667: goto  690 (offset +23)
        //    670: aload  9
        //    672: getstatic  #29 // jnr.ffi.provider.jffi.X86_64StubCompiler.dstRegisters64:[Ljnr/x86asm/Register;
        //    675: iload  13
        //    677: aaload
        //    678: getstatic  #60 // jnr.x86asm.Asm.rsp:Ljnr/x86asm/Register;
        //    681: iload  14
        //    683: i2l
        //    684: invokestatic  #76 // jnr.x86asm.Asm.qword_ptr:(Ljnr/x86asm/Register;J)Ljnr/x86asm/Mem;
        //    687: invokevirtual  #84 // jnr.x86asm.Assembler.mov:(Ljnr/x86asm/Register;Ljnr/x86asm/Mem;)V
        //    690: iinc  13, 1
        //    693: goto  459 (offset -234)
        //    696: iload  11
        //    698: bipush  8
        //    700: if_icmple  713 (offset +13)
        //    703: new  #6 // java.lang.IllegalArgumentException
        //    706: dup
        //    707: ldc  #1 // 'float argument count > 8'
        //    709: invokespecial  #63 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    712: athrow
        //    713: iload  12
        //    715: ifeq  758 (offset +43)
        //    718: aload  9
        //    720: aload_1
        //    721: invokevirtual  #62 // com.kenai.jffi.Function.getFunctionAddress:()J
        //    724: invokestatic  #75 // jnr.x86asm.Asm.imm:(J)Ljnr/x86asm/Immediate;
        //    727: invokevirtual  #81 // jnr.x86asm.Assembler.jmp:(Ljnr/x86asm/Immediate;)V
        //    730: aload_0
        //    731: getfield  #35 // jnr.ffi.provider.jffi.X86_64StubCompiler.stubs:Ljava/util/List;
        //    734: new  #16 // jnr.ffi.provider.jffi.AbstractX86StubCompiler$Stub
        //    737: dup
        //    738: aload_2
        //    739: aload  5
        //    741: aload  6
        //    743: invokestatic  #70 // jnr.ffi.provider.jffi.CodegenUtils.sig:(Ljava/lang/Class;[Ljava/lang/Class;)Ljava/lang/String;
        //    746: aload  9
        //    748: invokespecial  #69 // jnr.ffi.provider.jffi.AbstractX86StubCompiler$Stub.<init>:(Ljava/lang/String;Ljava/lang/String;Ljnr/x86asm/Assembler;)V
        //    751: invokeinterface  #99 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    756: pop
        //    757: return
        //    758: aload  5
        //    760: getstatic  #24 // java.lang.Float.TYPE:Ljava/lang/Class;
        //    763: if_acmpeq  774 (offset +11)
        //    766: aload  5
        //    768: getstatic  #23 // java.lang.Double.TYPE:Ljava/lang/Class;
        //    771: if_acmpne  779 (offset +8)
        //    774: bipush  24
        //    776: goto  781 (offset +5)
        //    779: bipush  8
        //    781: istore  13
        //    783: aload  9
        //    785: getstatic  #60 // jnr.x86asm.Asm.rsp:Ljnr/x86asm/Register;
        //    788: iload  13
        //    790: i2l
        //    791: invokestatic  #75 // jnr.x86asm.Asm.imm:(J)Ljnr/x86asm/Immediate;
        //    794: invokevirtual  #97 // jnr.x86asm.Assembler.sub:(Ljnr/x86asm/Register;Ljnr/x86asm/Immediate;)V
        //    797: aload  9
        //    799: getstatic  #55 // jnr.x86asm.Asm.rax:Ljnr/x86asm/Register;
        //    802: lconst_0
        //    803: invokestatic  #75 // jnr.x86asm.Asm.imm:(J)Ljnr/x86asm/Immediate;
        //    806: invokevirtual  #83 // jnr.x86asm.Assembler.mov:(Ljnr/x86asm/Register;Ljnr/x86asm/Immediate;)V
        //    809: aload  9
        //    811: aload_1
        //    812: invokevirtual  #62 // com.kenai.jffi.Function.getFunctionAddress:()J
        //    815: invokestatic  #75 // jnr.x86asm.Asm.imm:(J)Ljnr/x86asm/Immediate;
        //    818: invokevirtual  #80 // jnr.x86asm.Assembler.call:(Ljnr/x86asm/Immediate;)V
        //    821: iload  8
        //    823: ifeq  1181 (offset +358)
        //    826: getstatic  #36 // jnr.ffi.provider.jffi.X86_64StubCompiler$1.$SwitchMap$jnr$ffi$NativeType:[I
        //    829: aload_3
        //    830: invokevirtual  #67 // jnr.ffi.provider.ResultType.getNativeType:()Ljnr/ffi/NativeType;
        //    833: invokevirtual  #65 // jnr.ffi.NativeType.ordinal:()I
        //    836: iaload
        //    837: lookupswitch  default->911, 1->872, 12->875, 13->893
        //    872: goto  926 (offset +54)
        //    875: aload  9
        //    877: getstatic  #60 // jnr.x86asm.Asm.rsp:Ljnr/x86asm/Register;
        //    880: lconst_0
        //    881: invokestatic  #74 // jnr.x86asm.Asm.dword_ptr:(Ljnr/x86asm/Register;J)Ljnr/x86asm/Mem;
        //    884: getstatic  #61 // jnr.x86asm.Asm.xmm0:Ljnr/x86asm/XMMRegister;
        //    887: invokevirtual  #88 // jnr.x86asm.Assembler.movss:(Ljnr/x86asm/Mem;Ljnr/x86asm/XMMRegister;)V
        //    890: goto  926 (offset +36)
        //    893: aload  9
        //    895: getstatic  #60 // jnr.x86asm.Asm.rsp:Ljnr/x86asm/Register;
        //    898: lconst_0
        //    899: invokestatic  #76 // jnr.x86asm.Asm.qword_ptr:(Ljnr/x86asm/Register;J)Ljnr/x86asm/Mem;
        //    902: getstatic  #61 // jnr.x86asm.Asm.xmm0:Ljnr/x86asm/XMMRegister;
        //    905: invokevirtual  #86 // jnr.x86asm.Assembler.movsd:(Ljnr/x86asm/Mem;Ljnr/x86asm/XMMRegister;)V
        //    908: goto  926 (offset +18)
        //    911: aload  9
        //    913: getstatic  #60 // jnr.x86asm.Asm.rsp:Ljnr/x86asm/Register;
        //    916: lconst_0
        //    917: invokestatic  #76 // jnr.x86asm.Asm.qword_ptr:(Ljnr/x86asm/Register;J)Ljnr/x86asm/Mem;
        //    920: getstatic  #55 // jnr.x86asm.Asm.rax:Ljnr/x86asm/Register;
        //    923: invokevirtual  #82 // jnr.x86asm.Assembler.mov:(Ljnr/x86asm/Mem;Ljnr/x86asm/Register;)V
        //    926: aload  9
        //    928: getstatic  #30 // jnr.ffi.provider.jffi.X86_64StubCompiler.errnoFunctionAddress:J
        //    931: invokestatic  #75 // jnr.x86asm.Asm.imm:(J)Ljnr/x86asm/Immediate;
        //    934: invokevirtual  #80 // jnr.x86asm.Assembler.call:(Ljnr/x86asm/Immediate;)V
        //    937: getstatic  #36 // jnr.ffi.provider.jffi.X86_64StubCompiler$1.$SwitchMap$jnr$ffi$NativeType:[I
        //    940: aload_3
        //    941: invokevirtual  #67 // jnr.ffi.provider.ResultType.getNativeType:()Ljnr/ffi/NativeType;
        //    944: invokevirtual  #65 // jnr.ffi.NativeType.ordinal:()I
        //    947: iaload
        //    948: tableswitch  default->1163, 1->1016, 2->1019, 3->1037, 4->1055, 5->1073, 6->1091, 7->1109, 8->1163, 9->1163, 10->1163, 11->1163, 12->1127, 13->1145
        //   1016: goto  1329 (offset +313)
        //   1019: aload  9
        //   1021: getstatic  #55 // jnr.x86asm.Asm.rax:Ljnr/x86asm/Register;
        //   1024: getstatic  #60 // jnr.x86asm.Asm.rsp:Ljnr/x86asm/Register;
        //   1027: lconst_0
        //   1028: invokestatic  #73 // jnr.x86asm.Asm.byte_ptr:(Ljnr/x86asm/Register;J)Ljnr/x86asm/Mem;
        //   1031: invokevirtual  #90 // jnr.x86asm.Assembler.movsx:(Ljnr/x86asm/Register;Ljnr/x86asm/Mem;)V
        //   1034: goto  1329 (offset +295)
        //   1037: aload  9
        //   1039: getstatic  #55 // jnr.x86asm.Asm.rax:Ljnr/x86asm/Register;
        //   1042: getstatic  #60 // jnr.x86asm.Asm.rsp:Ljnr/x86asm/Register;
        //   1045: lconst_0
        //   1046: invokestatic  #73 // jnr.x86asm.Asm.byte_ptr:(Ljnr/x86asm/Register;J)Ljnr/x86asm/Mem;
        //   1049: invokevirtual  #94 // jnr.x86asm.Assembler.movzx:(Ljnr/x86asm/Register;Ljnr/x86asm/Mem;)V
        //   1052: goto  1329 (offset +277)
        //   1055: aload  9
        //   1057: getstatic  #55 // jnr.x86asm.Asm.rax:Ljnr/x86asm/Register;
        //   1060: getstatic  #60 // jnr.x86asm.Asm.rsp:Ljnr/x86asm/Register;
        //   1063: lconst_0
        //   1064: invokestatic  #77 // jnr.x86asm.Asm.word_ptr:(Ljnr/x86asm/Register;J)Ljnr/x86asm/Mem;
        //   1067: invokevirtual  #90 // jnr.x86asm.Assembler.movsx:(Ljnr/x86asm/Register;Ljnr/x86asm/Mem;)V
        //   1070: goto  1329 (offset +259)
        //   1073: aload  9
        //   1075: getstatic  #55 // jnr.x86asm.Asm.rax:Ljnr/x86asm/Register;
        //   1078: getstatic  #60 // jnr.x86asm.Asm.rsp:Ljnr/x86asm/Register;
        //   1081: lconst_0
        //   1082: invokestatic  #77 // jnr.x86asm.Asm.word_ptr:(Ljnr/x86asm/Register;J)Ljnr/x86asm/Mem;
        //   1085: invokevirtual  #94 // jnr.x86asm.Assembler.movzx:(Ljnr/x86asm/Register;Ljnr/x86asm/Mem;)V
        //   1088: goto  1329 (offset +241)
        //   1091: aload  9
        //   1093: getstatic  #55 // jnr.x86asm.Asm.rax:Ljnr/x86asm/Register;
        //   1096: getstatic  #60 // jnr.x86asm.Asm.rsp:Ljnr/x86asm/Register;
        //   1099: lconst_0
        //   1100: invokestatic  #74 // jnr.x86asm.Asm.dword_ptr:(Ljnr/x86asm/Register;J)Ljnr/x86asm/Mem;
        //   1103: invokevirtual  #92 // jnr.x86asm.Assembler.movsxd:(Ljnr/x86asm/Register;Ljnr/x86asm/Mem;)V
        //   1106: goto  1329 (offset +223)
        //   1109: aload  9
        //   1111: getstatic  #44 // jnr.x86asm.Asm.eax:Ljnr/x86asm/Register;
        //   1114: getstatic  #60 // jnr.x86asm.Asm.rsp:Ljnr/x86asm/Register;
        //   1117: lconst_0
        //   1118: invokestatic  #74 // jnr.x86asm.Asm.dword_ptr:(Ljnr/x86asm/Register;J)Ljnr/x86asm/Mem;
        //   1121: invokevirtual  #84 // jnr.x86asm.Assembler.mov:(Ljnr/x86asm/Register;Ljnr/x86asm/Mem;)V
        //   1124: goto  1329 (offset +205)
        //   1127: aload  9
        //   1129: getstatic  #61 // jnr.x86asm.Asm.xmm0:Ljnr/x86asm/XMMRegister;
        //   1132: getstatic  #60 // jnr.x86asm.Asm.rsp:Ljnr/x86asm/Register;
        //   1135: lconst_0
        //   1136: invokestatic  #74 // jnr.x86asm.Asm.dword_ptr:(Ljnr/x86asm/Register;J)Ljnr/x86asm/Mem;
        //   1139: invokevirtual  #89 // jnr.x86asm.Assembler.movss:(Ljnr/x86asm/XMMRegister;Ljnr/x86asm/Mem;)V
        //   1142: goto  1329 (offset +187)
        //   1145: aload  9
        //   1147: getstatic  #61 // jnr.x86asm.Asm.xmm0:Ljnr/x86asm/XMMRegister;
        //   1150: getstatic  #60 // jnr.x86asm.Asm.rsp:Ljnr/x86asm/Register;
        //   1153: lconst_0
        //   1154: invokestatic  #76 // jnr.x86asm.Asm.qword_ptr:(Ljnr/x86asm/Register;J)Ljnr/x86asm/Mem;
        //   1157: invokevirtual  #87 // jnr.x86asm.Assembler.movsd:(Ljnr/x86asm/XMMRegister;Ljnr/x86asm/Mem;)V
        //   1160: goto  1329 (offset +169)
        //   1163: aload  9
        //   1165: getstatic  #55 // jnr.x86asm.Asm.rax:Ljnr/x86asm/Register;
        //   1168: getstatic  #60 // jnr.x86asm.Asm.rsp:Ljnr/x86asm/Register;
        //   1171: lconst_0
        //   1172: invokestatic  #76 // jnr.x86asm.Asm.qword_ptr:(Ljnr/x86asm/Register;J)Ljnr/x86asm/Mem;
        //   1175: invokevirtual  #84 // jnr.x86asm.Assembler.mov:(Ljnr/x86asm/Register;Ljnr/x86asm/Mem;)V
        //   1178: goto  1329 (offset +151)
        //   1181: getstatic  #36 // jnr.ffi.provider.jffi.X86_64StubCompiler$1.$SwitchMap$jnr$ffi$NativeType:[I
        //   1184: aload_3
        //   1185: invokevirtual  #67 // jnr.ffi.provider.ResultType.getNativeType:()Ljnr/ffi/NativeType;
        //   1188: invokevirtual  #65 // jnr.ffi.NativeType.ordinal:()I
        //   1191: iaload
        //   1192: tableswitch  default->1329, 2->1232, 3->1246, 4->1260, 5->1274, 6->1288, 7->1310
        //   1232: aload  9
        //   1234: getstatic  #55 // jnr.x86asm.Asm.rax:Ljnr/x86asm/Register;
        //   1237: getstatic  #38 // jnr.x86asm.Asm.al:Ljnr/x86asm/Register;
        //   1240: invokevirtual  #91 // jnr.x86asm.Assembler.movsx:(Ljnr/x86asm/Register;Ljnr/x86asm/Register;)V
        //   1243: goto  1329 (offset +86)
        //   1246: aload  9
        //   1248: getstatic  #55 // jnr.x86asm.Asm.rax:Ljnr/x86asm/Register;
        //   1251: getstatic  #38 // jnr.x86asm.Asm.al:Ljnr/x86asm/Register;
        //   1254: invokevirtual  #95 // jnr.x86asm.Assembler.movzx:(Ljnr/x86asm/Register;Ljnr/x86asm/Register;)V
        //   1257: goto  1329 (offset +72)
        //   1260: aload  9
        //   1262: getstatic  #55 // jnr.x86asm.Asm.rax:Ljnr/x86asm/Register;
        //   1265: getstatic  #39 // jnr.x86asm.Asm.ax:Ljnr/x86asm/Register;
        //   1268: invokevirtual  #91 // jnr.x86asm.Assembler.movsx:(Ljnr/x86asm/Register;Ljnr/x86asm/Register;)V
        //   1271: goto  1329 (offset +58)
        //   1274: aload  9
        //   1276: getstatic  #55 // jnr.x86asm.Asm.rax:Ljnr/x86asm/Register;
        //   1279: getstatic  #39 // jnr.x86asm.Asm.ax:Ljnr/x86asm/Register;
        //   1282: invokevirtual  #95 // jnr.x86asm.Assembler.movzx:(Ljnr/x86asm/Register;Ljnr/x86asm/Register;)V
        //   1285: goto  1329 (offset +44)
        //   1288: getstatic  #26 // java.lang.Long.TYPE:Ljava/lang/Class;
        //   1291: aload  5
        //   1293: if_acmpne  1329 (offset +36)
        //   1296: aload  9
        //   1298: getstatic  #55 // jnr.x86asm.Asm.rax:Ljnr/x86asm/Register;
        //   1301: getstatic  #44 // jnr.x86asm.Asm.eax:Ljnr/x86asm/Register;
        //   1304: invokevirtual  #93 // jnr.x86asm.Assembler.movsxd:(Ljnr/x86asm/Register;Ljnr/x86asm/Register;)V
        //   1307: goto  1329 (offset +22)
        //   1310: getstatic  #26 // java.lang.Long.TYPE:Ljava/lang/Class;
        //   1313: aload  5
        //   1315: if_acmpne  1329 (offset +14)
        //   1318: aload  9
        //   1320: getstatic  #44 // jnr.x86asm.Asm.eax:Ljnr/x86asm/Register;
        //   1323: getstatic  #44 // jnr.x86asm.Asm.eax:Ljnr/x86asm/Register;
        //   1326: invokevirtual  #85 // jnr.x86asm.Assembler.mov:(Ljnr/x86asm/Register;Ljnr/x86asm/Register;)V
        //   1329: aload  9
        //   1331: getstatic  #60 // jnr.x86asm.Asm.rsp:Ljnr/x86asm/Register;
        //   1334: iload  13
        //   1336: i2l
        //   1337: invokestatic  #75 // jnr.x86asm.Asm.imm:(J)Ljnr/x86asm/Immediate;
        //   1340: invokevirtual  #79 // jnr.x86asm.Assembler.add:(Ljnr/x86asm/Register;Ljnr/x86asm/Immediate;)V
        //   1343: aload  9
        //   1345: invokevirtual  #96 // jnr.x86asm.Assembler.ret:()V
        //   1348: aload_0
        //   1349: getfield  #35 // jnr.ffi.provider.jffi.X86_64StubCompiler.stubs:Ljava/util/List;
        //   1352: new  #16 // jnr.ffi.provider.jffi.AbstractX86StubCompiler$Stub
        //   1355: dup
        //   1356: aload_2
        //   1357: aload  5
        //   1359: aload  6
        //   1361: invokestatic  #70 // jnr.ffi.provider.jffi.CodegenUtils.sig:(Ljava/lang/Class;[Ljava/lang/Class;)Ljava/lang/String;
        //   1364: aload  9
        //   1366: invokespecial  #69 // jnr.ffi.provider.jffi.AbstractX86StubCompiler$Stub.<init>:(Ljava/lang/String;Ljava/lang/String;Ljnr/x86asm/Assembler;)V
        //   1369: invokeinterface  #99 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //   1374: pop
        //   1375: return
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