// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.config.ReceivingServiceConfigBuilder$1
package org.freedesktop.dbus.connections.config;

import java.util.concurrent.atomic.AtomicInteger;
import org.freedesktop.dbus.connections.base.ReceivingService;
import org.freedesktop.dbus.connections.shared.ExecutorNames;
import org.freedesktop.dbus.connections.shared.IThreadPoolRetryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ReceivingServiceConfigBuilder_Anon1 implements IThreadPoolRetryHandler {

    // ---- поля ----
  private AtomicInteger retries;

   ReceivingServiceConfigBuilder_Anon1() { // было: <init>
        super();
        retries = new AtomicInteger(0);
    }

  public boolean handle(ExecutorNames arg0, Exception arg1) {
        if (retries.incrementAndGet() >= 10) {
            LoggerFactory.getLogger(ReceivingService.class).error("Dropping runnable for {}, retry failed for more than {} iterations, cause:", new Object[]{arg0, Integer.valueOf(10), arg1});
            return false;
        } else {
            return true;
        }
    }

}