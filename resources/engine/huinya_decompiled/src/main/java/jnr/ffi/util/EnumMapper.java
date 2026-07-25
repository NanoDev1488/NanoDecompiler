// исходный (обфусцированный) внутренний класс: jnr.ffi.util.EnumMapper
package jnr.ffi.util;

import java.lang.reflect.Method;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import jnr.ffi.mapper.FromNativeConverter_NoContext;
import jnr.ffi.mapper.ToNativeConverter_NoContext;
import jnr.ffi.util.EnumMapper_StaticDataHolder;

@ToNativeConverter_NoContext
@FromNativeConverter_NoContext
public final class EnumMapper {

    // ---- поля ----
  private final Class enumClass;
  private final int[] intValues;
  private final Map reverseLookupMap;

  private EnumMapper(Class arg0) { // было: <init>
        super();
        reverseLookupMap = new HashMap();
        enumClass = arg0;
        EnumSet var2 = EnumSet.allOf(arg0);
        intValues = new int[var2.size()];
        Method var3 = getNumberValueMethod(arg0, Integer.TYPE);
        Iterator var4 = var2.iterator();
        while (var4.hasNext()) {
            Enum var5 = ((Enum) var4.next());
            Integer var6;
            if (var3 == null) {
                var6 = Integer.valueOf(var5.ordinal());
            } else {
                var6 = reflectedNumberValue(var5, var3);
            }
            intValues[var5.ordinal()] = var6.intValue();
            reverseLookupMap.put(var6, var5);
            continue;
        }
    }

  public static EnumMapper getInstance(Class arg0) {
        EnumMapper var1 = ((EnumMapper) EnumMapper_StaticDataHolder.access$000().get(arg0));
        if (var1 == null) {
            return addMapper(arg0);
        } else {
            return var1;
        }
    }

  private static synchronized EnumMapper addMapper(Class arg0) {
        EnumMapper var1 = new EnumMapper(arg0);
        IdentityHashMap var2 = new IdentityHashMap(EnumMapper_StaticDataHolder.access$000());
        var2.put(arg0, var1);
        EnumMapper_StaticDataHolder.access$002(var2);
        return var1;
    }

  private static Method getNumberValueMethod(Class arg0, Class arg1) {
        Method __stk1;
        try {
            Method var2 = arg0.getDeclaredMethod(new StringBuilder().append(arg1.getSimpleName()).append("Value").toString(), new Class[0]);
            if (var2 == null) {
                __stk1 = null;
            }
            __stk1 = arg1 != var2.getReturnType() ? null : var2;
        } catch (Throwable e1) {
            Throwable var2 = e1;
            return null;
        }
    }

  private static Number reflectedNumberValue(Enum arg0, Method arg1) {
        Number __stk1;
        try {
            __stk1 = ((Number) arg1.invoke(arg0, new Object[0]));
        } catch (Throwable var2) {
            throw new RuntimeException(var2);
        }
    }

  public final Integer integerValue(Enum arg0) {
        if (arg0.getClass() == enumClass) {
            return Integer.valueOf(intValues[arg0.ordinal()]);
        } else {
            throw new IllegalArgumentException(new StringBuilder().append("enum class mismatch, ").append(arg0.getClass()).toString());
        }
    }

  public final int intValue(Enum arg0) {
        return integerValue(arg0).intValue();
    }

  public Enum valueOf(int arg0) {
        return reverseLookup(arg0);
    }

  private Enum reverseLookup(int arg0) {
        Enum var2 = ((Enum) reverseLookupMap.get(Integer.valueOf(arg0)));
        return var2 == null ? badValue(arg0) : var2;
    }

  private Enum badValue(int arg0) {
        Enum __stk1;
        try {
            __stk1 = Enum.valueOf(enumClass, "__UNKNOWN_NATIVE_VALUE");
        } catch (IllegalArgumentException var2) {
            throw new IllegalArgumentException(new StringBuilder().append("No known Enum mapping for value ").append(arg0).append(" of type ").append(enumClass.getName()).toString());
        }
    }

}