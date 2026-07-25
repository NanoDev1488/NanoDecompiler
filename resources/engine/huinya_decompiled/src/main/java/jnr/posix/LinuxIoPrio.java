// исходный (обфусцированный) внутренний класс: jnr.posix.LinuxIoPrio
package jnr.posix;

public abstract class LinuxIoPrio {

    // ---- поля ----
  public static int IOPRIO_WHO_PROCESS;
  public static int IOPRIO_WHO_PGRP;
  public static int IOPRIO_WHO_USER;
  public static int IOPRIO_CLASS_NONE;
  public static int IOPRIO_CLASS_RT;
  public static int IOPRIO_CLASS_BE;
  public static int IOPRIO_CLASS_IDLE;

    static {
        IOPRIO_WHO_PROCESS = 1;
        IOPRIO_WHO_PGRP = 2;
        IOPRIO_WHO_USER = 3;
        IOPRIO_CLASS_NONE = 0;
        IOPRIO_CLASS_RT = 1;
        IOPRIO_CLASS_BE = 2;
        IOPRIO_CLASS_IDLE = 3;
    }

  public LinuxIoPrio() { // было: <init>
        super();
    }

  public static int IOPRIO_PRIO_VALUE(int arg0, int arg1) {
        return arg0 << 13 | arg1;
    }

  public static int IOPRIO_PRIO_CLASS(int arg0) {
        return arg0 >> 13;
    }

  public static int IOPRIO_PRIO_DATA(int arg0) {
        return arg0 & 15;
    }

}