// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.EnumSetConverter
package jnr.ffi.provider.converters;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import jnr.ffi.mapper.DataConverter;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.FromNativeConverter_Cacheable;
import jnr.ffi.mapper.SignatureType;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeConverter_Cacheable;
import jnr.ffi.util.EnumMapper;

@FromNativeConverter_Cacheable
@ToNativeConverter_Cacheable
public final class EnumSetConverter implements DataConverter {

    // ---- поля ----
  private final Class enumClass;
  private final EnumMapper enumMapper;
  private final EnumSet allValues;

  private EnumSetConverter(Class arg0) { // было: <init>
        super();
        enumClass = arg0;
        enumMapper = EnumMapper.getInstance(arg0);
        allValues = EnumSet.allOf(arg0);
    }

  public static ToNativeConverter getToNativeConverter(SignatureType arg0, ToNativeContext arg1) {
        return getInstance(arg0.getGenericType());
    }

  public static FromNativeConverter getFromNativeConverter(SignatureType arg0, FromNativeContext arg1) {
        return getInstance(arg0.getGenericType());
    }

  private static EnumSetConverter getInstance(Type arg0) {
        if (arg0 instanceof ParameterizedType) {
            if ((((ParameterizedType) arg0)).getActualTypeArguments().length >= 1) {
                Object var1 = (((ParameterizedType) arg0)).getActualTypeArguments()[0];
                if (!(var1 instanceof Class)) {
                    return null;
                } else {
                    if (Enum.class.isAssignableFrom(((Class) var1))) {
                        return new EnumSetConverter((((Class) var1)).asSubclass(Enum.class));
                    } else {
                        return null;
                    }
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

  public Set fromNative(Integer arg0, FromNativeContext arg1) {
        EnumSet var3 = EnumSet.noneOf(enumClass);
        Iterator var4 = allValues.iterator();
        while (var4.hasNext()) {
            Enum var5 = ((Enum) var4.next());
            int var6 = enumMapper.intValue(var5);
            if ((arg0.intValue() & var6) == var6) {
                var3.add(var5);
            }
            continue;
        }
        return var3;
    }

  public Integer toNative(Set arg0, ToNativeContext arg1) {
        int var3 = 0;
        Iterator var4 = arg0.iterator();
        while (var4.hasNext()) {
            Enum var5 = ((Enum) var4.next());
            var3 = var3 | enumMapper.intValue(var5);
            continue;
        }
        return Integer.valueOf(var3);
    }

  public Class nativeType() {
        return Integer.class;
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(((Set) arg0), arg1);
    }

  public Object fromNative(Object arg0, FromNativeContext arg1) {
        return fromNative(((Integer) arg0), arg1);
    }

}