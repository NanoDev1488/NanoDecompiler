// исходный (обфусцированный) внутренний класс: com.kenai.jnr.x86asm.SEGMENT
package com.kenai.jnr.x86asm;

@Deprecated
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