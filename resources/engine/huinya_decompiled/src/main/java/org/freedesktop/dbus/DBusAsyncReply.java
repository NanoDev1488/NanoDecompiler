// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.DBusAsyncReply
package org.freedesktop.dbus;

import java.lang.reflect.Method;
import org.freedesktop.dbus.RemoteInvocationHandler;
import org.freedesktop.dbus.connections.AbstractConnection;
import org.freedesktop.dbus.errors.NoReply;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.exceptions.DBusExecutionException;
import org.freedesktop.dbus.messages.Error;
import org.freedesktop.dbus.messages.Message;
import org.freedesktop.dbus.messages.MethodCall;
import org.freedesktop.dbus.messages.MethodReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBusAsyncReply {

    // ---- поля ----
  private final Logger logger;
  private Object rval;
  private DBusExecutionException error;
  private final MethodCall mc;
  private final Method me;
  private final AbstractConnection conn;

  public DBusAsyncReply(MethodCall arg0, Method arg1, AbstractConnection arg2) { // было: <init>
        super();
        logger = LoggerFactory.getLogger(getClass());
        rval = null;
        error = null;
        mc = arg0;
        me = arg1;
        conn = arg2;
    }

  private synchronized void checkReply() {
        if (mc.hasReply()) {
            Message var1 = mc.getReply();
            if (!(var1 instanceof Error)) {
                if (var1 instanceof MethodReturn) {
                    try {
                        Object var3 = RemoteInvocationHandler.convertRV(var1.getParameters(), me, conn);
                        rval = var3;
                    } catch (DBusExecutionException e1) {
                        Throwable var3 = e1;
                        error = ((DBusExecutionException) var3);
                    } catch (DBusException e2) {
                        Throwable var3 = e2;
                        logger.debug("", ((Throwable) var3));
                        error = new DBusExecutionException(var3.getMessage());
                    }
                }
            } else {
                Error var2 = ((Error) var1);
                error = var2.getException();
            }
        }
    }

  public boolean hasReply() {
        if (null != rval) {
            return true;
        } else {
            if (null == error) {
                checkReply();
                return null != rval ? 1 : null != error;
            } else {
                return true;
            }
        }
    }

  public Object getReply() {
        if (null == rval) {
            if (null == error) {
                checkReply();
                if (null == rval) {
                    if (null == error) {
                        throw new NoReply("Async call has not had a reply");
                    } else {
                        throw error;
                    }
                } else {
                    return rval;
                }
            } else {
                throw error;
            }
        } else {
            return rval;
        }
    }

  public String toString() {
        return "Waiting for: " + String.valueOf(mc);
    }

  public Method getMethod() {
        return me;
    }

  public AbstractConnection getConnection() {
        return conn;
    }

  public MethodCall getCall() {
        return mc;
    }

}