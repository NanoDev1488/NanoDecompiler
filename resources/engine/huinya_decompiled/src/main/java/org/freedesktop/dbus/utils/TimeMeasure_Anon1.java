// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.utils.TimeMeasure$1
package org.freedesktop.dbus.utils;

import org.freedesktop.dbus.utils.TimeMeasure_ITimeMeasureFormat;

class TimeMeasure_Anon1 implements TimeMeasure_ITimeMeasureFormat {

   TimeMeasure_Anon1() { // было: <init>
        super();
    }

  public String format(long arg0) {
        return arg0 < 5000L ? arg0 + "ms" : ((double) ((long) (((double) arg0) / 1000.0 * 10.0))) / 10.0 + "s";
    }

}