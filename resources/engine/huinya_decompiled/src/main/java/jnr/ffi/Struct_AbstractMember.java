// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.AbstractMember
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.Struct_Info;
import jnr.ffi.Struct_Member;
import jnr.ffi.Struct_Offset;
import jnr.ffi.Type;

public abstract class Struct_AbstractMember extends Struct_Member {

    // ---- поля ----
  private final int offset;
  final Struct this$0;

  protected Struct_AbstractMember(Struct arg0, int arg1) { // было: <init>
        this(arg0, arg1, arg1);
    }

  protected Struct_AbstractMember(Struct arg0, int arg1, int arg2, Struct_Offset arg3) { // было: <init>
        super(arg0);
        this$0 = arg0;
        offset = arg0.__info.addField(arg1, arg2, arg3);
    }

  protected Struct_AbstractMember(Struct arg0, int arg1, int arg2) { // было: <init>
        super(arg0);
        this$0 = arg0;
        offset = arg0.__info.addField(arg1, arg2);
    }

  protected Struct_AbstractMember(Struct arg0, NativeType arg1) { // было: <init>
        super(arg0);
        this$0 = arg0;
        Type var3 = arg0.getRuntime().findType(arg1);
        offset = arg0.__info.addField(var3.size() * 8, var3.alignment() * 8);
    }

  protected Struct_AbstractMember(Struct arg0, NativeType arg1, Struct_Offset arg2) { // было: <init>
        super(arg0);
        this$0 = arg0;
        Type var4 = arg0.getRuntime().findType(arg1);
        offset = arg0.__info.addField(var4.size() * 8, var4.alignment() * 8, arg2);
    }

  public final Pointer getMemory() {
        return this$0.__info.getMemory();
    }

  public final Struct struct() {
        return this$0;
    }

  public final long offset() {
        return ((long) (offset + this$0.__info.getOffset()));
    }

}