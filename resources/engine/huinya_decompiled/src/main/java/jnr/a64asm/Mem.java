// исходный (обфусцированный) внутренний класс: jnr.a64asm.Mem
package jnr.a64asm;

import jnr.a64asm.Ext;
import jnr.a64asm.Label;
import jnr.a64asm.Operand;
import jnr.a64asm.Register;

public class Mem extends Operand {

    // ---- поля ----
  private final int base;
  private final int index;
  private final int shift;
  private final Ext extend;
  private final Label label;
  private final long target;
  private final long displacement;
  static final boolean $assertionsDisabled;

    static {
        boolean __stk1;
        __stk1 = !Mem.class.desiredAssertionStatus();
        $assertionsDisabled = __stk1;
    }

   Mem(Register arg0, int arg1) { // было: <init>
        this(arg0.index(), 255, 0, null, 0L, 0L, arg1, null);
    }

   Mem(Register arg0, Ext arg1, int arg2) { // было: <init>
        this(arg0.index(), 255, 0, null, 0L, 0L, arg2, null);
    }

   Mem(Label arg0, long arg1, int arg2) { // было: <init>
        this(255, 255, 0, arg0, 0L, arg1, arg2, null);
    }

   Mem(Register arg0, long arg1, int arg2) { // было: <init>
        this(arg0.index(), 255, 0, null, 0L, arg1, arg2, null);
    }

   Mem(Register arg0, Register arg1, int arg2, long arg3, int arg4) { // было: <init>
        this(arg0.index(), arg1.index(), arg2, null, 0L, arg3, arg4, null);
    }

   Mem(Label arg0, Register arg1, int arg2, long arg3, int arg4) { // было: <init>
        this(0, arg1.index(), arg2, arg0, 0L, arg3, arg4, null);
    }

   Mem(long arg0, long arg1, int arg2) { // было: <init>
        this(255, 255, 0, null, arg0, arg1, arg2, null);
    }

   Mem(long arg0, Register arg1, int arg2, long arg3, int arg4) { // было: <init>
        this(255, arg1.index(), arg2, null, arg0, arg3, arg4, null);
    }

  private Mem(int arg0, int arg1, int arg2, Label arg3, long arg4, long arg5, int arg6, Ext arg7) { // было: <init>
        super(2, arg6);
        if ($assertionsDisabled) {
            base = arg0;
            index = arg1;
            shift = arg2;
            label = arg3;
            target = arg4;
            displacement = arg5;
            extend = arg7;
            return;
        } else {
            if (arg2 <= 3) {
                base = arg0;
                index = arg1;
                shift = arg2;
                label = arg3;
                target = arg4;
                displacement = arg5;
                extend = arg7;
                return;
            } else {
                throw new AssertionError();
            }
        }
    }

  public final boolean hasLabel() {
        return label != null;
    }

  public final boolean hasBase() {
        return base != 255;
    }

   boolean hasIndex() {
        return index != 255;
    }

  public final int base() {
        return base;
    }

  public final long displacement() {
        return displacement;
    }

  public final int index() {
        return index;
    }

  public final Label label() {
        return label;
    }

  public final int shift() {
        return shift;
    }

  public final long target() {
        return target;
    }

}