// исходный (обфусцированный) внутренний класс: jnr.constants.platform.freebsd.aarch64.Errno.StringTable
package jnr.constants.platform.freebsd.aarch64;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.freebsd.aarch64.Errno;

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
        var0.put(Errno.EINTR, "Interrupted system call");
        var0.put(Errno.EIO, "Input/output error");
        var0.put(Errno.ENXIO, "Device not configured");
        var0.put(Errno.E2BIG, "Argument list too long");
        var0.put(Errno.ENOEXEC, "Exec format error");
        var0.put(Errno.EBADF, "Bad file descriptor");
        var0.put(Errno.ECHILD, "No child processes");
        var0.put(Errno.EDEADLK, "Resource deadlock avoided");
        var0.put(Errno.ENOMEM, "Cannot allocate memory");
        var0.put(Errno.EACCES, "Permission denied");
        var0.put(Errno.EFAULT, "Bad address");
        var0.put(Errno.ENOTBLK, "Block device required");
        var0.put(Errno.EBUSY, "Device busy");
        var0.put(Errno.EEXIST, "File exists");
        var0.put(Errno.EXDEV, "Cross-device link");
        var0.put(Errno.ENODEV, "Operation not supported by device");
        var0.put(Errno.ENOTDIR, "Not a directory");
        var0.put(Errno.EISDIR, "Is a directory");
        var0.put(Errno.EINVAL, "Invalid argument");
        var0.put(Errno.ENFILE, "Too many open files in system");
        var0.put(Errno.EMFILE, "Too many open files");
        var0.put(Errno.ENOTTY, "Inappropriate ioctl for device");
        var0.put(Errno.ETXTBSY, "Text file busy");
        var0.put(Errno.EFBIG, "File too large");
        var0.put(Errno.ENOSPC, "No space left on device");
        var0.put(Errno.ESPIPE, "Illegal seek");
        var0.put(Errno.EROFS, "Read-only file system");
        var0.put(Errno.EMLINK, "Too many links");
        var0.put(Errno.EPIPE, "Broken pipe");
        var0.put(Errno.EDOM, "Numerical argument out of domain");
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
        var0.put(Errno.EOPNOTSUPP, "Operation not supported");
        var0.put(Errno.EPFNOSUPPORT, "Protocol family not supported");
        var0.put(Errno.EAFNOSUPPORT, "Address family not supported by protocol family");
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
        var0.put(Errno.ETIMEDOUT, "Operation timed out");
        var0.put(Errno.ECONNREFUSED, "Connection refused");
        var0.put(Errno.ELOOP, "Too many levels of symbolic links");
        var0.put(Errno.ENAMETOOLONG, "File name too long");
        var0.put(Errno.EHOSTDOWN, "Host is down");
        var0.put(Errno.EHOSTUNREACH, "No route to host");
        var0.put(Errno.ENOTEMPTY, "Directory not empty");
        var0.put(Errno.EUSERS, "Too many users");
        var0.put(Errno.EDQUOT, "Disc quota exceeded");
        var0.put(Errno.ESTALE, "Stale NFS file handle");
        var0.put(Errno.EREMOTE, "Too many levels of remote in path");
        var0.put(Errno.ENOLCK, "No locks available");
        var0.put(Errno.ENOSYS, "Function not implemented");
        var0.put(Errno.EOVERFLOW, "Value too large to be stored in data type");
        var0.put(Errno.EIDRM, "Identifier removed");
        var0.put(Errno.ENOMSG, "No message of desired type");
        var0.put(Errno.EILSEQ, "Illegal byte sequence");
        var0.put(Errno.EBADMSG, "Bad message");
        var0.put(Errno.EMULTIHOP, "Multihop attempted");
        var0.put(Errno.ENOLINK, "Link has been severed");
        var0.put(Errno.EPROTO, "Protocol error");
        var0.put(Errno.ECANCELED, "Operation canceled");
        var0.put(Errno.ENOTRECOVERABLE, "State not recoverable");
        var0.put(Errno.EOWNERDEAD, "Previous owner died");
        var0.put(Errno.EAUTH, "Authentication error");
        var0.put(Errno.EBADRPC, "RPC struct is bad");
        var0.put(Errno.EDOOFUS, "Programming error");
        var0.put(Errno.EFTYPE, "Inappropriate file type or format");
        var0.put(Errno.ENEEDAUTH, "Need authenticator");
        var0.put(Errno.ENOATTR, "Attribute not found");
        var0.put(Errno.ENOTSUP, "Operation not supported");
        var0.put(Errno.EPROCLIM, "Too many processes");
        var0.put(Errno.EPROCUNAVAIL, "Bad procedure for program");
        var0.put(Errno.EPROGMISMATCH, "Program version wrong");
        var0.put(Errno.EPROGUNAVAIL, "RPC prog. not avail");
        var0.put(Errno.ERPCMISMATCH, "RPC version wrong");
        var0.put(Errno.ECAPMODE, "Not permitted in capability mode");
        var0.put(Errno.ENOTCAPABLE, "Capabilities insufficient");
        return var0;
    }

}