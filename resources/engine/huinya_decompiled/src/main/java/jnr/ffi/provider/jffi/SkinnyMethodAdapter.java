// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.SkinnyMethodAdapter
package jnr.ffi.provider.jffi;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;
import jnr.ffi.provider.jffi.CodegenUtils;
import jnr.ffi.provider.jffi.LocalVariable;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class SkinnyMethodAdapter extends MethodVisitor implements Opcodes {

    // ---- поля ----
  private static final boolean DEBUG;
  private MethodVisitor method;

    static {
        DEBUG = Boolean.getBoolean("jnr.ffi.compile.dump");
    }

  public SkinnyMethodAdapter(ClassVisitor arg0, int arg1, String arg2, String arg3, String arg4, String[] arg5) { // было: <init>
        super(262144);
        setMethodVisitor(arg0.visitMethod(arg1, arg2, arg3, arg4, arg5));
    }

  public MethodVisitor getMethodVisitor() {
        return method;
    }

  public void setMethodVisitor(MethodVisitor arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getstatic  #30 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.DEBUG:Z
        //      4: ifeq  14 (offset +10)
        //      7: aload_1
        //      8: invokestatic  #40 // jnr.ffi.provider.jffi.AsmUtil.newTraceMethodVisitor:(Lorg/objectweb/asm/MethodVisitor;)Lorg/objectweb/asm/MethodVisitor;
        //     11: goto  15 (offset +4)
        //     14: aload_1
        //     15: putfield  #31 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.method:Lorg/objectweb/asm/MethodVisitor;
        //     18: return
    }

  public void aload(int arg0) {
        getMethodVisitor().visitVarInsn(25, arg0);
    }

  public void aload(LocalVariable arg0) {
        getMethodVisitor().visitVarInsn(25, arg0.idx);
    }

  public void aload(int[] arg0) {
        int[] var2 = arg0;
        int var3 = var2.length;
        int var4 = 0;
        while (var4 < var3) {
            int var5 = var2[var4];
            getMethodVisitor().visitVarInsn(25, var5);
            ++var4;
            continue;
        }
    }

  public void aload(LocalVariable[] arg0) {
        LocalVariable[] var2 = arg0;
        int var3 = var2.length;
        int var4 = 0;
        while (var4 < var3) {
            Object var5 = var2[var4];
            getMethodVisitor().visitVarInsn(25, var5.idx);
            ++var4;
            continue;
        }
    }

  public void iload(int arg0) {
        getMethodVisitor().visitVarInsn(21, arg0);
    }

  public void iload(LocalVariable arg0) {
        getMethodVisitor().visitVarInsn(21, arg0.idx);
    }

  public void iload(int[] arg0) {
        int[] var2 = arg0;
        int var3 = var2.length;
        int var4 = 0;
        while (var4 < var3) {
            int var5 = var2[var4];
            getMethodVisitor().visitVarInsn(21, var5);
            ++var4;
            continue;
        }
    }

  public void iload(LocalVariable[] arg0) {
        LocalVariable[] var2 = arg0;
        int var3 = var2.length;
        int var4 = 0;
        while (var4 < var3) {
            Object var5 = var2[var4];
            getMethodVisitor().visitVarInsn(21, var5.idx);
            ++var4;
            continue;
        }
    }

  public void lload(int arg0) {
        getMethodVisitor().visitVarInsn(22, arg0);
    }

  public void lload(int[] arg0) {
        int[] var2 = arg0;
        int var3 = var2.length;
        int var4 = 0;
        while (var4 < var3) {
            int var5 = var2[var4];
            getMethodVisitor().visitVarInsn(22, var5);
            ++var4;
            continue;
        }
    }

  public void lload(LocalVariable[] arg0) {
        LocalVariable[] var2 = arg0;
        int var3 = var2.length;
        int var4 = 0;
        while (var4 < var3) {
            Object var5 = var2[var4];
            getMethodVisitor().visitVarInsn(22, var5.idx);
            ++var4;
            continue;
        }
    }

  public void fload(int arg0) {
        getMethodVisitor().visitVarInsn(23, arg0);
    }

  public void fload(LocalVariable arg0) {
        getMethodVisitor().visitVarInsn(23, arg0.idx);
    }

  public void fload(int[] arg0) {
        int[] var2 = arg0;
        int var3 = var2.length;
        int var4 = 0;
        while (var4 < var3) {
            int var5 = var2[var4];
            getMethodVisitor().visitVarInsn(23, var5);
            ++var4;
            continue;
        }
    }

  public void dload(LocalVariable arg0) {
        getMethodVisitor().visitVarInsn(24, arg0.idx);
    }

  public void dload(int arg0) {
        getMethodVisitor().visitVarInsn(24, arg0);
    }

  public void dload(int[] arg0) {
        int[] var2 = arg0;
        int var3 = var2.length;
        int var4 = 0;
        while (var4 < var3) {
            int var5 = var2[var4];
            getMethodVisitor().visitVarInsn(24, var5);
            ++var4;
            continue;
        }
    }

  public void astore(int arg0) {
        getMethodVisitor().visitVarInsn(58, arg0);
    }

  public void astore(LocalVariable arg0) {
        getMethodVisitor().visitVarInsn(58, arg0.idx);
    }

  public void istore(int arg0) {
        getMethodVisitor().visitVarInsn(54, arg0);
    }

  public void istore(LocalVariable arg0) {
        getMethodVisitor().visitVarInsn(54, arg0.idx);
    }

  public void lstore(int arg0) {
        getMethodVisitor().visitVarInsn(55, arg0);
    }

  public void lstore(LocalVariable arg0) {
        getMethodVisitor().visitVarInsn(55, arg0.idx);
    }

  public void fstore(int arg0) {
        getMethodVisitor().visitVarInsn(56, arg0);
    }

  public void fstore(LocalVariable arg0) {
        getMethodVisitor().visitVarInsn(56, arg0.idx);
    }

  public void dstore(int arg0) {
        getMethodVisitor().visitVarInsn(57, arg0);
    }

  public void dstore(LocalVariable arg0) {
        getMethodVisitor().visitVarInsn(57, arg0.idx);
    }

  public void ldc(Object arg0) {
        getMethodVisitor().visitLdcInsn(arg0);
    }

  public void bipush(int arg0) {
        getMethodVisitor().visitIntInsn(16, arg0);
    }

  public void sipush(int arg0) {
        getMethodVisitor().visitIntInsn(17, arg0);
    }

  public void pushInt(int arg0) {
        if (arg0 > 127) {
            if (arg0 > 32767) {
                ldc(Integer.valueOf(arg0));
            } else {
                if (arg0 < -32768) {
                    ldc(Integer.valueOf(arg0));
                } else {
                    sipush(arg0);
                }
            }
        } else {
            if (arg0 < -128) {
                if (arg0 > 32767) {
                    ldc(Integer.valueOf(arg0));
                } else {
                    if (arg0 < -32768) {
                        ldc(Integer.valueOf(arg0));
                    } else {
                        sipush(arg0);
                    }
                }
            } else {
                switch (arg0) {
                    case -1:
                        iconst_m1();
                        break;
                    case 0:
                        iconst_0();
                        break;
                    case 1:
                        iconst_1();
                        break;
                    case 2:
                        iconst_2();
                        break;
                    case 3:
                        iconst_3();
                        break;
                    case 4:
                        iconst_4();
                        break;
                    case 5:
                        iconst_5();
                        break;
                    default:
                        bipush(arg0);
                        break;
                }
            }
        }
    }

  public void pushBoolean(boolean arg0) {
        if (!arg0) {
            iconst_0();
        } else {
            iconst_1();
        }
    }

  public void invokestatic(String arg0, String arg1, String arg2) {
        getMethodVisitor().visitMethodInsn(184, arg0, arg1, arg2);
    }

  public void invokestatic(Class arg0, String arg1, Class arg2, Class[] arg3) {
        getMethodVisitor().visitMethodInsn(184, CodegenUtils.method1942(arg0), arg1, CodegenUtils.sig(arg2, arg3));
    }

  public void invokespecial(String arg0, String arg1, String arg2) {
        getMethodVisitor().visitMethodInsn(183, arg0, arg1, arg2);
    }

  public void invokespecial(Class arg0, String arg1, Class arg2, Class[] arg3) {
        getMethodVisitor().visitMethodInsn(183, CodegenUtils.method1942(arg0), arg1, CodegenUtils.sig(arg2, arg3));
    }

  public void invokevirtual(String arg0, String arg1, String arg2) {
        getMethodVisitor().visitMethodInsn(182, arg0, arg1, arg2);
    }

  public void invokevirtual(Class arg0, String arg1, Class arg2, Class[] arg3) {
        getMethodVisitor().visitMethodInsn(182, CodegenUtils.method1942(arg0), arg1, CodegenUtils.sig(arg2, arg3));
    }

  public void invokeinterface(String arg0, String arg1, String arg2) {
        getMethodVisitor().visitMethodInsn(185, arg0, arg1, arg2);
    }

  public void invokeinterface(Class arg0, String arg1, Class arg2, Class[] arg3) {
        getMethodVisitor().visitMethodInsn(185, CodegenUtils.method1942(arg0), arg1, CodegenUtils.sig(arg2, arg3));
    }

  public void invokedynamic(String arg0, String arg1, String arg2) {
        getMethodVisitor().visitMethodInsn(186, arg0, arg1, arg2);
    }

  public void aprintln() {
        dup();
        getstatic(CodegenUtils.method1942(System.class), "out", CodegenUtils.ci(PrintStream.class));
        swap();
        invokevirtual(CodegenUtils.method1942(PrintStream.class), "println", CodegenUtils.sig(Void.TYPE, CodegenUtils.params(new Class[]{Object.class})));
    }

  public void areturn() {
        getMethodVisitor().visitInsn(176);
    }

  public void ireturn() {
        getMethodVisitor().visitInsn(172);
    }

  public void freturn() {
        getMethodVisitor().visitInsn(174);
    }

  public void lreturn() {
        getMethodVisitor().visitInsn(173);
    }

  public void dreturn() {
        getMethodVisitor().visitInsn(175);
    }

  public void newobj(String arg0) {
        getMethodVisitor().visitTypeInsn(187, arg0);
    }

  public void dup() {
        getMethodVisitor().visitInsn(89);
    }

  public void swap() {
        getMethodVisitor().visitInsn(95);
    }

  public void swap2() {
        dup2_x2();
        pop2();
    }

  public void getstatic(String arg0, String arg1, String arg2) {
        getMethodVisitor().visitFieldInsn(178, arg0, arg1, arg2);
    }

  public void putstatic(String arg0, String arg1, String arg2) {
        getMethodVisitor().visitFieldInsn(179, arg0, arg1, arg2);
    }

  public void getfield(String arg0, String arg1, String arg2) {
        getMethodVisitor().visitFieldInsn(180, arg0, arg1, arg2);
    }

  public void putfield(String arg0, String arg1, String arg2) {
        getMethodVisitor().visitFieldInsn(181, arg0, arg1, arg2);
    }

  public void voidreturn() {
        getMethodVisitor().visitInsn(177);
    }

  public void anewarray(String arg0) {
        getMethodVisitor().visitTypeInsn(189, arg0);
    }

  public void multianewarray(String arg0, int arg1) {
        getMethodVisitor().visitMultiANewArrayInsn(arg0, arg1);
    }

  public void newarray(int arg0) {
        getMethodVisitor().visitIntInsn(188, arg0);
    }

  public void iconst_m1() {
        getMethodVisitor().visitInsn(2);
    }

  public void iconst_0() {
        getMethodVisitor().visitInsn(3);
    }

  public void iconst_1() {
        getMethodVisitor().visitInsn(4);
    }

  public void iconst_2() {
        getMethodVisitor().visitInsn(5);
    }

  public void iconst_3() {
        getMethodVisitor().visitInsn(6);
    }

  public void iconst_4() {
        getMethodVisitor().visitInsn(7);
    }

  public void iconst_5() {
        getMethodVisitor().visitInsn(8);
    }

  public void lconst_0() {
        getMethodVisitor().visitInsn(9);
    }

  public void aconst_null() {
        getMethodVisitor().visitInsn(1);
    }

  public void label(Label arg0) {
        getMethodVisitor().visitLabel(arg0);
    }

  public void nop() {
        getMethodVisitor().visitInsn(0);
    }

  public void pop() {
        getMethodVisitor().visitInsn(87);
    }

  public void pop2() {
        getMethodVisitor().visitInsn(88);
    }

  public void arrayload() {
        getMethodVisitor().visitInsn(50);
    }

  public void arraystore() {
        getMethodVisitor().visitInsn(83);
    }

  public void iarrayload() {
        getMethodVisitor().visitInsn(46);
    }

  public void barrayload() {
        getMethodVisitor().visitInsn(51);
    }

  public void barraystore() {
        getMethodVisitor().visitInsn(84);
    }

  public void aaload() {
        getMethodVisitor().visitInsn(50);
    }

  public void aastore() {
        getMethodVisitor().visitInsn(83);
    }

  public void iaload() {
        getMethodVisitor().visitInsn(46);
    }

  public void iastore() {
        getMethodVisitor().visitInsn(79);
    }

  public void laload() {
        getMethodVisitor().visitInsn(47);
    }

  public void lastore() {
        getMethodVisitor().visitInsn(80);
    }

  public void baload() {
        getMethodVisitor().visitInsn(51);
    }

  public void bastore() {
        getMethodVisitor().visitInsn(84);
    }

  public void saload() {
        getMethodVisitor().visitInsn(53);
    }

  public void sastore() {
        getMethodVisitor().visitInsn(86);
    }

  public void caload() {
        getMethodVisitor().visitInsn(52);
    }

  public void castore() {
        getMethodVisitor().visitInsn(85);
    }

  public void faload() {
        getMethodVisitor().visitInsn(48);
    }

  public void fastore() {
        getMethodVisitor().visitInsn(81);
    }

  public void daload() {
        getMethodVisitor().visitInsn(49);
    }

  public void dastore() {
        getMethodVisitor().visitInsn(82);
    }

  public void fcmpl() {
        getMethodVisitor().visitInsn(149);
    }

  public void fcmpg() {
        getMethodVisitor().visitInsn(150);
    }

  public void dcmpl() {
        getMethodVisitor().visitInsn(151);
    }

  public void dcmpg() {
        getMethodVisitor().visitInsn(152);
    }

  public void dup_x2() {
        getMethodVisitor().visitInsn(91);
    }

  public void dup_x1() {
        getMethodVisitor().visitInsn(90);
    }

  public void dup2_x2() {
        getMethodVisitor().visitInsn(94);
    }

  public void dup2_x1() {
        getMethodVisitor().visitInsn(93);
    }

  public void dup2() {
        getMethodVisitor().visitInsn(92);
    }

  public void trycatch(Label arg0, Label arg1, Label arg2, String arg3) {
        getMethodVisitor().visitTryCatchBlock(arg0, arg1, arg2, arg3);
    }

  public void trycatch(String arg0, Runnable arg1, Runnable arg2) {
        Label var4 = new Label();
        Label var5 = new Label();
        Label var6 = new Label();
        Label var7 = new Label();
        trycatch(var4, var5, var6, arg0);
        label(var4);
        arg1.run();
        label(var5);
        go_to(var7);
        if (arg2 != null) {
            label(var6);
            arg2.run();
        }
        label(var7);
    }

  public void go_to(Label arg0) {
        getMethodVisitor().visitJumpInsn(167, arg0);
    }

  public void lookupswitch(Label arg0, int[] arg1, Label[] arg2) {
        getMethodVisitor().visitLookupSwitchInsn(arg0, arg1, arg2);
    }

  public void athrow() {
        getMethodVisitor().visitInsn(191);
    }

  public void instance_of(String arg0) {
        getMethodVisitor().visitTypeInsn(193, arg0);
    }

  public void ifeq(Label arg0) {
        getMethodVisitor().visitJumpInsn(153, arg0);
    }

  public void iffalse(Label arg0) {
        ifeq(arg0);
    }

  public void ifne(Label arg0) {
        getMethodVisitor().visitJumpInsn(154, arg0);
    }

  public void iftrue(Label arg0) {
        ifne(arg0);
    }

  public void if_acmpne(Label arg0) {
        getMethodVisitor().visitJumpInsn(166, arg0);
    }

  public void if_acmpeq(Label arg0) {
        getMethodVisitor().visitJumpInsn(165, arg0);
    }

  public void if_icmple(Label arg0) {
        getMethodVisitor().visitJumpInsn(164, arg0);
    }

  public void if_icmpgt(Label arg0) {
        getMethodVisitor().visitJumpInsn(163, arg0);
    }

  public void if_icmpge(Label arg0) {
        getMethodVisitor().visitJumpInsn(162, arg0);
    }

  public void if_icmplt(Label arg0) {
        getMethodVisitor().visitJumpInsn(161, arg0);
    }

  public void if_icmpne(Label arg0) {
        getMethodVisitor().visitJumpInsn(160, arg0);
    }

  public void if_icmpeq(Label arg0) {
        getMethodVisitor().visitJumpInsn(159, arg0);
    }

  public void checkcast(String arg0) {
        getMethodVisitor().visitTypeInsn(192, arg0);
    }

  public void checkcast(Class arg0) {
        getMethodVisitor().visitTypeInsn(192, CodegenUtils.method1942(arg0));
    }

  public void start() {
        getMethodVisitor().visitCode();
    }

  private void dump() {
        PrintWriter var1 = new PrintWriter(System.out);
        Class var2 = getMethodVisitor().getClass();
        try {
            Method var3 = var2.getDeclaredMethod("print", new Class[]{PrintWriter.class});
            var1.write("*** Dumping ***\n");
            Object[] __obj2 = new Object[1];
            __obj2[0] = var1;
            var3.invoke(getMethodVisitor(), __obj2);
        } catch (Throwable e1) {
            Throwable var3 = e1;
            var1.flush();
        } catch (Throwable e3) {
            try {
                while (true) {
                    Throwable var4 = e3;
                }
            } catch (Throwable var4) {
            }
        }
    }

  public void end() {
        if (DEBUG) {
            dump();
        }
        getMethodVisitor().visitMaxs(1, 1);
        getMethodVisitor().visitEnd();
    }

  public void line(int arg0) {
        Label var2 = new Label();
        label(var2);
        visitLineNumber(arg0, var2);
    }

  public void line(int arg0, Label arg1) {
        visitLineNumber(arg0, arg1);
    }

  public void ifnonnull(Label arg0) {
        getMethodVisitor().visitJumpInsn(199, arg0);
    }

  public void ifnull(Label arg0) {
        getMethodVisitor().visitJumpInsn(198, arg0);
    }

  public void iflt(Label arg0) {
        getMethodVisitor().visitJumpInsn(155, arg0);
    }

  public void ifle(Label arg0) {
        getMethodVisitor().visitJumpInsn(158, arg0);
    }

  public void ifgt(Label arg0) {
        getMethodVisitor().visitJumpInsn(157, arg0);
    }

  public void ifge(Label arg0) {
        getMethodVisitor().visitJumpInsn(156, arg0);
    }

  public void arraylength() {
        getMethodVisitor().visitInsn(190);
    }

  public void ishr() {
        getMethodVisitor().visitInsn(122);
    }

  public void ishl() {
        getMethodVisitor().visitInsn(120);
    }

  public void iushr() {
        getMethodVisitor().visitInsn(124);
    }

  public void lshr() {
        getMethodVisitor().visitInsn(123);
    }

  public void lshl() {
        getMethodVisitor().visitInsn(121);
    }

  public void lushr() {
        getMethodVisitor().visitInsn(125);
    }

  public void lcmp() {
        getMethodVisitor().visitInsn(148);
    }

  public void iand() {
        getMethodVisitor().visitInsn(126);
    }

  public void ior() {
        getMethodVisitor().visitInsn(128);
    }

  public void ixor() {
        getMethodVisitor().visitInsn(130);
    }

  public void land() {
        getMethodVisitor().visitInsn(127);
    }

  public void lor() {
        getMethodVisitor().visitInsn(129);
    }

  public void lxor() {
        getMethodVisitor().visitInsn(131);
    }

  public void iadd() {
        getMethodVisitor().visitInsn(96);
    }

  public void ladd() {
        getMethodVisitor().visitInsn(97);
    }

  public void fadd() {
        getMethodVisitor().visitInsn(98);
    }

  public void dadd() {
        getMethodVisitor().visitInsn(99);
    }

  public void isub() {
        getMethodVisitor().visitInsn(100);
    }

  public void lsub() {
        getMethodVisitor().visitInsn(101);
    }

  public void fsub() {
        getMethodVisitor().visitInsn(102);
    }

  public void dsub() {
        getMethodVisitor().visitInsn(103);
    }

  public void idiv() {
        getMethodVisitor().visitInsn(108);
    }

  public void irem() {
        getMethodVisitor().visitInsn(112);
    }

  public void ineg() {
        getMethodVisitor().visitInsn(116);
    }

  public void i2d() {
        getMethodVisitor().visitInsn(135);
    }

  public void i2l() {
        getMethodVisitor().visitInsn(133);
    }

  public void i2f() {
        getMethodVisitor().visitInsn(134);
    }

  public void i2s() {
        getMethodVisitor().visitInsn(147);
    }

  public void i2c() {
        getMethodVisitor().visitInsn(146);
    }

  public void i2b() {
        getMethodVisitor().visitInsn(145);
    }

  public void ldiv() {
        getMethodVisitor().visitInsn(109);
    }

  public void lrem() {
        getMethodVisitor().visitInsn(113);
    }

  public void lneg() {
        getMethodVisitor().visitInsn(117);
    }

  public void l2d() {
        getMethodVisitor().visitInsn(138);
    }

  public void l2i() {
        getMethodVisitor().visitInsn(136);
    }

  public void l2f() {
        getMethodVisitor().visitInsn(137);
    }

  public void fdiv() {
        getMethodVisitor().visitInsn(110);
    }

  public void frem() {
        getMethodVisitor().visitInsn(114);
    }

  public void fneg() {
        getMethodVisitor().visitInsn(118);
    }

  public void f2d() {
        getMethodVisitor().visitInsn(141);
    }

  public void f2i() {
        getMethodVisitor().visitInsn(141);
    }

  public void f2l() {
        getMethodVisitor().visitInsn(140);
    }

  public void ddiv() {
        getMethodVisitor().visitInsn(111);
    }

  public void drem() {
        getMethodVisitor().visitInsn(115);
    }

  public void dneg() {
        getMethodVisitor().visitInsn(119);
    }

  public void d2f() {
        getMethodVisitor().visitInsn(144);
    }

  public void d2i() {
        getMethodVisitor().visitInsn(142);
    }

  public void d2l() {
        getMethodVisitor().visitInsn(143);
    }

  public void imul() {
        getMethodVisitor().visitInsn(104);
    }

  public void lmul() {
        getMethodVisitor().visitInsn(105);
    }

  public void fmul() {
        getMethodVisitor().visitInsn(106);
    }

  public void dmul() {
        getMethodVisitor().visitInsn(107);
    }

  public void iinc(int arg0, int arg1) {
        getMethodVisitor().visitIincInsn(arg0, arg1);
    }

  public void iinc(LocalVariable arg0, int arg1) {
        getMethodVisitor().visitIincInsn(arg0.idx, arg1);
    }

  public void monitorenter() {
        getMethodVisitor().visitInsn(194);
    }

  public void monitorexit() {
        getMethodVisitor().visitInsn(195);
    }

  public void jsr(Label arg0) {
        getMethodVisitor().visitJumpInsn(168, arg0);
    }

  public void ret(int arg0) {
        getMethodVisitor().visitVarInsn(169, arg0);
    }

  public AnnotationVisitor visitAnnotationDefault() {
        return getMethodVisitor().visitAnnotationDefault();
    }

  public AnnotationVisitor visitAnnotation(String arg0, boolean arg1) {
        return getMethodVisitor().visitAnnotation(arg0, arg1);
    }

  public AnnotationVisitor visitParameterAnnotation(int arg0, String arg1, boolean arg2) {
        return getMethodVisitor().visitParameterAnnotation(arg0, arg1, arg2);
    }

  public void visitAnnotationWithFields(String arg0, boolean arg1, Map arg2) {
        AnnotationVisitor var4 = visitAnnotation(arg0, arg1);
        CodegenUtils.visitAnnotationFields(var4, arg2);
        var4.visitEnd();
    }

  public void visitParameterAnnotationWithFields(int arg0, String arg1, boolean arg2, Map arg3) {
        AnnotationVisitor var5 = visitParameterAnnotation(arg0, arg1, arg2);
        CodegenUtils.visitAnnotationFields(var5, arg3);
        var5.visitEnd();
    }

  public void visitAttribute(Attribute arg0) {
        getMethodVisitor().visitAttribute(arg0);
    }

  public void visitCode() {
        getMethodVisitor().visitCode();
    }

  public void visitInsn(int arg0) {
        getMethodVisitor().visitInsn(arg0);
    }

  public void visitIntInsn(int arg0, int arg1) {
        getMethodVisitor().visitIntInsn(arg0, arg1);
    }

  public void visitVarInsn(int arg0, int arg1) {
        getMethodVisitor().visitVarInsn(arg0, arg1);
    }

  public void visitTypeInsn(int arg0, String arg1) {
        getMethodVisitor().visitTypeInsn(arg0, arg1);
    }

  public void visitFieldInsn(int arg0, String arg1, String arg2, String arg3) {
        getMethodVisitor().visitFieldInsn(arg0, arg1, arg2, arg3);
    }

  public void visitMethodInsn(int arg0, String arg1, String arg2, String arg3) {
        getMethodVisitor().visitMethodInsn(arg0, arg1, arg2, arg3);
    }

  public void visitJumpInsn(int arg0, Label arg1) {
        getMethodVisitor().visitJumpInsn(arg0, arg1);
    }

  public void visitLabel(Label arg0) {
        getMethodVisitor().visitLabel(arg0);
    }

  public void visitLdcInsn(Object arg0) {
        getMethodVisitor().visitLdcInsn(arg0);
    }

  public void visitIincInsn(int arg0, int arg1) {
        getMethodVisitor().visitIincInsn(arg0, arg1);
    }

  public void visitTableSwitchInsn(int arg0, int arg1, Label arg2, Label[] arg3) {
        getMethodVisitor().visitTableSwitchInsn(arg0, arg1, arg2, arg3);
    }

  public void visitLookupSwitchInsn(Label arg0, int[] arg1, Label[] arg2) {
        getMethodVisitor().visitLookupSwitchInsn(arg0, arg1, arg2);
    }

  public void visitMultiANewArrayInsn(String arg0, int arg1) {
        getMethodVisitor().visitMultiANewArrayInsn(arg0, arg1);
    }

  public void visitTryCatchBlock(Label arg0, Label arg1, Label arg2, String arg3) {
        getMethodVisitor().visitTryCatchBlock(arg0, arg1, arg2, arg3);
    }

  public void visitLocalVariable(String arg0, String arg1, String arg2, Label arg3, Label arg4, int arg5) {
        getMethodVisitor().visitLocalVariable(arg0, arg1, arg2, arg3, arg4, arg5);
    }

  public void visitLineNumber(int arg0, Label arg1) {
        getMethodVisitor().visitLineNumber(arg0, arg1);
    }

  public void visitMaxs(int arg0, int arg1) {
        if (DEBUG) {
            dump();
        }
        getMethodVisitor().visitMaxs(arg0, arg1);
    }

  public void visitEnd() {
        getMethodVisitor().visitEnd();
    }

  public void tableswitch(int arg0, int arg1, Label arg2, Label[] arg3) {
        getMethodVisitor().visitTableSwitchInsn(arg0, arg1, arg2, arg3);
    }

  public void visitFrame(int arg0, int arg1, Object[] arg2, int arg3, Object[] arg4) {
        getMethodVisitor().visitFrame(arg0, arg1, arg2, arg3, arg4);
    }

}