// исходный (обфусцированный) внутренний класс: jnr.ffi.byref.AddressByReference
package jnr.ffi.byref;

import jnr.ffi.Address;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.byref.AbstractReference;

public final class AddressByReference extends AbstractReference {

  public AddressByReference() { // было: <init>
        super(Address.valueOf(0));
    }

  public AddressByReference(Address arg0) { // было: <init>
        super(((Address) checkNull(arg0)));
    }

  public void toNative(Runtime arg0, Pointer arg1, long arg2) {
        arg1.putAddress(arg2, (((Address) value)).nativeAddress());
    }

  public void fromNative(Runtime arg0, Pointer arg1, long arg2) {
        value = Address.valueOf(arg1.getAddress(arg2));
    }

  public int nativeSize(Runtime arg0) {
        return arg0.addressSize();
    }

}