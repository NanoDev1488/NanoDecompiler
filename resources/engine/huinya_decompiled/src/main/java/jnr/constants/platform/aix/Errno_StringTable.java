// исходный (обфусцированный) внутренний класс: jnr.constants.platform.aix.Errno.StringTable
package jnr.constants.platform.aix;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.aix.Errno;

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
        var0.put(Errno.EPERM, "Not owner");
        var0.put(Errno.ENOENT, "No such file or directory");
        var0.put(Errno.ESRCH, "No such process");
        var0.put(Errno.EINTR, "Interrupted system call");
        var0.put(Errno.EIO, "I/O error");
        var0.put(Errno.ENXIO, "No such device or address");
        var0.put(Errno.E2BIG, "Arg list too long");
        var0.put(Errno.ENOEXEC, "Exec format error");
        var0.put(Errno.EBADF, "Bad file number");
        var0.put(Errno.ECHILD, "No child processes");
        var0.put(Errno.EDEADLK, "Deadlock condition if locked");
        var0.put(Errno.ENOMEM, "Not enough space");
        var0.put(Errno.EACCES, "Permission denied");
        var0.put(Errno.EFAULT, "Bad address");
        var0.put(Errno.ENOTBLK, "Block device required");
        var0.put(Errno.EBUSY, "Device busy");
        var0.put(Errno.EEXIST, "File exists");
        var0.put(Errno.EXDEV, "Cross-device link");
        var0.put(Errno.ENODEV, "No such device");
        var0.put(Errno.ENOTDIR, "Not a directory");
        var0.put(Errno.EISDIR, "Is a directory");
        var0.put(Errno.EINVAL, "Invalid argument");
        var0.put(Errno.ENFILE, "File table overflow");
        var0.put(Errno.EMFILE, "Too many open files");
        var0.put(Errno.ENOTTY, "Not a typewriter");
        var0.put(Errno.ETXTBSY, "Text file busy");
        var0.put(Errno.EFBIG, "File too large");
        var0.put(Errno.ENOSPC, "No space left on device");
        var0.put(Errno.ESPIPE, "Illegal seek");
        var0.put(Errno.EROFS, "Read-only file system");
        var0.put(Errno.EMLINK, "Too many links");
        var0.put(Errno.EPIPE, "Broken pipe");
        var0.put(Errno.EDOM, "Argument out of domain");
        var0.put(Errno.ERANGE, "Result too large");
        var0.put(Errno.EWOULDBLOCK, "Resource temporarily unavailable");
        var0.put(Errno.EAGAIN, "Resource temporarily unavailable");
        var0.put(Errno.EINPROGRESS, "Operation now in progress");
        var0.put(Errno.EALREADY, "Operation already in progress");
        var0.put(Errno.ENOTSOCK, "Socket operation on non-socket");
        var0.put(Errno.EDESTADDRREQ, "Destination address required");
        var0.put(Errno.EMSGSIZE, "Message too long");
        var0.put(Errno.EPROTOTYPE, "Protocol wrong type for socket");
        var0.put(Errno.ENOPROTOOPT, "Protocol not available");
        var0.put(Errno.EPROTONOSUPPORT, "Protocol not supported");
        var0.put(Errno.ESOCKTNOSUPPORT, "Socket type not supported");
        var0.put(Errno.EOPNOTSUPP, "Operation not supported on socket");
        var0.put(Errno.EPFNOSUPPORT, "Protocol family not supported");
        var0.put(Errno.EAFNOSUPPORT, "Addr family not supported by protocol");
        var0.put(Errno.EADDRINUSE, "Address already in use");
        var0.put(Errno.EADDRNOTAVAIL, "Can't assign requested address");
        var0.put(Errno.ENETDOWN, "Network is down");
        var0.put(Errno.ENETUNREACH, "Network is unreachable");
        var0.put(Errno.ENETRESET, "Network dropped connection on reset");
        var0.put(Errno.ECONNABORTED, "Software caused connection abort");
        var0.put(Errno.ECONNRESET, "Connection reset by peer");
        var0.put(Errno.ENOBUFS, "No buffer space available");
        var0.put(Errno.EISCONN, "Socket is already connected");
        var0.put(Errno.ENOTCONN, "Socket is not connected");
        var0.put(Errno.ESHUTDOWN, "Can't send after socket shutdown");
        var0.put(Errno.ETOOMANYREFS, "Too many references: can't splice");
        var0.put(Errno.ETIMEDOUT, "Connection timed out");
        var0.put(Errno.ECONNREFUSED, "Connection refused");
        var0.put(Errno.ELOOP, "Too many levels of symbolic links");
        var0.put(Errno.ENAMETOOLONG, "File name too long");
        var0.put(Errno.EHOSTDOWN, "Host is down");
        var0.put(Errno.EHOSTUNREACH, "No route to host");
        var0.put(Errno.ENOTEMPTY, "File exists");
        var0.put(Errno.EUSERS, "Too many users");
        var0.put(Errno.EDQUOT, "Disk quota exceeded");
        var0.put(Errno.ESTALE, "Missing file or filesystem");
        var0.put(Errno.EREMOTE, "Item is not local to host");
        var0.put(Errno.ENOLCK, "No locks available");
        var0.put(Errno.ENOSYS, "Function not implemented");
        var0.put(Errno.EOVERFLOW, "Value too large to be stored in data type");
        var0.put(Errno.EIDRM, "Identifier removed");
        var0.put(Errno.ENOMSG, "No message of desired type");
        var0.put(Errno.EILSEQ, "Invalid wide character");
        var0.put(Errno.EBADMSG, "Next message has wrong type");
        var0.put(Errno.EMULTIHOP, "Multihop is not allowed");
        var0.put(Errno.ENODATA, "No message on stream head read q");
        var0.put(Errno.ENOLINK, "The server link has been severed");
        var0.put(Errno.ENOSR, "Out of STREAMS resources");
        var0.put(Errno.ENOSTR, "fd not associated with a stream");
        var0.put(Errno.EPROTO, "Error in protocol");
        var0.put(Errno.ETIME, "System call timed out");
        return var0;
    }

}