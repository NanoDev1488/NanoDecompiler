// исходный (обфусцированный) внутренний класс: com.darkmagician6.eventapi.EventAPI
package com.darkmagician6.eventapi;

public final class EventAPI {

    // ---- поля ----
  public static final String VERSION;
  public static final String[] AUTHORS;

    static {
        VERSION = String.format("%s-%s", new Object[]{"0.7", "beta"});
        String[] __obj2 = new String[1];
        __obj2[0] = "DarkMagician6";
        AUTHORS = __obj2;
    }

  private EventAPI() { // было: <init>
        super();
    }

}