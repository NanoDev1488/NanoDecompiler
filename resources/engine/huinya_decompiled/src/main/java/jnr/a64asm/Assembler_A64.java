// исходный (обфусцированный) внутренний класс: jnr.a64asm.Assembler_A64
package jnr.a64asm;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import jnr.a64asm.CPU_A64;
import jnr.a64asm.CodeBuffer;
import jnr.a64asm.CpuInfo;
import jnr.a64asm.INST_CODE;
import jnr.a64asm.Immediate;
import jnr.a64asm.InstructionGroup;
import jnr.a64asm.Logger;
import jnr.a64asm.Operand;
import jnr.a64asm.RelocData;
import jnr.a64asm.RelocData_Type;
import jnr.a64asm.Serializer;

public final class Assembler_A64 extends Serializer {

    // ---- поля ----
  private final CodeBuffer _buffer;
  private final List _relocData;
  private final CpuInfo cpuInfo;
  private int _properties;
  private final Logger _logger;
  private final CPU_A64 cpu;
  public static final CPU_A64 Aarch_64;
  static final boolean $assertionsDisabled;

    static {
        boolean __stk1;
        __stk1 = !Assembler_A64.class.desiredAssertionStatus();
        $assertionsDisabled = __stk1;
        Aarch_64 = CPU_A64.Aarch64;
    }

   boolean is64() {
        return cpu == CPU_A64.A64;
    }

  private static final int intValue(boolean arg0) {
        return arg0;
    }

  public Assembler_A64(CPU_A64 arg0) { // было: <init>
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
        return _buffer.offset();
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
        //     53: invokevirtual  #175 // jnr.a64asm.Assembler_A64.setByteAt:(IB)V
        //     56: goto  96 (offset +40)
        //     59: aload_0
        //     60: iload_1
        //     61: lload_2
        //     62: l2i
        //     63: i2s
        //     64: invokevirtual  #178 // jnr.a64asm.Assembler_A64.setWordAt:(IS)V
        //     67: goto  96 (offset +29)
        //     70: aload_0
        //     71: iload_1
        //     72: lload_2
        //     73: l2i
        //     74: invokevirtual  #176 // jnr.a64asm.Assembler_A64.setDWordAt:(II)V
        //     77: goto  96 (offset +19)
        //     80: aload_0
        //     81: iload_1
        //     82: lload_2
        //     83: invokevirtual  #177 // jnr.a64asm.Assembler_A64.setQWordAt:(IJ)V
        //     86: new  #22 // java.lang.IllegalArgumentException
        //     89: dup
        //     90: ldc  #18 // 'invalid size'
        //     92: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
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

   void _emitImmediate(Immediate arg0, int arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: iload_2
        //      1: tableswitch  default->109, 1->48, 2->59, 3->109, 4->70, 5->109, 6->109, 7->109, 8->81
        //     48: aload_0
        //     49: aload_1
        //     50: invokevirtual  #197 // jnr.a64asm.Immediate.byteValue:()B
        //     53: invokevirtual  #166 // jnr.a64asm.Assembler_A64._emitByte:(I)V
        //     56: goto  119 (offset +63)
        //     59: aload_0
        //     60: aload_1
        //     61: invokevirtual  #200 // jnr.a64asm.Immediate.shortValue:()S
        //     64: invokevirtual  #171 // jnr.a64asm.Assembler_A64._emitWord:(I)V
        //     67: goto  119 (offset +52)
        //     70: aload_0
        //     71: aload_1
        //     72: invokevirtual  #198 // jnr.a64asm.Immediate.intValue:()I
        //     75: invokevirtual  #167 // jnr.a64asm.Assembler_A64._emitDWord:(I)V
        //     78: goto  119 (offset +41)
        //     81: aload_0
        //     82: invokevirtual  #173 // jnr.a64asm.Assembler_A64.is64:()Z
        //     85: ifne  98 (offset +13)
        //     88: new  #22 // java.lang.IllegalArgumentException
        //     91: dup
        //     92: ldc  #11 // '64 bit immediate values not supported for 32bit'
        //     94: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //     97: athrow
        //     98: aload_0
        //     99: aload_1
        //    100: invokevirtual  #199 // jnr.a64asm.Immediate.longValue:()J
        //    103: invokevirtual  #170 // jnr.a64asm.Assembler_A64._emitQWord:(J)V
        //    106: goto  119 (offset +13)
        //    109: new  #22 // java.lang.IllegalArgumentException
        //    112: dup
        //    113: ldc  #15 // 'invalid immediate operand size'
        //    115: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    118: athrow
        //    119: return
    }

   void _emita64(INST_CODE arg0, Operand arg1, Operand arg2, Operand arg3, Operand arg4, Operand arg5) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: invokestatic  #202 // jnr.a64asm.InstructionDescription.find:(Ljnr/a64asm/INST_CODE;)Ljnr/a64asm/InstructionDescription;
        //      4: astore  7
        //      6: getstatic  #107 // jnr.a64asm.Assembler_A64$1.$SwitchMap$jnr$a64asm$InstructionGroup:[I
        //      9: aload  7
        //     11: getfield  #138 // jnr.a64asm.InstructionDescription.group:Ljnr/a64asm/InstructionGroup;
        //     14: invokevirtual  #203 // jnr.a64asm.InstructionGroup.ordinal:()I
        //     17: iaload
        //     18: tableswitch  default->5814, 1->180, 2->180, 3->406, 4->406, 5->700, 6->893, 7->934, 8->1027, 9->1129, 10->1197, 11->1197, 12->1397, 13->1689, 14->1689, 15->1689, 16->1874, 17->1942, 18->2158, 19->2158, 20->2865, 21->2865, 22->3162, 23->3444, 24->3444, 25->3635, 26->3635, 27->3785, 28->3893, 29->3893, 30->3893, 31->4225, 32->4403, 33->4580, 34->4806, 35->4981, 36->5095, 37->5687
        //    180: iconst_0
        //    181: istore  8
        //    183: aload_2
        //    184: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //    187: ifeq  205 (offset +18)
        //    190: aload_3
        //    191: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //    194: ifeq  205 (offset +11)
        //    197: aload  4
        //    199: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //    202: ifne  218 (offset +16)
        //    205: aload  5
        //    207: ifnull  396 (offset +189)
        //    210: aload  5
        //    212: invokevirtual  #208 // jnr.a64asm.Operand.isExtend:()Z
        //    215: ifeq  396 (offset +181)
        //    218: aload_2
        //    219: checkcast  #46 // jnr.a64asm.Register
        //    222: astore  9
        //    224: aload_3
        //    225: checkcast  #46 // jnr.a64asm.Register
        //    228: astore  10
        //    230: aload  4
        //    232: checkcast  #46 // jnr.a64asm.Register
        //    235: astore  11
        //    237: aconst_null
        //    238: astore  12
        //    240: aload  5
        //    242: getstatic  #102 // jnr.a64asm.Assembler_A64._none:Ljnr/a64asm/Operand;
        //    245: if_acmpeq  263 (offset +18)
        //    248: aload  5
        //    250: invokevirtual  #208 // jnr.a64asm.Operand.isExtend:()Z
        //    253: ifeq  263 (offset +10)
        //    256: aload  5
        //    258: checkcast  #35 // jnr.a64asm.Ext
        //    261: astore  12
        //    263: aload_2
        //    264: invokevirtual  #215 // jnr.a64asm.Operand.size:()I
        //    267: bipush  64
        //    269: if_icmpne  279 (offset +10)
        //    272: iload  8
        //    274: ldc  #1 // -2147483648
        //    276: ior
        //    277: istore  8
        //    279: iload  8
        //    281: aload  9
        //    283: getfield  #151 // jnr.a64asm.Register.code:I
        //    286: bipush  31
        //    288: iand
        //    289: ior
        //    290: istore  8
        //    292: iload  8
        //    294: aload  10
        //    296: getfield  #151 // jnr.a64asm.Register.code:I
        //    299: bipush  31
        //    301: iand
        //    302: iconst_5
        //    303: ishl
        //    304: ior
        //    305: istore  8
        //    307: iload  8
        //    309: aload  11
        //    311: getfield  #151 // jnr.a64asm.Register.code:I
        //    314: bipush  31
        //    316: iand
        //    317: bipush  16
        //    319: ishl
        //    320: ior
        //    321: istore  8
        //    323: aload  7
        //    325: getfield  #138 // jnr.a64asm.InstructionDescription.group:Ljnr/a64asm/InstructionGroup;
        //    328: getstatic  #140 // jnr.a64asm.InstructionGroup.addsub_ext:Ljnr/a64asm/InstructionGroup;
        //    331: if_acmpne  377 (offset +46)
        //    334: aload  12
        //    336: ifnull  377 (offset +41)
        //    339: iload  8
        //    341: i2l
        //    342: aload  12
        //    344: invokevirtual  #196 // jnr.a64asm.Ext.value:()J
        //    347: ldc2_w  #63 // 7L
        //    350: land
        //    351: bipush  10
        //    353: lshl
        //    354: lor
        //    355: l2i
        //    356: istore  8
        //    358: iload  8
        //    360: i2l
        //    361: aload  12
        //    363: invokevirtual  #195 // jnr.a64asm.Ext.type:()J
        //    366: ldc2_w  #63 // 7L
        //    369: land
        //    370: bipush  13
        //    372: lshl
        //    373: lor
        //    374: l2i
        //    375: istore  8
        //    377: iload  8
        //    379: aload  7
        //    381: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //    384: ior
        //    385: istore  8
        //    387: aload_0
        //    388: iload  8
        //    390: invokevirtual  #168 // jnr.a64asm.Assembler_A64._emitInt32:(I)V
        //    393: goto  5814 (offset +5421)
        //    396: new  #22 // java.lang.IllegalArgumentException
        //    399: dup
        //    400: ldc  #14 // 'illegal arguments'
        //    402: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    405: athrow
        //    406: iconst_0
        //    407: istore  8
        //    409: aload_2
        //    410: getstatic  #102 // jnr.a64asm.Assembler_A64._none:Ljnr/a64asm/Operand;
        //    413: if_acmpeq  690 (offset +277)
        //    416: aload_2
        //    417: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //    420: ifeq  690 (offset +270)
        //    423: aload_3
        //    424: getstatic  #102 // jnr.a64asm.Assembler_A64._none:Ljnr/a64asm/Operand;
        //    427: if_acmpeq  690 (offset +263)
        //    430: aload_3
        //    431: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //    434: ifeq  690 (offset +256)
        //    437: aload_2
        //    438: checkcast  #46 // jnr.a64asm.Register
        //    441: astore  9
        //    443: aload_3
        //    444: checkcast  #46 // jnr.a64asm.Register
        //    447: astore  10
        //    449: aconst_null
        //    450: astore  11
        //    452: aload  4
        //    454: getstatic  #102 // jnr.a64asm.Assembler_A64._none:Ljnr/a64asm/Operand;
        //    457: if_acmpeq  475 (offset +18)
        //    460: aload  4
        //    462: invokevirtual  #209 // jnr.a64asm.Operand.isImm:()Z
        //    465: ifeq  475 (offset +10)
        //    468: aload  4
        //    470: checkcast  #37 // jnr.a64asm.Immediate
        //    473: astore  11
        //    475: aconst_null
        //    476: astore  12
        //    478: aload  5
        //    480: getstatic  #102 // jnr.a64asm.Assembler_A64._none:Ljnr/a64asm/Operand;
        //    483: if_acmpeq  493 (offset +10)
        //    486: aload  5
        //    488: checkcast  #50 // jnr.a64asm.Shift
        //    491: astore  12
        //    493: aconst_null
        //    494: astore  13
        //    496: aload  4
        //    498: getstatic  #102 // jnr.a64asm.Assembler_A64._none:Ljnr/a64asm/Operand;
        //    501: if_acmpeq  519 (offset +18)
        //    504: aload  4
        //    506: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //    509: ifeq  519 (offset +10)
        //    512: aload  4
        //    514: checkcast  #46 // jnr.a64asm.Register
        //    517: astore  13
        //    519: aload_2
        //    520: invokevirtual  #215 // jnr.a64asm.Operand.size:()I
        //    523: bipush  64
        //    525: if_icmpne  535 (offset +10)
        //    528: iload  8
        //    530: ldc  #1 // -2147483648
        //    532: ior
        //    533: istore  8
        //    535: iload  8
        //    537: aload  9
        //    539: getfield  #151 // jnr.a64asm.Register.code:I
        //    542: bipush  31
        //    544: iand
        //    545: ior
        //    546: istore  8
        //    548: iload  8
        //    550: aload  10
        //    552: getfield  #151 // jnr.a64asm.Register.code:I
        //    555: bipush  31
        //    557: iand
        //    558: bipush  16
        //    560: ishl
        //    561: ior
        //    562: istore  8
        //    564: aload  7
        //    566: getfield  #138 // jnr.a64asm.InstructionDescription.group:Ljnr/a64asm/InstructionGroup;
        //    569: getstatic  #141 // jnr.a64asm.InstructionGroup.addsub_shift:Ljnr/a64asm/InstructionGroup;
        //    572: if_acmpne  623 (offset +51)
        //    575: aload  13
        //    577: ifnull  596 (offset +19)
        //    580: iload  8
        //    582: aload  13
        //    584: getfield  #151 // jnr.a64asm.Register.code:I
        //    587: bipush  31
        //    589: iand
        //    590: bipush  16
        //    592: ishl
        //    593: ior
        //    594: istore  8
        //    596: aload  12
        //    598: ifnull  647 (offset +49)
        //    601: iload  8
        //    603: i2l
        //    604: aload  12
        //    606: invokevirtual  #226 // jnr.a64asm.Shift.value:()J
        //    609: ldc2_w  #75 // 63L
        //    612: land
        //    613: bipush  10
        //    615: lshl
        //    616: lor
        //    617: l2i
        //    618: istore  8
        //    620: goto  647 (offset +27)
        //    623: aload  11
        //    625: ifnull  647 (offset +22)
        //    628: iload  8
        //    630: i2l
        //    631: aload  11
        //    633: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //    636: ldc2_w  #81 // 4095L
        //    639: land
        //    640: bipush  10
        //    642: lshl
        //    643: lor
        //    644: l2i
        //    645: istore  8
        //    647: aload  12
        //    649: ifnull  671 (offset +22)
        //    652: iload  8
        //    654: i2l
        //    655: aload  12
        //    657: invokevirtual  #225 // jnr.a64asm.Shift.type:()J
        //    660: ldc2_w  #59 // 3L
        //    663: land
        //    664: bipush  22
        //    666: lshl
        //    667: lor
        //    668: l2i
        //    669: istore  8
        //    671: iload  8
        //    673: aload  7
        //    675: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //    678: ior
        //    679: istore  8
        //    681: aload_0
        //    682: iload  8
        //    684: invokevirtual  #168 // jnr.a64asm.Assembler_A64._emitInt32:(I)V
        //    687: goto  5814 (offset +5127)
        //    690: new  #22 // java.lang.IllegalArgumentException
        //    693: dup
        //    694: ldc  #14 // 'illegal arguments'
        //    696: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    699: athrow
        //    700: iconst_0
        //    701: istore  8
        //    703: aload_2
        //    704: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //    707: ifeq  883 (offset +176)
        //    710: aload_3
        //    711: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //    714: ifeq  883 (offset +169)
        //    717: aload_2
        //    718: checkcast  #46 // jnr.a64asm.Register
        //    721: astore  9
        //    723: aload_3
        //    724: checkcast  #46 // jnr.a64asm.Register
        //    727: astore  10
        //    729: aconst_null
        //    730: astore  11
        //    732: aload  4
        //    734: invokevirtual  #209 // jnr.a64asm.Operand.isImm:()Z
        //    737: ifeq  747 (offset +10)
        //    740: aload  4
        //    742: checkcast  #37 // jnr.a64asm.Immediate
        //    745: astore  11
        //    747: aconst_null
        //    748: astore  12
        //    750: aload  5
        //    752: invokevirtual  #209 // jnr.a64asm.Operand.isImm:()Z
        //    755: ifeq  765 (offset +10)
        //    758: aload  5
        //    760: checkcast  #37 // jnr.a64asm.Immediate
        //    763: astore  12
        //    765: aload_2
        //    766: invokevirtual  #215 // jnr.a64asm.Operand.size:()I
        //    769: bipush  64
        //    771: if_icmpne  788 (offset +17)
        //    774: iload  8
        //    776: ldc  #1 // -2147483648
        //    778: ior
        //    779: istore  8
        //    781: iload  8
        //    783: ldc  #8 // 4194304
        //    785: ior
        //    786: istore  8
        //    788: iload  8
        //    790: aload  9
        //    792: getfield  #151 // jnr.a64asm.Register.code:I
        //    795: bipush  31
        //    797: iand
        //    798: ior
        //    799: istore  8
        //    801: iload  8
        //    803: aload  10
        //    805: getfield  #151 // jnr.a64asm.Register.code:I
        //    808: bipush  31
        //    810: iand
        //    811: iconst_5
        //    812: ishl
        //    813: ior
        //    814: istore  8
        //    816: aload  11
        //    818: ifnull  840 (offset +22)
        //    821: iload  8
        //    823: i2l
        //    824: aload  11
        //    826: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //    829: ldc2_w  #75 // 63L
        //    832: land
        //    833: bipush  10
        //    835: lshl
        //    836: lor
        //    837: l2i
        //    838: istore  8
        //    840: aload  12
        //    842: ifnull  864 (offset +22)
        //    845: iload  8
        //    847: i2l
        //    848: aload  12
        //    850: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //    853: ldc2_w  #75 // 63L
        //    856: land
        //    857: bipush  16
        //    859: lshl
        //    860: lor
        //    861: l2i
        //    862: istore  8
        //    864: iload  8
        //    866: aload  7
        //    868: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //    871: ior
        //    872: istore  8
        //    874: aload_0
        //    875: iload  8
        //    877: invokevirtual  #168 // jnr.a64asm.Assembler_A64._emitInt32:(I)V
        //    880: goto  5814 (offset +4934)
        //    883: new  #22 // java.lang.IllegalArgumentException
        //    886: dup
        //    887: ldc  #14 // 'illegal arguments'
        //    889: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    892: athrow
        //    893: iconst_0
        //    894: istore  8
        //    896: aload_2
        //    897: getstatic  #102 // jnr.a64asm.Assembler_A64._none:Ljnr/a64asm/Operand;
        //    900: if_acmpeq  924 (offset +24)
        //    903: aload_2
        //    904: checkcast  #37 // jnr.a64asm.Immediate
        //    907: astore  9
        //    909: aload_0
        //    910: getstatic  #142 // jnr.a64asm.InstructionGroup.branch_imm:Ljnr/a64asm/InstructionGroup;
        //    913: aload  9
        //    915: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //    918: invokevirtual  #169 // jnr.a64asm.Assembler_A64._emitJmpOrCallReloc:(Ljnr/a64asm/InstructionGroup;J)V
        //    921: goto  5814 (offset +4893)
        //    924: new  #22 // java.lang.IllegalArgumentException
        //    927: dup
        //    928: ldc  #14 // 'illegal arguments'
        //    930: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    933: athrow
        //    934: iconst_0
        //    935: istore  8
        //    937: aconst_null
        //    938: astore  9
        //    940: aload_2
        //    941: getstatic  #102 // jnr.a64asm.Assembler_A64._none:Ljnr/a64asm/Operand;
        //    944: if_acmpeq  964 (offset +20)
        //    947: aload_2
        //    948: ifnull  964 (offset +16)
        //    951: aload_2
        //    952: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //    955: ifeq  964 (offset +9)
        //    958: aload_2
        //    959: checkcast  #46 // jnr.a64asm.Register
        //    962: astore  9
        //    964: aload  9
        //    966: ifnull  984 (offset +18)
        //    969: iload  8
        //    971: aload  9
        //    973: getfield  #151 // jnr.a64asm.Register.code:I
        //    976: bipush  31
        //    978: iand
        //    979: iconst_5
        //    980: ishl
        //    981: ior
        //    982: istore  8
        //    984: aload  9
        //    986: ifnonnull  1008 (offset +22)
        //    989: aload  7
        //    991: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //    994: getstatic  #129 // jnr.a64asm.INST_CODE.INST_RET_BRANCH_REG:Ljnr/a64asm/INST_CODE;
        //    997: if_acmpne  1008 (offset +11)
        //   1000: iload  8
        //   1002: sipush  960
        //   1005: ior
        //   1006: istore  8
        //   1008: iload  8
        //   1010: aload  7
        //   1012: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //   1015: ior
        //   1016: istore  8
        //   1018: aload_0
        //   1019: iload  8
        //   1021: invokevirtual  #168 // jnr.a64asm.Assembler_A64._emitInt32:(I)V
        //   1024: goto  5814 (offset +4790)
        //   1027: iconst_0
        //   1028: istore  8
        //   1030: aload_2
        //   1031: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   1034: ifeq  1119 (offset +85)
        //   1037: aload_3
        //   1038: invokevirtual  #210 // jnr.a64asm.Operand.isLabel:()Z
        //   1041: ifeq  1119 (offset +78)
        //   1044: aload_2
        //   1045: checkcast  #46 // jnr.a64asm.Register
        //   1048: astore  9
        //   1050: aload_3
        //   1051: checkcast  #40 // jnr.a64asm.Label
        //   1054: astore  10
        //   1056: aload_2
        //   1057: invokevirtual  #215 // jnr.a64asm.Operand.size:()I
        //   1060: bipush  64
        //   1062: if_icmpne  1072 (offset +10)
        //   1065: iload  8
        //   1067: ldc  #1 // -2147483648
        //   1069: ior
        //   1070: istore  8
        //   1072: iload  8
        //   1074: aload  9
        //   1076: getfield  #151 // jnr.a64asm.Register.code:I
        //   1079: bipush  31
        //   1081: iand
        //   1082: ior
        //   1083: istore  8
        //   1085: iload  8
        //   1087: aload  10
        //   1089: invokevirtual  #204 // jnr.a64asm.Label.position:()I
        //   1092: ldc  #6 // 524287
        //   1094: iand
        //   1095: iconst_5
        //   1096: ishl
        //   1097: ior
        //   1098: istore  8
        //   1100: iload  8
        //   1102: aload  7
        //   1104: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //   1107: ior
        //   1108: istore  8
        //   1110: aload_0
        //   1111: iload  8
        //   1113: invokevirtual  #168 // jnr.a64asm.Assembler_A64._emitInt32:(I)V
        //   1116: goto  5814 (offset +4698)
        //   1119: new  #22 // java.lang.IllegalArgumentException
        //   1122: dup
        //   1123: ldc  #14 // 'illegal arguments'
        //   1125: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   1128: athrow
        //   1129: iconst_0
        //   1130: istore  8
        //   1132: aload_2
        //   1133: invokevirtual  #209 // jnr.a64asm.Operand.isImm:()Z
        //   1136: ifeq  1187 (offset +51)
        //   1139: aload_2
        //   1140: checkcast  #37 // jnr.a64asm.Immediate
        //   1143: astore  9
        //   1145: aload  9
        //   1147: ifnull  1168 (offset +21)
        //   1150: iload  8
        //   1152: i2l
        //   1153: aload  9
        //   1155: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   1158: ldc2_w  #91 // 524287L
        //   1161: land
        //   1162: iconst_5
        //   1163: lshl
        //   1164: lor
        //   1165: l2i
        //   1166: istore  8
        //   1168: iload  8
        //   1170: aload  7
        //   1172: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //   1175: ior
        //   1176: istore  8
        //   1178: aload_0
        //   1179: iload  8
        //   1181: invokevirtual  #168 // jnr.a64asm.Assembler_A64._emitInt32:(I)V
        //   1184: goto  5814 (offset +4630)
        //   1187: new  #22 // java.lang.IllegalArgumentException
        //   1190: dup
        //   1191: ldc  #14 // 'illegal arguments'
        //   1193: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   1196: athrow
        //   1197: iconst_0
        //   1198: istore  8
        //   1200: aload_2
        //   1201: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   1204: ifeq  1387 (offset +183)
        //   1207: aload_3
        //   1208: invokevirtual  #209 // jnr.a64asm.Operand.isImm:()Z
        //   1211: ifeq  1387 (offset +176)
        //   1214: aload_2
        //   1215: checkcast  #46 // jnr.a64asm.Register
        //   1218: astore  9
        //   1220: aload_3
        //   1221: checkcast  #46 // jnr.a64asm.Register
        //   1224: astore  10
        //   1226: aload_3
        //   1227: checkcast  #37 // jnr.a64asm.Immediate
        //   1230: astore  11
        //   1232: aload  4
        //   1234: checkcast  #37 // jnr.a64asm.Immediate
        //   1237: astore  12
        //   1239: aload  5
        //   1241: checkcast  #33 // jnr.a64asm.Conditions
        //   1244: astore  13
        //   1246: aload  9
        //   1248: ifnull  1264 (offset +16)
        //   1251: iload  8
        //   1253: aload  9
        //   1255: getfield  #151 // jnr.a64asm.Register.code:I
        //   1258: bipush  31
        //   1260: iand
        //   1261: ior
        //   1262: istore  8
        //   1264: aload  7
        //   1266: getfield  #138 // jnr.a64asm.InstructionDescription.group:Ljnr/a64asm/InstructionGroup;
        //   1269: getstatic  #143 // jnr.a64asm.InstructionGroup.condcmp_reg:Ljnr/a64asm/InstructionGroup;
        //   1272: if_acmpne  1299 (offset +27)
        //   1275: aload  10
        //   1277: ifnull  1299 (offset +22)
        //   1280: iload  8
        //   1282: aload  10
        //   1284: getfield  #151 // jnr.a64asm.Register.code:I
        //   1287: bipush  31
        //   1289: iand
        //   1290: bipush  16
        //   1292: ishl
        //   1293: ior
        //   1294: istore  8
        //   1296: goto  1323 (offset +27)
        //   1299: aload  11
        //   1301: ifnull  1323 (offset +22)
        //   1304: iload  8
        //   1306: i2l
        //   1307: aload  11
        //   1309: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   1312: ldc2_w  #71 // 31L
        //   1315: land
        //   1316: bipush  16
        //   1318: lshl
        //   1319: lor
        //   1320: l2i
        //   1321: istore  8
        //   1323: aload  12
        //   1325: ifnull  1344 (offset +19)
        //   1328: iload  8
        //   1330: i2l
        //   1331: aload  12
        //   1333: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   1336: ldc2_w  #67 // 15L
        //   1339: land
        //   1340: lor
        //   1341: l2i
        //   1342: istore  8
        //   1344: aload  13
        //   1346: ifnull  1368 (offset +22)
        //   1349: iload  8
        //   1351: i2l
        //   1352: aload  13
        //   1354: invokevirtual  #194 // jnr.a64asm.Conditions.value:()J
        //   1357: ldc2_w  #67 // 15L
        //   1360: land
        //   1361: bipush  12
        //   1363: lshl
        //   1364: lor
        //   1365: l2i
        //   1366: istore  8
        //   1368: iload  8
        //   1370: aload  7
        //   1372: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //   1375: ior
        //   1376: istore  8
        //   1378: aload_0
        //   1379: iload  8
        //   1381: invokevirtual  #168 // jnr.a64asm.Assembler_A64._emitInt32:(I)V
        //   1384: goto  5814 (offset +4430)
        //   1387: new  #22 // java.lang.IllegalArgumentException
        //   1390: dup
        //   1391: ldc  #14 // 'illegal arguments'
        //   1393: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   1396: athrow
        //   1397: iconst_0
        //   1398: istore  8
        //   1400: aload_2
        //   1401: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   1404: ifeq  1679 (offset +275)
        //   1407: aload_2
        //   1408: checkcast  #46 // jnr.a64asm.Register
        //   1411: astore  9
        //   1413: aload_3
        //   1414: checkcast  #46 // jnr.a64asm.Register
        //   1417: astore  10
        //   1419: aload  4
        //   1421: checkcast  #46 // jnr.a64asm.Register
        //   1424: astore  11
        //   1426: aload  5
        //   1428: checkcast  #33 // jnr.a64asm.Conditions
        //   1431: astore  12
        //   1433: aload  4
        //   1435: checkcast  #33 // jnr.a64asm.Conditions
        //   1438: astore  13
        //   1440: aload_3
        //   1441: checkcast  #33 // jnr.a64asm.Conditions
        //   1444: astore  14
        //   1446: aload  9
        //   1448: ifnull  1464 (offset +16)
        //   1451: iload  8
        //   1453: aload  9
        //   1455: getfield  #151 // jnr.a64asm.Register.code:I
        //   1458: bipush  31
        //   1460: iand
        //   1461: ior
        //   1462: istore  8
        //   1464: aload  5
        //   1466: invokevirtual  #207 // jnr.a64asm.Operand.isCond:()Z
        //   1469: ifeq  1539 (offset +70)
        //   1472: aload  12
        //   1474: invokevirtual  #194 // jnr.a64asm.Conditions.value:()J
        //   1477: ldc2_w  #65 // 14L
        //   1480: land
        //   1481: ldc2_w  #65 // 14L
        //   1484: lcmp
        //   1485: ifeq  1539 (offset +54)
        //   1488: iload  8
        //   1490: i2l
        //   1491: aload  12
        //   1493: invokevirtual  #194 // jnr.a64asm.Conditions.value:()J
        //   1496: lconst_1
        //   1497: lxor
        //   1498: bipush  12
        //   1500: lshl
        //   1501: lor
        //   1502: l2i
        //   1503: istore  8
        //   1505: iload  8
        //   1507: aload  10
        //   1509: getfield  #151 // jnr.a64asm.Register.code:I
        //   1512: bipush  31
        //   1514: iand
        //   1515: iconst_5
        //   1516: ishl
        //   1517: ior
        //   1518: istore  8
        //   1520: iload  8
        //   1522: aload  11
        //   1524: getfield  #151 // jnr.a64asm.Register.code:I
        //   1527: bipush  31
        //   1529: iand
        //   1530: bipush  16
        //   1532: ishl
        //   1533: ior
        //   1534: istore  8
        //   1536: goto  1660 (offset +124)
        //   1539: aload  4
        //   1541: invokevirtual  #207 // jnr.a64asm.Operand.isCond:()Z
        //   1544: ifeq  1605 (offset +61)
        //   1547: aload  13
        //   1549: invokevirtual  #194 // jnr.a64asm.Conditions.value:()J
        //   1552: ldc2_w  #65 // 14L
        //   1555: land
        //   1556: ldc2_w  #65 // 14L
        //   1559: lcmp
        //   1560: ifeq  1605 (offset +45)
        //   1563: iload  8
        //   1565: i2l
        //   1566: aload  13
        //   1568: invokevirtual  #194 // jnr.a64asm.Conditions.value:()J
        //   1571: lconst_1
        //   1572: lxor
        //   1573: bipush  12
        //   1575: lshl
        //   1576: lor
        //   1577: l2i
        //   1578: istore  8
        //   1580: iload  8
        //   1582: aload  10
        //   1584: getfield  #151 // jnr.a64asm.Register.code:I
        //   1587: bipush  31
        //   1589: iand
        //   1590: iconst_5
        //   1591: ishl
        //   1592: ior
        //   1593: istore  8
        //   1595: iload  8
        //   1597: ldc  #7 // 2031616
        //   1599: ior
        //   1600: istore  8
        //   1602: goto  1660 (offset +58)
        //   1605: aload_3
        //   1606: invokevirtual  #207 // jnr.a64asm.Operand.isCond:()Z
        //   1609: ifeq  1660 (offset +51)
        //   1612: aload  14
        //   1614: invokevirtual  #194 // jnr.a64asm.Conditions.value:()J
        //   1617: ldc2_w  #65 // 14L
        //   1620: land
        //   1621: ldc2_w  #65 // 14L
        //   1624: lcmp
        //   1625: ifeq  1660 (offset +35)
        //   1628: iload  8
        //   1630: i2l
        //   1631: aload  13
        //   1633: invokevirtual  #194 // jnr.a64asm.Conditions.value:()J
        //   1636: lconst_1
        //   1637: lxor
        //   1638: bipush  12
        //   1640: lshl
        //   1641: lor
        //   1642: l2i
        //   1643: istore  8
        //   1645: iload  8
        //   1647: sipush  992
        //   1650: ior
        //   1651: istore  8
        //   1653: iload  8
        //   1655: ldc  #7 // 2031616
        //   1657: ior
        //   1658: istore  8
        //   1660: iload  8
        //   1662: aload  7
        //   1664: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //   1667: ior
        //   1668: istore  8
        //   1670: aload_0
        //   1671: iload  8
        //   1673: invokevirtual  #168 // jnr.a64asm.Assembler_A64._emitInt32:(I)V
        //   1676: goto  5814 (offset +4138)
        //   1679: new  #22 // java.lang.IllegalArgumentException
        //   1682: dup
        //   1683: ldc  #14 // 'illegal arguments'
        //   1685: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   1688: athrow
        //   1689: iconst_0
        //   1690: istore  8
        //   1692: aload_2
        //   1693: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   1696: ifeq  1864 (offset +168)
        //   1699: aload_3
        //   1700: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   1703: ifeq  1864 (offset +161)
        //   1706: aload_2
        //   1707: checkcast  #46 // jnr.a64asm.Register
        //   1710: astore  9
        //   1712: aload_3
        //   1713: checkcast  #46 // jnr.a64asm.Register
        //   1716: astore  10
        //   1718: aload  4
        //   1720: checkcast  #46 // jnr.a64asm.Register
        //   1723: astore  11
        //   1725: aload  5
        //   1727: checkcast  #46 // jnr.a64asm.Register
        //   1730: astore  12
        //   1732: aload  9
        //   1734: ifnull  1750 (offset +16)
        //   1737: iload  8
        //   1739: aload  9
        //   1741: getfield  #151 // jnr.a64asm.Register.code:I
        //   1744: bipush  31
        //   1746: iand
        //   1747: ior
        //   1748: istore  8
        //   1750: aload  10
        //   1752: ifnull  1770 (offset +18)
        //   1755: iload  8
        //   1757: aload  10
        //   1759: getfield  #151 // jnr.a64asm.Register.code:I
        //   1762: bipush  31
        //   1764: iand
        //   1765: iconst_5
        //   1766: ishl
        //   1767: ior
        //   1768: istore  8
        //   1770: aload  11
        //   1772: ifnull  1813 (offset +41)
        //   1775: aload  7
        //   1777: getfield  #138 // jnr.a64asm.InstructionDescription.group:Ljnr/a64asm/InstructionGroup;
        //   1780: getstatic  #144 // jnr.a64asm.InstructionGroup.dp_2src:Ljnr/a64asm/InstructionGroup;
        //   1783: if_acmpeq  1797 (offset +14)
        //   1786: aload  7
        //   1788: getfield  #138 // jnr.a64asm.InstructionDescription.group:Ljnr/a64asm/InstructionGroup;
        //   1791: getstatic  #145 // jnr.a64asm.InstructionGroup.dp_3src:Ljnr/a64asm/InstructionGroup;
        //   1794: if_acmpne  1813 (offset +19)
        //   1797: iload  8
        //   1799: aload  11
        //   1801: getfield  #151 // jnr.a64asm.Register.code:I
        //   1804: bipush  31
        //   1806: iand
        //   1807: bipush  16
        //   1809: ishl
        //   1810: ior
        //   1811: istore  8
        //   1813: aload  12
        //   1815: ifnull  1845 (offset +30)
        //   1818: aload  7
        //   1820: getfield  #138 // jnr.a64asm.InstructionDescription.group:Ljnr/a64asm/InstructionGroup;
        //   1823: getstatic  #145 // jnr.a64asm.InstructionGroup.dp_3src:Ljnr/a64asm/InstructionGroup;
        //   1826: if_acmpne  1845 (offset +19)
        //   1829: iload  8
        //   1831: aload  12
        //   1833: getfield  #151 // jnr.a64asm.Register.code:I
        //   1836: bipush  31
        //   1838: iand
        //   1839: bipush  10
        //   1841: ishl
        //   1842: ior
        //   1843: istore  8
        //   1845: iload  8
        //   1847: aload  7
        //   1849: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //   1852: ior
        //   1853: istore  8
        //   1855: aload_0
        //   1856: iload  8
        //   1858: invokevirtual  #168 // jnr.a64asm.Assembler_A64._emitInt32:(I)V
        //   1861: goto  5814 (offset +3953)
        //   1864: new  #22 // java.lang.IllegalArgumentException
        //   1867: dup
        //   1868: ldc  #14 // 'illegal arguments'
        //   1870: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   1873: athrow
        //   1874: iconst_0
        //   1875: istore  8
        //   1877: aload_2
        //   1878: invokevirtual  #209 // jnr.a64asm.Operand.isImm:()Z
        //   1881: ifeq  1932 (offset +51)
        //   1884: aload_2
        //   1885: checkcast  #37 // jnr.a64asm.Immediate
        //   1888: astore  9
        //   1890: aload  9
        //   1892: ifnull  1913 (offset +21)
        //   1895: iload  8
        //   1897: i2l
        //   1898: aload  9
        //   1900: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   1903: ldc2_w  #89 // 65535L
        //   1906: land
        //   1907: iconst_5
        //   1908: lshl
        //   1909: lor
        //   1910: l2i
        //   1911: istore  8
        //   1913: iload  8
        //   1915: aload  7
        //   1917: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //   1920: ior
        //   1921: istore  8
        //   1923: aload_0
        //   1924: iload  8
        //   1926: invokevirtual  #168 // jnr.a64asm.Assembler_A64._emitInt32:(I)V
        //   1929: goto  5814 (offset +3885)
        //   1932: new  #22 // java.lang.IllegalArgumentException
        //   1935: dup
        //   1936: ldc  #14 // 'illegal arguments'
        //   1938: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   1941: athrow
        //   1942: iconst_0
        //   1943: istore  8
        //   1945: aload_2
        //   1946: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   1949: ifeq  2148 (offset +199)
        //   1952: aload_3
        //   1953: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   1956: ifeq  2148 (offset +192)
        //   1959: aload_2
        //   1960: checkcast  #46 // jnr.a64asm.Register
        //   1963: astore  9
        //   1965: aload_3
        //   1966: checkcast  #46 // jnr.a64asm.Register
        //   1969: astore  10
        //   1971: aload  4
        //   1973: checkcast  #46 // jnr.a64asm.Register
        //   1976: astore  11
        //   1978: aload  5
        //   1980: checkcast  #37 // jnr.a64asm.Immediate
        //   1983: astore  12
        //   1985: aload  4
        //   1987: checkcast  #37 // jnr.a64asm.Immediate
        //   1990: astore  13
        //   1992: aload  9
        //   1994: ifnull  2010 (offset +16)
        //   1997: iload  8
        //   1999: aload  9
        //   2001: getfield  #151 // jnr.a64asm.Register.code:I
        //   2004: bipush  31
        //   2006: iand
        //   2007: ior
        //   2008: istore  8
        //   2010: aload  10
        //   2012: ifnull  2030 (offset +18)
        //   2015: iload  8
        //   2017: aload  10
        //   2019: getfield  #151 // jnr.a64asm.Register.code:I
        //   2022: bipush  31
        //   2024: iand
        //   2025: iconst_5
        //   2026: ishl
        //   2027: ior
        //   2028: istore  8
        //   2030: aload  4
        //   2032: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   2035: ifeq  2081 (offset +46)
        //   2038: aload  11
        //   2040: ifnull  2081 (offset +41)
        //   2043: iload  8
        //   2045: aload  11
        //   2047: getfield  #151 // jnr.a64asm.Register.code:I
        //   2050: bipush  31
        //   2052: iand
        //   2053: bipush  16
        //   2055: ishl
        //   2056: ior
        //   2057: istore  8
        //   2059: iload  8
        //   2061: i2l
        //   2062: aload  12
        //   2064: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   2067: ldc2_w  #75 // 63L
        //   2070: land
        //   2071: bipush  10
        //   2073: lshl
        //   2074: lor
        //   2075: l2i
        //   2076: istore  8
        //   2078: goto  2129 (offset +51)
        //   2081: aload  4
        //   2083: invokevirtual  #209 // jnr.a64asm.Operand.isImm:()Z
        //   2086: ifeq  2129 (offset +43)
        //   2089: aload  13
        //   2091: ifnull  2129 (offset +38)
        //   2094: iload  8
        //   2096: i2l
        //   2097: aload  13
        //   2099: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   2102: ldc2_w  #75 // 63L
        //   2105: land
        //   2106: bipush  10
        //   2108: lshl
        //   2109: lor
        //   2110: l2i
        //   2111: istore  8
        //   2113: iload  8
        //   2115: aload  10
        //   2117: getfield  #151 // jnr.a64asm.Register.code:I
        //   2120: bipush  31
        //   2122: iand
        //   2123: bipush  16
        //   2125: ishl
        //   2126: ior
        //   2127: istore  8
        //   2129: iload  8
        //   2131: aload  7
        //   2133: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //   2136: ior
        //   2137: istore  8
        //   2139: aload_0
        //   2140: iload  8
        //   2142: invokevirtual  #168 // jnr.a64asm.Assembler_A64._emitInt32:(I)V
        //   2145: goto  5814 (offset +3669)
        //   2148: new  #22 // java.lang.IllegalArgumentException
        //   2151: dup
        //   2152: ldc  #14 // 'illegal arguments'
        //   2154: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   2157: athrow
        //   2158: iconst_0
        //   2159: istore  8
        //   2161: aload_2
        //   2162: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   2165: ifne  2179 (offset +14)
        //   2168: aload  7
        //   2170: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   2173: getstatic  #125 // jnr.a64asm.INST_CODE.INST_PRFM_LDST_POS__IMMEDIATE:Ljnr/a64asm/INST_CODE;
        //   2176: if_acmpne  2855 (offset +679)
        //   2179: aconst_null
        //   2180: astore  9
        //   2182: aconst_null
        //   2183: astore  10
        //   2185: aload  7
        //   2187: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   2190: getstatic  #125 // jnr.a64asm.INST_CODE.INST_PRFM_LDST_POS__IMMEDIATE:Ljnr/a64asm/INST_CODE;
        //   2193: if_acmpne  2209 (offset +16)
        //   2196: aload_2
        //   2197: ifnull  2209 (offset +12)
        //   2200: aload_2
        //   2201: checkcast  #43 // jnr.a64asm.PRFOP_ENUM
        //   2204: astore  10
        //   2206: goto  2222 (offset +16)
        //   2209: aload_2
        //   2210: getstatic  #102 // jnr.a64asm.Assembler_A64._none:Ljnr/a64asm/Operand;
        //   2213: if_acmpeq  2222 (offset +9)
        //   2216: aload_2
        //   2217: checkcast  #46 // jnr.a64asm.Register
        //   2220: astore  9
        //   2222: aconst_null
        //   2223: astore  11
        //   2225: aconst_null
        //   2226: astore  12
        //   2228: aconst_null
        //   2229: astore  13
        //   2231: aconst_null
        //   2232: astore  14
        //   2234: aconst_null
        //   2235: astore  15
        //   2237: aconst_null
        //   2238: astore  16
        //   2240: aload_3
        //   2241: getstatic  #102 // jnr.a64asm.Assembler_A64._none:Ljnr/a64asm/Operand;
        //   2244: if_acmpeq  2263 (offset +19)
        //   2247: aload_3
        //   2248: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   2251: ifeq  2263 (offset +12)
        //   2254: aload_3
        //   2255: checkcast  #46 // jnr.a64asm.Register
        //   2258: astore  11
        //   2260: goto  2371 (offset +111)
        //   2263: aload_3
        //   2264: getstatic  #102 // jnr.a64asm.Assembler_A64._none:Ljnr/a64asm/Operand;
        //   2267: if_acmpeq  2371 (offset +104)
        //   2270: aload_3
        //   2271: invokevirtual  #213 // jnr.a64asm.Operand.isPreIndex:()Z
        //   2274: ifne  2291 (offset +17)
        //   2277: aload_3
        //   2278: invokevirtual  #212 // jnr.a64asm.Operand.isPostIndex:()Z
        //   2281: ifne  2291 (offset +10)
        //   2284: aload_3
        //   2285: invokevirtual  #211 // jnr.a64asm.Operand.isOffset:()Z
        //   2288: ifeq  2371 (offset +83)
        //   2291: aload_3
        //   2292: invokevirtual  #213 // jnr.a64asm.Operand.isPreIndex:()Z
        //   2295: ifeq  2321 (offset +26)
        //   2298: aload_3
        //   2299: checkcast  #45 // jnr.a64asm.Pre_index
        //   2302: astore  13
        //   2304: aload  13
        //   2306: invokevirtual  #220 // jnr.a64asm.Pre_index.getRegister:()Ljnr/a64asm/Register;
        //   2309: astore  11
        //   2311: aload  13
        //   2313: invokevirtual  #219 // jnr.a64asm.Pre_index.getPreIndex:()Ljnr/a64asm/Immediate;
        //   2316: astore  15
        //   2318: goto  2371 (offset +53)
        //   2321: aload_3
        //   2322: invokevirtual  #212 // jnr.a64asm.Operand.isPostIndex:()Z
        //   2325: ifeq  2351 (offset +26)
        //   2328: aload_3
        //   2329: checkcast  #44 // jnr.a64asm.Post_index
        //   2332: astore  12
        //   2334: aload  12
        //   2336: invokevirtual  #218 // jnr.a64asm.Post_index.getRegister:()Ljnr/a64asm/Register;
        //   2339: astore  11
        //   2341: aload  12
        //   2343: invokevirtual  #217 // jnr.a64asm.Post_index.getPostIndex:()Ljnr/a64asm/Immediate;
        //   2346: astore  15
        //   2348: goto  2371 (offset +23)
        //   2351: aload_3
        //   2352: checkcast  #41 // jnr.a64asm.Offset
        //   2355: astore  14
        //   2357: aload  14
        //   2359: invokevirtual  #206 // jnr.a64asm.Offset.getRegister:()Ljnr/a64asm/Register;
        //   2362: astore  11
        //   2364: aload  14
        //   2366: invokevirtual  #205 // jnr.a64asm.Offset.getOffset:()Ljnr/a64asm/Immediate;
        //   2369: astore  16
        //   2371: aload  4
        //   2373: getstatic  #102 // jnr.a64asm.Assembler_A64._none:Ljnr/a64asm/Operand;
        //   2376: if_acmpeq  2411 (offset +35)
        //   2379: aload  7
        //   2381: getfield  #138 // jnr.a64asm.InstructionDescription.group:Ljnr/a64asm/InstructionGroup;
        //   2384: getstatic  #146 // jnr.a64asm.InstructionGroup.ldst_imm9:Ljnr/a64asm/InstructionGroup;
        //   2387: if_acmpne  2411 (offset +24)
        //   2390: aload_3
        //   2391: invokevirtual  #213 // jnr.a64asm.Operand.isPreIndex:()Z
        //   2394: ifne  2411 (offset +17)
        //   2397: aload_3
        //   2398: invokevirtual  #212 // jnr.a64asm.Operand.isPostIndex:()Z
        //   2401: ifne  2411 (offset +10)
        //   2404: aload  4
        //   2406: checkcast  #37 // jnr.a64asm.Immediate
        //   2409: astore  15
        //   2411: aload  4
        //   2413: getstatic  #102 // jnr.a64asm.Assembler_A64._none:Ljnr/a64asm/Operand;
        //   2416: if_acmpeq  2451 (offset +35)
        //   2419: aload  7
        //   2421: getfield  #138 // jnr.a64asm.InstructionDescription.group:Ljnr/a64asm/InstructionGroup;
        //   2424: getstatic  #147 // jnr.a64asm.InstructionGroup.ldst_pos:Ljnr/a64asm/InstructionGroup;
        //   2427: if_acmpne  2451 (offset +24)
        //   2430: aload_3
        //   2431: invokevirtual  #213 // jnr.a64asm.Operand.isPreIndex:()Z
        //   2434: ifne  2451 (offset +17)
        //   2437: aload_3
        //   2438: invokevirtual  #212 // jnr.a64asm.Operand.isPostIndex:()Z
        //   2441: ifne  2451 (offset +10)
        //   2444: aload  4
        //   2446: checkcast  #37 // jnr.a64asm.Immediate
        //   2449: astore  16
        //   2451: aload_2
        //   2452: invokevirtual  #215 // jnr.a64asm.Operand.size:()I
        //   2455: bipush  64
        //   2457: if_icmpne  2533 (offset +76)
        //   2460: aload  7
        //   2462: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   2465: getstatic  #125 // jnr.a64asm.INST_CODE.INST_PRFM_LDST_POS__IMMEDIATE:Ljnr/a64asm/INST_CODE;
        //   2468: if_acmpeq  2533 (offset +65)
        //   2471: aload  7
        //   2473: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   2476: getstatic  #121 // jnr.a64asm.INST_CODE.INST_LDRSW_IMM_OFF:Ljnr/a64asm/INST_CODE;
        //   2479: if_acmpeq  2533 (offset +54)
        //   2482: aload  7
        //   2484: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   2487: getstatic  #118 // jnr.a64asm.INST_CODE.INST_LDRH_IMM_OFF:Ljnr/a64asm/INST_CODE;
        //   2490: if_acmpeq  2533 (offset +43)
        //   2493: aload  7
        //   2495: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   2498: getstatic  #120 // jnr.a64asm.INST_CODE.INST_LDRSH_IMM_OFF:Ljnr/a64asm/INST_CODE;
        //   2501: if_acmpeq  2533 (offset +32)
        //   2504: aload  7
        //   2506: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   2509: getstatic  #117 // jnr.a64asm.INST_CODE.INST_LDRB_IMM_OFF:Ljnr/a64asm/INST_CODE;
        //   2512: if_acmpeq  2533 (offset +21)
        //   2515: aload  7
        //   2517: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   2520: getstatic  #119 // jnr.a64asm.INST_CODE.INST_LDRSB_IMM_OFF:Ljnr/a64asm/INST_CODE;
        //   2523: if_acmpeq  2533 (offset +10)
        //   2526: iload  8
        //   2528: ldc  #10 // 1073741824
        //   2530: ior
        //   2531: istore  8
        //   2533: aload_2
        //   2534: invokevirtual  #215 // jnr.a64asm.Operand.size:()I
        //   2537: bipush  32
        //   2539: if_icmpne  2582 (offset +43)
        //   2542: aload  7
        //   2544: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   2547: getstatic  #117 // jnr.a64asm.INST_CODE.INST_LDRB_IMM_OFF:Ljnr/a64asm/INST_CODE;
        //   2550: if_acmpeq  2582 (offset +32)
        //   2553: aload  7
        //   2555: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   2558: getstatic  #120 // jnr.a64asm.INST_CODE.INST_LDRSH_IMM_OFF:Ljnr/a64asm/INST_CODE;
        //   2561: if_acmpeq  2575 (offset +14)
        //   2564: aload  7
        //   2566: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   2569: getstatic  #119 // jnr.a64asm.INST_CODE.INST_LDRSB_IMM_OFF:Ljnr/a64asm/INST_CODE;
        //   2572: if_acmpne  2582 (offset +10)
        //   2575: iload  8
        //   2577: ldc  #8 // 4194304
        //   2579: ior
        //   2580: istore  8
        //   2582: aload  9
        //   2584: ifnull  2603 (offset +19)
        //   2587: iload  8
        //   2589: aload  9
        //   2591: getfield  #151 // jnr.a64asm.Register.code:I
        //   2594: bipush  31
        //   2596: iand
        //   2597: ior
        //   2598: istore  8
        //   2600: goto  2624 (offset +24)
        //   2603: aload  10
        //   2605: ifnull  2624 (offset +19)
        //   2608: iload  8
        //   2610: i2l
        //   2611: aload  10
        //   2613: invokevirtual  #216 // jnr.a64asm.PRFOP_ENUM.intValue:()J
        //   2616: ldc2_w  #71 // 31L
        //   2619: land
        //   2620: lor
        //   2621: l2i
        //   2622: istore  8
        //   2624: aload  11
        //   2626: ifnull  2644 (offset +18)
        //   2629: iload  8
        //   2631: aload  11
        //   2633: getfield  #151 // jnr.a64asm.Register.code:I
        //   2636: bipush  31
        //   2638: iand
        //   2639: iconst_5
        //   2640: ishl
        //   2641: ior
        //   2642: istore  8
        //   2644: aload  7
        //   2646: getfield  #138 // jnr.a64asm.InstructionDescription.group:Ljnr/a64asm/InstructionGroup;
        //   2649: getstatic  #146 // jnr.a64asm.InstructionGroup.ldst_imm9:Ljnr/a64asm/InstructionGroup;
        //   2652: if_acmpne  2677 (offset +25)
        //   2655: iload  8
        //   2657: i2l
        //   2658: aload  15
        //   2660: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   2663: ldc2_w  #79 // 511L
        //   2666: land
        //   2667: bipush  12
        //   2669: lshl
        //   2670: lor
        //   2671: l2i
        //   2672: istore  8
        //   2674: goto  2836 (offset +162)
        //   2677: aload  7
        //   2679: getfield  #138 // jnr.a64asm.InstructionDescription.group:Ljnr/a64asm/InstructionGroup;
        //   2682: getstatic  #147 // jnr.a64asm.InstructionGroup.ldst_pos:Ljnr/a64asm/InstructionGroup;
        //   2685: if_acmpne  2732 (offset +47)
        //   2688: aload  7
        //   2690: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   2693: getstatic  #117 // jnr.a64asm.INST_CODE.INST_LDRB_IMM_OFF:Ljnr/a64asm/INST_CODE;
        //   2696: if_acmpeq  2710 (offset +14)
        //   2699: aload  7
        //   2701: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   2704: getstatic  #119 // jnr.a64asm.INST_CODE.INST_LDRSB_IMM_OFF:Ljnr/a64asm/INST_CODE;
        //   2707: if_acmpne  2732 (offset +25)
        //   2710: iload  8
        //   2712: i2l
        //   2713: aload  16
        //   2715: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   2718: ldc2_w  #81 // 4095L
        //   2721: land
        //   2722: bipush  10
        //   2724: lshl
        //   2725: lor
        //   2726: l2i
        //   2727: istore  8
        //   2729: goto  2836 (offset +107)
        //   2732: aload  7
        //   2734: getfield  #138 // jnr.a64asm.InstructionDescription.group:Ljnr/a64asm/InstructionGroup;
        //   2737: getstatic  #147 // jnr.a64asm.InstructionGroup.ldst_pos:Ljnr/a64asm/InstructionGroup;
        //   2740: if_acmpne  2789 (offset +49)
        //   2743: aload  7
        //   2745: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   2748: getstatic  #118 // jnr.a64asm.INST_CODE.INST_LDRH_IMM_OFF:Ljnr/a64asm/INST_CODE;
        //   2751: if_acmpeq  2765 (offset +14)
        //   2754: aload  7
        //   2756: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   2759: getstatic  #120 // jnr.a64asm.INST_CODE.INST_LDRSH_IMM_OFF:Ljnr/a64asm/INST_CODE;
        //   2762: if_acmpne  2789 (offset +27)
        //   2765: iload  8
        //   2767: i2l
        //   2768: aload  16
        //   2770: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   2773: iconst_1
        //   2774: lshr
        //   2775: ldc2_w  #81 // 4095L
        //   2778: land
        //   2779: bipush  10
        //   2781: lshl
        //   2782: lor
        //   2783: l2i
        //   2784: istore  8
        //   2786: goto  2836 (offset +50)
        //   2789: aload  7
        //   2791: getfield  #138 // jnr.a64asm.InstructionDescription.group:Ljnr/a64asm/InstructionGroup;
        //   2794: getstatic  #147 // jnr.a64asm.InstructionGroup.ldst_pos:Ljnr/a64asm/InstructionGroup;
        //   2797: if_acmpne  2836 (offset +39)
        //   2800: iload  8
        //   2802: i2l
        //   2803: aload  16
        //   2805: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   2808: aload  7
        //   2810: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   2813: getstatic  #121 // jnr.a64asm.INST_CODE.INST_LDRSW_IMM_OFF:Ljnr/a64asm/INST_CODE;
        //   2816: if_acmpne  2823 (offset +7)
        //   2819: iconst_2
        //   2820: goto  2824 (offset +4)
        //   2823: iconst_3
        //   2824: lshr
        //   2825: ldc2_w  #81 // 4095L
        //   2828: land
        //   2829: bipush  10
        //   2831: lshl
        //   2832: lor
        //   2833: l2i
        //   2834: istore  8
        //   2836: iload  8
        //   2838: aload  7
        //   2840: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //   2843: ior
        //   2844: istore  8
        //   2846: aload_0
        //   2847: iload  8
        //   2849: invokevirtual  #168 // jnr.a64asm.Assembler_A64._emitInt32:(I)V
        //   2852: goto  5814 (offset +2962)
        //   2855: new  #22 // java.lang.IllegalArgumentException
        //   2858: dup
        //   2859: ldc  #14 // 'illegal arguments'
        //   2861: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   2864: athrow
        //   2865: iconst_0
        //   2866: istore  8
        //   2868: aload_2
        //   2869: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   2872: ifeq  3152 (offset +280)
        //   2875: aload_2
        //   2876: checkcast  #46 // jnr.a64asm.Register
        //   2879: astore  9
        //   2881: aload_3
        //   2882: checkcast  #46 // jnr.a64asm.Register
        //   2885: astore  10
        //   2887: aconst_null
        //   2888: astore  11
        //   2890: aconst_null
        //   2891: astore  12
        //   2893: aconst_null
        //   2894: astore  13
        //   2896: aconst_null
        //   2897: astore  14
        //   2899: aconst_null
        //   2900: astore  15
        //   2902: aload  4
        //   2904: getstatic  #102 // jnr.a64asm.Assembler_A64._none:Ljnr/a64asm/Operand;
        //   2907: if_acmpeq  2918 (offset +11)
        //   2910: aload  4
        //   2912: invokevirtual  #213 // jnr.a64asm.Operand.isPreIndex:()Z
        //   2915: ifne  2934 (offset +19)
        //   2918: aload  4
        //   2920: invokevirtual  #212 // jnr.a64asm.Operand.isPostIndex:()Z
        //   2923: ifne  2934 (offset +11)
        //   2926: aload  4
        //   2928: invokevirtual  #211 // jnr.a64asm.Operand.isOffset:()Z
        //   2931: ifeq  3019 (offset +88)
        //   2934: aload  4
        //   2936: invokevirtual  #213 // jnr.a64asm.Operand.isPreIndex:()Z
        //   2939: ifeq  2966 (offset +27)
        //   2942: aload  4
        //   2944: checkcast  #45 // jnr.a64asm.Pre_index
        //   2947: astore  12
        //   2949: aload  12
        //   2951: invokevirtual  #220 // jnr.a64asm.Pre_index.getRegister:()Ljnr/a64asm/Register;
        //   2954: astore  15
        //   2956: aload  12
        //   2958: invokevirtual  #219 // jnr.a64asm.Pre_index.getPreIndex:()Ljnr/a64asm/Immediate;
        //   2961: astore  14
        //   2963: goto  3019 (offset +56)
        //   2966: aload  4
        //   2968: invokevirtual  #212 // jnr.a64asm.Operand.isPostIndex:()Z
        //   2971: ifeq  2998 (offset +27)
        //   2974: aload  4
        //   2976: checkcast  #44 // jnr.a64asm.Post_index
        //   2979: astore  11
        //   2981: aload  11
        //   2983: invokevirtual  #218 // jnr.a64asm.Post_index.getRegister:()Ljnr/a64asm/Register;
        //   2986: astore  15
        //   2988: aload  11
        //   2990: invokevirtual  #217 // jnr.a64asm.Post_index.getPostIndex:()Ljnr/a64asm/Immediate;
        //   2993: astore  14
        //   2995: goto  3019 (offset +24)
        //   2998: aload  4
        //   3000: checkcast  #41 // jnr.a64asm.Offset
        //   3003: astore  13
        //   3005: aload  13
        //   3007: invokevirtual  #206 // jnr.a64asm.Offset.getRegister:()Ljnr/a64asm/Register;
        //   3010: astore  15
        //   3012: aload  13
        //   3014: invokevirtual  #205 // jnr.a64asm.Offset.getOffset:()Ljnr/a64asm/Immediate;
        //   3017: astore  14
        //   3019: aload_2
        //   3020: invokevirtual  #215 // jnr.a64asm.Operand.size:()I
        //   3023: bipush  64
        //   3025: if_icmpne  3035 (offset +10)
        //   3028: iload  8
        //   3030: ldc  #1 // -2147483648
        //   3032: ior
        //   3033: istore  8
        //   3035: aload  9
        //   3037: ifnull  3053 (offset +16)
        //   3040: iload  8
        //   3042: aload  9
        //   3044: getfield  #151 // jnr.a64asm.Register.code:I
        //   3047: bipush  31
        //   3049: iand
        //   3050: ior
        //   3051: istore  8
        //   3053: aload  15
        //   3055: ifnull  3073 (offset +18)
        //   3058: iload  8
        //   3060: aload  15
        //   3062: getfield  #151 // jnr.a64asm.Register.code:I
        //   3065: bipush  31
        //   3067: iand
        //   3068: iconst_5
        //   3069: ishl
        //   3070: ior
        //   3071: istore  8
        //   3073: aload  10
        //   3075: ifnull  3094 (offset +19)
        //   3078: iload  8
        //   3080: aload  10
        //   3082: getfield  #151 // jnr.a64asm.Register.code:I
        //   3085: bipush  31
        //   3087: iand
        //   3088: bipush  10
        //   3090: ishl
        //   3091: ior
        //   3092: istore  8
        //   3094: aload  14
        //   3096: ifnull  3133 (offset +37)
        //   3099: iload  8
        //   3101: i2l
        //   3102: aload  14
        //   3104: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   3107: aload_2
        //   3108: invokevirtual  #215 // jnr.a64asm.Operand.size:()I
        //   3111: bipush  64
        //   3113: if_icmpne  3120 (offset +7)
        //   3116: iconst_3
        //   3117: goto  3121 (offset +4)
        //   3120: iconst_2
        //   3121: lshr
        //   3122: ldc2_w  #77 // 127L
        //   3125: land
        //   3126: bipush  15
        //   3128: lshl
        //   3129: lor
        //   3130: l2i
        //   3131: istore  8
        //   3133: iload  8
        //   3135: aload  7
        //   3137: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //   3140: ior
        //   3141: istore  8
        //   3143: aload_0
        //   3144: iload  8
        //   3146: invokevirtual  #168 // jnr.a64asm.Assembler_A64._emitInt32:(I)V
        //   3149: goto  5814 (offset +2665)
        //   3152: new  #22 // java.lang.IllegalArgumentException
        //   3155: dup
        //   3156: ldc  #14 // 'illegal arguments'
        //   3158: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   3161: athrow
        //   3162: iconst_0
        //   3163: istore  8
        //   3165: aload_2
        //   3166: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   3169: ifeq  3179 (offset +10)
        //   3172: aload_3
        //   3173: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   3176: ifne  3190 (offset +14)
        //   3179: aload  7
        //   3181: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   3184: getstatic  #126 // jnr.a64asm.INST_CODE.INST_PRFM_LDST_REGOFF__REGISTER:Ljnr/a64asm/INST_CODE;
        //   3187: if_acmpne  3434 (offset +247)
        //   3190: aconst_null
        //   3191: astore  9
        //   3193: aconst_null
        //   3194: astore  10
        //   3196: aload  7
        //   3198: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   3201: getstatic  #126 // jnr.a64asm.INST_CODE.INST_PRFM_LDST_REGOFF__REGISTER:Ljnr/a64asm/INST_CODE;
        //   3204: if_acmpne  3216 (offset +12)
        //   3207: aload_2
        //   3208: checkcast  #43 // jnr.a64asm.PRFOP_ENUM
        //   3211: astore  10
        //   3213: goto  3222 (offset +9)
        //   3216: aload_2
        //   3217: checkcast  #46 // jnr.a64asm.Register
        //   3220: astore  9
        //   3222: aload_3
        //   3223: checkcast  #46 // jnr.a64asm.Register
        //   3226: astore  11
        //   3228: aload  4
        //   3230: checkcast  #46 // jnr.a64asm.Register
        //   3233: astore  12
        //   3235: aload  4
        //   3237: checkcast  #35 // jnr.a64asm.Ext
        //   3240: astore  13
        //   3242: aload_2
        //   3243: invokevirtual  #215 // jnr.a64asm.Operand.size:()I
        //   3246: bipush  64
        //   3248: if_icmpne  3269 (offset +21)
        //   3251: aload  7
        //   3253: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   3256: getstatic  #126 // jnr.a64asm.INST_CODE.INST_PRFM_LDST_REGOFF__REGISTER:Ljnr/a64asm/INST_CODE;
        //   3259: if_acmpeq  3269 (offset +10)
        //   3262: iload  8
        //   3264: ldc  #10 // 1073741824
        //   3266: ior
        //   3267: istore  8
        //   3269: aload  9
        //   3271: ifnull  3290 (offset +19)
        //   3274: iload  8
        //   3276: aload  9
        //   3278: getfield  #151 // jnr.a64asm.Register.code:I
        //   3281: bipush  31
        //   3283: iand
        //   3284: ior
        //   3285: istore  8
        //   3287: goto  3306 (offset +19)
        //   3290: iload  8
        //   3292: i2l
        //   3293: aload  10
        //   3295: invokevirtual  #216 // jnr.a64asm.PRFOP_ENUM.intValue:()J
        //   3298: ldc2_w  #71 // 31L
        //   3301: land
        //   3302: lor
        //   3303: l2i
        //   3304: istore  8
        //   3306: aload  11
        //   3308: ifnull  3326 (offset +18)
        //   3311: iload  8
        //   3313: aload  11
        //   3315: getfield  #151 // jnr.a64asm.Register.code:I
        //   3318: bipush  31
        //   3320: iand
        //   3321: iconst_5
        //   3322: ishl
        //   3323: ior
        //   3324: istore  8
        //   3326: aload  12
        //   3328: ifnull  3347 (offset +19)
        //   3331: iload  8
        //   3333: aload  12
        //   3335: getfield  #151 // jnr.a64asm.Register.code:I
        //   3338: bipush  31
        //   3340: iand
        //   3341: bipush  16
        //   3343: ishl
        //   3344: ior
        //   3345: istore  8
        //   3347: aload  5
        //   3349: ifnull  3415 (offset +66)
        //   3352: aload  5
        //   3354: invokevirtual  #208 // jnr.a64asm.Operand.isExtend:()Z
        //   3357: ifeq  3415 (offset +58)
        //   3360: iload  8
        //   3362: aload  13
        //   3364: invokevirtual  #196 // jnr.a64asm.Ext.value:()J
        //   3367: ldc2_w  #59 // 3L
        //   3370: lcmp
        //   3371: ifeq  3386 (offset +15)
        //   3374: aload  13
        //   3376: invokevirtual  #196 // jnr.a64asm.Ext.value:()J
        //   3379: ldc2_w  #57 // 2L
        //   3382: lcmp
        //   3383: ifne  3392 (offset +9)
        //   3386: sipush  4096
        //   3389: goto  3393 (offset +4)
        //   3392: iconst_0
        //   3393: ior
        //   3394: istore  8
        //   3396: iload  8
        //   3398: i2l
        //   3399: aload  13
        //   3401: invokevirtual  #195 // jnr.a64asm.Ext.type:()J
        //   3404: ldc2_w  #63 // 7L
        //   3407: land
        //   3408: bipush  13
        //   3410: lshl
        //   3411: lor
        //   3412: l2i
        //   3413: istore  8
        //   3415: iload  8
        //   3417: aload  7
        //   3419: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //   3422: ior
        //   3423: istore  8
        //   3425: aload_0
        //   3426: iload  8
        //   3428: invokevirtual  #168 // jnr.a64asm.Assembler_A64._emitInt32:(I)V
        //   3431: goto  5814 (offset +2383)
        //   3434: new  #22 // java.lang.IllegalArgumentException
        //   3437: dup
        //   3438: ldc  #14 // 'illegal arguments'
        //   3440: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   3443: athrow
        //   3444: iconst_0
        //   3445: istore  8
        //   3447: aload_2
        //   3448: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   3451: ifeq  3461 (offset +10)
        //   3454: aload_3
        //   3455: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   3458: ifne  3472 (offset +14)
        //   3461: aload  7
        //   3463: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   3466: getstatic  #128 // jnr.a64asm.INST_CODE.INST_PRFUM_LDST_UNSCALED:Ljnr/a64asm/INST_CODE;
        //   3469: if_acmpne  3625 (offset +156)
        //   3472: aconst_null
        //   3473: astore  9
        //   3475: aconst_null
        //   3476: astore  10
        //   3478: aload  7
        //   3480: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   3483: getstatic  #128 // jnr.a64asm.INST_CODE.INST_PRFUM_LDST_UNSCALED:Ljnr/a64asm/INST_CODE;
        //   3486: if_acmpne  3498 (offset +12)
        //   3489: aload_2
        //   3490: checkcast  #43 // jnr.a64asm.PRFOP_ENUM
        //   3493: astore  10
        //   3495: goto  3504 (offset +9)
        //   3498: aload_2
        //   3499: checkcast  #46 // jnr.a64asm.Register
        //   3502: astore  9
        //   3504: aload_3
        //   3505: checkcast  #46 // jnr.a64asm.Register
        //   3508: astore  11
        //   3510: aload  4
        //   3512: checkcast  #37 // jnr.a64asm.Immediate
        //   3515: astore  12
        //   3517: aload  9
        //   3519: ifnull  3538 (offset +19)
        //   3522: iload  8
        //   3524: aload  9
        //   3526: getfield  #151 // jnr.a64asm.Register.code:I
        //   3529: bipush  31
        //   3531: iand
        //   3532: ior
        //   3533: istore  8
        //   3535: goto  3554 (offset +19)
        //   3538: iload  8
        //   3540: i2l
        //   3541: aload  10
        //   3543: invokevirtual  #216 // jnr.a64asm.PRFOP_ENUM.intValue:()J
        //   3546: ldc2_w  #71 // 31L
        //   3549: land
        //   3550: lor
        //   3551: l2i
        //   3552: istore  8
        //   3554: aload  11
        //   3556: ifnull  3574 (offset +18)
        //   3559: iload  8
        //   3561: aload  11
        //   3563: getfield  #151 // jnr.a64asm.Register.code:I
        //   3566: bipush  31
        //   3568: iand
        //   3569: iconst_5
        //   3570: ishl
        //   3571: ior
        //   3572: istore  8
        //   3574: aload  4
        //   3576: ifnull  3606 (offset +30)
        //   3579: aload  4
        //   3581: invokevirtual  #209 // jnr.a64asm.Operand.isImm:()Z
        //   3584: ifeq  3606 (offset +22)
        //   3587: iload  8
        //   3589: i2l
        //   3590: aload  12
        //   3592: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   3595: ldc2_w  #79 // 511L
        //   3598: land
        //   3599: bipush  12
        //   3601: lshl
        //   3602: lor
        //   3603: l2i
        //   3604: istore  8
        //   3606: iload  8
        //   3608: aload  7
        //   3610: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //   3613: ior
        //   3614: istore  8
        //   3616: aload_0
        //   3617: iload  8
        //   3619: invokevirtual  #168 // jnr.a64asm.Assembler_A64._emitInt32:(I)V
        //   3622: goto  5814 (offset +2192)
        //   3625: new  #22 // java.lang.IllegalArgumentException
        //   3628: dup
        //   3629: ldc  #14 // 'illegal arguments'
        //   3631: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   3634: athrow
        //   3635: iconst_0
        //   3636: istore  8
        //   3638: aload_2
        //   3639: checkcast  #46 // jnr.a64asm.Register
        //   3642: astore  9
        //   3644: aload_3
        //   3645: checkcast  #46 // jnr.a64asm.Register
        //   3648: astore  10
        //   3650: aconst_null
        //   3651: astore  11
        //   3653: aconst_null
        //   3654: astore  12
        //   3656: aload  4
        //   3658: ifnull  3679 (offset +21)
        //   3661: aload  4
        //   3663: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   3666: ifeq  3679 (offset +13)
        //   3669: aload  4
        //   3671: checkcast  #46 // jnr.a64asm.Register
        //   3674: astore  11
        //   3676: goto  3706 (offset +30)
        //   3679: aload  4
        //   3681: ifnull  3706 (offset +25)
        //   3684: aload  4
        //   3686: invokevirtual  #211 // jnr.a64asm.Operand.isOffset:()Z
        //   3689: ifeq  3706 (offset +17)
        //   3692: aload  4
        //   3694: checkcast  #41 // jnr.a64asm.Offset
        //   3697: astore  12
        //   3699: aload  12
        //   3701: invokevirtual  #206 // jnr.a64asm.Offset.getRegister:()Ljnr/a64asm/Register;
        //   3704: astore  11
        //   3706: iload  8
        //   3708: aload  9
        //   3710: getfield  #151 // jnr.a64asm.Register.code:I
        //   3713: bipush  31
        //   3715: iand
        //   3716: bipush  16
        //   3718: ishl
        //   3719: ior
        //   3720: istore  8
        //   3722: iload  8
        //   3724: aload  10
        //   3726: getfield  #151 // jnr.a64asm.Register.code:I
        //   3729: bipush  31
        //   3731: iand
        //   3732: ior
        //   3733: istore  8
        //   3735: aload  7
        //   3737: getfield  #138 // jnr.a64asm.InstructionDescription.group:Ljnr/a64asm/InstructionGroup;
        //   3740: getstatic  #149 // jnr.a64asm.InstructionGroup.ldstexcl_op3:Ljnr/a64asm/InstructionGroup;
        //   3743: if_acmpne  3766 (offset +23)
        //   3746: aload  11
        //   3748: ifnull  3766 (offset +18)
        //   3751: iload  8
        //   3753: aload  11
        //   3755: getfield  #151 // jnr.a64asm.Register.code:I
        //   3758: bipush  31
        //   3760: iand
        //   3761: iconst_5
        //   3762: ishl
        //   3763: ior
        //   3764: istore  8
        //   3766: iload  8
        //   3768: aload  7
        //   3770: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //   3773: ior
        //   3774: istore  8
        //   3776: aload_0
        //   3777: iload  8
        //   3779: invokevirtual  #168 // jnr.a64asm.Assembler_A64._emitInt32:(I)V
        //   3782: goto  5814 (offset +2032)
        //   3785: iconst_0
        //   3786: istore  8
        //   3788: aload_2
        //   3789: checkcast  #46 // jnr.a64asm.Register
        //   3792: astore  9
        //   3794: aload_3
        //   3795: checkcast  #46 // jnr.a64asm.Register
        //   3798: astore  10
        //   3800: aload  4
        //   3802: checkcast  #46 // jnr.a64asm.Register
        //   3805: astore  11
        //   3807: aload  4
        //   3809: checkcast  #46 // jnr.a64asm.Register
        //   3812: astore  12
        //   3814: iload  8
        //   3816: aload  9
        //   3818: getfield  #151 // jnr.a64asm.Register.code:I
        //   3821: bipush  31
        //   3823: iand
        //   3824: bipush  16
        //   3826: ishl
        //   3827: ior
        //   3828: istore  8
        //   3830: iload  8
        //   3832: aload  10
        //   3834: getfield  #151 // jnr.a64asm.Register.code:I
        //   3837: bipush  31
        //   3839: iand
        //   3840: ior
        //   3841: istore  8
        //   3843: iload  8
        //   3845: aload  11
        //   3847: getfield  #151 // jnr.a64asm.Register.code:I
        //   3850: bipush  31
        //   3852: iand
        //   3853: bipush  10
        //   3855: ishl
        //   3856: ior
        //   3857: istore  8
        //   3859: iload  8
        //   3861: aload  12
        //   3863: getfield  #151 // jnr.a64asm.Register.code:I
        //   3866: bipush  31
        //   3868: iand
        //   3869: iconst_5
        //   3870: ishl
        //   3871: ior
        //   3872: istore  8
        //   3874: iload  8
        //   3876: aload  7
        //   3878: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //   3881: ior
        //   3882: istore  8
        //   3884: aload_0
        //   3885: iload  8
        //   3887: invokevirtual  #168 // jnr.a64asm.Assembler_A64._emitInt32:(I)V
        //   3890: goto  5814 (offset +1924)
        //   3893: iconst_0
        //   3894: istore  8
        //   3896: aload_2
        //   3897: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   3900: ifeq  4215 (offset +315)
        //   3903: aload_3
        //   3904: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   3907: ifeq  4215 (offset +308)
        //   3910: aload_2
        //   3911: checkcast  #46 // jnr.a64asm.Register
        //   3914: astore  9
        //   3916: aload_3
        //   3917: checkcast  #46 // jnr.a64asm.Register
        //   3920: astore  10
        //   3922: aconst_null
        //   3923: astore  11
        //   3925: aconst_null
        //   3926: astore  12
        //   3928: aconst_null
        //   3929: astore  13
        //   3931: aconst_null
        //   3932: astore  14
        //   3934: aload  4
        //   3936: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   3939: ifeq  3952 (offset +13)
        //   3942: aload  4
        //   3944: checkcast  #46 // jnr.a64asm.Register
        //   3947: astore  11
        //   3949: goto  4013 (offset +64)
        //   3952: aload  4
        //   3954: invokevirtual  #212 // jnr.a64asm.Operand.isPostIndex:()Z
        //   3957: ifeq  3984 (offset +27)
        //   3960: aload  4
        //   3962: checkcast  #44 // jnr.a64asm.Post_index
        //   3965: astore  13
        //   3967: aload  13
        //   3969: invokevirtual  #218 // jnr.a64asm.Post_index.getRegister:()Ljnr/a64asm/Register;
        //   3972: astore  11
        //   3974: aload  13
        //   3976: invokevirtual  #217 // jnr.a64asm.Post_index.getPostIndex:()Ljnr/a64asm/Immediate;
        //   3979: astore  14
        //   3981: goto  4013 (offset +32)
        //   3984: aload  4
        //   3986: invokevirtual  #213 // jnr.a64asm.Operand.isPreIndex:()Z
        //   3989: ifeq  4013 (offset +24)
        //   3992: aload  4
        //   3994: checkcast  #45 // jnr.a64asm.Pre_index
        //   3997: astore  12
        //   3999: aload  12
        //   4001: invokevirtual  #220 // jnr.a64asm.Pre_index.getRegister:()Ljnr/a64asm/Register;
        //   4004: astore  11
        //   4006: aload  12
        //   4008: invokevirtual  #219 // jnr.a64asm.Pre_index.getPreIndex:()Ljnr/a64asm/Immediate;
        //   4011: astore  14
        //   4013: aload  5
        //   4015: getstatic  #102 // jnr.a64asm.Assembler_A64._none:Ljnr/a64asm/Operand;
        //   4018: if_acmpeq  4036 (offset +18)
        //   4021: aload  5
        //   4023: invokevirtual  #209 // jnr.a64asm.Operand.isImm:()Z
        //   4026: ifeq  4036 (offset +10)
        //   4029: aload  5
        //   4031: checkcast  #37 // jnr.a64asm.Immediate
        //   4034: astore  14
        //   4036: aload  7
        //   4038: getfield  #138 // jnr.a64asm.InstructionDescription.group:Ljnr/a64asm/InstructionGroup;
        //   4041: getstatic  #148 // jnr.a64asm.InstructionGroup.ldstexcl:Ljnr/a64asm/InstructionGroup;
        //   4044: if_acmpeq  4058 (offset +14)
        //   4047: aload  7
        //   4049: getfield  #138 // jnr.a64asm.InstructionDescription.group:Ljnr/a64asm/InstructionGroup;
        //   4052: getstatic  #150 // jnr.a64asm.InstructionGroup.ldstnapair_offs:Ljnr/a64asm/InstructionGroup;
        //   4055: if_acmpne  4097 (offset +42)
        //   4058: aload_2
        //   4059: invokevirtual  #215 // jnr.a64asm.Operand.size:()I
        //   4062: bipush  64
        //   4064: if_icmpne  4077 (offset +13)
        //   4067: iload  8
        //   4069: ldc  #10 // 1073741824
        //   4071: ior
        //   4072: istore  8
        //   4074: goto  4113 (offset +39)
        //   4077: aload_2
        //   4078: invokevirtual  #215 // jnr.a64asm.Operand.size:()I
        //   4081: sipush  128
        //   4084: if_icmpne  4113 (offset +29)
        //   4087: iload  8
        //   4089: ldc  #1 // -2147483648
        //   4091: ior
        //   4092: istore  8
        //   4094: goto  4113 (offset +19)
        //   4097: aload_2
        //   4098: invokevirtual  #215 // jnr.a64asm.Operand.size:()I
        //   4101: bipush  64
        //   4103: if_icmpne  4113 (offset +10)
        //   4106: iload  8
        //   4108: ldc  #1 // -2147483648
        //   4110: ior
        //   4111: istore  8
        //   4113: iload  8
        //   4115: aload  9
        //   4117: getfield  #151 // jnr.a64asm.Register.code:I
        //   4120: bipush  31
        //   4122: iand
        //   4123: ior
        //   4124: istore  8
        //   4126: iload  8
        //   4128: aload  10
        //   4130: getfield  #151 // jnr.a64asm.Register.code:I
        //   4133: bipush  31
        //   4135: iand
        //   4136: bipush  10
        //   4138: ishl
        //   4139: ior
        //   4140: istore  8
        //   4142: iload  8
        //   4144: aload  11
        //   4146: getfield  #151 // jnr.a64asm.Register.code:I
        //   4149: bipush  31
        //   4151: iand
        //   4152: iconst_5
        //   4153: ishl
        //   4154: ior
        //   4155: istore  8
        //   4157: aload  14
        //   4159: ifnull  4196 (offset +37)
        //   4162: iload  8
        //   4164: i2l
        //   4165: aload  14
        //   4167: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   4170: aload_2
        //   4171: invokevirtual  #215 // jnr.a64asm.Operand.size:()I
        //   4174: bipush  64
        //   4176: if_icmpne  4183 (offset +7)
        //   4179: iconst_3
        //   4180: goto  4184 (offset +4)
        //   4183: iconst_2
        //   4184: lshr
        //   4185: ldc2_w  #77 // 127L
        //   4188: land
        //   4189: bipush  15
        //   4191: lshl
        //   4192: lor
        //   4193: l2i
        //   4194: istore  8
        //   4196: iload  8
        //   4198: aload  7
        //   4200: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //   4203: ior
        //   4204: istore  8
        //   4206: aload_0
        //   4207: iload  8
        //   4209: invokevirtual  #168 // jnr.a64asm.Assembler_A64._emitInt32:(I)V
        //   4212: goto  5814 (offset +1602)
        //   4215: new  #22 // java.lang.IllegalArgumentException
        //   4218: dup
        //   4219: ldc  #14 // 'illegal arguments'
        //   4221: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   4224: athrow
        //   4225: iconst_0
        //   4226: istore  8
        //   4228: aload_2
        //   4229: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   4232: ifne  4246 (offset +14)
        //   4235: aload  7
        //   4237: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   4240: getstatic  #127 // jnr.a64asm.INST_CODE.INST_PRFM_LOADLIT__LITERAL:Ljnr/a64asm/INST_CODE;
        //   4243: if_acmpne  4393 (offset +150)
        //   4246: aconst_null
        //   4247: astore  9
        //   4249: aconst_null
        //   4250: astore  10
        //   4252: aload  7
        //   4254: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   4257: getstatic  #127 // jnr.a64asm.INST_CODE.INST_PRFM_LOADLIT__LITERAL:Ljnr/a64asm/INST_CODE;
        //   4260: if_acmpne  4272 (offset +12)
        //   4263: aload_2
        //   4264: checkcast  #43 // jnr.a64asm.PRFOP_ENUM
        //   4267: astore  10
        //   4269: goto  4278 (offset +9)
        //   4272: aload_2
        //   4273: checkcast  #46 // jnr.a64asm.Register
        //   4276: astore  9
        //   4278: aload_3
        //   4279: checkcast  #37 // jnr.a64asm.Immediate
        //   4282: astore  11
        //   4284: aload_2
        //   4285: invokevirtual  #215 // jnr.a64asm.Operand.size:()I
        //   4288: bipush  64
        //   4290: if_icmpne  4311 (offset +21)
        //   4293: aload  7
        //   4295: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   4298: getstatic  #127 // jnr.a64asm.INST_CODE.INST_PRFM_LOADLIT__LITERAL:Ljnr/a64asm/INST_CODE;
        //   4301: if_acmpeq  4311 (offset +10)
        //   4304: iload  8
        //   4306: ldc  #10 // 1073741824
        //   4308: ior
        //   4309: istore  8
        //   4311: aload  7
        //   4313: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   4316: getstatic  #127 // jnr.a64asm.INST_CODE.INST_PRFM_LOADLIT__LITERAL:Ljnr/a64asm/INST_CODE;
        //   4319: if_acmpne  4341 (offset +22)
        //   4322: iload  8
        //   4324: i2l
        //   4325: aload  10
        //   4327: invokevirtual  #216 // jnr.a64asm.PRFOP_ENUM.intValue:()J
        //   4330: ldc2_w  #71 // 31L
        //   4333: land
        //   4334: lor
        //   4335: l2i
        //   4336: istore  8
        //   4338: goto  4354 (offset +16)
        //   4341: iload  8
        //   4343: aload  9
        //   4345: getfield  #151 // jnr.a64asm.Register.code:I
        //   4348: bipush  31
        //   4350: iand
        //   4351: ior
        //   4352: istore  8
        //   4354: iload  8
        //   4356: i2l
        //   4357: aload  11
        //   4359: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   4362: iconst_2
        //   4363: lshr
        //   4364: ldc2_w  #87 // 32767L
        //   4367: land
        //   4368: iconst_5
        //   4369: lshl
        //   4370: lor
        //   4371: l2i
        //   4372: istore  8
        //   4374: iload  8
        //   4376: aload  7
        //   4378: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //   4381: ior
        //   4382: istore  8
        //   4384: aload_0
        //   4385: iload  8
        //   4387: invokevirtual  #168 // jnr.a64asm.Assembler_A64._emitInt32:(I)V
        //   4390: goto  5814 (offset +1424)
        //   4393: new  #22 // java.lang.IllegalArgumentException
        //   4396: dup
        //   4397: ldc  #14 // 'illegal arguments'
        //   4399: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   4402: athrow
        //   4403: iconst_0
        //   4404: istore  8
        //   4406: aload_2
        //   4407: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   4410: ifeq  4570 (offset +160)
        //   4413: aload_3
        //   4414: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   4417: ifeq  4570 (offset +153)
        //   4420: aload  4
        //   4422: invokevirtual  #209 // jnr.a64asm.Operand.isImm:()Z
        //   4425: ifeq  4570 (offset +145)
        //   4428: aload_2
        //   4429: checkcast  #46 // jnr.a64asm.Register
        //   4432: astore  9
        //   4434: aload_3
        //   4435: checkcast  #46 // jnr.a64asm.Register
        //   4438: astore  10
        //   4440: aload  4
        //   4442: checkcast  #37 // jnr.a64asm.Immediate
        //   4445: astore  11
        //   4447: aload_2
        //   4448: invokevirtual  #215 // jnr.a64asm.Operand.size:()I
        //   4451: bipush  64
        //   4453: if_icmpne  4466 (offset +13)
        //   4456: iload  8
        //   4458: ldc  #1 // -2147483648
        //   4460: ior
        //   4461: istore  8
        //   4463: goto  4473 (offset +10)
        //   4466: iload  8
        //   4468: ldc  #3 // -4194305
        //   4470: iand
        //   4471: istore  8
        //   4473: iload  8
        //   4475: aload  9
        //   4477: getfield  #151 // jnr.a64asm.Register.code:I
        //   4480: bipush  31
        //   4482: iand
        //   4483: ior
        //   4484: istore  8
        //   4486: iload  8
        //   4488: aload  10
        //   4490: getfield  #151 // jnr.a64asm.Register.code:I
        //   4493: bipush  31
        //   4495: iand
        //   4496: iconst_5
        //   4497: ishl
        //   4498: ior
        //   4499: istore  8
        //   4501: aload_2
        //   4502: invokevirtual  #215 // jnr.a64asm.Operand.size:()I
        //   4505: bipush  64
        //   4507: if_icmpne  4532 (offset +25)
        //   4510: iload  8
        //   4512: i2l
        //   4513: aload  11
        //   4515: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   4518: ldc2_w  #83 // 8191L
        //   4521: land
        //   4522: bipush  10
        //   4524: lshl
        //   4525: lor
        //   4526: l2i
        //   4527: istore  8
        //   4529: goto  4551 (offset +22)
        //   4532: iload  8
        //   4534: i2l
        //   4535: aload  11
        //   4537: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   4540: ldc2_w  #81 // 4095L
        //   4543: land
        //   4544: bipush  10
        //   4546: lshl
        //   4547: lor
        //   4548: l2i
        //   4549: istore  8
        //   4551: iload  8
        //   4553: aload  7
        //   4555: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //   4558: ior
        //   4559: istore  8
        //   4561: aload_0
        //   4562: iload  8
        //   4564: invokevirtual  #168 // jnr.a64asm.Assembler_A64._emitInt32:(I)V
        //   4567: goto  5814 (offset +1247)
        //   4570: new  #22 // java.lang.IllegalArgumentException
        //   4573: dup
        //   4574: ldc  #14 // 'illegal arguments'
        //   4576: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   4579: athrow
        //   4580: iconst_0
        //   4581: istore  8
        //   4583: aload_2
        //   4584: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   4587: ifeq  4796 (offset +209)
        //   4590: aload_3
        //   4591: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   4594: ifeq  4796 (offset +202)
        //   4597: aload_2
        //   4598: checkcast  #46 // jnr.a64asm.Register
        //   4601: astore  9
        //   4603: aload_3
        //   4604: checkcast  #46 // jnr.a64asm.Register
        //   4607: astore  10
        //   4609: aconst_null
        //   4610: astore  11
        //   4612: aload  4
        //   4614: getstatic  #102 // jnr.a64asm.Assembler_A64._none:Ljnr/a64asm/Operand;
        //   4617: if_acmpeq  4627 (offset +10)
        //   4620: aload  4
        //   4622: checkcast  #46 // jnr.a64asm.Register
        //   4625: astore  11
        //   4627: aconst_null
        //   4628: astore  12
        //   4630: aload  5
        //   4632: getstatic  #102 // jnr.a64asm.Assembler_A64._none:Ljnr/a64asm/Operand;
        //   4635: if_acmpeq  4645 (offset +10)
        //   4638: aload  5
        //   4640: checkcast  #50 // jnr.a64asm.Shift
        //   4643: astore  12
        //   4645: aload_2
        //   4646: invokevirtual  #215 // jnr.a64asm.Operand.size:()I
        //   4649: bipush  64
        //   4651: if_icmpne  4661 (offset +10)
        //   4654: iload  8
        //   4656: ldc  #1 // -2147483648
        //   4658: ior
        //   4659: istore  8
        //   4661: iload  8
        //   4663: aload  9
        //   4665: getfield  #151 // jnr.a64asm.Register.code:I
        //   4668: bipush  31
        //   4670: iand
        //   4671: ior
        //   4672: istore  8
        //   4674: aload  7
        //   4676: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   4679: getstatic  #122 // jnr.a64asm.INST_CODE.INST_MOV_LOG_SHIFT:Ljnr/a64asm/INST_CODE;
        //   4682: if_acmpne  4703 (offset +21)
        //   4685: iload  8
        //   4687: aload  10
        //   4689: getfield  #151 // jnr.a64asm.Register.code:I
        //   4692: bipush  31
        //   4694: iand
        //   4695: iconst_5
        //   4696: ishl
        //   4697: ior
        //   4698: istore  8
        //   4700: goto  4734 (offset +34)
        //   4703: iload  8
        //   4705: aload  10
        //   4707: getfield  #151 // jnr.a64asm.Register.code:I
        //   4710: bipush  31
        //   4712: iand
        //   4713: iconst_5
        //   4714: ishl
        //   4715: ior
        //   4716: istore  8
        //   4718: iload  8
        //   4720: aload  11
        //   4722: getfield  #151 // jnr.a64asm.Register.code:I
        //   4725: bipush  31
        //   4727: iand
        //   4728: bipush  16
        //   4730: ishl
        //   4731: ior
        //   4732: istore  8
        //   4734: aload  12
        //   4736: ifnull  4777 (offset +41)
        //   4739: iload  8
        //   4741: i2l
        //   4742: aload  12
        //   4744: invokevirtual  #226 // jnr.a64asm.Shift.value:()J
        //   4747: ldc2_w  #75 // 63L
        //   4750: land
        //   4751: bipush  10
        //   4753: lshl
        //   4754: lor
        //   4755: l2i
        //   4756: istore  8
        //   4758: iload  8
        //   4760: i2l
        //   4761: aload  12
        //   4763: invokevirtual  #225 // jnr.a64asm.Shift.type:()J
        //   4766: ldc2_w  #59 // 3L
        //   4769: land
        //   4770: bipush  22
        //   4772: lshl
        //   4773: lor
        //   4774: l2i
        //   4775: istore  8
        //   4777: iload  8
        //   4779: aload  7
        //   4781: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //   4784: ior
        //   4785: istore  8
        //   4787: aload_0
        //   4788: iload  8
        //   4790: invokevirtual  #168 // jnr.a64asm.Assembler_A64._emitInt32:(I)V
        //   4793: goto  5814 (offset +1021)
        //   4796: new  #22 // java.lang.IllegalArgumentException
        //   4799: dup
        //   4800: ldc  #14 // 'illegal arguments'
        //   4802: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   4805: athrow
        //   4806: iconst_0
        //   4807: istore  8
        //   4809: aload_2
        //   4810: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   4813: ifeq  4971 (offset +158)
        //   4816: aload_3
        //   4817: invokevirtual  #209 // jnr.a64asm.Operand.isImm:()Z
        //   4820: ifeq  4971 (offset +151)
        //   4823: aload_2
        //   4824: checkcast  #46 // jnr.a64asm.Register
        //   4827: astore  9
        //   4829: aload_3
        //   4830: checkcast  #37 // jnr.a64asm.Immediate
        //   4833: astore  10
        //   4835: aconst_null
        //   4836: astore  11
        //   4838: aload  4
        //   4840: getstatic  #102 // jnr.a64asm.Assembler_A64._none:Ljnr/a64asm/Operand;
        //   4843: if_acmpeq  4853 (offset +10)
        //   4846: aload  4
        //   4848: checkcast  #50 // jnr.a64asm.Shift
        //   4851: astore  11
        //   4853: aload_2
        //   4854: invokevirtual  #215 // jnr.a64asm.Operand.size:()I
        //   4857: bipush  64
        //   4859: if_icmpne  4869 (offset +10)
        //   4862: iload  8
        //   4864: ldc  #1 // -2147483648
        //   4866: ior
        //   4867: istore  8
        //   4869: iload  8
        //   4871: aload  9
        //   4873: getfield  #151 // jnr.a64asm.Register.code:I
        //   4876: bipush  31
        //   4878: iand
        //   4879: ior
        //   4880: istore  8
        //   4882: iload  8
        //   4884: i2l
        //   4885: aload  10
        //   4887: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   4890: ldc2_w  #89 // 65535L
        //   4893: land
        //   4894: iconst_5
        //   4895: lshl
        //   4896: lor
        //   4897: l2i
        //   4898: istore  8
        //   4900: aload  11
        //   4902: ifnull  4952 (offset +50)
        //   4905: aload  11
        //   4907: invokevirtual  #226 // jnr.a64asm.Shift.value:()J
        //   4910: ldc2_w  #69 // 16L
        //   4913: lrem
        //   4914: lconst_0
        //   4915: lcmp
        //   4916: ifne  4952 (offset +36)
        //   4919: aload  11
        //   4921: invokevirtual  #226 // jnr.a64asm.Shift.value:()J
        //   4924: ldc2_w  #73 // 49L
        //   4927: lcmp
        //   4928: ifge  4952 (offset +24)
        //   4931: iload  8
        //   4933: i2l
        //   4934: aload  11
        //   4936: invokevirtual  #226 // jnr.a64asm.Shift.value:()J
        //   4939: iconst_4
        //   4940: lshr
        //   4941: ldc2_w  #59 // 3L
        //   4944: land
        //   4945: bipush  21
        //   4947: lshl
        //   4948: lor
        //   4949: l2i
        //   4950: istore  8
        //   4952: iload  8
        //   4954: aload  7
        //   4956: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //   4959: ior
        //   4960: istore  8
        //   4962: aload_0
        //   4963: iload  8
        //   4965: invokevirtual  #168 // jnr.a64asm.Assembler_A64._emitInt32:(I)V
        //   4968: goto  5814 (offset +846)
        //   4971: new  #22 // java.lang.IllegalArgumentException
        //   4974: dup
        //   4975: ldc  #14 // 'illegal arguments'
        //   4977: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   4980: athrow
        //   4981: iconst_0
        //   4982: istore  8
        //   4984: aload_2
        //   4985: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   4988: ifeq  5085 (offset +97)
        //   4991: aload_3
        //   4992: invokevirtual  #209 // jnr.a64asm.Operand.isImm:()Z
        //   4995: ifeq  5085 (offset +90)
        //   4998: aload_2
        //   4999: checkcast  #46 // jnr.a64asm.Register
        //   5002: astore  9
        //   5004: aload_3
        //   5005: checkcast  #37 // jnr.a64asm.Immediate
        //   5008: astore  10
        //   5010: iload  8
        //   5012: aload  9
        //   5014: getfield  #151 // jnr.a64asm.Register.code:I
        //   5017: bipush  31
        //   5019: iand
        //   5020: ior
        //   5021: istore  8
        //   5023: aload  10
        //   5025: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   5028: bipush  12
        //   5030: lshr
        //   5031: lstore  11
        //   5033: iload  8
        //   5035: i2l
        //   5036: lload  11
        //   5038: iconst_2
        //   5039: lshr
        //   5040: ldc2_w  #91 // 524287L
        //   5043: land
        //   5044: iconst_5
        //   5045: lshl
        //   5046: lor
        //   5047: l2i
        //   5048: istore  8
        //   5050: iload  8
        //   5052: i2l
        //   5053: lload  11
        //   5055: ldc2_w  #59 // 3L
        //   5058: land
        //   5059: bipush  29
        //   5061: lshl
        //   5062: lor
        //   5063: l2i
        //   5064: istore  8
        //   5066: iload  8
        //   5068: aload  7
        //   5070: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //   5073: ior
        //   5074: istore  8
        //   5076: aload_0
        //   5077: iload  8
        //   5079: invokevirtual  #168 // jnr.a64asm.Assembler_A64._emitInt32:(I)V
        //   5082: goto  5814 (offset +732)
        //   5085: new  #22 // java.lang.IllegalArgumentException
        //   5088: dup
        //   5089: ldc  #14 // 'illegal arguments'
        //   5091: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   5094: athrow
        //   5095: iconst_0
        //   5096: istore  8
        //   5098: aload  7
        //   5100: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   5103: getstatic  #124 // jnr.a64asm.INST_CODE.INST_NOP_IC_SYSTEM:Ljnr/a64asm/INST_CODE;
        //   5106: if_acmpeq  5164 (offset +58)
        //   5109: aload  7
        //   5111: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   5114: getstatic  #136 // jnr.a64asm.INST_CODE.INST_YIELD_IC_SYSTEM:Ljnr/a64asm/INST_CODE;
        //   5117: if_acmpeq  5164 (offset +47)
        //   5120: aload  7
        //   5122: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   5125: getstatic  #134 // jnr.a64asm.INST_CODE.INST_WFE_IC_SYSTEM:Ljnr/a64asm/INST_CODE;
        //   5128: if_acmpeq  5164 (offset +36)
        //   5131: aload  7
        //   5133: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   5136: getstatic  #135 // jnr.a64asm.INST_CODE.INST_WFI_IC_SYSTEM:Ljnr/a64asm/INST_CODE;
        //   5139: if_acmpeq  5164 (offset +25)
        //   5142: aload  7
        //   5144: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   5147: getstatic  #131 // jnr.a64asm.INST_CODE.INST_SEV_IC_SYSTEM:Ljnr/a64asm/INST_CODE;
        //   5150: if_acmpeq  5164 (offset +14)
        //   5153: aload  7
        //   5155: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   5158: getstatic  #130 // jnr.a64asm.INST_CODE.INST_SEVL_IC_SYSTEM:Ljnr/a64asm/INST_CODE;
        //   5161: if_acmpne  5177 (offset +16)
        //   5164: iload  8
        //   5166: aload  7
        //   5168: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //   5171: ior
        //   5172: istore  8
        //   5174: goto  5668 (offset +494)
        //   5177: aload  7
        //   5179: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   5182: getstatic  #115 // jnr.a64asm.INST_CODE.INST_HINT_IC_SYSTEM:Ljnr/a64asm/INST_CODE;
        //   5185: if_acmpeq  5232 (offset +47)
        //   5188: aload  7
        //   5190: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   5193: getstatic  #112 // jnr.a64asm.INST_CODE.INST_CLREX_IC_SYSTEM:Ljnr/a64asm/INST_CODE;
        //   5196: if_acmpeq  5232 (offset +36)
        //   5199: aload  7
        //   5201: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   5204: getstatic  #114 // jnr.a64asm.INST_CODE.INST_DSB_IC_SYSTEM:Ljnr/a64asm/INST_CODE;
        //   5207: if_acmpeq  5232 (offset +25)
        //   5210: aload  7
        //   5212: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   5215: getstatic  #113 // jnr.a64asm.INST_CODE.INST_DMB_IC_SYSTEM:Ljnr/a64asm/INST_CODE;
        //   5218: if_acmpeq  5232 (offset +14)
        //   5221: aload  7
        //   5223: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   5226: getstatic  #116 // jnr.a64asm.INST_CODE.INST_ISB_IC_SYSTEM:Ljnr/a64asm/INST_CODE;
        //   5229: if_acmpne  5292 (offset +63)
        //   5232: aload_2
        //   5233: checkcast  #37 // jnr.a64asm.Immediate
        //   5236: astore  9
        //   5238: aload  7
        //   5240: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   5243: getstatic  #115 // jnr.a64asm.INST_CODE.INST_HINT_IC_SYSTEM:Ljnr/a64asm/INST_CODE;
        //   5246: if_acmpne  5270 (offset +24)
        //   5249: iload  8
        //   5251: i2l
        //   5252: aload  9
        //   5254: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   5257: ldc2_w  #77 // 127L
        //   5260: land
        //   5261: iconst_5
        //   5262: lshl
        //   5263: lor
        //   5264: l2i
        //   5265: istore  8
        //   5267: goto  5289 (offset +22)
        //   5270: iload  8
        //   5272: i2l
        //   5273: aload  9
        //   5275: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   5278: ldc2_w  #67 // 15L
        //   5281: land
        //   5282: bipush  8
        //   5284: lshl
        //   5285: lor
        //   5286: l2i
        //   5287: istore  8
        //   5289: goto  5668 (offset +379)
        //   5292: aload  7
        //   5294: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   5297: getstatic  #123 // jnr.a64asm.INST_CODE.INST_MSR_IC_SYSTEM_X:Ljnr/a64asm/INST_CODE;
        //   5300: if_acmpne  5356 (offset +56)
        //   5303: aload_2
        //   5304: checkcast  #52 // jnr.a64asm.SysRegister
        //   5307: astore  9
        //   5309: aload_3
        //   5310: checkcast  #46 // jnr.a64asm.Register
        //   5313: astore  10
        //   5315: aload  9
        //   5317: invokevirtual  #228 // jnr.a64asm.SysRegister.getEnum:()Ljnr/a64asm/SYSREG_CODE;
        //   5320: invokestatic  #227 // jnr.a64asm.SysRegDescription.find:(Ljnr/a64asm/SYSREG_CODE;)Ljnr/a64asm/SysRegDescription;
        //   5323: astore  11
        //   5325: iload  8
        //   5327: aload  11
        //   5329: getfield  #157 // jnr.a64asm.SysRegDescription.reg_code:I
        //   5332: ldc  #5 // 65535
        //   5334: iand
        //   5335: iconst_5
        //   5336: ishl
        //   5337: ior
        //   5338: istore  8
        //   5340: iload  8
        //   5342: aload  10
        //   5344: getfield  #151 // jnr.a64asm.Register.code:I
        //   5347: bipush  31
        //   5349: iand
        //   5350: ior
        //   5351: istore  8
        //   5353: goto  5668 (offset +315)
        //   5356: aload  7
        //   5358: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   5361: getstatic  #133 // jnr.a64asm.INST_CODE.INST_SYS_IC_SYSTEM:Ljnr/a64asm/INST_CODE;
        //   5364: if_acmpeq  5378 (offset +14)
        //   5367: aload  7
        //   5369: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   5372: getstatic  #132 // jnr.a64asm.INST_CODE.INST_SYSL_IC_SYSTEM:Ljnr/a64asm/INST_CODE;
        //   5375: if_acmpne  5658 (offset +283)
        //   5378: aload_2
        //   5379: checkcast  #37 // jnr.a64asm.Immediate
        //   5382: astore  9
        //   5384: aload_2
        //   5385: checkcast  #46 // jnr.a64asm.Register
        //   5388: astore  10
        //   5390: aload_3
        //   5391: checkcast  #46 // jnr.a64asm.Register
        //   5394: astore  11
        //   5396: aload_3
        //   5397: checkcast  #37 // jnr.a64asm.Immediate
        //   5400: astore  12
        //   5402: aload  4
        //   5404: checkcast  #46 // jnr.a64asm.Register
        //   5407: astore  13
        //   5409: aload  4
        //   5411: checkcast  #46 // jnr.a64asm.Register
        //   5414: astore  14
        //   5416: aload  5
        //   5418: checkcast  #37 // jnr.a64asm.Immediate
        //   5421: astore  15
        //   5423: aload  5
        //   5425: checkcast  #46 // jnr.a64asm.Register
        //   5428: astore  16
        //   5430: aconst_null
        //   5431: astore  17
        //   5433: aconst_null
        //   5434: astore  18
        //   5436: aload  6
        //   5438: ifnull  5455 (offset +17)
        //   5441: aload  6
        //   5443: checkcast  #46 // jnr.a64asm.Register
        //   5446: astore  17
        //   5448: aload  6
        //   5450: checkcast  #37 // jnr.a64asm.Immediate
        //   5453: astore  18
        //   5455: iload  8
        //   5457: i2l
        //   5458: aload  7
        //   5460: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   5463: getstatic  #133 // jnr.a64asm.INST_CODE.INST_SYS_IC_SYSTEM:Ljnr/a64asm/INST_CODE;
        //   5466: if_acmpne  5477 (offset +11)
        //   5469: aload  9
        //   5471: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   5474: goto  5482 (offset +8)
        //   5477: aload  12
        //   5479: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   5482: ldc2_w  #63 // 7L
        //   5485: land
        //   5486: bipush  16
        //   5488: lshl
        //   5489: lor
        //   5490: l2i
        //   5491: istore  8
        //   5493: iload  8
        //   5495: aload  7
        //   5497: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   5500: getstatic  #133 // jnr.a64asm.INST_CODE.INST_SYS_IC_SYSTEM:Ljnr/a64asm/INST_CODE;
        //   5503: if_acmpne  5514 (offset +11)
        //   5506: aload  11
        //   5508: invokevirtual  #221 // jnr.a64asm.Register.code:()I
        //   5511: goto  5519 (offset +8)
        //   5514: aload  14
        //   5516: invokevirtual  #221 // jnr.a64asm.Register.code:()I
        //   5519: bipush  15
        //   5521: iand
        //   5522: bipush  12
        //   5524: ishl
        //   5525: ior
        //   5526: istore  8
        //   5528: iload  8
        //   5530: aload  7
        //   5532: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   5535: getstatic  #133 // jnr.a64asm.INST_CODE.INST_SYS_IC_SYSTEM:Ljnr/a64asm/INST_CODE;
        //   5538: if_acmpne  5549 (offset +11)
        //   5541: aload  13
        //   5543: invokevirtual  #221 // jnr.a64asm.Register.code:()I
        //   5546: goto  5554 (offset +8)
        //   5549: aload  16
        //   5551: invokevirtual  #221 // jnr.a64asm.Register.code:()I
        //   5554: bipush  15
        //   5556: iand
        //   5557: bipush  8
        //   5559: ishl
        //   5560: ior
        //   5561: istore  8
        //   5563: iload  8
        //   5565: i2l
        //   5566: aload  7
        //   5568: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   5571: getstatic  #133 // jnr.a64asm.INST_CODE.INST_SYS_IC_SYSTEM:Ljnr/a64asm/INST_CODE;
        //   5574: if_acmpne  5585 (offset +11)
        //   5577: aload  15
        //   5579: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   5582: goto  5590 (offset +8)
        //   5585: aload  18
        //   5587: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   5590: ldc2_w  #63 // 7L
        //   5593: land
        //   5594: iconst_5
        //   5595: lshl
        //   5596: lor
        //   5597: l2i
        //   5598: istore  8
        //   5600: aload  7
        //   5602: getfield  #137 // jnr.a64asm.InstructionDescription.code:Ljnr/a64asm/INST_CODE;
        //   5605: getstatic  #133 // jnr.a64asm.INST_CODE.INST_SYS_IC_SYSTEM:Ljnr/a64asm/INST_CODE;
        //   5608: if_acmpne  5642 (offset +34)
        //   5611: aload  17
        //   5613: ifnull  5632 (offset +19)
        //   5616: iload  8
        //   5618: aload  17
        //   5620: invokevirtual  #221 // jnr.a64asm.Register.code:()I
        //   5623: bipush  31
        //   5625: iand
        //   5626: ior
        //   5627: istore  8
        //   5629: goto  5655 (offset +26)
        //   5632: iload  8
        //   5634: bipush  31
        //   5636: ior
        //   5637: istore  8
        //   5639: goto  5655 (offset +16)
        //   5642: iload  8
        //   5644: aload  10
        //   5646: invokevirtual  #221 // jnr.a64asm.Register.code:()I
        //   5649: bipush  31
        //   5651: iand
        //   5652: ior
        //   5653: istore  8
        //   5655: goto  5668 (offset +13)
        //   5658: new  #22 // java.lang.IllegalArgumentException
        //   5661: dup
        //   5662: ldc  #14 // 'illegal arguments'
        //   5664: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   5667: athrow
        //   5668: iload  8
        //   5670: aload  7
        //   5672: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //   5675: ior
        //   5676: istore  8
        //   5678: aload_0
        //   5679: iload  8
        //   5681: invokevirtual  #168 // jnr.a64asm.Assembler_A64._emitInt32:(I)V
        //   5684: goto  5814 (offset +130)
        //   5687: iconst_0
        //   5688: istore  8
        //   5690: aload_2
        //   5691: invokevirtual  #214 // jnr.a64asm.Operand.isReg:()Z
        //   5694: ifeq  5804 (offset +110)
        //   5697: aload_2
        //   5698: checkcast  #46 // jnr.a64asm.Register
        //   5701: astore  9
        //   5703: aload_3
        //   5704: checkcast  #37 // jnr.a64asm.Immediate
        //   5707: astore  10
        //   5709: aload  4
        //   5711: checkcast  #37 // jnr.a64asm.Immediate
        //   5714: astore  11
        //   5716: iload  8
        //   5718: aload  9
        //   5720: getfield  #151 // jnr.a64asm.Register.code:I
        //   5723: bipush  31
        //   5725: iand
        //   5726: ior
        //   5727: istore  8
        //   5729: iload  8
        //   5731: i2l
        //   5732: aload  10
        //   5734: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   5737: ldc2_w  #71 // 31L
        //   5740: land
        //   5741: bipush  19
        //   5743: lshl
        //   5744: lor
        //   5745: l2i
        //   5746: istore  8
        //   5748: iload  8
        //   5750: i2l
        //   5751: aload  10
        //   5753: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   5756: iconst_5
        //   5757: lshr
        //   5758: lconst_1
        //   5759: land
        //   5760: bipush  31
        //   5762: lshl
        //   5763: lor
        //   5764: l2i
        //   5765: istore  8
        //   5767: iload  8
        //   5769: i2l
        //   5770: aload  11
        //   5772: invokevirtual  #201 // jnr.a64asm.Immediate.value:()J
        //   5775: ldc2_w  #85 // 16383L
        //   5778: land
        //   5779: iconst_5
        //   5780: lshl
        //   5781: lor
        //   5782: l2i
        //   5783: istore  8
        //   5785: iload  8
        //   5787: aload  7
        //   5789: getfield  #139 // jnr.a64asm.InstructionDescription.opcode:I
        //   5792: ior
        //   5793: istore  8
        //   5795: aload_0
        //   5796: iload  8
        //   5798: invokevirtual  #168 // jnr.a64asm.Assembler_A64._emitInt32:(I)V
        //   5801: goto  5814 (offset +13)
        //   5804: new  #22 // java.lang.IllegalArgumentException
        //   5807: dup
        //   5808: ldc  #14 // 'illegal arguments'
        //   5810: invokespecial  #161 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   5813: athrow
        //   5814: return
    }

   void _emitJmpOrCallReloc(InstructionGroup arg0, long arg1) {
        RelocData var4 = new RelocData(RelocData_Type.ABSOLUTE_TO_RELATIVE_TRAMPOLINE, 4, offset(), arg1);
        _relocData.add(var4);
        _emitInt32(0);
    }

  public void relocCode(ByteBuffer arg0, long arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #172 // jnr.a64asm.Assembler_A64.codeSize:()I
        //      4: istore  4
        //      6: aload_0
        //      7: getfield  #100 // jnr.a64asm.Assembler_A64._buffer:Ljnr/a64asm/CodeBuffer;
        //     10: aload_1
        //     11: invokevirtual  #180 // jnr.a64asm.CodeBuffer.copyTo:(Ljava/nio/ByteBuffer;)V
        //     14: aload_0
        //     15: getfield  #104 // jnr.a64asm.Assembler_A64._relocData:Ljava/util/List;
        //     18: invokeinterface  #232 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //     23: astore  5
        //     25: aload  5
        //     27: invokeinterface  #229 // java.util.Iterator.hasNext:()Z, count 1
        //     32: ifeq  324 (offset +292)
        //     35: aload  5
        //     37: invokeinterface  #230 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //     42: checkcast  #47 // jnr.a64asm.RelocData
        //     45: astore  6
        //     47: getstatic  #98 // jnr.a64asm.Assembler_A64.$assertionsDisabled:Z
        //     50: ifne  77 (offset +27)
        //     53: aload  6
        //     55: getfield  #153 // jnr.a64asm.RelocData.offset:I
        //     58: aload  6
        //     60: getfield  #154 // jnr.a64asm.RelocData.size:I
        //     63: iadd
        //     64: iload  4
        //     66: if_icmple  77 (offset +11)
        //     69: new  #20 // java.lang.AssertionError
        //     72: dup
        //     73: invokespecial  #159 // java.lang.AssertionError.<init>:()V
        //     76: athrow
        //     77: getstatic  #108 // jnr.a64asm.Assembler_A64$1.$SwitchMap$jnr$a64asm$RelocData$Type:[I
        //     80: aload  6
        //     82: getfield  #155 // jnr.a64asm.RelocData.type:Ljnr/a64asm/RelocData$Type;
        //     85: invokevirtual  #223 // jnr.a64asm.RelocData$Type.ordinal:()I
        //     88: iaload
        //     89: tableswitch  default->223, 1->120, 2->130, 3->142, 4->142
        //    120: aload  6
        //    122: getfield  #152 // jnr.a64asm.RelocData.destination:J
        //    125: lstore  7
        //    127: goto  233 (offset +106)
        //    130: lload_2
        //    131: aload  6
        //    133: getfield  #152 // jnr.a64asm.RelocData.destination:J
        //    136: ladd
        //    137: lstore  7
        //    139: goto  233 (offset +94)
        //    142: aload  6
        //    144: getfield  #152 // jnr.a64asm.RelocData.destination:J
        //    147: lload_2
        //    148: aload  6
        //    150: getfield  #153 // jnr.a64asm.RelocData.offset:I
        //    153: i2l
        //    154: ladd
        //    155: lsub
        //    156: ldc2_w  #95 // 134217728L
        //    159: lcmp
        //    160: ifle  171 (offset +11)
        //    163: getstatic  #97 // java.lang.System.out:Ljava/io/PrintStream;
        //    166: ldc  #12 // 'IMPOSSIBLE JUMP : ADDRESS AHEAD OF RANGE of 128MB'
        //    168: invokevirtual  #158 // java.io.PrintStream.println:(Ljava/lang/String;)V
        //    171: aload  6
        //    173: getfield  #152 // jnr.a64asm.RelocData.destination:J
        //    176: lload_2
        //    177: aload  6
        //    179: getfield  #153 // jnr.a64asm.RelocData.offset:I
        //    182: i2l
        //    183: ladd
        //    184: lsub
        //    185: ldc2_w  #55 // -134217728L
        //    188: lcmp
        //    189: ifge  200 (offset +11)
        //    192: getstatic  #97 // java.lang.System.out:Ljava/io/PrintStream;
        //    195: ldc  #13 // 'IMPOSSIBLE JUMP : ADDRESS BELOW OF RANGE of 128MB'
        //    197: invokevirtual  #158 // java.io.PrintStream.println:(Ljava/lang/String;)V
        //    200: aload  6
        //    202: getfield  #152 // jnr.a64asm.RelocData.destination:J
        //    205: lload_2
        //    206: aload  6
        //    208: getfield  #153 // jnr.a64asm.RelocData.offset:I
        //    211: i2l
        //    212: ladd
        //    213: lsub
        //    214: ldc2_w  #61 // 4L
        //    217: ldiv
        //    218: lstore  7
        //    220: goto  233 (offset +13)
        //    223: new  #23 // java.lang.IllegalStateException
        //    226: dup
        //    227: ldc  #17 // 'invalid relocation type'
        //    229: invokespecial  #162 // java.lang.IllegalStateException.<init>:(Ljava/lang/String;)V
        //    232: athrow
        //    233: aload  6
        //    235: getfield  #154 // jnr.a64asm.RelocData.size:I
        //    238: lookupswitch  default->311, 4->264, 8->296
        //    264: lload  7
        //    266: ldc2_w  #93 // 67108863L
        //    269: land
        //    270: lstore  7
        //    272: lload  7
        //    274: ldc2_w  #53 // -1811939328L
        //    277: lor
        //    278: lstore  7
        //    280: aload_1
        //    281: aload  6
        //    283: getfield  #153 // jnr.a64asm.RelocData.offset:I
        //    286: lload  7
        //    288: l2i
        //    289: invokevirtual  #163 // java.nio.ByteBuffer.putInt:(II)Ljava/nio/ByteBuffer;
        //    292: pop
        //    293: goto  321 (offset +28)
        //    296: aload_1
        //    297: aload  6
        //    299: getfield  #153 // jnr.a64asm.RelocData.offset:I
        //    302: lload  7
        //    304: invokevirtual  #164 // java.nio.ByteBuffer.putLong:(IJ)Ljava/nio/ByteBuffer;
        //    307: pop
        //    308: goto  321 (offset +13)
        //    311: new  #23 // java.lang.IllegalStateException
        //    314: dup
        //    315: ldc  #16 // 'invalid relocation size'
        //    317: invokespecial  #162 // java.lang.IllegalStateException.<init>:(Ljava/lang/String;)V
        //    320: athrow
        //    321: goto  25 (offset -296)
        //    324: return
    }

}