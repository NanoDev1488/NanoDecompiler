// исходный (обфусцированный) внутренний класс: jnr.posix.JavaLibCHelper.ErrnoParsingOutputStream
package jnr.posix;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jnr.constants.platform.Errno;
import jnr.posix.JavaLibCHelper_Anon1;

final class JavaLibCHelper_ErrnoParsingOutputStream extends OutputStream {

    // ---- поля ----
  private final ByteArrayOutputStream baos;
  private final AtomicReference errno;
  static Map errorPatterns;

    static {
        errorPatterns = new HashMap();
        errorPatterns.put(Pattern.compile("File exists"), Errno.EEXIST);
        errorPatterns.put(Pattern.compile("Operation not permitted"), Errno.EPERM);
        errorPatterns.put(Pattern.compile("No such file or directory"), Errno.ENOENT);
        errorPatterns.put(Pattern.compile("Input/output error"), Errno.EIO);
        errorPatterns.put(Pattern.compile("Not a directory"), Errno.ENOTDIR);
        errorPatterns.put(Pattern.compile("No space left on device"), Errno.ENOSPC);
        errorPatterns.put(Pattern.compile("Read-only file system"), Errno.EROFS);
        errorPatterns.put(Pattern.compile("Too many links"), Errno.EMLINK);
    }

  private JavaLibCHelper_ErrnoParsingOutputStream(AtomicReference arg0) { // было: <init>
        super();
        baos = new ByteArrayOutputStream();
        errno = arg0;
    }

  public void write(int arg0) {
        if (arg0 == 13) {
            if (baos.size() > 0) {
                String var2 = baos.toString();
                baos.reset();
                parseError(var2);
            }
        } else {
            if (arg0 == 10) {
                if (baos.size() > 0) {
                    String var2 = baos.toString();
                    baos.reset();
                    parseError(var2);
                }
            } else {
                if (arg0 == -1) {
                    if (baos.size() > 0) {
                        String var2 = baos.toString();
                        baos.reset();
                        parseError(var2);
                    }
                } else {
                    baos.write(arg0);
                }
            }
        }
    }

   void parseError(String arg0) {
        Iterator var2 = errorPatterns.entrySet().iterator();
        while (var2.hasNext()) {
            Entry var3 = ((Entry) var2.next());
            if ((((Pattern) var3.getKey())).matcher(arg0).find()) {
                errno.set(((Errno) var3.getValue()));
            }
            continue;
        }
    }

   JavaLibCHelper_ErrnoParsingOutputStream(AtomicReference arg0, JavaLibCHelper_Anon1 arg1) { // было: <init>
        this(arg0);
    }

}