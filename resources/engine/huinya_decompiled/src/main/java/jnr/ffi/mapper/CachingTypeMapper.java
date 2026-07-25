// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.CachingTypeMapper
package jnr.ffi.mapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import jnr.ffi.mapper.AbstractSignatureTypeMapper;
import jnr.ffi.mapper.CachingTypeMapper_InvalidType;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeType;
import jnr.ffi.mapper.FromNativeType_Cacheable;
import jnr.ffi.mapper.SignatureType;
import jnr.ffi.mapper.SignatureTypeMapper;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeType;
import jnr.ffi.mapper.ToNativeType_Cacheable;

public final class CachingTypeMapper extends AbstractSignatureTypeMapper implements SignatureTypeMapper {

    // ---- поля ----
  private final SignatureTypeMapper mapper;
  private volatile Map toNativeTypeMap;
  private volatile Map fromNativeTypeMap;
  private static final CachingTypeMapper_InvalidType UNCACHEABLE_TYPE;
  private static final CachingTypeMapper_InvalidType NO_TYPE;

    static {
        UNCACHEABLE_TYPE = new CachingTypeMapper_InvalidType(null);
        NO_TYPE = new CachingTypeMapper_InvalidType(null);
    }

  public CachingTypeMapper(SignatureTypeMapper arg0) { // было: <init>
        super();
        toNativeTypeMap = Collections.emptyMap();
        fromNativeTypeMap = Collections.emptyMap();
        mapper = arg0;
    }

  public FromNativeType getFromNativeType(SignatureType arg0, FromNativeContext arg1) {
        FromNativeType var3 = ((FromNativeType) fromNativeTypeMap.get(arg0));
        if (var3 != UNCACHEABLE_TYPE) {
            if (var3 != NO_TYPE) {
                return var3 == null ? lookupAndCacheFromNativeType(arg0, arg1) : var3;
            } else {
                return null;
            }
        } else {
            return mapper.getFromNativeType(arg0, arg1);
        }
    }

  public ToNativeType getToNativeType(SignatureType arg0, ToNativeContext arg1) {
        ToNativeType var3 = ((ToNativeType) toNativeTypeMap.get(arg0));
        if (var3 != UNCACHEABLE_TYPE) {
            if (var3 != NO_TYPE) {
                return var3 == null ? lookupAndCacheToNativeType(arg0, arg1) : var3;
            } else {
                return null;
            }
        } else {
            return mapper.getToNativeType(arg0, arg1);
        }
    }

  private synchronized FromNativeType lookupAndCacheFromNativeType(SignatureType arg0, FromNativeContext arg1) {
        FromNativeType var3 = ((FromNativeType) fromNativeTypeMap.get(arg0));
        if (var3 == null) {
            var3 = mapper.getFromNativeType(arg0, arg1);
            FromNativeType var4 = var3;
            if (var3 != null) {
                if (!var3.getClass().isAnnotationPresent(FromNativeType_Cacheable.class)) {
                    var4 = UNCACHEABLE_TYPE;
                }
            } else {
                var4 = NO_TYPE;
            }
            HashMap var5 = new HashMap(fromNativeTypeMap.size() + 1);
            var5.putAll(fromNativeTypeMap);
            var5.put(arg0, var4);
            fromNativeTypeMap = Collections.unmodifiableMap(var5);
        }
        return var3 == NO_TYPE ? null : var3;
    }

  private synchronized ToNativeType lookupAndCacheToNativeType(SignatureType arg0, ToNativeContext arg1) {
        ToNativeType var3 = ((ToNativeType) toNativeTypeMap.get(arg0));
        if (var3 == null) {
            var3 = mapper.getToNativeType(arg0, arg1);
            ToNativeType var4 = var3;
            if (var3 != null) {
                if (!var3.getClass().isAnnotationPresent(ToNativeType_Cacheable.class)) {
                    var4 = UNCACHEABLE_TYPE;
                }
            } else {
                var4 = NO_TYPE;
            }
            HashMap var5 = new HashMap(toNativeTypeMap.size() + 1);
            var5.putAll(toNativeTypeMap);
            var5.put(arg0, var4);
            toNativeTypeMap = Collections.unmodifiableMap(var5);
        }
        return var3 == NO_TYPE ? null : var3;
    }

}