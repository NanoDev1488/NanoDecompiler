// исходный (обфусцированный) внутренний класс: jnr.a64asm.ExtendedValue
package jnr.a64asm;

public class ExtendedValue {

    // ---- поля ----
   boolean lsl;
   int value;

   ExtendedValue(boolean arg0, int arg1) { // было: <init>
        super();
        lsl = arg0;
        value = arg1;
    }

}