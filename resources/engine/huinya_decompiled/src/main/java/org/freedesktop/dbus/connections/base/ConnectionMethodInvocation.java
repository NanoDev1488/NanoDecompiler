// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.base.ConnectionMethodInvocation
package org.freedesktop.dbus.connections.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import org.freedesktop.dbus.DBusCallInfo;
import org.freedesktop.dbus.Marshalling;
import org.freedesktop.dbus.connections.base.AbstractConnectionBase;
import org.freedesktop.dbus.connections.base.ReceivingService;
import org.freedesktop.dbus.connections.config.ReceivingServiceConfig;
import org.freedesktop.dbus.connections.config.TransportConfig;
import org.freedesktop.dbus.errors.UnknownMethod;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.exceptions.DBusExecutionException;
import org.freedesktop.dbus.messages.Message;
import org.freedesktop.dbus.messages.MessageFactory;
import org.freedesktop.dbus.messages.MethodCall;
import org.freedesktop.dbus.messages.MethodReturn;
import org.freedesktop.dbus.utils.LoggingHelper;
import org.slf4j.Logger;

public abstract class ConnectionMethodInvocation extends AbstractConnectionBase {

  protected ConnectionMethodInvocation(TransportConfig arg0, ReceivingServiceConfig arg1) { // было: <init>
        super(arg0, arg1);
    }

  protected abstract void handleException(Message arg0, DBusExecutionException arg1);

  protected void queueInvokeMethod(MethodCall arg0, Method arg1, Object arg2) {
        boolean __stk1;
        getLogger().trace("Adding Runnable for method {}", arg1);
        __stk1 = 1 == (arg0.getFlags() & 1);
        int var4 = __stk1;
        getReceivingService().execMethodCallHandler(() -> lambda$queueInvokeMethod$0(arg0, arg1, arg2, ((Boolean) var4)));
    }

  protected Object setupAndInvoke(MethodCall arg0, Method arg1, Object arg2, boolean arg3) {
        getLogger().debug("Running method {} for remote call", arg1);
        try {
            Type[] var5 = arg1.getGenericParameterTypes();
            Object[] var6 = arg0.getParameters();
            arg0.setArgs(Marshalling.deSerializeParameters(var6, var5, this));
            LoggingHelper.logIf(getLogger().isTraceEnabled(), () -> lambda$setupAndInvoke$1(arg0, var5));
        } catch (Exception e1) {
            Throwable var5 = e1;
            getLogger().debug("", var5);
            handleException(arg0, new UnknownMethod("Failure in de-serializing message: " + String.valueOf(var5)));
            return null;
        }
    }

  protected Object invokeMethodAndReply(MethodCall arg0, Method arg1, Object arg2, boolean arg3) {
        Object __stk2;
        try {
            Object var5 = invokeMethod(arg0, arg1, arg2);
            if (arg3) {
                __stk2 = var5;
            } else {
                invokedMethodReply(arg0, arg1, var5);
                __stk2 = var5;
            }
        } catch (DBusExecutionException e1) {
            Throwable var5 = e1;
            getLogger().debug("Failed to invoke method call", ((Throwable) var5));
            handleException(arg0, ((DBusExecutionException) var5));
        } catch (Throwable e2) {
            Throwable var5 = e2;
            getLogger().debug("Error invoking method call", ((Throwable) var5));
            handleException(arg0, new DBusExecutionException(String.format("Error Executing Method %s.%s: %s", new Object[]{arg0.getInterface(), arg0.getName(), var5.getMessage()})));
        }
    }

  protected void invokedMethodReply(MethodCall arg0, Method arg1, Object arg2) {
        MethodReturn var4;
        if (!Void.TYPE.equals(arg1.getReturnType())) {
            StringBuilder var5 = new StringBuilder();
            String[] var6 = Marshalling.getDBusType(arg1.getGenericReturnType());
            int var7 = var6.length;
            int var8 = 0;
            while (var8 < var7) {
                Object var9 = var6[var8];
                var5.append(((String) var9));
                ++var8;
                continue;
            }
            Type[] __obj2 = new Type[1];
            __obj2[0] = arg1.getGenericReturnType();
            var6 = Marshalling.convertParameters(new Object[]{arg2}, __obj2, this);
            var4 = getMessageFactory().createMethodReturn(arg0, var5.toString(), var6);
        } else {
            var4 = getMessageFactory().createMethodReturn(arg0, null, new Object[0]);
        }
        sendMessage(var4);
    }

  protected Object invokeMethod(MethodCall arg0, Method arg1, Object arg2) {
        DBusCallInfo var4 = new DBusCallInfo(arg0);
        getInfoMap().put(Thread.currentThread(), var4);
        try {
            LoggingHelper.logIf(getLogger().isTraceEnabled(), () -> lambda$invokeMethod$2(arg0, arg1, arg2));
            Object[] var5 = arg0.getParameters();
            Object var6 = arg1.invoke(arg2, var5);
        } catch (InvocationTargetException e2) {
            try {
                Throwable var5 = e2;
                getLogger().debug(var5.getMessage(), var5);
                throw var5.getCause();
            } catch (Throwable e2) {
            }
        } catch (Throwable e3) {
            while (true) {
                Throwable var7 = e3;
            }
        }
    }

  private void lambda$invokeMethod$2(MethodCall arg0, Method arg1, Object arg2) {
        try {
            Object[] var4 = arg0.getParameters();
            getLogger().trace("Invoking Method: {} on {} with parameters {}", new Object[]{arg1, arg2, Arrays.deepToString(var4)});
        } catch (DBusException e1) {
            Throwable var4 = e1;
            getLogger().trace("Error getting parameters from method call", var4);
        }
    }

  private void lambda$setupAndInvoke$1(MethodCall arg0, Type[] arg1) {
        try {
            Object[] var3 = arg0.getParameters();
            getLogger().trace("Deserialised {} to types {}", Arrays.deepToString(var3), Arrays.deepToString(arg1));
        } catch (Exception e1) {
            Throwable var3 = e1;
            getLogger().trace("Error getting method call parameters", var3);
        }
    }

  private void lambda$queueInvokeMethod$0(MethodCall arg0, Method arg1, Object arg2, boolean arg3) {
        setupAndInvoke(arg0, arg1, arg2, arg3);
    }

}