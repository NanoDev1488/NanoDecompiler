// исходный (обфусцированный) внутренний класс: jnr.posix.JavaLibCHelper
package jnr.posix;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.util.HashMap;
import java.util.Map;
import jnr.constants.platform.Errno;
import jnr.ffi.Pointer;
import jnr.posix.FileStat;
import jnr.posix.HANDLE;
import jnr.posix.JavaFileStat;
import jnr.posix.JavaLibCHelper_Anon1;
import jnr.posix.JavaLibCHelper_PosixExec;
import jnr.posix.JavaLibCHelper_ReflectiveAccess;
import jnr.posix.JavaPOSIX_LoginInfo;
import jnr.posix.JavaPasswd;
import jnr.posix.JavaSecuredFile;
import jnr.posix.POSIXHandler;
import jnr.posix.Passwd;
import jnr.posix.util.Chmod;
import jnr.posix.util.JavaCrypt;

public class JavaLibCHelper {

    // ---- поля ----
  public static final int STDIN = 0;
  public static final int STDOUT = 1;
  public static final int STDERR = 2;
  private static final ThreadLocal errno;
  private final POSIXHandler handler;
  private final Map env;
   ThreadLocal pwIndex;

    static {
        errno = new ThreadLocal();
    }

  public JavaLibCHelper(POSIXHandler arg0) { // было: <init>
        super();
        pwIndex = new JavaLibCHelper_Anon1(this);
        env = new HashMap();
        handler = arg0;
    }

  public static FileDescriptor getDescriptorFromChannel(Channel arg0) {
        FileDescriptor __stk1;
        FileDescriptor __stk2;
        Throwable __stk3;
        if (JavaLibCHelper_ReflectiveAccess.access$000() == null) {
            if (JavaLibCHelper_ReflectiveAccess.access$200() == null) {
                FileDescriptor var1;
                if (JavaLibCHelper_ReflectiveAccess.access$400() == null) {
                    return new FileDescriptor();
                } else {
                    var1 = new FileDescriptor();
                }
                try {
                    Method var2 = arg0.getClass().getMethod("getFD", new Class[0]);
                    JavaLibCHelper_ReflectiveAccess.access$400().set(var1, ((Integer) var2.invoke(arg0, new Object[0])));
                    __stk3 = var1;
                } catch (Exception e1) {
                    Throwable var2 = e1;
                }
            } else {
                if (!JavaLibCHelper_ReflectiveAccess.access$300().isInstance(arg0)) {
                    FileDescriptor var1;
                    if (JavaLibCHelper_ReflectiveAccess.access$400() == null) {
                        return new FileDescriptor();
                    } else {
                        var1 = new FileDescriptor();
                    }
                    Method var2 = arg0.getClass().getMethod("getFD", new Class[0]);
                    JavaLibCHelper_ReflectiveAccess.access$400().set(var1, ((Integer) var2.invoke(arg0, new Object[0])));
                    return var1;
                } else {
                    try {
                        __stk2 = ((FileDescriptor) JavaLibCHelper_ReflectiveAccess.access$200().get(arg0));
                    } catch (Exception e2) {
                        Throwable var1 = e2;
                    }
                }
            }
        } else {
            if (!JavaLibCHelper_ReflectiveAccess.access$100().isInstance(arg0)) {
                if (JavaLibCHelper_ReflectiveAccess.access$200() == null) {
                    FileDescriptor var1;
                    if (JavaLibCHelper_ReflectiveAccess.access$400() == null) {
                        return new FileDescriptor();
                    } else {
                        var1 = new FileDescriptor();
                    }
                    Method var2 = arg0.getClass().getMethod("getFD", new Class[0]);
                    JavaLibCHelper_ReflectiveAccess.access$400().set(var1, ((Integer) var2.invoke(arg0, new Object[0])));
                    return var1;
                } else {
                    if (!JavaLibCHelper_ReflectiveAccess.access$300().isInstance(arg0)) {
                        FileDescriptor var1;
                        if (JavaLibCHelper_ReflectiveAccess.access$400() == null) {
                            return new FileDescriptor();
                        } else {
                            var1 = new FileDescriptor();
                        }
                        Method var2 = arg0.getClass().getMethod("getFD", new Class[0]);
                        JavaLibCHelper_ReflectiveAccess.access$400().set(var1, ((Integer) var2.invoke(arg0, new Object[0])));
                        return var1;
                    } else {
                        return ((FileDescriptor) JavaLibCHelper_ReflectiveAccess.access$200().get(arg0));
                    }
                }
            } else {
                try {
                    __stk1 = ((FileDescriptor) JavaLibCHelper_ReflectiveAccess.access$000().invoke(arg0, new Object[0]));
                } catch (Exception var1) {
                }
            }
        }
    }

  static int errno() {
        Integer var0 = ((Integer) errno.get());
        return var0 == null ? 0 : var0.intValue();
    }

  static void errno(int arg0) {
        errno.set(Integer.valueOf(arg0));
    }

  static void errno(Errno arg0) {
        errno.set(Integer.valueOf(arg0.intValue()));
    }

  public int chmod(String arg0, int arg1) {
        return Chmod.chmod(new JavaSecuredFile(arg0), Integer.toOctalString(arg1));
    }

  public int chown(String arg0, int arg1, int arg2) {
        JavaLibCHelper_PosixExec var4 = new JavaLibCHelper_PosixExec(handler);
        int var5 = -1;
        int var6 = -1;
        try {
            if (arg1 == -1) {
                if (arg2 != -1) {
                    var6 = var4.runAndWait(new String[]{"chgrp ", new StringBuilder().append("").append(arg1).toString(), arg0});
                }
            } else {
                var5 = var4.runAndWait(new String[]{"chown", new StringBuilder().append("").append(arg1).toString(), arg0});
                if (arg2 != -1) {
                    String[] __obj2 = new String[]{"chgrp ", new StringBuilder().append("").append(arg1).toString(), arg0};
                    __obj2[0] = "chgrp ";
                    __obj2[1] = new StringBuilder().append("").append(arg1).toString();
                    __obj2[2] = arg0;
                    var6 = var4.runAndWait(new String[]{"chgrp ", new StringBuilder().append("").append(arg1).toString(), arg0});
                }
            }
            return var5 == -1 ? 1 : var6 == -1;
        } catch (InterruptedException var7) {
            Thread.currentThread().interrupt();
        } catch (Exception e2) {
            Throwable var7 = e2;
        }
        return var5 == -1 ? 1 : var6 == -1;
    }

  public static CharSequence crypt(CharSequence arg0, CharSequence arg1) {
        return JavaCrypt.crypt(arg0, arg1);
    }

  public static byte[] crypt(byte[] arg0, byte[] arg1) {
        return JavaCrypt.crypt(new String(arg0), new String(arg1)).toString().getBytes();
    }

  public int getfd(FileDescriptor arg0) {
        return getfdFromDescriptor(arg0);
    }

  public static int getfdFromDescriptor(FileDescriptor arg0) {
        int __stk1;
        if (arg0 == null) {
            return -1;
        }
        if (JavaLibCHelper_ReflectiveAccess.access$400() == null) {
            return -1;
        }
        try {
            __stk1 = JavaLibCHelper_ReflectiveAccess.access$400().getInt(arg0);
        } catch (SecurityException var1) {
        } catch (IllegalArgumentException e2) {
            Throwable var1 = e2;
        } catch (IllegalAccessException e3) {
            Throwable var1 = e3;
        }
    }

  public static HANDLE gethandle(FileDescriptor arg0) {
        HANDLE __stk1;
        if (arg0 == null) {
            return HANDLE.valueOf(-1L);
        }
        if (JavaLibCHelper_ReflectiveAccess.access$500() == null) {
            return HANDLE.valueOf(-1L);
        }
        try {
            __stk1 = gethandle(JavaLibCHelper_ReflectiveAccess.access$500().getLong(arg0));
        } catch (SecurityException var1) {
        } catch (IllegalArgumentException e2) {
            Throwable var1 = e2;
        } catch (IllegalAccessException e3) {
            Throwable var1 = e3;
        }
    }

  public static HANDLE gethandle(long arg0) {
        return HANDLE.valueOf(arg0);
    }

  public String getlogin() {
        return System.getProperty("user.name");
    }

  public String gethostname() {
        String var1 = System.getenv("HOSTNAME");
        if (var1 == null) {
            var1 = System.getenv("COMPUTERNAME");
        }
        return var1;
    }

  public int getpid() {
        int __stk1;
        try {
            __stk1 = handler.getPID();
        } catch (UnsupportedOperationException var1) {
        }
    }

  public Passwd getpwent() {
        JavaPasswd __stk1;
        __stk1 = (((Integer) pwIndex.get())).intValue() != 0 ? null : new JavaPasswd(handler);
        JavaPasswd var1 = __stk1;
        pwIndex.set(Integer.valueOf((((Integer) pwIndex.get())).intValue() + 1));
        return ((Passwd) var1);
    }

  public int setpwent() {
        return 0;
    }

  public int endpwent() {
        pwIndex.set(Integer.valueOf(0));
        return 0;
    }

  public Passwd getpwuid(int arg0) {
        return arg0 != JavaPOSIX_LoginInfo.UID ? null : new JavaPasswd(handler);
    }

  public int isatty(int arg0) {
        return arg0 == 1 ? 1 : arg0 == 0 ? 1 : arg0 == 2;
    }

  public int link(String arg0, String arg1) {
        int __stk2;
        try {
            __stk2 = new JavaLibCHelper_PosixExec(handler).runAndWait(new String[]{"ln", arg0, arg1});
        } catch (InterruptedException var3) {
            Thread.currentThread().interrupt();
        } catch (Exception e2) {
            Throwable var3 = e2;
        }
    }

  public int lstat(String arg0, FileStat arg1) {
        JavaSecuredFile var3 = new JavaSecuredFile(arg0);
        if (var3.exists()) {
            JavaFileStat var4 = ((JavaFileStat) arg1);
            var4.setup(arg0);
            return 0;
        } else {
            errno(Errno.ENOENT);
            return -1;
        }
    }

  public int mkdir(String arg0, int arg1) {
        JavaSecuredFile var3 = new JavaSecuredFile(arg0);
        if (var3.mkdir()) {
            chmod(arg0, arg1);
            return 0;
        } else {
            return -1;
        }
    }

  public int rmdir(String arg0) {
        return !new JavaSecuredFile(arg0).delete() ? -1 : 0;
    }

  public static int chdir(String arg0) {
        System.setProperty("user.dir", arg0);
        return 0;
    }

  public int stat(String arg0, FileStat arg1) {
        int __stk1;
        JavaFileStat var3 = ((JavaFileStat) arg1);
        try {
            JavaSecuredFile var4 = new JavaSecuredFile(arg0);
            if (var4.exists()) {
                try {
                    var3.setup(var4.getCanonicalPath());
                } catch (IOException e1) {
                    var4 = e1;
                }
            } else {
                errno(Errno.ENOENT);
                __stk1 = -1;
            }
            return 0;
        } catch (IOException e2) {
            Throwable var4 = e1;
        }
    }

  public int symlink(String arg0, String arg1) {
        int __stk2;
        try {
            __stk2 = new JavaLibCHelper_PosixExec(handler).runAndWait(new String[]{"ln", "-s", arg0, arg1});
        } catch (InterruptedException var3) {
            Thread.currentThread().interrupt();
        } catch (Exception e2) {
            Throwable var3 = e2;
        }
    }

  public int readlink(String arg0, ByteBuffer arg1, int arg2) {
        int __stk2;
        int __stk3;
        try {
            ByteArrayOutputStream var4 = new ByteArrayOutputStream();
            new JavaLibCHelper_PosixExec(handler).runAndWait(var4, new String[]{"readlink", arg0});
            byte[] var5 = var4.toByteArray();
            if (var5.length > arg2) {
                __stk2 = -1;
            }
            if (var5.length == 0) {
                __stk2 = -1;
            }
            try {
                arg1.put(var5, 0, var5.length - 1);
                __stk3 = arg1.position();
            } catch (InterruptedException e1) {
                var4 = e1;
                Thread.currentThread().interrupt();
                errno(Errno.ENOENT);
                return -1;
            }
        } catch (InterruptedException e2) {
            Throwable var4 = e1;
            Thread.currentThread().interrupt();
            errno(Errno.ENOENT);
            return -1;
        }
    }

  public Map getEnv() {
        return env;
    }

  public static FileDescriptor toFileDescriptor(int arg0) {
        FileDescriptor var1 = new FileDescriptor();
        try {
            JavaLibCHelper_ReflectiveAccess.access$400().set(var1, Integer.valueOf(arg0));
        } catch (IllegalAccessException var2) {
            throw new RuntimeException(var2);
        }
    }

  public static FileDescriptor toFileDescriptor(HANDLE arg0) {
        FileDescriptor var1 = new FileDescriptor();
        try {
            JavaLibCHelper_ReflectiveAccess.access$500().set(var1, Long.valueOf(arg0.toPointer().address()));
        } catch (IllegalAccessException var2) {
            throw new RuntimeException(var2);
        }
    }

}