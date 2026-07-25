// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.NativeLibrary
package jnr.ffi.provider.jffi;

import com.kenai.jffi.Library;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jnr.ffi.LibraryOption;
import jnr.ffi.Platform;
import jnr.ffi.Runtime;
import jnr.ffi.provider.jffi.NativeLibrary_LoadedLibraryData;
import jnr.ffi.provider.jffi.NativeRuntime;
import jnr.ffi.provider.jffi.SymbolNotFoundError;

public class NativeLibrary {

    // ---- поля ----
  private final List libraryNames;
  private final List searchPaths;
  private final List successfulPaths;
  private final Map options;
  private volatile List nativeLibraries;
  private static final Pattern BAD_ELF;
  private static final Pattern ELF_GROUP;

    static {
        BAD_ELF = Pattern.compile("(.*): (invalid ELF header|file too short|invalid file format)");
        ELF_GROUP = Pattern.compile("GROUP\\s*\\(\\s*(\\S*).*\\)");
    }

   NativeLibrary(Collection arg0, Collection arg1, Map arg2) { // было: <init>
        super();
        successfulPaths = new ArrayList();
        nativeLibraries = Collections.emptyList();
        libraryNames = Collections.unmodifiableList(new ArrayList(arg0));
        searchPaths = Collections.unmodifiableList(new ArrayList(arg1));
        options = arg2;
        if (arg2.containsKey(LibraryOption.LoadNow)) {
            getNativeLibraries();
        }
    }

  private String locateLibrary(String arg0) {
        return Platform.getNativePlatform().locateLibrary(arg0, searchPaths, options);
    }

   long getSymbolAddress(String arg0) {
        Iterator var2 = getNativeLibraries().iterator();
        long var4;
        while (true) {
            if (!var2.hasNext()) {
                return 0L;
            }
            Library var3 = ((Library) var2.next());
            var4 = var3.getSymbolAddress(arg0);
            if (var4 != 0L) {
                break;
            }
            continue;
        }
        return var4;
    }

   long findSymbolAddress(String arg0) {
        long var2 = getSymbolAddress(arg0);
        if (var2 != 0L) {
            return var2;
        } else {
            throw new SymbolNotFoundError(Library.getLastError());
        }
    }

  private synchronized List getNativeLibraries() {
        if (nativeLibraries.isEmpty()) {
            nativeLibraries = loadNativeLibraries();
            return loadNativeLibraries();
        } else {
            return nativeLibraries;
        }
    }

  private synchronized List loadNativeLibraries() {
        ArrayList var1 = new ArrayList();
        Iterator var2 = libraryNames.iterator();
        while (true) {
            if (!var2.hasNext()) {
                putLibraryIntoRuntime();
                return Collections.unmodifiableList(var1);
            }
            String var3 = ((String) var2.next());
            if (var3 != null) {
                if (!var3.equals("RTLD_DEFAULT")) {
                    Library var4 = openLibrary(var3, successfulPaths);
                    if (var4 == null) {
                        String var5 = locateLibrary(var3);
                        if (!var3.equals(var5)) {
                            var4 = openLibrary(var5, successfulPaths);
                        }
                    }
                    if (var4 == null) {
                        break;
                    }
                    var1.add(var4);
                    continue;
                } else {
                    var1.add(Library.getDefault());
                    continue;
                }
            } else {
                continue;
            }
        }
        throw new UnsatisfiedLinkError(new StringBuilder().append(Library.getLastError()).append("\nLibrary names\n").append(libraryNames.toString()).append("\nSearch paths:\n").append(searchPaths.toString()).toString());
    }

  private static Library openLibrary(String arg0, List arg1) {
        Library var2 = Library.getCachedInstance(arg0, 9);
        if (var2 == null) {
            Matcher var3 = BAD_ELF.matcher(Library.getLastError());
            if (!var3.lookingAt()) {
                return null;
            } else {
                File var4 = new File(var3.group(1));
                if (!var4.isFile()) {
                    return null;
                } else {
                    if (var4.length() >= 4096L) {
                        return null;
                    } else {
                        Matcher var5 = ELF_GROUP.matcher(readAll(var4));
                        if (!var5.find()) {
                            return null;
                        } else {
                            var2 = Library.getCachedInstance(var5.group(1), 9);
                            if (var2 != null) {
                                arg1.add(arg0);
                            }
                            return var2;
                        }
                    }
                }
            }
        } else {
            arg1.add(arg0);
            return var2;
        }
    }

  private static String readAll(File arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aconst_null
        //      1: astore_1
        //      2: new  #7 // java.io.BufferedReader
        //      5: dup
        //      6: new  #12 // java.io.InputStreamReader
        //      9: dup
        //     10: new  #9 // java.io.FileInputStream
        //     13: dup
        //     14: aload_0
        //     15: invokespecial  #54 // java.io.FileInputStream.<init>:(Ljava/io/File;)V
        //     18: invokespecial  #55 // java.io.InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //     21: invokespecial  #48 // java.io.BufferedReader.<init>:(Ljava/io/Reader;)V
        //     24: astore_1
        //     25: new  #16 // java.lang.StringBuilder
        //     28: dup
        //     29: invokespecial  #60 // java.lang.StringBuilder.<init>:()V
        //     32: astore_2
        //     33: aload_1
        //     34: invokevirtual  #50 // java.io.BufferedReader.readLine:()Ljava/lang/String;
        //     37: dup
        //     38: astore_3
        //     39: ifnull  51 (offset +12)
        //     42: aload_2
        //     43: aload_3
        //     44: invokevirtual  #61 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     47: pop
        //     48: goto  33 (offset -15)
        //     51: aload_2
        //     52: invokevirtual  #62 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //     55: astore  4
        //     57: aload_1
        //     58: ifnull  80 (offset +22)
        //     61: aload_1
        //     62: invokevirtual  #49 // java.io.BufferedReader.close:()V
        //     65: goto  80 (offset +15)
        //     68: astore  5
        //     70: new  #14 // java.lang.RuntimeException
        //     73: dup
        //     74: aload  5
        //     76: invokespecial  #58 // java.lang.RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //     79: athrow
        //     80: aload  4
        //     82: areturn
        //     83: astore_2
        //     84: new  #14 // java.lang.RuntimeException
        //     87: dup
        //     88: aload_2
        //     89: invokespecial  #58 // java.lang.RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //     92: athrow
        //     93: astore_2
        //     94: new  #14 // java.lang.RuntimeException
        //     97: dup
        //     98: aload_2
        //     99: invokespecial  #58 // java.lang.RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //    102: athrow
        //    103: astore  6
        //    105: aload_1
        //    106: ifnull  128 (offset +22)
        //    109: aload_1
        //    110: invokevirtual  #49 // java.io.BufferedReader.close:()V
        //    113: goto  128 (offset +15)
        //    116: astore  7
        //    118: new  #14 // java.lang.RuntimeException
        //    121: dup
        //    122: aload  7
        //    124: invokespecial  #58 // java.lang.RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //    127: athrow
        //    128: aload  6
        //    130: athrow
        //       Exception table:
        //         from 61 to 65 target 68 type java.io.IOException
        //         from 2 to 57 target 83 type java.io.FileNotFoundException
        //         from 2 to 57 target 93 type java.io.IOException
        //         from 2 to 57 target 103 type any
        //         from 109 to 113 target 116 type java.io.IOException
        //         from 83 to 105 target 103 type any
    }

  private void putLibraryIntoRuntime() {
        if (Runtime.getSystemRuntime() instanceof NativeRuntime) {
            (((NativeRuntime) Runtime.getSystemRuntime())).loadedLibraries.put(this, new NativeLibrary_LoadedLibraryData(libraryNames, searchPaths, successfulPaths));
        }
    }

}