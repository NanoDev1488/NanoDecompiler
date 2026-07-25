// исходный (обфусцированный) внутренний класс: jnr.a64asm.LinkData
package jnr.a64asm;

final class LinkData {

    // ---- поля ----
  final int offset;
   long displacement;
   int relocId;

  public LinkData(int arg0, long arg1, int arg2) { // было: <init>
        super();
        offset = arg0;
        displacement = arg1;
        relocId = arg2;
    }

}