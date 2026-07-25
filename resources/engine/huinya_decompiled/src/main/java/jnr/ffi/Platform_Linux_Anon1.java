// исходный (обфусцированный) внутренний класс: jnr.ffi.Platform.Linux$1
package jnr.ffi;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jnr.ffi.Platform_Linux;

class Platform_Linux_Anon1 implements FilenameFilter {

    // ---- поля ----
  final Pattern val$versionedLibPattern;
  final Platform_Linux this$0;

   Platform_Linux_Anon1(Platform_Linux arg0, Pattern arg1) { // было: <init>
        super();
        this$0 = arg0;
        val$versionedLibPattern = arg1;
    }

  public boolean accept(File arg0, String arg1) {
        return val$versionedLibPattern.matcher(arg1).matches();
    }

}