// исходный (обфусцированный) внутренний класс: jnr.ffi.util.AnnotationNameComparator
package jnr.ffi.util;

import java.lang.annotation.Annotation;
import java.util.Comparator;

final class AnnotationNameComparator implements Comparator {

    // ---- поля ----
  static final Comparator INSTANCE;

    static {
        INSTANCE = new AnnotationNameComparator();
    }

   AnnotationNameComparator() { // было: <init>
        super();
    }

  public static Comparator getInstance() {
        return INSTANCE;
    }

  public int compare(Annotation arg0, Annotation arg1) {
        return arg0.annotationType().getName().compareTo(arg1.annotationType().getName());
    }

  public boolean equals(Object arg0) {
        return arg0 == null ? 0 : getClass().equals(arg0.getClass());
    }

  public int compare(Object arg0, Object arg1) {
        return compare(((Annotation) arg0), ((Annotation) arg1));
    }

}