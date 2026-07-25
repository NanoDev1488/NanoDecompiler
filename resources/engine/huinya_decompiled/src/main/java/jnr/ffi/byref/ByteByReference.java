// исходный (обфусцированный) внутренний класс: jnr.ffi.byref.ByteByReference
package jnr.ffi.byref;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.byref.AbstractNumberReference;

public final class ByteByReference extends AbstractNumberReference {

  public ByteByReference() { // было: <init>
        super(Byte.valueOf(0));
    }

  public ByteByReference(Byte arg0) { // было: <init>
        super(((Byte) checkNull(arg0)));
    }

  public ByteByReference(byte arg0) { // было: <init>
        super(Byte.valueOf(arg0));
    }

  public void toNative(Runtime arg0, Pointer arg1, long arg2) {
        arg1.putByte(arg2, (((Byte) value)).byteValue());
    }

  public void fromNative(Runtime arg0, Pointer arg1, long arg2) {
        value = Byte.valueOf(arg1.getByte(arg2));
    }

  public final int nativeSize(Runtime arg0) {
        return 1;
    }

}