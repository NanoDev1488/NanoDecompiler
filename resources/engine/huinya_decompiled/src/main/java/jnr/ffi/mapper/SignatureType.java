// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.SignatureType
package jnr.ffi.mapper;

import java.lang.reflect.Type;
import java.util.Collection;

public interface SignatureType {

  public abstract Class getDeclaredType();

  public abstract Collection getAnnotations();

  public abstract Type getGenericType();

}