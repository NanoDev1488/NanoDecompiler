// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.AbstractMemoryIO
package jnr.ffi.provider;

import jnr.ffi.Address;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.Type;
import jnr.ffi.provider.BoundedMemoryIO;
import jnr.ffi.provider.ShareMemoryIO;

public abstract class AbstractMemoryIO extends Pointer {

  protected static void checkBounds(long arg0, long arg1, long arg2) {
        if ((arg1 | arg2 | arg1 + arg2 | arg0 - (arg1 + arg2)) >= 0L) {
            return;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

  protected AbstractMemoryIO(Runtime arg0, long arg1, boolean arg2) { // было: <init>
        super(arg0, arg1, arg2);
    }

  public int indexOf(long arg0, byte arg1) {
        return indexOf(arg0, arg1, 2147483647);
    }

  public long getAddress(long arg0) {
        return getRuntime().addressSize() != 4 ? getLongLong(arg0) : ((long) getInt(arg0));
    }

  public void putAddress(long arg0, long arg1) {
        if (getRuntime().addressSize() != 4) {
            putLongLong(arg0, arg1);
        } else {
            putInt(arg0, ((int) arg1));
        }
    }

  public void checkBounds(long arg0, long arg1) {
        // (пустое тело)
    }

  public void putAddress(long arg0, Address arg1) {
        if (getRuntime().addressSize() != 4) {
            putLongLong(arg0, arg1.longValue());
        } else {
            putInt(arg0, arg1.intValue());
        }
    }

  public final long getNativeLong(long arg0) {
        return getRuntime().longSize() != 4 ? getLongLong(arg0) : ((long) getInt(arg0));
    }

  public void putNativeLong(long arg0, long arg1) {
        if (getRuntime().longSize() != 4) {
            putLongLong(arg0, arg1);
        } else {
            putInt(arg0, ((int) arg1));
        }
    }

  public long getLong(long arg0) {
        return getRuntime().longSize() != 4 ? getLongLong(arg0) : ((long) getInt(arg0));
    }

  public void putLong(long arg0, long arg1) {
        if (getRuntime().longSize() != 4) {
            putLongLong(arg0, arg1);
        } else {
            putInt(arg0, ((int) arg1));
        }
    }

  public void putInt(Type arg0, long arg1, long arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #19 // jnr.ffi.provider.AbstractMemoryIO$1.$SwitchMap$jnr$ffi$NativeType:[I
        //      3: aload_1
        //      4: invokevirtual  #39 // jnr.ffi.Type.getNativeType:()Ljnr/ffi/NativeType;
        //      7: invokevirtual  #32 // jnr.ffi.NativeType.ordinal:()I
        //     10: iaload
        //     11: tableswitch  default->119, 1->64, 2->64, 3->76, 4->76, 5->88, 6->88, 7->99, 8->99, 9->109, 10->109
        //     64: aload_0
        //     65: lload_2
        //     66: lload  4
        //     68: l2i
        //     69: i2b
        //     70: invokevirtual  #52 // jnr.ffi.provider.AbstractMemoryIO.putByte:(JB)V
        //     73: goto  149 (offset +76)
        //     76: aload_0
        //     77: lload_2
        //     78: lload  4
        //     80: l2i
        //     81: i2s
        //     82: invokevirtual  #56 // jnr.ffi.provider.AbstractMemoryIO.putShort:(JS)V
        //     85: goto  149 (offset +64)
        //     88: aload_0
        //     89: lload_2
        //     90: lload  4
        //     92: l2i
        //     93: invokevirtual  #53 // jnr.ffi.provider.AbstractMemoryIO.putInt:(JI)V
        //     96: goto  149 (offset +53)
        //     99: aload_0
        //    100: lload_2
        //    101: lload  4
        //    103: invokevirtual  #55 // jnr.ffi.provider.AbstractMemoryIO.putNativeLong:(JJ)V
        //    106: goto  149 (offset +43)
        //    109: aload_0
        //    110: lload_2
        //    111: lload  4
        //    113: invokevirtual  #54 // jnr.ffi.provider.AbstractMemoryIO.putLongLong:(JJ)V
        //    116: goto  149 (offset +33)
        //    119: new  #3 // java.lang.IllegalArgumentException
        //    122: dup
        //    123: new  #5 // java.lang.StringBuilder
        //    126: dup
        //    127: invokespecial  #22 // java.lang.StringBuilder.<init>:()V
        //    130: ldc  #2 // 'unsupported integer type: '
        //    132: invokevirtual  #24 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    135: aload_1
        //    136: invokevirtual  #39 // jnr.ffi.Type.getNativeType:()Ljnr/ffi/NativeType;
        //    139: invokevirtual  #23 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    142: invokevirtual  #25 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    145: invokespecial  #20 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    148: athrow
        //    149: return
    }

  public long getInt(Type arg0, long arg1) {
        switch (arg0.getNativeType()) {
            case SCHAR:
            case UCHAR:
                return ((long) getByte(arg1));
            case SSHORT:
            case USHORT:
                return ((long) getShort(arg1));
            case SINT:
            case UINT:
                return ((long) getInt(arg1));
            case SLONG:
            case ULONG:
                return getNativeLong(arg1);
            case SLONGLONG:
            case ULONGLONG:
                return getLongLong(arg1);
            default:
                throw new IllegalArgumentException(new StringBuilder().append("unsupported integer type: ").append(arg0.getNativeType()).toString());
        }
    }

  public AbstractMemoryIO slice(long arg0) {
        return new ShareMemoryIO(this, arg0);
    }

  public AbstractMemoryIO slice(long arg0, long arg1) {
        return new BoundedMemoryIO(this, arg0, arg1);
    }

  public void transferTo(long arg0, Pointer arg1, long arg2, long arg3) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_3
        //      1: instanceof  #17 // jnr.ffi.provider.DelegatingMemoryIO
        //      4: ifeq  19 (offset +15)
        //      7: aload_3
        //      8: checkcast  #17 // jnr.ffi.provider.DelegatingMemoryIO
        //     11: invokeinterface  #61 // jnr.ffi.provider.DelegatingMemoryIO.getDelegatedMemoryIO:()Ljnr/ffi/Pointer;, count 1
        //     16: goto  20 (offset +4)
        //     19: aload_3
        //     20: astore  8
        //     22: aload  8
        //     24: lload  4
        //     26: lload  6
        //     28: invokevirtual  #34 // jnr.ffi.Pointer.checkBounds:(JJ)V
        //     31: aload  8
        //     33: instanceof  #12 // jnr.ffi.provider.AbstractArrayMemoryIO
        //     36: ifeq  71 (offset +35)
        //     39: aload  8
        //     41: checkcast  #12 // jnr.ffi.provider.AbstractArrayMemoryIO
        //     44: astore  9
        //     46: aload_0
        //     47: lload_1
        //     48: aload  9
        //     50: invokevirtual  #40 // jnr.ffi.provider.AbstractArrayMemoryIO.array:()[B
        //     53: aload  9
        //     55: invokevirtual  #41 // jnr.ffi.provider.AbstractArrayMemoryIO.offset:()I
        //     58: lload  4
        //     60: l2i
        //     61: iadd
        //     62: lload  6
        //     64: l2i
        //     65: invokevirtual  #43 // jnr.ffi.provider.AbstractMemoryIO.get:(J[BII)V
        //     68: goto  171 (offset +103)
        //     71: aload  8
        //     73: instanceof  #13 // jnr.ffi.provider.AbstractBufferMemoryIO
        //     76: ifeq  134 (offset +58)
        //     79: aload  8
        //     81: checkcast  #13 // jnr.ffi.provider.AbstractBufferMemoryIO
        //     84: invokevirtual  #42 // jnr.ffi.provider.AbstractBufferMemoryIO.getByteBuffer:()Ljava/nio/ByteBuffer;
        //     87: invokevirtual  #28 // java.nio.ByteBuffer.hasArray:()Z
        //     90: ifeq  134 (offset +44)
        //     93: aload  8
        //     95: checkcast  #13 // jnr.ffi.provider.AbstractBufferMemoryIO
        //     98: invokevirtual  #42 // jnr.ffi.provider.AbstractBufferMemoryIO.getByteBuffer:()Ljava/nio/ByteBuffer;
        //    101: astore  9
        //    103: aload_0
        //    104: lload_1
        //    105: aload  9
        //    107: invokevirtual  #26 // java.nio.ByteBuffer.array:()[B
        //    110: aload  9
        //    112: invokevirtual  #27 // java.nio.ByteBuffer.arrayOffset:()I
        //    115: aload  9
        //    117: invokevirtual  #29 // java.nio.ByteBuffer.position:()I
        //    120: iadd
        //    121: lload  4
        //    123: l2i
        //    124: iadd
        //    125: lload  6
        //    127: l2i
        //    128: invokevirtual  #43 // jnr.ffi.provider.AbstractMemoryIO.get:(J[BII)V
        //    131: goto  171 (offset +40)
        //    134: lconst_0
        //    135: lstore  9
        //    137: lload  9
        //    139: lload  6
        //    141: lcmp
        //    142: ifge  171 (offset +29)
        //    145: aload_3
        //    146: lload  4
        //    148: lload  9
        //    150: ladd
        //    151: aload_0
        //    152: lload_1
        //    153: lload  9
        //    155: ladd
        //    156: invokevirtual  #44 // jnr.ffi.provider.AbstractMemoryIO.getByte:(J)B
        //    159: invokevirtual  #36 // jnr.ffi.Pointer.putByte:(JB)V
        //    162: lload  9
        //    164: lconst_1
        //    165: ladd
        //    166: lstore  9
        //    168: goto  137 (offset -31)
        //    171: return
    }

  public void transferFrom(long arg0, Pointer arg1, long arg2, long arg3) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_3
        //      1: instanceof  #17 // jnr.ffi.provider.DelegatingMemoryIO
        //      4: ifeq  19 (offset +15)
        //      7: aload_3
        //      8: checkcast  #17 // jnr.ffi.provider.DelegatingMemoryIO
        //     11: invokeinterface  #61 // jnr.ffi.provider.DelegatingMemoryIO.getDelegatedMemoryIO:()Ljnr/ffi/Pointer;, count 1
        //     16: goto  20 (offset +4)
        //     19: aload_3
        //     20: astore  8
        //     22: aload  8
        //     24: lload  4
        //     26: lload  6
        //     28: invokevirtual  #34 // jnr.ffi.Pointer.checkBounds:(JJ)V
        //     31: aload  8
        //     33: instanceof  #12 // jnr.ffi.provider.AbstractArrayMemoryIO
        //     36: ifeq  71 (offset +35)
        //     39: aload  8
        //     41: checkcast  #12 // jnr.ffi.provider.AbstractArrayMemoryIO
        //     44: astore  9
        //     46: aload_0
        //     47: lload_1
        //     48: aload  9
        //     50: invokevirtual  #40 // jnr.ffi.provider.AbstractArrayMemoryIO.array:()[B
        //     53: aload  9
        //     55: invokevirtual  #41 // jnr.ffi.provider.AbstractArrayMemoryIO.offset:()I
        //     58: lload  4
        //     60: l2i
        //     61: iadd
        //     62: lload  6
        //     64: l2i
        //     65: invokevirtual  #51 // jnr.ffi.provider.AbstractMemoryIO.put:(J[BII)V
        //     68: goto  171 (offset +103)
        //     71: aload  8
        //     73: instanceof  #13 // jnr.ffi.provider.AbstractBufferMemoryIO
        //     76: ifeq  134 (offset +58)
        //     79: aload  8
        //     81: checkcast  #13 // jnr.ffi.provider.AbstractBufferMemoryIO
        //     84: invokevirtual  #42 // jnr.ffi.provider.AbstractBufferMemoryIO.getByteBuffer:()Ljava/nio/ByteBuffer;
        //     87: invokevirtual  #28 // java.nio.ByteBuffer.hasArray:()Z
        //     90: ifeq  134 (offset +44)
        //     93: aload  8
        //     95: checkcast  #13 // jnr.ffi.provider.AbstractBufferMemoryIO
        //     98: invokevirtual  #42 // jnr.ffi.provider.AbstractBufferMemoryIO.getByteBuffer:()Ljava/nio/ByteBuffer;
        //    101: astore  9
        //    103: aload_0
        //    104: lload_1
        //    105: aload  9
        //    107: invokevirtual  #26 // java.nio.ByteBuffer.array:()[B
        //    110: aload  9
        //    112: invokevirtual  #27 // java.nio.ByteBuffer.arrayOffset:()I
        //    115: aload  9
        //    117: invokevirtual  #29 // java.nio.ByteBuffer.position:()I
        //    120: iadd
        //    121: lload  4
        //    123: l2i
        //    124: iadd
        //    125: lload  6
        //    127: l2i
        //    128: invokevirtual  #51 // jnr.ffi.provider.AbstractMemoryIO.put:(J[BII)V
        //    131: goto  171 (offset +40)
        //    134: lconst_0
        //    135: lstore  9
        //    137: lload  9
        //    139: lload  6
        //    141: lcmp
        //    142: ifge  171 (offset +29)
        //    145: aload_0
        //    146: lload_1
        //    147: lload  9
        //    149: ladd
        //    150: aload_3
        //    151: lload  4
        //    153: lload  9
        //    155: ladd
        //    156: invokevirtual  #35 // jnr.ffi.Pointer.getByte:(J)B
        //    159: invokevirtual  #52 // jnr.ffi.provider.AbstractMemoryIO.putByte:(JB)V
        //    162: lload  9
        //    164: lconst_1
        //    165: ladd
        //    166: lstore  9
        //    168: goto  137 (offset -31)
        //    171: return
    }

  public Pointer slice(long arg0, long arg1) {
        return slice(arg0, arg1);
    }

  public Pointer slice(long arg0) {
        return slice(arg0);
    }

}