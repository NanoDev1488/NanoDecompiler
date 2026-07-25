// исходный (обфусцированный) внутренний класс: jnr.ffi.byref.NumberByReference
package jnr.ffi.byref;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.Type;
import jnr.ffi.TypeAlias;
import jnr.ffi.byref.AbstractNumberReference;

public class NumberByReference extends AbstractNumberReference {

    // ---- поля ----
  private final TypeAlias typeAlias;

  public NumberByReference(TypeAlias arg0, Number arg1) { // было: <init>
        super(checkNull(arg1));
        typeAlias = arg0;
    }

  public NumberByReference(TypeAlias arg0) { // было: <init>
        super(Integer.valueOf(0));
        typeAlias = arg0;
    }

  public int nativeSize(Runtime arg0) {
        return arg0.findType(typeAlias).size();
    }

  public void fromNative(Runtime arg0, Pointer arg1, long arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #20 // jnr.ffi.byref.NumberByReference$1.$SwitchMap$jnr$ffi$NativeType:[I
        //      3: aload_1
        //      4: aload_0
        //      5: getfield  #18 // jnr.ffi.byref.NumberByReference.typeAlias:Ljnr/ffi/TypeAlias;
        //      8: invokevirtual  #55 // jnr.ffi.Runtime.findType:(Ljnr/ffi/TypeAlias;)Ljnr/ffi/Type;
        //     11: invokevirtual  #56 // jnr.ffi.Type.getNativeType:()Ljnr/ffi/NativeType;
        //     14: invokevirtual  #38 // jnr.ffi.NativeType.ordinal:()I
        //     17: iaload
        //     18: tableswitch  default->204, 1->84, 2->84, 3->99, 4->99, 5->114, 6->114, 7->129, 8->129, 9->144, 10->144, 11->159, 12->174, 13->189
        //     84: aload_0
        //     85: aload_2
        //     86: lload_3
        //     87: invokevirtual  #40 // jnr.ffi.Pointer.getByte:(J)B
        //     90: invokestatic  #21 // java.lang.Byte.valueOf:(B)Ljava/lang/Byte;
        //     93: putfield  #19 // jnr.ffi.byref.NumberByReference.value:Ljava/lang/Number;
        //     96: goto  234 (offset +138)
        //     99: aload_0
        //    100: aload_2
        //    101: lload_3
        //    102: invokevirtual  #46 // jnr.ffi.Pointer.getShort:(J)S
        //    105: invokestatic  #32 // java.lang.Short.valueOf:(S)Ljava/lang/Short;
        //    108: putfield  #19 // jnr.ffi.byref.NumberByReference.value:Ljava/lang/Number;
        //    111: goto  234 (offset +123)
        //    114: aload_0
        //    115: aload_2
        //    116: lload_3
        //    117: invokevirtual  #43 // jnr.ffi.Pointer.getInt:(J)I
        //    120: invokestatic  #24 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    123: putfield  #19 // jnr.ffi.byref.NumberByReference.value:Ljava/lang/Number;
        //    126: goto  234 (offset +108)
        //    129: aload_0
        //    130: aload_2
        //    131: lload_3
        //    132: invokevirtual  #44 // jnr.ffi.Pointer.getLong:(J)J
        //    135: invokestatic  #25 // java.lang.Long.valueOf:(J)Ljava/lang/Long;
        //    138: putfield  #19 // jnr.ffi.byref.NumberByReference.value:Ljava/lang/Number;
        //    141: goto  234 (offset +93)
        //    144: aload_0
        //    145: aload_2
        //    146: lload_3
        //    147: invokevirtual  #45 // jnr.ffi.Pointer.getLongLong:(J)J
        //    150: invokestatic  #25 // java.lang.Long.valueOf:(J)Ljava/lang/Long;
        //    153: putfield  #19 // jnr.ffi.byref.NumberByReference.value:Ljava/lang/Number;
        //    156: goto  234 (offset +78)
        //    159: aload_0
        //    160: aload_2
        //    161: lload_3
        //    162: invokevirtual  #39 // jnr.ffi.Pointer.getAddress:(J)J
        //    165: invokestatic  #25 // java.lang.Long.valueOf:(J)Ljava/lang/Long;
        //    168: putfield  #19 // jnr.ffi.byref.NumberByReference.value:Ljava/lang/Number;
        //    171: goto  234 (offset +63)
        //    174: aload_0
        //    175: aload_2
        //    176: lload_3
        //    177: invokevirtual  #42 // jnr.ffi.Pointer.getFloat:(J)F
        //    180: invokestatic  #23 // java.lang.Float.valueOf:(F)Ljava/lang/Float;
        //    183: putfield  #19 // jnr.ffi.byref.NumberByReference.value:Ljava/lang/Number;
        //    186: goto  234 (offset +48)
        //    189: aload_0
        //    190: aload_2
        //    191: lload_3
        //    192: invokevirtual  #41 // jnr.ffi.Pointer.getDouble:(J)D
        //    195: invokestatic  #22 // java.lang.Double.valueOf:(D)Ljava/lang/Double;
        //    198: putfield  #19 // jnr.ffi.byref.NumberByReference.value:Ljava/lang/Number;
        //    201: goto  234 (offset +33)
        //    204: new  #10 // java.lang.UnsupportedOperationException
        //    207: dup
        //    208: new  #9 // java.lang.StringBuilder
        //    211: dup
        //    212: invokespecial  #33 // java.lang.StringBuilder.<init>:()V
        //    215: ldc  #1 // 'unsupported type: '
        //    217: invokevirtual  #35 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    220: aload_0
        //    221: getfield  #18 // jnr.ffi.byref.NumberByReference.typeAlias:Ljnr/ffi/TypeAlias;
        //    224: invokevirtual  #34 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    227: invokevirtual  #36 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    230: invokespecial  #37 // java.lang.UnsupportedOperationException.<init>:(Ljava/lang/String;)V
        //    233: athrow
        //    234: return
    }

  public void toNative(Runtime arg0, Pointer arg1, long arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #20 // jnr.ffi.byref.NumberByReference$1.$SwitchMap$jnr$ffi$NativeType:[I
        //      3: aload_1
        //      4: aload_0
        //      5: getfield  #18 // jnr.ffi.byref.NumberByReference.typeAlias:Ljnr/ffi/TypeAlias;
        //      8: invokevirtual  #55 // jnr.ffi.Runtime.findType:(Ljnr/ffi/TypeAlias;)Ljnr/ffi/Type;
        //     11: invokevirtual  #56 // jnr.ffi.Type.getNativeType:()Ljnr/ffi/NativeType;
        //     14: invokevirtual  #38 // jnr.ffi.NativeType.ordinal:()I
        //     17: iaload
        //     18: tableswitch  default->204, 1->84, 2->84, 3->99, 4->99, 5->114, 6->114, 7->129, 8->129, 9->144, 10->144, 11->159, 12->174, 13->189
        //     84: aload_2
        //     85: lload_3
        //     86: aload_0
        //     87: getfield  #19 // jnr.ffi.byref.NumberByReference.value:Ljava/lang/Number;
        //     90: invokevirtual  #26 // java.lang.Number.byteValue:()B
        //     93: invokevirtual  #48 // jnr.ffi.Pointer.putByte:(JB)V
        //     96: goto  234 (offset +138)
        //     99: aload_2
        //    100: lload_3
        //    101: aload_0
        //    102: getfield  #19 // jnr.ffi.byref.NumberByReference.value:Ljava/lang/Number;
        //    105: invokevirtual  #31 // java.lang.Number.shortValue:()S
        //    108: invokevirtual  #54 // jnr.ffi.Pointer.putShort:(JS)V
        //    111: goto  234 (offset +123)
        //    114: aload_2
        //    115: lload_3
        //    116: aload_0
        //    117: getfield  #19 // jnr.ffi.byref.NumberByReference.value:Ljava/lang/Number;
        //    120: invokevirtual  #29 // java.lang.Number.intValue:()I
        //    123: invokevirtual  #51 // jnr.ffi.Pointer.putInt:(JI)V
        //    126: goto  234 (offset +108)
        //    129: aload_2
        //    130: lload_3
        //    131: aload_0
        //    132: getfield  #19 // jnr.ffi.byref.NumberByReference.value:Ljava/lang/Number;
        //    135: invokevirtual  #30 // java.lang.Number.longValue:()J
        //    138: invokevirtual  #52 // jnr.ffi.Pointer.putLong:(JJ)V
        //    141: goto  234 (offset +93)
        //    144: aload_2
        //    145: lload_3
        //    146: aload_0
        //    147: getfield  #19 // jnr.ffi.byref.NumberByReference.value:Ljava/lang/Number;
        //    150: invokevirtual  #30 // java.lang.Number.longValue:()J
        //    153: invokevirtual  #53 // jnr.ffi.Pointer.putLongLong:(JJ)V
        //    156: goto  234 (offset +78)
        //    159: aload_2
        //    160: lload_3
        //    161: aload_0
        //    162: getfield  #19 // jnr.ffi.byref.NumberByReference.value:Ljava/lang/Number;
        //    165: invokevirtual  #30 // java.lang.Number.longValue:()J
        //    168: invokevirtual  #47 // jnr.ffi.Pointer.putAddress:(JJ)V
        //    171: goto  234 (offset +63)
        //    174: aload_2
        //    175: lload_3
        //    176: aload_0
        //    177: getfield  #19 // jnr.ffi.byref.NumberByReference.value:Ljava/lang/Number;
        //    180: invokevirtual  #28 // java.lang.Number.floatValue:()F
        //    183: invokevirtual  #50 // jnr.ffi.Pointer.putFloat:(JF)V
        //    186: goto  234 (offset +48)
        //    189: aload_2
        //    190: lload_3
        //    191: aload_0
        //    192: getfield  #19 // jnr.ffi.byref.NumberByReference.value:Ljava/lang/Number;
        //    195: invokevirtual  #27 // java.lang.Number.doubleValue:()D
        //    198: invokevirtual  #49 // jnr.ffi.Pointer.putDouble:(JD)V
        //    201: goto  234 (offset +33)
        //    204: new  #10 // java.lang.UnsupportedOperationException
        //    207: dup
        //    208: new  #9 // java.lang.StringBuilder
        //    211: dup
        //    212: invokespecial  #33 // java.lang.StringBuilder.<init>:()V
        //    215: ldc  #1 // 'unsupported type: '
        //    217: invokevirtual  #35 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    220: aload_0
        //    221: getfield  #18 // jnr.ffi.byref.NumberByReference.typeAlias:Ljnr/ffi/TypeAlias;
        //    224: invokevirtual  #34 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    227: invokevirtual  #36 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    230: invokespecial  #37 // java.lang.UnsupportedOperationException.<init>:(Ljava/lang/String;)V
        //    233: athrow
        //    234: return
    }

}