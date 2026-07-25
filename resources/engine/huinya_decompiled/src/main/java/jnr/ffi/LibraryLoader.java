// исходный (обфусцированный) внутренний класс: jnr.ffi.LibraryLoader
package jnr.ffi;

import java.io.File;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import jnr.ffi.CallingConvention;
import jnr.ffi.LibraryLoader_Anon1;
import jnr.ffi.LibraryLoader_DefaultLibPaths;
import jnr.ffi.LibraryOption;
import jnr.ffi.mapper.DataConverter;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.FunctionMapper;
import jnr.ffi.mapper.FunctionMapper_Builder;
import jnr.ffi.mapper.SignatureTypeMapper;
import jnr.ffi.mapper.SignatureTypeMapperAdapter;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.TypeMapper;
import jnr.ffi.mapper.TypeMapper_Builder;
import jnr.ffi.provider.FFIProvider;

public abstract class LibraryLoader {

    // ---- поля ----
  public static final String DEFAULT_LIBRARY = "RTLD_DEFAULT";
  private final List searchPaths;
  private final List libraryNames;
  private final List typeMappers;
  private final List functionMappers;
  private final Map optionMap;
  private final TypeMapper_Builder typeMapperBuilder;
  private final FunctionMapper_Builder functionMapperBuilder;
  private final Class interfaceClass;
  private boolean failImmediately;

  public static LibraryLoader create(Class arg0) {
        return FFIProvider.getSystemProvider().createLibraryLoader(arg0);
    }

  protected LibraryLoader(Class arg0) { // было: <init>
        super();
        searchPaths = new ArrayList();
        libraryNames = new ArrayList();
        typeMappers = new ArrayList();
        functionMappers = new ArrayList();
        optionMap = new EnumMap(LibraryOption.class);
        typeMapperBuilder = new TypeMapper_Builder();
        functionMapperBuilder = new FunctionMapper_Builder();
        failImmediately = false;
        interfaceClass = arg0;
    }

  public static boolean saveError(Map arg0, boolean arg1, boolean arg2) {
        int __stk1;
        __stk1 = arg0.containsKey(LibraryOption.SaveError) ? 1 : !arg0.containsKey(LibraryOption.IgnoreError);
        int var3 = __stk1;
        if (var3 == 0) {
            if (arg1) {
                var3 = 1;
            }
        } else {
            if (arg2) {
                if (!arg1) {
                    var3 = 0;
                }
            }
        }
        return ((Boolean) var3);
    }

  public static Object loadLibrary(Class arg0, Map arg1, Map arg2, String[] arg3) {
        LibraryLoader var4 = FFIProvider.getSystemProvider().createLibraryLoader(arg0);
        String[] var5 = arg3;
        int var6 = var5.length;
        int var7 = 0;
        while (var7 < var6) {
            Object var8 = var5[var7];
            if (!var8.equals("RTLD_DEFAULT")) {
                var4.library(((String) var8));
                List var9 = ((List) arg2.get(var8));
                if (var9 != null) {
                    Iterator var10 = var9.iterator();
                    while (var10.hasNext()) {
                        String var11 = ((String) var10.next());
                        var4.search(var11);
                        continue;
                    }
                }
            } else {
                var4.searchDefault();
            }
            ++var7;
            continue;
        }
        if (arg1 != null) {
            var5 = arg1.entrySet().iterator();
            while (var5.hasNext()) {
                var6 = ((Entry) var5.next());
                var4.option(((LibraryOption) var6.getKey()), var6.getValue());
                continue;
            }
        }
        return var4.failImmediately().load();
    }

  public static Object loadLibrary(Class arg0, Map arg1, String[] arg2) {
        return loadLibrary(arg0, arg1, Collections.EMPTY_MAP, arg2);
    }

  public LibraryLoader library(String arg0) {
        if (!arg0.equals("RTLD_DEFAULT")) {
            libraryNames.add(arg0);
            return this;
        } else {
            return searchDefault();
        }
    }

  public LibraryLoader searchDefault() {
        libraryNames.add("RTLD_DEFAULT");
        return this;
    }

  public LibraryLoader search(String arg0) {
        searchPaths.add(arg0);
        return this;
    }

  public LibraryLoader option(LibraryOption arg0, Object arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #53 // jnr.ffi.LibraryLoader$2.$SwitchMap$jnr$ffi$LibraryOption:[I
        //      3: aload_1
        //      4: invokevirtual  #98 // jnr.ffi.LibraryOption.ordinal:()I
        //      7: iaload
        //      8: lookupswitch  default->120, 1->36, 2->108
        //     36: aload_2
        //     37: instanceof  #35 // jnr.ffi.mapper.SignatureTypeMapper
        //     40: ifeq  55 (offset +15)
        //     43: aload_0
        //     44: aload_2
        //     45: checkcast  #35 // jnr.ffi.mapper.SignatureTypeMapper
        //     48: invokevirtual  #92 // jnr.ffi.LibraryLoader.mapper:(Ljnr/ffi/mapper/SignatureTypeMapper;)Ljnr/ffi/LibraryLoader;
        //     51: pop
        //     52: goto  132 (offset +80)
        //     55: aload_2
        //     56: instanceof  #37 // jnr.ffi.mapper.TypeMapper
        //     59: ifeq  74 (offset +15)
        //     62: aload_0
        //     63: aload_2
        //     64: checkcast  #37 // jnr.ffi.mapper.TypeMapper
        //     67: invokevirtual  #93 // jnr.ffi.LibraryLoader.mapper:(Ljnr/ffi/mapper/TypeMapper;)Ljnr/ffi/LibraryLoader;
        //     70: pop
        //     71: goto  132 (offset +61)
        //     74: aload_2
        //     75: ifnull  132 (offset +57)
        //     78: new  #7 // java.lang.IllegalArgumentException
        //     81: dup
        //     82: new  #12 // java.lang.StringBuilder
        //     85: dup
        //     86: invokespecial  #68 // java.lang.StringBuilder.<init>:()V
        //     89: ldc  #2 // 'invalid TypeMapper: '
        //     91: invokevirtual  #70 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     94: aload_2
        //     95: invokevirtual  #64 // java.lang.Object.getClass:()Ljava/lang/Class;
        //     98: invokevirtual  #69 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    101: invokevirtual  #71 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    104: invokespecial  #62 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    107: athrow
        //    108: aload_0
        //    109: aload_2
        //    110: checkcast  #33 // jnr.ffi.mapper.FunctionMapper
        //    113: invokevirtual  #91 // jnr.ffi.LibraryLoader.mapper:(Ljnr/ffi/mapper/FunctionMapper;)Ljnr/ffi/LibraryLoader;
        //    116: pop
        //    117: goto  132 (offset +15)
        //    120: aload_0
        //    121: getfield  #49 // jnr.ffi.LibraryLoader.optionMap:Ljava/util/Map;
        //    124: aload_1
        //    125: aload_2
        //    126: invokeinterface  #124 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //    131: pop
        //    132: aload_0
        //    133: areturn
    }

  public LibraryLoader mapper(TypeMapper arg0) {
        typeMappers.add(new SignatureTypeMapperAdapter(arg0));
        return this;
    }

  public LibraryLoader mapper(SignatureTypeMapper arg0) {
        typeMappers.add(arg0);
        return this;
    }

  public LibraryLoader map(Class arg0, ToNativeConverter arg1) {
        typeMapperBuilder.map(arg0, arg1);
        return this;
    }

  public LibraryLoader map(Class arg0, FromNativeConverter arg1) {
        typeMapperBuilder.map(arg0, arg1);
        return this;
    }

  public LibraryLoader map(Class arg0, DataConverter arg1) {
        typeMapperBuilder.map(arg0, arg1);
        return this;
    }

  public LibraryLoader mapper(FunctionMapper arg0) {
        functionMappers.add(arg0);
        return this;
    }

  public LibraryLoader map(String arg0, String arg1) {
        functionMapperBuilder.map(arg0, arg1);
        return this;
    }

  public LibraryLoader convention(CallingConvention arg0) {
        optionMap.put(LibraryOption.CallingConvention, arg0);
        return this;
    }

  public final LibraryLoader stdcall() {
        return convention(CallingConvention.STDCALL);
    }

  public final LibraryLoader failImmediately() {
        failImmediately = true;
        return this;
    }

  public Object load(String arg0) {
        return library(arg0).load();
    }

  public Object load() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #48 // jnr.ffi.LibraryLoader.libraryNames:Ljava/util/List;
        //      4: invokeinterface  #118 // java.util.List.isEmpty:()Z, count 1
        //      9: ifeq  22 (offset +13)
        //     12: new  #14 // java.lang.UnsatisfiedLinkError
        //     15: dup
        //     16: ldc  #3 // 'no library names specified'
        //     18: invokespecial  #73 // java.lang.UnsatisfiedLinkError.<init>:(Ljava/lang/String;)V
        //     21: athrow
        //     22: aload_0
        //     23: getfield  #52 // jnr.ffi.LibraryLoader.typeMappers:Ljava/util/List;
        //     26: iconst_0
        //     27: new  #36 // jnr.ffi.mapper.SignatureTypeMapperAdapter
        //     30: dup
        //     31: aload_0
        //     32: getfield  #51 // jnr.ffi.LibraryLoader.typeMapperBuilder:Ljnr/ffi/mapper/TypeMapper$Builder;
        //     35: invokevirtual  #106 // jnr.ffi.mapper.TypeMapper$Builder.build:()Ljnr/ffi/mapper/TypeMapper;
        //     38: invokespecial  #104 // jnr.ffi.mapper.SignatureTypeMapperAdapter.<init>:(Ljnr/ffi/mapper/TypeMapper;)V
        //     41: invokeinterface  #114 // java.util.List.add:(ILjava/lang/Object;)V, count 3
        //     46: aload_0
        //     47: getfield  #49 // jnr.ffi.LibraryLoader.optionMap:Ljava/util/Map;
        //     50: getstatic  #59 // jnr.ffi.LibraryOption.TypeMapper:Ljnr/ffi/LibraryOption;
        //     53: aload_0
        //     54: getfield  #52 // jnr.ffi.LibraryLoader.typeMappers:Ljava/util/List;
        //     57: invokeinterface  #120 // java.util.List.size:()I, count 1
        //     62: iconst_1
        //     63: if_icmple  80 (offset +17)
        //     66: new  #32 // jnr.ffi.mapper.CompositeTypeMapper
        //     69: dup
        //     70: aload_0
        //     71: getfield  #52 // jnr.ffi.LibraryLoader.typeMappers:Ljava/util/List;
        //     74: invokespecial  #100 // jnr.ffi.mapper.CompositeTypeMapper.<init>:(Ljava/util/Collection;)V
        //     77: goto  90 (offset +13)
        //     80: aload_0
        //     81: getfield  #52 // jnr.ffi.LibraryLoader.typeMappers:Ljava/util/List;
        //     84: iconst_0
        //     85: invokeinterface  #117 // java.util.List.get:(I)Ljava/lang/Object;, count 2
        //     90: invokeinterface  #124 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //     95: pop
        //     96: aload_0
        //     97: getfield  #46 // jnr.ffi.LibraryLoader.functionMappers:Ljava/util/List;
        //    100: iconst_0
        //    101: aload_0
        //    102: getfield  #45 // jnr.ffi.LibraryLoader.functionMapperBuilder:Ljnr/ffi/mapper/FunctionMapper$Builder;
        //    105: invokevirtual  #102 // jnr.ffi.mapper.FunctionMapper$Builder.build:()Ljnr/ffi/mapper/FunctionMapper;
        //    108: invokeinterface  #114 // java.util.List.add:(ILjava/lang/Object;)V, count 3
        //    113: aload_0
        //    114: getfield  #49 // jnr.ffi.LibraryLoader.optionMap:Ljava/util/Map;
        //    117: getstatic  #56 // jnr.ffi.LibraryOption.FunctionMapper:Ljnr/ffi/LibraryOption;
        //    120: aload_0
        //    121: getfield  #46 // jnr.ffi.LibraryLoader.functionMappers:Ljava/util/List;
        //    124: invokeinterface  #120 // java.util.List.size:()I, count 1
        //    129: iconst_1
        //    130: if_icmple  147 (offset +17)
        //    133: new  #31 // jnr.ffi.mapper.CompositeFunctionMapper
        //    136: dup
        //    137: aload_0
        //    138: getfield  #46 // jnr.ffi.LibraryLoader.functionMappers:Ljava/util/List;
        //    141: invokespecial  #99 // jnr.ffi.mapper.CompositeFunctionMapper.<init>:(Ljava/util/Collection;)V
        //    144: goto  157 (offset +13)
        //    147: aload_0
        //    148: getfield  #46 // jnr.ffi.LibraryLoader.functionMappers:Ljava/util/List;
        //    151: iconst_0
        //    152: invokeinterface  #117 // java.util.List.get:(I)Ljava/lang/Object;, count 2
        //    157: invokeinterface  #124 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //    162: pop
        //    163: aload_0
        //    164: aload_0
        //    165: getfield  #47 // jnr.ffi.LibraryLoader.interfaceClass:Ljava/lang/Class;
        //    168: aload_0
        //    169: getfield  #48 // jnr.ffi.LibraryLoader.libraryNames:Ljava/util/List;
        //    172: invokestatic  #79 // java.util.Collections.unmodifiableList:(Ljava/util/List;)Ljava/util/List;
        //    175: aload_0
        //    176: invokespecial  #86 // jnr.ffi.LibraryLoader.getSearchPaths:()Ljava/util/Collection;
        //    179: aload_0
        //    180: getfield  #49 // jnr.ffi.LibraryLoader.optionMap:Ljava/util/Map;
        //    183: invokestatic  #80 // java.util.Collections.unmodifiableMap:(Ljava/util/Map;)Ljava/util/Map;
        //    186: aload_0
        //    187: getfield  #44 // jnr.ffi.LibraryLoader.failImmediately:Z
        //    190: invokevirtual  #89 // jnr.ffi.LibraryLoader.loadLibrary:(Ljava/lang/Class;Ljava/util/Collection;Ljava/util/Collection;Ljava/util/Map;Z)Ljava/lang/Object;
        //    193: areturn
        //    194: astore_1
        //    195: aload_0
        //    196: getfield  #44 // jnr.ffi.LibraryLoader.failImmediately:Z
        //    199: ifeq  204 (offset +5)
        //    202: aload_1
        //    203: athrow
        //    204: aload_0
        //    205: aload_1
        //    206: invokespecial  #83 // jnr.ffi.LibraryLoader.createErrorProxy:(Ljava/lang/Throwable;)Ljava/lang/Object;
        //    209: areturn
        //    210: astore_1
        //    211: aload_1
        //    212: instanceof  #10 // java.lang.RuntimeException
        //    215: ifeq  225 (offset +10)
        //    218: aload_1
        //    219: checkcast  #10 // java.lang.RuntimeException
        //    222: goto  233 (offset +11)
        //    225: new  #10 // java.lang.RuntimeException
        //    228: dup
        //    229: aload_1
        //    230: invokespecial  #65 // java.lang.RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //    233: astore_2
        //    234: aload_0
        //    235: getfield  #44 // jnr.ffi.LibraryLoader.failImmediately:Z
        //    238: ifeq  243 (offset +5)
        //    241: aload_2
        //    242: athrow
        //    243: aload_0
        //    244: aload_2
        //    245: invokespecial  #83 // jnr.ffi.LibraryLoader.createErrorProxy:(Ljava/lang/Throwable;)Ljava/lang/Object;
        //    248: areturn
        //       Exception table:
        //         from 163 to 193 target 194 type java.lang.LinkageError
        //         from 163 to 193 target 210 type java.lang.Exception
    }

  private Object createErrorProxy(Throwable arg0) {
        return interfaceClass.cast(Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass, LoadedLibrary.class}, new LibraryLoader_Anon1(this, arg0)));
    }

  private Collection getSearchPaths() {
        ArrayList var1 = new ArrayList(searchPaths);
        var1.addAll(LibraryLoader_DefaultLibPaths.PATHS);
        return Collections.unmodifiableList(var1);
    }

  protected abstract Object loadLibrary(Class arg0, Collection arg1, Collection arg2, Map arg3, boolean arg4);

  private static List getPropertyPaths(String arg0) {
        String var1 = System.getProperty(arg0);
        if (var1 == null) {
            return Collections.emptyList();
        } else {
            String[] var2 = var1.split(File.pathSeparator);
            return new ArrayList(Arrays.asList(var2));
        }
    }

  static List access$000(String arg0) {
        return getPropertyPaths(arg0);
    }

}