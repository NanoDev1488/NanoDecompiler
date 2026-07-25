// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.AbstractField
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_Field;
import jnr.ffi.StructLayout_Offset;
import jnr.ffi.Type;

public abstract class StructLayout_AbstractField extends StructLayout_Field {

    // ---- поля ----
  final StructLayout this$0;

  protected StructLayout_AbstractField(StructLayout arg0, int arg1, int arg2, StructLayout_Offset arg3) { // было: <init>
        super(arg0, arg0.addField(arg1, arg2, arg3));
        this$0 = arg0;
    }

  protected StructLayout_AbstractField(StructLayout arg0, int arg1, int arg2) { // было: <init>
        super(arg0, arg0.addField(arg1, arg2));
        this$0 = arg0;
    }

  protected StructLayout_AbstractField(StructLayout arg0, NativeType arg1) { // было: <init>
        super(arg0, arg0.addField(arg0.getRuntime().findType(arg1)));
        this$0 = arg0;
    }

  protected StructLayout_AbstractField(StructLayout arg0, Type arg1) { // было: <init>
        super(arg0, arg0.addField(arg1));
        this$0 = arg0;
    }

  protected StructLayout_AbstractField(StructLayout arg0, NativeType arg1, StructLayout_Offset arg2) { // было: <init>
        super(arg0, arg0.addField(arg0.getRuntime().findType(arg1), arg2));
        this$0 = arg0;
    }

  protected StructLayout_AbstractField(StructLayout arg0, Type arg1, StructLayout_Offset arg2) { // было: <init>
        super(arg0, arg0.addField(arg1, arg2));
        this$0 = arg0;
    }

}