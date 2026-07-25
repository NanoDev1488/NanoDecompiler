// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.propertyref.PropRefRemoteHandler
package org.freedesktop.dbus.propertyref;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Optional;
import org.freedesktop.dbus.RemoteInvocationHandler;
import org.freedesktop.dbus.RemoteObject;
import org.freedesktop.dbus.TypeRef;
import org.freedesktop.dbus.annotations.DBusBoundProperty;
import org.freedesktop.dbus.annotations.DBusProperty_Access;
import org.freedesktop.dbus.connections.AbstractConnection;
import org.freedesktop.dbus.exceptions.DBusExecutionException;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.dbus.propertyref.PropertyRef;
import org.freedesktop.dbus.utils.DBusNamingUtil;
import org.freedesktop.dbus.utils.Util;

public final class PropRefRemoteHandler {

    // ---- поля ----
  private static final Method PROP_GET_METHOD;
  private static final Method PROP_SET_METHOD;

    static {
        PROP_GET_METHOD = getPropertiesMethod("Get", new Class[]{String.class, String.class});
        Class[] __obj2 = new Class[3];
        __obj2[0] = String.class;
        __obj2[1] = String.class;
        __obj2[2] = Object.class;
        PROP_SET_METHOD = getPropertiesMethod("Set", __obj2);
    }

  private PropRefRemoteHandler() { // было: <init>
        super();
    }

  public static Object handleDBusBoundProperty(AbstractConnection arg0, RemoteObject arg1, Method arg2, Object[] arg3) {
        String[] __stk6;
        String var4 = DBusNamingUtil.getPropertyName(arg2);
        DBusProperty_Access var5 = PropertyRef.accessForMethod(arg2);
        Class var6 = (((DBusBoundProperty) arg2.getAnnotation(DBusBoundProperty.class))).type();
        Object var7 = null;
        if (TypeRef.class.isAssignableFrom(var6)) {
            var7 = ((Type[]) Optional.ofNullable(Util.unwrapTypeRef(var6)).map(lp0 -> lambda$handleDBusBoundProperty$0(((Type) lp0))).orElse(null));
        }
        if (var7 == null) {
            __stk6 = null;
        } else {
            __stk6 = new String[]{Marshalling.getDBusType(((Type[]) var7))};
        }
        String[] var8 = __stk6;
        if (var5 != DBusProperty_Access.READ) {
            Object[] __obj5 = new Object[3];
            __obj5[0] = DBusNamingUtil.getInterfaceName(arg2.getDeclaringClass());
            __obj5[1] = var4;
            __obj5[2] = arg3[0];
            return RemoteInvocationHandler.executeRemoteMethod(arg1, PROP_SET_METHOD, ((String[]) var8), new Type[]{arg2.getGenericReturnType()}, arg0, 0, null, __obj5);
        } else {
            Object[] __obj3 = new Object[2];
            __obj3[0] = DBusNamingUtil.getInterfaceName(arg2.getDeclaringClass());
            __obj3[1] = var4;
            return RemoteInvocationHandler.executeRemoteMethod(arg1, PROP_GET_METHOD, new Type[]{arg2.getGenericReturnType()}, arg0, 0, null, __obj3);
        }
    }

  private static Method getPropertiesMethod(String arg0, Class[] arg1) {
        Method __stk1;
        try {
            __stk1 = Properties.class.getMethod(arg0, arg1);
        } catch (NoSuchMethodException var2) {
            throw new DBusExecutionException("Unable to get methods of DBus Properties interface", var2);
        }
    }

  private static Type[] lambda$handleDBusBoundProperty$0(Type arg0) {
        return new Type[]{arg0};
    }

}