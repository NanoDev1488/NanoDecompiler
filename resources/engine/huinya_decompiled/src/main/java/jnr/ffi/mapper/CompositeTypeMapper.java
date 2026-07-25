// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.CompositeTypeMapper
package jnr.ffi.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeType;
import jnr.ffi.mapper.SignatureType;
import jnr.ffi.mapper.SignatureTypeMapper;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeType;

public final class CompositeTypeMapper implements SignatureTypeMapper {

    // ---- поля ----
  private final Collection signatureTypeMappers;

  public CompositeTypeMapper(SignatureTypeMapper[] arg0) { // было: <init>
        super();
        signatureTypeMappers = Collections.unmodifiableList(Arrays.asList(((SignatureTypeMapper[]) arg0.clone())));
    }

  public CompositeTypeMapper(Collection arg0) { // было: <init>
        super();
        signatureTypeMappers = Collections.unmodifiableList(new ArrayList(arg0));
    }

  public FromNativeType getFromNativeType(SignatureType arg0, FromNativeContext arg1) {
        Iterator var3 = signatureTypeMappers.iterator();
        FromNativeType var5;
        while (true) {
            if (!var3.hasNext()) {
                return null;
            }
            SignatureTypeMapper var4 = ((SignatureTypeMapper) var3.next());
            var5 = var4.getFromNativeType(arg0, arg1);
            if (var5 != null) {
                break;
            }
            continue;
        }
        return var5;
    }

  public ToNativeType getToNativeType(SignatureType arg0, ToNativeContext arg1) {
        Iterator var3 = signatureTypeMappers.iterator();
        ToNativeType var5;
        while (true) {
            if (!var3.hasNext()) {
                return null;
            }
            SignatureTypeMapper var4 = ((SignatureTypeMapper) var3.next());
            var5 = var4.getToNativeType(arg0, arg1);
            if (var5 != null) {
                break;
            }
            continue;
        }
        return var5;
    }

}