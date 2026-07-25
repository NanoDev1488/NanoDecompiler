// исходный (обфусцированный) внутренний класс: jnr.constants.platform.solaris.Errno.StringTable
package jnr.constants.platform.solaris;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.solaris.Errno;

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
        var0.put(Errno.EDEADLK, "Deadlock situation detected/avoided");
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
        var0.put(Errno.ENOTTY, "Inappropriate ioctl for device");
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
        var0.put(Errno.ENOPROTOOPT, "Option not supported by protocol");
        var0.put(Errno.EPROTONOSUPPORT, "Protocol not supported");
        var0.put(Errno.ESOCKTNOSUPPORT, "Socket type not supported");
        var0.put(Errno.EOPNOTSUPP, "Operation not supported on transport endpoint");
        var0.put(Errno.EPFNOSUPPORT, "Protocol family not supported");
        var0.put(Errno.EAFNOSUPPORT, "Address family not supported by protocol family");
        var0.put(Errno.EADDRINUSE, "Address already in use");
        var0.put(Errno.EADDRNOTAVAIL, "Cannot assign requested address");
        var0.put(Errno.ENETDOWN, "Network is down");
        var0.put(Errno.ENETUNREACH, "Network is unreachable");
        var0.put(Errno.ENETRESET, "Network dropped connection because of reset");
        var0.put(Errno.ECONNABORTED, "Software caused connection abort");
        var0.put(Errno.ECONNRESET, "Connection reset by peer");
        var0.put(Errno.ENOBUFS, "No buffer space available");
        var0.put(Errno.EISCONN, "Transport endpoint is already connected");
        var0.put(Errno.ENOTCONN, "Transport endpoint is not connected");
        var0.put(Errno.ESHUTDOWN, "Cannot send after socket shutdown");
        var0.put(Errno.ETOOMANYREFS, "Too many references: cannot splice");
        var0.put(Errno.ETIMEDOUT, "Connection timed out");
        var0.put(Errno.ECONNREFUSED, "Connection refused");
        var0.put(Errno.ELOOP, "Number of symbolic links encountered during path name traversal exceeds MAXSYMLINKS");
        var0.put(Errno.ENAMETOOLONG, "File name too long");
        var0.put(Errno.EHOSTDOWN, "Host is down");
        var0.put(Errno.EHOSTUNREACH, "No route to host");
        var0.put(Errno.ENOTEMPTY, "Directory not empty");
        var0.put(Errno.EUSERS, "Too many users");
        var0.put(Errno.EDQUOT, "Disc quota exceeded");
        var0.put(Errno.ESTALE, "Stale NFS file handle");
        var0.put(Errno.EREMOTE, "Object is remote");
        var0.put(Errno.ENOLCK, "No record locks available");
        var0.put(Errno.ENOSYS, "Operation not applicable");
        var0.put(Errno.EOVERFLOW, "Value too large for defined data type");
        var0.put(Errno.EIDRM, "Identifier removed");
        var0.put(Errno.ENOMSG, "No message of desired type");
        var0.put(Errno.EILSEQ, "Illegal byte sequence");
        var0.put(Errno.EBADMSG, "Not a data message");
        var0.put(Errno.EMULTIHOP, "Multihop attempted");
        var0.put(Errno.ENODATA, "No data available");
        var0.put(Errno.ENOLINK, "Link has been severed");
        var0.put(Errno.ENOSR, "Out of stream resources");
        var0.put(Errno.ENOSTR, "Not a stream device");
        var0.put(Errno.EPROTO, "Protocol error");
        var0.put(Errno.ETIME, "Timer expired");
        var0.put(Errno.ECHRNG, "Channel number out of range");
        var0.put(Errno.EL2NSYNC, "Level 2 not synchronized");
        var0.put(Errno.EL3HLT, "Level 3 halted");
        var0.put(Errno.EL3RST, "Level 3 reset");
        var0.put(Errno.ELNRNG, "Link number out of range");
        var0.put(Errno.EUNATCH, "Protocol driver not attached");
        var0.put(Errno.ENOCSI, "No CSI structure available");
        var0.put(Errno.EL2HLT, "Level 2 halted");
        var0.put(Errno.EBADE, "Checksum failure");
        var0.put(Errno.EBADR, "Too fragmented");
        var0.put(Errno.EXFULL, "Message tables full");
        var0.put(Errno.ENOANO, "Cryptographic key not available");
        var0.put(Errno.EBADRQC, "Bad request code");
        var0.put(Errno.EBADSLT, "Invalid slot");
        var0.put(Errno.EDEADLOCK, "File locking deadlock");
        var0.put(Errno.EBFONT, "Bad font file format");
        var0.put(Errno.ENONET, "Machine is not on the network");
        var0.put(Errno.ENOPKG, "Package not installed");
        var0.put(Errno.EADV, "Advertise error");
        var0.put(Errno.ESRMNT, "Srmount error");
        var0.put(Errno.ECOMM, "Communication error on send");
        var0.put(Errno.ENOTUNIQ, "Name not unique on network");
        var0.put(Errno.EBADFD, "File descriptor in bad state");
        var0.put(Errno.EREMCHG, "Remote address changed");
        var0.put(Errno.ELIBACC, "Can not access a needed shared library");
        var0.put(Errno.ELIBBAD, "Accessing a corrupted shared library");
        var0.put(Errno.ELIBSCN, ".lib section in a.out corrupted");
        var0.put(Errno.ELIBMAX, "Attempting to link in more shared libraries than system limit");
        var0.put(Errno.ELIBEXEC, "Can not exec a shared library directly");
        var0.put(Errno.ERESTART, "Error 91");
        var0.put(Errno.ESTRPIPE, "Error 92");
        var0.put(Errno.ECANCELED, "Operation canceled");
        var0.put(Errno.ENOTRECOVERABLE, "Lock is not recoverable");
        var0.put(Errno.EOWNERDEAD, "Owner of the lock died");
        var0.put(Errno.ENOTSUP, "Operation not supported");
        return var0;
    }

}