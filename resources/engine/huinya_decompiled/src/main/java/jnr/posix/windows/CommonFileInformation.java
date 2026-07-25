// исходный (обфусцированный) внутренний класс: jnr.posix.windows.CommonFileInformation
package jnr.posix.windows;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.posix.windows.CommonFileInformation_HackyFileTime;

public abstract class CommonFileInformation extends Struct {

    // ---- поля ----
  public static int FILE_ATTRIBUTE_READONLY;
  public static int FILE_ATTRIBUTE_DIRECTORY;
  public static final int NANOSECONDS = 1000000000;
  private static final double DAYS_BETWEEN_WINDOWS_AND_UNIX = 134774.4825;
  private static final long NANOSECONDS_TO_UNIX_EPOCH_FROM_WINDOWS = -6802270473709551616L;

    static {
        FILE_ATTRIBUTE_READONLY = 1;
        FILE_ATTRIBUTE_DIRECTORY = 16;
    }

  protected CommonFileInformation(Runtime arg0) { // было: <init>
        super(arg0);
    }

  public abstract int getFileAttributes();

  public abstract CommonFileInformation_HackyFileTime getCreationTime();

  public abstract CommonFileInformation_HackyFileTime getLastAccessTime();

  public abstract CommonFileInformation_HackyFileTime getLastWriteTime();

  public abstract long getFileSizeHigh();

  public abstract long getFileSizeLow();

  public int getMode(String arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #30 // jnr.posix.windows.CommonFileInformation.getFileAttributes:()I
        //      4: istore_2
        //      5: sipush  256
        //      8: istore_3
        //      9: iload_2
        //     10: getstatic  #24 // jnr.posix.windows.CommonFileInformation.FILE_ATTRIBUTE_READONLY:I
        //     13: iand
        //     14: ifne  23 (offset +9)
        //     17: iload_3
        //     18: sipush  128
        //     21: ior
        //     22: istore_3
        //     23: iload_3
        //     24: iload_2
        //     25: getstatic  #23 // jnr.posix.windows.CommonFileInformation.FILE_ATTRIBUTE_DIRECTORY:I
        //     28: iand
        //     29: ifeq  38 (offset +9)
        //     32: sipush  16448
        //     35: goto  40 (offset +5)
        //     38: ldc  #1 // 32768
        //     40: ior
        //     41: istore_3
        //     42: aload_1
        //     43: invokevirtual  #26 // java.lang.String.toLowerCase:()Ljava/lang/String;
        //     46: astore_1
        //     47: aload_1
        //     48: ifnull  99 (offset +51)
        //     51: iload_3
        //     52: ldc  #1 // 32768
        //     54: iand
        //     55: ifeq  99 (offset +44)
        //     58: aload_1
        //     59: ldc  #3 // '.bat'
        //     61: invokevirtual  #25 // java.lang.String.endsWith:(Ljava/lang/String;)Z
        //     64: ifne  94 (offset +30)
        //     67: aload_1
        //     68: ldc  #4 // '.cmd'
        //     70: invokevirtual  #25 // java.lang.String.endsWith:(Ljava/lang/String;)Z
        //     73: ifne  94 (offset +21)
        //     76: aload_1
        //     77: ldc  #5 // '.com'
        //     79: invokevirtual  #25 // java.lang.String.endsWith:(Ljava/lang/String;)Z
        //     82: ifne  94 (offset +12)
        //     85: aload_1
        //     86: ldc  #6 // '.exe'
        //     88: invokevirtual  #25 // java.lang.String.endsWith:(Ljava/lang/String;)Z
        //     91: ifeq  99 (offset +8)
        //     94: iload_3
        //     95: bipush  64
        //     97: ior
        //     98: istore_3
        //     99: iload_3
        //    100: iload_3
        //    101: sipush  448
        //    104: iand
        //    105: iconst_3
        //    106: ishr
        //    107: ior
        //    108: istore_3
        //    109: iload_3
        //    110: iload_3
        //    111: sipush  448
        //    114: iand
        //    115: bipush  6
        //    117: ishr
        //    118: ior
        //    119: istore_3
        //    120: iload_3
        //    121: ireturn
    }

  public long getLastWriteTimeNanoseconds() {
        return epochNanos(getLastWriteTime().getLongValue());
    }

  public long getLastAccessTimeNanoseconds() {
        return epochNanos(getLastAccessTime().getLongValue());
    }

  public long getCreationTimeNanoseconds() {
        return epochNanos(getCreationTime().getLongValue());
    }

  public long getFileSize() {
        return getFileSizeHigh() << 32 | getFileSizeLow();
    }

  private long epochNanos(long arg0) {
        return arg0 * 100L - -6802270473709551616L;
    }

  public static long asNanoSeconds(long arg0) {
        return (arg0 * 1000L + -6802270473709551L) * 10L;
    }

}