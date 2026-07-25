// исходный (обфусцированный) внутренний класс: com.kenai.jffi.ArrayFlags
package com.kenai.jffi;

public final class ArrayFlags {

    // ---- поля ----
  public static final int IN = 1;
  public static final int OUT = 2;
  public static final int PINNED = 8;
  public static final int NULTERMINATE = 4;
  public static final int CLEAR = 16;

  private ArrayFlags() { // было: <init>
        super();
    }

  public static final boolean isOut(int arg0) {
        return (arg0 & 3) != 1;
    }

  public static final boolean isIn(int arg0) {
        return (arg0 & 3) != 2;
    }

}