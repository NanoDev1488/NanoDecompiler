// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.AsmBuilder
package jnr.ffi.provider.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.Function;
import com.kenai.jffi.ObjectParameterInfo;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jnr.ffi.Runtime;
import jnr.ffi.Variable;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.provider.jffi.AsmBuilder_ObjectField;
import jnr.ffi.provider.jffi.AsmBuilder_ObjectNameGenerator;
import jnr.ffi.provider.jffi.AsmClassLoader;
import jnr.ffi.provider.jffi.AsmUtil;
import jnr.ffi.provider.jffi.CodegenUtils;
import jnr.ffi.provider.jffi.SkinnyMethodAdapter;
import org.objectweb.asm.ClassVisitor;

class AsmBuilder {

    // ---- поля ----
  private final Runtime runtime;
  private final String classNamePath;
  private final ClassVisitor classVisitor;
  private final AsmClassLoader classLoader;
  private final AsmBuilder_ObjectNameGenerator functionId;
  private final AsmBuilder_ObjectNameGenerator contextId;
  private final AsmBuilder_ObjectNameGenerator toNativeConverterId;
  private final AsmBuilder_ObjectNameGenerator toNativeContextId;
  private final AsmBuilder_ObjectNameGenerator fromNativeConverterId;
  private final AsmBuilder_ObjectNameGenerator fromNativeContextId;
  private final AsmBuilder_ObjectNameGenerator objectParameterInfoId;
  private final AsmBuilder_ObjectNameGenerator variableAccessorId;
  private final AsmBuilder_ObjectNameGenerator genericObjectId;
  private final Map toNativeConverters;
  private final Map toNativeContexts;
  private final Map fromNativeConverters;
  private final Map fromNativeContexts;
  private final Map objectParameterInfo;
  private final Map variableAccessors;
  private final Map callContextMap;
  private final Map functionAddresses;
  private final Map genericObjects;
  private final List objectFields;

   AsmBuilder(Runtime arg0, String arg1, ClassVisitor arg2, AsmClassLoader arg3) { // было: <init>
        super();
        functionId = new AsmBuilder_ObjectNameGenerator("functionAddress");
        contextId = new AsmBuilder_ObjectNameGenerator("callContext");
        toNativeConverterId = new AsmBuilder_ObjectNameGenerator("toNativeConverter");
        toNativeContextId = new AsmBuilder_ObjectNameGenerator("toNativeContext");
        fromNativeConverterId = new AsmBuilder_ObjectNameGenerator("fromNativeConverter");
        fromNativeContextId = new AsmBuilder_ObjectNameGenerator("fromNativeContext");
        objectParameterInfoId = new AsmBuilder_ObjectNameGenerator("objectParameterInfo");
        variableAccessorId = new AsmBuilder_ObjectNameGenerator("variableAccessor");
        genericObjectId = new AsmBuilder_ObjectNameGenerator("objectField");
        toNativeConverters = new IdentityHashMap();
        toNativeContexts = new IdentityHashMap();
        fromNativeConverters = new IdentityHashMap();
        fromNativeContexts = new IdentityHashMap();
        objectParameterInfo = new HashMap();
        variableAccessors = new HashMap();
        callContextMap = new HashMap();
        functionAddresses = new HashMap();
        genericObjects = new IdentityHashMap();
        objectFields = new ArrayList();
        runtime = arg0;
        classNamePath = arg1;
        classVisitor = arg2;
        classLoader = arg3;
    }

  public String getClassNamePath() {
        return classNamePath;
    }

   ClassVisitor getClassVisitor() {
        return classVisitor;
    }

  public AsmClassLoader getClassLoader() {
        return classLoader;
    }

  public Runtime getRuntime() {
        return runtime;
    }

   AsmBuilder_ObjectField addField(Map arg0, Object arg1, Class arg2, AsmBuilder_ObjectNameGenerator arg3) {
        AsmBuilder_ObjectField var5 = new AsmBuilder_ObjectField(arg3.generateName(), arg1, arg2);
        objectFields.add(var5);
        arg0.put(arg1, var5);
        return var5;
    }

   AsmBuilder_ObjectField getField(Map arg0, Object arg1, Class arg2, AsmBuilder_ObjectNameGenerator arg3) {
        AsmBuilder_ObjectField var5 = ((AsmBuilder_ObjectField) arg0.get(arg1));
        return var5 == null ? addField(arg0, arg1, arg2, arg3) : var5;
    }

   String getCallContextFieldName(Function arg0) {
        return getField(callContextMap, arg0.getCallContext(), CallContext.class, contextId).name;
    }

   String getCallContextFieldName(CallContext arg0) {
        return getField(callContextMap, arg0, CallContext.class, contextId).name;
    }

   String getFunctionAddressFieldName(Function arg0) {
        return getField(functionAddresses, Long.valueOf(arg0.getFunctionAddress()), Long.TYPE, functionId).name;
    }

   AsmBuilder_ObjectField getRuntimeField() {
        return getObjectField(runtime, runtime.getClass());
    }

   String getFromNativeConverterName(FromNativeConverter arg0) {
        return getFromNativeConverterField(arg0).name;
    }

   String getToNativeConverterName(ToNativeConverter arg0) {
        return getToNativeConverterField(arg0).name;
    }

  private static Class nearestClass(Object arg0, Class arg1) {
        return !Modifier.isPublic(arg0.getClass().getModifiers()) ? arg1 : arg0.getClass();
    }

   AsmBuilder_ObjectField getToNativeConverterField(ToNativeConverter arg0) {
        return getField(toNativeConverters, arg0, nearestClass(arg0, ToNativeConverter.class), toNativeConverterId);
    }

   AsmBuilder_ObjectField getFromNativeConverterField(FromNativeConverter arg0) {
        return getField(fromNativeConverters, arg0, nearestClass(arg0, FromNativeConverter.class), fromNativeConverterId);
    }

   AsmBuilder_ObjectField getToNativeContextField(ToNativeContext arg0) {
        return getField(toNativeContexts, arg0, nearestClass(arg0, ToNativeContext.class), toNativeContextId);
    }

   AsmBuilder_ObjectField getFromNativeContextField(FromNativeContext arg0) {
        return getField(fromNativeContexts, arg0, nearestClass(arg0, FromNativeContext.class), fromNativeContextId);
    }

   String getObjectParameterInfoName(ObjectParameterInfo arg0) {
        return getField(objectParameterInfo, arg0, ObjectParameterInfo.class, objectParameterInfoId).name;
    }

   String getObjectFieldName(Object arg0, Class arg1) {
        return getField(genericObjects, arg0, arg1, genericObjectId).name;
    }

   AsmBuilder_ObjectField getObjectField(Object arg0, Class arg1) {
        return getField(genericObjects, arg0, arg1, genericObjectId);
    }

   String getVariableName(Variable arg0) {
        return getField(variableAccessors, arg0, Variable.class, variableAccessorId).name;
    }

   AsmBuilder_ObjectField[] getObjectFieldArray() {
        return ((AsmBuilder_ObjectField[]) objectFields.toArray(new AsmBuilder_ObjectField[objectFields.size()]));
    }

   Object[] getObjectFieldValues() {
        Object[] var1 = new Object[objectFields.size()];
        int var2 = 0;
        Iterator var3 = objectFields.iterator();
        while (var3.hasNext()) {
            AsmBuilder_ObjectField var4 = ((AsmBuilder_ObjectField) var3.next());
            var1[var2++] = var4.value;
            continue;
        }
        return var1;
    }

   void emitFieldInitialization(SkinnyMethodAdapter arg0, int arg1) {
        int var3 = 0;
        Iterator var4 = objectFields.iterator();
        while (var4.hasNext()) {
            AsmBuilder_ObjectField var5 = ((AsmBuilder_ObjectField) var4.next());
            getClassVisitor().visitField(18, var5.name, CodegenUtils.ci(var5.klass), null, null);
            arg0.aload(0);
            arg0.aload(arg1);
            arg0.pushInt(var3++);
            arg0.aaload();
            if (!var5.klass.isPrimitive()) {
                arg0.checkcast(var5.klass);
            } else {
                Class var6 = AsmUtil.boxedType(var5.klass);
                arg0.checkcast(var6);
                AsmUtil.unboxNumber(arg0, var6, var5.klass);
            }
            arg0.putfield(getClassNamePath(), var5.name, CodegenUtils.ci(var5.klass));
            continue;
        }
    }

}