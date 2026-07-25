// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.utils.TimeMeasure
package org.freedesktop.dbus.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.TimeZone;
import org.freedesktop.dbus.utils.TimeMeasure_Anon1;
import org.freedesktop.dbus.utils.TimeMeasure_ITimeMeasureFormat;

public class TimeMeasure {

    // ---- поля ----
  private volatile long startTm;
  private final TimeMeasure_ITimeMeasureFormat tmf;

  public TimeMeasure(TimeMeasure_ITimeMeasureFormat arg0) { // было: <init>
        super();
        tmf = arg0;
        reset();
    }

  public TimeMeasure() { // было: <init>
        this(new TimeMeasure_Anon1());
    }

  public final TimeMeasure reset() {
        startTm = System.nanoTime();
        return this;
    }

  public long getStartTime() {
        return startTm;
    }

  public long getElapsed() {
        return Duration.ofNanos(System.nanoTime() - startTm).toMillis();
    }

  public long getElapsedSeconds() {
        return Duration.ofNanos(System.nanoTime() - startTm).toSeconds();
    }

  public String getElapsedFormatted(DateFormat arg0) {
        return getElapsedFormatted(arg0, getElapsed());
    }

   String getElapsedFormatted(DateFormat arg0, long arg1) {
        Date var4 = new Date(arg1);
        DateFormat var5 = arg0;
        if (arg0 == null) {
            var5 = new SimpleDateFormat("HH:mm:ss.SSS");
        }
        var5.setTimeZone(TimeZone.getTimeZone("UTC"));
        return var5.format(var4);
    }

   void setStartTm(long arg0) {
        startTm = arg0;
    }

  public long getElapsedAndReset() {
        long var1 = getElapsed();
        reset();
        return var1;
    }

  public String toString() {
        if (tmf != null) {
            return tmf.format(getElapsed());
        } else {
            return String.valueOf(getElapsed());
        }
    }

}