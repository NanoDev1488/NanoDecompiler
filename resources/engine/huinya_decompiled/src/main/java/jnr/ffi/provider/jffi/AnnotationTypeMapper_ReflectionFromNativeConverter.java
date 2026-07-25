// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.AnnotationTypeMapper.ReflectionFromNativeConverter
package jnr.ffi.provider.jffi;

import java.lang.reflect.Method;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.FromNativeConverter_Cacheable;
import jnr.ffi.provider.jffi.AnnotationTypeMapper;
import jnr.ffi.provider.jffi.AnnotationTypeMapper_AbstractReflectionConverter;

@FromNativeConverter_Cacheable
public final class AnnotationTypeMapper_ReflectionFromNativeConverter extends AnnotationTypeMapper_AbstractReflectionConverter implements FromNativeConverter {

    // ---- поля ----
  final AnnotationTypeMapper this$0;

  public AnnotationTypeMapper_ReflectionFromNativeConverter(AnnotationTypeMapper arg0, Method arg1, Class arg2) { // было: <init>
        super(arg0, arg1, arg2);
        this$0 = arg0;
    }

  public Object fromNative(Object arg0, FromNativeContext arg1) {
        return invoke(arg0, arg1);
    }

}