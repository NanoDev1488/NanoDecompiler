// исходный (обфусцированный) внутренний класс: jnr.x86asm.SEGMENT
package jnr.x86asm;

public enum SEGMENT {

    SEGMENT_NONE(0),
    SEGMENT_CS(46),
    SEGMENT_SS(54),
    SEGMENT_DS(62),
    SEGMENT_ES(38),
    SEGMENT_FS(100),
    SEGMENT_GS(100);

    // ---- поля ----
  private final int prefix;

  private SEGMENT(int arg2) { // было: <init>
        prefix = arg2;
    }

  public final int prefix() {
        return prefix;
    }

}