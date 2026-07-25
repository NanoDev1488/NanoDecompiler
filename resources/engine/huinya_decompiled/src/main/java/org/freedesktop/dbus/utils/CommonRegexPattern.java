// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.utils.CommonRegexPattern
package org.freedesktop.dbus.utils;

import java.util.regex.Pattern;

public final class CommonRegexPattern {

    // ---- поля ----
  public static final Pattern PROXY_SPLIT_PATTERN;
  public static final Pattern IFACE_PATTERN;
  public static final Pattern DBUS_IFACE_PATTERN;
  public static final Pattern EXCEPTION_EXTRACT_PATTERN;
  public static final Pattern EXCEPTION_PARTIAL_PATTERN;

    static {
        PROXY_SPLIT_PATTERN = Pattern.compile("[<>]");
        IFACE_PATTERN = Pattern.compile("^interface *name *= *['\"]([^'\"]*)['\"].*$");
        DBUS_IFACE_PATTERN = Pattern.compile("^.*\\.([^\\.]+)$");
        EXCEPTION_EXTRACT_PATTERN = Pattern.compile("\\.([^\\.]*)$");
        EXCEPTION_PARTIAL_PATTERN = Pattern.compile(".*\\..*");
    }

  private CommonRegexPattern() { // было: <init>
        super();
    }

}