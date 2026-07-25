// исходный (обфусцированный) внутренний класс: jnr.ffi.Platform.Darwin
package jnr.ffi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jnr.ffi.Platform_OS;
import jnr.ffi.Platform_Supported;

final class Platform_Darwin extends Platform_Supported {

  public Platform_Darwin() { // было: <init>
        super(Platform_OS.DARWIN);
    }

  public String mapLibraryName(String arg0) {
        if (!libPattern.matcher(arg0).find()) {
            return new StringBuilder().append("lib").append(arg0).append(".dylib").toString();
        } else {
            return arg0;
        }
    }

}