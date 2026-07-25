// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.AnnotationTypeMapper.ReflectionToNativeConverter
package jnr.ffi.provider.jffi;

import java.lang.reflect.Method;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeConverter_Cacheable;
import jnr.ffi.provider.jffi.AnnotationTypeMapper;
import jnr.ffi.provider.jffi.AnnotationTypeMapper_AbstractReflectionConverter;

@ToNativeConverter_Cacheable
public final class AnnotationTypeMapper_ReflectionToNativeConverter extends AnnotationTypeMapper_AbstractReflectionConverter implements ToNativeConverter {

    // ---- поля ----
  final AnnotationTypeMapper this$0;

  public AnnotationTypeMapper_ReflectionToNativeConverter(AnnotationTypeMapper arg0, Method arg1, Class arg2) { // было: <init>
        super(arg0, arg1, arg2);
        this$0 = arg0;
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return invoke(arg0, arg1);
    }

}