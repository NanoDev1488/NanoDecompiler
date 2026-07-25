// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.base.FallbackContainer
package org.freedesktop.dbus.connections.base;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.freedesktop.dbus.messages.ExportedObject;
import org.freedesktop.dbus.utils.LoggingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FallbackContainer {

    // ---- поля ----
  private final Logger logger;
  private final Map fallbacks;

   FallbackContainer() { // было: <init>
        super();
        logger = LoggerFactory.getLogger(getClass());
        fallbacks = new ConcurrentHashMap();
    }

  public synchronized void add(String arg0, ExportedObject arg1) {
        logger.debug("Adding fallback on {} of {}", arg0, arg1);
        fallbacks.put(arg0.split("/"), arg1);
    }

  public synchronized void remove(String arg0) {
        logger.debug("Removing fallback on {}", arg0);
        fallbacks.remove(arg0.split("/"));
    }

  public synchronized ExportedObject get(String arg0) {
        int var2 = 0;
        Object var3 = null;
        String[] var4 = arg0.split("/");
        Iterator var5 = fallbacks.entrySet().iterator();
        while (var5.hasNext()) {
            Entry var6 = ((Entry) var5.next());
            String[] var7 = ((String[]) var6.getKey());
            LoggingHelper.logIf(logger.isTraceEnabled(), () -> lambda$get$0(var7, var4));
            int var8 = 0;
            while (var8 < var4.length) {
                if (var8 >= var7.length) {
                    break;
                }
                if (var4[var8].equals(var7[var8])) {
                    ++var8;
                    continue;
                } else {
                    break;
                }
            }
            if (var8 > 0) {
                if (var8 == var7.length) {
                    if (var8 > var2) {
                        var3 = ((ExportedObject) var6.getValue());
                    }
                }
            }
            logger.trace("Matches {} bestobject now {}", Integer.valueOf(var8), var3);
            continue;
        }
        logger.debug("Found fallback for {} of {}", arg0, var3);
        return ((ExportedObject) var3);
    }

  private void lambda$get$0(String[] arg0, String[] arg1) {
        logger.trace("Trying fallback path {} to match {}", Arrays.deepToString(arg0), Arrays.deepToString(arg1));
    }

}