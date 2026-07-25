// исходный (обфусцированный) внутренний класс: jnr.a64asm.Label
package jnr.a64asm;

import java.util.LinkedList;
import java.util.List;
import jnr.a64asm.LABEL_STATE;
import jnr.a64asm.LinkData;
import jnr.a64asm.Operand;

public final class Label extends Operand {

    // ---- поля ----
  final int id;
   LABEL_STATE state;
   int position;
  final List links;

  public Label() { // было: <init>
        this(0);
    }

  public Label(int arg0) { // было: <init>
        super(4, 4);
        links = new LinkedList();
        id = arg0;
        state = LABEL_STATE.LABEL_STATE_UNUSED;
        position = -1;
    }

  final boolean isUnused() {
        return state == LABEL_STATE.LABEL_STATE_UNUSED;
    }

  final boolean isLinked() {
        return state == LABEL_STATE.LABEL_STATE_LINKED;
    }

  final boolean isBound() {
        return state == LABEL_STATE.LABEL_STATE_BOUND;
    }

  final int position() {
        return position;
    }

  final void link(LinkData arg0) {
        links.add(arg0);
        state = LABEL_STATE.LABEL_STATE_LINKED;
    }

}