// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.AnnotationTypeMapper
package jnr.ffi.provider.jffi;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import jnr.ffi.mapper.AbstractSignatureTypeMapper;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeConverter_FromNative;
import jnr.ffi.mapper.FromNativeType;
import jnr.ffi.mapper.FromNativeTypes;
import jnr.ffi.mapper.SignatureType;
import jnr.ffi.mapper.SignatureTypeMapper;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter_ToNative;
import jnr.ffi.mapper.ToNativeType;
import jnr.ffi.mapper.ToNativeTypes;
import jnr.ffi.provider.jffi.AnnotationTypeMapper_ReflectionFromNativeConverter;
import jnr.ffi.provider.jffi.AnnotationTypeMapper_ReflectionToNativeConverter;

public class AnnotationTypeMapper extends AbstractSignatureTypeMapper implements SignatureTypeMapper {

  public AnnotationTypeMapper() { // было: <init>
        super();
    }

  public FromNativeType getFromNativeType(SignatureType arg0, FromNativeContext arg1) {
        Method var3 = findMethodWithAnnotation(arg0, FromNativeConverter_FromNative.class);
        if (var3 != null) {
            if (Modifier.isStatic(var3.getModifiers())) {
                return FromNativeTypes.create(new AnnotationTypeMapper_ReflectionFromNativeConverter(this, var3, (((FromNativeConverter_FromNative) var3.getAnnotation(FromNativeConverter_FromNative.class))).nativeType()));
            } else {
                throw new IllegalArgumentException(new StringBuilder().append(var3.getDeclaringClass().getName()).append(".").append(var3.getName()).append(" should be declared static").toString());
            }
        } else {
            return null;
        }
    }

  public ToNativeType getToNativeType(SignatureType arg0, ToNativeContext arg1) {
        Method var3 = findMethodWithAnnotation(arg0, ToNativeConverter_ToNative.class);
        if (var3 != null) {
            if (Modifier.isStatic(var3.getModifiers())) {
                return ToNativeTypes.create(new AnnotationTypeMapper_ReflectionToNativeConverter(this, var3, (((ToNativeConverter_ToNative) var3.getAnnotation(ToNativeConverter_ToNative.class))).nativeType()));
            } else {
                throw new IllegalArgumentException(new StringBuilder().append(var3.getDeclaringClass().getName()).append(".").append(var3.getName()).append(" should be declared static").toString());
            }
        } else {
            return null;
        }
    }

  private static Method findMethodWithAnnotation(SignatureType arg0, Class arg1) {
        Class var2 = arg0.getDeclaredType();
        Object var6;
        loop1: while (true) {
            if (var2 == null) {
                return null;
            }
            if (var2 == Object.class) {
                return null;
            } else {
                Method[] var3 = var2.getDeclaredMethods();
                int var4 = var3.length;
                int var5 = 0;
            }
            while (true) {
                if (var5 >= var4) {
                    var2 = var2.getSuperclass();
                    continue loop1;
                } else {
                    var6 = var3[var5];
                    if (var6.isAnnotationPresent(arg1)) {
                        break;
                    }
                    ++var5;
                    continue;
                }
            }
        }
        return ((Method) var6);
    }

}