// исходный (обфусцированный) внутренний класс: jnr.x86asm.Assembler
package jnr.x86asm;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import jnr.x86asm.BaseReg;
import jnr.x86asm.CPU;
import jnr.x86asm.CodeBuffer;
import jnr.x86asm.CpuInfo;
import jnr.x86asm.INST_CODE;
import jnr.x86asm.Immediate;
import jnr.x86asm.InstructionGroup;
import jnr.x86asm.Label;
import jnr.x86asm.LinkData;
import jnr.x86asm.Logger;
import jnr.x86asm.Mem;
import jnr.x86asm.Operand;
import jnr.x86asm.RelocData;
import jnr.x86asm.RelocData_Type;
import jnr.x86asm.SEGMENT;
import jnr.x86asm.Serializer;

public final class Assembler extends Serializer {

    // ---- поля ----
  private final CodeBuffer _buffer;
  private final List _relocData;
  private final CpuInfo cpuInfo;
  private int _properties;
   int _trampolineSize;
  private final Logger _logger;
  private final CPU cpu;
  public static final CPU I386;
  public static final CPU X86_64;
  private static final int[] nop1;
  private static final int[] nop2;
  private static final int[] nop3;
  private static final int[] nop4;
  private static final int[] nop5;
  private static final int[] nop6;
  private static final int[] nop7;
  private static final int[] nop8;
  private static final int[] nop9;
  private static final int[] nop10;
  private static final int[] nop11;
  static final boolean $assertionsDisabled;

    static {
        boolean __stk12;
        __stk12 = !Assembler.class.desiredAssertionStatus();
        $assertionsDisabled = __stk12;
        I386 = CPU.I386;
        X86_64 = CPU.X86_64;
        nop1 = new int[]{144};
        int[] __obj14 = new int[2];
        __obj14[0] = 102;
        __obj14[1] = 144;
        nop2 = __obj14;
        int[] __obj15 = new int[3];
        __obj15[0] = 15;
        __obj15[1] = 31;
        __obj15[2] = 0;
        nop3 = __obj15;
        int[] __obj16 = new int[4];
        __obj16[0] = 15;
        __obj16[1] = 31;
        __obj16[2] = 64;
        __obj16[3] = 0;
        nop4 = __obj16;
        int[] __obj17 = new int[5];
        __obj17[0] = 15;
        __obj17[1] = 31;
        __obj17[2] = 68;
        __obj17[3] = 0;
        __obj17[4] = 0;
        nop5 = __obj17;
        int[] __obj18 = new int[6];
        __obj18[0] = 102;
        __obj18[1] = 15;
        __obj18[2] = 31;
        __obj18[3] = 68;
        __obj18[4] = 0;
        __obj18[5] = 0;
        nop6 = __obj18;
        int[] __obj19 = new int[7];
        __obj19[0] = 15;
        __obj19[1] = 31;
        __obj19[2] = 128;
        __obj19[3] = 0;
        __obj19[4] = 0;
        __obj19[5] = 0;
        __obj19[6] = 0;
        nop7 = __obj19;
        int[] __obj20 = new int[8];
        __obj20[0] = 15;
        __obj20[1] = 31;
        __obj20[2] = 132;
        __obj20[3] = 0;
        __obj20[4] = 0;
        __obj20[5] = 0;
        __obj20[6] = 0;
        __obj20[7] = 0;
        nop8 = __obj20;
        int[] __obj21 = new int[9];
        __obj21[0] = 102;
        __obj21[1] = 15;
        __obj21[2] = 31;
        __obj21[3] = 132;
        __obj21[4] = 0;
        __obj21[5] = 0;
        __obj21[6] = 0;
        __obj21[7] = 0;
        __obj21[8] = 0;
        nop9 = __obj21;
        int[] __obj22 = new int[10];
        __obj22[0] = 102;
        __obj22[1] = 102;
        __obj22[2] = 15;
        __obj22[3] = 31;
        __obj22[4] = 132;
        __obj22[5] = 0;
        __obj22[6] = 0;
        __obj22[7] = 0;
        __obj22[8] = 0;
        __obj22[9] = 0;
        nop10 = __obj22;
        int[] __obj23 = new int[11];
        __obj23[0] = 102;
        __obj23[1] = 102;
        __obj23[2] = 102;
        __obj23[3] = 15;
        __obj23[4] = 31;
        __obj23[5] = 132;
        __obj23[6] = 0;
        __obj23[7] = 0;
        __obj23[8] = 0;
        __obj23[9] = 0;
        __obj23[10] = 0;
        nop11 = __obj23;
    }

   boolean is64() {
        return cpu == CPU.X86_64;
    }

  private static final int intValue(boolean arg0) {
        return arg0;
    }

  public Assembler(CPU arg0) { // было: <init>
        super();
        _buffer = new CodeBuffer();
        _relocData = new LinkedList();
        cpuInfo = CpuInfo.GENERIC;
        _properties = 0;
        _logger = null;
        cpu = arg0;
    }

  public final int offset() {
        return _buffer.offset();
    }

  public final int codeSize() {
        return _buffer.offset() + trampolineSize();
    }

   int trampolineSize() {
        return _trampolineSize;
    }

  public final byte getByteAt(int arg0) {
        return _buffer.getByteAt(arg0);
    }

  public final short getWordAt(int arg0) {
        return _buffer.getWordAt(arg0);
    }

  public final int getDWordAt(int arg0) {
        return _buffer.getDWordAt(arg0);
    }

  public final long getQWordAt(int arg0) {
        return _buffer.getQWordAt(arg0);
    }

  public final void setByteAt(int arg0, byte arg1) {
        _buffer.setByteAt(arg0, arg1);
    }

  public final void setWordAt(int arg0, short arg1) {
        _buffer.setWordAt(arg0, arg1);
    }

  public final void setDWordAt(int arg0, int arg1) {
        _buffer.setDWordAt(arg0, arg1);
    }

  public final void setQWordAt(int arg0, long arg1) {
        _buffer.setQWordAt(arg0, arg1);
    }

  public final int getInt32At(int arg0) {
        return _buffer.getDWordAt(arg0);
    }

  public final void setInt32At(int arg0, long arg1) {
        _buffer.setDWordAt(arg0, ((int) arg1));
    }

  public final void setVarAt(int arg0, long arg1, boolean arg2, int arg3) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: iload  5
        //      2: tableswitch  default->86, 1->48, 2->59, 3->86, 4->70, 5->86, 6->86, 7->86, 8->80
        //     48: aload_0
        //     49: iload_1
        //     50: lload_2
        //     51: l2i
        //     52: i2b
        //     53: invokevirtual  #176 // jnr.x86asm.Assembler.setByteAt:(IB)V
        //     56: goto  96 (offset +40)
        //     59: aload_0
        //     60: iload_1
        //     61: lload_2
        //     62: l2i
        //     63: i2s
        //     64: invokevirtual  #179 // jnr.x86asm.Assembler.setWordAt:(IS)V
        //     67: goto  96 (offset +29)
        //     70: aload_0
        //     71: iload_1
        //     72: lload_2
        //     73: l2i
        //     74: invokevirtual  #177 // jnr.x86asm.Assembler.setDWordAt:(II)V
        //     77: goto  96 (offset +19)
        //     80: aload_0
        //     81: iload_1
        //     82: lload_2
        //     83: invokevirtual  #178 // jnr.x86asm.Assembler.setQWordAt:(IJ)V
        //     86: new  #33 // java.lang.IllegalArgumentException
        //     89: dup
        //     90: ldc  #24 // 'invalid size'
        //     92: invokespecial  #140 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //     95: athrow
        //     96: return
    }

  final void _emitByte(int arg0) {
        _buffer.emitByte(((byte) arg0));
    }

  final void _emitWord(int arg0) {
        _buffer.emitWord(((short) arg0));
    }

  final void _emitDWord(int arg0) {
        _buffer.emitDWord(arg0);
    }

  final void _emitQWord(long arg0) {
        _buffer.emitQWord(arg0);
    }

  final void _emitInt32(int arg0) {
        _buffer.emitDWord(arg0);
    }

  final void _emitSysInt(long arg0) {
        if (!is64()) {
            _buffer.emitDWord(((int) arg0));
        } else {
            _buffer.emitQWord(arg0);
        }
    }

  final void _emitOpCode(int arg0) {
        if ((arg0 & -16777216) != 0) {
            _emitByte(((byte) ((arg0 & -16777216) >> 24)));
        }
        if ((arg0 & 16711680) != 0) {
            _emitByte(((byte) ((arg0 & 16711680) >> 16)));
        }
        if ((arg0 & 65280) != 0) {
            _emitByte(((byte) ((arg0 & 65280) >> 8)));
        }
        _emitByte(((byte) (arg0 & 255)));
    }

   void _emitSegmentPrefix(Operand arg0) {
        if (arg0.isMem()) {
            SEGMENT var2 = (((Mem) arg0)).segmentPrefix();
            if (var2 != SEGMENT.SEGMENT_NONE) {
                _emitByte(var2.prefix());
            }
        }
    }

   void _emitImmediate(Immediate arg0, int arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: iload_2
        //      1: tableswitch  default->109, 1->48, 2->59, 3->109, 4->70, 5->109, 6->109, 7->109, 8->81
        //     48: aload_0
        //     49: aload_1
        //     50: invokevirtual  #202 // jnr.x86asm.Immediate.byteValue:()B
        //     53: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //     56: goto  119 (offset +63)
        //     59: aload_0
        //     60: aload_1
        //     61: invokevirtual  #206 // jnr.x86asm.Immediate.shortValue:()S
        //     64: invokevirtual  #167 // jnr.x86asm.Assembler._emitWord:(I)V
        //     67: goto  119 (offset +52)
        //     70: aload_0
        //     71: aload_1
        //     72: invokevirtual  #203 // jnr.x86asm.Immediate.intValue:()I
        //     75: invokevirtual  #149 // jnr.x86asm.Assembler._emitDWord:(I)V
        //     78: goto  119 (offset +41)
        //     81: aload_0
        //     82: invokevirtual  #174 // jnr.x86asm.Assembler.is64:()Z
        //     85: ifne  98 (offset +13)
        //     88: new  #33 // java.lang.IllegalArgumentException
        //     91: dup
        //     92: ldc  #14 // '64 bit immediate values not supported for 32bit'
        //     94: invokespecial  #140 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //     97: athrow
        //     98: aload_0
        //     99: aload_1
        //    100: invokevirtual  #204 // jnr.x86asm.Immediate.longValue:()J
        //    103: invokevirtual  #160 // jnr.x86asm.Assembler._emitQWord:(J)V
        //    106: goto  119 (offset +13)
        //    109: new  #33 // java.lang.IllegalArgumentException
        //    112: dup
        //    113: ldc  #21 // 'invalid immediate operand size'
        //    115: invokespecial  #140 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    118: athrow
        //    119: return
    }

   void _emitRexR(int arg0, int arg1, int arg2) {
        boolean __stk1;
        boolean __stk2;
        if (is64()) {
            __stk1 = (arg1 & 8) != 0;
            int var4 = __stk1;
            __stk2 = (arg2 & 8) != 0;
            int var5 = __stk2;
            if (arg0 != 0) {
                _emitByte(64 | arg0 << 3 | intValue(((Boolean) var4)) << 2 | intValue(((Boolean) var5)));
            } else {
                if (var4 != 0) {
                    _emitByte(64 | arg0 << 3 | intValue(((Boolean) var4)) << 2 | intValue(((Boolean) var5)));
                } else {
                    if (var5 != 0) {
                        _emitByte(64 | arg0 << 3 | intValue(((Boolean) var4)) << 2 | intValue(((Boolean) var5)));
                    } else {
                        if ((_properties & 2) != 0) {
                            _emitByte(64 | arg0 << 3 | intValue(((Boolean) var4)) << 2 | intValue(((Boolean) var5)));
                        }
                    }
                }
            }
        }
    }

   void _emitRexR(boolean arg0, int arg1, int arg2) {
        _emitRexR(intValue(arg0), arg1, arg2);
    }

   void _emitRexRM(int arg0, int arg1, Operand arg2) {
        boolean __stk1;
        boolean __stk2;
        int __stk3;
        int __stk4;
        if (is64()) {
            __stk1 = (arg1 & 8) != 0;
            int var4 = __stk1;
            int var5 = 0;
            int var6 = 0;
            if (!arg2.isReg()) {
                if (arg2.isMem()) {
                    __stk3 = ((((Mem) arg2)).index() & 8) == 0 ? 0 : (((Mem) arg2)).index() != 255;
                    var5 = __stk3;
                    __stk4 = ((((Mem) arg2)).base() & 8) == 0 ? 0 : (((Mem) arg2)).base() != 255;
                    var6 = __stk4;
                }
            } else {
                __stk2 = ((((BaseReg) arg2)).code() & 8) != 0;
                var6 = __stk2;
            }
            if (arg0 != 0) {
                _emitByte(64 | arg0 << 3 | intValue(((Boolean) var4)) << 2 | intValue(var5) << 1 | intValue(var6));
            } else {
                if (var4 != 0) {
                    _emitByte(64 | arg0 << 3 | intValue(((Boolean) var4)) << 2 | intValue(var5) << 1 | intValue(var6));
                } else {
                    if (var5 != 0) {
                        _emitByte(64 | arg0 << 3 | intValue(((Boolean) var4)) << 2 | intValue(var5) << 1 | intValue(var6));
                    } else {
                        if (var6 != 0) {
                            _emitByte(64 | arg0 << 3 | intValue(((Boolean) var4)) << 2 | intValue(var5) << 1 | intValue(var6));
                        } else {
                            if ((_properties & 2) != 0) {
                                _emitByte(64 | arg0 << 3 | intValue(((Boolean) var4)) << 2 | intValue(var5) << 1 | intValue(var6));
                            }
                        }
                    }
                }
            }
        }
    }

   void _emitRexRM(boolean arg0, int arg1, Operand arg2) {
        _emitRexRM(intValue(arg0), arg1, arg2);
    }

   void _emitModM(int arg0, Mem arg1, int arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #80 // jnr.x86asm.Assembler.$assertionsDisabled:Z
        //      3: ifne  22 (offset +19)
        //      6: aload_2
        //      7: invokevirtual  #225 // jnr.x86asm.Mem.op:()I
        //     10: iconst_2
        //     11: if_icmpeq  22 (offset +11)
        //     14: new  #31 // java.lang.AssertionError
        //     17: dup
        //     18: invokespecial  #138 // java.lang.AssertionError.<init>:()V
        //     21: athrow
        //     22: aload_2
        //     23: invokevirtual  #218 // jnr.x86asm.Mem.base:()I
        //     26: bipush  7
        //     28: iand
        //     29: istore  4
        //     31: aload_2
        //     32: invokevirtual  #223 // jnr.x86asm.Mem.index:()I
        //     35: bipush  7
        //     37: iand
        //     38: istore  5
        //     40: aload_2
        //     41: invokevirtual  #219 // jnr.x86asm.Mem.displacement:()J
        //     44: lstore  6
        //     46: aload_2
        //     47: invokevirtual  #227 // jnr.x86asm.Mem.shift:()I
        //     50: istore  8
        //     52: aload_2
        //     53: invokevirtual  #220 // jnr.x86asm.Mem.hasBase:()Z
        //     56: ifeq  217 (offset +161)
        //     59: aload_2
        //     60: invokevirtual  #221 // jnr.x86asm.Mem.hasIndex:()Z
        //     63: ifne  217 (offset +154)
        //     66: iload  4
        //     68: iconst_4
        //     69: if_icmpne  148 (offset +79)
        //     72: iconst_0
        //     73: istore  9
        //     75: lload  6
        //     77: lconst_0
        //     78: lcmp
        //     79: ifeq  97 (offset +18)
        //     82: lload  6
        //     84: invokestatic  #252 // jnr.x86asm.Util.isInt8:(J)Z
        //     87: ifeq  94 (offset +7)
        //     90: iconst_1
        //     91: goto  95 (offset +4)
        //     94: iconst_2
        //     95: istore  9
        //     97: aload_0
        //     98: iload  9
        //    100: iload_1
        //    101: iconst_4
        //    102: invokevirtual  #155 // jnr.x86asm.Assembler._emitMod:(III)V
        //    105: aload_0
        //    106: iconst_0
        //    107: iconst_4
        //    108: iconst_4
        //    109: invokevirtual  #166 // jnr.x86asm.Assembler._emitSib:(III)V
        //    112: lload  6
        //    114: lconst_0
        //    115: lcmp
        //    116: ifeq  145 (offset +29)
        //    119: lload  6
        //    121: invokestatic  #252 // jnr.x86asm.Util.isInt8:(J)Z
        //    124: ifeq  138 (offset +14)
        //    127: aload_0
        //    128: lload  6
        //    130: l2i
        //    131: i2b
        //    132: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //    135: goto  145 (offset +10)
        //    138: aload_0
        //    139: lload  6
        //    141: l2i
        //    142: invokevirtual  #152 // jnr.x86asm.Assembler._emitInt32:(I)V
        //    145: goto  648 (offset +503)
        //    148: iload  4
        //    150: iconst_5
        //    151: if_icmpeq  172 (offset +21)
        //    154: lload  6
        //    156: lconst_0
        //    157: lcmp
        //    158: ifne  172 (offset +14)
        //    161: aload_0
        //    162: iconst_0
        //    163: iload_1
        //    164: iload  4
        //    166: invokevirtual  #155 // jnr.x86asm.Assembler._emitMod:(III)V
        //    169: goto  648 (offset +479)
        //    172: lload  6
        //    174: invokestatic  #252 // jnr.x86asm.Util.isInt8:(J)Z
        //    177: ifeq  199 (offset +22)
        //    180: aload_0
        //    181: iconst_1
        //    182: iload_1
        //    183: iload  4
        //    185: invokevirtual  #155 // jnr.x86asm.Assembler._emitMod:(III)V
        //    188: aload_0
        //    189: lload  6
        //    191: l2i
        //    192: i2b
        //    193: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //    196: goto  648 (offset +452)
        //    199: aload_0
        //    200: iconst_2
        //    201: iload_1
        //    202: iload  4
        //    204: invokevirtual  #155 // jnr.x86asm.Assembler._emitMod:(III)V
        //    207: aload_0
        //    208: lload  6
        //    210: l2i
        //    211: invokevirtual  #152 // jnr.x86asm.Assembler._emitInt32:(I)V
        //    214: goto  648 (offset +434)
        //    217: aload_2
        //    218: invokevirtual  #220 // jnr.x86asm.Mem.hasBase:()Z
        //    221: ifeq  327 (offset +106)
        //    224: aload_2
        //    225: invokevirtual  #221 // jnr.x86asm.Mem.hasIndex:()Z
        //    228: ifeq  327 (offset +99)
        //    231: iload  4
        //    233: iconst_5
        //    234: if_icmpeq  264 (offset +30)
        //    237: lload  6
        //    239: lconst_0
        //    240: lcmp
        //    241: ifne  264 (offset +23)
        //    244: aload_0
        //    245: iconst_0
        //    246: iload_1
        //    247: iconst_4
        //    248: invokevirtual  #155 // jnr.x86asm.Assembler._emitMod:(III)V
        //    251: aload_0
        //    252: iload  8
        //    254: iload  5
        //    256: iload  4
        //    258: invokevirtual  #166 // jnr.x86asm.Assembler._emitSib:(III)V
        //    261: goto  648 (offset +387)
        //    264: lload  6
        //    266: invokestatic  #252 // jnr.x86asm.Util.isInt8:(J)Z
        //    269: ifeq  300 (offset +31)
        //    272: aload_0
        //    273: iconst_1
        //    274: iload_1
        //    275: iconst_4
        //    276: invokevirtual  #155 // jnr.x86asm.Assembler._emitMod:(III)V
        //    279: aload_0
        //    280: iload  8
        //    282: iload  5
        //    284: iload  4
        //    286: invokevirtual  #166 // jnr.x86asm.Assembler._emitSib:(III)V
        //    289: aload_0
        //    290: lload  6
        //    292: l2i
        //    293: i2b
        //    294: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //    297: goto  648 (offset +351)
        //    300: aload_0
        //    301: iconst_2
        //    302: iload_1
        //    303: iconst_4
        //    304: invokevirtual  #155 // jnr.x86asm.Assembler._emitMod:(III)V
        //    307: aload_0
        //    308: iload  8
        //    310: iload  5
        //    312: iload  4
        //    314: invokevirtual  #166 // jnr.x86asm.Assembler._emitSib:(III)V
        //    317: aload_0
        //    318: lload  6
        //    320: l2i
        //    321: invokevirtual  #152 // jnr.x86asm.Assembler._emitInt32:(I)V
        //    324: goto  648 (offset +324)
        //    327: aload_0
        //    328: invokevirtual  #174 // jnr.x86asm.Assembler.is64:()Z
        //    331: ifne  488 (offset +157)
        //    334: aload_2
        //    335: invokevirtual  #221 // jnr.x86asm.Mem.hasIndex:()Z
        //    338: ifeq  360 (offset +22)
        //    341: aload_0
        //    342: iconst_0
        //    343: iload_1
        //    344: iconst_4
        //    345: invokevirtual  #155 // jnr.x86asm.Assembler._emitMod:(III)V
        //    348: aload_0
        //    349: iload  8
        //    351: iload  5
        //    353: iconst_5
        //    354: invokevirtual  #166 // jnr.x86asm.Assembler._emitSib:(III)V
        //    357: goto  367 (offset +10)
        //    360: aload_0
        //    361: iconst_0
        //    362: iload_1
        //    363: iconst_5
        //    364: invokevirtual  #155 // jnr.x86asm.Assembler._emitMod:(III)V
        //    367: aload_2
        //    368: invokevirtual  #222 // jnr.x86asm.Mem.hasLabel:()Z
        //    371: ifeq  473 (offset +102)
        //    374: aload_2
        //    375: invokevirtual  #224 // jnr.x86asm.Mem.label:()Ljnr/x86asm/Label;
        //    378: astore  9
        //    380: aload_0
        //    381: getfield  #86 // jnr.x86asm.Assembler._relocData:Ljava/util/List;
        //    384: invokeinterface  #260 // java.util.List.size:()I, count 1
        //    389: istore  10
        //    391: lload  6
        //    393: lstore  11
        //    395: aload  9
        //    397: invokevirtual  #211 // jnr.x86asm.Label.isBound:()Z
        //    400: ifeq  422 (offset +22)
        //    403: lload  11
        //    405: aload  9
        //    407: invokevirtual  #213 // jnr.x86asm.Label.position:()I
        //    410: i2l
        //    411: ladd
        //    412: lstore  11
        //    414: aload_0
        //    415: iconst_0
        //    416: invokevirtual  #152 // jnr.x86asm.Assembler._emitInt32:(I)V
        //    419: goto  439 (offset +20)
        //    422: aload_0
        //    423: aload  9
        //    425: bipush  -4
        //    427: iload_3
        //    428: isub
        //    429: i2l
        //    430: iconst_4
        //    431: invokevirtual  #150 // jnr.x86asm.Assembler._emitDisplacement:(Ljnr/x86asm/Label;JI)Ljnr/x86asm/LinkData;
        //    434: iload  10
        //    436: putfield  #129 // jnr.x86asm.LinkData.relocId:I
        //    439: new  #62 // jnr.x86asm.RelocData
        //    442: dup
        //    443: getstatic  #136 // jnr.x86asm.RelocData$Type.RELATIVE_TO_ABSOLUTE:Ljnr/x86asm/RelocData$Type;
        //    446: iconst_4
        //    447: aload_0
        //    448: invokevirtual  #175 // jnr.x86asm.Assembler.offset:()I
        //    451: lload  11
        //    453: invokespecial  #246 // jnr.x86asm.RelocData.<init>:(Ljnr/x86asm/RelocData$Type;IIJ)V
        //    456: astore  13
        //    458: aload_0
        //    459: getfield  #86 // jnr.x86asm.Assembler._relocData:Ljava/util/List;
        //    462: aload  13
        //    464: invokeinterface  #258 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    469: pop
        //    470: goto  648 (offset +178)
        //    473: aload_0
        //    474: aload_2
        //    475: invokevirtual  #228 // jnr.x86asm.Mem.target:()J
        //    478: lload  6
        //    480: ladd
        //    481: l2i
        //    482: invokevirtual  #152 // jnr.x86asm.Assembler._emitInt32:(I)V
        //    485: goto  648 (offset +163)
        //    488: aload_2
        //    489: invokevirtual  #222 // jnr.x86asm.Mem.hasLabel:()Z
        //    492: ifeq  581 (offset +89)
        //    495: aload_2
        //    496: invokevirtual  #224 // jnr.x86asm.Mem.label:()Ljnr/x86asm/Label;
        //    499: astore  9
        //    501: aload_2
        //    502: invokevirtual  #221 // jnr.x86asm.Mem.hasIndex:()Z
        //    505: ifeq  518 (offset +13)
        //    508: new  #33 // java.lang.IllegalArgumentException
        //    511: dup
        //    512: ldc  #19 // 'illegal addressing'
        //    514: invokespecial  #140 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    517: athrow
        //    518: aload_0
        //    519: iconst_0
        //    520: iload_1
        //    521: iconst_5
        //    522: invokevirtual  #155 // jnr.x86asm.Assembler._emitMod:(III)V
        //    525: lload  6
        //    527: iconst_4
        //    528: iload_3
        //    529: iadd
        //    530: i2l
        //    531: lsub
        //    532: lstore  6
        //    534: aload  9
        //    536: invokevirtual  #211 // jnr.x86asm.Label.isBound:()Z
        //    539: ifeq  568 (offset +29)
        //    542: lload  6
        //    544: aload_0
        //    545: invokevirtual  #175 // jnr.x86asm.Assembler.offset:()I
        //    548: aload  9
        //    550: invokevirtual  #213 // jnr.x86asm.Label.position:()I
        //    553: isub
        //    554: i2l
        //    555: ladd
        //    556: lstore  6
        //    558: aload_0
        //    559: lload  6
        //    561: l2i
        //    562: invokevirtual  #152 // jnr.x86asm.Assembler._emitInt32:(I)V
        //    565: goto  578 (offset +13)
        //    568: aload_0
        //    569: aload  9
        //    571: lload  6
        //    573: iconst_4
        //    574: invokevirtual  #150 // jnr.x86asm.Assembler._emitDisplacement:(Ljnr/x86asm/Label;JI)Ljnr/x86asm/LinkData;
        //    577: pop
        //    578: goto  648 (offset +70)
        //    581: aload_0
        //    582: iconst_0
        //    583: iload_1
        //    584: iconst_4
        //    585: invokevirtual  #155 // jnr.x86asm.Assembler._emitMod:(III)V
        //    588: aload_2
        //    589: invokevirtual  #221 // jnr.x86asm.Mem.hasIndex:()Z
        //    592: ifeq  607 (offset +15)
        //    595: aload_0
        //    596: iload  8
        //    598: iload  5
        //    600: iconst_5
        //    601: invokevirtual  #166 // jnr.x86asm.Assembler._emitSib:(III)V
        //    604: goto  614 (offset +10)
        //    607: aload_0
        //    608: iconst_0
        //    609: iconst_4
        //    610: iconst_5
        //    611: invokevirtual  #166 // jnr.x86asm.Assembler._emitSib:(III)V
        //    614: aload_2
        //    615: invokevirtual  #228 // jnr.x86asm.Mem.target:()J
        //    618: lload  6
        //    620: ladd
        //    621: lstore  9
        //    623: lload  9
        //    625: ldc2_w  #78 // 4294967295L
        //    628: lcmp
        //    629: ifle  641 (offset +12)
        //    632: aload_0
        //    633: getfield  #84 // jnr.x86asm.Assembler._logger:Ljnr/x86asm/Logger;
        //    636: ldc  #18 // '; Warning: Absolute address truncated to 32 bits\n'
        //    638: invokevirtual  #215 // jnr.x86asm.Logger.log:(Ljava/lang/String;)V
        //    641: aload_0
        //    642: lload  9
        //    644: l2i
        //    645: invokevirtual  #152 // jnr.x86asm.Assembler._emitInt32:(I)V
        //    648: return
    }

   void _emitX86Inl(int arg0, boolean arg1, boolean arg2, int arg3) {
        _emitX86Inl(arg0, arg1, intValue(arg2), arg3);
    }

   void _emitX86Inl(int arg0, boolean arg1, int arg2, int arg3) {
        if (arg1) {
            _emitByte(102);
        }
        if ((arg0 & -16777216) != 0) {
            _emitByte((arg0 & -16777216) >> 24);
        }
        if (is64()) {
            _emitRexR(arg2, 0, arg3);
        }
        if ((arg0 & 16711680) != 0) {
            _emitByte((arg0 & 16711680) >> 16);
        }
        if ((arg0 & 65280) != 0) {
            _emitByte((arg0 & 65280) >> 8);
        }
        _emitByte((arg0 & 255) + (arg3 & 7));
    }

   void _emitModRM(int arg0, Operand arg1, int arg2) {
        if ($assertionsDisabled) {
            if (arg1.op() != 1) {
                _emitModM(arg0, ((Mem) arg1), arg2);
            } else {
                _emitModR(arg0, (((BaseReg) arg1)).code());
            }
            return;
        } else {
            if (arg1.op() == 1) {
                if (arg1.op() != 1) {
                    _emitModM(arg0, ((Mem) arg1), arg2);
                } else {
                    _emitModR(arg0, (((BaseReg) arg1)).code());
                }
                return;
            } else {
                if (arg1.op() == 2) {
                    if (arg1.op() != 1) {
                        _emitModM(arg0, ((Mem) arg1), arg2);
                    } else {
                        _emitModR(arg0, (((BaseReg) arg1)).code());
                    }
                    return;
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

   void _emitMod(int arg0, int arg1, int arg2) {
        _emitByte(((byte) ((arg0 & 3) << 6 | (arg1 & 7) << 3 | arg2 & 7)));
    }

   void _emitSib(int arg0, int arg1, int arg2) {
        _emitByte(((byte) ((arg0 & 3) << 6 | (arg1 & 7) << 3 | arg2 & 7)));
    }

   void _emitModR(int arg0, int arg1) {
        _emitMod(3, arg0, arg1);
    }

   void _emitModR(int arg0, BaseReg arg1) {
        _emitMod(3, arg0, arg1.code());
    }

   void _emitX86RM(int arg0, boolean arg1, boolean arg2, int arg3, Operand arg4, int arg5) {
        _emitX86RM(arg0, arg1, intValue(arg2), arg3, arg4, arg5);
    }

   void _emitX86RM(int arg0, boolean arg1, int arg2, int arg3, Operand arg4, int arg5) {
        if (arg1) {
            _emitByte(102);
        }
        _emitSegmentPrefix(arg4);
        if ((arg0 & -16777216) != 0) {
            _emitByte((arg0 & -16777216) >> 24);
        }
        if (is64()) {
            _emitRexRM(arg2, arg3, arg4);
        }
        if ((arg0 & 16711680) != 0) {
            _emitByte(((byte) ((arg0 & 16711680) >> 16)));
        }
        if ((arg0 & 65280) != 0) {
            _emitByte(((byte) ((arg0 & 65280) >> 8)));
        }
        _emitByte(((byte) (arg0 & 255)));
        _emitModRM(arg3, arg4, arg5);
    }

   void _emitX86(INST_CODE arg0, Operand arg1, Operand arg2, Operand arg3) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: invokestatic  #209 // jnr.x86asm.InstructionDescription.find:(Ljnr/x86asm/INST_CODE;)Ljnr/x86asm/InstructionDescription;
        //      4: astore  5
        //      6: getstatic  #101 // jnr.x86asm.Assembler$1.$SwitchMap$jnr$x86asm$InstructionGroup:[I
        //      9: aload  5
        //     11: getfield  #121 // jnr.x86asm.InstructionDescription.group:Ljnr/x86asm/InstructionGroup;
        //     14: invokevirtual  #210 // jnr.x86asm.InstructionGroup.ordinal:()I
        //     17: iaload
        //     18: tableswitch  default->7913, 1->200, 2->210, 3->573, 4->635, 5->767, 6->930, 7->1052, 8->1094, 9->1554, 10->1690, 11->2007, 12->2231, 13->2287, 14->2321, 15->2880, 16->3075, 17->3227, 18->3287, 19->3355, 20->3468, 21->3564, 22->3590, 23->3665, 24->3756, 25->3858, 26->4025, 27->4176, 28->4512, 29->4688, 30->4784, 31->5039, 32->5089, 33->5160, 34->5208, 35->5387, 36->5830, 37->5962, 38->6424, 39->6474, 40->6825, 41->7366, 42->7853
        //    200: aload_0
        //    201: aload  5
        //    203: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //    206: invokevirtual  #159 // jnr.x86asm.Assembler._emitOpCode:(I)V
        //    209: return
        //    210: aload  5
        //    212: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //    215: istore  6
        //    217: aload  5
        //    219: getfield  #126 // jnr.x86asm.InstructionDescription.opCodeR:I
        //    222: istore  7
        //    224: aload_2
        //    225: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //    228: ifeq  283 (offset +55)
        //    231: aload_3
        //    232: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //    235: ifeq  283 (offset +48)
        //    238: aload_0
        //    239: iload  6
        //    241: aload_3
        //    242: iconst_0
        //    243: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //    246: ifne  253 (offset +7)
        //    249: iconst_1
        //    250: goto  254 (offset +4)
        //    253: iconst_0
        //    254: invokestatic  #173 // jnr.x86asm.Assembler.intValue:(Z)I
        //    257: iadd
        //    258: aload_3
        //    259: bipush  16
        //    261: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //    264: aload_3
        //    265: bipush  48
        //    267: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //    270: aload_3
        //    271: checkcast  #61 // jnr.x86asm.Register
        //    274: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //    277: aload_2
        //    278: iconst_0
        //    279: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //    282: return
        //    283: aload_2
        //    284: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //    287: ifeq  344 (offset +57)
        //    290: aload_3
        //    291: invokevirtual  #236 // jnr.x86asm.Operand.isRegMem:()Z
        //    294: ifeq  344 (offset +50)
        //    297: aload_0
        //    298: iload  6
        //    300: iconst_2
        //    301: iadd
        //    302: aload_2
        //    303: iconst_0
        //    304: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //    307: ifne  314 (offset +7)
        //    310: iconst_1
        //    311: goto  315 (offset +4)
        //    314: iconst_0
        //    315: invokestatic  #173 // jnr.x86asm.Assembler.intValue:(Z)I
        //    318: iadd
        //    319: aload_2
        //    320: bipush  16
        //    322: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //    325: aload_2
        //    326: bipush  48
        //    328: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //    331: aload_2
        //    332: checkcast  #61 // jnr.x86asm.Register
        //    335: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //    338: aload_3
        //    339: iconst_0
        //    340: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //    343: return
        //    344: aload_2
        //    345: iconst_0
        //    346: invokevirtual  #235 // jnr.x86asm.Operand.isRegIndex:(I)Z
        //    349: ifeq  444 (offset +95)
        //    352: aload_3
        //    353: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //    356: ifeq  444 (offset +88)
        //    359: aload_2
        //    360: bipush  16
        //    362: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //    365: ifeq  377 (offset +12)
        //    368: aload_0
        //    369: bipush  102
        //    371: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //    374: goto  392 (offset +18)
        //    377: aload_2
        //    378: bipush  48
        //    380: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //    383: ifeq  392 (offset +9)
        //    386: aload_0
        //    387: bipush  72
        //    389: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //    392: aload_0
        //    393: iload  7
        //    395: iconst_3
        //    396: ishl
        //    397: iconst_4
        //    398: aload_2
        //    399: iconst_0
        //    400: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //    403: ifne  410 (offset +7)
        //    406: iconst_1
        //    407: goto  411 (offset +4)
        //    410: iconst_0
        //    411: invokestatic  #173 // jnr.x86asm.Assembler.intValue:(Z)I
        //    414: iadd
        //    415: ior
        //    416: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //    419: aload_0
        //    420: aload_3
        //    421: checkcast  #51 // jnr.x86asm.Immediate
        //    424: aload_2
        //    425: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //    428: iconst_4
        //    429: if_icmpgt  439 (offset +10)
        //    432: aload_2
        //    433: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //    436: goto  440 (offset +4)
        //    439: iconst_4
        //    440: invokevirtual  #151 // jnr.x86asm.Assembler._emitImmediate:(Ljnr/x86asm/Immediate;I)V
        //    443: return
        //    444: aload_2
        //    445: invokevirtual  #236 // jnr.x86asm.Operand.isRegMem:()Z
        //    448: ifeq  7913 (offset +7465)
        //    451: aload_3
        //    452: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //    455: ifeq  7913 (offset +7458)
        //    458: aload_3
        //    459: checkcast  #51 // jnr.x86asm.Immediate
        //    462: astore  8
        //    464: aload  8
        //    466: invokevirtual  #208 // jnr.x86asm.Immediate.value:()J
        //    469: invokestatic  #252 // jnr.x86asm.Util.isInt8:(J)Z
        //    472: ifeq  479 (offset +7)
        //    475: iconst_1
        //    476: goto  495 (offset +19)
        //    479: aload_2
        //    480: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //    483: iconst_4
        //    484: if_icmpgt  494 (offset +10)
        //    487: aload_2
        //    488: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //    491: goto  495 (offset +4)
        //    494: iconst_4
        //    495: istore  9
        //    497: aload_0
        //    498: aload  5
        //    500: getfield  #125 // jnr.x86asm.InstructionDescription.opCode2:I
        //    503: aload_2
        //    504: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //    507: iconst_1
        //    508: if_icmpeq  525 (offset +17)
        //    511: iload  9
        //    513: iconst_1
        //    514: if_icmpeq  521 (offset +7)
        //    517: iconst_1
        //    518: goto  526 (offset +8)
        //    521: iconst_3
        //    522: goto  526 (offset +4)
        //    525: iconst_0
        //    526: iadd
        //    527: aload_2
        //    528: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //    531: iconst_2
        //    532: if_icmpne  539 (offset +7)
        //    535: iconst_1
        //    536: goto  540 (offset +4)
        //    539: iconst_0
        //    540: aload_2
        //    541: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //    544: bipush  8
        //    546: if_icmpne  553 (offset +7)
        //    549: iconst_1
        //    550: goto  554 (offset +4)
        //    553: iconst_0
        //    554: iload  7
        //    556: aload_2
        //    557: iload  9
        //    559: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //    562: aload_0
        //    563: aload_3
        //    564: checkcast  #51 // jnr.x86asm.Immediate
        //    567: iload  9
        //    569: invokevirtual  #151 // jnr.x86asm.Assembler._emitImmediate:(Ljnr/x86asm/Immediate;I)V
        //    572: return
        //    573: aload_2
        //    574: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //    577: ifeq  7913 (offset +7336)
        //    580: aload_2
        //    581: checkcast  #61 // jnr.x86asm.Register
        //    584: astore  6
        //    586: aload_0
        //    587: invokevirtual  #174 // jnr.x86asm.Assembler.is64:()Z
        //    590: ifeq  618 (offset +28)
        //    593: aload_0
        //    594: aload  6
        //    596: invokevirtual  #245 // jnr.x86asm.Register.type:()I
        //    599: bipush  48
        //    601: if_icmpne  608 (offset +7)
        //    604: iconst_1
        //    605: goto  609 (offset +4)
        //    608: iconst_0
        //    609: iconst_1
        //    610: aload  6
        //    612: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //    615: invokevirtual  #162 // jnr.x86asm.Assembler._emitRexR:(ZII)V
        //    618: aload_0
        //    619: bipush  15
        //    621: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //    624: aload_0
        //    625: iconst_1
        //    626: aload  6
        //    628: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //    631: invokevirtual  #157 // jnr.x86asm.Assembler._emitModR:(II)V
        //    634: return
        //    635: aload_2
        //    636: invokevirtual  #236 // jnr.x86asm.Operand.isRegMem:()Z
        //    639: ifeq  690 (offset +51)
        //    642: aload_3
        //    643: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //    646: ifeq  690 (offset +44)
        //    649: aload_2
        //    650: astore  6
        //    652: aload_3
        //    653: checkcast  #61 // jnr.x86asm.Register
        //    656: astore  7
        //    658: aload_0
        //    659: aload  5
        //    661: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //    664: aload  7
        //    666: bipush  16
        //    668: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //    671: aload  7
        //    673: bipush  48
        //    675: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //    678: aload  7
        //    680: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //    683: aload  6
        //    685: iconst_0
        //    686: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //    689: return
        //    690: aload_2
        //    691: invokevirtual  #236 // jnr.x86asm.Operand.isRegMem:()Z
        //    694: ifeq  7913 (offset +7219)
        //    697: aload_3
        //    698: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //    701: ifeq  7913 (offset +7212)
        //    704: aload_2
        //    705: astore  6
        //    707: aload_3
        //    708: checkcast  #51 // jnr.x86asm.Immediate
        //    711: astore  7
        //    713: aload_0
        //    714: aload  5
        //    716: getfield  #125 // jnr.x86asm.InstructionDescription.opCode2:I
        //    719: aload  7
        //    721: invokevirtual  #207 // jnr.x86asm.Immediate.size:()I
        //    724: iconst_2
        //    725: if_icmpne  732 (offset +7)
        //    728: iconst_1
        //    729: goto  733 (offset +4)
        //    732: iconst_0
        //    733: aload  7
        //    735: invokevirtual  #207 // jnr.x86asm.Immediate.size:()I
        //    738: bipush  8
        //    740: if_icmpne  747 (offset +7)
        //    743: iconst_1
        //    744: goto  748 (offset +4)
        //    747: iconst_0
        //    748: aload  5
        //    750: getfield  #126 // jnr.x86asm.InstructionDescription.opCodeR:I
        //    753: aload  6
        //    755: iconst_1
        //    756: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //    759: aload_0
        //    760: aload  7
        //    762: iconst_1
        //    763: invokevirtual  #151 // jnr.x86asm.Assembler._emitImmediate:(Ljnr/x86asm/Immediate;I)V
        //    766: return
        //    767: aload_2
        //    768: aload_0
        //    769: invokevirtual  #174 // jnr.x86asm.Assembler.is64:()Z
        //    772: ifeq  780 (offset +8)
        //    775: bipush  48
        //    777: goto  782 (offset +5)
        //    780: bipush  32
        //    782: invokevirtual  #237 // jnr.x86asm.Operand.isRegMem:(I)Z
        //    785: ifeq  805 (offset +20)
        //    788: aload_2
        //    789: astore  6
        //    791: aload_0
        //    792: sipush  255
        //    795: iconst_0
        //    796: iconst_0
        //    797: iconst_2
        //    798: aload  6
        //    800: iconst_0
        //    801: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //    804: return
        //    805: aload_2
        //    806: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //    809: ifeq  838 (offset +29)
        //    812: aload_2
        //    813: checkcast  #51 // jnr.x86asm.Immediate
        //    816: astore  6
        //    818: aload_0
        //    819: sipush  232
        //    822: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //    825: aload_0
        //    826: getstatic  #127 // jnr.x86asm.InstructionGroup.I_CALL:Ljnr/x86asm/InstructionGroup;
        //    829: aload  6
        //    831: invokevirtual  #208 // jnr.x86asm.Immediate.value:()J
        //    834: invokevirtual  #153 // jnr.x86asm.Assembler._emitJmpOrCallReloc:(Ljnr/x86asm/InstructionGroup;J)V
        //    837: return
        //    838: aload_2
        //    839: invokevirtual  #230 // jnr.x86asm.Operand.isLabel:()Z
        //    842: ifeq  7913 (offset +7071)
        //    845: aload_2
        //    846: checkcast  #54 // jnr.x86asm.Label
        //    849: astore  6
        //    851: aload  6
        //    853: invokevirtual  #211 // jnr.x86asm.Label.isBound:()Z
        //    856: ifeq  911 (offset +55)
        //    859: iconst_5
        //    860: istore  7
        //    862: aload  6
        //    864: invokevirtual  #213 // jnr.x86asm.Label.position:()I
        //    867: aload_0
        //    868: invokevirtual  #175 // jnr.x86asm.Assembler.offset:()I
        //    871: isub
        //    872: istore  8
        //    874: getstatic  #80 // jnr.x86asm.Assembler.$assertionsDisabled:Z
        //    877: ifne  893 (offset +16)
        //    880: iload  8
        //    882: ifle  893 (offset +11)
        //    885: new  #31 // java.lang.AssertionError
        //    888: dup
        //    889: invokespecial  #138 // java.lang.AssertionError.<init>:()V
        //    892: athrow
        //    893: aload_0
        //    894: sipush  232
        //    897: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //    900: aload_0
        //    901: iload  8
        //    903: iconst_5
        //    904: isub
        //    905: invokevirtual  #152 // jnr.x86asm.Assembler._emitInt32:(I)V
        //    908: goto  929 (offset +21)
        //    911: aload_0
        //    912: sipush  232
        //    915: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //    918: aload_0
        //    919: aload  6
        //    921: ldc2_w  #70 // -4L
        //    924: iconst_4
        //    925: invokevirtual  #150 // jnr.x86asm.Assembler._emitDisplacement:(Ljnr/x86asm/Label;JI)Ljnr/x86asm/LinkData;
        //    928: pop
        //    929: return
        //    930: aload_2
        //    931: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //    934: ifeq  7913 (offset +6979)
        //    937: aload_3
        //    938: invokevirtual  #236 // jnr.x86asm.Operand.isRegMem:()Z
        //    941: ifeq  7913 (offset +6972)
        //    944: aload_2
        //    945: checkcast  #61 // jnr.x86asm.Register
        //    948: astore  6
        //    950: aload_3
        //    951: astore  7
        //    953: getstatic  #80 // jnr.x86asm.Assembler.$assertionsDisabled:Z
        //    956: ifne  987 (offset +31)
        //    959: aload  6
        //    961: invokevirtual  #245 // jnr.x86asm.Register.type:()I
        //    964: bipush  32
        //    966: if_icmpeq  987 (offset +21)
        //    969: aload  6
        //    971: invokevirtual  #245 // jnr.x86asm.Register.type:()I
        //    974: bipush  48
        //    976: if_icmpeq  987 (offset +11)
        //    979: new  #31 // java.lang.AssertionError
        //    982: dup
        //    983: invokespecial  #138 // java.lang.AssertionError.<init>:()V
        //    986: athrow
        //    987: aload_0
        //    988: aload  5
        //    990: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //    993: aload  7
        //    995: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //    998: iconst_1
        //    999: if_icmpeq  1006 (offset +7)
        //   1002: iconst_1
        //   1003: goto  1007 (offset +4)
        //   1006: iconst_0
        //   1007: invokestatic  #173 // jnr.x86asm.Assembler.intValue:(Z)I
        //   1010: iadd
        //   1011: aload  7
        //   1013: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   1016: iconst_2
        //   1017: if_icmpne  1024 (offset +7)
        //   1020: iconst_1
        //   1021: goto  1025 (offset +4)
        //   1024: iconst_0
        //   1025: aload  6
        //   1027: invokevirtual  #245 // jnr.x86asm.Register.type:()I
        //   1030: bipush  8
        //   1032: if_icmpne  1039 (offset +7)
        //   1035: iconst_1
        //   1036: goto  1040 (offset +4)
        //   1039: iconst_0
        //   1040: aload  6
        //   1042: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //   1045: aload  7
        //   1047: iconst_0
        //   1048: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //   1051: return
        //   1052: aload_2
        //   1053: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //   1056: ifeq  7913 (offset +6857)
        //   1059: aload_3
        //   1060: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //   1063: ifeq  7913 (offset +6850)
        //   1066: aload_0
        //   1067: sipush  200
        //   1070: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   1073: aload_0
        //   1074: aload_2
        //   1075: checkcast  #51 // jnr.x86asm.Immediate
        //   1078: iconst_2
        //   1079: invokevirtual  #151 // jnr.x86asm.Assembler._emitImmediate:(Ljnr/x86asm/Immediate;I)V
        //   1082: aload_0
        //   1083: aload_3
        //   1084: checkcast  #51 // jnr.x86asm.Immediate
        //   1087: iconst_1
        //   1088: invokevirtual  #151 // jnr.x86asm.Assembler._emitImmediate:(Ljnr/x86asm/Immediate;I)V
        //   1091: goto  7913 (offset +6822)
        //   1094: aload_2
        //   1095: invokevirtual  #236 // jnr.x86asm.Operand.isRegMem:()Z
        //   1098: ifeq  1178 (offset +80)
        //   1101: aload_3
        //   1102: invokevirtual  #232 // jnr.x86asm.Operand.isNone:()Z
        //   1105: ifeq  1178 (offset +73)
        //   1108: aload  4
        //   1110: invokevirtual  #232 // jnr.x86asm.Operand.isNone:()Z
        //   1113: ifeq  1178 (offset +65)
        //   1116: aload_2
        //   1117: astore  6
        //   1119: aload_0
        //   1120: sipush  246
        //   1123: aload  6
        //   1125: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   1128: iconst_1
        //   1129: if_icmpeq  1136 (offset +7)
        //   1132: iconst_1
        //   1133: goto  1137 (offset +4)
        //   1136: iconst_0
        //   1137: invokestatic  #173 // jnr.x86asm.Assembler.intValue:(Z)I
        //   1140: iadd
        //   1141: aload  6
        //   1143: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   1146: iconst_2
        //   1147: if_icmpne  1154 (offset +7)
        //   1150: iconst_1
        //   1151: goto  1155 (offset +4)
        //   1154: iconst_0
        //   1155: aload  6
        //   1157: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   1160: bipush  8
        //   1162: if_icmpne  1169 (offset +7)
        //   1165: iconst_1
        //   1166: goto  1170 (offset +4)
        //   1169: iconst_0
        //   1170: iconst_5
        //   1171: aload  6
        //   1173: iconst_0
        //   1174: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //   1177: return
        //   1178: aload_2
        //   1179: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   1182: ifeq  1401 (offset +219)
        //   1185: aload_3
        //   1186: invokevirtual  #232 // jnr.x86asm.Operand.isNone:()Z
        //   1189: ifne  1401 (offset +212)
        //   1192: aload  4
        //   1194: invokevirtual  #232 // jnr.x86asm.Operand.isNone:()Z
        //   1197: ifeq  1401 (offset +204)
        //   1200: aload_2
        //   1201: checkcast  #61 // jnr.x86asm.Register
        //   1204: astore  6
        //   1206: getstatic  #80 // jnr.x86asm.Assembler.$assertionsDisabled:Z
        //   1209: ifne  1230 (offset +21)
        //   1212: aload  6
        //   1214: bipush  16
        //   1216: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //   1219: ifeq  1230 (offset +11)
        //   1222: new  #31 // java.lang.AssertionError
        //   1225: dup
        //   1226: invokespecial  #138 // java.lang.AssertionError.<init>:()V
        //   1229: athrow
        //   1230: aload_3
        //   1231: invokevirtual  #236 // jnr.x86asm.Operand.isRegMem:()Z
        //   1234: ifeq  1270 (offset +36)
        //   1237: aload_3
        //   1238: astore  7
        //   1240: aload_0
        //   1241: sipush  4015
        //   1244: aload  6
        //   1246: bipush  16
        //   1248: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //   1251: aload  6
        //   1253: bipush  48
        //   1255: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //   1258: aload  6
        //   1260: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //   1263: aload  7
        //   1265: iconst_0
        //   1266: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //   1269: return
        //   1270: aload_3
        //   1271: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //   1274: ifeq  1398 (offset +124)
        //   1277: aload_3
        //   1278: checkcast  #51 // jnr.x86asm.Immediate
        //   1281: astore  7
        //   1283: aload  7
        //   1285: invokevirtual  #208 // jnr.x86asm.Immediate.value:()J
        //   1288: invokestatic  #252 // jnr.x86asm.Util.isInt8:(J)Z
        //   1291: ifeq  1343 (offset +52)
        //   1294: aload  7
        //   1296: invokevirtual  #205 // jnr.x86asm.Immediate.relocMode:()Ljnr/x86asm/RELOC_MODE;
        //   1299: getstatic  #130 // jnr.x86asm.RELOC_MODE.RELOC_NONE:Ljnr/x86asm/RELOC_MODE;
        //   1302: if_acmpne  1343 (offset +41)
        //   1305: aload_0
        //   1306: bipush  107
        //   1308: aload  6
        //   1310: bipush  16
        //   1312: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //   1315: aload  6
        //   1317: bipush  48
        //   1319: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //   1322: aload  6
        //   1324: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //   1327: aload  6
        //   1329: iconst_1
        //   1330: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //   1333: aload_0
        //   1334: aload  7
        //   1336: iconst_1
        //   1337: invokevirtual  #151 // jnr.x86asm.Assembler._emitImmediate:(Ljnr/x86asm/Immediate;I)V
        //   1340: goto  1397 (offset +57)
        //   1343: aload  6
        //   1345: bipush  16
        //   1347: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //   1350: ifeq  1357 (offset +7)
        //   1353: iconst_2
        //   1354: goto  1358 (offset +4)
        //   1357: iconst_4
        //   1358: istore  8
        //   1360: aload_0
        //   1361: bipush  105
        //   1363: aload  6
        //   1365: bipush  16
        //   1367: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //   1370: aload  6
        //   1372: bipush  48
        //   1374: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //   1377: aload  6
        //   1379: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //   1382: aload  6
        //   1384: iload  8
        //   1386: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //   1389: aload_0
        //   1390: aload  7
        //   1392: iload  8
        //   1394: invokevirtual  #151 // jnr.x86asm.Assembler._emitImmediate:(Ljnr/x86asm/Immediate;I)V
        //   1397: return
        //   1398: goto  7913 (offset +6515)
        //   1401: aload_2
        //   1402: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   1405: ifeq  7913 (offset +6508)
        //   1408: aload_3
        //   1409: invokevirtual  #236 // jnr.x86asm.Operand.isRegMem:()Z
        //   1412: ifeq  7913 (offset +6501)
        //   1415: aload  4
        //   1417: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //   1420: ifeq  7913 (offset +6493)
        //   1423: aload_2
        //   1424: checkcast  #61 // jnr.x86asm.Register
        //   1427: astore  6
        //   1429: aload_3
        //   1430: astore  7
        //   1432: aload  4
        //   1434: checkcast  #51 // jnr.x86asm.Immediate
        //   1437: astore  8
        //   1439: aload  8
        //   1441: invokevirtual  #208 // jnr.x86asm.Immediate.value:()J
        //   1444: invokestatic  #252 // jnr.x86asm.Util.isInt8:(J)Z
        //   1447: ifeq  1499 (offset +52)
        //   1450: aload  8
        //   1452: invokevirtual  #205 // jnr.x86asm.Immediate.relocMode:()Ljnr/x86asm/RELOC_MODE;
        //   1455: getstatic  #130 // jnr.x86asm.RELOC_MODE.RELOC_NONE:Ljnr/x86asm/RELOC_MODE;
        //   1458: if_acmpne  1499 (offset +41)
        //   1461: aload_0
        //   1462: bipush  107
        //   1464: aload  6
        //   1466: bipush  16
        //   1468: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //   1471: aload  6
        //   1473: bipush  48
        //   1475: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //   1478: aload  6
        //   1480: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //   1483: aload  7
        //   1485: iconst_1
        //   1486: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //   1489: aload_0
        //   1490: aload  8
        //   1492: iconst_1
        //   1493: invokevirtual  #151 // jnr.x86asm.Assembler._emitImmediate:(Ljnr/x86asm/Immediate;I)V
        //   1496: goto  1553 (offset +57)
        //   1499: aload  6
        //   1501: bipush  16
        //   1503: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //   1506: ifeq  1513 (offset +7)
        //   1509: iconst_2
        //   1510: goto  1514 (offset +4)
        //   1513: iconst_4
        //   1514: istore  9
        //   1516: aload_0
        //   1517: bipush  105
        //   1519: aload  6
        //   1521: bipush  16
        //   1523: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //   1526: aload  6
        //   1528: bipush  48
        //   1530: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //   1533: aload  6
        //   1535: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //   1538: aload  7
        //   1540: iload  9
        //   1542: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //   1545: aload_0
        //   1546: aload  8
        //   1548: iload  9
        //   1550: invokevirtual  #151 // jnr.x86asm.Assembler._emitImmediate:(Ljnr/x86asm/Immediate;I)V
        //   1553: return
        //   1554: aload_2
        //   1555: invokevirtual  #236 // jnr.x86asm.Operand.isRegMem:()Z
        //   1558: ifeq  7913 (offset +6355)
        //   1561: aload_2
        //   1562: astore  6
        //   1564: aload_0
        //   1565: invokevirtual  #174 // jnr.x86asm.Assembler.is64:()Z
        //   1568: ifne  1625 (offset +57)
        //   1571: aload  6
        //   1573: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   1576: ifeq  1625 (offset +49)
        //   1579: aload  6
        //   1581: bipush  16
        //   1583: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   1586: ifne  1599 (offset +13)
        //   1589: aload  6
        //   1591: bipush  32
        //   1593: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   1596: ifeq  1625 (offset +29)
        //   1599: aload_0
        //   1600: aload  5
        //   1602: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   1605: aload  6
        //   1607: bipush  16
        //   1609: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   1612: iconst_0
        //   1613: aload  6
        //   1615: checkcast  #44 // jnr.x86asm.BaseReg
        //   1618: invokevirtual  #181 // jnr.x86asm.BaseReg.code:()I
        //   1621: invokevirtual  #168 // jnr.x86asm.Assembler._emitX86Inl:(IZII)V
        //   1624: return
        //   1625: aload_0
        //   1626: aload  5
        //   1628: getfield  #125 // jnr.x86asm.InstructionDescription.opCode2:I
        //   1631: aload  6
        //   1633: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   1636: iconst_1
        //   1637: if_icmpeq  1644 (offset +7)
        //   1640: iconst_1
        //   1641: goto  1645 (offset +4)
        //   1644: iconst_0
        //   1645: invokestatic  #173 // jnr.x86asm.Assembler.intValue:(Z)I
        //   1648: iadd
        //   1649: aload  6
        //   1651: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   1654: iconst_2
        //   1655: if_icmpne  1662 (offset +7)
        //   1658: iconst_1
        //   1659: goto  1663 (offset +4)
        //   1662: iconst_0
        //   1663: aload  6
        //   1665: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   1668: bipush  8
        //   1670: if_icmpne  1677 (offset +7)
        //   1673: iconst_1
        //   1674: goto  1678 (offset +4)
        //   1677: iconst_0
        //   1678: aload  5
        //   1680: getfield  #126 // jnr.x86asm.InstructionDescription.opCodeR:I
        //   1683: aload  6
        //   1685: iconst_0
        //   1686: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //   1689: return
        //   1690: aload_2
        //   1691: invokevirtual  #230 // jnr.x86asm.Operand.isLabel:()Z
        //   1694: ifeq  7913 (offset +6219)
        //   1697: aload_2
        //   1698: checkcast  #54 // jnr.x86asm.Label
        //   1701: astore  6
        //   1703: aload_1
        //   1704: invokevirtual  #201 // jnr.x86asm.INST_CODE.ordinal:()I
        //   1707: getstatic  #116 // jnr.x86asm.INST_CODE.INST_J_SHORT:Ljnr/x86asm/INST_CODE;
        //   1710: invokevirtual  #201 // jnr.x86asm.INST_CODE.ordinal:()I
        //   1713: if_icmplt  1733 (offset +20)
        //   1716: aload_1
        //   1717: invokevirtual  #201 // jnr.x86asm.INST_CODE.ordinal:()I
        //   1720: getstatic  #115 // jnr.x86asm.INST_CODE.INST_JMP_SHORT:Ljnr/x86asm/INST_CODE;
        //   1723: invokevirtual  #201 // jnr.x86asm.INST_CODE.ordinal:()I
        //   1726: if_icmpgt  1733 (offset +7)
        //   1729: iconst_1
        //   1730: goto  1734 (offset +4)
        //   1733: iconst_0
        //   1734: istore  7
        //   1736: aload_3
        //   1737: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //   1740: ifeq  1757 (offset +17)
        //   1743: aload_3
        //   1744: checkcast  #51 // jnr.x86asm.Immediate
        //   1747: invokevirtual  #208 // jnr.x86asm.Immediate.value:()J
        //   1750: l2i
        //   1751: invokestatic  #200 // jnr.x86asm.HINT.valueOf:(I)Ljnr/x86asm/HINT;
        //   1754: goto  1760 (offset +6)
        //   1757: getstatic  #110 // jnr.x86asm.HINT.HINT_NONE:Ljnr/x86asm/HINT;
        //   1760: astore  8
        //   1762: aload  8
        //   1764: getstatic  #112 // jnr.x86asm.HINT.HINT_TAKEN:Ljnr/x86asm/HINT;
        //   1767: if_acmpeq  1787 (offset +20)
        //   1770: aload  8
        //   1772: getstatic  #111 // jnr.x86asm.HINT.HINT_NOT_TAKEN:Ljnr/x86asm/HINT;
        //   1775: if_acmpne  1796 (offset +21)
        //   1778: aload_0
        //   1779: getfield  #85 // jnr.x86asm.Assembler._properties:I
        //   1782: iconst_4
        //   1783: iand
        //   1784: ifeq  1796 (offset +12)
        //   1787: aload_0
        //   1788: aload  8
        //   1790: invokevirtual  #199 // jnr.x86asm.HINT.value:()I
        //   1793: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   1796: aload  6
        //   1798: invokevirtual  #211 // jnr.x86asm.Label.isBound:()Z
        //   1801: ifeq  1937 (offset +136)
        //   1804: iconst_2
        //   1805: istore  9
        //   1807: bipush  6
        //   1809: istore  10
        //   1811: aload  6
        //   1813: invokevirtual  #213 // jnr.x86asm.Label.position:()I
        //   1816: aload_0
        //   1817: invokevirtual  #175 // jnr.x86asm.Assembler.offset:()I
        //   1820: isub
        //   1821: istore  11
        //   1823: getstatic  #80 // jnr.x86asm.Assembler.$assertionsDisabled:Z
        //   1826: ifne  1842 (offset +16)
        //   1829: iload  11
        //   1831: ifle  1842 (offset +11)
        //   1834: new  #31 // java.lang.AssertionError
        //   1837: dup
        //   1838: invokespecial  #138 // java.lang.AssertionError.<init>:()V
        //   1841: athrow
        //   1842: iload  11
        //   1844: iconst_2
        //   1845: isub
        //   1846: i2l
        //   1847: invokestatic  #252 // jnr.x86asm.Util.isInt8:(J)Z
        //   1850: ifeq  1881 (offset +31)
        //   1853: aload_0
        //   1854: bipush  112
        //   1856: aload  5
        //   1858: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   1861: sipush  255
        //   1864: iand
        //   1865: ior
        //   1866: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   1869: aload_0
        //   1870: iload  11
        //   1872: iconst_2
        //   1873: isub
        //   1874: i2b
        //   1875: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   1878: goto  1934 (offset +56)
        //   1881: iload  7
        //   1883: ifeq  1902 (offset +19)
        //   1886: aload_0
        //   1887: getfield  #84 // jnr.x86asm.Assembler._logger:Ljnr/x86asm/Logger;
        //   1890: ifnull  1902 (offset +12)
        //   1893: aload_0
        //   1894: getfield  #84 // jnr.x86asm.Assembler._logger:Ljnr/x86asm/Logger;
        //   1897: ldc  #16 // '; WARNING: Emitting long conditional jump, but short jump instruction forced!'
        //   1899: invokevirtual  #215 // jnr.x86asm.Logger.log:(Ljava/lang/String;)V
        //   1902: aload_0
        //   1903: bipush  15
        //   1905: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   1908: aload_0
        //   1909: sipush  128
        //   1912: aload  5
        //   1914: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   1917: sipush  255
        //   1920: iand
        //   1921: ior
        //   1922: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   1925: aload_0
        //   1926: iload  11
        //   1928: bipush  6
        //   1930: isub
        //   1931: invokevirtual  #152 // jnr.x86asm.Assembler._emitInt32:(I)V
        //   1934: goto  2006 (offset +72)
        //   1937: iload  7
        //   1939: ifeq  1972 (offset +33)
        //   1942: aload_0
        //   1943: bipush  112
        //   1945: aload  5
        //   1947: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   1950: sipush  255
        //   1953: iand
        //   1954: ior
        //   1955: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   1958: aload_0
        //   1959: aload  6
        //   1961: ldc2_w  #72 // -1L
        //   1964: iconst_1
        //   1965: invokevirtual  #150 // jnr.x86asm.Assembler._emitDisplacement:(Ljnr/x86asm/Label;JI)Ljnr/x86asm/LinkData;
        //   1968: pop
        //   1969: goto  2006 (offset +37)
        //   1972: aload_0
        //   1973: bipush  15
        //   1975: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   1978: aload_0
        //   1979: sipush  128
        //   1982: aload  5
        //   1984: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   1987: sipush  255
        //   1990: iand
        //   1991: ior
        //   1992: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   1995: aload_0
        //   1996: aload  6
        //   1998: ldc2_w  #70 // -4L
        //   2001: iconst_4
        //   2002: invokevirtual  #150 // jnr.x86asm.Assembler._emitDisplacement:(Ljnr/x86asm/Label;JI)Ljnr/x86asm/LinkData;
        //   2005: pop
        //   2006: return
        //   2007: aload_2
        //   2008: invokevirtual  #236 // jnr.x86asm.Operand.isRegMem:()Z
        //   2011: ifeq  2031 (offset +20)
        //   2014: aload_2
        //   2015: astore  6
        //   2017: aload_0
        //   2018: sipush  255
        //   2021: iconst_0
        //   2022: iconst_0
        //   2023: iconst_4
        //   2024: aload  6
        //   2026: iconst_0
        //   2027: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //   2030: return
        //   2031: aload_2
        //   2032: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //   2035: ifeq  2064 (offset +29)
        //   2038: aload_2
        //   2039: checkcast  #51 // jnr.x86asm.Immediate
        //   2042: astore  6
        //   2044: aload_0
        //   2045: sipush  233
        //   2048: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   2051: aload_0
        //   2052: getstatic  #128 // jnr.x86asm.InstructionGroup.I_JMP:Ljnr/x86asm/InstructionGroup;
        //   2055: aload  6
        //   2057: invokevirtual  #208 // jnr.x86asm.Immediate.value:()J
        //   2060: invokevirtual  #153 // jnr.x86asm.Assembler._emitJmpOrCallReloc:(Ljnr/x86asm/InstructionGroup;J)V
        //   2063: return
        //   2064: aload_2
        //   2065: invokevirtual  #230 // jnr.x86asm.Operand.isLabel:()Z
        //   2068: ifeq  7913 (offset +5845)
        //   2071: aload_2
        //   2072: checkcast  #54 // jnr.x86asm.Label
        //   2075: astore  6
        //   2077: aload_1
        //   2078: getstatic  #115 // jnr.x86asm.INST_CODE.INST_JMP_SHORT:Ljnr/x86asm/INST_CODE;
        //   2081: if_acmpne  2088 (offset +7)
        //   2084: iconst_1
        //   2085: goto  2089 (offset +4)
        //   2088: iconst_0
        //   2089: istore  7
        //   2091: aload  6
        //   2093: invokevirtual  #211 // jnr.x86asm.Label.isBound:()Z
        //   2096: ifeq  2186 (offset +90)
        //   2099: iconst_2
        //   2100: istore  8
        //   2102: iconst_5
        //   2103: istore  9
        //   2105: aload  6
        //   2107: invokevirtual  #213 // jnr.x86asm.Label.position:()I
        //   2110: aload_0
        //   2111: invokevirtual  #175 // jnr.x86asm.Assembler.offset:()I
        //   2114: isub
        //   2115: istore  10
        //   2117: iload  10
        //   2119: iconst_2
        //   2120: isub
        //   2121: i2l
        //   2122: invokestatic  #252 // jnr.x86asm.Util.isInt8:(J)Z
        //   2125: ifeq  2147 (offset +22)
        //   2128: aload_0
        //   2129: sipush  235
        //   2132: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   2135: aload_0
        //   2136: iload  10
        //   2138: iconst_2
        //   2139: isub
        //   2140: i2b
        //   2141: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   2144: goto  2183 (offset +39)
        //   2147: iload  7
        //   2149: ifeq  2168 (offset +19)
        //   2152: aload_0
        //   2153: getfield  #84 // jnr.x86asm.Assembler._logger:Ljnr/x86asm/Logger;
        //   2156: ifnull  2168 (offset +12)
        //   2159: aload_0
        //   2160: getfield  #84 // jnr.x86asm.Assembler._logger:Ljnr/x86asm/Logger;
        //   2163: ldc  #17 // '; WARNING: Emitting long jump, but short jump instruction forced!'
        //   2165: invokevirtual  #215 // jnr.x86asm.Logger.log:(Ljava/lang/String;)V
        //   2168: aload_0
        //   2169: sipush  233
        //   2172: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   2175: aload_0
        //   2176: iload  10
        //   2178: iconst_5
        //   2179: isub
        //   2180: invokevirtual  #152 // jnr.x86asm.Assembler._emitInt32:(I)V
        //   2183: goto  2230 (offset +47)
        //   2186: iload  7
        //   2188: ifeq  2212 (offset +24)
        //   2191: aload_0
        //   2192: sipush  235
        //   2195: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   2198: aload_0
        //   2199: aload  6
        //   2201: ldc2_w  #72 // -1L
        //   2204: iconst_1
        //   2205: invokevirtual  #150 // jnr.x86asm.Assembler._emitDisplacement:(Ljnr/x86asm/Label;JI)Ljnr/x86asm/LinkData;
        //   2208: pop
        //   2209: goto  2230 (offset +21)
        //   2212: aload_0
        //   2213: sipush  233
        //   2216: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   2219: aload_0
        //   2220: aload  6
        //   2222: ldc2_w  #70 // -4L
        //   2225: iconst_4
        //   2226: invokevirtual  #150 // jnr.x86asm.Assembler._emitDisplacement:(Ljnr/x86asm/Label;JI)Ljnr/x86asm/LinkData;
        //   2229: pop
        //   2230: return
        //   2231: aload_2
        //   2232: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   2235: ifeq  7913 (offset +5678)
        //   2238: aload_3
        //   2239: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   2242: ifeq  7913 (offset +5671)
        //   2245: aload_2
        //   2246: checkcast  #61 // jnr.x86asm.Register
        //   2249: astore  6
        //   2251: aload_3
        //   2252: checkcast  #58 // jnr.x86asm.Mem
        //   2255: astore  7
        //   2257: aload_0
        //   2258: sipush  141
        //   2261: aload  6
        //   2263: bipush  16
        //   2265: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //   2268: aload  6
        //   2270: bipush  48
        //   2272: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //   2275: aload  6
        //   2277: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //   2280: aload  7
        //   2282: iconst_0
        //   2283: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //   2286: return
        //   2287: aload_2
        //   2288: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   2291: ifeq  7913 (offset +5622)
        //   2294: aload_0
        //   2295: aload  5
        //   2297: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   2300: iconst_0
        //   2301: aload  5
        //   2303: getfield  #125 // jnr.x86asm.InstructionDescription.opCode2:I
        //   2306: i2b
        //   2307: aload  5
        //   2309: getfield  #126 // jnr.x86asm.InstructionDescription.opCodeR:I
        //   2312: aload_2
        //   2313: checkcast  #58 // jnr.x86asm.Mem
        //   2316: iconst_0
        //   2317: invokevirtual  #170 // jnr.x86asm.Assembler._emitX86RM:(IZIILjnr/x86asm/Operand;I)V
        //   2320: return
        //   2321: aload_2
        //   2322: astore  6
        //   2324: aload_3
        //   2325: astore  7
        //   2327: aload  6
        //   2329: invokevirtual  #239 // jnr.x86asm.Operand.op:()I
        //   2332: iconst_4
        //   2333: ishl
        //   2334: aload  7
        //   2336: invokevirtual  #239 // jnr.x86asm.Operand.op:()I
        //   2339: ior
        //   2340: lookupswitch  default->2877, 17->2392, 18->2445, 19->2549, 33->2682, 35->2786
        //   2392: getstatic  #80 // jnr.x86asm.Assembler.$assertionsDisabled:Z
        //   2395: ifne  2445 (offset +50)
        //   2398: aload  7
        //   2400: iconst_0
        //   2401: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   2404: ifne  2445 (offset +41)
        //   2407: aload  7
        //   2409: bipush  16
        //   2411: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   2414: ifne  2445 (offset +31)
        //   2417: aload  7
        //   2419: bipush  32
        //   2421: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   2424: ifne  2445 (offset +21)
        //   2427: aload  7
        //   2429: bipush  48
        //   2431: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   2434: ifne  2445 (offset +11)
        //   2437: new  #31 // java.lang.AssertionError
        //   2440: dup
        //   2441: invokespecial  #138 // java.lang.AssertionError.<init>:()V
        //   2444: athrow
        //   2445: getstatic  #80 // jnr.x86asm.Assembler.$assertionsDisabled:Z
        //   2448: ifne  2498 (offset +50)
        //   2451: aload  6
        //   2453: iconst_0
        //   2454: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   2457: ifne  2498 (offset +41)
        //   2460: aload  6
        //   2462: bipush  16
        //   2464: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   2467: ifne  2498 (offset +31)
        //   2470: aload  6
        //   2472: bipush  32
        //   2474: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   2477: ifne  2498 (offset +21)
        //   2480: aload  6
        //   2482: bipush  48
        //   2484: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   2487: ifne  2498 (offset +11)
        //   2490: new  #31 // java.lang.AssertionError
        //   2493: dup
        //   2494: invokespecial  #138 // java.lang.AssertionError.<init>:()V
        //   2497: athrow
        //   2498: aload_0
        //   2499: sipush  138
        //   2502: aload  6
        //   2504: iconst_0
        //   2505: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   2508: ifne  2515 (offset +7)
        //   2511: iconst_1
        //   2512: goto  2516 (offset +4)
        //   2515: iconst_0
        //   2516: invokestatic  #173 // jnr.x86asm.Assembler.intValue:(Z)I
        //   2519: iadd
        //   2520: aload  6
        //   2522: bipush  16
        //   2524: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   2527: aload  6
        //   2529: bipush  48
        //   2531: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   2534: aload  6
        //   2536: checkcast  #61 // jnr.x86asm.Register
        //   2539: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //   2542: aload  7
        //   2544: iconst_0
        //   2545: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //   2548: return
        //   2549: aload_3
        //   2550: checkcast  #51 // jnr.x86asm.Immediate
        //   2553: astore  8
        //   2555: aload  6
        //   2557: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   2560: istore  9
        //   2562: aload_0
        //   2563: invokevirtual  #174 // jnr.x86asm.Assembler.is64:()Z
        //   2566: ifeq  2629 (offset +63)
        //   2569: iload  9
        //   2571: bipush  8
        //   2573: if_icmpne  2629 (offset +56)
        //   2576: aload  8
        //   2578: invokevirtual  #208 // jnr.x86asm.Immediate.value:()J
        //   2581: invokestatic  #251 // jnr.x86asm.Util.isInt32:(J)Z
        //   2584: ifeq  2629 (offset +45)
        //   2587: aload  8
        //   2589: invokevirtual  #205 // jnr.x86asm.Immediate.relocMode:()Ljnr/x86asm/RELOC_MODE;
        //   2592: getstatic  #130 // jnr.x86asm.RELOC_MODE.RELOC_NONE:Ljnr/x86asm/RELOC_MODE;
        //   2595: if_acmpne  2629 (offset +34)
        //   2598: aload_0
        //   2599: sipush  199
        //   2602: aload  6
        //   2604: bipush  16
        //   2606: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   2609: aload  6
        //   2611: bipush  48
        //   2613: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   2616: iconst_0
        //   2617: aload  6
        //   2619: iconst_0
        //   2620: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //   2623: iconst_4
        //   2624: istore  9
        //   2626: goto  2673 (offset +47)
        //   2629: aload_0
        //   2630: aload  6
        //   2632: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   2635: iconst_1
        //   2636: if_icmpne  2645 (offset +9)
        //   2639: sipush  176
        //   2642: goto  2648 (offset +6)
        //   2645: sipush  184
        //   2648: aload  6
        //   2650: bipush  16
        //   2652: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   2655: aload  6
        //   2657: bipush  48
        //   2659: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   2662: aload  6
        //   2664: checkcast  #61 // jnr.x86asm.Register
        //   2667: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //   2670: invokevirtual  #169 // jnr.x86asm.Assembler._emitX86Inl:(IZZI)V
        //   2673: aload_0
        //   2674: aload  8
        //   2676: iload  9
        //   2678: invokevirtual  #151 // jnr.x86asm.Assembler._emitImmediate:(Ljnr/x86asm/Immediate;I)V
        //   2681: return
        //   2682: getstatic  #80 // jnr.x86asm.Assembler.$assertionsDisabled:Z
        //   2685: ifne  2735 (offset +50)
        //   2688: aload  7
        //   2690: iconst_0
        //   2691: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   2694: ifne  2735 (offset +41)
        //   2697: aload  7
        //   2699: bipush  16
        //   2701: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   2704: ifne  2735 (offset +31)
        //   2707: aload  7
        //   2709: bipush  32
        //   2711: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   2714: ifne  2735 (offset +21)
        //   2717: aload  7
        //   2719: bipush  48
        //   2721: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   2724: ifne  2735 (offset +11)
        //   2727: new  #31 // java.lang.AssertionError
        //   2730: dup
        //   2731: invokespecial  #138 // java.lang.AssertionError.<init>:()V
        //   2734: athrow
        //   2735: aload_0
        //   2736: sipush  136
        //   2739: aload  7
        //   2741: iconst_0
        //   2742: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   2745: ifne  2752 (offset +7)
        //   2748: iconst_1
        //   2749: goto  2753 (offset +4)
        //   2752: iconst_0
        //   2753: invokestatic  #173 // jnr.x86asm.Assembler.intValue:(Z)I
        //   2756: iadd
        //   2757: aload  7
        //   2759: bipush  16
        //   2761: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   2764: aload  7
        //   2766: bipush  48
        //   2768: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   2771: aload  7
        //   2773: checkcast  #61 // jnr.x86asm.Register
        //   2776: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //   2779: aload  6
        //   2781: iconst_0
        //   2782: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //   2785: return
        //   2786: aload  6
        //   2788: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   2791: iconst_4
        //   2792: if_icmpgt  2803 (offset +11)
        //   2795: aload  6
        //   2797: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   2800: goto  2804 (offset +4)
        //   2803: iconst_4
        //   2804: istore  8
        //   2806: aload_0
        //   2807: sipush  198
        //   2810: aload  6
        //   2812: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   2815: iconst_1
        //   2816: if_icmpeq  2823 (offset +7)
        //   2819: iconst_1
        //   2820: goto  2824 (offset +4)
        //   2823: iconst_0
        //   2824: invokestatic  #173 // jnr.x86asm.Assembler.intValue:(Z)I
        //   2827: iadd
        //   2828: aload  6
        //   2830: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   2833: iconst_2
        //   2834: if_icmpne  2841 (offset +7)
        //   2837: iconst_1
        //   2838: goto  2842 (offset +4)
        //   2841: iconst_0
        //   2842: aload  6
        //   2844: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   2847: bipush  8
        //   2849: if_icmpne  2856 (offset +7)
        //   2852: iconst_1
        //   2853: goto  2857 (offset +4)
        //   2856: iconst_0
        //   2857: iconst_0
        //   2858: aload  6
        //   2860: iload  8
        //   2862: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //   2865: aload_0
        //   2866: aload  7
        //   2868: checkcast  #51 // jnr.x86asm.Immediate
        //   2871: iload  8
        //   2873: invokevirtual  #151 // jnr.x86asm.Assembler._emitImmediate:(Ljnr/x86asm/Immediate;I)V
        //   2876: return
        //   2877: goto  7913 (offset +5036)
        //   2880: aload_2
        //   2881: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   2884: ifeq  2894 (offset +10)
        //   2887: aload_3
        //   2888: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //   2891: ifne  2908 (offset +17)
        //   2894: aload_2
        //   2895: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //   2898: ifeq  7913 (offset +5015)
        //   2901: aload_3
        //   2902: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   2905: ifeq  7913 (offset +5008)
        //   2908: aload_2
        //   2909: invokevirtual  #239 // jnr.x86asm.Operand.op:()I
        //   2912: iconst_1
        //   2913: if_icmpne  2920 (offset +7)
        //   2916: iconst_1
        //   2917: goto  2921 (offset +4)
        //   2920: iconst_0
        //   2921: istore  6
        //   2923: iload  6
        //   2925: ifne  2934 (offset +9)
        //   2928: sipush  160
        //   2931: goto  2937 (offset +6)
        //   2934: sipush  162
        //   2937: istore  7
        //   2939: iload  6
        //   2941: ifne  2948 (offset +7)
        //   2944: aload_2
        //   2945: goto  2949 (offset +4)
        //   2948: aload_3
        //   2949: checkcast  #61 // jnr.x86asm.Register
        //   2952: astore  8
        //   2954: iload  6
        //   2956: ifne  2963 (offset +7)
        //   2959: aload_3
        //   2960: goto  2964 (offset +4)
        //   2963: aload_2
        //   2964: checkcast  #51 // jnr.x86asm.Immediate
        //   2967: astore  9
        //   2969: aload  8
        //   2971: invokevirtual  #242 // jnr.x86asm.Register.index:()I
        //   2974: ifeq  2987 (offset +13)
        //   2977: new  #34 // java.lang.IllegalStateException
        //   2980: dup
        //   2981: ldc  #29 // 'reg.index() != 0'
        //   2983: invokespecial  #141 // java.lang.IllegalStateException.<init>:(Ljava/lang/String;)V
        //   2986: athrow
        //   2987: aload  8
        //   2989: bipush  16
        //   2991: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //   2994: ifeq  3003 (offset +9)
        //   2997: aload_0
        //   2998: bipush  102
        //   3000: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   3003: aload_0
        //   3004: invokevirtual  #174 // jnr.x86asm.Assembler.is64:()Z
        //   3007: ifeq  3031 (offset +24)
        //   3010: aload_0
        //   3011: aload  8
        //   3013: invokevirtual  #244 // jnr.x86asm.Register.size:()I
        //   3016: bipush  8
        //   3018: if_icmpne  3025 (offset +7)
        //   3021: iconst_1
        //   3022: goto  3026 (offset +4)
        //   3025: iconst_0
        //   3026: iconst_0
        //   3027: iconst_0
        //   3028: invokevirtual  #162 // jnr.x86asm.Assembler._emitRexR:(ZII)V
        //   3031: aload_0
        //   3032: iload  7
        //   3034: aload  8
        //   3036: invokevirtual  #244 // jnr.x86asm.Register.size:()I
        //   3039: iconst_1
        //   3040: if_icmpeq  3047 (offset +7)
        //   3043: iconst_1
        //   3044: goto  3048 (offset +4)
        //   3047: iconst_0
        //   3048: invokestatic  #173 // jnr.x86asm.Assembler.intValue:(Z)I
        //   3051: iadd
        //   3052: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   3055: aload_0
        //   3056: aload  9
        //   3058: aload_0
        //   3059: invokevirtual  #174 // jnr.x86asm.Assembler.is64:()Z
        //   3062: ifeq  3070 (offset +8)
        //   3065: bipush  8
        //   3067: goto  3071 (offset +4)
        //   3070: iconst_4
        //   3071: invokevirtual  #151 // jnr.x86asm.Assembler._emitImmediate:(Ljnr/x86asm/Immediate;I)V
        //   3074: return
        //   3075: aload_2
        //   3076: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   3079: ifeq  7913 (offset +4834)
        //   3082: aload_3
        //   3083: invokevirtual  #236 // jnr.x86asm.Operand.isRegMem:()Z
        //   3086: ifeq  7913 (offset +4827)
        //   3089: aload_2
        //   3090: checkcast  #61 // jnr.x86asm.Register
        //   3093: checkcast  #61 // jnr.x86asm.Register
        //   3096: astore  6
        //   3098: aload_3
        //   3099: astore  7
        //   3101: aload  6
        //   3103: iconst_0
        //   3104: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //   3107: ifeq  3120 (offset +13)
        //   3110: new  #33 // java.lang.IllegalArgumentException
        //   3113: dup
        //   3114: ldc  #25 // 'not gpb'
        //   3116: invokespecial  #140 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   3119: athrow
        //   3120: aload  7
        //   3122: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   3125: iconst_1
        //   3126: if_icmpeq  3148 (offset +22)
        //   3129: aload  7
        //   3131: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   3134: iconst_2
        //   3135: if_icmpeq  3148 (offset +13)
        //   3138: new  #33 // java.lang.IllegalArgumentException
        //   3141: dup
        //   3142: ldc  #30 // 'src.size !=1 && src.size != 2'
        //   3144: invokespecial  #140 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   3147: athrow
        //   3148: aload  7
        //   3150: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   3153: iconst_2
        //   3154: if_icmpne  3177 (offset +23)
        //   3157: aload  6
        //   3159: bipush  16
        //   3161: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //   3164: ifeq  3177 (offset +13)
        //   3167: new  #33 // java.lang.IllegalArgumentException
        //   3170: dup
        //   3171: ldc  #26 // 'not gpw'
        //   3173: invokespecial  #140 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   3176: athrow
        //   3177: aload_0
        //   3178: aload  5
        //   3180: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   3183: aload  7
        //   3185: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   3188: iconst_1
        //   3189: if_icmpeq  3196 (offset +7)
        //   3192: iconst_1
        //   3193: goto  3197 (offset +4)
        //   3196: iconst_0
        //   3197: invokestatic  #173 // jnr.x86asm.Assembler.intValue:(Z)I
        //   3200: iadd
        //   3201: aload  6
        //   3203: bipush  16
        //   3205: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //   3208: aload  6
        //   3210: bipush  48
        //   3212: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //   3215: aload  6
        //   3217: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //   3220: aload  7
        //   3222: iconst_0
        //   3223: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //   3226: return
        //   3227: aload_0
        //   3228: invokevirtual  #174 // jnr.x86asm.Assembler.is64:()Z
        //   3231: ifne  3244 (offset +13)
        //   3234: new  #34 // java.lang.IllegalStateException
        //   3237: dup
        //   3238: ldc  #20 // 'illegal instruction'
        //   3240: invokespecial  #141 // java.lang.IllegalStateException.<init>:(Ljava/lang/String;)V
        //   3243: athrow
        //   3244: aload_2
        //   3245: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   3248: ifeq  7913 (offset +4665)
        //   3251: aload_3
        //   3252: invokevirtual  #236 // jnr.x86asm.Operand.isRegMem:()Z
        //   3255: ifeq  7913 (offset +4658)
        //   3258: aload_2
        //   3259: checkcast  #61 // jnr.x86asm.Register
        //   3262: checkcast  #61 // jnr.x86asm.Register
        //   3265: astore  6
        //   3267: aload_3
        //   3268: astore  7
        //   3270: aload_0
        //   3271: bipush  99
        //   3273: iconst_0
        //   3274: iconst_1
        //   3275: aload  6
        //   3277: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //   3280: aload  7
        //   3282: iconst_0
        //   3283: invokevirtual  #170 // jnr.x86asm.Assembler._emitX86RM:(IZIILjnr/x86asm/Operand;I)V
        //   3286: return
        //   3287: aload_2
        //   3288: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //   3291: ifeq  3355 (offset +64)
        //   3294: aload_2
        //   3295: checkcast  #51 // jnr.x86asm.Immediate
        //   3298: checkcast  #51 // jnr.x86asm.Immediate
        //   3301: astore  6
        //   3303: aload  6
        //   3305: invokevirtual  #208 // jnr.x86asm.Immediate.value:()J
        //   3308: invokestatic  #252 // jnr.x86asm.Util.isInt8:(J)Z
        //   3311: ifeq  3341 (offset +30)
        //   3314: aload  6
        //   3316: invokevirtual  #205 // jnr.x86asm.Immediate.relocMode:()Ljnr/x86asm/RELOC_MODE;
        //   3319: getstatic  #130 // jnr.x86asm.RELOC_MODE.RELOC_NONE:Ljnr/x86asm/RELOC_MODE;
        //   3322: if_acmpne  3341 (offset +19)
        //   3325: aload_0
        //   3326: bipush  106
        //   3328: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   3331: aload_0
        //   3332: aload  6
        //   3334: iconst_1
        //   3335: invokevirtual  #151 // jnr.x86asm.Assembler._emitImmediate:(Ljnr/x86asm/Immediate;I)V
        //   3338: goto  3354 (offset +16)
        //   3341: aload_0
        //   3342: bipush  104
        //   3344: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   3347: aload_0
        //   3348: aload  6
        //   3350: iconst_4
        //   3351: invokevirtual  #151 // jnr.x86asm.Assembler._emitImmediate:(Ljnr/x86asm/Immediate;I)V
        //   3354: return
        //   3355: aload_2
        //   3356: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   3359: ifeq  3430 (offset +71)
        //   3362: getstatic  #80 // jnr.x86asm.Assembler.$assertionsDisabled:Z
        //   3365: ifne  3406 (offset +41)
        //   3368: aload_2
        //   3369: bipush  16
        //   3371: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   3374: ifne  3406 (offset +32)
        //   3377: aload_2
        //   3378: aload_0
        //   3379: invokevirtual  #174 // jnr.x86asm.Assembler.is64:()Z
        //   3382: ifeq  3390 (offset +8)
        //   3385: bipush  48
        //   3387: goto  3392 (offset +5)
        //   3390: bipush  32
        //   3392: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   3395: ifne  3406 (offset +11)
        //   3398: new  #31 // java.lang.AssertionError
        //   3401: dup
        //   3402: invokespecial  #138 // java.lang.AssertionError.<init>:()V
        //   3405: athrow
        //   3406: aload_0
        //   3407: aload  5
        //   3409: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   3412: aload_2
        //   3413: bipush  16
        //   3415: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   3418: iconst_0
        //   3419: aload_2
        //   3420: checkcast  #61 // jnr.x86asm.Register
        //   3423: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //   3426: invokevirtual  #168 // jnr.x86asm.Assembler._emitX86Inl:(IZII)V
        //   3429: return
        //   3430: aload_2
        //   3431: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   3434: ifeq  7913 (offset +4479)
        //   3437: aload_0
        //   3438: aload  5
        //   3440: getfield  #125 // jnr.x86asm.InstructionDescription.opCode2:I
        //   3443: aload_2
        //   3444: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   3447: iconst_2
        //   3448: if_icmpne  3455 (offset +7)
        //   3451: iconst_1
        //   3452: goto  3456 (offset +4)
        //   3455: iconst_0
        //   3456: iconst_0
        //   3457: aload  5
        //   3459: getfield  #126 // jnr.x86asm.InstructionDescription.opCodeR:I
        //   3462: aload_2
        //   3463: iconst_0
        //   3464: invokevirtual  #170 // jnr.x86asm.Assembler._emitX86RM:(IZIILjnr/x86asm/Operand;I)V
        //   3467: return
        //   3468: aload_2
        //   3469: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   3472: ifeq  7913 (offset +4441)
        //   3475: aload_3
        //   3476: invokevirtual  #236 // jnr.x86asm.Operand.isRegMem:()Z
        //   3479: ifeq  7913 (offset +4434)
        //   3482: aload_2
        //   3483: checkcast  #61 // jnr.x86asm.Register
        //   3486: checkcast  #61 // jnr.x86asm.Register
        //   3489: astore  6
        //   3491: getstatic  #80 // jnr.x86asm.Assembler.$assertionsDisabled:Z
        //   3494: ifne  3513 (offset +19)
        //   3497: aload  6
        //   3499: invokevirtual  #245 // jnr.x86asm.Register.type:()I
        //   3502: ifne  3513 (offset +11)
        //   3505: new  #31 // java.lang.AssertionError
        //   3508: dup
        //   3509: invokespecial  #138 // java.lang.AssertionError.<init>:()V
        //   3512: athrow
        //   3513: aload_3
        //   3514: astore  7
        //   3516: aload_0
        //   3517: aload  5
        //   3519: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   3522: aload  6
        //   3524: invokevirtual  #245 // jnr.x86asm.Register.type:()I
        //   3527: bipush  16
        //   3529: if_icmpne  3536 (offset +7)
        //   3532: iconst_1
        //   3533: goto  3537 (offset +4)
        //   3536: iconst_0
        //   3537: aload  6
        //   3539: invokevirtual  #245 // jnr.x86asm.Register.type:()I
        //   3542: bipush  48
        //   3544: if_icmpne  3551 (offset +7)
        //   3547: iconst_1
        //   3548: goto  3552 (offset +4)
        //   3551: iconst_0
        //   3552: aload  6
        //   3554: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //   3557: aload  7
        //   3559: iconst_0
        //   3560: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //   3563: return
        //   3564: aload_2
        //   3565: invokevirtual  #236 // jnr.x86asm.Operand.isRegMem:()Z
        //   3568: ifeq  7913 (offset +4345)
        //   3571: aload_2
        //   3572: astore  6
        //   3574: aload_0
        //   3575: aload  5
        //   3577: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   3580: iconst_0
        //   3581: iconst_0
        //   3582: iconst_0
        //   3583: aload  6
        //   3585: iconst_0
        //   3586: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //   3589: return
        //   3590: aload_2
        //   3591: invokevirtual  #236 // jnr.x86asm.Operand.isRegMem:()Z
        //   3594: ifeq  7913 (offset +4319)
        //   3597: aload_2
        //   3598: astore  6
        //   3600: aload_0
        //   3601: aload  5
        //   3603: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   3606: aload  6
        //   3608: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   3611: iconst_1
        //   3612: if_icmpeq  3619 (offset +7)
        //   3615: iconst_1
        //   3616: goto  3620 (offset +4)
        //   3619: iconst_0
        //   3620: invokestatic  #173 // jnr.x86asm.Assembler.intValue:(Z)I
        //   3623: iadd
        //   3624: aload  6
        //   3626: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   3629: iconst_2
        //   3630: if_icmpne  3637 (offset +7)
        //   3633: iconst_1
        //   3634: goto  3638 (offset +4)
        //   3637: iconst_0
        //   3638: aload  6
        //   3640: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   3643: bipush  8
        //   3645: if_icmpne  3652 (offset +7)
        //   3648: iconst_1
        //   3649: goto  3653 (offset +4)
        //   3652: iconst_0
        //   3653: aload  5
        //   3655: getfield  #126 // jnr.x86asm.InstructionDescription.opCodeR:I
        //   3658: aload  6
        //   3660: iconst_0
        //   3661: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //   3664: return
        //   3665: aload_2
        //   3666: invokevirtual  #236 // jnr.x86asm.Operand.isRegMem:()Z
        //   3669: ifeq  7913 (offset +4244)
        //   3672: aload_3
        //   3673: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   3676: ifeq  7913 (offset +4237)
        //   3679: aload_2
        //   3680: astore  6
        //   3682: aload_3
        //   3683: checkcast  #61 // jnr.x86asm.Register
        //   3686: checkcast  #61 // jnr.x86asm.Register
        //   3689: astore  7
        //   3691: aload_0
        //   3692: aload  5
        //   3694: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   3697: aload  7
        //   3699: invokevirtual  #245 // jnr.x86asm.Register.type:()I
        //   3702: ifeq  3709 (offset +7)
        //   3705: iconst_1
        //   3706: goto  3710 (offset +4)
        //   3709: iconst_0
        //   3710: invokestatic  #173 // jnr.x86asm.Assembler.intValue:(Z)I
        //   3713: iadd
        //   3714: aload  7
        //   3716: invokevirtual  #245 // jnr.x86asm.Register.type:()I
        //   3719: bipush  16
        //   3721: if_icmpne  3728 (offset +7)
        //   3724: iconst_1
        //   3725: goto  3729 (offset +4)
        //   3728: iconst_0
        //   3729: aload  7
        //   3731: invokevirtual  #245 // jnr.x86asm.Register.type:()I
        //   3734: bipush  48
        //   3736: if_icmpne  3743 (offset +7)
        //   3739: iconst_1
        //   3740: goto  3744 (offset +4)
        //   3743: iconst_0
        //   3744: aload  7
        //   3746: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //   3749: aload  6
        //   3751: iconst_0
        //   3752: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //   3755: return
        //   3756: aload_2
        //   3757: invokevirtual  #232 // jnr.x86asm.Operand.isNone:()Z
        //   3760: ifeq  3771 (offset +11)
        //   3763: aload_0
        //   3764: sipush  195
        //   3767: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   3770: return
        //   3771: aload_2
        //   3772: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //   3775: ifeq  7913 (offset +4138)
        //   3778: aload_2
        //   3779: checkcast  #51 // jnr.x86asm.Immediate
        //   3782: checkcast  #51 // jnr.x86asm.Immediate
        //   3785: astore  6
        //   3787: getstatic  #80 // jnr.x86asm.Assembler.$assertionsDisabled:Z
        //   3790: ifne  3812 (offset +22)
        //   3793: aload  6
        //   3795: invokevirtual  #208 // jnr.x86asm.Immediate.value:()J
        //   3798: invokestatic  #253 // jnr.x86asm.Util.isUInt16:(J)Z
        //   3801: ifne  3812 (offset +11)
        //   3804: new  #31 // java.lang.AssertionError
        //   3807: dup
        //   3808: invokespecial  #138 // java.lang.AssertionError.<init>:()V
        //   3811: athrow
        //   3812: aload  6
        //   3814: invokevirtual  #208 // jnr.x86asm.Immediate.value:()J
        //   3817: lconst_0
        //   3818: lcmp
        //   3819: ifne  3843 (offset +24)
        //   3822: aload  6
        //   3824: invokevirtual  #205 // jnr.x86asm.Immediate.relocMode:()Ljnr/x86asm/RELOC_MODE;
        //   3827: getstatic  #130 // jnr.x86asm.RELOC_MODE.RELOC_NONE:Ljnr/x86asm/RELOC_MODE;
        //   3830: if_acmpne  3843 (offset +13)
        //   3833: aload_0
        //   3834: sipush  195
        //   3837: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   3840: goto  3857 (offset +17)
        //   3843: aload_0
        //   3844: sipush  194
        //   3847: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   3850: aload_0
        //   3851: aload  6
        //   3853: iconst_2
        //   3854: invokevirtual  #151 // jnr.x86asm.Assembler._emitImmediate:(Ljnr/x86asm/Immediate;I)V
        //   3857: return
        //   3858: aload_2
        //   3859: invokevirtual  #236 // jnr.x86asm.Operand.isRegMem:()Z
        //   3862: ifeq  7913 (offset +4051)
        //   3865: aload_3
        //   3866: iconst_1
        //   3867: invokevirtual  #234 // jnr.x86asm.Operand.isRegCode:(I)Z
        //   3870: ifne  3880 (offset +10)
        //   3873: aload_3
        //   3874: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //   3877: ifeq  7913 (offset +4036)
        //   3880: aload_3
        //   3881: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //   3884: ifeq  3916 (offset +32)
        //   3887: aload_3
        //   3888: checkcast  #51 // jnr.x86asm.Immediate
        //   3891: invokevirtual  #208 // jnr.x86asm.Immediate.value:()J
        //   3894: lconst_1
        //   3895: lcmp
        //   3896: ifne  3912 (offset +16)
        //   3899: aload_3
        //   3900: checkcast  #51 // jnr.x86asm.Immediate
        //   3903: invokevirtual  #205 // jnr.x86asm.Immediate.relocMode:()Ljnr/x86asm/RELOC_MODE;
        //   3906: getstatic  #130 // jnr.x86asm.RELOC_MODE.RELOC_NONE:Ljnr/x86asm/RELOC_MODE;
        //   3909: if_acmpeq  3916 (offset +7)
        //   3912: iconst_1
        //   3913: goto  3917 (offset +4)
        //   3916: iconst_0
        //   3917: istore  6
        //   3919: iload  6
        //   3921: ifeq  3930 (offset +9)
        //   3924: sipush  192
        //   3927: goto  3933 (offset +6)
        //   3930: sipush  208
        //   3933: istore  7
        //   3935: aload_2
        //   3936: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   3939: iconst_1
        //   3940: if_icmpeq  3949 (offset +9)
        //   3943: iload  7
        //   3945: iconst_1
        //   3946: ior
        //   3947: istore  7
        //   3949: aload_3
        //   3950: invokevirtual  #239 // jnr.x86asm.Operand.op:()I
        //   3953: iconst_1
        //   3954: if_icmpne  3963 (offset +9)
        //   3957: iload  7
        //   3959: iconst_2
        //   3960: ior
        //   3961: istore  7
        //   3963: aload_0
        //   3964: iload  7
        //   3966: aload_2
        //   3967: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   3970: iconst_2
        //   3971: if_icmpne  3978 (offset +7)
        //   3974: iconst_1
        //   3975: goto  3979 (offset +4)
        //   3978: iconst_0
        //   3979: aload_2
        //   3980: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   3983: bipush  8
        //   3985: if_icmpne  3992 (offset +7)
        //   3988: iconst_1
        //   3989: goto  3993 (offset +4)
        //   3992: iconst_0
        //   3993: aload  5
        //   3995: getfield  #126 // jnr.x86asm.InstructionDescription.opCodeR:I
        //   3998: aload_2
        //   3999: iload  6
        //   4001: invokestatic  #173 // jnr.x86asm.Assembler.intValue:(Z)I
        //   4004: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //   4007: iload  6
        //   4009: ifeq  4024 (offset +15)
        //   4012: aload_0
        //   4013: aload_3
        //   4014: checkcast  #51 // jnr.x86asm.Immediate
        //   4017: checkcast  #51 // jnr.x86asm.Immediate
        //   4020: iconst_1
        //   4021: invokevirtual  #151 // jnr.x86asm.Assembler._emitImmediate:(Ljnr/x86asm/Immediate;I)V
        //   4024: return
        //   4025: aload_2
        //   4026: invokevirtual  #236 // jnr.x86asm.Operand.isRegMem:()Z
        //   4029: ifeq  7913 (offset +3884)
        //   4032: aload_3
        //   4033: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   4036: ifeq  7913 (offset +3877)
        //   4039: aload  4
        //   4041: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //   4044: ifne  4064 (offset +20)
        //   4047: aload  4
        //   4049: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   4052: ifeq  7913 (offset +3861)
        //   4055: aload  4
        //   4057: iconst_1
        //   4058: invokevirtual  #234 // jnr.x86asm.Operand.isRegCode:(I)Z
        //   4061: ifeq  7913 (offset +3852)
        //   4064: aload_2
        //   4065: astore  6
        //   4067: aload_3
        //   4068: checkcast  #61 // jnr.x86asm.Register
        //   4071: checkcast  #61 // jnr.x86asm.Register
        //   4074: astore  7
        //   4076: aload  4
        //   4078: astore  8
        //   4080: getstatic  #80 // jnr.x86asm.Assembler.$assertionsDisabled:Z
        //   4083: ifne  4107 (offset +24)
        //   4086: aload  6
        //   4088: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   4091: aload  7
        //   4093: invokevirtual  #244 // jnr.x86asm.Register.size:()I
        //   4096: if_icmpeq  4107 (offset +11)
        //   4099: new  #31 // java.lang.AssertionError
        //   4102: dup
        //   4103: invokespecial  #138 // java.lang.AssertionError.<init>:()V
        //   4106: athrow
        //   4107: aload_0
        //   4108: aload  5
        //   4110: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   4113: aload  8
        //   4115: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   4118: invokestatic  #173 // jnr.x86asm.Assembler.intValue:(Z)I
        //   4121: iadd
        //   4122: aload  7
        //   4124: bipush  16
        //   4126: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //   4129: aload  7
        //   4131: bipush  48
        //   4133: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //   4136: aload  7
        //   4138: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //   4141: aload  6
        //   4143: aload  8
        //   4145: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //   4148: invokestatic  #173 // jnr.x86asm.Assembler.intValue:(Z)I
        //   4151: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //   4154: aload  8
        //   4156: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //   4159: ifeq  4175 (offset +16)
        //   4162: aload_0
        //   4163: aload  8
        //   4165: checkcast  #51 // jnr.x86asm.Immediate
        //   4168: checkcast  #51 // jnr.x86asm.Immediate
        //   4171: iconst_1
        //   4172: invokevirtual  #151 // jnr.x86asm.Assembler._emitImmediate:(Ljnr/x86asm/Immediate;I)V
        //   4175: return
        //   4176: aload_2
        //   4177: invokevirtual  #236 // jnr.x86asm.Operand.isRegMem:()Z
        //   4180: ifeq  4276 (offset +96)
        //   4183: aload_3
        //   4184: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   4187: ifeq  4276 (offset +89)
        //   4190: getstatic  #80 // jnr.x86asm.Assembler.$assertionsDisabled:Z
        //   4193: ifne  4215 (offset +22)
        //   4196: aload_2
        //   4197: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   4200: aload_3
        //   4201: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   4204: if_icmpeq  4215 (offset +11)
        //   4207: new  #31 // java.lang.AssertionError
        //   4210: dup
        //   4211: invokespecial  #138 // java.lang.AssertionError.<init>:()V
        //   4214: athrow
        //   4215: aload_0
        //   4216: sipush  132
        //   4219: aload_3
        //   4220: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   4223: iconst_1
        //   4224: if_icmpeq  4231 (offset +7)
        //   4227: iconst_1
        //   4228: goto  4232 (offset +4)
        //   4231: iconst_0
        //   4232: invokestatic  #173 // jnr.x86asm.Assembler.intValue:(Z)I
        //   4235: iadd
        //   4236: aload_3
        //   4237: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   4240: iconst_2
        //   4241: if_icmpne  4248 (offset +7)
        //   4244: iconst_1
        //   4245: goto  4249 (offset +4)
        //   4248: iconst_0
        //   4249: aload_3
        //   4250: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   4253: bipush  8
        //   4255: if_icmpne  4262 (offset +7)
        //   4258: iconst_1
        //   4259: goto  4263 (offset +4)
        //   4262: iconst_0
        //   4263: aload_3
        //   4264: checkcast  #44 // jnr.x86asm.BaseReg
        //   4267: invokevirtual  #181 // jnr.x86asm.BaseReg.code:()I
        //   4270: aload_2
        //   4271: iconst_0
        //   4272: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //   4275: return
        //   4276: aload_2
        //   4277: iconst_0
        //   4278: invokevirtual  #235 // jnr.x86asm.Operand.isRegIndex:(I)Z
        //   4281: ifeq  4388 (offset +107)
        //   4284: aload_3
        //   4285: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //   4288: ifeq  4388 (offset +100)
        //   4291: aload_2
        //   4292: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   4295: iconst_4
        //   4296: if_icmpgt  4306 (offset +10)
        //   4299: aload_2
        //   4300: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   4303: goto  4307 (offset +4)
        //   4306: iconst_4
        //   4307: istore  6
        //   4309: aload_2
        //   4310: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   4313: iconst_2
        //   4314: if_icmpne  4323 (offset +9)
        //   4317: aload_0
        //   4318: bipush  102
        //   4320: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   4323: aload_0
        //   4324: invokevirtual  #174 // jnr.x86asm.Assembler.is64:()Z
        //   4327: ifeq  4350 (offset +23)
        //   4330: aload_0
        //   4331: aload_2
        //   4332: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   4335: bipush  8
        //   4337: if_icmpne  4344 (offset +7)
        //   4340: iconst_1
        //   4341: goto  4345 (offset +4)
        //   4344: iconst_0
        //   4345: iconst_0
        //   4346: aload_2
        //   4347: invokevirtual  #164 // jnr.x86asm.Assembler._emitRexRM:(ZILjnr/x86asm/Operand;)V
        //   4350: aload_0
        //   4351: sipush  168
        //   4354: aload_2
        //   4355: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   4358: iconst_1
        //   4359: if_icmpeq  4366 (offset +7)
        //   4362: iconst_1
        //   4363: goto  4367 (offset +4)
        //   4366: iconst_0
        //   4367: invokestatic  #173 // jnr.x86asm.Assembler.intValue:(Z)I
        //   4370: iadd
        //   4371: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   4374: aload_0
        //   4375: aload_3
        //   4376: checkcast  #51 // jnr.x86asm.Immediate
        //   4379: checkcast  #51 // jnr.x86asm.Immediate
        //   4382: iload  6
        //   4384: invokevirtual  #151 // jnr.x86asm.Assembler._emitImmediate:(Ljnr/x86asm/Immediate;I)V
        //   4387: return
        //   4388: aload_2
        //   4389: invokevirtual  #236 // jnr.x86asm.Operand.isRegMem:()Z
        //   4392: ifeq  7913 (offset +3521)
        //   4395: aload_3
        //   4396: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //   4399: ifeq  7913 (offset +3514)
        //   4402: aload_2
        //   4403: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   4406: iconst_4
        //   4407: if_icmpgt  4417 (offset +10)
        //   4410: aload_2
        //   4411: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   4414: goto  4418 (offset +4)
        //   4417: iconst_4
        //   4418: istore  6
        //   4420: aload_2
        //   4421: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   4424: iconst_2
        //   4425: if_icmpne  4434 (offset +9)
        //   4428: aload_0
        //   4429: bipush  102
        //   4431: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   4434: aload_0
        //   4435: aload_2
        //   4436: invokevirtual  #165 // jnr.x86asm.Assembler._emitSegmentPrefix:(Ljnr/x86asm/Operand;)V
        //   4439: aload_0
        //   4440: invokevirtual  #174 // jnr.x86asm.Assembler.is64:()Z
        //   4443: ifeq  4466 (offset +23)
        //   4446: aload_0
        //   4447: aload_2
        //   4448: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   4451: bipush  8
        //   4453: if_icmpne  4460 (offset +7)
        //   4456: iconst_1
        //   4457: goto  4461 (offset +4)
        //   4460: iconst_0
        //   4461: iconst_0
        //   4462: aload_2
        //   4463: invokevirtual  #164 // jnr.x86asm.Assembler._emitRexRM:(ZILjnr/x86asm/Operand;)V
        //   4466: aload_0
        //   4467: sipush  246
        //   4470: aload_2
        //   4471: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   4474: iconst_1
        //   4475: if_icmpeq  4482 (offset +7)
        //   4478: iconst_1
        //   4479: goto  4483 (offset +4)
        //   4482: iconst_0
        //   4483: invokestatic  #173 // jnr.x86asm.Assembler.intValue:(Z)I
        //   4486: iadd
        //   4487: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   4490: aload_0
        //   4491: iconst_0
        //   4492: aload_2
        //   4493: iload  6
        //   4495: invokevirtual  #158 // jnr.x86asm.Assembler._emitModRM:(ILjnr/x86asm/Operand;I)V
        //   4498: aload_0
        //   4499: aload_3
        //   4500: checkcast  #51 // jnr.x86asm.Immediate
        //   4503: checkcast  #51 // jnr.x86asm.Immediate
        //   4506: iload  6
        //   4508: invokevirtual  #151 // jnr.x86asm.Assembler._emitImmediate:(Ljnr/x86asm/Immediate;I)V
        //   4511: return
        //   4512: aload_2
        //   4513: invokevirtual  #236 // jnr.x86asm.Operand.isRegMem:()Z
        //   4516: ifeq  7913 (offset +3397)
        //   4519: aload_3
        //   4520: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   4523: ifeq  7913 (offset +3390)
        //   4526: aload_2
        //   4527: astore  6
        //   4529: aload_3
        //   4530: checkcast  #61 // jnr.x86asm.Register
        //   4533: checkcast  #61 // jnr.x86asm.Register
        //   4536: astore  7
        //   4538: aload  7
        //   4540: bipush  16
        //   4542: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //   4545: ifeq  4554 (offset +9)
        //   4548: aload_0
        //   4549: bipush  102
        //   4551: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   4554: aload_0
        //   4555: aload  6
        //   4557: invokevirtual  #165 // jnr.x86asm.Assembler._emitSegmentPrefix:(Ljnr/x86asm/Operand;)V
        //   4560: aload_0
        //   4561: invokevirtual  #174 // jnr.x86asm.Assembler.is64:()Z
        //   4564: ifeq  4585 (offset +21)
        //   4567: aload_0
        //   4568: aload  7
        //   4570: bipush  48
        //   4572: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //   4575: aload  7
        //   4577: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //   4580: aload  6
        //   4582: invokevirtual  #164 // jnr.x86asm.Assembler._emitRexRM:(ZILjnr/x86asm/Operand;)V
        //   4585: aload  6
        //   4587: invokevirtual  #239 // jnr.x86asm.Operand.op:()I
        //   4590: iconst_1
        //   4591: if_icmpne  4650 (offset +59)
        //   4594: aload  6
        //   4596: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   4599: iconst_1
        //   4600: if_icmple  4650 (offset +50)
        //   4603: aload  6
        //   4605: checkcast  #61 // jnr.x86asm.Register
        //   4608: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //   4611: ifeq  4622 (offset +11)
        //   4614: aload  7
        //   4616: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //   4619: ifne  4650 (offset +31)
        //   4622: aload  6
        //   4624: checkcast  #61 // jnr.x86asm.Register
        //   4627: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //   4630: aload  7
        //   4632: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //   4635: ior
        //   4636: istore  8
        //   4638: aload_0
        //   4639: sipush  144
        //   4642: iload  8
        //   4644: iadd
        //   4645: i2b
        //   4646: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   4649: return
        //   4650: aload_0
        //   4651: sipush  134
        //   4654: aload  7
        //   4656: iconst_0
        //   4657: invokevirtual  #243 // jnr.x86asm.Register.isRegType:(I)Z
        //   4660: ifne  4667 (offset +7)
        //   4663: iconst_1
        //   4664: goto  4668 (offset +4)
        //   4667: iconst_0
        //   4668: invokestatic  #173 // jnr.x86asm.Assembler.intValue:(Z)I
        //   4671: iadd
        //   4672: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   4675: aload_0
        //   4676: aload  7
        //   4678: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //   4681: aload  6
        //   4683: iconst_0
        //   4684: invokevirtual  #158 // jnr.x86asm.Assembler._emitModRM:(ILjnr/x86asm/Operand;I)V
        //   4687: return
        //   4688: aload_2
        //   4689: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   4692: ifeq  4736 (offset +44)
        //   4695: aload_3
        //   4696: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   4699: ifeq  4736 (offset +37)
        //   4702: aload_0
        //   4703: ldc  #6 // 997616
        //   4705: aload_2
        //   4706: bipush  16
        //   4708: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   4711: aload_2
        //   4712: bipush  48
        //   4714: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   4717: aload_2
        //   4718: checkcast  #61 // jnr.x86asm.Register
        //   4721: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //   4724: aload_3
        //   4725: checkcast  #58 // jnr.x86asm.Mem
        //   4728: checkcast  #58 // jnr.x86asm.Mem
        //   4731: iconst_0
        //   4732: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //   4735: return
        //   4736: aload_2
        //   4737: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   4740: ifeq  7913 (offset +3173)
        //   4743: aload_3
        //   4744: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   4747: ifeq  7913 (offset +3166)
        //   4750: aload_0
        //   4751: ldc  #7 // 997617
        //   4753: aload_3
        //   4754: bipush  16
        //   4756: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   4759: aload_3
        //   4760: bipush  48
        //   4762: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   4765: aload_3
        //   4766: checkcast  #61 // jnr.x86asm.Register
        //   4769: invokevirtual  #241 // jnr.x86asm.Register.code:()I
        //   4772: aload_2
        //   4773: checkcast  #58 // jnr.x86asm.Mem
        //   4776: checkcast  #58 // jnr.x86asm.Mem
        //   4779: iconst_0
        //   4780: invokevirtual  #171 // jnr.x86asm.Assembler._emitX86RM:(IZZILjnr/x86asm/Operand;I)V
        //   4783: return
        //   4784: aload_2
        //   4785: bipush  80
        //   4787: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   4790: ifeq  4943 (offset +153)
        //   4793: aload_2
        //   4794: checkcast  #68 // jnr.x86asm.X87Register
        //   4797: invokevirtual  #254 // jnr.x86asm.X87Register.index:()I
        //   4800: istore  6
        //   4802: iconst_0
        //   4803: istore  7
        //   4805: aload_1
        //   4806: getstatic  #113 // jnr.x86asm.INST_CODE.INST_FCOM:Ljnr/x86asm/INST_CODE;
        //   4809: if_acmpeq  4850 (offset +41)
        //   4812: aload_1
        //   4813: getstatic  #114 // jnr.x86asm.INST_CODE.INST_FCOMP:Ljnr/x86asm/INST_CODE;
        //   4816: if_acmpeq  4850 (offset +34)
        //   4819: aload_3
        //   4820: bipush  80
        //   4822: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   4825: ifne  4838 (offset +13)
        //   4828: new  #33 // java.lang.IllegalArgumentException
        //   4831: dup
        //   4832: ldc  #28 // 'not x87 reg'
        //   4834: invokespecial  #140 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   4837: athrow
        //   4838: aload_3
        //   4839: checkcast  #68 // jnr.x86asm.X87Register
        //   4842: invokevirtual  #254 // jnr.x86asm.X87Register.index:()I
        //   4845: istore  7
        //   4847: goto  4870 (offset +23)
        //   4850: iload  6
        //   4852: ifeq  4870 (offset +18)
        //   4855: iload  7
        //   4857: ifeq  4870 (offset +13)
        //   4860: new  #33 // java.lang.IllegalArgumentException
        //   4863: dup
        //   4864: ldc  #20 // 'illegal instruction'
        //   4866: invokespecial  #140 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   4869: athrow
        //   4870: aload_0
        //   4871: iload  6
        //   4873: ifne  4890 (offset +17)
        //   4876: aload  5
        //   4878: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   4881: ldc  #4 // -16777216
        //   4883: iand
        //   4884: bipush  24
        //   4886: ishr
        //   4887: goto  4901 (offset +14)
        //   4890: aload  5
        //   4892: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   4895: ldc  #8 // 16711680
        //   4897: iand
        //   4898: bipush  16
        //   4900: ishr
        //   4901: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   4904: aload_0
        //   4905: iload  6
        //   4907: ifne  4927 (offset +20)
        //   4910: aload  5
        //   4912: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   4915: ldc  #5 // 65280
        //   4917: iand
        //   4918: bipush  8
        //   4920: ishr
        //   4921: iload  7
        //   4923: iadd
        //   4924: goto  4939 (offset +15)
        //   4927: aload  5
        //   4929: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   4932: sipush  255
        //   4935: iand
        //   4936: iload  6
        //   4938: iadd
        //   4939: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   4942: return
        //   4943: aload_2
        //   4944: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   4947: ifeq  7913 (offset +2966)
        //   4950: aload_2
        //   4951: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   4954: iconst_4
        //   4955: if_icmpeq  4967 (offset +12)
        //   4958: aload_2
        //   4959: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   4962: bipush  8
        //   4964: if_icmpne  7913 (offset +2949)
        //   4967: aload_3
        //   4968: invokevirtual  #232 // jnr.x86asm.Operand.isNone:()Z
        //   4971: ifeq  7913 (offset +2942)
        //   4974: aload_2
        //   4975: checkcast  #58 // jnr.x86asm.Mem
        //   4978: checkcast  #58 // jnr.x86asm.Mem
        //   4981: astore  6
        //   4983: aload_0
        //   4984: aload  6
        //   4986: invokevirtual  #165 // jnr.x86asm.Assembler._emitSegmentPrefix:(Ljnr/x86asm/Operand;)V
        //   4989: aload_0
        //   4990: aload_2
        //   4991: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   4994: iconst_4
        //   4995: if_icmpne  5012 (offset +17)
        //   4998: aload  5
        //   5000: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   5003: ldc  #4 // -16777216
        //   5005: iand
        //   5006: bipush  24
        //   5008: ishr
        //   5009: goto  5023 (offset +14)
        //   5012: aload  5
        //   5014: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   5017: ldc  #8 // 16711680
        //   5019: iand
        //   5020: bipush  16
        //   5022: ishr
        //   5023: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   5026: aload_0
        //   5027: aload  5
        //   5029: getfield  #126 // jnr.x86asm.InstructionDescription.opCodeR:I
        //   5032: aload  6
        //   5034: iconst_0
        //   5035: invokevirtual  #156 // jnr.x86asm.Assembler._emitModM:(ILjnr/x86asm/Mem;I)V
        //   5038: return
        //   5039: aload_2
        //   5040: bipush  80
        //   5042: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   5045: ifeq  7913 (offset +2868)
        //   5048: aload_2
        //   5049: checkcast  #68 // jnr.x86asm.X87Register
        //   5052: invokevirtual  #254 // jnr.x86asm.X87Register.index:()I
        //   5055: istore  6
        //   5057: aload_0
        //   5058: aload  5
        //   5060: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   5063: ldc  #5 // 65280
        //   5065: iand
        //   5066: bipush  8
        //   5068: ishr
        //   5069: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   5072: aload_0
        //   5073: aload  5
        //   5075: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   5078: sipush  255
        //   5081: iand
        //   5082: iload  6
        //   5084: iadd
        //   5085: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   5088: return
        //   5089: aload_2
        //   5090: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   5093: ifeq  5128 (offset +35)
        //   5096: aload_2
        //   5097: checkcast  #44 // jnr.x86asm.BaseReg
        //   5100: invokevirtual  #183 // jnr.x86asm.BaseReg.type:()I
        //   5103: bipush  48
        //   5105: if_icmpgt  5128 (offset +23)
        //   5108: aload_2
        //   5109: checkcast  #44 // jnr.x86asm.BaseReg
        //   5112: invokevirtual  #182 // jnr.x86asm.BaseReg.index:()I
        //   5115: ifne  5128 (offset +13)
        //   5118: aload_0
        //   5119: aload  5
        //   5121: getfield  #125 // jnr.x86asm.InstructionDescription.opCode2:I
        //   5124: invokevirtual  #159 // jnr.x86asm.Assembler._emitOpCode:(I)V
        //   5127: return
        //   5128: aload_2
        //   5129: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   5132: ifeq  7913 (offset +2781)
        //   5135: aload_0
        //   5136: aload  5
        //   5138: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   5141: iconst_0
        //   5142: iconst_0
        //   5143: aload  5
        //   5145: getfield  #126 // jnr.x86asm.InstructionDescription.opCodeR:I
        //   5148: aload_2
        //   5149: checkcast  #58 // jnr.x86asm.Mem
        //   5152: checkcast  #58 // jnr.x86asm.Mem
        //   5155: iconst_0
        //   5156: invokevirtual  #170 // jnr.x86asm.Assembler._emitX86RM:(IZIILjnr/x86asm/Operand;I)V
        //   5159: return
        //   5160: aload_2
        //   5161: bipush  80
        //   5163: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   5166: ifeq  5208 (offset +42)
        //   5169: aload_0
        //   5170: aload  5
        //   5172: getfield  #125 // jnr.x86asm.InstructionDescription.opCode2:I
        //   5175: ldc  #4 // -16777216
        //   5177: iand
        //   5178: bipush  24
        //   5180: ishr
        //   5181: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   5184: aload_0
        //   5185: aload  5
        //   5187: getfield  #125 // jnr.x86asm.InstructionDescription.opCode2:I
        //   5190: ldc  #8 // 16711680
        //   5192: iand
        //   5193: bipush  16
        //   5195: ishr
        //   5196: aload_2
        //   5197: checkcast  #68 // jnr.x86asm.X87Register
        //   5200: invokevirtual  #254 // jnr.x86asm.X87Register.index:()I
        //   5203: iadd
        //   5204: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   5207: return
        //   5208: aload_2
        //   5209: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   5212: ifne  5225 (offset +13)
        //   5215: new  #33 // java.lang.IllegalArgumentException
        //   5218: dup
        //   5219: ldc  #27 // 'not x87 mem'
        //   5221: invokespecial  #140 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   5224: athrow
        //   5225: aload_2
        //   5226: checkcast  #58 // jnr.x86asm.Mem
        //   5229: checkcast  #58 // jnr.x86asm.Mem
        //   5232: astore  6
        //   5234: iconst_0
        //   5235: istore  7
        //   5237: iconst_0
        //   5238: istore  8
        //   5240: aload_2
        //   5241: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   5244: iconst_2
        //   5245: if_icmpne  5278 (offset +33)
        //   5248: aload  5
        //   5250: getfield  #122 // jnr.x86asm.InstructionDescription.o1Flags:I
        //   5253: iconst_2
        //   5254: iand
        //   5255: ifeq  5278 (offset +23)
        //   5258: aload  5
        //   5260: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   5263: ldc  #4 // -16777216
        //   5265: iand
        //   5266: bipush  24
        //   5268: ishr
        //   5269: istore  7
        //   5271: aload  5
        //   5273: getfield  #126 // jnr.x86asm.InstructionDescription.opCodeR:I
        //   5276: istore  8
        //   5278: aload_2
        //   5279: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   5282: iconst_4
        //   5283: if_icmpne  5316 (offset +33)
        //   5286: aload  5
        //   5288: getfield  #122 // jnr.x86asm.InstructionDescription.o1Flags:I
        //   5291: iconst_4
        //   5292: iand
        //   5293: ifeq  5316 (offset +23)
        //   5296: aload  5
        //   5298: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   5301: ldc  #8 // 16711680
        //   5303: iand
        //   5304: bipush  16
        //   5306: ishr
        //   5307: istore  7
        //   5309: aload  5
        //   5311: getfield  #126 // jnr.x86asm.InstructionDescription.opCodeR:I
        //   5314: istore  8
        //   5316: aload_2
        //   5317: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   5320: bipush  8
        //   5322: if_icmpne  5360 (offset +38)
        //   5325: aload  5
        //   5327: getfield  #122 // jnr.x86asm.InstructionDescription.o1Flags:I
        //   5330: bipush  8
        //   5332: iand
        //   5333: ifeq  5360 (offset +27)
        //   5336: aload  5
        //   5338: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   5341: ldc  #5 // 65280
        //   5343: iand
        //   5344: bipush  8
        //   5346: ishr
        //   5347: istore  7
        //   5349: aload  5
        //   5351: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   5354: sipush  255
        //   5357: iand
        //   5358: istore  8
        //   5360: iload  7
        //   5362: ifeq  7913 (offset +2551)
        //   5365: aload_0
        //   5366: aload  6
        //   5368: invokevirtual  #165 // jnr.x86asm.Assembler._emitSegmentPrefix:(Ljnr/x86asm/Operand;)V
        //   5371: aload_0
        //   5372: iload  7
        //   5374: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   5377: aload_0
        //   5378: iload  8
        //   5380: aload  6
        //   5382: iconst_0
        //   5383: invokevirtual  #156 // jnr.x86asm.Assembler._emitModM:(ILjnr/x86asm/Mem;I)V
        //   5386: return
        //   5387: getstatic  #80 // jnr.x86asm.Assembler.$assertionsDisabled:Z
        //   5390: ifne  5409 (offset +19)
        //   5393: aload  5
        //   5395: getfield  #122 // jnr.x86asm.InstructionDescription.o1Flags:I
        //   5398: ifne  5409 (offset +11)
        //   5401: new  #31 // java.lang.AssertionError
        //   5404: dup
        //   5405: invokespecial  #138 // java.lang.AssertionError.<init>:()V
        //   5408: athrow
        //   5409: getstatic  #80 // jnr.x86asm.Assembler.$assertionsDisabled:Z
        //   5412: ifne  5431 (offset +19)
        //   5415: aload  5
        //   5417: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   5420: ifne  5431 (offset +11)
        //   5423: new  #31 // java.lang.AssertionError
        //   5426: dup
        //   5427: invokespecial  #138 // java.lang.AssertionError.<init>:()V
        //   5430: athrow
        //   5431: aload_2
        //   5432: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   5435: ifeq  5449 (offset +14)
        //   5438: aload  5
        //   5440: getfield  #122 // jnr.x86asm.InstructionDescription.o1Flags:I
        //   5443: bipush  64
        //   5445: iand
        //   5446: ifeq  5625 (offset +179)
        //   5449: aload_2
        //   5450: bipush  96
        //   5452: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   5455: ifeq  5469 (offset +14)
        //   5458: aload  5
        //   5460: getfield  #122 // jnr.x86asm.InstructionDescription.o1Flags:I
        //   5463: bipush  16
        //   5465: iand
        //   5466: ifeq  5625 (offset +159)
        //   5469: aload_2
        //   5470: bipush  112
        //   5472: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   5475: ifeq  5489 (offset +14)
        //   5478: aload  5
        //   5480: getfield  #122 // jnr.x86asm.InstructionDescription.o1Flags:I
        //   5483: bipush  32
        //   5485: iand
        //   5486: ifeq  5625 (offset +139)
        //   5489: aload_2
        //   5490: bipush  32
        //   5492: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   5495: ifeq  5508 (offset +13)
        //   5498: aload  5
        //   5500: getfield  #122 // jnr.x86asm.InstructionDescription.o1Flags:I
        //   5503: iconst_4
        //   5504: iand
        //   5505: ifeq  5625 (offset +120)
        //   5508: aload_2
        //   5509: bipush  48
        //   5511: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   5514: ifeq  5528 (offset +14)
        //   5517: aload  5
        //   5519: getfield  #122 // jnr.x86asm.InstructionDescription.o1Flags:I
        //   5522: bipush  8
        //   5524: iand
        //   5525: ifeq  5625 (offset +100)
        //   5528: aload_3
        //   5529: bipush  96
        //   5531: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   5534: ifeq  5548 (offset +14)
        //   5537: aload  5
        //   5539: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   5542: bipush  16
        //   5544: iand
        //   5545: ifeq  5625 (offset +80)
        //   5548: aload_3
        //   5549: bipush  112
        //   5551: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   5554: ifeq  5568 (offset +14)
        //   5557: aload  5
        //   5559: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   5562: bipush  32
        //   5564: iand
        //   5565: ifeq  5625 (offset +60)
        //   5568: aload_3
        //   5569: bipush  32
        //   5571: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   5574: ifeq  5587 (offset +13)
        //   5577: aload  5
        //   5579: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   5582: iconst_4
        //   5583: iand
        //   5584: ifeq  5625 (offset +41)
        //   5587: aload_3
        //   5588: bipush  48
        //   5590: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   5593: ifeq  5607 (offset +14)
        //   5596: aload  5
        //   5598: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   5601: bipush  8
        //   5603: iand
        //   5604: ifeq  5625 (offset +21)
        //   5607: aload_3
        //   5608: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   5611: ifeq  5635 (offset +24)
        //   5614: aload  5
        //   5616: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   5619: bipush  64
        //   5621: iand
        //   5622: ifne  5635 (offset +13)
        //   5625: new  #33 // java.lang.IllegalArgumentException
        //   5628: dup
        //   5629: ldc  #20 // 'illegal instruction'
        //   5631: invokespecial  #140 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   5634: athrow
        //   5635: aload_2
        //   5636: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   5639: ifeq  5659 (offset +20)
        //   5642: aload_3
        //   5643: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   5646: ifeq  5659 (offset +13)
        //   5649: new  #33 // java.lang.IllegalArgumentException
        //   5652: dup
        //   5653: ldc  #20 // 'illegal instruction'
        //   5655: invokespecial  #140 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   5658: athrow
        //   5659: aload  5
        //   5661: getfield  #122 // jnr.x86asm.InstructionDescription.o1Flags:I
        //   5664: aload  5
        //   5666: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   5669: ior
        //   5670: iconst_1
        //   5671: iand
        //   5672: ifeq  5679 (offset +7)
        //   5675: iconst_0
        //   5676: goto  5705 (offset +29)
        //   5679: aload_2
        //   5680: bipush  48
        //   5682: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   5685: ifne  5697 (offset +12)
        //   5688: aload_2
        //   5689: bipush  48
        //   5691: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   5694: ifeq  5701 (offset +7)
        //   5697: iconst_1
        //   5698: goto  5702 (offset +4)
        //   5701: iconst_0
        //   5702: invokestatic  #173 // jnr.x86asm.Assembler.intValue:(Z)I
        //   5705: istore  6
        //   5707: aload_2
        //   5708: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   5711: ifeq  5748 (offset +37)
        //   5714: aload_3
        //   5715: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   5718: ifeq  5748 (offset +30)
        //   5721: aload_0
        //   5722: aload  5
        //   5724: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   5727: iload  6
        //   5729: aload_2
        //   5730: checkcast  #44 // jnr.x86asm.BaseReg
        //   5733: invokevirtual  #181 // jnr.x86asm.BaseReg.code:()I
        //   5736: aload_3
        //   5737: checkcast  #44 // jnr.x86asm.BaseReg
        //   5740: checkcast  #44 // jnr.x86asm.BaseReg
        //   5743: iconst_0
        //   5744: invokevirtual  #154 // jnr.x86asm.Assembler._emitMmu:(IIILjnr/x86asm/Operand;I)V
        //   5747: return
        //   5748: aload_2
        //   5749: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   5752: ifeq  5789 (offset +37)
        //   5755: aload_3
        //   5756: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   5759: ifeq  5789 (offset +30)
        //   5762: aload_0
        //   5763: aload  5
        //   5765: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   5768: iload  6
        //   5770: aload_2
        //   5771: checkcast  #44 // jnr.x86asm.BaseReg
        //   5774: invokevirtual  #181 // jnr.x86asm.BaseReg.code:()I
        //   5777: aload_3
        //   5778: checkcast  #58 // jnr.x86asm.Mem
        //   5781: checkcast  #58 // jnr.x86asm.Mem
        //   5784: iconst_0
        //   5785: invokevirtual  #154 // jnr.x86asm.Assembler._emitMmu:(IIILjnr/x86asm/Operand;I)V
        //   5788: return
        //   5789: aload_2
        //   5790: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   5793: ifeq  7913 (offset +2120)
        //   5796: aload_3
        //   5797: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   5800: ifeq  7913 (offset +2113)
        //   5803: aload_0
        //   5804: aload  5
        //   5806: getfield  #125 // jnr.x86asm.InstructionDescription.opCode2:I
        //   5809: iload  6
        //   5811: aload_3
        //   5812: checkcast  #44 // jnr.x86asm.BaseReg
        //   5815: invokevirtual  #181 // jnr.x86asm.BaseReg.code:()I
        //   5818: aload_2
        //   5819: checkcast  #58 // jnr.x86asm.Mem
        //   5822: checkcast  #58 // jnr.x86asm.Mem
        //   5825: iconst_0
        //   5826: invokevirtual  #154 // jnr.x86asm.Assembler._emitMmu:(IIILjnr/x86asm/Operand;I)V
        //   5829: return
        //   5830: aload_2
        //   5831: bipush  96
        //   5833: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   5836: ifne  5848 (offset +12)
        //   5839: aload_2
        //   5840: bipush  112
        //   5842: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   5845: ifeq  5896 (offset +51)
        //   5848: aload_3
        //   5849: bipush  32
        //   5851: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   5854: ifne  5864 (offset +10)
        //   5857: aload_3
        //   5858: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   5861: ifeq  5896 (offset +35)
        //   5864: aload_0
        //   5865: aload_2
        //   5866: bipush  112
        //   5868: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   5871: ifeq  5879 (offset +8)
        //   5874: ldc  #11 // 1711279982
        //   5876: goto  5882 (offset +6)
        //   5879: sipush  3950
        //   5882: iconst_0
        //   5883: aload_2
        //   5884: checkcast  #44 // jnr.x86asm.BaseReg
        //   5887: invokevirtual  #181 // jnr.x86asm.BaseReg.code:()I
        //   5890: aload_3
        //   5891: iconst_0
        //   5892: invokevirtual  #154 // jnr.x86asm.Assembler._emitMmu:(IIILjnr/x86asm/Operand;I)V
        //   5895: return
        //   5896: aload_2
        //   5897: bipush  32
        //   5899: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   5902: ifne  5912 (offset +10)
        //   5905: aload_2
        //   5906: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   5909: ifeq  7913 (offset +2004)
        //   5912: aload_3
        //   5913: bipush  96
        //   5915: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   5918: ifne  5930 (offset +12)
        //   5921: aload_3
        //   5922: bipush  112
        //   5924: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   5927: ifeq  7913 (offset +1986)
        //   5930: aload_0
        //   5931: aload_3
        //   5932: bipush  112
        //   5934: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   5937: ifeq  5945 (offset +8)
        //   5940: ldc  #12 // 1711279998
        //   5942: goto  5948 (offset +6)
        //   5945: sipush  3966
        //   5948: iconst_0
        //   5949: aload_3
        //   5950: checkcast  #44 // jnr.x86asm.BaseReg
        //   5953: invokevirtual  #181 // jnr.x86asm.BaseReg.code:()I
        //   5956: aload_2
        //   5957: iconst_0
        //   5958: invokevirtual  #154 // jnr.x86asm.Assembler._emitMmu:(IIILjnr/x86asm/Operand;I)V
        //   5961: return
        //   5962: aload_2
        //   5963: bipush  96
        //   5965: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   5968: ifeq  6004 (offset +36)
        //   5971: aload_3
        //   5972: bipush  96
        //   5974: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   5977: ifeq  6004 (offset +27)
        //   5980: aload_0
        //   5981: sipush  3951
        //   5984: iconst_0
        //   5985: aload_2
        //   5986: checkcast  #57 // jnr.x86asm.MMRegister
        //   5989: invokevirtual  #217 // jnr.x86asm.MMRegister.code:()I
        //   5992: aload_3
        //   5993: checkcast  #57 // jnr.x86asm.MMRegister
        //   5996: checkcast  #57 // jnr.x86asm.MMRegister
        //   5999: iconst_0
        //   6000: invokevirtual  #154 // jnr.x86asm.Assembler._emitMmu:(IIILjnr/x86asm/Operand;I)V
        //   6003: return
        //   6004: aload_2
        //   6005: bipush  112
        //   6007: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6010: ifeq  6045 (offset +35)
        //   6013: aload_3
        //   6014: bipush  112
        //   6016: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6019: ifeq  6045 (offset +26)
        //   6022: aload_0
        //   6023: ldc  #2 // -218099842
        //   6025: iconst_0
        //   6026: aload_2
        //   6027: checkcast  #69 // jnr.x86asm.XMMRegister
        //   6030: invokevirtual  #255 // jnr.x86asm.XMMRegister.code:()I
        //   6033: aload_3
        //   6034: checkcast  #69 // jnr.x86asm.XMMRegister
        //   6037: checkcast  #69 // jnr.x86asm.XMMRegister
        //   6040: iconst_0
        //   6041: invokevirtual  #154 // jnr.x86asm.Assembler._emitMmu:(IIILjnr/x86asm/Operand;I)V
        //   6044: return
        //   6045: aload_2
        //   6046: bipush  96
        //   6048: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6051: ifeq  6086 (offset +35)
        //   6054: aload_3
        //   6055: bipush  112
        //   6057: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6060: ifeq  6086 (offset +26)
        //   6063: aload_0
        //   6064: ldc  #1 // -234876970
        //   6066: iconst_0
        //   6067: aload_2
        //   6068: checkcast  #57 // jnr.x86asm.MMRegister
        //   6071: invokevirtual  #217 // jnr.x86asm.MMRegister.code:()I
        //   6074: aload_3
        //   6075: checkcast  #69 // jnr.x86asm.XMMRegister
        //   6078: checkcast  #69 // jnr.x86asm.XMMRegister
        //   6081: iconst_0
        //   6082: invokevirtual  #154 // jnr.x86asm.Assembler._emitMmu:(IIILjnr/x86asm/Operand;I)V
        //   6085: return
        //   6086: aload_2
        //   6087: bipush  112
        //   6089: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6092: ifeq  6127 (offset +35)
        //   6095: aload_3
        //   6096: bipush  96
        //   6098: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6101: ifeq  6127 (offset +26)
        //   6104: aload_0
        //   6105: ldc  #3 // -218099754
        //   6107: iconst_0
        //   6108: aload_2
        //   6109: checkcast  #69 // jnr.x86asm.XMMRegister
        //   6112: invokevirtual  #255 // jnr.x86asm.XMMRegister.code:()I
        //   6115: aload_3
        //   6116: checkcast  #57 // jnr.x86asm.MMRegister
        //   6119: checkcast  #57 // jnr.x86asm.MMRegister
        //   6122: iconst_0
        //   6123: invokevirtual  #154 // jnr.x86asm.Assembler._emitMmu:(IIILjnr/x86asm/Operand;I)V
        //   6126: return
        //   6127: aload_2
        //   6128: bipush  96
        //   6130: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6133: ifeq  6167 (offset +34)
        //   6136: aload_3
        //   6137: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   6140: ifeq  6167 (offset +27)
        //   6143: aload_0
        //   6144: sipush  3951
        //   6147: iconst_0
        //   6148: aload_2
        //   6149: checkcast  #57 // jnr.x86asm.MMRegister
        //   6152: invokevirtual  #217 // jnr.x86asm.MMRegister.code:()I
        //   6155: aload_3
        //   6156: checkcast  #58 // jnr.x86asm.Mem
        //   6159: checkcast  #58 // jnr.x86asm.Mem
        //   6162: iconst_0
        //   6163: invokevirtual  #154 // jnr.x86asm.Assembler._emitMmu:(IIILjnr/x86asm/Operand;I)V
        //   6166: return
        //   6167: aload_2
        //   6168: bipush  112
        //   6170: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6173: ifeq  6206 (offset +33)
        //   6176: aload_3
        //   6177: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   6180: ifeq  6206 (offset +26)
        //   6183: aload_0
        //   6184: ldc  #2 // -218099842
        //   6186: iconst_0
        //   6187: aload_2
        //   6188: checkcast  #69 // jnr.x86asm.XMMRegister
        //   6191: invokevirtual  #255 // jnr.x86asm.XMMRegister.code:()I
        //   6194: aload_3
        //   6195: checkcast  #58 // jnr.x86asm.Mem
        //   6198: checkcast  #58 // jnr.x86asm.Mem
        //   6201: iconst_0
        //   6202: invokevirtual  #154 // jnr.x86asm.Assembler._emitMmu:(IIILjnr/x86asm/Operand;I)V
        //   6205: return
        //   6206: aload_2
        //   6207: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   6210: ifeq  6246 (offset +36)
        //   6213: aload_3
        //   6214: bipush  96
        //   6216: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6219: ifeq  6246 (offset +27)
        //   6222: aload_0
        //   6223: sipush  3967
        //   6226: iconst_0
        //   6227: aload_3
        //   6228: checkcast  #57 // jnr.x86asm.MMRegister
        //   6231: invokevirtual  #217 // jnr.x86asm.MMRegister.code:()I
        //   6234: aload_2
        //   6235: checkcast  #58 // jnr.x86asm.Mem
        //   6238: checkcast  #58 // jnr.x86asm.Mem
        //   6241: iconst_0
        //   6242: invokevirtual  #154 // jnr.x86asm.Assembler._emitMmu:(IIILjnr/x86asm/Operand;I)V
        //   6245: return
        //   6246: aload_2
        //   6247: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   6250: ifeq  6285 (offset +35)
        //   6253: aload_3
        //   6254: bipush  112
        //   6256: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6259: ifeq  6285 (offset +26)
        //   6262: aload_0
        //   6263: ldc  #13 // 1711280086
        //   6265: iconst_0
        //   6266: aload_3
        //   6267: checkcast  #69 // jnr.x86asm.XMMRegister
        //   6270: invokevirtual  #255 // jnr.x86asm.XMMRegister.code:()I
        //   6273: aload_2
        //   6274: checkcast  #58 // jnr.x86asm.Mem
        //   6277: checkcast  #58 // jnr.x86asm.Mem
        //   6280: iconst_0
        //   6281: invokevirtual  #154 // jnr.x86asm.Assembler._emitMmu:(IIILjnr/x86asm/Operand;I)V
        //   6284: return
        //   6285: aload_0
        //   6286: invokevirtual  #174 // jnr.x86asm.Assembler.is64:()Z
        //   6289: ifeq  7913 (offset +1624)
        //   6292: aload_2
        //   6293: bipush  96
        //   6295: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6298: ifne  6310 (offset +12)
        //   6301: aload_2
        //   6302: bipush  112
        //   6304: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6307: ifeq  6358 (offset +51)
        //   6310: aload_3
        //   6311: bipush  48
        //   6313: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6316: ifne  6326 (offset +10)
        //   6319: aload_3
        //   6320: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   6323: ifeq  6358 (offset +35)
        //   6326: aload_0
        //   6327: aload_2
        //   6328: bipush  112
        //   6330: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6333: ifeq  6341 (offset +8)
        //   6336: ldc  #11 // 1711279982
        //   6338: goto  6344 (offset +6)
        //   6341: sipush  3950
        //   6344: iconst_1
        //   6345: aload_2
        //   6346: checkcast  #44 // jnr.x86asm.BaseReg
        //   6349: invokevirtual  #181 // jnr.x86asm.BaseReg.code:()I
        //   6352: aload_3
        //   6353: iconst_0
        //   6354: invokevirtual  #154 // jnr.x86asm.Assembler._emitMmu:(IIILjnr/x86asm/Operand;I)V
        //   6357: return
        //   6358: aload_2
        //   6359: bipush  48
        //   6361: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6364: ifne  6374 (offset +10)
        //   6367: aload_2
        //   6368: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   6371: ifeq  7913 (offset +1542)
        //   6374: aload_3
        //   6375: bipush  96
        //   6377: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6380: ifne  6392 (offset +12)
        //   6383: aload_3
        //   6384: bipush  112
        //   6386: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6389: ifeq  7913 (offset +1524)
        //   6392: aload_0
        //   6393: aload_3
        //   6394: bipush  112
        //   6396: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6399: ifeq  6407 (offset +8)
        //   6402: ldc  #12 // 1711279998
        //   6404: goto  6410 (offset +6)
        //   6407: sipush  3966
        //   6410: iconst_1
        //   6411: aload_3
        //   6412: checkcast  #44 // jnr.x86asm.BaseReg
        //   6415: invokevirtual  #181 // jnr.x86asm.BaseReg.code:()I
        //   6418: aload_2
        //   6419: iconst_0
        //   6420: invokevirtual  #154 // jnr.x86asm.Assembler._emitMmu:(IIILjnr/x86asm/Operand;I)V
        //   6423: return
        //   6424: aload_2
        //   6425: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   6428: ifeq  7913 (offset +1485)
        //   6431: aload_3
        //   6432: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //   6435: ifeq  7913 (offset +1478)
        //   6438: aload_2
        //   6439: checkcast  #58 // jnr.x86asm.Mem
        //   6442: checkcast  #58 // jnr.x86asm.Mem
        //   6445: astore  6
        //   6447: aload_3
        //   6448: checkcast  #51 // jnr.x86asm.Immediate
        //   6451: checkcast  #51 // jnr.x86asm.Immediate
        //   6454: astore  7
        //   6456: aload_0
        //   6457: sipush  3864
        //   6460: iconst_0
        //   6461: aload  7
        //   6463: invokevirtual  #208 // jnr.x86asm.Immediate.value:()J
        //   6466: l2i
        //   6467: aload  6
        //   6469: iconst_0
        //   6470: invokevirtual  #154 // jnr.x86asm.Assembler._emitMmu:(IIILjnr/x86asm/Operand;I)V
        //   6473: return
        //   6474: aload_2
        //   6475: invokevirtual  #236 // jnr.x86asm.Operand.isRegMem:()Z
        //   6478: ifeq  6514 (offset +36)
        //   6481: aload_3
        //   6482: bipush  112
        //   6484: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6487: ifne  6506 (offset +19)
        //   6490: aload_1
        //   6491: getstatic  #120 // jnr.x86asm.INST_CODE.INST_PEXTRW:Ljnr/x86asm/INST_CODE;
        //   6494: if_acmpne  6514 (offset +20)
        //   6497: aload_3
        //   6498: bipush  96
        //   6500: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6503: ifeq  6514 (offset +11)
        //   6506: aload  4
        //   6508: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //   6511: ifne  6524 (offset +13)
        //   6514: new  #34 // java.lang.IllegalStateException
        //   6517: dup
        //   6518: ldc  #20 // 'illegal instruction'
        //   6520: invokespecial  #141 // java.lang.IllegalStateException.<init>:(Ljava/lang/String;)V
        //   6523: athrow
        //   6524: aload  5
        //   6526: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   6529: istore  6
        //   6531: aload_2
        //   6532: bipush  32
        //   6534: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6537: ifne  6549 (offset +12)
        //   6540: aload_2
        //   6541: bipush  48
        //   6543: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6546: ifeq  6553 (offset +7)
        //   6549: iconst_1
        //   6550: goto  6554 (offset +4)
        //   6553: iconst_0
        //   6554: istore  7
        //   6556: aload_1
        //   6557: getstatic  #117 // jnr.x86asm.INST_CODE.INST_PEXTRB:Ljnr/x86asm/INST_CODE;
        //   6560: if_acmpne  6593 (offset +33)
        //   6563: aload_2
        //   6564: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   6567: ifeq  6593 (offset +26)
        //   6570: aload_2
        //   6571: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   6574: iconst_1
        //   6575: if_icmpeq  6593 (offset +18)
        //   6578: iload  7
        //   6580: ifne  6593 (offset +13)
        //   6583: new  #34 // java.lang.IllegalStateException
        //   6586: dup
        //   6587: ldc  #20 // 'illegal instruction'
        //   6589: invokespecial  #141 // java.lang.IllegalStateException.<init>:(Ljava/lang/String;)V
        //   6592: athrow
        //   6593: aload_1
        //   6594: getstatic  #120 // jnr.x86asm.INST_CODE.INST_PEXTRW:Ljnr/x86asm/INST_CODE;
        //   6597: if_acmpne  6630 (offset +33)
        //   6600: aload_2
        //   6601: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   6604: ifeq  6630 (offset +26)
        //   6607: aload_2
        //   6608: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   6611: iconst_2
        //   6612: if_icmpeq  6630 (offset +18)
        //   6615: iload  7
        //   6617: ifne  6630 (offset +13)
        //   6620: new  #34 // java.lang.IllegalStateException
        //   6623: dup
        //   6624: ldc  #20 // 'illegal instruction'
        //   6626: invokespecial  #141 // java.lang.IllegalStateException.<init>:(Ljava/lang/String;)V
        //   6629: athrow
        //   6630: aload_1
        //   6631: getstatic  #118 // jnr.x86asm.INST_CODE.INST_PEXTRD:Ljnr/x86asm/INST_CODE;
        //   6634: if_acmpne  6667 (offset +33)
        //   6637: aload_2
        //   6638: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   6641: ifeq  6667 (offset +26)
        //   6644: aload_2
        //   6645: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   6648: iconst_4
        //   6649: if_icmpeq  6667 (offset +18)
        //   6652: iload  7
        //   6654: ifne  6667 (offset +13)
        //   6657: new  #34 // java.lang.IllegalStateException
        //   6660: dup
        //   6661: ldc  #20 // 'illegal instruction'
        //   6663: invokespecial  #141 // java.lang.IllegalStateException.<init>:(Ljava/lang/String;)V
        //   6666: athrow
        //   6667: aload_1
        //   6668: getstatic  #119 // jnr.x86asm.INST_CODE.INST_PEXTRQ:Ljnr/x86asm/INST_CODE;
        //   6671: if_acmpne  6705 (offset +34)
        //   6674: aload_2
        //   6675: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   6678: ifeq  6705 (offset +27)
        //   6681: aload_2
        //   6682: invokevirtual  #240 // jnr.x86asm.Operand.size:()I
        //   6685: bipush  8
        //   6687: if_icmpeq  6705 (offset +18)
        //   6690: iload  7
        //   6692: ifne  6705 (offset +13)
        //   6695: new  #34 // java.lang.IllegalStateException
        //   6698: dup
        //   6699: ldc  #20 // 'illegal instruction'
        //   6701: invokespecial  #141 // java.lang.IllegalStateException.<init>:(Ljava/lang/String;)V
        //   6704: athrow
        //   6705: aload_3
        //   6706: bipush  112
        //   6708: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6711: ifeq  6721 (offset +10)
        //   6714: iload  6
        //   6716: ldc  #10 // 1711276032
        //   6718: ior
        //   6719: istore  6
        //   6721: aload_2
        //   6722: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   6725: ifeq  6778 (offset +53)
        //   6728: aload_0
        //   6729: iload  6
        //   6731: aload  5
        //   6733: getfield  #126 // jnr.x86asm.InstructionDescription.opCodeR:I
        //   6736: aload_2
        //   6737: bipush  48
        //   6739: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6742: invokestatic  #173 // jnr.x86asm.Assembler.intValue:(Z)I
        //   6745: ior
        //   6746: aload_3
        //   6747: checkcast  #44 // jnr.x86asm.BaseReg
        //   6750: invokevirtual  #181 // jnr.x86asm.BaseReg.code:()I
        //   6753: aload_2
        //   6754: checkcast  #44 // jnr.x86asm.BaseReg
        //   6757: checkcast  #44 // jnr.x86asm.BaseReg
        //   6760: iconst_1
        //   6761: invokevirtual  #154 // jnr.x86asm.Assembler._emitMmu:(IIILjnr/x86asm/Operand;I)V
        //   6764: aload_0
        //   6765: aload  4
        //   6767: checkcast  #51 // jnr.x86asm.Immediate
        //   6770: checkcast  #51 // jnr.x86asm.Immediate
        //   6773: iconst_1
        //   6774: invokevirtual  #151 // jnr.x86asm.Assembler._emitImmediate:(Ljnr/x86asm/Immediate;I)V
        //   6777: return
        //   6778: aload_2
        //   6779: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   6782: ifeq  7913 (offset +1131)
        //   6785: aload_0
        //   6786: iload  6
        //   6788: aload  5
        //   6790: getfield  #126 // jnr.x86asm.InstructionDescription.opCodeR:I
        //   6793: aload_3
        //   6794: checkcast  #44 // jnr.x86asm.BaseReg
        //   6797: invokevirtual  #181 // jnr.x86asm.BaseReg.code:()I
        //   6800: aload_2
        //   6801: checkcast  #58 // jnr.x86asm.Mem
        //   6804: checkcast  #58 // jnr.x86asm.Mem
        //   6807: iconst_1
        //   6808: invokevirtual  #154 // jnr.x86asm.Assembler._emitMmu:(IIILjnr/x86asm/Operand;I)V
        //   6811: aload_0
        //   6812: aload  4
        //   6814: checkcast  #51 // jnr.x86asm.Immediate
        //   6817: checkcast  #51 // jnr.x86asm.Immediate
        //   6820: iconst_1
        //   6821: invokevirtual  #151 // jnr.x86asm.Assembler._emitImmediate:(Ljnr/x86asm/Immediate;I)V
        //   6824: return
        //   6825: getstatic  #80 // jnr.x86asm.Assembler.$assertionsDisabled:Z
        //   6828: ifne  6847 (offset +19)
        //   6831: aload  5
        //   6833: getfield  #122 // jnr.x86asm.InstructionDescription.o1Flags:I
        //   6836: ifne  6847 (offset +11)
        //   6839: new  #31 // java.lang.AssertionError
        //   6842: dup
        //   6843: invokespecial  #138 // java.lang.AssertionError.<init>:()V
        //   6846: athrow
        //   6847: getstatic  #80 // jnr.x86asm.Assembler.$assertionsDisabled:Z
        //   6850: ifne  6869 (offset +19)
        //   6853: aload  5
        //   6855: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   6858: ifne  6869 (offset +11)
        //   6861: new  #31 // java.lang.AssertionError
        //   6864: dup
        //   6865: invokespecial  #138 // java.lang.AssertionError.<init>:()V
        //   6868: athrow
        //   6869: aload_2
        //   6870: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   6873: ifeq  7071 (offset +198)
        //   6876: aload_2
        //   6877: bipush  96
        //   6879: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6882: ifeq  6896 (offset +14)
        //   6885: aload  5
        //   6887: getfield  #122 // jnr.x86asm.InstructionDescription.o1Flags:I
        //   6890: bipush  16
        //   6892: iand
        //   6893: ifeq  7071 (offset +178)
        //   6896: aload_2
        //   6897: bipush  112
        //   6899: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6902: ifeq  6916 (offset +14)
        //   6905: aload  5
        //   6907: getfield  #122 // jnr.x86asm.InstructionDescription.o1Flags:I
        //   6910: bipush  32
        //   6912: iand
        //   6913: ifeq  7071 (offset +158)
        //   6916: aload_2
        //   6917: bipush  32
        //   6919: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6922: ifeq  6935 (offset +13)
        //   6925: aload  5
        //   6927: getfield  #122 // jnr.x86asm.InstructionDescription.o1Flags:I
        //   6930: iconst_4
        //   6931: iand
        //   6932: ifeq  7071 (offset +139)
        //   6935: aload_2
        //   6936: bipush  48
        //   6938: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6941: ifeq  6955 (offset +14)
        //   6944: aload  5
        //   6946: getfield  #122 // jnr.x86asm.InstructionDescription.o1Flags:I
        //   6949: bipush  8
        //   6951: iand
        //   6952: ifeq  7071 (offset +119)
        //   6955: aload_3
        //   6956: bipush  96
        //   6958: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6961: ifeq  6975 (offset +14)
        //   6964: aload  5
        //   6966: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   6969: bipush  16
        //   6971: iand
        //   6972: ifeq  7071 (offset +99)
        //   6975: aload_3
        //   6976: bipush  112
        //   6978: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   6981: ifeq  6995 (offset +14)
        //   6984: aload  5
        //   6986: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   6989: bipush  32
        //   6991: iand
        //   6992: ifeq  7071 (offset +79)
        //   6995: aload_3
        //   6996: bipush  32
        //   6998: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   7001: ifeq  7014 (offset +13)
        //   7004: aload  5
        //   7006: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   7009: iconst_4
        //   7010: iand
        //   7011: ifeq  7071 (offset +60)
        //   7014: aload_3
        //   7015: bipush  48
        //   7017: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   7020: ifeq  7034 (offset +14)
        //   7023: aload  5
        //   7025: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   7028: bipush  8
        //   7030: iand
        //   7031: ifeq  7071 (offset +40)
        //   7034: aload_3
        //   7035: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   7038: ifeq  7052 (offset +14)
        //   7041: aload  5
        //   7043: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   7046: bipush  64
        //   7048: iand
        //   7049: ifeq  7071 (offset +22)
        //   7052: aload_3
        //   7053: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //   7056: ifeq  7081 (offset +25)
        //   7059: aload  5
        //   7061: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   7064: sipush  128
        //   7067: iand
        //   7068: ifne  7081 (offset +13)
        //   7071: new  #34 // java.lang.IllegalStateException
        //   7074: dup
        //   7075: ldc  #20 // 'illegal instruction'
        //   7077: invokespecial  #141 // java.lang.IllegalStateException.<init>:(Ljava/lang/String;)V
        //   7080: athrow
        //   7081: aload  5
        //   7083: getfield  #122 // jnr.x86asm.InstructionDescription.o1Flags:I
        //   7086: bipush  48
        //   7088: iand
        //   7089: bipush  48
        //   7091: if_icmpne  7103 (offset +12)
        //   7094: aload_2
        //   7095: bipush  112
        //   7097: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   7100: ifne  7125 (offset +25)
        //   7103: aload  5
        //   7105: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   7108: bipush  48
        //   7110: iand
        //   7111: bipush  48
        //   7113: if_icmpne  7130 (offset +17)
        //   7116: aload_3
        //   7117: bipush  112
        //   7119: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   7122: ifeq  7130 (offset +8)
        //   7125: ldc  #10 // 1711276032
        //   7127: goto  7131 (offset +4)
        //   7130: iconst_0
        //   7131: istore  6
        //   7133: aload  5
        //   7135: getfield  #122 // jnr.x86asm.InstructionDescription.o1Flags:I
        //   7138: aload  5
        //   7140: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   7143: ior
        //   7144: iconst_1
        //   7145: iand
        //   7146: ifeq  7153 (offset +7)
        //   7149: iconst_0
        //   7150: goto  7179 (offset +29)
        //   7153: aload_2
        //   7154: bipush  48
        //   7156: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   7159: ifne  7171 (offset +12)
        //   7162: aload_2
        //   7163: bipush  48
        //   7165: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   7168: ifeq  7175 (offset +7)
        //   7171: iconst_1
        //   7172: goto  7176 (offset +4)
        //   7175: iconst_0
        //   7176: invokestatic  #173 // jnr.x86asm.Assembler.intValue:(Z)I
        //   7179: istore  7
        //   7181: aload_3
        //   7182: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   7185: ifeq  7239 (offset +54)
        //   7188: aload  5
        //   7190: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   7193: bipush  60
        //   7195: iand
        //   7196: ifne  7209 (offset +13)
        //   7199: new  #34 // java.lang.IllegalStateException
        //   7202: dup
        //   7203: ldc  #20 // 'illegal instruction'
        //   7205: invokespecial  #141 // java.lang.IllegalStateException.<init>:(Ljava/lang/String;)V
        //   7208: athrow
        //   7209: aload_0
        //   7210: aload  5
        //   7212: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   7215: iload  6
        //   7217: ior
        //   7218: iload  7
        //   7220: aload_2
        //   7221: checkcast  #44 // jnr.x86asm.BaseReg
        //   7224: invokevirtual  #181 // jnr.x86asm.BaseReg.code:()I
        //   7227: aload_3
        //   7228: checkcast  #44 // jnr.x86asm.BaseReg
        //   7231: checkcast  #44 // jnr.x86asm.BaseReg
        //   7234: iconst_0
        //   7235: invokevirtual  #154 // jnr.x86asm.Assembler._emitMmu:(IIILjnr/x86asm/Operand;I)V
        //   7238: return
        //   7239: aload_3
        //   7240: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   7243: ifeq  7297 (offset +54)
        //   7246: aload  5
        //   7248: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   7251: bipush  64
        //   7253: iand
        //   7254: ifne  7267 (offset +13)
        //   7257: new  #34 // java.lang.IllegalStateException
        //   7260: dup
        //   7261: ldc  #20 // 'illegal instruction'
        //   7263: invokespecial  #141 // java.lang.IllegalStateException.<init>:(Ljava/lang/String;)V
        //   7266: athrow
        //   7267: aload_0
        //   7268: aload  5
        //   7270: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   7273: iload  6
        //   7275: ior
        //   7276: iload  7
        //   7278: aload_2
        //   7279: checkcast  #44 // jnr.x86asm.BaseReg
        //   7282: invokevirtual  #181 // jnr.x86asm.BaseReg.code:()I
        //   7285: aload_3
        //   7286: checkcast  #58 // jnr.x86asm.Mem
        //   7289: checkcast  #58 // jnr.x86asm.Mem
        //   7292: iconst_0
        //   7293: invokevirtual  #154 // jnr.x86asm.Assembler._emitMmu:(IIILjnr/x86asm/Operand;I)V
        //   7296: return
        //   7297: aload_3
        //   7298: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //   7301: ifeq  7913 (offset +612)
        //   7304: aload  5
        //   7306: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   7309: sipush  128
        //   7312: iand
        //   7313: ifne  7326 (offset +13)
        //   7316: new  #34 // java.lang.IllegalStateException
        //   7319: dup
        //   7320: ldc  #20 // 'illegal instruction'
        //   7322: invokespecial  #141 // java.lang.IllegalStateException.<init>:(Ljava/lang/String;)V
        //   7325: athrow
        //   7326: aload_0
        //   7327: aload  5
        //   7329: getfield  #125 // jnr.x86asm.InstructionDescription.opCode2:I
        //   7332: iload  6
        //   7334: ior
        //   7335: iload  7
        //   7337: aload  5
        //   7339: getfield  #126 // jnr.x86asm.InstructionDescription.opCodeR:I
        //   7342: aload_2
        //   7343: checkcast  #44 // jnr.x86asm.BaseReg
        //   7346: checkcast  #44 // jnr.x86asm.BaseReg
        //   7349: iconst_1
        //   7350: invokevirtual  #154 // jnr.x86asm.Assembler._emitMmu:(IIILjnr/x86asm/Operand;I)V
        //   7353: aload_0
        //   7354: aload_3
        //   7355: checkcast  #51 // jnr.x86asm.Immediate
        //   7358: checkcast  #51 // jnr.x86asm.Immediate
        //   7361: iconst_1
        //   7362: invokevirtual  #151 // jnr.x86asm.Assembler._emitImmediate:(Ljnr/x86asm/Immediate;I)V
        //   7365: return
        //   7366: getstatic  #80 // jnr.x86asm.Assembler.$assertionsDisabled:Z
        //   7369: ifne  7388 (offset +19)
        //   7372: aload  5
        //   7374: getfield  #122 // jnr.x86asm.InstructionDescription.o1Flags:I
        //   7377: ifne  7388 (offset +11)
        //   7380: new  #31 // java.lang.AssertionError
        //   7383: dup
        //   7384: invokespecial  #138 // java.lang.AssertionError.<init>:()V
        //   7387: athrow
        //   7388: getstatic  #80 // jnr.x86asm.Assembler.$assertionsDisabled:Z
        //   7391: ifne  7410 (offset +19)
        //   7394: aload  5
        //   7396: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   7399: ifne  7410 (offset +11)
        //   7402: new  #31 // java.lang.AssertionError
        //   7405: dup
        //   7406: invokespecial  #138 // java.lang.AssertionError.<init>:()V
        //   7409: athrow
        //   7410: aload_2
        //   7411: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   7414: ifeq  7601 (offset +187)
        //   7417: aload_2
        //   7418: bipush  96
        //   7420: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   7423: ifeq  7437 (offset +14)
        //   7426: aload  5
        //   7428: getfield  #122 // jnr.x86asm.InstructionDescription.o1Flags:I
        //   7431: bipush  16
        //   7433: iand
        //   7434: ifeq  7601 (offset +167)
        //   7437: aload_2
        //   7438: bipush  112
        //   7440: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   7443: ifeq  7457 (offset +14)
        //   7446: aload  5
        //   7448: getfield  #122 // jnr.x86asm.InstructionDescription.o1Flags:I
        //   7451: bipush  32
        //   7453: iand
        //   7454: ifeq  7601 (offset +147)
        //   7457: aload_2
        //   7458: bipush  32
        //   7460: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   7463: ifeq  7476 (offset +13)
        //   7466: aload  5
        //   7468: getfield  #122 // jnr.x86asm.InstructionDescription.o1Flags:I
        //   7471: iconst_4
        //   7472: iand
        //   7473: ifeq  7601 (offset +128)
        //   7476: aload_2
        //   7477: bipush  48
        //   7479: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   7482: ifeq  7496 (offset +14)
        //   7485: aload  5
        //   7487: getfield  #122 // jnr.x86asm.InstructionDescription.o1Flags:I
        //   7490: bipush  8
        //   7492: iand
        //   7493: ifeq  7601 (offset +108)
        //   7496: aload_3
        //   7497: bipush  96
        //   7499: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   7502: ifeq  7516 (offset +14)
        //   7505: aload  5
        //   7507: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   7510: bipush  16
        //   7512: iand
        //   7513: ifeq  7601 (offset +88)
        //   7516: aload_3
        //   7517: bipush  112
        //   7519: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   7522: ifeq  7536 (offset +14)
        //   7525: aload  5
        //   7527: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   7530: bipush  32
        //   7532: iand
        //   7533: ifeq  7601 (offset +68)
        //   7536: aload_3
        //   7537: bipush  32
        //   7539: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   7542: ifeq  7555 (offset +13)
        //   7545: aload  5
        //   7547: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   7550: iconst_4
        //   7551: iand
        //   7552: ifeq  7601 (offset +49)
        //   7555: aload_3
        //   7556: bipush  48
        //   7558: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   7561: ifeq  7575 (offset +14)
        //   7564: aload  5
        //   7566: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   7569: bipush  8
        //   7571: iand
        //   7572: ifeq  7601 (offset +29)
        //   7575: aload_3
        //   7576: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   7579: ifeq  7593 (offset +14)
        //   7582: aload  5
        //   7584: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   7587: bipush  64
        //   7589: iand
        //   7590: ifeq  7601 (offset +11)
        //   7593: aload  4
        //   7595: invokevirtual  #229 // jnr.x86asm.Operand.isImm:()Z
        //   7598: ifne  7611 (offset +13)
        //   7601: new  #34 // java.lang.IllegalStateException
        //   7604: dup
        //   7605: ldc  #20 // 'illegal instruction'
        //   7607: invokespecial  #141 // java.lang.IllegalStateException.<init>:(Ljava/lang/String;)V
        //   7610: athrow
        //   7611: aload  5
        //   7613: getfield  #122 // jnr.x86asm.InstructionDescription.o1Flags:I
        //   7616: bipush  48
        //   7618: iand
        //   7619: bipush  48
        //   7621: if_icmpne  7633 (offset +12)
        //   7624: aload_2
        //   7625: bipush  112
        //   7627: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   7630: ifne  7655 (offset +25)
        //   7633: aload  5
        //   7635: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   7638: bipush  48
        //   7640: iand
        //   7641: bipush  48
        //   7643: if_icmpne  7660 (offset +17)
        //   7646: aload_3
        //   7647: bipush  112
        //   7649: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   7652: ifeq  7660 (offset +8)
        //   7655: ldc  #10 // 1711276032
        //   7657: goto  7661 (offset +4)
        //   7660: iconst_0
        //   7661: istore  6
        //   7663: aload  5
        //   7665: getfield  #122 // jnr.x86asm.InstructionDescription.o1Flags:I
        //   7668: aload  5
        //   7670: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   7673: ior
        //   7674: iconst_1
        //   7675: iand
        //   7676: ifeq  7683 (offset +7)
        //   7679: iconst_0
        //   7680: goto  7709 (offset +29)
        //   7683: aload_2
        //   7684: bipush  48
        //   7686: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   7689: ifne  7701 (offset +12)
        //   7692: aload_2
        //   7693: bipush  48
        //   7695: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   7698: ifeq  7705 (offset +7)
        //   7701: iconst_1
        //   7702: goto  7706 (offset +4)
        //   7705: iconst_0
        //   7706: invokestatic  #173 // jnr.x86asm.Assembler.intValue:(Z)I
        //   7709: istore  7
        //   7711: aload_3
        //   7712: invokevirtual  #233 // jnr.x86asm.Operand.isReg:()Z
        //   7715: ifeq  7782 (offset +67)
        //   7718: aload  5
        //   7720: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   7723: bipush  60
        //   7725: iand
        //   7726: ifne  7739 (offset +13)
        //   7729: new  #34 // java.lang.IllegalStateException
        //   7732: dup
        //   7733: ldc  #20 // 'illegal instruction'
        //   7735: invokespecial  #141 // java.lang.IllegalStateException.<init>:(Ljava/lang/String;)V
        //   7738: athrow
        //   7739: aload_0
        //   7740: aload  5
        //   7742: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   7745: iload  6
        //   7747: ior
        //   7748: iload  7
        //   7750: aload_2
        //   7751: checkcast  #44 // jnr.x86asm.BaseReg
        //   7754: invokevirtual  #181 // jnr.x86asm.BaseReg.code:()I
        //   7757: aload_3
        //   7758: checkcast  #44 // jnr.x86asm.BaseReg
        //   7761: checkcast  #44 // jnr.x86asm.BaseReg
        //   7764: iconst_1
        //   7765: invokevirtual  #154 // jnr.x86asm.Assembler._emitMmu:(IIILjnr/x86asm/Operand;I)V
        //   7768: aload_0
        //   7769: aload  4
        //   7771: checkcast  #51 // jnr.x86asm.Immediate
        //   7774: checkcast  #51 // jnr.x86asm.Immediate
        //   7777: iconst_1
        //   7778: invokevirtual  #151 // jnr.x86asm.Assembler._emitImmediate:(Ljnr/x86asm/Immediate;I)V
        //   7781: return
        //   7782: aload_3
        //   7783: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   7786: ifeq  7913 (offset +127)
        //   7789: aload  5
        //   7791: getfield  #123 // jnr.x86asm.InstructionDescription.o2Flags:I
        //   7794: bipush  64
        //   7796: iand
        //   7797: ifne  7810 (offset +13)
        //   7800: new  #34 // java.lang.IllegalStateException
        //   7803: dup
        //   7804: ldc  #20 // 'illegal instruction'
        //   7806: invokespecial  #141 // java.lang.IllegalStateException.<init>:(Ljava/lang/String;)V
        //   7809: athrow
        //   7810: aload_0
        //   7811: aload  5
        //   7813: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   7816: iload  6
        //   7818: ior
        //   7819: iload  7
        //   7821: aload_2
        //   7822: checkcast  #44 // jnr.x86asm.BaseReg
        //   7825: invokevirtual  #181 // jnr.x86asm.BaseReg.code:()I
        //   7828: aload_3
        //   7829: checkcast  #58 // jnr.x86asm.Mem
        //   7832: checkcast  #58 // jnr.x86asm.Mem
        //   7835: iconst_1
        //   7836: invokevirtual  #154 // jnr.x86asm.Assembler._emitMmu:(IIILjnr/x86asm/Operand;I)V
        //   7839: aload_0
        //   7840: aload  4
        //   7842: checkcast  #51 // jnr.x86asm.Immediate
        //   7845: checkcast  #51 // jnr.x86asm.Immediate
        //   7848: iconst_1
        //   7849: invokevirtual  #151 // jnr.x86asm.Assembler._emitImmediate:(Ljnr/x86asm/Immediate;I)V
        //   7852: return
        //   7853: aload_2
        //   7854: bipush  96
        //   7856: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   7859: ifeq  7913 (offset +54)
        //   7862: aload_3
        //   7863: bipush  96
        //   7865: invokevirtual  #238 // jnr.x86asm.Operand.isRegType:(I)Z
        //   7868: ifne  7878 (offset +10)
        //   7871: aload_3
        //   7872: invokevirtual  #231 // jnr.x86asm.Operand.isMem:()Z
        //   7875: ifeq  7913 (offset +38)
        //   7878: aload_0
        //   7879: aload  5
        //   7881: getfield  #124 // jnr.x86asm.InstructionDescription.opCode1:I
        //   7884: iconst_0
        //   7885: aload_2
        //   7886: checkcast  #44 // jnr.x86asm.BaseReg
        //   7889: invokevirtual  #181 // jnr.x86asm.BaseReg.code:()I
        //   7892: aload_3
        //   7893: checkcast  #58 // jnr.x86asm.Mem
        //   7896: checkcast  #58 // jnr.x86asm.Mem
        //   7899: iconst_1
        //   7900: invokevirtual  #154 // jnr.x86asm.Assembler._emitMmu:(IIILjnr/x86asm/Operand;I)V
        //   7903: aload_0
        //   7904: aload  5
        //   7906: getfield  #125 // jnr.x86asm.InstructionDescription.opCode2:I
        //   7909: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //   7912: return
        //   7913: return
    }

   void _emitFpu(int arg0) {
        _emitOpCode(arg0);
    }

   void _emitFpuSTI(int arg0, int arg1) {
        if ($assertionsDisabled) {
            _emitOpCode(arg0 + arg1);
            return;
        } else {
            if (0 > arg1) {
                throw new AssertionError();
            } else {
                if (arg1 < 8) {
                    _emitOpCode(arg0 + arg1);
                    return;
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

   void _emitFpuMEM(int arg0, int arg1, Mem arg2) {
        _emitSegmentPrefix(arg2);
        if ((arg0 & -16777216) != 0) {
            _emitByte((arg0 & -16777216) >> 24);
        }
        if (is64()) {
            _emitRexRM(0, arg1, arg2);
        }
        if ((arg0 & 16711680) != 0) {
            _emitByte((arg0 & 16711680) >> 16);
        }
        if ((arg0 & 65280) != 0) {
            _emitByte((arg0 & 65280) >> 8);
        }
        _emitByte(arg0 & 255);
        _emitModM(arg1, arg2, 0);
    }

   void _emitMmu(int arg0, int arg1, int arg2, Operand arg3, int arg4) {
        _emitSegmentPrefix(arg3);
        if ((arg0 & -16777216) != 0) {
            _emitByte((arg0 & -16777216) >> 24);
        }
        if (is64()) {
            _emitRexRM(arg1, arg2, arg3);
        }
        if ((arg0 & 16711680) != 0) {
            _emitByte((arg0 & 16711680) >> 16);
        }
        _emitByte((arg0 & 65280) >> 8);
        _emitByte(arg0 & 255);
        if (!arg3.isReg()) {
            _emitModM(arg2, ((Mem) arg3), arg4);
        } else {
            _emitModR(arg2, (((BaseReg) arg3)).code());
        }
    }

   LinkData _emitDisplacement(Label arg0, long arg1, int arg2) {
        if ($assertionsDisabled) {
            if ($assertionsDisabled) {
                LinkData var5 = new LinkData(offset(), arg1, -1);
                arg0.link(var5);
                if (arg2 != 1) {
                    _emitDWord(67372036);
                } else {
                    _emitByte(1);
                }
                return var5;
            } else {
                if (arg2 == 1) {
                    LinkData var5 = new LinkData(offset(), arg1, -1);
                    arg0.link(var5);
                    if (arg2 != 1) {
                        _emitDWord(67372036);
                    } else {
                        _emitByte(1);
                    }
                    return var5;
                } else {
                    if (arg2 == 4) {
                        LinkData var5 = new LinkData(offset(), arg1, -1);
                        arg0.link(var5);
                        if (arg2 != 1) {
                            _emitDWord(67372036);
                        } else {
                            _emitByte(1);
                        }
                        return var5;
                    } else {
                        throw new AssertionError();
                    }
                }
            }
        } else {
            if (!arg0.isBound()) {
                if ($assertionsDisabled) {
                    LinkData var5 = new LinkData(offset(), arg1, -1);
                    arg0.link(var5);
                    if (arg2 != 1) {
                        _emitDWord(67372036);
                    } else {
                        _emitByte(1);
                    }
                    return var5;
                } else {
                    if (arg2 == 1) {
                        LinkData var5 = new LinkData(offset(), arg1, -1);
                        arg0.link(var5);
                        if (arg2 != 1) {
                            _emitDWord(67372036);
                        } else {
                            _emitByte(1);
                        }
                        return var5;
                    } else {
                        if (arg2 == 4) {
                            LinkData var5 = new LinkData(offset(), arg1, -1);
                            arg0.link(var5);
                            if (arg2 != 1) {
                                _emitDWord(67372036);
                            } else {
                                _emitByte(1);
                            }
                            return var5;
                        } else {
                            throw new AssertionError();
                        }
                    }
                }
            } else {
                throw new AssertionError();
            }
        }
    }

   void _emitJmpOrCallReloc(InstructionGroup arg0, long arg1) {
        if (is64()) {
            _trampolineSize = _trampolineSize + 14;
        }
        RelocData var4 = new RelocData(RelocData_Type.ABSOLUTE_TO_RELATIVE_TRAMPOLINE, 4, offset(), arg1);
        _relocData.add(var4);
        _emitInt32(0);
    }

  public void relocCode(ByteBuffer arg0, long arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #172 // jnr.x86asm.Assembler.codeSize:()I
        //      4: istore  4
        //      6: aload_0
        //      7: getfield  #83 // jnr.x86asm.Assembler._buffer:Ljnr/x86asm/CodeBuffer;
        //     10: aload_1
        //     11: invokevirtual  #185 // jnr.x86asm.CodeBuffer.copyTo:(Ljava/nio/ByteBuffer;)V
        //     14: aload_0
        //     15: getfield  #86 // jnr.x86asm.Assembler._relocData:Ljava/util/List;
        //     18: invokeinterface  #259 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //     23: astore  5
        //     25: aload  5
        //     27: invokeinterface  #256 // java.util.Iterator.hasNext:()Z, count 1
        //     32: ifeq  369 (offset +337)
        //     35: aload  5
        //     37: invokeinterface  #257 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //     42: checkcast  #62 // jnr.x86asm.RelocData
        //     45: astore  6
        //     47: iconst_0
        //     48: istore  9
        //     50: getstatic  #80 // jnr.x86asm.Assembler.$assertionsDisabled:Z
        //     53: ifne  80 (offset +27)
        //     56: aload  6
        //     58: getfield  #132 // jnr.x86asm.RelocData.offset:I
        //     61: aload  6
        //     63: getfield  #133 // jnr.x86asm.RelocData.size:I
        //     66: iadd
        //     67: iload  4
        //     69: if_icmple  80 (offset +11)
        //     72: new  #31 // java.lang.AssertionError
        //     75: dup
        //     76: invokespecial  #138 // java.lang.AssertionError.<init>:()V
        //     79: athrow
        //     80: getstatic  #102 // jnr.x86asm.Assembler$1.$SwitchMap$jnr$x86asm$RelocData$Type:[I
        //     83: aload  6
        //     85: getfield  #134 // jnr.x86asm.RelocData.type:Ljnr/x86asm/RelocData$Type;
        //     88: invokevirtual  #247 // jnr.x86asm.RelocData$Type.ordinal:()I
        //     91: iaload
        //     92: tableswitch  default->214, 1->124, 2->134, 3->146, 4->146
        //    124: aload  6
        //    126: getfield  #131 // jnr.x86asm.RelocData.destination:J
        //    129: lstore  7
        //    131: goto  224 (offset +93)
        //    134: lload_2
        //    135: aload  6
        //    137: getfield  #131 // jnr.x86asm.RelocData.destination:J
        //    140: ladd
        //    141: lstore  7
        //    143: goto  224 (offset +81)
        //    146: aload  6
        //    148: getfield  #131 // jnr.x86asm.RelocData.destination:J
        //    151: lload_2
        //    152: aload  6
        //    154: getfield  #132 // jnr.x86asm.RelocData.offset:I
        //    157: i2l
        //    158: ladd
        //    159: ldc2_w  #74 // 4L
        //    162: ladd
        //    163: lsub
        //    164: lstore  7
        //    166: aload_0
        //    167: invokevirtual  #174 // jnr.x86asm.Assembler.is64:()Z
        //    170: ifeq  224 (offset +54)
        //    173: aload  6
        //    175: getfield  #134 // jnr.x86asm.RelocData.type:Ljnr/x86asm/RelocData$Type;
        //    178: getstatic  #135 // jnr.x86asm.RelocData$Type.ABSOLUTE_TO_RELATIVE_TRAMPOLINE:Ljnr/x86asm/RelocData$Type;
        //    181: if_acmpne  224 (offset +43)
        //    184: lload  7
        //    186: invokestatic  #251 // jnr.x86asm.Util.isInt32:(J)Z
        //    189: ifne  224 (offset +35)
        //    192: aload_1
        //    193: invokevirtual  #144 // java.nio.ByteBuffer.position:()I
        //    196: i2l
        //    197: aload  6
        //    199: getfield  #132 // jnr.x86asm.RelocData.offset:I
        //    202: iconst_4
        //    203: iadd
        //    204: i2l
        //    205: lsub
        //    206: lstore  7
        //    208: iconst_1
        //    209: istore  9
        //    211: goto  224 (offset +13)
        //    214: new  #34 // java.lang.IllegalStateException
        //    217: dup
        //    218: ldc  #23 // 'invalid relocation type'
        //    220: invokespecial  #141 // java.lang.IllegalStateException.<init>:(Ljava/lang/String;)V
        //    223: athrow
        //    224: aload  6
        //    226: getfield  #133 // jnr.x86asm.RelocData.size:I
        //    229: lookupswitch  default->287, 4->256, 8->272
        //    256: aload_1
        //    257: aload  6
        //    259: getfield  #132 // jnr.x86asm.RelocData.offset:I
        //    262: lload  7
        //    264: l2i
        //    265: invokevirtual  #145 // java.nio.ByteBuffer.putInt:(II)Ljava/nio/ByteBuffer;
        //    268: pop
        //    269: goto  297 (offset +28)
        //    272: aload_1
        //    273: aload  6
        //    275: getfield  #132 // jnr.x86asm.RelocData.offset:I
        //    278: lload  7
        //    280: invokevirtual  #146 // java.nio.ByteBuffer.putLong:(IJ)Ljava/nio/ByteBuffer;
        //    283: pop
        //    284: goto  297 (offset +13)
        //    287: new  #34 // java.lang.IllegalStateException
        //    290: dup
        //    291: ldc  #22 // 'invalid relocation size'
        //    293: invokespecial  #141 // java.lang.IllegalStateException.<init>:(Ljava/lang/String;)V
        //    296: athrow
        //    297: aload_0
        //    298: invokevirtual  #174 // jnr.x86asm.Assembler.is64:()Z
        //    301: ifeq  366 (offset +65)
        //    304: iload  9
        //    306: ifeq  366 (offset +60)
        //    309: aload_0
        //    310: getfield  #84 // jnr.x86asm.Assembler._logger:Ljnr/x86asm/Logger;
        //    313: ifnull  357 (offset +44)
        //    316: aload_0
        //    317: getfield  #84 // jnr.x86asm.Assembler._logger:Ljnr/x86asm/Logger;
        //    320: ldc  #15 // '; Trampoline from %x -> %x\n'
        //    322: iconst_2
        //    323: anewarray  #36 // java.lang.Object
        //    326: dup
        //    327: iconst_0
        //    328: lload_2
        //    329: aload  6
        //    331: getfield  #132 // jnr.x86asm.RelocData.offset:I
        //    334: i2l
        //    335: ladd
        //    336: invokestatic  #142 // java.lang.Long.valueOf:(J)Ljava/lang/Long;
        //    339: aastore
        //    340: dup
        //    341: iconst_1
        //    342: aload  6
        //    344: getfield  #131 // jnr.x86asm.RelocData.destination:J
        //    347: invokestatic  #142 // java.lang.Long.valueOf:(J)Ljava/lang/Long;
        //    350: aastore
        //    351: invokestatic  #143 // java.lang.String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    354: invokevirtual  #215 // jnr.x86asm.Logger.log:(Ljava/lang/String;)V
        //    357: aload_1
        //    358: aload  6
        //    360: getfield  #131 // jnr.x86asm.RelocData.destination:J
        //    363: invokestatic  #250 // jnr.x86asm.TrampolineWriter.writeTrampoline:(Ljava/nio/ByteBuffer;J)V
        //    366: goto  25 (offset -341)
        //    369: return
    }

  public void align(long arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #84 // jnr.x86asm.Assembler._logger:Ljnr/x86asm/Logger;
        //      4: ifnull  15 (offset +11)
        //      7: aload_0
        //      8: getfield  #84 // jnr.x86asm.Assembler._logger:Ljnr/x86asm/Logger;
        //     11: lload_1
        //     12: invokevirtual  #216 // jnr.x86asm.Logger.logAlign:(J)V
        //     15: lload_1
        //     16: lconst_1
        //     17: lcmp
        //     18: ifge  22 (offset +4)
        //     21: return
        //     22: lload_1
        //     23: ldc2_w  #76 // 64L
        //     26: lcmp
        //     27: ifle  53 (offset +26)
        //     30: getstatic  #80 // jnr.x86asm.Assembler.$assertionsDisabled:Z
        //     33: ifne  52 (offset +19)
        //     36: lload_1
        //     37: ldc2_w  #76 // 64L
        //     40: lcmp
        //     41: ifle  52 (offset +11)
        //     44: new  #31 // java.lang.AssertionError
        //     47: dup
        //     48: invokespecial  #138 // java.lang.AssertionError.<init>:()V
        //     51: athrow
        //     52: return
        //     53: lload_1
        //     54: aload_0
        //     55: invokevirtual  #175 // jnr.x86asm.Assembler.offset:()I
        //     58: i2l
        //     59: lload_1
        //     60: lrem
        //     61: lsub
        //     62: l2i
        //     63: istore_3
        //     64: iload_3
        //     65: i2l
        //     66: lload_1
        //     67: lcmp
        //     68: ifne  72 (offset +4)
        //     71: return
        //     72: aload_0
        //     73: getfield  #85 // jnr.x86asm.Assembler._properties:I
        //     76: iconst_1
        //     77: iand
        //     78: ifeq  625 (offset +547)
        //     81: aload_0
        //     82: getfield  #89 // jnr.x86asm.Assembler.cpuInfo:Ljnr/x86asm/CpuInfo;
        //     85: getfield  #107 // jnr.x86asm.CpuInfo.vendor:Ljnr/x86asm/CpuInfo$Vendor;
        //     88: getstatic  #109 // jnr.x86asm.CpuInfo$Vendor.INTEL:Ljnr/x86asm/CpuInfo$Vendor;
        //     91: if_acmpne  308 (offset +217)
        //     94: aload_0
        //     95: getfield  #89 // jnr.x86asm.Assembler.cpuInfo:Ljnr/x86asm/CpuInfo;
        //     98: getfield  #106 // jnr.x86asm.CpuInfo.family:I
        //    101: bipush  15
        //    103: iand
        //    104: bipush  6
        //    106: if_icmpeq  124 (offset +18)
        //    109: aload_0
        //    110: getfield  #89 // jnr.x86asm.Assembler.cpuInfo:Ljnr/x86asm/CpuInfo;
        //    113: getfield  #106 // jnr.x86asm.CpuInfo.family:I
        //    116: bipush  15
        //    118: iand
        //    119: bipush  15
        //    121: if_icmpne  308 (offset +187)
        //    124: iload_3
        //    125: tableswitch  default->263, 1->172, 2->183, 3->194, 4->205, 5->216, 6->227, 7->239, 8->251
        //    172: getstatic  #90 // jnr.x86asm.Assembler.nop1:[I
        //    175: astore  5
        //    177: iconst_1
        //    178: istore  4
        //    180: goto  272 (offset +92)
        //    183: getstatic  #93 // jnr.x86asm.Assembler.nop2:[I
        //    186: astore  5
        //    188: iconst_2
        //    189: istore  4
        //    191: goto  272 (offset +81)
        //    194: getstatic  #94 // jnr.x86asm.Assembler.nop3:[I
        //    197: astore  5
        //    199: iconst_3
        //    200: istore  4
        //    202: goto  272 (offset +70)
        //    205: getstatic  #95 // jnr.x86asm.Assembler.nop4:[I
        //    208: astore  5
        //    210: iconst_4
        //    211: istore  4
        //    213: goto  272 (offset +59)
        //    216: getstatic  #96 // jnr.x86asm.Assembler.nop5:[I
        //    219: astore  5
        //    221: iconst_5
        //    222: istore  4
        //    224: goto  272 (offset +48)
        //    227: getstatic  #97 // jnr.x86asm.Assembler.nop6:[I
        //    230: astore  5
        //    232: bipush  6
        //    234: istore  4
        //    236: goto  272 (offset +36)
        //    239: getstatic  #98 // jnr.x86asm.Assembler.nop7:[I
        //    242: astore  5
        //    244: bipush  7
        //    246: istore  4
        //    248: goto  272 (offset +24)
        //    251: getstatic  #99 // jnr.x86asm.Assembler.nop8:[I
        //    254: astore  5
        //    256: bipush  8
        //    258: istore  4
        //    260: goto  272 (offset +12)
        //    263: getstatic  #100 // jnr.x86asm.Assembler.nop9:[I
        //    266: astore  5
        //    268: bipush  9
        //    270: istore  4
        //    272: iload_3
        //    273: iload  4
        //    275: isub
        //    276: istore_3
        //    277: iconst_0
        //    278: istore  6
        //    280: iload  4
        //    282: ifle  303 (offset +21)
        //    285: aload_0
        //    286: aload  5
        //    288: iload  6
        //    290: iaload
        //    291: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //    294: iinc  6, 1
        //    297: iinc  4, -1
        //    300: goto  280 (offset -20)
        //    303: iload_3
        //    304: ifgt  124 (offset -180)
        //    307: return
        //    308: aload_0
        //    309: getfield  #89 // jnr.x86asm.Assembler.cpuInfo:Ljnr/x86asm/CpuInfo;
        //    312: getfield  #107 // jnr.x86asm.CpuInfo.vendor:Ljnr/x86asm/CpuInfo$Vendor;
        //    315: getstatic  #108 // jnr.x86asm.CpuInfo$Vendor.AMD:Ljnr/x86asm/CpuInfo$Vendor;
        //    318: if_acmpne  548 (offset +230)
        //    321: aload_0
        //    322: getfield  #89 // jnr.x86asm.Assembler.cpuInfo:Ljnr/x86asm/CpuInfo;
        //    325: getfield  #106 // jnr.x86asm.CpuInfo.family:I
        //    328: bipush  15
        //    330: if_icmplt  548 (offset +218)
        //    333: iload_3
        //    334: tableswitch  default->503, 1->388, 2->399, 3->410, 4->421, 5->432, 6->443, 7->455, 8->467, 9->479, 10->491
        //    388: getstatic  #90 // jnr.x86asm.Assembler.nop1:[I
        //    391: astore  5
        //    393: iconst_1
        //    394: istore  4
        //    396: goto  512 (offset +116)
        //    399: getstatic  #93 // jnr.x86asm.Assembler.nop2:[I
        //    402: astore  5
        //    404: iconst_2
        //    405: istore  4
        //    407: goto  512 (offset +105)
        //    410: getstatic  #94 // jnr.x86asm.Assembler.nop3:[I
        //    413: astore  5
        //    415: iconst_3
        //    416: istore  4
        //    418: goto  512 (offset +94)
        //    421: getstatic  #95 // jnr.x86asm.Assembler.nop4:[I
        //    424: astore  5
        //    426: iconst_4
        //    427: istore  4
        //    429: goto  512 (offset +83)
        //    432: getstatic  #96 // jnr.x86asm.Assembler.nop5:[I
        //    435: astore  5
        //    437: iconst_5
        //    438: istore  4
        //    440: goto  512 (offset +72)
        //    443: getstatic  #97 // jnr.x86asm.Assembler.nop6:[I
        //    446: astore  5
        //    448: bipush  6
        //    450: istore  4
        //    452: goto  512 (offset +60)
        //    455: getstatic  #98 // jnr.x86asm.Assembler.nop7:[I
        //    458: astore  5
        //    460: bipush  7
        //    462: istore  4
        //    464: goto  512 (offset +48)
        //    467: getstatic  #99 // jnr.x86asm.Assembler.nop8:[I
        //    470: astore  5
        //    472: bipush  8
        //    474: istore  4
        //    476: goto  512 (offset +36)
        //    479: getstatic  #100 // jnr.x86asm.Assembler.nop9:[I
        //    482: astore  5
        //    484: bipush  9
        //    486: istore  4
        //    488: goto  512 (offset +24)
        //    491: getstatic  #91 // jnr.x86asm.Assembler.nop10:[I
        //    494: astore  5
        //    496: bipush  10
        //    498: istore  4
        //    500: goto  512 (offset +12)
        //    503: getstatic  #92 // jnr.x86asm.Assembler.nop11:[I
        //    506: astore  5
        //    508: bipush  11
        //    510: istore  4
        //    512: iload_3
        //    513: iload  4
        //    515: isub
        //    516: istore_3
        //    517: iconst_0
        //    518: istore  6
        //    520: iload  4
        //    522: ifle  543 (offset +21)
        //    525: aload_0
        //    526: aload  5
        //    528: iload  6
        //    530: iaload
        //    531: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //    534: iinc  6, 1
        //    537: iinc  4, -1
        //    540: goto  520 (offset -20)
        //    543: iload_3
        //    544: ifgt  333 (offset -211)
        //    547: return
        //    548: aload_0
        //    549: invokevirtual  #174 // jnr.x86asm.Assembler.is64:()Z
        //    552: ifne  625 (offset +73)
        //    555: iload_3
        //    556: tableswitch  default->584, 1->611, 2->602, 3->593
        //    584: aload_0
        //    585: bipush  102
        //    587: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //    590: iinc  3, -1
        //    593: aload_0
        //    594: bipush  102
        //    596: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //    599: iinc  3, -1
        //    602: aload_0
        //    603: bipush  102
        //    605: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //    608: iinc  3, -1
        //    611: aload_0
        //    612: sipush  144
        //    615: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //    618: iinc  3, -1
        //    621: iload_3
        //    622: ifgt  555 (offset -67)
        //    625: iload_3
        //    626: iinc  3, -1
        //    629: ifle  642 (offset +13)
        //    632: aload_0
        //    633: sipush  144
        //    636: invokevirtual  #148 // jnr.x86asm.Assembler._emitByte:(I)V
        //    639: goto  625 (offset -14)
        //    642: return
    }

}