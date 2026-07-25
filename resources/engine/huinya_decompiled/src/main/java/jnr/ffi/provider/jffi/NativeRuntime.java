// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.NativeRuntime
package jnr.ffi.provider.jffi;

import com.kenai.jffi.LastError;
import java.lang.reflect.Field;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import jnr.ffi.NativeType;
import jnr.ffi.ObjectReferenceManager;
import jnr.ffi.Platform;
import jnr.ffi.Platform_CPU;
import jnr.ffi.Platform_OS;
import jnr.ffi.Runtime;
import jnr.ffi.Type;
import jnr.ffi.TypeAlias;
import jnr.ffi.mapper.DefaultTypeMapper;
import jnr.ffi.mapper.SignatureTypeMapperAdapter;
import jnr.ffi.provider.AbstractRuntime;
import jnr.ffi.provider.BadType;
import jnr.ffi.provider.ClosureManager;
import jnr.ffi.provider.DefaultObjectReferenceManager;
import jnr.ffi.provider.MemoryManager;
import jnr.ffi.provider.jffi.NativeClosureManager;
import jnr.ffi.provider.jffi.NativeMemoryManager;
import jnr.ffi.provider.jffi.NativeRuntime_Anon1;
import jnr.ffi.provider.jffi.NativeRuntime_SingletonHolder;
import jnr.ffi.provider.jffi.NativeRuntime_TypeDelegate;

public final class NativeRuntime extends AbstractRuntime {

    // ---- поля ----
  private final NativeMemoryManager mm;
  private final NativeClosureManager closureManager;
  private final Type[] aliases;
  final WeakHashMap loadedLibraries;

  public static NativeRuntime getInstance() {
        return NativeRuntime_SingletonHolder.INSTANCE;
    }

  public static List getLoadedLibraries() {
        if (!(getSystemRuntime() instanceof NativeRuntime)) {
            return Collections.emptyList();
        } else {
            return new ArrayList((((NativeRuntime) getSystemRuntime())).loadedLibraries.values());
        }
    }

  private NativeRuntime() { // было: <init>
        super(ByteOrder.nativeOrder(), buildTypeMap());
        mm = new NativeMemoryManager(this);
        closureManager = new NativeClosureManager(this, new SignatureTypeMapperAdapter(new DefaultTypeMapper()));
        loadedLibraries = new WeakHashMap();
        NativeType[] var1 = buildNativeTypeAliases();
        EnumSet var2 = EnumSet.allOf(TypeAlias.class);
        aliases = new Type[var2.size()];
        Iterator var3 = var2.iterator();
        while (var3.hasNext()) {
            TypeAlias var4 = ((TypeAlias) var3.next());
            if (var1.length <= var4.ordinal()) {
                aliases[var4.ordinal()] = new BadType(var4.name());
            } else {
                if (var1[var4.ordinal()] == NativeType.VOID) {
                    aliases[var4.ordinal()] = new BadType(var4.name());
                } else {
                    aliases[var4.ordinal()] = findType(((NativeType) var1[var4.ordinal()]));
                }
            }
            continue;
        }
    }

  private static EnumMap buildTypeMap() {
        EnumMap var0 = new EnumMap(NativeType.class);
        EnumSet var1 = EnumSet.allOf(NativeType.class);
        Iterator var2 = var1.iterator();
        while (var2.hasNext()) {
            NativeType var3 = ((NativeType) var2.next());
            var0.put(var3, jafflType(var3));
            continue;
        }
        return var0;
    }

  private static NativeType[] buildNativeTypeAliases() {
        Platform var0 = Platform.getNativePlatform();
        Package var1 = NativeRuntime.class.getPackage();
        String var2 = var0.getCPU().toString();
        String var3 = var0.getOS().toString();
        EnumSet var4 = EnumSet.allOf(TypeAlias.class);
        NativeType[] var5 = new NativeType[0];
        try {
            Class var6 = Class.forName(new StringBuilder().append(var1.getName()).append(".platform.").append(var2).append(".").append(var3).append(".TypeAliases").toString());
            Field var7 = var6.getField("ALIASES");
            Map var8 = ((Map) Map.class.cast(var7.get(var6)));
            var5 = new NativeType[var4.size()];
            Iterator var9 = var4.iterator();
            while (var9.hasNext()) {
                TypeAlias var10 = ((TypeAlias) var9.next());
                var5[var10.ordinal()] = ((NativeType) var8.get(var10));
                if (var5[var10.ordinal()] != null) {
                    continue;
                } else {
                    var5[var10.ordinal()] = NativeType.VOID;
                    continue;
                }
                return var5;
            }
        } catch (ClassNotFoundException e1) {
            Throwable var7 = e1;
            Logger.getLogger(NativeRuntime.class.getName()).log(Level.SEVERE, new StringBuilder().append("failed to load type aliases: ").append(var7).toString());
        } catch (NoSuchFieldException e2) {
            Throwable var7 = e2;
            Logger.getLogger(NativeRuntime.class.getName()).log(Level.SEVERE, new StringBuilder().append("failed to load type aliases: ").append(var7).toString());
        } catch (IllegalAccessException e3) {
            Throwable var7 = e3;
            Logger.getLogger(NativeRuntime.class.getName()).log(Level.SEVERE, new StringBuilder().append("failed to load type aliases: ").append(var7).toString());
        }
        return var5;
    }

  public Type findType(TypeAlias arg0) {
        return ((Type) aliases[arg0.ordinal()]);
    }

  public final NativeMemoryManager getMemoryManager() {
        return mm;
    }

  public NativeClosureManager getClosureManager() {
        return closureManager;
    }

  public ObjectReferenceManager newObjectReferenceManager() {
        return new DefaultObjectReferenceManager(this);
    }

  public int getLastError() {
        return LastError.getInstance().get();
    }

  public void setLastError(int arg0) {
        LastError.getInstance().set(arg0);
    }

  public boolean isCompatible(Runtime arg0) {
        return arg0 instanceof NativeRuntime;
    }

  public boolean equals(Object arg0) {
        if (this != arg0) {
            if (arg0 == null) {
                return false;
            } else {
                if (getClass() == arg0.getClass()) {
                    NativeRuntime var2 = ((NativeRuntime) arg0);
                    return !Arrays.equals(aliases, var2.aliases) ? 0 : !closureManager.equals(var2.closureManager) ? 0 : mm.equals(var2.mm);
                } else {
                    return false;
                }
            }
        } else {
            return true;
        }
    }

  public int hashCode() {
        int var1 = mm.hashCode();
        var1 = 31 * var1 + closureManager.hashCode();
        var1 = 31 * var1 + Arrays.hashCode(aliases);
        return var1;
    }

  private static Type jafflType(NativeType arg0) {
        switch (arg0) {
            case VOID:
                return new NativeRuntime_TypeDelegate(com.kenai.jffi.Type.VOID, NativeType.VOID);
            case SCHAR:
                return new NativeRuntime_TypeDelegate(com.kenai.jffi.Type.SCHAR, NativeType.SCHAR);
            case UCHAR:
                return new NativeRuntime_TypeDelegate(com.kenai.jffi.Type.UCHAR, NativeType.UCHAR);
            case SSHORT:
                return new NativeRuntime_TypeDelegate(com.kenai.jffi.Type.SSHORT, NativeType.SSHORT);
            case USHORT:
                return new NativeRuntime_TypeDelegate(com.kenai.jffi.Type.USHORT, NativeType.USHORT);
            case SINT:
                return new NativeRuntime_TypeDelegate(com.kenai.jffi.Type.SINT, NativeType.SINT);
            case UINT:
                return new NativeRuntime_TypeDelegate(com.kenai.jffi.Type.UINT, NativeType.UINT);
            case SLONG:
                return new NativeRuntime_TypeDelegate(com.kenai.jffi.Type.SLONG, NativeType.SLONG);
            case ULONG:
                return new NativeRuntime_TypeDelegate(com.kenai.jffi.Type.ULONG, NativeType.ULONG);
            case SLONGLONG:
                return new NativeRuntime_TypeDelegate(com.kenai.jffi.Type.SINT64, NativeType.SLONGLONG);
            case ULONGLONG:
                return new NativeRuntime_TypeDelegate(com.kenai.jffi.Type.UINT64, NativeType.ULONGLONG);
            case FLOAT:
                return new NativeRuntime_TypeDelegate(com.kenai.jffi.Type.FLOAT, NativeType.FLOAT);
            case DOUBLE:
                return new NativeRuntime_TypeDelegate(com.kenai.jffi.Type.DOUBLE, NativeType.DOUBLE);
            case ADDRESS:
                return new NativeRuntime_TypeDelegate(com.kenai.jffi.Type.POINTER, NativeType.ADDRESS);
            default:
                return new BadType(arg0.toString());
        }
    }

  public MemoryManager getMemoryManager() {
        return getMemoryManager();
    }

  public ClosureManager getClosureManager() {
        return getClosureManager();
    }

   NativeRuntime(NativeRuntime_Anon1 arg0) { // было: <init>
        this();
    }

}