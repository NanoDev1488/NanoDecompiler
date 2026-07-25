// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.X86_32StubCompiler
package jnr.ffi.provider.jffi;

import com.kenai.jffi.Function;
import java.util.List;
import jnr.ffi.CallingConvention;
import jnr.ffi.NativeType;
import jnr.ffi.Runtime;
import jnr.ffi.provider.ParameterType;
import jnr.ffi.provider.ResultType;
import jnr.ffi.provider.jffi.AbstractX86StubCompiler;
import jnr.ffi.provider.jffi.AbstractX86StubCompiler_Stub;
import jnr.ffi.provider.jffi.CodegenUtils;
import jnr.x86asm.Asm;
import jnr.x86asm.Assembler;
import jnr.x86asm.Mem;
import jnr.x86asm.Register;

final class X86_32StubCompiler extends AbstractX86StubCompiler {

   X86_32StubCompiler(Runtime arg0) { // было: <init>
        super(arg0);
    }

   boolean canCompile(ResultType arg0, ParameterType[] arg1, CallingConvention arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #42 // jnr.ffi.provider.jffi.X86_32StubCompiler$1.$SwitchMap$jnr$ffi$NativeType:[I
        //      3: aload_1
        //      4: invokevirtual  #58 // jnr.ffi.provider.ResultType.getNativeType:()Ljnr/ffi/NativeType;
        //      7: invokevirtual  #56 // jnr.ffi.NativeType.ordinal:()I
        //     10: iaload
        //     11: tableswitch  default->83, 1->80, 2->80, 3->80, 4->80, 5->80, 6->80, 7->80, 8->80, 9->80, 10->80, 11->80, 12->80, 13->80, 14->80
        //     80: goto  85 (offset +5)
        //     83: iconst_0
        //     84: ireturn
        //     85: aload_3
        //     86: getstatic  #37 // jnr.ffi.CallingConvention.DEFAULT:Ljnr/ffi/CallingConvention;
        //     89: if_acmpeq  94 (offset +5)
        //     92: iconst_0
        //     93: ireturn
        //     94: iconst_0
        //     95: istore  4
        //     97: iconst_0
        //     98: istore  5
        //    100: aload_2
        //    101: astore  6
        //    103: aload  6
        //    105: arraylength
        //    106: istore  7
        //    108: iconst_0
        //    109: istore  8
        //    111: iload  8
        //    113: iload  7
        //    115: if_icmpge  224 (offset +109)
        //    118: aload  6
        //    120: iload  8
        //    122: aaload
        //    123: astore  9
        //    125: getstatic  #42 // jnr.ffi.provider.jffi.X86_32StubCompiler$1.$SwitchMap$jnr$ffi$NativeType:[I
        //    128: aload  9
        //    130: invokevirtual  #57 // jnr.ffi.provider.ParameterType.getNativeType:()Ljnr/ffi/NativeType;
        //    133: invokevirtual  #56 // jnr.ffi.NativeType.ordinal:()I
        //    136: iaload
        //    137: tableswitch  default->216, 2->204, 3->204, 4->204, 5->204, 6->204, 7->204, 8->204, 9->204, 10->204, 11->204, 12->210, 13->210, 14->204
        //    204: iinc  5, 1
        //    207: goto  218 (offset +11)
        //    210: iinc  4, 1
        //    213: goto  218 (offset +5)
        //    216: iconst_0
        //    217: ireturn
        //    218: iinc  8, 1
        //    221: goto  111 (offset -110)
        //    224: iconst_1
        //    225: ireturn
    }

   void compile(Function arg0, String arg1, ResultType arg2, ParameterType[] arg3, Class arg4, Class[] arg5, CallingConvention arg6, boolean arg7) {
        int var9 = 0;
        ParameterType[] var10 = arg3;
        int var11 = var10.length;
        int var12 = 0;
        Object var13;
        while (var12 < var11) {
            var13 = var10[var12];
            var9 = var9 + parameterSize(((ParameterType) var13));
            ++var12;
            continue;
        }
        var10 = resultSize(arg2);
        var11 = align(Math.max(var9, var10) + 4, 16) - 4;
        var12 = new Assembler(Asm.X86_32);
        var12.sub(Asm.esp, Asm.imm(((long) var11)));
        int var13 = 0;
        int var14 = 0;
        int var15 = 0;
        while (var13 < arg3.length) {
            int var16 = parameterSize(((Class) arg5[var13]));
            int var17 = parameterSize(((ParameterType) arg3[var13]));
            int var18 = var11 + 4 + 8 + var14;
            switch (arg3[var13].getNativeType()) {
                case SCHAR:
                case SSHORT:
                    var12.movsx(Asm.eax, ptr(Asm.esp, ((long) var18), arg3[var13].getNativeType()));
                    break;
                case UCHAR:
                case USHORT:
                    var12.movzx(Asm.eax, ptr(Asm.esp, ((long) var18), arg3[var13].getNativeType()));
                    break;
                default:
                    var12.mov(Asm.eax, Asm.dword_ptr(Asm.esp, ((long) var18)));
            }
            var12.mov(Asm.dword_ptr(Asm.esp, ((long) var15)), Asm.eax);
            if (var17 > 4) {
                if (arg3[var13].getNativeType() != NativeType.SLONGLONG) {
                    if (arg3[var13].getNativeType() != NativeType.ULONGLONG) {
                        var12.mov(Asm.eax, Asm.dword_ptr(Asm.esp, ((long) (var18 + 4))));
                    } else {
                        if (Long.TYPE == arg5[var13]) {
                            var12.mov(Asm.eax, Asm.dword_ptr(Asm.esp, ((long) (var18 + 4))));
                        } else {
                            var12.mov(Asm.dword_ptr(Asm.esp, ((long) (var15 + 4))), Asm.imm(0L));
                        }
                    }
                } else {
                    if (Long.TYPE == arg5[var13]) {
                        if (arg3[var13].getNativeType() != NativeType.ULONGLONG) {
                            var12.mov(Asm.eax, Asm.dword_ptr(Asm.esp, ((long) (var18 + 4))));
                        } else {
                            if (Long.TYPE == arg5[var13]) {
                                var12.mov(Asm.eax, Asm.dword_ptr(Asm.esp, ((long) (var18 + 4))));
                            } else {
                                var12.mov(Asm.dword_ptr(Asm.esp, ((long) (var15 + 4))), Asm.imm(0L));
                            }
                        }
                    } else {
                        var12.sar(Asm.eax, Asm.imm(31L));
                    }
                }
                var12.mov(Asm.dword_ptr(Asm.esp, ((long) (var15 + 4))), Asm.eax);
            }
            var15 = var15 + var17;
            var14 = var14 + var16;
            ++var13;
            continue;
        }
        var12.call(Asm.imm(arg0.getFunctionAddress() & 4294967295L));
        if (!arg7) {
            switch (arg2.getNativeType()) {
                case SCHAR:
                    var12.movsx(Asm.eax, Asm.al);
                    break;
                case UCHAR:
                    var12.movzx(Asm.eax, Asm.al);
                    break;
                case SSHORT:
                    var12.movsx(Asm.eax, Asm.ax);
                    break;
                case USHORT:
                    var12.movzx(Asm.eax, Asm.ax);
                default:
            }
        } else {
            var13 = 0;
            switch (arg2.getNativeType()) {
                case FLOAT:
                    var12.fstp(Asm.dword_ptr(Asm.esp, ((long) var13)));
                    break;
                case DOUBLE:
                    var12.fstp(Asm.qword_ptr(Asm.esp, ((long) var13)));
                    break;
                case SLONGLONG:
                case ULONGLONG:
                    var12.mov(Asm.dword_ptr(Asm.esp, ((long) var13)), Asm.eax);
                    var12.mov(Asm.dword_ptr(Asm.esp, ((long) (var13 + 4))), Asm.edx);
                    break;
                case VOID:
                    break;
                default:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    var12.mov(Asm.dword_ptr(Asm.esp, ((long) var13)), Asm.eax);
            }
            var12.call(Asm.imm(errnoFunctionAddress & 4294967295L));
            switch (arg2.getNativeType()) {
                case FLOAT:
                    var12.fld(Asm.dword_ptr(Asm.esp, ((long) var13)));
                    break;
                case DOUBLE:
                    var12.fld(Asm.qword_ptr(Asm.esp, ((long) var13)));
                    break;
                case SCHAR:
                    var12.movsx(Asm.eax, Asm.byte_ptr(Asm.esp, ((long) var13)));
                    break;
                case UCHAR:
                    var12.movzx(Asm.eax, Asm.byte_ptr(Asm.esp, ((long) var13)));
                    break;
                case SSHORT:
                    var12.movsx(Asm.eax, Asm.word_ptr(Asm.esp, ((long) var13)));
                    break;
                case USHORT:
                    var12.movzx(Asm.eax, Asm.word_ptr(Asm.esp, ((long) var13)));
                    break;
                case SLONGLONG:
                case ULONGLONG:
                    var12.mov(Asm.eax, Asm.dword_ptr(Asm.esp, ((long) var13)));
                    var12.mov(Asm.edx, Asm.dword_ptr(Asm.esp, ((long) (var13 + 4))));
                    break;
                case VOID:
                    break;
                default:
                case 6:
                case 7:
                case 8:
                case 9:
                    var12.mov(Asm.eax, Asm.dword_ptr(Asm.esp, ((long) var13)));
            }
        }
        if (Long.TYPE == arg4) {
            switch (arg2.getNativeType()) {
                case SCHAR:
                case SSHORT:
                case SINT:
                case SLONG:
                    var12.mov(Asm.edx, Asm.eax);
                    var12.sar(Asm.edx, Asm.imm(31L));
                    break;
                case UCHAR:
                case USHORT:
                case UINT:
                case ULONG:
                case ADDRESS:
                    var12.mov(Asm.edx, Asm.imm(0L));
                default:
                case 10:
                case 11:
                case 12:
                case 13:
            }
        }
        var12.add(Asm.esp, Asm.imm(((long) var11)));
        var12.ret();
        stubs.add(new AbstractX86StubCompiler_Stub(arg1, CodegenUtils.sig(arg4, arg5), var12));
    }

  static int parameterSize(ParameterType arg0) {
        switch (arg0.getNativeType()) {
            case SCHAR:
            case UCHAR:
            case SSHORT:
            case USHORT:
            case SINT:
            case UINT:
            case SLONG:
            case ULONG:
            case FLOAT:
            case ADDRESS:
                return 4;
            case SLONGLONG:
            case ULONGLONG:
            case DOUBLE:
                return 8;
            default:
                throw new IllegalArgumentException(new StringBuilder().append("invalid parameter type").append(arg0).toString());
        }
    }

  static int parameterSize(Class arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #30 // java.lang.Byte.TYPE:Ljava/lang/Class;
        //      3: aload_0
        //      4: if_acmpeq  49 (offset +45)
        //      7: getstatic  #36 // java.lang.Short.TYPE:Ljava/lang/Class;
        //     10: aload_0
        //     11: if_acmpeq  49 (offset +38)
        //     14: getstatic  #31 // java.lang.Character.TYPE:Ljava/lang/Class;
        //     17: aload_0
        //     18: if_acmpne  25 (offset +7)
        //     21: iconst_1
        //     22: goto  26 (offset +4)
        //     25: iconst_0
        //     26: getstatic  #34 // java.lang.Integer.TYPE:Ljava/lang/Class;
        //     29: aload_0
        //     30: if_acmpne  37 (offset +7)
        //     33: iconst_1
        //     34: goto  38 (offset +4)
        //     37: iconst_0
        //     38: ior
        //     39: ifne  49 (offset +10)
        //     42: getstatic  #33 // java.lang.Float.TYPE:Ljava/lang/Class;
        //     45: aload_0
        //     46: if_acmpne  51 (offset +5)
        //     49: iconst_4
        //     50: ireturn
        //     51: getstatic  #35 // java.lang.Long.TYPE:Ljava/lang/Class;
        //     54: aload_0
        //     55: if_acmpeq  65 (offset +10)
        //     58: getstatic  #32 // java.lang.Double.TYPE:Ljava/lang/Class;
        //     61: aload_0
        //     62: if_acmpne  68 (offset +6)
        //     65: bipush  8
        //     67: ireturn
        //     68: new  #8 // java.lang.IllegalArgumentException
        //     71: dup
        //     72: new  #13 // java.lang.StringBuilder
        //     75: dup
        //     76: invokespecial  #52 // java.lang.StringBuilder.<init>:()V
        //     79: ldc  #1 // 'invalid parameter type'
        //     81: invokevirtual  #54 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     84: aload_0
        //     85: invokevirtual  #53 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //     88: invokevirtual  #55 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //     91: invokespecial  #50 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //     94: athrow
    }

  static int resultSize(ResultType arg0) {
        switch (arg0.getNativeType()) {
            case SCHAR:
            case UCHAR:
            case SSHORT:
            case USHORT:
            case SINT:
            case UINT:
            case SLONG:
            case ULONG:
            case ADDRESS:
                return 4;
            case SLONGLONG:
            case ULONGLONG:
                return 8;
            case FLOAT:
            case DOUBLE:
                return 16;
            case VOID:
                return 0;
            default:
                throw new IllegalArgumentException(new StringBuilder().append("invalid return type ").append(arg0).toString());
        }
    }

  static Mem ptr(Register arg0, long arg1, NativeType arg2) {
        switch (arg2) {
            case SCHAR:
            case UCHAR:
                return Asm.byte_ptr(arg0, arg1);
            case SSHORT:
            case USHORT:
                return Asm.word_ptr(arg0, arg1);
            default:
                return Asm.dword_ptr(arg0, arg1);
        }
    }

}