// исходный (обфусцированный) внутренний класс: jnr.posix.JavaFileStat.PreNIO2FileAttributes
package jnr.posix;

import java.io.File;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import jnr.posix.JavaFileStat;

class JavaFileStat_PreNIO2FileAttributes implements BasicFileAttributes {

    // ---- поля ----
  final long st_size;
  final int st_ctime;
  final int st_mtime;
  final boolean regularFile;
  final boolean directory;
  final JavaFileStat this$0;

   JavaFileStat_PreNIO2FileAttributes(JavaFileStat arg0, File arg1) { // было: <init>
        super();
        this$0 = arg0;
        st_size = arg1.length();
        st_mtime = ((int) (arg1.lastModified() / 1000L));
        st_ctime = arg1.getParentFile() == null ? st_mtime : ((int) (arg1.getParentFile().lastModified() / 1000L));
        regularFile = arg1.isFile();
        directory = arg1.isDirectory();
    }

  public FileTime lastModifiedTime() {
        return FileTime.fromMillis(((long) st_mtime));
    }

  public FileTime lastAccessTime() {
        return lastModifiedTime();
    }

  public FileTime creationTime() {
        return FileTime.fromMillis(((long) st_mtime));
    }

  public boolean isRegularFile() {
        return (this$0.st_mode & 32768) != 0;
    }

  public boolean isDirectory() {
        return (this$0.st_mode & 16384) != 0;
    }

  public boolean isSymbolicLink() {
        return (this$0.st_mode & 40960) != 0;
    }

  public boolean isOther() {
        return isRegularFile() ? 0 : isDirectory() ? 0 : !isSymbolicLink();
    }

  public long size() {
        return st_size;
    }

  public Object fileKey() {
        return null;
    }

}