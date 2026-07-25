// исходный (обфусцированный) внутренний класс: jnr.posix.JavaTimes
package jnr.posix;

import jnr.posix.Times;

final class JavaTimes implements Times {

    // ---- поля ----
  private static final long startTime;
  static final long HZ = 1000L;

    static {
        startTime = System.currentTimeMillis();
    }

   JavaTimes() { // было: <init>
        super();
    }

  public long utime() {
        return Math.max(System.currentTimeMillis() - startTime, 1L);
    }

  public long stime() {
        return 0L;
    }

  public long cutime() {
        return 0L;
    }

  public long cstime() {
        return 0L;
    }

}