// исходный (обфусцированный) внутренний класс: jnr.constants.platform.windows.LastError.StringTable
package jnr.constants.platform.windows;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.windows.LastError;

final class LastError_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   LastError_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(LastError.class);
        var0.put(LastError.ERROR_INVALID_FUNCTION, "Incorrect function");
        var0.put(LastError.ERROR_FILE_NOT_FOUND, "The system cannot find the file specified");
        var0.put(LastError.ERROR_PATH_NOT_FOUND, "The system cannot find the path specified");
        var0.put(LastError.ERROR_TOO_MANY_OPEN_FILES, "The system cannot open the file");
        var0.put(LastError.ERROR_ACCESS_DENIED, "Access is denied");
        var0.put(LastError.ERROR_INVALID_HANDLE, "The handle is invalid");
        var0.put(LastError.ERROR_ARENA_TRASHED, "The storage control blocks were destroyed");
        var0.put(LastError.ERROR_NOT_ENOUGH_MEMORY, "Not enough memory resources are available to process this command");
        var0.put(LastError.ERROR_INVALID_BLOCK, "The storage control block address is invalid");
        var0.put(LastError.ERROR_BAD_ENVIRONMENT, "The environment is incorrect");
        var0.put(LastError.ERROR_BAD_FORMAT, "An attempt was made to load a program with an incorrect format");
        var0.put(LastError.ERROR_INVALID_ACCESS, "The access code is invalid");
        var0.put(LastError.ERROR_INVALID_DATA, "The data is invalid");
        var0.put(LastError.ERROR_INVALID_DRIVE, "The system cannot find the drive specified");
        var0.put(LastError.ERROR_CURRENT_DIRECTORY, "The directory cannot be removed");
        var0.put(LastError.ERROR_NOT_SAME_DEVICE, "The system cannot move the file to a different disk drive");
        var0.put(LastError.ERROR_NO_MORE_FILES, "There are no more files");
        var0.put(LastError.ERROR_WRITE_PROTECT, "The media is write protected");
        var0.put(LastError.ERROR_BAD_UNIT, "The system cannot find the device specified");
        var0.put(LastError.ERROR_NOT_READY, "The device is not ready");
        var0.put(LastError.ERROR_BAD_COMMAND, "The device does not recognize the command");
        var0.put(LastError.ERROR_CRC, "Data error (cyclic redundancy check)");
        var0.put(LastError.ERROR_BAD_LENGTH, "The program issued a command but the command length is incorrect");
        var0.put(LastError.ERROR_SEEK, "The drive cannot locate a specific area or track on the disk");
        var0.put(LastError.ERROR_NOT_DOS_DISK, "The specified disk or diskette cannot be accessed");
        var0.put(LastError.ERROR_SECTOR_NOT_FOUND, "The drive cannot find the sector requested");
        var0.put(LastError.ERROR_OUT_OF_PAPER, "The printer is out of paper");
        var0.put(LastError.ERROR_WRITE_FAULT, "The system cannot write to the specified device");
        var0.put(LastError.ERROR_READ_FAULT, "The system cannot read from the specified device");
        var0.put(LastError.ERROR_GEN_FAILURE, "A device attached to the system is not functioning");
        var0.put(LastError.ERROR_LOCK_VIOLATION, "The process cannot access the file because another process has locked a portion of the file");
        var0.put(LastError.ERROR_SHARING_VIOLATION, "The process cannot access the file because it is being used by another process");
        var0.put(LastError.ERROR_WRONG_DISK, "ERROR_WRONG_DISK");
        var0.put(LastError.ERROR_SHARING_BUFFER_EXCEEDED, "Too many files opened for sharing");
        var0.put(LastError.ERROR_BAD_NETPATH, "The network path was not found");
        var0.put(LastError.ERROR_NETWORK_ACCESS_DENIED, "Network access is denied");
        var0.put(LastError.ERROR_BAD_NET_NAME, "The network name cannot be found");
        var0.put(LastError.ERROR_FILE_EXISTS, "The file exists");
        var0.put(LastError.ERROR_CANNOT_MAKE, "The directory or file cannot be created");
        var0.put(LastError.ERROR_FAIL_I24, "Fail on INT 24");
        var0.put(LastError.ERROR_INVALID_PARAMETER, "The parameter is incorrect");
        var0.put(LastError.ERROR_NO_PROC_SLOTS, "The system cannot start another process at this time");
        var0.put(LastError.ERROR_DRIVE_LOCKED, "The disk is in use or locked by another process");
        var0.put(LastError.ERROR_BROKEN_PIPE, "The pipe has been ended");
        var0.put(LastError.ERROR_DISK_FULL, "There is not enough space on the disk");
        var0.put(LastError.ERROR_INVALID_TARGET_HANDLE, "The target internal file identifier is incorrect");
        var0.put(LastError.ERROR_WAIT_NO_CHILDREN, "There are no child processes to wait for");
        var0.put(LastError.ERROR_CHILD_NOT_COMPLETE, "ERROR_CHILD_NOT_COMPLETE");
        var0.put(LastError.ERROR_DIRECT_ACCESS_HANDLE, "Attempt to use a file handle to an open disk partition for an operation other than raw disk I/O");
        var0.put(LastError.ERROR_NEGATIVE_SEEK, "An attempt was made to move the file pointer before the beginning of the file");
        var0.put(LastError.ERROR_SEEK_ON_DEVICE, "The file pointer cannot be set on the specified device or file");
        var0.put(LastError.ERROR_DIR_NOT_EMPTY, "The directory is not empty");
        var0.put(LastError.ERROR_DIRECTORY, "The directory name is invalid");
        var0.put(LastError.ERROR_NOT_LOCKED, "The segment is already unlocked");
        var0.put(LastError.ERROR_BAD_PATHNAME, "The specified path is invalid");
        var0.put(LastError.ERROR_MAX_THRDS_REACHED, "No more threads can be created in the system");
        var0.put(LastError.ERROR_LOCK_FAILED, "Unable to lock a region of a file");
        var0.put(LastError.ERROR_ALREADY_EXISTS, "Cannot create a file when that file already exists");
        var0.put(LastError.ERROR_INVALID_STARTING_CODESEG, "ERROR_INVALID_STARTING_CODESEG");
        var0.put(LastError.ERROR_INVALID_STACKSEG, "ERROR_INVALID_STACKSEG");
        var0.put(LastError.ERROR_INVALID_MODULETYPE, "ERROR_INVALID_MODULETYPE");
        var0.put(LastError.ERROR_INVALID_EXE_SIGNATURE, "ERROR_INVALID_EXE_SIGNATURE");
        var0.put(LastError.ERROR_EXE_MARKED_INVALID, "ERROR_EXE_MARKED_INVALID");
        var0.put(LastError.ERROR_BAD_EXE_FORMAT, "ERROR_BAD_EXE_FORMAT");
        var0.put(LastError.ERROR_ITERATED_DATA_EXCEEDS_64k, "ERROR_ITERATED_DATA_EXCEEDS_64k");
        var0.put(LastError.ERROR_INVALID_MINALLOCSIZE, "ERROR_INVALID_MINALLOCSIZE");
        var0.put(LastError.ERROR_DYNLINK_FROM_INVALID_RING, "The operating system cannot run this application program");
        var0.put(LastError.ERROR_IOPL_NOT_ENABLED, "The operating system is not presently configured to run this application");
        var0.put(LastError.ERROR_INVALID_SEGDPL, "ERROR_INVALID_SEGDPL");
        var0.put(LastError.ERROR_AUTODATASEG_EXCEEDS_64k, "The operating system cannot run this application program");
        var0.put(LastError.ERROR_RING2SEG_MUST_BE_MOVABLE, "The code segment cannot be greater than or equal to 64K");
        var0.put(LastError.ERROR_RELOC_CHAIN_XEEDS_SEGLIM, "ERROR_RELOC_CHAIN_XEEDS_SEGLIM");
        var0.put(LastError.ERROR_INFLOOP_IN_RELOC_CHAIN, "ERROR_INFLOOP_IN_RELOC_CHAIN");
        var0.put(LastError.ERROR_FILENAME_EXCED_RANGE, "The filename or extension is too long");
        var0.put(LastError.ERROR_NESTING_NOT_ALLOWED, "Cannot nest calls to LoadModule");
        var0.put(LastError.ERROR_PIPE_LOCAL, "The pipe is local");
        var0.put(LastError.ERROR_BAD_PIPE, "The pipe state is invalid");
        var0.put(LastError.ERROR_PIPE_BUSY, "All pipe instances are busy");
        var0.put(LastError.ERROR_NO_DATA, "The pipe is being closed");
        var0.put(LastError.ERROR_PIPE_NOT_CONNECTED, "No process is on the other end of the pipe");
        var0.put(LastError.ERROR_OPERATION_ABORTED, "The I/O operation has been aborted because of either a thread exit or an application request");
        var0.put(LastError.ERROR_NOT_ENOUGH_QUOTA, "Not enough quota is available to process this command");
        var0.put(LastError.ERROR_MOD_NOT_FOUND, "The specified module could not be found");
        var0.put(LastError.WSAEINTR, "A blocking operation was interrupted by a call to WSACancelBlockingCall");
        var0.put(LastError.WSAEBADF, "The file handle supplied is not valid");
        var0.put(LastError.WSAEACCES, "An attempt was made to access a socket in a way forbidden by its access permissions");
        var0.put(LastError.WSAEFAULT, "The system detected an invalid pointer address in attempting to use a pointer argument in a call");
        var0.put(LastError.WSAEINVAL, "An invalid argument was supplied");
        var0.put(LastError.WSAEMFILE, "Too many open sockets");
        var0.put(LastError.WSAEWOULDBLOCK, "A non-blocking socket operation could not be completed immediately");
        var0.put(LastError.WSAEINPROGRESS, "A blocking operation is currently executing");
        var0.put(LastError.WSAEALREADY, "An operation was attempted on a non-blocking socket that already had an operation in progress");
        var0.put(LastError.WSAENOTSOCK, "An operation was attempted on something that is not a socket");
        var0.put(LastError.WSAEDESTADDRREQ, "A required address was omitted from an operation on a socket");
        var0.put(LastError.WSAEMSGSIZE, "A message sent on a datagram socket was larger than the internal message buffer or some other network limit, or the buffer used to receive a datagram into was smaller than the datagram itself");
        var0.put(LastError.WSAEPROTOTYPE, "A protocol was specified in the socket function call that does not support the semantics of the socket type requested");
        var0.put(LastError.WSAENOPROTOOPT, "An unknown, invalid, or unsupported option or level was specified in a getsockopt or setsockopt call");
        var0.put(LastError.WSAEPROTONOSUPPORT, "The requested protocol has not been configured into the system, or no implementation for it exists");
        var0.put(LastError.WSAESOCKTNOSUPPORT, "The support for the specified socket type does not exist in this address family");
        var0.put(LastError.WSAEOPNOTSUPP, "The attempted operation is not supported for the type of object referenced");
        var0.put(LastError.WSAEPFNOSUPPORT, "The protocol family has not been configured into the system or no implementation for it exists");
        var0.put(LastError.WSAEAFNOSUPPORT, "An address incompatible with the requested protocol was used");
        var0.put(LastError.WSAEADDRINUSE, "Only one usage of each socket address (protocol/network address/port) is normally permitted");
        var0.put(LastError.WSAEADDRNOTAVAIL, "The requested address is not valid in its context");
        var0.put(LastError.WSAENETDOWN, "A socket operation encountered a dead network");
        var0.put(LastError.WSAENETUNREACH, "A socket operation was attempted to an unreachable network");
        var0.put(LastError.WSAENETRESET, "The connection has been broken due to keep-alive activity detecting a failure while the operation was in progress");
        var0.put(LastError.WSAECONNABORTED, "An established connection was aborted by the software in your host machine");
        var0.put(LastError.WSAECONNRESET, "An existing connection was forcibly closed by the remote host");
        var0.put(LastError.WSAENOBUFS, "An operation on a socket could not be performed because the system lacked sufficient buffer space or because a queue was full");
        var0.put(LastError.WSAEISCONN, "A connect request was made on an already connected socket");
        var0.put(LastError.WSAENOTCONN, "A request to send or receive data was disallowed because the socket is not connected and (when sending on a datagram socket using a sendto call) no address was supplied");
        var0.put(LastError.WSAESHUTDOWN, "A request to send or receive data was disallowed because the socket had already been shut down in that direction with a previous shutdown call");
        var0.put(LastError.WSAETOOMANYREFS, "Too many references to some kernel object");
        var0.put(LastError.WSAETIMEDOUT, "A connection attempt failed because the connected party did not properly respond after a period of time, or established connection failed because connected host has failed to respond");
        var0.put(LastError.WSAECONNREFUSED, "No connection could be made because the target machine actively refused it");
        var0.put(LastError.WSAELOOP, "Cannot translate name");
        var0.put(LastError.WSAENAMETOOLONG, "Name component or name was too long");
        var0.put(LastError.WSAEHOSTDOWN, "A socket operation failed because the destination host was down");
        var0.put(LastError.WSAEHOSTUNREACH, "A socket operation was attempted to an unreachable host");
        var0.put(LastError.WSAENOTEMPTY, "Cannot remove a directory that is not empty");
        var0.put(LastError.WSAEPROCLIM, "A Windows Sockets implementation may have a limit on the number of applications that may use it simultaneously");
        var0.put(LastError.WSAEUSERS, "Ran out of quota");
        var0.put(LastError.WSAEDQUOT, "Ran out of disk quota");
        var0.put(LastError.WSAESTALE, "File handle reference is no longer available");
        var0.put(LastError.WSAEREMOTE, "Item is not available locally");
        var0.put(LastError.WSASYSNOTREADY, "WSAStartup cannot function at this time because the underlying system it uses to provide network services is currently unavailable");
        var0.put(LastError.WSAVERNOTSUPPORTED, "The Windows Sockets version requested is not supported");
        var0.put(LastError.WSANOTINITIALISED, "Either the application has not called WSAStartup, or WSAStartup failed");
        var0.put(LastError.WSAEDISCON, "Returned by WSARecv or WSARecvFrom to indicate the remote party has initiated a graceful shutdown sequence");
        var0.put(LastError.WSAENOMORE, "No more results can be returned by WSALookupServiceNext");
        var0.put(LastError.WSAECANCELLED, "A call to WSALookupServiceEnd was made while this call was still processing. The call has been canceled");
        var0.put(LastError.WSAEINVALIDPROCTABLE, "The procedure call table is invalid");
        var0.put(LastError.WSAEINVALIDPROVIDER, "The requested service provider is invalid");
        var0.put(LastError.WSAEPROVIDERFAILEDINIT, "The requested service provider could not be loaded or initialized");
        var0.put(LastError.WSASYSCALLFAILURE, "A system call has failed");
        var0.put(LastError.WSASERVICE_NOT_FOUND, "No such service is known. The service cannot be found in the specified name space");
        var0.put(LastError.WSATYPE_NOT_FOUND, "The specified class was not found");
        var0.put(LastError.WSA_E_NO_MORE, "No more results can be returned by WSALookupServiceNext");
        var0.put(LastError.WSA_E_CANCELLED, "A call to WSALookupServiceEnd was made while this call was still processing. The call has been canceled");
        var0.put(LastError.WSAEREFUSED, "A database query failed because it was actively refused");
        var0.put(LastError.WSAHOST_NOT_FOUND, "No such host is known");
        var0.put(LastError.WSATRY_AGAIN, "This is usually a temporary error during hostname resolution and means that the local server did not receive a response from an authoritative server");
        var0.put(LastError.WSANO_RECOVERY, "A non-recoverable error occurred during a database lookup");
        var0.put(LastError.WSANO_DATA, "The requested name is valid, but no data of the requested type was found");
        return var0;
    }

}