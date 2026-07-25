// исходный (обфусцированный) внутренний класс: com.kenai.jnr.x86asm.Mem
package com.kenai.jnr.x86asm;

import com.kenai.jnr.x86asm.Label;
import com.kenai.jnr.x86asm.Operand;
import com.kenai.jnr.x86asm.Register;
import com.kenai.jnr.x86asm.SEGMENT;

@Deprecated
public final class Mem extends Operand {

    // ---- поля ----
  private final int base;
  private final int index;
  private final int shift;
  private final SEGMENT segmentPrefix;
  private final Label label;
  private final long target;
  private final long displacement;
  static final boolean $assertionsDisabled;

    static {
        boolean __stk1;
        __stk1 = !Mem.class.desiredAssertionStatus();
        $assertionsDisabled = __stk1;
    }

   Mem(Label arg0, long arg1, int arg2) { // было: <init>
        this(255, 255, 0, SEGMENT.SEGMENT_NONE, arg0, 0L, arg1, arg2);
    }

   Mem(Register arg0, long arg1, int arg2) { // было: <init>
        this(arg0.index(), 255, 0, SEGMENT.SEGMENT_NONE, null, 0L, arg1, arg2);
    }

   Mem(Register arg0, Register arg1, int arg2, long arg3, int arg4) { // было: <init>
        this(arg0.index(), arg1.index(), arg2, SEGMENT.SEGMENT_NONE, null, 0L, arg3, arg4);
    }

   Mem(Label arg0, Register arg1, int arg2, long arg3, int arg4) { // было: <init>
        this(0, arg1.index(), arg2, SEGMENT.SEGMENT_NONE, arg0, 0L, arg3, arg4);
    }

   Mem(long arg0, long arg1, SEGMENT arg2, int arg3) { // было: <init>
        this(255, 255, 0, arg2, null, arg0, arg1, arg3);
    }

   Mem(long arg0, Register arg1, int arg2, SEGMENT arg3, long arg4, int arg5) { // было: <init>
        this(255, arg1.index(), arg2, arg3, null, arg0, arg4, arg5);
    }

  private Mem(int arg0, int arg1, int arg2, SEGMENT arg3, Label arg4, long arg5, long arg6, int arg7) { // было: <init>
        super(2, arg7);
        if ($assertionsDisabled) {
            base = arg0;
            index = arg1;
            shift = arg2;
            segmentPrefix = arg3;
            label = arg4;
            target = arg5;
            displacement = arg6;
            return;
        } else {
            if (arg2 <= 3) {
                base = arg0;
                index = arg1;
                shift = arg2;
                segmentPrefix = arg3;
                label = arg4;
                target = arg5;
                displacement = arg6;
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

  public final SEGMENT segmentPrefix() {
        return segmentPrefix;
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