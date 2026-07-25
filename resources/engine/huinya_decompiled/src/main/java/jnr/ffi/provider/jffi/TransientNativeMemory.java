// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.TransientNativeMemory
package jnr.ffi.provider.jffi;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.provider.jffi.DirectMemoryIO;
import jnr.ffi.provider.jffi.TransientNativeMemory_Sentinel;

public class TransientNativeMemory extends DirectMemoryIO {

    // ---- поля ----
  private static final Map referenceSet;
  private static final ThreadLocal currentMagazine;
  private static final int PAGES_PER_MAGAZINE = 2;
  private final TransientNativeMemory_Sentinel sentinel;
  private final long size;

    static {
        referenceSet = new ConcurrentHashMap();
        currentMagazine = new ThreadLocal();
    }

  public static DirectMemoryIO allocate(Runtime arg0, int arg1, int arg2, boolean arg3) {
        return allocate(arg0, ((long) arg1), arg2, arg3);
    }

  public static DirectMemoryIO allocate(Runtime arg0, long arg1, int arg2, boolean arg3) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: lload_1
        //      1: lconst_0
        //      2: lcmp
        //      3: ifge  33 (offset +30)
        //      6: new  #5 // java.lang.IllegalArgumentException
        //      9: dup
        //     10: new  #6 // java.lang.StringBuilder
        //     13: dup
        //     14: invokespecial  #30 // java.lang.StringBuilder.<init>:()V
        //     17: ldc  #2 // 'negative size: '
        //     19: invokevirtual  #32 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     22: lload_1
        //     23: invokevirtual  #31 // java.lang.StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //     26: invokevirtual  #33 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //     29: invokespecial  #29 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //     32: athrow
        //     33: lload_1
        //     34: ldc2_w  #20 // 256L
        //     37: lcmp
        //     38: ifle  53 (offset +15)
        //     41: new  #11 // jnr.ffi.provider.jffi.AllocatedDirectMemoryIO
        //     44: dup
        //     45: aload_0
        //     46: lload_1
        //     47: iload  4
        //     49: invokespecial  #39 // jnr.ffi.provider.jffi.AllocatedDirectMemoryIO.<init>:(Ljnr/ffi/Runtime;JZ)V
        //     52: areturn
        //     53: getstatic  #23 // jnr.ffi.provider.jffi.TransientNativeMemory.currentMagazine:Ljava/lang/ThreadLocal;
        //     56: invokevirtual  #36 // java.lang.ThreadLocal.get:()Ljava/lang/Object;
        //     59: checkcast  #15 // jnr.ffi.provider.jffi.TransientNativeMemory$Magazine
        //     62: astore  5
        //     64: aload  5
        //     66: ifnull  77 (offset +11)
        //     69: aload  5
        //     71: invokevirtual  #63 // jnr.ffi.provider.jffi.TransientNativeMemory$Magazine.sentinel:()Ljnr/ffi/provider/jffi/TransientNativeMemory$Sentinel;
        //     74: goto  78 (offset +4)
        //     77: aconst_null
        //     78: astore  6
        //     80: aload  6
        //     82: ifnull  100 (offset +18)
        //     85: aload  5
        //     87: lload_1
        //     88: iload_3
        //     89: invokevirtual  #62 // jnr.ffi.provider.jffi.TransientNativeMemory$Magazine.allocate:(JI)J
        //     92: dup2
        //     93: lstore  7
        //     95: lconst_0
        //     96: lcmp
        //     97: ifne  197 (offset +100)
        //    100: invokestatic  #28 // com.kenai.jffi.PageManager.getInstance:()Lcom/kenai/jffi/PageManager;
        //    103: astore  9
        //    105: aload  9
        //    107: iconst_2
        //    108: iconst_3
        //    109: invokevirtual  #27 // com.kenai.jffi.PageManager.allocatePages:(II)J
        //    112: lstore  10
        //    114: lload  10
        //    116: lconst_0
        //    117: lcmp
        //    118: ifeq  133 (offset +15)
        //    121: lload  10
        //    123: ldc2_w  #18 // -1L
        //    126: lcmp
        //    127: ifeq  133 (offset +6)
        //    130: goto  142 (offset +12)
        //    133: invokestatic  #34 // java.lang.System.gc:()V
        //    136: invokestatic  #65 // jnr.ffi.util.ref.FinalizableReferenceQueue.cleanUpAll:()V
        //    139: goto  105 (offset -34)
        //    142: getstatic  #24 // jnr.ffi.provider.jffi.TransientNativeMemory.referenceSet:Ljava/util/Map;
        //    145: new  #15 // jnr.ffi.provider.jffi.TransientNativeMemory$Magazine
        //    148: dup
        //    149: new  #16 // jnr.ffi.provider.jffi.TransientNativeMemory$Sentinel
        //    152: dup
        //    153: aconst_null
        //    154: invokespecial  #64 // jnr.ffi.provider.jffi.TransientNativeMemory$Sentinel.<init>:(Ljnr/ffi/provider/jffi/TransientNativeMemory$1;)V
        //    157: dup
        //    158: astore  6
        //    160: aload  9
        //    162: lload  10
        //    164: iconst_2
        //    165: invokespecial  #61 // jnr.ffi.provider.jffi.TransientNativeMemory$Magazine.<init>:(Ljnr/ffi/provider/jffi/TransientNativeMemory$Sentinel;Lcom/kenai/jffi/PageManager;JI)V
        //    168: dup
        //    169: astore  5
        //    171: getstatic  #22 // java.lang.Boolean.TRUE:Ljava/lang/Boolean;
        //    174: invokeinterface  #66 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //    179: pop
        //    180: getstatic  #23 // jnr.ffi.provider.jffi.TransientNativeMemory.currentMagazine:Ljava/lang/ThreadLocal;
        //    183: aload  5
        //    185: invokevirtual  #37 // java.lang.ThreadLocal.set:(Ljava/lang/Object;)V
        //    188: aload  5
        //    190: lload_1
        //    191: iload_3
        //    192: invokevirtual  #62 // jnr.ffi.provider.jffi.TransientNativeMemory$Magazine.allocate:(JI)J
        //    195: lstore  7
        //    197: new  #13 // jnr.ffi.provider.jffi.TransientNativeMemory
        //    200: dup
        //    201: aload_0
        //    202: aload  6
        //    204: lload  7
        //    206: lload_1
        //    207: invokespecial  #57 // jnr.ffi.provider.jffi.TransientNativeMemory.<init>:(Ljnr/ffi/Runtime;Ljnr/ffi/provider/jffi/TransientNativeMemory$Sentinel;JJ)V
        //    210: areturn
    }

   TransientNativeMemory(Runtime arg0, TransientNativeMemory_Sentinel arg1, long arg2, long arg3) { // было: <init>
        super(arg0, arg2);
        sentinel = arg1;
        size = arg3;
    }

  private static long align(long arg0, long arg1) {
        return arg0 + arg1 - 1L & (arg1 - 1L ^ -1L);
    }

  public long size() {
        return size;
    }

  public int hashCode() {
        return super.hashCode();
    }

  public boolean equals(Object arg0) {
        if (!(arg0 instanceof TransientNativeMemory)) {
            return super.equals(arg0);
        } else {
            TransientNativeMemory var2 = ((TransientNativeMemory) arg0);
            return var2.size != size ? 0 : var2.address() == address();
        }
    }

  public final void dispose() {
        // (пустое тело)
    }

  public void transferFrom(long arg0, Pointer arg1, long arg2, long arg3) {
        super.transferFrom(arg0, arg1, arg2, arg3);
    }

  public void transferTo(long arg0, Pointer arg1, long arg2, long arg3) {
        super.transferTo(arg0, arg1, arg2, arg3);
    }

  public int indexOf(long arg0, byte arg1, int arg2) {
        return super.indexOf(arg0, arg1, arg2);
    }

  public void putZeroTerminatedByteArray(long arg0, byte[] arg1, int arg2, int arg3) {
        super.putZeroTerminatedByteArray(arg0, arg1, arg2, arg3);
    }

  public void putString(long arg0, String arg1, int arg2, Charset arg3) {
        super.putString(arg0, arg1, arg2, arg3);
    }

  public String getString(long arg0, int arg1, Charset arg2) {
        return super.getString(arg0, arg1, arg2);
    }

  public String getString(long arg0) {
        return super.getString(arg0);
    }

  public void putPointer(long arg0, Pointer arg1) {
        super.putPointer(arg0, arg1);
    }

  public Pointer getPointer(long arg0, long arg1) {
        return super.getPointer(arg0, arg1);
    }

  public Pointer getPointer(long arg0) {
        return super.getPointer(arg0);
    }

  public int arrayLength() {
        return super.arrayLength();
    }

  public int arrayOffset() {
        return super.arrayOffset();
    }

  public Object array() {
        return super.array();
    }

  public boolean hasArray() {
        return super.hasArray();
    }

  static long access$100(long arg0, long arg1) {
        return align(arg0, arg1);
    }

  static Map access$200() {
        return referenceSet;
    }

}