// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.config.ReceivingServiceConfig
package org.freedesktop.dbus.connections.config;

import org.freedesktop.dbus.connections.shared.IThreadPoolRetryHandler;

public final class ReceivingServiceConfig {

    // ---- поля ----
  private int signalThreadPoolSize;
  private int errorThreadPoolSize;
  private int methodCallThreadPoolSize;
  private int methodReturnThreadPoolSize;
  private int signalThreadPriority;
  private int methodCallThreadPriority;
  private int errorThreadPriority;
  private int methodReturnThreadPriority;
  private IThreadPoolRetryHandler retryHandler;

   ReceivingServiceConfig() { // было: <init>
        super();
        signalThreadPoolSize = 1;
        errorThreadPoolSize = 1;
        methodCallThreadPoolSize = 4;
        methodReturnThreadPoolSize = 1;
        signalThreadPriority = 5;
        methodCallThreadPriority = 5;
        errorThreadPriority = 5;
        methodReturnThreadPriority = 5;
    }

  public int getSignalThreadPoolSize() {
        return signalThreadPoolSize;
    }

  public int getErrorThreadPoolSize() {
        return errorThreadPoolSize;
    }

  public int getMethodCallThreadPoolSize() {
        return methodCallThreadPoolSize;
    }

  public int getMethodReturnThreadPoolSize() {
        return methodReturnThreadPoolSize;
    }

  public int getSignalThreadPriority() {
        return signalThreadPriority;
    }

  public int getMethodCallThreadPriority() {
        return methodCallThreadPriority;
    }

  public int getErrorThreadPriority() {
        return errorThreadPriority;
    }

  public int getMethodReturnThreadPriority() {
        return methodReturnThreadPriority;
    }

  public IThreadPoolRetryHandler getRetryHandler() {
        return retryHandler;
    }

   void setSignalThreadPoolSize(int arg0) {
        signalThreadPoolSize = arg0;
    }

   void setErrorThreadPoolSize(int arg0) {
        errorThreadPoolSize = arg0;
    }

   void setMethodCallThreadPoolSize(int arg0) {
        methodCallThreadPoolSize = arg0;
    }

   void setMethodReturnThreadPoolSize(int arg0) {
        methodReturnThreadPoolSize = arg0;
    }

   void setSignalThreadPriority(int arg0) {
        signalThreadPriority = arg0;
    }

   void setMethodCallThreadPriority(int arg0) {
        methodCallThreadPriority = arg0;
    }

   void setErrorThreadPriority(int arg0) {
        errorThreadPriority = arg0;
    }

   void setMethodReturnThreadPriority(int arg0) {
        methodReturnThreadPriority = arg0;
    }

   void setRetryHandler(IThreadPoolRetryHandler arg0) {
        retryHandler = arg0;
    }

}