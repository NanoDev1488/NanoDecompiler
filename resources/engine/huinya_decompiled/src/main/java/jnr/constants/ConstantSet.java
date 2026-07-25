// исходный (обфусцированный) внутренний класс: jnr.constants.ConstantSet
package jnr.constants;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import jnr.constants.Constant;
import jnr.constants.ConstantSet_ConstantIterator;
import jnr.constants.PlatformConstants;
import jnr.constants.platform.Errno;

public class ConstantSet extends AbstractSet {

    // ---- поля ----
  private final Map nameToConstant;
  private final Map valueToConstant;
  private final Set constants;
  private final Class enumClass;
  private volatile Long minValue;
  private volatile Long maxValue;
  private static final ConcurrentMap constantSets;
  private static final Object lock;
  private static final ClassLoader LOADER;
  private static final boolean CAN_LOAD_RESOURCES;
  private static volatile Throwable RESOURCE_READ_ERROR;

    static {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: new  #34 // java.util.concurrent.ConcurrentHashMap
        //      3: dup
        //      4: invokespecial  #87 // java.util.concurrent.ConcurrentHashMap.<init>:()V
        //      7: putstatic  #50 // jnr.constants.ConstantSet.constantSets:Ljava/util/concurrent/ConcurrentMap;
        //     10: new  #18 // java.lang.Object
        //     13: dup
        //     14: invokespecial  #72 // java.lang.Object.<init>:()V
        //     17: putstatic  #53 // jnr.constants.ConstantSet.lock:Ljava/lang/Object;
        //     20: ldc  #37 // jnr.constants.ConstantSet
        //     22: invokevirtual  #63 // java.lang.Class.getClassLoader:()Ljava/lang/ClassLoader;
        //     25: astore_0
        //     26: aload_0
        //     27: ifnull  37 (offset +10)
        //     30: aload_0
        //     31: putstatic  #48 // jnr.constants.ConstantSet.LOADER:Ljava/lang/ClassLoader;
        //     34: goto  43 (offset +9)
        //     37: invokestatic  #68 // java.lang.ClassLoader.getSystemClassLoader:()Ljava/lang/ClassLoader;
        //     40: putstatic  #48 // jnr.constants.ConstantSet.LOADER:Ljava/lang/ClassLoader;
        //     43: iconst_0
        //     44: istore_1
        //     45: new  #38 // jnr.constants.ConstantSet$1
        //     48: dup
        //     49: invokespecial  #94 // jnr.constants.ConstantSet$1.<init>:()V
        //     52: invokestatic  #82 // java.security.AccessController.doPrivileged:(Ljava/security/PrivilegedAction;)Ljava/lang/Object;
        //     55: checkcast  #25 // java.net.URL
        //     58: astore_2
        //     59: aload_2
        //     60: invokevirtual  #81 // java.net.URL.openStream:()Ljava/io/InputStream;
        //     63: astore_3
        //     64: aload_3
        //     65: invokevirtual  #59 // java.io.InputStream.read:()I
        //     68: pop
        //     69: aload_3
        //     70: invokevirtual  #58 // java.io.InputStream.close:()V
        //     73: goto  114 (offset +41)
        //     76: astore  4
        //     78: goto  114 (offset +36)
        //     81: astore  4
        //     83: aload  4
        //     85: putstatic  #49 // jnr.constants.ConstantSet.RESOURCE_READ_ERROR:Ljava/lang/Throwable;
        //     88: aload_3
        //     89: invokevirtual  #58 // java.io.InputStream.close:()V
        //     92: goto  114 (offset +22)
        //     95: astore  4
        //     97: goto  114 (offset +17)
        //    100: astore  5
        //    102: aload_3
        //    103: invokevirtual  #58 // java.io.InputStream.close:()V
        //    106: goto  111 (offset +5)
        //    109: astore  6
        //    111: aload  5
        //    113: athrow
        //    114: iconst_1
        //    115: istore_1
        //    116: goto  130 (offset +14)
        //    119: astore_2
        //    120: getstatic  #49 // jnr.constants.ConstantSet.RESOURCE_READ_ERROR:Ljava/lang/Throwable;
        //    123: ifnonnull  130 (offset +7)
        //    126: aload_2
        //    127: putstatic  #49 // jnr.constants.ConstantSet.RESOURCE_READ_ERROR:Ljava/lang/Throwable;
        //    130: iload_1
        //    131: putstatic  #47 // jnr.constants.ConstantSet.CAN_LOAD_RESOURCES:Z
        //    134: return
        //       Exception table:
        //         from 69 to 73 target 76 type java.lang.Exception
        //         from 64 to 69 target 81 type java.lang.Throwable
        //         from 88 to 92 target 95 type java.lang.Exception
        //         from 64 to 69 target 100 type any
        //         from 81 to 88 target 100 type any
        //         from 102 to 106 target 109 type java.lang.Exception
        //         from 100 to 102 target 100 type any
        //         from 45 to 116 target 119 type java.lang.Throwable
    }

  public static ConstantSet getConstantSet(String arg0) {
        ConstantSet var1 = ((ConstantSet) constantSets.get(arg0));
        return var1 == null ? loadConstantSet(arg0) : var1;
    }

  private static ConstantSet loadConstantSet(String arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #53 // jnr.constants.ConstantSet.lock:Ljava/lang/Object;
        //      3: dup
        //      4: astore_1
        //      5: monitorenter
        //      6: getstatic  #50 // jnr.constants.ConstantSet.constantSets:Ljava/util/concurrent/ConcurrentMap;
        //      9: aload_0
        //     10: invokeinterface  #105 // java.util.concurrent.ConcurrentMap.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     15: checkcast  #37 // jnr.constants.ConstantSet
        //     18: astore_2
        //     19: aload_2
        //     20: ifnonnull  97 (offset +77)
        //     23: aload_0
        //     24: invokestatic  #91 // jnr.constants.ConstantSet.getEnumClass:(Ljava/lang/String;)Ljava/lang/Class;
        //     27: astore_3
        //     28: aload_3
        //     29: ifnonnull  36 (offset +7)
        //     32: aconst_null
        //     33: aload_1
        //     34: monitorexit
        //     35: areturn
        //     36: ldc  #36 // jnr.constants.Constant
        //     38: aload_3
        //     39: invokevirtual  #65 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //     42: ifne  77 (offset +35)
        //     45: new  #11 // java.lang.ClassCastException
        //     48: dup
        //     49: new  #21 // java.lang.StringBuilder
        //     52: dup
        //     53: invokespecial  #77 // java.lang.StringBuilder.<init>:()V
        //     56: ldc  #6 // 'class for '
        //     58: invokevirtual  #78 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     61: aload_0
        //     62: invokevirtual  #78 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     65: ldc  #1 // ' does not implement Constant interface'
        //     67: invokevirtual  #78 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     70: invokevirtual  #79 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //     73: invokespecial  #66 // java.lang.ClassCastException.<init>:(Ljava/lang/String;)V
        //     76: athrow
        //     77: getstatic  #50 // jnr.constants.ConstantSet.constantSets:Ljava/util/concurrent/ConcurrentMap;
        //     80: aload_0
        //     81: new  #37 // jnr.constants.ConstantSet
        //     84: dup
        //     85: aload_3
        //     86: invokespecial  #88 // jnr.constants.ConstantSet.<init>:(Ljava/lang/Class;)V
        //     89: dup
        //     90: astore_2
        //     91: invokeinterface  #106 // java.util.concurrent.ConcurrentMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //     96: pop
        //     97: aload_2
        //     98: aload_1
        //     99: monitorexit
        //    100: areturn
        //    101: astore  4
        //    103: aload_1
        //    104: monitorexit
        //    105: aload  4
        //    107: athrow
        //       Exception table:
        //         from 6 to 35 target 101 type any
        //         from 36 to 100 target 101 type any
        //         from 101 to 105 target 101 type any
    }

  private static final Class getEnumClass(String arg0) {
        Class __stk1;
        String[] var1 = PlatformConstants.getPlatform().getPackagePrefixes();
        String[] var2 = var1;
        int var3 = var2.length;
        int var4 = 0;
        while (true) {
            int var7;
            if (var4 >= var3) {
                return null;
            } else {
                Object var5 = var2[var4];
                String var6 = new StringBuilder().append(((String) var5)).append(".").append(arg0).toString();
                var7 = 1;
                if (CAN_LOAD_RESOURCES) {
                    String var8 = new StringBuilder().append(var6.replace('.', '/')).append(".class").toString();
                    URL var9 = LOADER.getResource(var8);
                    if (var9 == null) {
                        var7 = 0;
                    }
                }
            }
            if (var7 == 0) {
                ++var4;
                continue;
            } else {
                try {
                    __stk1 = Class.forName(var6, true, LOADER).asSubclass(Enum.class);
                } catch (ClassNotFoundException e1) {
                    Throwable var8 = e1;
                }
            }
        }
        return __stk1;
    }

  private ConstantSet(Class arg0) { // было: <init>
        super();
        enumClass = arg0;
        constants = EnumSet.allOf(arg0);
        HashMap var2 = new HashMap();
        HashMap var3 = new HashMap();
        Iterator var4 = constants.iterator();
        while (var4.hasNext()) {
            Enum var5 = ((Enum) var4.next());
            if (var5 instanceof Constant) {
                Constant var6 = ((Constant) var5);
                var2.put(var5.name(), var6);
                var3.put(Long.valueOf(var6.longValue()), var6);
            }
            continue;
        }
        nameToConstant = Collections.unmodifiableMap(var2);
        valueToConstant = Collections.unmodifiableMap(var3);
    }

  public final Constant getConstant(String arg0) {
        return ((Constant) nameToConstant.get(arg0));
    }

  public Constant getConstant(long arg0) {
        return ((Constant) valueToConstant.get(Long.valueOf(arg0)));
    }

  public long getValue(String arg0) {
        Constant var2 = getConstant(arg0);
        return var2 == null ? 0L : var2.longValue();
    }

  public String getName(int arg0) {
        Constant var2 = getConstant(((long) arg0));
        return var2 == null ? "unknown" : var2.name();
    }

  private Long getLongField(String arg0, long arg1) {
        Long __stk1;
        try {
            Field var4 = enumClass.getField(arg0);
            __stk1 = ((Long) var4.get(enumClass));
        } catch (NoSuchFieldException e1) {
            Throwable var4 = e1;
            return Long.valueOf(arg1);
        } catch (RuntimeException e2) {
            Throwable var4 = e2;
            throw var4;
        } catch (Exception e3) {
            Throwable var4 = e3;
            throw new RuntimeException(var4);
        }
    }

  public long minValue() {
        if (minValue == null) {
            minValue = getLongField("MIN_VALUE", -2147483648L);
        }
        return ((long) minValue.intValue());
    }

  public long maxValue() {
        if (maxValue == null) {
            maxValue = getLongField("MAX_VALUE", 2147483647L);
        }
        return ((long) maxValue.intValue());
    }

  public Iterator iterator() {
        return new ConstantSet_ConstantIterator(this, constants);
    }

  public int size() {
        return constants.size();
    }

  public boolean contains(Object arg0) {
        return arg0 == null ? 0 : arg0.getClass().equals(enumClass);
    }

  public static void main(String[] arg0) {
        System.out.println(Errno.values().length);
    }

  static ClassLoader access$000() {
        return LOADER;
    }

}