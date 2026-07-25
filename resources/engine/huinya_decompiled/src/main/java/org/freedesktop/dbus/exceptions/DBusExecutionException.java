// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.exceptions.DBusExecutionException
package org.freedesktop.dbus.exceptions;

public class DBusExecutionException extends RuntimeException {

    // ---- поля ----
  private static final long serialVersionUID = 6327661667731344250L;
  private String type;

  public DBusExecutionException(String arg0) { // было: <init>
        super(arg0);
    }

  public DBusExecutionException(String arg0, Throwable arg1) { // было: <init>
        super(arg0, arg1);
    }

  public void setType(String arg0) {
        type = arg0;
    }

  public String getType() {
        if (null != type) {
            return type;
        } else {
            return getClass().getName();
        }
    }

}