// исходный (обфусцированный) внутренний класс: jnr.ffi.util.Annotations
package jnr.ffi.util;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import jnr.ffi.util.AnnotationNameComparator;

public final class Annotations {

    // ---- поля ----
  public static final Collection EMPTY_ANNOTATIONS;

    static {
        EMPTY_ANNOTATIONS = Collections.emptyList();
    }

  private Annotations() { // было: <init>
        super();
    }

  public static Collection sortedAnnotationCollection(Annotation[] arg0) {
        if (arg0.length <= 1) {
            if (arg0.length <= 0) {
                return Collections.emptyList();
            } else {
                return Collections.singletonList(arg0[0]);
            }
        } else {
            return sortedAnnotationCollection(Arrays.asList(arg0));
        }
    }

  public static Collection sortedAnnotationCollection(Collection arg0) {
        if (arg0.size() < 2) {
            return arg0;
        } else {
            if (!(arg0 instanceof SortedSet)) {
                TreeSet var1 = new TreeSet(AnnotationNameComparator.getInstance());
                var1.addAll(arg0);
                return Collections.unmodifiableSortedSet(var1);
            } else {
                if (!((((SortedSet) arg0)).comparator() instanceof AnnotationNameComparator)) {
                    TreeSet var1 = new TreeSet(AnnotationNameComparator.getInstance());
                    var1.addAll(arg0);
                    return Collections.unmodifiableSortedSet(var1);
                } else {
                    return arg0;
                }
            }
        }
    }

  public static final Collection mergeAnnotations(Collection arg0, Collection arg1) {
        if (!arg0.isEmpty()) {
            if (arg0.isEmpty()) {
                if (!arg0.isEmpty()) {
                    ArrayList var2 = new ArrayList(arg0);
                    var2.addAll(arg1);
                    return sortedAnnotationCollection(var2);
                } else {
                    if (arg1.isEmpty()) {
                        ArrayList var2 = new ArrayList(arg0);
                        var2.addAll(arg1);
                        return sortedAnnotationCollection(var2);
                    } else {
                        return arg1;
                    }
                }
            } else {
                if (!arg1.isEmpty()) {
                    if (!arg0.isEmpty()) {
                        ArrayList var2 = new ArrayList(arg0);
                        var2.addAll(arg1);
                        return sortedAnnotationCollection(var2);
                    } else {
                        if (arg1.isEmpty()) {
                            ArrayList var2 = new ArrayList(arg0);
                            var2.addAll(arg1);
                            return sortedAnnotationCollection(var2);
                        } else {
                            return arg1;
                        }
                    }
                } else {
                    return arg0;
                }
            }
        } else {
            if (!arg1.isEmpty()) {
                if (arg0.isEmpty()) {
                    if (!arg0.isEmpty()) {
                        ArrayList var2 = new ArrayList(arg0);
                        var2.addAll(arg1);
                        return sortedAnnotationCollection(var2);
                    } else {
                        if (arg1.isEmpty()) {
                            ArrayList var2 = new ArrayList(arg0);
                            var2.addAll(arg1);
                            return sortedAnnotationCollection(var2);
                        } else {
                            return arg1;
                        }
                    }
                } else {
                    if (!arg1.isEmpty()) {
                        if (!arg0.isEmpty()) {
                            ArrayList var2 = new ArrayList(arg0);
                            var2.addAll(arg1);
                            return sortedAnnotationCollection(var2);
                        } else {
                            if (arg1.isEmpty()) {
                                ArrayList var2 = new ArrayList(arg0);
                                var2.addAll(arg1);
                                return sortedAnnotationCollection(var2);
                            } else {
                                return arg1;
                            }
                        }
                    } else {
                        return arg0;
                    }
                }
            } else {
                return EMPTY_ANNOTATIONS;
            }
        }
    }

  public static final Collection mergeAnnotations(Collection[] arg0) {
        int var1 = 0;
        Collection[] var2 = arg0;
        int var3 = var2.length;
        int var4 = 0;
        Object var5;
        while (var4 < var3) {
            var5 = var2[var4];
            var1 = var1 + var5.size();
            ++var4;
            continue;
        }
        var2 = new ArrayList(var1);
        var3 = arg0;
        var4 = var3.length;
        int var5 = 0;
        while (var5 < var4) {
            Object var6 = var3[var5];
            var2.addAll(((Collection) var6));
            ++var5;
            continue;
        }
        return sortedAnnotationCollection(var2);
    }

}