// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Foreign
package com.kenai.jffi;

import com.kenai.jffi.Foreign_Anon1;
import com.kenai.jffi.Foreign_InstanceHolder;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.Buffer;
import java.nio.ByteBuffer;

final class Foreign {

    // ---- поля ----
  public static final int VERSION_MAJOR;
  public static final int VERSION_MINOR;
  public static final int VERSION_MICRO;
  public static final int TYPE_VOID = 0;
  public static final int TYPE_FLOAT = 2;
  public static final int TYPE_DOUBLE = 3;
  public static final int TYPE_LONGDOUBLE = 4;
  public static final int TYPE_UINT8 = 5;
  public static final int TYPE_SINT8 = 6;
  public static final int TYPE_UINT16 = 7;
  public static final int TYPE_SINT16 = 8;
  public static final int TYPE_UINT32 = 9;
  public static final int TYPE_SINT32 = 10;
  public static final int TYPE_UINT64 = 11;
  public static final int TYPE_SINT64 = 12;
  public static final int TYPE_STRUCT = 13;
  public static final int TYPE_POINTER = 14;
  public static final int TYPE_UCHAR = 101;
  public static final int TYPE_SCHAR = 102;
  public static final int TYPE_USHORT = 103;
  public static final int TYPE_SSHORT = 104;
  public static final int TYPE_UINT = 105;
  public static final int TYPE_SINT = 106;
  public static final int TYPE_ULONG = 107;
  public static final int TYPE_SLONG = 108;
  public static final int RTLD_LAZY = 1;
  public static final int RTLD_NOW = 2;
  public static final int RTLD_LOCAL = 4;
  public static final int RTLD_GLOBAL = 8;
  public static final int PROT_READ = 1;
  public static final int PROT_WRITE = 2;
  public static final int PROT_EXEC = 4;
  public static final int PROT_NONE = 0;
  public static final int MAP_SHARED = 1;
  public static final int MAP_PRIVATE = 2;
  public static final int MAP_FIXED = 16;
  public static final int MAP_NORESERVE = 64;
  public static final int MAP_ANON = 256;
  public static final int MAP_ALIGN = 512;
  public static final int MAP_TEXT = 1024;
  public static final int PAGE_NOACCESS = 1;
  public static final int PAGE_READONLY = 2;
  public static final int PAGE_READWRITE = 4;
  public static final int PAGE_WRITECOPY = 8;
  public static final int PAGE_EXECUTE = 16;
  public static final int PAGE_EXECUTE_READ = 32;
  public static final int PAGE_EXECUTE_READWRITE = 64;
  public static final int PAGE_EXECUTE_WRITECOPY = 128;
  public static final int MEM_COMMIT = 4096;
  public static final int MEM_RESERVE = 8192;
  public static final int MEM_DECOMMIT = 16384;
  public static final int MEM_RELEASE = 32768;
  public static final int MEM_FREE = 65536;
  public static final int MEM_PRIVATE = 131072;
  public static final int MEM_MAPPED = 262144;
  public static final int MEM_RESET = 524288;
  public static final int MEM_TOP_DOWN = 1048576;
  public static final int MEM_PHYSICAL = 4194304;
  public static final int MEM_4MB_PAGES = -2147483648;
  public static final int JNI_OK = 0;
  public static final int JNI_ERR = -1;
  public static final int JNI_EDETACHED = -2;
  public static final int JNI_EVERSION = -3;
  public static final int JNI_ENOMEM = -4;
  public static final int JNI_EEXIST = -5;
  public static final int JNI_EINVAL = -6;
  public static final int F_DEFAULT = 0;
  public static final int F_STDCALL = 1;
  public static final int F_NOERRNO = 2;
  public static final int F_PROTECT = 4;

    static {
        VERSION_MAJOR = getVersionField("MAJOR");
        VERSION_MINOR = getVersionField("MINOR");
        VERSION_MICRO = getVersionField("MICRO");
    }

  private static UnsatisfiedLinkError newLoadError(Throwable arg0) {
        UnsatisfiedLinkError var1 = new UnsatisfiedLinkError(arg0.getMessage());
        var1.initCause(arg0);
        return var1;
    }

  public static Foreign getInstance() {
        return Foreign_InstanceHolder.INSTANCE.getForeign();
    }

  private Foreign() { // было: <init>
        super();
    }

  private static int getVersionField(String arg0) {
        int __stk1;
        try {
            Class var1 = Class.forName(new StringBuilder().append(Foreign.class.getPackage().getName()).append(".Version").toString());
            __stk1 = (((Integer) var1.getField(arg0).get(var1))).intValue();
        } catch (Throwable e1) {
            Throwable var1 = e1;
            throw new RuntimeException(var1);
        }
    }

  final int getVersion();

  private void init();

  private static boolean isFaultProtectionEnabled();

  static boolean isMemoryProtectionEnabled() {
        boolean __stk1;
        try {
            __stk1 = isFaultProtectionEnabled();
        } catch (UnsatisfiedLinkError var0) {
            return false;
        }
    }

  static long dlopen(String arg0, int arg1);

  static void dlclose(long arg0);

  static long dlsym(long arg0, String arg1);

  static String dlerror();

  static long allocateMemory(long arg0, boolean arg1);

  static void freeMemory(long arg0);

  static long pageSize();

  static long mmap(long arg0, long arg1, int arg2, int arg3, int arg4, long arg5);

  static int munmap(long arg0, long arg1);

  static int mprotect(long arg0, long arg1, int arg2);

  static long VirtualAlloc(long arg0, int arg1, int arg2, int arg3);

  static boolean VirtualFree(long arg0, int arg1, int arg2);

  static boolean VirtualProtect(long arg0, int arg1, int arg2);

  final long newCallContext(long arg0, long[] arg1, int arg2);

  final void freeCallContext(long arg0);

  final int getCallContextRawParameterSize(long arg0);

  final boolean isRawParameterPackingEnabled();

  static int getLastError();

  static void setLastError(int arg0);

  final long newClosureMagazine(long arg0, Method arg1, boolean arg2);

  final void freeClosureMagazine(long arg0);

  final long closureMagazineGet(long arg0, Object arg1);

  final long lookupBuiltinType(int arg0);

  final int getTypeSize(long arg0);

  final int getTypeAlign(long arg0);

  final int getTypeType(long arg0);

  final long newStruct(long[] arg0, boolean arg1);

  final long newArray(long arg0, int arg1);

  final void freeAggregate(long arg0);

  static int invokeI0(long arg0, long arg1);

  static int invokeI0NoErrno(long arg0, long arg1);

  static int invokeI1(long arg0, long arg1, int arg2);

  static int invokeI1NoErrno(long arg0, long arg1, int arg2);

  static int invokeI2(long arg0, long arg1, int arg2, int arg3);

  static int invokeI2NoErrno(long arg0, long arg1, int arg2, int arg3);

  static int invokeI3(long arg0, long arg1, int arg2, int arg3, int arg4);

  static int invokeI4(long arg0, long arg1, int arg2, int arg3, int arg4, int arg5);

  static int invokeI5(long arg0, long arg1, int arg2, int arg3, int arg4, int arg5, int arg6);

  static int invokeI6(long arg0, long arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7);

  static int invokeI3NoErrno(long arg0, long arg1, int arg2, int arg3, int arg4);

  static int invokeI4NoErrno(long arg0, long arg1, int arg2, int arg3, int arg4, int arg5);

  static int invokeI5NoErrno(long arg0, long arg1, int arg2, int arg3, int arg4, int arg5, int arg6);

  static int invokeI6NoErrno(long arg0, long arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7);

  static long invokeL0(long arg0, long arg1);

  static long invokeL1(long arg0, long arg1, long arg2);

  static long invokeL2(long arg0, long arg1, long arg2, long arg3);

  static long invokeL3(long arg0, long arg1, long arg2, long arg3, long arg4);

  static long invokeL4(long arg0, long arg1, long arg2, long arg3, long arg4, long arg5);

  static long invokeL5(long arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6);

  static long invokeL6(long arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7);

  static long invokeL0NoErrno(long arg0, long arg1);

  static long invokeL1NoErrno(long arg0, long arg1, long arg2);

  static long invokeL2NoErrno(long arg0, long arg1, long arg2, long arg3);

  static long invokeL3NoErrno(long arg0, long arg1, long arg2, long arg3, long arg4);

  static long invokeL4NoErrno(long arg0, long arg1, long arg2, long arg3, long arg4, long arg5);

  static long invokeL5NoErrno(long arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6);

  static long invokeL6NoErrno(long arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7);

  static long invokeN0(long arg0, long arg1);

  static long invokeN1(long arg0, long arg1, long arg2);

  static long invokeN2(long arg0, long arg1, long arg2, long arg3);

  static long invokeN3(long arg0, long arg1, long arg2, long arg3, long arg4);

  static long invokeN4(long arg0, long arg1, long arg2, long arg3, long arg4, long arg5);

  static long invokeN5(long arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6);

  static long invokeN6(long arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7);

  static long invokeN1O1(long arg0, long arg1, long arg2, Object arg3, int arg4, int arg5, int arg6);

  static long invokeN2O1(long arg0, long arg1, long arg2, long arg3, Object arg4, int arg5, int arg6, int arg7);

  static long invokeN2O2(long arg0, long arg1, long arg2, long arg3, Object arg4, int arg5, int arg6, int arg7, Object arg8, int arg9, int arg10, int arg11);

  static long invokeN3O1(long arg0, long arg1, long arg2, long arg3, long arg4, Object arg5, int arg6, int arg7, int arg8);

  static long invokeN3O2(long arg0, long arg1, long arg2, long arg3, long arg4, Object arg5, int arg6, int arg7, int arg8, Object arg9, int arg10, int arg11, int arg12);

  static long invokeN3O3(long arg0, long arg1, long arg2, long arg3, long arg4, Object arg5, int arg6, int arg7, int arg8, Object arg9, int arg10, int arg11, int arg12, Object arg13, int arg14, int arg15, int arg16);

  static long invokeN4O1(long arg0, long arg1, long arg2, long arg3, long arg4, long arg5, Object arg6, int arg7, int arg8, int arg9);

  static long invokeN4O2(long arg0, long arg1, long arg2, long arg3, long arg4, long arg5, Object arg6, int arg7, int arg8, int arg9, Object arg10, int arg11, int arg12, int arg13);

  static long invokeN4O3(long arg0, long arg1, long arg2, long arg3, long arg4, long arg5, Object arg6, int arg7, int arg8, int arg9, Object arg10, int arg11, int arg12, int arg13, Object arg14, int arg15, int arg16, int arg17);

  static long invokeN4O4(long arg0, long arg1, long arg2, long arg3, long arg4, long arg5, Object arg6, int arg7, int arg8, int arg9, Object arg10, int arg11, int arg12, int arg13, Object arg14, int arg15, int arg16, int arg17, Object arg18, int arg19, int arg20, int arg21);

  static long invokeN5O1(long arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, Object arg7, int arg8, int arg9, int arg10);

  static long invokeN5O2(long arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, Object arg7, int arg8, int arg9, int arg10, Object arg11, int arg12, int arg13, int arg14);

  static long invokeN5O3(long arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, Object arg7, int arg8, int arg9, int arg10, Object arg11, int arg12, int arg13, int arg14, Object arg15, int arg16, int arg17, int arg18);

  static long invokeN5O4(long arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, Object arg7, int arg8, int arg9, int arg10, Object arg11, int arg12, int arg13, int arg14, Object arg15, int arg16, int arg17, int arg18, Object arg19, int arg20, int arg21, int arg22);

  static long invokeN5O5(long arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, Object arg7, int arg8, int arg9, int arg10, Object arg11, int arg12, int arg13, int arg14, Object arg15, int arg16, int arg17, int arg18, Object arg19, int arg20, int arg21, int arg22, Object arg23, int arg24, int arg25, int arg26);

  static long invokeN6O1(long arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7, Object arg8, int arg9, int arg10, int arg11);

  static long invokeN6O2(long arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7, Object arg8, int arg9, int arg10, int arg11, Object arg12, int arg13, int arg14, int arg15);

  static long invokeN6O3(long arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7, Object arg8, int arg9, int arg10, int arg11, Object arg12, int arg13, int arg14, int arg15, Object arg16, int arg17, int arg18, int arg19);

  static long invokeN6O4(long arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7, Object arg8, int arg9, int arg10, int arg11, Object arg12, int arg13, int arg14, int arg15, Object arg16, int arg17, int arg18, int arg19, Object arg20, int arg21, int arg22, int arg23);

  static long invokeN6O5(long arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7, Object arg8, int arg9, int arg10, int arg11, Object arg12, int arg13, int arg14, int arg15, Object arg16, int arg17, int arg18, int arg19, Object arg20, int arg21, int arg22, int arg23, Object arg24, int arg25, int arg26, int arg27);

  static long invokeN6O6(long arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7, Object arg8, int arg9, int arg10, int arg11, Object arg12, int arg13, int arg14, int arg15, Object arg16, int arg17, int arg18, int arg19, Object arg20, int arg21, int arg22, int arg23, Object arg24, int arg25, int arg26, int arg27, Object arg28, int arg29, int arg30, int arg31);

  static int invokeArrayReturnInt(long arg0, long arg1, byte[] arg2);

  static long invokeArrayReturnLong(long arg0, long arg1, byte[] arg2);

  static float invokeArrayReturnFloat(long arg0, long arg1, byte[] arg2);

  static double invokeArrayReturnDouble(long arg0, long arg1, byte[] arg2);

  static void invokeArrayReturnStruct(long arg0, long arg1, byte[] arg2, byte[] arg3, int arg4);

  static Object invokeArrayWithObjectsReturnObject(long arg0, long arg1, byte[] arg2, int arg3, int[] arg4, Object[] arg5);

  static int invokeArrayWithObjectsInt32(long arg0, long arg1, byte[] arg2, int arg3, int[] arg4, Object[] arg5);

  static long invokeArrayWithObjectsInt64(long arg0, long arg1, byte[] arg2, int arg3, int[] arg4, Object[] arg5);

  static float invokeArrayWithObjectsFloat(long arg0, long arg1, byte[] arg2, int arg3, int[] arg4, Object[] arg5);

  static double invokeArrayWithObjectsDouble(long arg0, long arg1, byte[] arg2, int arg3, int[] arg4, Object[] arg5);

  static void invokeArrayWithObjectsReturnStruct(long arg0, long arg1, byte[] arg2, int arg3, int[] arg4, Object[] arg5, byte[] arg6, int arg7);

  static int invokeArrayO1Int32(long arg0, long arg1, byte[] arg2, Object arg3, int arg4, int arg5, int arg6);

  static int invokeArrayO2Int32(long arg0, long arg1, byte[] arg2, Object arg3, int arg4, int arg5, int arg6, Object arg7, int arg8, int arg9, int arg10);

  static long invokeArrayO1Int64(long arg0, long arg1, byte[] arg2, Object arg3, int arg4, int arg5, int arg6);

  static long invokeArrayO2Int64(long arg0, long arg1, byte[] arg2, Object arg3, int arg4, int arg5, int arg6, Object arg7, int arg8, int arg9, int arg10);

  static void invokePointerParameterArray(long arg0, long arg1, long arg2, long[] arg3);

  static byte getByte(long arg0);

  static short getShort(long arg0);

  static int getInt(long arg0);

  static long getLong(long arg0);

  static float getFloat(long arg0);

  static double getDouble(long arg0);

  static long getAddress(long arg0);

  static void putByte(long arg0, byte arg1);

  static void putShort(long arg0, short arg1);

  static void putInt(long arg0, int arg1);

  static void putLong(long arg0, long arg1);

  static void putFloat(long arg0, float arg1);

  static void putDouble(long arg0, double arg1);

  static void putAddress(long arg0, long arg1);

  static void setMemory(long arg0, long arg1, byte arg2);

  static void copyMemory(long arg0, long arg1, long arg2);

  static void putByteArray(long arg0, byte[] arg1, int arg2, int arg3);

  static void getByteArray(long arg0, byte[] arg1, int arg2, int arg3);

  static void putCharArray(long arg0, char[] arg1, int arg2, int arg3);

  static void getCharArray(long arg0, char[] arg1, int arg2, int arg3);

  static void putShortArray(long arg0, short[] arg1, int arg2, int arg3);

  static void getShortArray(long arg0, short[] arg1, int arg2, int arg3);

  static void putIntArray(long arg0, int[] arg1, int arg2, int arg3);

  static void getIntArray(long arg0, int[] arg1, int arg2, int arg3);

  static void putLongArray(long arg0, long[] arg1, int arg2, int arg3);

  static void getLongArray(long arg0, long[] arg1, int arg2, int arg3);

  static void putFloatArray(long arg0, float[] arg1, int arg2, int arg3);

  static void getFloatArray(long arg0, float[] arg1, int arg2, int arg3);

  static void putDoubleArray(long arg0, double[] arg1, int arg2, int arg3);

  static void getDoubleArray(long arg0, double[] arg1, int arg2, int arg3);

  static long memchr(long arg0, int arg1, long arg2);

  static void memmove(long arg0, long arg1, long arg2);

  static void memcpy(long arg0, long arg1, long arg2);

  static long strlen(long arg0);

  static byte[] getZeroTerminatedByteArray(long arg0);

  static byte[] getZeroTerminatedByteArray(long arg0, int arg1);

  static void putZeroTerminatedByteArray(long arg0, byte[] arg1, int arg2, int arg3);

  static byte getByteChecked(long arg0);

  static short getShortChecked(long arg0);

  static int getIntChecked(long arg0);

  static long getLongChecked(long arg0);

  static float getFloatChecked(long arg0);

  static double getDoubleChecked(long arg0);

  static long getAddressChecked(long arg0);

  static void putByteChecked(long arg0, byte arg1);

  static void putShortChecked(long arg0, short arg1);

  static void putIntChecked(long arg0, int arg1);

  static void putLongChecked(long arg0, long arg1);

  static void putFloatChecked(long arg0, float arg1);

  static void putDoubleChecked(long arg0, double arg1);

  static void putAddressChecked(long arg0, long arg1);

  static void setMemoryChecked(long arg0, long arg1, byte arg2);

  static void copyMemoryChecked(long arg0, long arg1, long arg2);

  static void putByteArrayChecked(long arg0, byte[] arg1, int arg2, int arg3);

  static void getByteArrayChecked(long arg0, byte[] arg1, int arg2, int arg3);

  static void putCharArrayChecked(long arg0, char[] arg1, int arg2, int arg3);

  static void getCharArrayChecked(long arg0, char[] arg1, int arg2, int arg3);

  static void putShortArrayChecked(long arg0, short[] arg1, int arg2, int arg3);

  static void getShortArrayChecked(long arg0, short[] arg1, int arg2, int arg3);

  static void putIntArrayChecked(long arg0, int[] arg1, int arg2, int arg3);

  static void getIntArrayChecked(long arg0, int[] arg1, int arg2, int arg3);

  static void putLongArrayChecked(long arg0, long[] arg1, int arg2, int arg3);

  static void getLongArrayChecked(long arg0, long[] arg1, int arg2, int arg3);

  static void putFloatArrayChecked(long arg0, float[] arg1, int arg2, int arg3);

  static void getFloatArrayChecked(long arg0, float[] arg1, int arg2, int arg3);

  static void putDoubleArrayChecked(long arg0, double[] arg1, int arg2, int arg3);

  static void getDoubleArrayChecked(long arg0, double[] arg1, int arg2, int arg3);

  static long memchrChecked(long arg0, int arg1, long arg2);

  static void memmoveChecked(long arg0, long arg1, long arg2);

  static void memcpyChecked(long arg0, long arg1, long arg2);

  static long strlenChecked(long arg0);

  static byte[] getZeroTerminatedByteArrayChecked(long arg0);

  static byte[] getZeroTerminatedByteArrayChecked(long arg0, int arg1);

  static void putZeroTerminatedByteArrayChecked(long arg0, byte[] arg1, int arg2, int arg3);

  final ByteBuffer newDirectByteBuffer(long arg0, int arg1);

  final long getDirectBufferAddress(Buffer arg0);

  final void longDoubleFromDouble(double arg0, byte[] arg1, int arg2, int arg3);

  final double longDoubleToDouble(byte[] arg0, int arg1, int arg2);

  final void longDoubleFromString(String arg0, byte[] arg1, int arg2, int arg3);

  final String longDoubleToString(byte[] arg0, int arg1, int arg2);

  final String longDoubleToEngineeringString(byte[] arg0, int arg1, int arg2);

  final String longDoubleToPlainString(byte[] arg0, int arg1, int arg2);

  final long newNativeMethod(String arg0, String arg1, long arg2);

  final void freeNativeMethod(long arg0);

  final long compileNativeMethods(long[] arg0);

  final void freeCompiledMethods(long arg0);

  final boolean registerNativeMethods(Class arg0, long arg1);

  final void unregisterNativeMethods(Class arg0);

  final long getSaveErrnoFunction();

  final void setCallContextErrorFunction(long arg0, long arg1);

  final long getSaveErrnoCtxFunction();

  final int getJNIVersion();

  final long getJavaVM();

  final void fatalError(String arg0);

  final Class defineClass(String arg0, Object arg1, byte[] arg2, int arg3, int arg4);

  final Class defineClass(String arg0, Object arg1, ByteBuffer arg2);

  final Object allocObject(Class arg0);

  final int registerNatives(Class arg0, long arg1, int arg2);

  final int unregisterNatives(Class arg0);

  final String getArch();

   Foreign(Foreign_Anon1 arg0) { // было: <init>
        this();
    }

  static void access$100(Foreign arg0) {
        arg0.init();
    }

  static UnsatisfiedLinkError access$300(Throwable arg0) {
        return newLoadError(arg0);
    }

}