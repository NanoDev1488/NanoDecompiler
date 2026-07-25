// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.AbstractX86StubCompiler
package jnr.ffi.provider.jffi;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import jnr.ffi.Runtime;
import jnr.ffi.provider.jffi.AbstractX86StubCompiler_PageHolder;
import jnr.ffi.provider.jffi.StubCompiler;

abstract class AbstractX86StubCompiler extends StubCompiler {

    // ---- поля ----
  public static final boolean DEBUG;
  private final Runtime runtime;
  final List stubs;
  static final AtomicIntegerFieldUpdater PAGE_HOLDER_UPDATER;

    static {
        DEBUG = Boolean.getBoolean("jnr.ffi.compile.dump");
        PAGE_HOLDER_UPDATER = AtomicIntegerFieldUpdater.newUpdater(AbstractX86StubCompiler_PageHolder.class, "disposed");
    }

  protected AbstractX86StubCompiler(Runtime arg0) { // было: <init>
        super();
        stubs = new LinkedList();
        runtime = arg0;
    }

  public final Runtime getRuntime() {
        return runtime;
    }

   void attach(Class arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #52 // jnr.ffi.provider.jffi.AbstractX86StubCompiler.stubs:Ljava/util/List;
        //      4: invokeinterface  #111 // java.util.List.isEmpty:()Z, count 1
        //      9: ifeq  13 (offset +4)
        //     12: return
        //     13: lconst_0
        //     14: lstore_2
        //     15: aload_0
        //     16: getfield  #52 // jnr.ffi.provider.jffi.AbstractX86StubCompiler.stubs:Ljava/util/List;
        //     19: invokeinterface  #112 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //     24: astore  4
        //     26: aload  4
        //     28: invokeinterface  #108 // java.util.Iterator.hasNext:()Z, count 1
        //     33: ifeq  66 (offset +33)
        //     36: aload  4
        //     38: invokeinterface  #109 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //     43: checkcast  #34 // jnr.ffi.provider.jffi.AbstractX86StubCompiler$Stub
        //     46: astore  5
        //     48: lload_2
        //     49: aload  5
        //     51: getfield  #54 // jnr.ffi.provider.jffi.AbstractX86StubCompiler$Stub.assembler:Ljnr/x86asm/Assembler;
        //     54: invokevirtual  #105 // jnr.x86asm.Assembler.codeSize:()I
        //     57: bipush  8
        //     59: iadd
        //     60: i2l
        //     61: ladd
        //     62: lstore_2
        //     63: goto  26 (offset -37)
        //     66: invokestatic  #65 // com.kenai.jffi.PageManager.getInstance:()Lcom/kenai/jffi/PageManager;
        //     69: astore  4
        //     71: lload_2
        //     72: aload  4
        //     74: invokevirtual  #66 // com.kenai.jffi.PageManager.pageSize:()J
        //     77: ladd
        //     78: lconst_1
        //     79: lsub
        //     80: aload  4
        //     82: invokevirtual  #66 // com.kenai.jffi.PageManager.pageSize:()J
        //     85: ldiv
        //     86: lstore  5
        //     88: aload  4
        //     90: lload  5
        //     92: l2i
        //     93: iconst_3
        //     94: invokevirtual  #64 // com.kenai.jffi.PageManager.allocatePages:(II)J
        //     97: lstore  7
        //     99: lload  7
        //    101: lconst_0
        //    102: lcmp
        //    103: ifne  133 (offset +30)
        //    106: new  #18 // java.lang.OutOfMemoryError
        //    109: dup
        //    110: new  #19 // java.lang.StringBuilder
        //    113: dup
        //    114: invokespecial  #77 // java.lang.StringBuilder.<init>:()V
        //    117: ldc  #5 // 'allocatePages failed for codeSize='
        //    119: invokevirtual  #79 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    122: lload_2
        //    123: invokevirtual  #78 // java.lang.StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //    126: invokevirtual  #80 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    129: invokespecial  #76 // java.lang.OutOfMemoryError.<init>:(Ljava/lang/String;)V
        //    132: athrow
        //    133: new  #32 // jnr.ffi.provider.jffi.AbstractX86StubCompiler$PageHolder
        //    136: dup
        //    137: aload  4
        //    139: lload  7
        //    141: lload  5
        //    143: invokespecial  #94 // jnr.ffi.provider.jffi.AbstractX86StubCompiler$PageHolder.<init>:(Lcom/kenai/jffi/PageManager;JJ)V
        //    146: astore  9
        //    148: new  #23 // java.util.ArrayList
        //    151: dup
        //    152: aload_0
        //    153: getfield  #52 // jnr.ffi.provider.jffi.AbstractX86StubCompiler.stubs:Ljava/util/List;
        //    156: invokeinterface  #113 // java.util.List.size:()I, count 1
        //    161: invokespecial  #88 // java.util.ArrayList.<init>:(I)V
        //    164: astore  10
        //    166: lload  7
        //    168: lstore  11
        //    170: getstatic  #45 // java.lang.System.err:Ljava/io/PrintStream;
        //    173: astore  13
        //    175: getstatic  #46 // java.lang.System.out:Ljava/io/PrintStream;
        //    178: invokevirtual  #68 // java.io.PrintStream.flush:()V
        //    181: getstatic  #45 // java.lang.System.err:Ljava/io/PrintStream;
        //    184: invokevirtual  #68 // java.io.PrintStream.flush:()V
        //    187: aload_0
        //    188: getfield  #52 // jnr.ffi.provider.jffi.AbstractX86StubCompiler.stubs:Ljava/util/List;
        //    191: invokeinterface  #112 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    196: astore  14
        //    198: aload  14
        //    200: invokeinterface  #108 // java.util.Iterator.hasNext:()Z, count 1
        //    205: ifeq  536 (offset +331)
        //    208: aload  14
        //    210: invokeinterface  #109 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    215: checkcast  #34 // jnr.ffi.provider.jffi.AbstractX86StubCompiler$Stub
        //    218: astore  15
        //    220: aload  15
        //    222: getfield  #54 // jnr.ffi.provider.jffi.AbstractX86StubCompiler$Stub.assembler:Ljnr/x86asm/Assembler;
        //    225: astore  16
        //    227: lload  11
        //    229: ldc2_w  #43 // 8L
        //    232: invokestatic  #93 // jnr.ffi.provider.jffi.AbstractX86StubCompiler.align:(JJ)J
        //    235: lstore  11
        //    237: aload  16
        //    239: invokevirtual  #105 // jnr.x86asm.Assembler.codeSize:()I
        //    242: invokestatic  #81 // java.nio.ByteBuffer.allocate:(I)Ljava/nio/ByteBuffer;
        //    245: getstatic  #47 // java.nio.ByteOrder.LITTLE_ENDIAN:Ljava/nio/ByteOrder;
        //    248: invokevirtual  #86 // java.nio.ByteBuffer.order:(Ljava/nio/ByteOrder;)Ljava/nio/ByteBuffer;
        //    251: astore  17
        //    253: aload  15
        //    255: getfield  #54 // jnr.ffi.provider.jffi.AbstractX86StubCompiler$Stub.assembler:Ljnr/x86asm/Assembler;
        //    258: aload  17
        //    260: lload  11
        //    262: invokevirtual  #107 // jnr.x86asm.Assembler.relocCode:(Ljava/nio/ByteBuffer;J)V
        //    265: aload  17
        //    267: invokevirtual  #84 // java.nio.ByteBuffer.flip:()Ljava/nio/Buffer;
        //    270: pop
        //    271: invokestatic  #60 // com.kenai.jffi.MemoryIO.getInstance:()Lcom/kenai/jffi/MemoryIO;
        //    274: lload  11
        //    276: aload  17
        //    278: invokevirtual  #82 // java.nio.ByteBuffer.array:()[B
        //    281: aload  17
        //    283: invokevirtual  #83 // java.nio.ByteBuffer.arrayOffset:()I
        //    286: aload  17
        //    288: invokevirtual  #85 // java.nio.ByteBuffer.limit:()I
        //    291: invokevirtual  #61 // com.kenai.jffi.MemoryIO.putByteArray:(J[BII)V
        //    294: getstatic  #49 // jnr.ffi.provider.jffi.AbstractX86StubCompiler.DEBUG:Z
        //    297: ifeq  495 (offset +198)
        //    300: invokestatic  #100 // jnr.ffi.provider.jffi.X86Disassembler.isAvailable:()Z
        //    303: ifeq  495 (offset +192)
        //    306: aload  13
        //    308: new  #19 // java.lang.StringBuilder
        //    311: dup
        //    312: invokespecial  #77 // java.lang.StringBuilder.<init>:()V
        //    315: aload_1
        //    316: invokevirtual  #73 // java.lang.Class.getName:()Ljava/lang/String;
        //    319: invokevirtual  #79 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    322: ldc  #4 // '.'
        //    324: invokevirtual  #79 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    327: aload  15
        //    329: getfield  #55 // jnr.ffi.provider.jffi.AbstractX86StubCompiler$Stub.name:Ljava/lang/String;
        //    332: invokevirtual  #79 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    335: ldc  #1 // ' '
        //    337: invokevirtual  #79 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    340: aload  15
        //    342: getfield  #56 // jnr.ffi.provider.jffi.AbstractX86StubCompiler$Stub.signature:Ljava/lang/String;
        //    345: invokevirtual  #79 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    348: invokevirtual  #80 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    351: invokevirtual  #71 // java.io.PrintStream.println:(Ljava/lang/String;)V
        //    354: invokestatic  #97 // jnr.ffi.provider.jffi.X86Disassembler.create:()Ljnr/ffi/provider/jffi/X86Disassembler;
        //    357: astore  18
        //    359: aload  18
        //    361: invokestatic  #92 // jnr.ffi.Platform.getNativePlatform:()Ljnr/ffi/Platform;
        //    364: invokevirtual  #91 // jnr.ffi.Platform.getCPU:()Ljnr/ffi/Platform$CPU;
        //    367: getstatic  #48 // jnr.ffi.Platform$CPU.I386:Ljnr/ffi/Platform$CPU;
        //    370: if_acmpne  379 (offset +9)
        //    373: getstatic  #57 // jnr.ffi.provider.jffi.X86Disassembler$Mode.I386:Ljnr/ffi/provider/jffi/X86Disassembler$Mode;
        //    376: goto  382 (offset +6)
        //    379: getstatic  #58 // jnr.ffi.provider.jffi.X86Disassembler$Mode.X86_64:Ljnr/ffi/provider/jffi/X86Disassembler$Mode;
        //    382: invokevirtual  #103 // jnr.ffi.provider.jffi.X86Disassembler.setMode:(Ljnr/ffi/provider/jffi/X86Disassembler$Mode;)V
        //    385: aload  18
        //    387: getstatic  #59 // jnr.ffi.provider.jffi.X86Disassembler$Syntax.INTEL:Ljnr/ffi/provider/jffi/X86Disassembler$Syntax;
        //    390: invokevirtual  #104 // jnr.ffi.provider.jffi.X86Disassembler.setSyntax:(Ljnr/ffi/provider/jffi/X86Disassembler$Syntax;)V
        //    393: aload  18
        //    395: aload_0
        //    396: getfield  #51 // jnr.ffi.provider.jffi.AbstractX86StubCompiler.runtime:Ljnr/ffi/Runtime;
        //    399: lload  11
        //    401: invokestatic  #95 // jnr.ffi.provider.jffi.MemoryUtil.newPointer:(Ljnr/ffi/Runtime;J)Ljnr/ffi/Pointer;
        //    404: aload  16
        //    406: invokevirtual  #106 // jnr.x86asm.Assembler.offset:()I
        //    409: invokevirtual  #102 // jnr.ffi.provider.jffi.X86Disassembler.setInputBuffer:(Ljnr/ffi/Pointer;I)V
        //    412: aload  18
        //    414: invokevirtual  #98 // jnr.ffi.provider.jffi.X86Disassembler.disassemble:()Z
        //    417: ifeq  454 (offset +37)
        //    420: aload  13
        //    422: ldc  #2 // '%8x: %s\n'
        //    424: iconst_2
        //    425: anewarray  #17 // java.lang.Object
        //    428: dup
        //    429: iconst_0
        //    430: aload  18
        //    432: invokevirtual  #101 // jnr.ffi.provider.jffi.X86Disassembler.offset:()J
        //    435: invokestatic  #75 // java.lang.Long.valueOf:(J)Ljava/lang/Long;
        //    438: aastore
        //    439: dup
        //    440: iconst_1
        //    441: aload  18
        //    443: invokevirtual  #99 // jnr.ffi.provider.jffi.X86Disassembler.insn:()Ljava/lang/String;
        //    446: aastore
        //    447: invokevirtual  #69 // java.io.PrintStream.printf:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/io/PrintStream;
        //    450: pop
        //    451: goto  412 (offset -39)
        //    454: aload  17
        //    456: invokevirtual  #87 // java.nio.ByteBuffer.remaining:()I
        //    459: aload  16
        //    461: invokevirtual  #106 // jnr.x86asm.Assembler.offset:()I
        //    464: if_icmple  490 (offset +26)
        //    467: aload  13
        //    469: ldc  #3 // '%8x: <indirect call trampolines>\n'
        //    471: iconst_1
        //    472: anewarray  #17 // java.lang.Object
        //    475: dup
        //    476: iconst_0
        //    477: aload  16
        //    479: invokevirtual  #106 // jnr.x86asm.Assembler.offset:()I
        //    482: invokestatic  #74 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    485: aastore
        //    486: invokevirtual  #69 // java.io.PrintStream.printf:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/io/PrintStream;
        //    489: pop
        //    490: aload  13
        //    492: invokevirtual  #70 // java.io.PrintStream.println:()V
        //    495: aload  10
        //    497: new  #9 // com.kenai.jffi.NativeMethod
        //    500: dup
        //    501: lload  11
        //    503: aload  15
        //    505: getfield  #55 // jnr.ffi.provider.jffi.AbstractX86StubCompiler$Stub.name:Ljava/lang/String;
        //    508: aload  15
        //    510: getfield  #56 // jnr.ffi.provider.jffi.AbstractX86StubCompiler$Stub.signature:Ljava/lang/String;
        //    513: invokespecial  #62 // com.kenai.jffi.NativeMethod.<init>:(JLjava/lang/String;Ljava/lang/String;)V
        //    516: invokeinterface  #110 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    521: pop
        //    522: lload  11
        //    524: aload  16
        //    526: invokevirtual  #105 // jnr.x86asm.Assembler.codeSize:()I
        //    529: i2l
        //    530: ladd
        //    531: lstore  11
        //    533: goto  198 (offset -335)
        //    536: aload  4
        //    538: lload  7
        //    540: lload  5
        //    542: l2i
        //    543: iconst_5
        //    544: invokevirtual  #67 // com.kenai.jffi.PageManager.protectPages:(JII)V
        //    547: aload_1
        //    548: aload  10
        //    550: invokestatic  #63 // com.kenai.jffi.NativeMethods.register:(Ljava/lang/Class;Ljava/util/List;)V
        //    553: getstatic  #53 // jnr.ffi.provider.jffi.AbstractX86StubCompiler$StaticDataHolder.PAGES:Ljava/util/Map;
        //    556: aload_1
        //    557: aload  9
        //    559: invokeinterface  #114 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //    564: pop
        //    565: return
    }

  static int align(int arg0, int arg1) {
        return arg0 + arg1 - 1 & (arg1 - 1 ^ -1);
    }

  static long align(long arg0, long arg1) {
        return arg0 + arg1 - 1L & (arg1 - 1L ^ -1L);
    }

}