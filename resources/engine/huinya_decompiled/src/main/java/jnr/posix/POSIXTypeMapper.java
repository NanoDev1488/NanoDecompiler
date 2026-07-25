// исходный (обфусцированный) внутренний класс: jnr.posix.POSIXTypeMapper
package jnr.posix;

import jnr.constants.Constant;
import jnr.ffi.Platform_OS;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.TypeMapper;
import jnr.posix.AixPOSIX;
import jnr.posix.BaseNativePOSIX;
import jnr.posix.DragonFlyPOSIX;
import jnr.posix.FileStat;
import jnr.posix.FreeBSDPOSIX;
import jnr.posix.Group;
import jnr.posix.HANDLE;
import jnr.posix.LinuxPOSIX;
import jnr.posix.MacOSPOSIX;
import jnr.posix.MsgHdr;
import jnr.posix.NativeTimes;
import jnr.posix.OpenBSDPOSIX;
import jnr.posix.Passwd;
import jnr.posix.SolarisPOSIX;
import jnr.posix.WString;
import jnr.posix.WindowsPOSIX;
import jnr.posix.util.Platform;

final class POSIXTypeMapper implements TypeMapper {

    // ---- поля ----
  public static final TypeMapper INSTANCE;

    static {
        INSTANCE = new POSIXTypeMapper();
    }

  private POSIXTypeMapper() { // было: <init>
        super();
    }

  public FromNativeConverter getFromNativeConverter(Class arg0) {
        if (!Passwd.class.isAssignableFrom(arg0)) {
            if (!Group.class.isAssignableFrom(arg0)) {
                if (!HANDLE.class.isAssignableFrom(arg0)) {
                    return null;
                } else {
                    return HANDLE.Converter;
                }
            } else {
                return BaseNativePOSIX.GROUP;
            }
        } else {
            if (!Platform.IS_MAC) {
                if (!Platform.IS_LINUX) {
                    if (!Platform.IS_SOLARIS) {
                        if (!Platform.IS_FREEBSD) {
                            if (!Platform.IS_DRAGONFLY) {
                                if (!Platform.IS_OPENBSD) {
                                    if (!Platform.IS_WINDOWS) {
                                        if (!jnr.ffi.Platform.getNativePlatform().getOS().equals(Platform_OS.AIX)) {
                                            return null;
                                        } else {
                                            return AixPOSIX.PASSWD;
                                        }
                                    } else {
                                        return WindowsPOSIX.PASSWD;
                                    }
                                } else {
                                    return OpenBSDPOSIX.PASSWD;
                                }
                            } else {
                                return DragonFlyPOSIX.PASSWD;
                            }
                        } else {
                            return FreeBSDPOSIX.PASSWD;
                        }
                    } else {
                        return SolarisPOSIX.PASSWD;
                    }
                } else {
                    return LinuxPOSIX.PASSWD;
                }
            } else {
                return MacOSPOSIX.PASSWD;
            }
        }
    }

  public ToNativeConverter getToNativeConverter(Class arg0) {
        if (!FileStat.class.isAssignableFrom(arg0)) {
            if (!NativeTimes.class.isAssignableFrom(arg0)) {
                if (!Constant.class.isAssignableFrom(arg0)) {
                    if (!WString.class.isAssignableFrom(arg0)) {
                        if (!HANDLE.class.isAssignableFrom(arg0)) {
                            if (!MsgHdr.class.isAssignableFrom(arg0)) {
                                return null;
                            } else {
                                return BaseNativePOSIX.MsgHdrConverter;
                            }
                        } else {
                            return HANDLE.Converter;
                        }
                    } else {
                        return WString.Converter;
                    }
                } else {
                    return BaseNativePOSIX.ConstantConverter;
                }
            } else {
                return BaseNativePOSIX.TimesConverter;
            }
        } else {
            return BaseNativePOSIX.FileStatConverter;
        }
    }

  public final ToNativeConverter getToNativeConverter(Class arg0, ToNativeContext arg1) {
        return getToNativeConverter(arg0);
    }

  public final FromNativeConverter getFromNativeConverter(Class arg0, FromNativeContext arg1) {
        return getFromNativeConverter(arg0);
    }

}