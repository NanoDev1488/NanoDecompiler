// исходный (обфусцированный) внутренний класс: jnr.constants.platform.ConstantResolver
package jnr.constants.platform;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import jnr.constants.Constant;
import jnr.constants.ConstantSet;

class ConstantResolver {

    // ---- поля ----
  public static final String __UNKNOWN_CONSTANT__ = "__UNKNOWN_CONSTANT__";
  private final Object modLock;
  private final Class enumType;
  private final Map reverseLookupMap;
  private final AtomicLong nextUnknown;
  private final boolean bitmask;
  private Constant[] cache;
  private volatile Enum[] valueCache;
  private volatile int cacheGuard;
  private volatile ConstantSet constants;

  private ConstantResolver(Class arg0) { // было: <init>
        this(arg0, -2147483648, -2147482648, false);
    }

  private ConstantResolver(Class arg0, int arg1, int arg2, boolean arg3) { // было: <init>
        super();
        modLock = new Object();
        reverseLookupMap = new ConcurrentHashMap();
        cache = null;
        valueCache = null;
        cacheGuard = 0;
        enumType = arg0;
        nextUnknown = new AtomicLong(((long) arg1));
        bitmask = arg3;
    }

  static ConstantResolver getResolver(Class arg0) {
        return new ConstantResolver(arg0);
    }

  static ConstantResolver getResolver(Class arg0, int arg1, int arg2) {
        return new ConstantResolver(arg0, arg1, arg2, false);
    }

  static ConstantResolver getBitmaskResolver(Class arg0) {
        return new ConstantResolver(arg0, 0, -2147483648, true);
    }

  private Constant getConstant(Enum arg0) {
        if (cacheGuard == 0) {
            return lookupAndCacheConstant(arg0);
        } else {
            Object var2 = cache[arg0.ordinal()];
            if (var2 == null) {
                return lookupAndCacheConstant(arg0);
            } else {
                return ((Constant) var2);
            }
        }
    }

  private Constant lookupAndCacheConstant(Enum arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #32 // jnr.constants.platform.ConstantResolver.modLock:Ljava/lang/Object;
        //      4: dup
        //      5: astore_2
        //      6: monitorenter
        //      7: aload_0
        //      8: getfield  #29 // jnr.constants.platform.ConstantResolver.cacheGuard:I
        //     11: ifeq  32 (offset +21)
        //     14: aload_0
        //     15: getfield  #28 // jnr.constants.platform.ConstantResolver.cache:[Ljnr/constants/Constant;
        //     18: aload_1
        //     19: invokevirtual  #38 // java.lang.Enum.ordinal:()I
        //     22: aaload
        //     23: dup
        //     24: astore_3
        //     25: ifnull  32 (offset +7)
        //     28: aload_3
        //     29: aload_2
        //     30: monitorexit
        //     31: areturn
        //     32: aload_0
        //     33: getfield  #31 // jnr.constants.platform.ConstantResolver.enumType:Ljava/lang/Class;
        //     36: invokestatic  #50 // java.util.EnumSet.allOf:(Ljava/lang/Class;)Ljava/util/EnumSet;
        //     39: astore  4
        //     41: aload_0
        //     42: invokespecial  #62 // jnr.constants.platform.ConstantResolver.getConstants:()Ljnr/constants/ConstantSet;
        //     45: astore  5
        //     47: aload_0
        //     48: getfield  #28 // jnr.constants.platform.ConstantResolver.cache:[Ljnr/constants/Constant;
        //     51: ifnonnull  66 (offset +15)
        //     54: aload_0
        //     55: aload  4
        //     57: invokevirtual  #52 // java.util.EnumSet.size:()I
        //     60: anewarray  #19 // jnr.constants.Constant
        //     63: putfield  #28 // jnr.constants.platform.ConstantResolver.cache:[Ljnr/constants/Constant;
        //     66: lconst_0
        //     67: lstore  6
        //     69: lconst_0
        //     70: lstore  8
        //     72: aload  4
        //     74: invokevirtual  #51 // java.util.EnumSet.iterator:()Ljava/util/Iterator;
        //     77: astore  10
        //     79: aload  10
        //     81: invokeinterface  #65 // java.util.Iterator.hasNext:()Z, count 1
        //     86: ifeq  208 (offset +122)
        //     89: aload  10
        //     91: invokeinterface  #66 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //     96: checkcast  #7 // java.lang.Enum
        //     99: astore  11
        //    101: aload  5
        //    103: aload  11
        //    105: invokevirtual  #37 // java.lang.Enum.name:()Ljava/lang/String;
        //    108: invokevirtual  #57 // jnr.constants.ConstantSet.getConstant:(Ljava/lang/String;)Ljnr/constants/Constant;
        //    111: astore_3
        //    112: aload_3
        //    113: ifnonnull  176 (offset +63)
        //    116: aload_0
        //    117: getfield  #27 // jnr.constants.platform.ConstantResolver.bitmask:Z
        //    120: ifeq  152 (offset +32)
        //    123: lload  8
        //    125: lconst_1
        //    126: aload  11
        //    128: invokevirtual  #38 // java.lang.Enum.ordinal:()I
        //    131: lshl
        //    132: lor
        //    133: lstore  8
        //    135: new  #22 // jnr.constants.platform.ConstantResolver$UnknownConstant
        //    138: dup
        //    139: lconst_0
        //    140: aload  11
        //    142: invokevirtual  #37 // java.lang.Enum.name:()Ljava/lang/String;
        //    145: invokespecial  #64 // jnr.constants.platform.ConstantResolver$UnknownConstant.<init>:(JLjava/lang/String;)V
        //    148: astore_3
        //    149: goto  194 (offset +45)
        //    152: new  #22 // jnr.constants.platform.ConstantResolver$UnknownConstant
        //    155: dup
        //    156: aload_0
        //    157: getfield  #33 // jnr.constants.platform.ConstantResolver.nextUnknown:Ljava/util/concurrent/atomic/AtomicLong;
        //    160: lconst_1
        //    161: invokevirtual  #55 // java.util.concurrent.atomic.AtomicLong.getAndAdd:(J)J
        //    164: aload  11
        //    166: invokevirtual  #37 // java.lang.Enum.name:()Ljava/lang/String;
        //    169: invokespecial  #64 // jnr.constants.platform.ConstantResolver$UnknownConstant.<init>:(JLjava/lang/String;)V
        //    172: astore_3
        //    173: goto  194 (offset +21)
        //    176: aload_0
        //    177: getfield  #27 // jnr.constants.platform.ConstantResolver.bitmask:Z
        //    180: ifeq  194 (offset +14)
        //    183: lload  6
        //    185: aload_3
        //    186: invokeinterface  #71 // jnr.constants.Constant.longValue:()J, count 1
        //    191: lor
        //    192: lstore  6
        //    194: aload_0
        //    195: getfield  #28 // jnr.constants.platform.ConstantResolver.cache:[Ljnr/constants/Constant;
        //    198: aload  11
        //    200: invokevirtual  #38 // java.lang.Enum.ordinal:()I
        //    203: aload_3
        //    204: aastore
        //    205: goto  79 (offset -126)
        //    208: aload_0
        //    209: getfield  #27 // jnr.constants.platform.ConstantResolver.bitmask:Z
        //    212: ifeq  311 (offset +99)
        //    215: lconst_0
        //    216: lstore  10
        //    218: lload  8
        //    220: invokestatic  #40 // java.lang.Long.lowestOneBit:(J)J
        //    223: dup2
        //    224: lstore  10
        //    226: lconst_0
        //    227: lcmp
        //    228: ifeq  311 (offset +83)
        //    231: lload  10
        //    233: invokestatic  #41 // java.lang.Long.numberOfTrailingZeros:(J)I
        //    236: istore  12
        //    238: lload  6
        //    240: ldc2_w  #23 // -1L
        //    243: lxor
        //    244: invokestatic  #40 // java.lang.Long.lowestOneBit:(J)J
        //    247: invokestatic  #41 // java.lang.Long.numberOfTrailingZeros:(J)I
        //    250: istore  13
        //    252: iconst_1
        //    253: iload  13
        //    255: ishl
        //    256: istore  14
        //    258: aload_0
        //    259: getfield  #28 // jnr.constants.platform.ConstantResolver.cache:[Ljnr/constants/Constant;
        //    262: iload  12
        //    264: new  #22 // jnr.constants.platform.ConstantResolver$UnknownConstant
        //    267: dup
        //    268: iload  14
        //    270: i2l
        //    271: aload_0
        //    272: getfield  #28 // jnr.constants.platform.ConstantResolver.cache:[Ljnr/constants/Constant;
        //    275: iload  12
        //    277: aaload
        //    278: invokeinterface  #72 // jnr.constants.Constant.name:()Ljava/lang/String;, count 1
        //    283: invokespecial  #64 // jnr.constants.platform.ConstantResolver$UnknownConstant.<init>:(JLjava/lang/String;)V
        //    286: aastore
        //    287: lload  6
        //    289: iload  14
        //    291: i2l
        //    292: lor
        //    293: lstore  6
        //    295: lload  8
        //    297: lconst_1
        //    298: iload  12
        //    300: lshl
        //    301: ldc2_w  #23 // -1L
        //    304: lxor
        //    305: land
        //    306: lstore  8
        //    308: goto  218 (offset -90)
        //    311: aload_0
        //    312: iconst_1
        //    313: putfield  #29 // jnr.constants.platform.ConstantResolver.cacheGuard:I
        //    316: aload_0
        //    317: getfield  #28 // jnr.constants.platform.ConstantResolver.cache:[Ljnr/constants/Constant;
        //    320: aload_1
        //    321: invokevirtual  #38 // java.lang.Enum.ordinal:()I
        //    324: aaload
        //    325: aload_2
        //    326: monitorexit
        //    327: areturn
        //    328: astore  15
        //    330: aload_2
        //    331: monitorexit
        //    332: aload  15
        //    334: athrow
        //       Exception table:
        //         from 7 to 31 target 328 type any
        //         from 32 to 327 target 328 type any
        //         from 328 to 332 target 328 type any
    }

  final int intValue(Enum arg0) {
        return getConstant(arg0).intValue();
    }

  final long longValue(Enum arg0) {
        return getConstant(arg0).longValue();
    }

  final String description(Enum arg0) {
        return getConstant(arg0).toString();
    }

  final boolean defined(Enum arg0) {
        return getConstant(arg0).defined();
    }

  final Enum valueOf(long arg0) {
        Object __stk1;
        if (arg0 < 0L) {
            Enum var3 = ((Enum) reverseLookupMap.get(Long.valueOf(arg0)));
            if (var3 != null) {
                return ((Enum) var3);
            }
            Constant var4 = getConstants().getConstant(arg0);
            if (var4 == null) {
                return Enum.valueOf(enumType, "__UNKNOWN_CONSTANT__");
            }
            try {
                var3 = Enum.valueOf(enumType, var4.name());
                reverseLookupMap.put(Long.valueOf(arg0), var3);
                if (var4.intValue() < 0) {
                    __stk1 = var3;
                }
                if (var4.intValue() >= 256) {
                    __stk1 = var3;
                }
                Enum[] var5 = valueCache;
                if (var5 != null) {
                    var5[var4.intValue()] = var3;
                    valueCache = var5;
                    __stk1 = var3;
                } else {
                    var5 = ((Enum[]) Array.newInstance(enumType, 256));
                    var5[var4.intValue()] = var3;
                    valueCache = var5;
                    __stk1 = var3;
                }
            } catch (IllegalArgumentException e1) {
                Throwable var5 = e1;
            }
        } else {
            if (arg0 >= 256L) {
                Enum var3 = ((Enum) reverseLookupMap.get(Long.valueOf(arg0)));
                if (var3 != null) {
                    return ((Enum) var3);
                }
                Constant var4 = getConstants().getConstant(arg0);
                if (var4 == null) {
                    return Enum.valueOf(enumType, "__UNKNOWN_CONSTANT__");
                }
                var3 = Enum.valueOf(enumType, var4.name());
                reverseLookupMap.put(Long.valueOf(arg0), var3);
                if (var4.intValue() < 0) {
                    __stk1 = var3;
                    return ((Enum) __stk1);
                }
                if (var4.intValue() >= 256) {
                    __stk1 = var3;
                    return ((Enum) __stk1);
                }
                Enum[] var5 = valueCache;
                if (var5 != null) {
                    var5[var4.intValue()] = var3;
                    valueCache = var5;
                    __stk1 = var3;
                    return ((Enum) __stk1);
                } else {
                    var5 = ((Enum[]) Array.newInstance(enumType, 256));
                    var5[var4.intValue()] = var3;
                    valueCache = var5;
                    __stk1 = var3;
                    return ((Enum) __stk1);
                }
            } else {
                if (valueCache == null) {
                    Enum var3 = ((Enum) reverseLookupMap.get(Long.valueOf(arg0)));
                    if (var3 != null) {
                        return ((Enum) var3);
                    }
                    Constant var4 = getConstants().getConstant(arg0);
                    if (var4 == null) {
                        return Enum.valueOf(enumType, "__UNKNOWN_CONSTANT__");
                    }
                    var3 = Enum.valueOf(enumType, var4.name());
                    reverseLookupMap.put(Long.valueOf(arg0), var3);
                    if (var4.intValue() < 0) {
                        __stk1 = var3;
                        return ((Enum) __stk1);
                    }
                    if (var4.intValue() >= 256) {
                        __stk1 = var3;
                        return ((Enum) __stk1);
                    }
                    Enum[] var5 = valueCache;
                    if (var5 != null) {
                        var5[var4.intValue()] = var3;
                        valueCache = var5;
                        __stk1 = var3;
                        return ((Enum) __stk1);
                    } else {
                        var5 = ((Enum[]) Array.newInstance(enumType, 256));
                        var5[var4.intValue()] = var3;
                        valueCache = var5;
                        __stk1 = var3;
                        return ((Enum) __stk1);
                    }
                } else {
                    Object var3 = valueCache[((int) arg0)];
                    if (var3 != null) {
                        return ((Enum) var3);
                    }
                    var3 = ((Enum) reverseLookupMap.get(Long.valueOf(arg0)));
                    if (var3 != null) {
                        return ((Enum) var3);
                    }
                    Constant var4 = getConstants().getConstant(arg0);
                    if (var4 == null) {
                        return Enum.valueOf(enumType, "__UNKNOWN_CONSTANT__");
                    }
                    var3 = Enum.valueOf(enumType, var4.name());
                    reverseLookupMap.put(Long.valueOf(arg0), var3);
                    if (var4.intValue() < 0) {
                        __stk1 = var3;
                        return ((Enum) __stk1);
                    }
                    if (var4.intValue() >= 256) {
                        __stk1 = var3;
                        return ((Enum) __stk1);
                    }
                    Enum[] var5 = valueCache;
                    if (var5 != null) {
                        var5[var4.intValue()] = var3;
                        valueCache = var5;
                        __stk1 = var3;
                        return ((Enum) __stk1);
                    } else {
                        var5 = ((Enum[]) Array.newInstance(enumType, 256));
                        var5[var4.intValue()] = var3;
                        valueCache = var5;
                        __stk1 = var3;
                        return ((Enum) __stk1);
                    }
                }
            }
        }
    }

  private ConstantSet getConstants() {
        if (constants != null) {
            return constants;
        } else {
            constants = ConstantSet.getConstantSet(enumType.getSimpleName());
            if (constants != null) {
                return constants;
            } else {
                throw new RuntimeException(new StringBuilder().append("Could not load platform constants for ").append(enumType.getSimpleName()).toString());
            }
        }
    }

}