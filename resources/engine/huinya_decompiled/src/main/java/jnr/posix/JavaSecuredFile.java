// исходный (обфусцированный) внутренний класс: jnr.posix.JavaSecuredFile
package jnr.posix;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URI;

public class JavaSecuredFile extends File {

  public JavaSecuredFile(String arg0) { // было: <init>
        super(arg0);
    }

  public JavaSecuredFile(String arg0, String arg1) { // было: <init>
        super(arg0, arg1);
    }

  public JavaSecuredFile(File arg0, String arg1) { // было: <init>
        super(arg0, arg1);
    }

  public JavaSecuredFile(URI arg0) { // было: <init>
        super(arg0);
    }

  public File getParentFile() {
        JavaSecuredFile __stk1;
        String var1 = getParent();
        __stk1 = var1 != null ? new JavaSecuredFile(var1) : null;
        return ((File) __stk1);
    }

  public File getAbsoluteFile() {
        JavaSecuredFile __stk1;
        String var1 = getAbsolutePath();
        __stk1 = var1 != null ? new JavaSecuredFile(var1) : null;
        return ((File) __stk1);
    }

  public File getCanonicalFile() {
        JavaSecuredFile __stk1;
        String var1 = getCanonicalPath();
        __stk1 = var1 != null ? new JavaSecuredFile(var1) : null;
        return ((File) __stk1);
    }

  public boolean canRead() {
        boolean __stk1;
        try {
            __stk1 = super.canRead();
        } catch (SecurityException var1) {
            return false;
        }
    }

  public boolean canWrite() {
        boolean __stk1;
        try {
            __stk1 = super.canWrite();
        } catch (SecurityException var1) {
            return false;
        }
    }

  public boolean exists() {
        boolean __stk1;
        try {
            __stk1 = super.exists();
        } catch (SecurityException var1) {
            return false;
        }
    }

  public boolean isDirectory() {
        boolean __stk1;
        try {
            __stk1 = super.isDirectory();
        } catch (SecurityException var1) {
            return false;
        }
    }

  public boolean isFile() {
        boolean __stk1;
        try {
            __stk1 = super.isFile();
        } catch (SecurityException var1) {
            return false;
        }
    }

  public boolean isHidden() {
        boolean __stk1;
        try {
            __stk1 = super.isHidden();
        } catch (SecurityException var1) {
            return false;
        }
    }

  public boolean delete() {
        boolean __stk1;
        try {
            __stk1 = super.delete();
        } catch (SecurityException var1) {
            return false;
        }
    }

  public boolean mkdir() {
        boolean __stk1;
        try {
            __stk1 = super.mkdir();
        } catch (SecurityException var1) {
            return false;
        }
    }

  public boolean mkdirs() {
        boolean __stk1;
        try {
            __stk1 = super.mkdirs();
        } catch (SecurityException var1) {
            return false;
        }
    }

  public boolean renameTo(File arg0) {
        boolean __stk1;
        try {
            __stk1 = super.renameTo(arg0);
        } catch (SecurityException var2) {
            return false;
        }
    }

  public boolean setLastModified(long arg0) {
        boolean __stk1;
        try {
            __stk1 = super.setLastModified(arg0);
        } catch (SecurityException var3) {
            return false;
        }
    }

  public boolean setReadOnly() {
        boolean __stk1;
        try {
            __stk1 = super.setReadOnly();
        } catch (SecurityException var1) {
            return false;
        }
    }

  public String getCanonicalPath() {
        String __stk1;
        try {
            __stk1 = super.getCanonicalPath();
        } catch (SecurityException var1) {
            throw new IOException(var1);
        }
    }

  public boolean createNewFile() {
        boolean __stk1;
        try {
            __stk1 = super.createNewFile();
        } catch (SecurityException var1) {
            throw new IOException(var1);
        }
    }

  public String[] list() {
        String[] __stk1;
        try {
            __stk1 = super.list();
        } catch (SecurityException var1) {
            return null;
        }
    }

  public String[] list(FilenameFilter arg0) {
        String[] __stk1;
        try {
            __stk1 = super.list(arg0);
        } catch (SecurityException var2) {
            return null;
        }
    }

  public File[] listFiles() {
        File[] __stk1;
        try {
            __stk1 = super.listFiles();
        } catch (SecurityException var1) {
            return null;
        }
    }

  public File[] listFiles(FileFilter arg0) {
        File[] __stk1;
        try {
            __stk1 = super.listFiles(arg0);
        } catch (SecurityException var2) {
            return null;
        }
    }

  public long lastModified() {
        long __stk1;
        try {
            __stk1 = super.lastModified();
        } catch (SecurityException var1) {
            return 0L;
        }
    }

  public long length() {
        long __stk1;
        try {
            __stk1 = super.length();
        } catch (SecurityException var1) {
            return 0L;
        }
    }

}