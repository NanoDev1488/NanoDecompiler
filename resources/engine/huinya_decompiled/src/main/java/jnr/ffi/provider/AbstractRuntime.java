// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.AbstractRuntime
package jnr.ffi.provider;

import java.nio.ByteOrder;
import java.util.EnumMap;
import jnr.ffi.NativeType;
import jnr.ffi.Runtime;
import jnr.ffi.Type;
import jnr.ffi.provider.MemoryManager;

public abstract class AbstractRuntime extends Runtime {

    // ---- поля ----
  private final Type[] types;
  private final long addressMask;
  private final int addressSize;
  private final int longSize;
  private final ByteOrder byteOrder;

  public AbstractRuntime(ByteOrder arg0, EnumMap arg1) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokespecial  #27 // jnr.ffi.Runtime.<init>:()V
        //      4: aload_0
        //      5: aload_1
        //      6: putfield  #17 // jnr.ffi.provider.AbstractRuntime.byteOrder:Ljava/nio/ByteOrder;
        //      9: ldc  #4 // jnr.ffi.NativeType
        //     11: invokestatic  #22 // java.util.EnumSet.allOf:(Ljava/lang/Class;)Ljava/util/EnumSet;
        //     14: astore_3
        //     15: aload_0
        //     16: aload_3
        //     17: invokevirtual  #24 // java.util.EnumSet.size:()I
        //     20: anewarray  #6 // jnr.ffi.Type
        //     23: putfield  #19 // jnr.ffi.provider.AbstractRuntime.types:[Ljnr/ffi/Type;
        //     26: aload_3
        //     27: invokevirtual  #23 // java.util.EnumSet.iterator:()Ljava/util/Iterator;
        //     30: astore  4
        //     32: aload  4
        //     34: invokeinterface  #30 // java.util.Iterator.hasNext:()Z, count 1
        //     39: ifeq  100 (offset +61)
        //     42: aload  4
        //     44: invokeinterface  #31 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //     49: checkcast  #4 // jnr.ffi.NativeType
        //     52: astore  5
        //     54: aload_0
        //     55: getfield  #19 // jnr.ffi.provider.AbstractRuntime.types:[Ljnr/ffi/Type;
        //     58: aload  5
        //     60: invokevirtual  #25 // jnr.ffi.NativeType.ordinal:()I
        //     63: aload_2
        //     64: aload  5
        //     66: invokevirtual  #20 // java.util.EnumMap.containsKey:(Ljava/lang/Object;)Z
        //     69: ifeq  84 (offset +15)
        //     72: aload_2
        //     73: aload  5
        //     75: invokevirtual  #21 // java.util.EnumMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //     78: checkcast  #6 // jnr.ffi.Type
        //     81: goto  96 (offset +15)
        //     84: new  #8 // jnr.ffi.provider.BadType
        //     87: dup
        //     88: aload  5
        //     90: invokevirtual  #26 // jnr.ffi.NativeType.toString:()Ljava/lang/String;
        //     93: invokespecial  #29 // jnr.ffi.provider.BadType.<init>:(Ljava/lang/String;)V
        //     96: aastore
        //     97: goto  32 (offset -65)
        //    100: aload_0
        //    101: aload_0
        //    102: getfield  #19 // jnr.ffi.provider.AbstractRuntime.types:[Ljnr/ffi/Type;
        //    105: getstatic  #13 // jnr.ffi.NativeType.ADDRESS:Ljnr/ffi/NativeType;
        //    108: invokevirtual  #25 // jnr.ffi.NativeType.ordinal:()I
        //    111: aaload
        //    112: invokevirtual  #28 // jnr.ffi.Type.size:()I
        //    115: putfield  #16 // jnr.ffi.provider.AbstractRuntime.addressSize:I
        //    118: aload_0
        //    119: aload_0
        //    120: getfield  #19 // jnr.ffi.provider.AbstractRuntime.types:[Ljnr/ffi/Type;
        //    123: getstatic  #14 // jnr.ffi.NativeType.SLONG:Ljnr/ffi/NativeType;
        //    126: invokevirtual  #25 // jnr.ffi.NativeType.ordinal:()I
        //    129: aaload
        //    130: invokevirtual  #28 // jnr.ffi.Type.size:()I
        //    133: putfield  #18 // jnr.ffi.provider.AbstractRuntime.longSize:I
        //    136: aload_0
        //    137: aload_0
        //    138: getfield  #16 // jnr.ffi.provider.AbstractRuntime.addressSize:I
        //    141: iconst_4
        //    142: if_icmpne  151 (offset +9)
        //    145: ldc2_w  #11 // 4294967295L
        //    148: goto  154 (offset +6)
        //    151: ldc2_w  #9 // -1L
        //    154: putfield  #15 // jnr.ffi.provider.AbstractRuntime.addressMask:J
        //    157: return
    }

  public final Type findType(NativeType arg0) {
        return ((Type) types[arg0.ordinal()]);
    }

  public abstract MemoryManager getMemoryManager();

  public abstract int getLastError();

  public abstract void setLastError(int arg0);

  public final long addressMask() {
        return addressMask;
    }

  public final int addressSize() {
        return addressSize;
    }

  public final int longSize() {
        return longSize;
    }

  public final ByteOrder byteOrder() {
        return byteOrder;
    }

}