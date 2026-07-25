// исходный (обфусцированный) внутренний класс: jnr.ffi.Library
package jnr.ffi;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import jnr.ffi.LibraryLoader;
import jnr.ffi.Runtime;
import jnr.ffi.provider.LoadedLibrary;

public final class Library {

    // ---- поля ----
  private static final Map customSearchPaths;
  private final String name;

    static {
        customSearchPaths = new ConcurrentHashMap();
    }

  private Library(String arg0) { // было: <init>
        super();
        name = arg0;
    }

  public static Runtime getRuntime(Object arg0) {
        return (((LoadedLibrary) arg0)).getRuntime();
    }

  public static Object loadLibrary(String arg0, Class arg1) {
        return loadLibrary(arg1, new String[]{arg0});
    }

  public static Object loadLibrary(Class arg0, String[] arg1) {
        Map var2 = Collections.emptyMap();
        return loadLibrary(arg0, var2, arg1);
    }

  public static Object loadLibrary(String arg0, Class arg1, Map arg2) {
        return loadLibrary(arg1, arg2, new String[]{arg0});
    }

  public static Object loadLibrary(Class arg0, Map arg1, String[] arg2) {
        return LibraryLoader.loadLibrary(arg0, arg1, customSearchPaths, arg2);
    }

  public static synchronized void addLibraryPath(String arg0, File arg1) {
        List var2 = ((List) customSearchPaths.get(arg0));
        if (var2 == null) {
            var2 = new CopyOnWriteArrayList();
            customSearchPaths.put(arg0, var2);
        }
        var2.add(arg1.getAbsolutePath());
    }

  public static List getLibraryPath(String arg0) {
        List var1 = ((List) customSearchPaths.get(arg0));
        if (var1 == null) {
            return Collections.emptyList();
        } else {
            return var1;
        }
    }

    @Deprecated
  public static Library getInstance(String arg0) {
        return new Library(arg0);
    }

    @Deprecated
  public String getName() {
        return name;
    }

}