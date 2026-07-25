// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.RemoteObject
package org.freedesktop.dbus;

public class RemoteObject {

    // ---- поля ----
  private final String busname;
  private final String objectpath;
  private final Class iface;
  private final boolean autostart;

  public RemoteObject(String arg0, String arg1, Class arg2, boolean arg3) { // было: <init>
        super();
        busname = arg0;
        objectpath = arg1;
        iface = arg2;
        autostart = arg3;
    }

  public boolean equals(Object arg0) {
        if (arg0 instanceof RemoteObject) {
            RemoteObject var2 = ((RemoteObject) arg0);
            if (var2.objectpath.equals(objectpath)) {
                if (null != busname) {
                    if (null == busname) {
                        if (null == var2.busname) {
                            if (null != iface) {
                                if (null == iface) {
                                    if (null == var2.iface) {
                                        return true;
                                    } else {
                                        if (var2.iface.equals(iface)) {
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    }
                                } else {
                                    if (null != var2.iface) {
                                        if (null == var2.iface) {
                                            return true;
                                        } else {
                                            if (var2.iface.equals(iface)) {
                                                return true;
                                            } else {
                                                return false;
                                            }
                                        }
                                    } else {
                                        return false;
                                    }
                                }
                            } else {
                                if (null == var2.iface) {
                                    if (null == iface) {
                                        if (null == var2.iface) {
                                            return true;
                                        } else {
                                            if (var2.iface.equals(iface)) {
                                                return true;
                                            } else {
                                                return false;
                                            }
                                        }
                                    } else {
                                        if (null != var2.iface) {
                                            if (null == var2.iface) {
                                                return true;
                                            } else {
                                                if (var2.iface.equals(iface)) {
                                                    return true;
                                                } else {
                                                    return false;
                                                }
                                            }
                                        } else {
                                            return false;
                                        }
                                    }
                                } else {
                                    return false;
                                }
                            }
                        } else {
                            if (var2.busname.equals(busname)) {
                                if (null != iface) {
                                    if (null == iface) {
                                        if (null == var2.iface) {
                                            return true;
                                        } else {
                                            if (var2.iface.equals(iface)) {
                                                return true;
                                            } else {
                                                return false;
                                            }
                                        }
                                    } else {
                                        if (null != var2.iface) {
                                            if (null == var2.iface) {
                                                return true;
                                            } else {
                                                if (var2.iface.equals(iface)) {
                                                    return true;
                                                } else {
                                                    return false;
                                                }
                                            }
                                        } else {
                                            return false;
                                        }
                                    }
                                } else {
                                    if (null == var2.iface) {
                                        if (null == iface) {
                                            if (null == var2.iface) {
                                                return true;
                                            } else {
                                                if (var2.iface.equals(iface)) {
                                                    return true;
                                                } else {
                                                    return false;
                                                }
                                            }
                                        } else {
                                            if (null != var2.iface) {
                                                if (null == var2.iface) {
                                                    return true;
                                                } else {
                                                    if (var2.iface.equals(iface)) {
                                                        return true;
                                                    } else {
                                                        return false;
                                                    }
                                                }
                                            } else {
                                                return false;
                                            }
                                        }
                                    } else {
                                        return false;
                                    }
                                }
                            } else {
                                return false;
                            }
                        }
                    } else {
                        if (null != var2.busname) {
                            if (null == var2.busname) {
                                if (null != iface) {
                                    if (null == iface) {
                                        if (null == var2.iface) {
                                            return true;
                                        } else {
                                            if (var2.iface.equals(iface)) {
                                                return true;
                                            } else {
                                                return false;
                                            }
                                        }
                                    } else {
                                        if (null != var2.iface) {
                                            if (null == var2.iface) {
                                                return true;
                                            } else {
                                                if (var2.iface.equals(iface)) {
                                                    return true;
                                                } else {
                                                    return false;
                                                }
                                            }
                                        } else {
                                            return false;
                                        }
                                    }
                                } else {
                                    if (null == var2.iface) {
                                        if (null == iface) {
                                            if (null == var2.iface) {
                                                return true;
                                            } else {
                                                if (var2.iface.equals(iface)) {
                                                    return true;
                                                } else {
                                                    return false;
                                                }
                                            }
                                        } else {
                                            if (null != var2.iface) {
                                                if (null == var2.iface) {
                                                    return true;
                                                } else {
                                                    if (var2.iface.equals(iface)) {
                                                        return true;
                                                    } else {
                                                        return false;
                                                    }
                                                }
                                            } else {
                                                return false;
                                            }
                                        }
                                    } else {
                                        return false;
                                    }
                                }
                            } else {
                                if (var2.busname.equals(busname)) {
                                    if (null != iface) {
                                        if (null == iface) {
                                            if (null == var2.iface) {
                                                return true;
                                            } else {
                                                if (var2.iface.equals(iface)) {
                                                    return true;
                                                } else {
                                                    return false;
                                                }
                                            }
                                        } else {
                                            if (null != var2.iface) {
                                                if (null == var2.iface) {
                                                    return true;
                                                } else {
                                                    if (var2.iface.equals(iface)) {
                                                        return true;
                                                    } else {
                                                        return false;
                                                    }
                                                }
                                            } else {
                                                return false;
                                            }
                                        }
                                    } else {
                                        if (null == var2.iface) {
                                            if (null == iface) {
                                                if (null == var2.iface) {
                                                    return true;
                                                } else {
                                                    if (var2.iface.equals(iface)) {
                                                        return true;
                                                    } else {
                                                        return false;
                                                    }
                                                }
                                            } else {
                                                if (null != var2.iface) {
                                                    if (null == var2.iface) {
                                                        return true;
                                                    } else {
                                                        if (var2.iface.equals(iface)) {
                                                            return true;
                                                        } else {
                                                            return false;
                                                        }
                                                    }
                                                } else {
                                                    return false;
                                                }
                                            }
                                        } else {
                                            return false;
                                        }
                                    }
                                } else {
                                    return false;
                                }
                            }
                        } else {
                            return false;
                        }
                    }
                } else {
                    if (null == var2.busname) {
                        if (null == busname) {
                            if (null == var2.busname) {
                                if (null != iface) {
                                    if (null == iface) {
                                        if (null == var2.iface) {
                                            return true;
                                        } else {
                                            if (var2.iface.equals(iface)) {
                                                return true;
                                            } else {
                                                return false;
                                            }
                                        }
                                    } else {
                                        if (null != var2.iface) {
                                            if (null == var2.iface) {
                                                return true;
                                            } else {
                                                if (var2.iface.equals(iface)) {
                                                    return true;
                                                } else {
                                                    return false;
                                                }
                                            }
                                        } else {
                                            return false;
                                        }
                                    }
                                } else {
                                    if (null == var2.iface) {
                                        if (null == iface) {
                                            if (null == var2.iface) {
                                                return true;
                                            } else {
                                                if (var2.iface.equals(iface)) {
                                                    return true;
                                                } else {
                                                    return false;
                                                }
                                            }
                                        } else {
                                            if (null != var2.iface) {
                                                if (null == var2.iface) {
                                                    return true;
                                                } else {
                                                    if (var2.iface.equals(iface)) {
                                                        return true;
                                                    } else {
                                                        return false;
                                                    }
                                                }
                                            } else {
                                                return false;
                                            }
                                        }
                                    } else {
                                        return false;
                                    }
                                }
                            } else {
                                if (var2.busname.equals(busname)) {
                                    if (null != iface) {
                                        if (null == iface) {
                                            if (null == var2.iface) {
                                                return true;
                                            } else {
                                                if (var2.iface.equals(iface)) {
                                                    return true;
                                                } else {
                                                    return false;
                                                }
                                            }
                                        } else {
                                            if (null != var2.iface) {
                                                if (null == var2.iface) {
                                                    return true;
                                                } else {
                                                    if (var2.iface.equals(iface)) {
                                                        return true;
                                                    } else {
                                                        return false;
                                                    }
                                                }
                                            } else {
                                                return false;
                                            }
                                        }
                                    } else {
                                        if (null == var2.iface) {
                                            if (null == iface) {
                                                if (null == var2.iface) {
                                                    return true;
                                                } else {
                                                    if (var2.iface.equals(iface)) {
                                                        return true;
                                                    } else {
                                                        return false;
                                                    }
                                                }
                                            } else {
                                                if (null != var2.iface) {
                                                    if (null == var2.iface) {
                                                        return true;
                                                    } else {
                                                        if (var2.iface.equals(iface)) {
                                                            return true;
                                                        } else {
                                                            return false;
                                                        }
                                                    }
                                                } else {
                                                    return false;
                                                }
                                            }
                                        } else {
                                            return false;
                                        }
                                    }
                                } else {
                                    return false;
                                }
                            }
                        } else {
                            if (null != var2.busname) {
                                if (null == var2.busname) {
                                    if (null != iface) {
                                        if (null == iface) {
                                            if (null == var2.iface) {
                                                return true;
                                            } else {
                                                if (var2.iface.equals(iface)) {
                                                    return true;
                                                } else {
                                                    return false;
                                                }
                                            }
                                        } else {
                                            if (null != var2.iface) {
                                                if (null == var2.iface) {
                                                    return true;
                                                } else {
                                                    if (var2.iface.equals(iface)) {
                                                        return true;
                                                    } else {
                                                        return false;
                                                    }
                                                }
                                            } else {
                                                return false;
                                            }
                                        }
                                    } else {
                                        if (null == var2.iface) {
                                            if (null == iface) {
                                                if (null == var2.iface) {
                                                    return true;
                                                } else {
                                                    if (var2.iface.equals(iface)) {
                                                        return true;
                                                    } else {
                                                        return false;
                                                    }
                                                }
                                            } else {
                                                if (null != var2.iface) {
                                                    if (null == var2.iface) {
                                                        return true;
                                                    } else {
                                                        if (var2.iface.equals(iface)) {
                                                            return true;
                                                        } else {
                                                            return false;
                                                        }
                                                    }
                                                } else {
                                                    return false;
                                                }
                                            }
                                        } else {
                                            return false;
                                        }
                                    }
                                } else {
                                    if (var2.busname.equals(busname)) {
                                        if (null != iface) {
                                            if (null == iface) {
                                                if (null == var2.iface) {
                                                    return true;
                                                } else {
                                                    if (var2.iface.equals(iface)) {
                                                        return true;
                                                    } else {
                                                        return false;
                                                    }
                                                }
                                            } else {
                                                if (null != var2.iface) {
                                                    if (null == var2.iface) {
                                                        return true;
                                                    } else {
                                                        if (var2.iface.equals(iface)) {
                                                            return true;
                                                        } else {
                                                            return false;
                                                        }
                                                    }
                                                } else {
                                                    return false;
                                                }
                                            }
                                        } else {
                                            if (null == var2.iface) {
                                                if (null == iface) {
                                                    if (null == var2.iface) {
                                                        return true;
                                                    } else {
                                                        if (var2.iface.equals(iface)) {
                                                            return true;
                                                        } else {
                                                            return false;
                                                        }
                                                    }
                                                } else {
                                                    if (null != var2.iface) {
                                                        if (null == var2.iface) {
                                                            return true;
                                                        } else {
                                                            if (var2.iface.equals(iface)) {
                                                                return true;
                                                            } else {
                                                                return false;
                                                            }
                                                        }
                                                    } else {
                                                        return false;
                                                    }
                                                }
                                            } else {
                                                return false;
                                            }
                                        }
                                    } else {
                                        return false;
                                    }
                                }
                            } else {
                                return false;
                            }
                        }
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

  public int hashCode() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aconst_null
        //      1: aload_0
        //      2: getfield  #9 // org.freedesktop.dbus.RemoteObject.busname:Ljava/lang/String;
        //      5: if_acmpne  12 (offset +7)
        //      8: iconst_0
        //      9: goto  19 (offset +10)
        //     12: aload_0
        //     13: getfield  #9 // org.freedesktop.dbus.RemoteObject.busname:Ljava/lang/String;
        //     16: invokevirtual  #16 // java.lang.String.hashCode:()I
        //     19: aload_0
        //     20: getfield  #11 // org.freedesktop.dbus.RemoteObject.objectpath:Ljava/lang/String;
        //     23: invokevirtual  #16 // java.lang.String.hashCode:()I
        //     26: iadd
        //     27: aconst_null
        //     28: aload_0
        //     29: getfield  #10 // org.freedesktop.dbus.RemoteObject.iface:Ljava/lang/Class;
        //     32: if_acmpne  39 (offset +7)
        //     35: iconst_0
        //     36: goto  46 (offset +10)
        //     39: aload_0
        //     40: getfield  #10 // org.freedesktop.dbus.RemoteObject.iface:Ljava/lang/Class;
        //     43: invokevirtual  #14 // java.lang.Object.hashCode:()I
        //     46: iadd
        //     47: ireturn
    }

  public boolean isAutostart() {
        return autostart;
    }

  public String getBusName() {
        return busname;
    }

  public String getObjectPath() {
        return objectpath;
    }

  public Class getInterface() {
        return iface;
    }

  public String toString() {
        return busname + ":" + objectpath + ":" + String.valueOf(iface);
    }

}