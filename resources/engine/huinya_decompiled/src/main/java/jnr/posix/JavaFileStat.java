// исходный (обфусцированный) внутренний класс: jnr.posix.JavaFileStat
package jnr.posix;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;
import jnr.posix.AbstractJavaFileStat;
import jnr.posix.FileStat;
import jnr.posix.JavaSecuredFile;
import jnr.posix.POSIX;
import jnr.posix.POSIXHandler;

public class JavaFileStat extends AbstractJavaFileStat {

    // ---- поля ----
   short st_mode;
   BasicFileAttributes attrs;
   PosixFileAttributes posixAttrs;
   DosFileAttributes dosAttrs;

  public JavaFileStat(POSIX arg0, POSIXHandler arg1) { // было: <init>
        super(arg0, arg1);
    }

  public void setup(String arg0) {
        JavaSecuredFile var2 = new JavaSecuredFile(arg0);
        Path var3 = var2.toPath();
        try {
            posixAttrs = ((PosixFileAttributes) Files.readAttributes(var3, PosixFileAttributes.class, new LinkOption[]{LinkOption.NOFOLLOW_LINKS}));
            attrs = posixAttrs;
        } catch (UnsupportedOperationException var4) {
            try {
                dosAttrs = ((DosFileAttributes) Files.readAttributes(var3, DosFileAttributes.class, new LinkOption[]{LinkOption.NOFOLLOW_LINKS}));
                attrs = dosAttrs;
            } catch (UnsupportedOperationException var5) {
                attrs = Files.readAttributes(var3, BasicFileAttributes.class, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
            }
        }
        st_mode = calculateMode(var2, 0);
    }

  private short calculateMode(File arg0, short arg1) {
        if (arg0.canRead()) {
            arg1 = ((short) (arg1 | 292));
        }
        if (arg0.canWrite()) {
            arg1 = ((short) (arg1 | 146));
            arg1 = ((short) (arg1 & -19));
        }
        if (!arg0.isDirectory()) {
            if (arg0.isFile()) {
                arg1 = ((short) (arg1 | 32768));
            }
        } else {
            arg1 = ((short) (arg1 | 16384));
        }
        if (posixAttrs == null) {
            try {
                arg1 = calculateSymlink(arg0, arg1);
            } catch (IOException var3) {
            }
        } else {
            arg1 = !posixAttrs.isSymbolicLink() ? calculateSymlink(arg0, arg1) : ((short) (arg1 | 40960));
        }
        return arg1;
    }

  private static short calculateSymlink(File arg0, short arg1) {
        if (arg0.getAbsoluteFile().getParentFile() != null) {
            File var2 = arg0.getAbsoluteFile().getParentFile();
            File var3 = var2.getCanonicalFile();
            if (!var3.getAbsolutePath().equals(var2.getAbsolutePath())) {
                arg0 = new JavaSecuredFile(new StringBuilder().append(var3.getAbsolutePath()).append("/").append(arg0.getName()).toString());
                if (!arg0.getAbsolutePath().equalsIgnoreCase(arg0.getCanonicalPath())) {
                    arg1 = ((short) (arg1 | 40960));
                }
                return arg1;
            } else {
                if (arg0.getAbsolutePath().equalsIgnoreCase(arg0.getCanonicalPath())) {
                    arg0 = new JavaSecuredFile(new StringBuilder().append(var3.getAbsolutePath()).append("/").append(arg0.getName()).toString());
                    if (!arg0.getAbsolutePath().equalsIgnoreCase(arg0.getCanonicalPath())) {
                        arg1 = ((short) (arg1 | 40960));
                    }
                    return arg1;
                } else {
                    arg1 = ((short) (arg1 | 40960));
                    return arg1;
                }
            }
        } else {
            return arg1;
        }
    }

  public long atime() {
        return ((long) ((int) (attrs.lastAccessTime().toMillis() / 1000L)));
    }

  public long ctime() {
        return ((long) ((int) (attrs.creationTime().toMillis() / 1000L)));
    }

  public boolean isDirectory() {
        return attrs.isDirectory();
    }

  public boolean isEmpty() {
        return attrs.size() == 0L;
    }

  public boolean isExecutable() {
        if (posixAttrs == null) {
            return false;
        } else {
            Set var1 = posixAttrs.permissions();
            return var1.contains(PosixFilePermission.OWNER_EXECUTE) ? 1 : var1.contains(PosixFilePermission.GROUP_EXECUTE) ? 1 : var1.contains(PosixFilePermission.OTHERS_EXECUTE);
        }
    }

  public boolean isExecutableReal() {
        return isExecutable();
    }

  public boolean isFile() {
        return attrs.isRegularFile();
    }

  public boolean isGroupOwned() {
        return groupMember(gid());
    }

  public boolean isIdentical(FileStat arg0) {
        Object var2 = attrs.fileKey();
        if (var2 == null) {
            handler.unimplementedError("identical file detection");
            return false;
        } else {
            if (!(arg0 instanceof JavaFileStat)) {
                handler.unimplementedError("identical file detection");
                return false;
            } else {
                JavaFileStat var3 = ((JavaFileStat) arg0);
                return var2.equals(var3.attrs.fileKey());
            }
        }
    }

  public boolean isOwned() {
        return posix.geteuid() == uid();
    }

  public boolean isROwned() {
        return posix.getuid() == uid();
    }

  public boolean isReadable() {
        if (posixAttrs == null) {
            int var1 = mode();
            if ((var1 & 256) == 0) {
                if ((var1 & 32) == 0) {
                    if ((var1 & 4) == 0) {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            } else {
                return true;
            }
        } else {
            Set var1 = posixAttrs.permissions();
            return var1.contains(PosixFilePermission.OWNER_READ) ? 1 : var1.contains(PosixFilePermission.GROUP_READ) ? 1 : var1.contains(PosixFilePermission.OTHERS_READ);
        }
    }

  public boolean isReadableReal() {
        return isReadable();
    }

  public boolean isSymlink() {
        if (posixAttrs == null) {
            return (mode() & 40960) == 40960;
        } else {
            return posixAttrs.isSymbolicLink();
        }
    }

  public boolean isWritable() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #42 // jnr.posix.JavaFileStat.posixAttrs:Ljava/nio/file/attribute/PosixFileAttributes;
        //      4: ifnull  59 (offset +55)
        //      7: aload_0
        //      8: getfield  #42 // jnr.posix.JavaFileStat.posixAttrs:Ljava/nio/file/attribute/PosixFileAttributes;
        //     11: invokeinterface  #84 // java.nio.file.attribute.PosixFileAttributes.permissions:()Ljava/util/Set;, count 1
        //     16: astore_1
        //     17: aload_1
        //     18: getstatic  #37 // java.nio.file.attribute.PosixFilePermission.OWNER_WRITE:Ljava/nio/file/attribute/PosixFilePermission;
        //     21: invokeinterface  #85 // java.util.Set.contains:(Ljava/lang/Object;)Z, count 2
        //     26: ifne  53 (offset +27)
        //     29: aload_1
        //     30: getstatic  #31 // java.nio.file.attribute.PosixFilePermission.GROUP_WRITE:Ljava/nio/file/attribute/PosixFilePermission;
        //     33: invokeinterface  #85 // java.util.Set.contains:(Ljava/lang/Object;)Z, count 2
        //     38: ifne  53 (offset +15)
        //     41: aload_1
        //     42: getstatic  #34 // java.nio.file.attribute.PosixFilePermission.OTHERS_WRITE:Ljava/nio/file/attribute/PosixFilePermission;
        //     45: invokeinterface  #85 // java.util.Set.contains:(Ljava/lang/Object;)Z, count 2
        //     50: ifeq  57 (offset +7)
        //     53: iconst_1
        //     54: goto  58 (offset +4)
        //     57: iconst_0
        //     58: ireturn
        //     59: aload_0
        //     60: getfield  #39 // jnr.posix.JavaFileStat.dosAttrs:Ljava/nio/file/attribute/DosFileAttributes;
        //     63: ifnull  84 (offset +21)
        //     66: aload_0
        //     67: getfield  #39 // jnr.posix.JavaFileStat.dosAttrs:Ljava/nio/file/attribute/DosFileAttributes;
        //     70: invokeinterface  #82 // java.nio.file.attribute.DosFileAttributes.isReadOnly:()Z, count 1
        //     75: ifne  82 (offset +7)
        //     78: iconst_1
        //     79: goto  83 (offset +4)
        //     82: iconst_0
        //     83: ireturn
        //     84: aload_0
        //     85: invokevirtual  #71 // jnr.posix.JavaFileStat.mode:()I
        //     88: istore_1
        //     89: iload_1
        //     90: sipush  128
        //     93: iand
        //     94: ifeq  99 (offset +5)
        //     97: iconst_1
        //     98: ireturn
        //     99: iload_1
        //    100: bipush  16
        //    102: iand
        //    103: ifeq  108 (offset +5)
        //    106: iconst_1
        //    107: ireturn
        //    108: iload_1
        //    109: iconst_2
        //    110: iand
        //    111: ifeq  116 (offset +5)
        //    114: iconst_1
        //    115: ireturn
        //    116: iconst_0
        //    117: ireturn
    }

  public boolean isWritableReal() {
        return isWritable();
    }

  public int mode() {
        return st_mode & 65535;
    }

  public long mtime() {
        return ((long) ((int) (attrs.lastModifiedTime().toMillis() / 1000L)));
    }

  public long st_size() {
        return attrs.size();
    }

}