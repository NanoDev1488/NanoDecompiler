// исходный (обфусцированный) внутренний класс: jnr.constants.platform.windows.Errno.StringTable
package jnr.constants.platform.windows;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.windows.Errno;

final class Errno_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   Errno_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(Errno.class);
        var0.put(Errno.EPERM, "Operation not permitted");
        var0.put(Errno.ENOENT, "No such file or directory");
        var0.put(Errno.ESRCH, "No such process");
        var0.put(Errno.EINTR, "Interrupted function call");
        var0.put(Errno.EIO, "Input/output error");
        var0.put(Errno.ENXIO, "No such device or address");
        var0.put(Errno.E2BIG, "Arg list too long");
        var0.put(Errno.ENOEXEC, "Exec format error");
        var0.put(Errno.EBADF, "Bad file descriptor");
        var0.put(Errno.ECHILD, "No child processes");
        var0.put(Errno.EDEADLK, "Resource deadlock avoided");
        var0.put(Errno.ENOMEM, "Not enough space");
        var0.put(Errno.EACCES, "Permission denied");
        var0.put(Errno.EFAULT, "Bad address");
        var0.put(Errno.EBUSY, "Resource device");
        var0.put(Errno.EEXIST, "File exists");
        var0.put(Errno.EXDEV, "Improper link");
        var0.put(Errno.ENODEV, "No such device");
        var0.put(Errno.ENOTDIR, "Not a directory");
        var0.put(Errno.EISDIR, "Is a directory");
        var0.put(Errno.EINVAL, "Invalid argument");
        var0.put(Errno.ENFILE, "Too many open files in system");
        var0.put(Errno.EMFILE, "Too many open files");
        var0.put(Errno.ENOTTY, "Inappropriate I/O control operation");
        var0.put(Errno.ETXTBSY, "Unknown error");
        var0.put(Errno.EFBIG, "File too large");
        var0.put(Errno.ENOSPC, "No space left on device");
        var0.put(Errno.ESPIPE, "Invalid seek");
        var0.put(Errno.EROFS, "Read-only file system");
        var0.put(Errno.EMLINK, "Too many links");
        var0.put(Errno.EPIPE, "Broken pipe");
        var0.put(Errno.EDOM, "Domain error");
        var0.put(Errno.ERANGE, "Result too large");
        var0.put(Errno.EWOULDBLOCK, "Unknown error");
        var0.put(Errno.EAGAIN, "Resource temporarily unavailable");
        var0.put(Errno.EINPROGRESS, "Unknown error");
        var0.put(Errno.EALREADY, "Unknown error");
        var0.put(Errno.ENOTSOCK, "Unknown error");
        var0.put(Errno.EDESTADDRREQ, "Unknown error");
        var0.put(Errno.EMSGSIZE, "Unknown error");
        var0.put(Errno.EPROTOTYPE, "Unknown error");
        var0.put(Errno.ENOPROTOOPT, "Unknown error");
        var0.put(Errno.EPROTONOSUPPORT, "Unknown error");
        var0.put(Errno.EOPNOTSUPP, "Unknown error");
        var0.put(Errno.EAFNOSUPPORT, "Unknown error");
        var0.put(Errno.EADDRINUSE, "Unknown error");
        var0.put(Errno.EADDRNOTAVAIL, "Unknown error");
        var0.put(Errno.ENETDOWN, "Unknown error");
        var0.put(Errno.ENETUNREACH, "Unknown error");
        var0.put(Errno.ENETRESET, "Unknown error");
        var0.put(Errno.ECONNABORTED, "Unknown error");
        var0.put(Errno.ECONNRESET, "Unknown error");
        var0.put(Errno.ENOBUFS, "Unknown error");
        var0.put(Errno.EISCONN, "Unknown error");
        var0.put(Errno.ENOTCONN, "Unknown error");
        var0.put(Errno.ETIMEDOUT, "Unknown error");
        var0.put(Errno.ECONNREFUSED, "Unknown error");
        var0.put(Errno.ELOOP, "Unknown error");
        var0.put(Errno.ENAMETOOLONG, "Filename too long");
        var0.put(Errno.EHOSTUNREACH, "Unknown error");
        var0.put(Errno.ENOTEMPTY, "Directory not empty");
        var0.put(Errno.ENOLCK, "No locks available");
        var0.put(Errno.ENOSYS, "Function not implemented");
        var0.put(Errno.EOVERFLOW, "Unknown error");
        var0.put(Errno.EIDRM, "Unknown error");
        var0.put(Errno.ENOMSG, "Unknown error");
        var0.put(Errno.EILSEQ, "Illegal byte sequence");
        var0.put(Errno.EBADMSG, "Unknown error");
        var0.put(Errno.ENODATA, "Unknown error");
        var0.put(Errno.ENOLINK, "Unknown error");
        var0.put(Errno.ENOSR, "Unknown error");
        var0.put(Errno.ENOSTR, "Unknown error");
        var0.put(Errno.EPROTO, "Unknown error");
        var0.put(Errno.ETIME, "Unknown error");
        var0.put(Errno.EDEADLOCK, "Resource deadlock avoided");
        var0.put(Errno.ECANCELED, "Unknown error");
        var0.put(Errno.ENOTRECOVERABLE, "Unknown error");
        var0.put(Errno.EOWNERDEAD, "Unknown error");
        var0.put(Errno.ENOTSUP, "Unknown error");
        return var0;
    }

}