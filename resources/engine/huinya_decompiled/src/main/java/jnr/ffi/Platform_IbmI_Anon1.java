// исходный (обфусцированный) внутренний класс: jnr.ffi.Platform.IbmI$1
package jnr.ffi;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jnr.ffi.Platform_IbmI;

class Platform_IbmI_Anon1 implements FilenameFilter {

    // ---- поля ----
  final Pattern val$dotAorSoPattern;
  final Pattern val$versionedLibPattern;
  final Platform_IbmI this$0;

   Platform_IbmI_Anon1(Platform_IbmI arg0, Pattern arg1, Pattern arg2) { // было: <init>
        super();
        this$0 = arg0;
        val$dotAorSoPattern = arg1;
        val$versionedLibPattern = arg2;
    }

  public boolean accept(File arg0, String arg1) {
        return val$dotAorSoPattern.matcher(arg1).matches() ? 1 : val$versionedLibPattern.matcher(arg1).matches();
    }

}