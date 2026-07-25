// исходный (обфусцированный) внутренний класс: jnr.ffi.util.AnnotationProperty
package jnr.ffi.util;

import java.util.Arrays;

final class AnnotationProperty {

    // ---- поля ----
  private final String name;
  private final Class type;
  private Object value;

  public AnnotationProperty(String arg0, Class arg1) { // было: <init>
        super();
        name = arg0;
        type = arg1;
    }

  public String getName() {
        return name;
    }

  public Class getType() {
        return type;
    }

  public Object getValue() {
        return value;
    }

  public void setValue(Object arg0) {
        if (arg0 == null) {
            value = arg0;
            return;
        } else {
            if (type.isAssignableFrom(arg0.getClass())) {
                value = arg0;
                return;
            } else {
                if (type != Boolean.TYPE) {
                    if (type != Byte.TYPE) {
                        if (type != Character.TYPE) {
                            if (type != Double.TYPE) {
                                if (type != Float.TYPE) {
                                    if (type != Integer.TYPE) {
                                        if (type != Long.TYPE) {
                                            if (type != Short.TYPE) {
                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                            } else {
                                                if (arg0.getClass() == Short.class) {
                                                    value = arg0;
                                                    return;
                                                } else {
                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                }
                                            }
                                        } else {
                                            if (arg0.getClass() == Long.class) {
                                                value = arg0;
                                                return;
                                            } else {
                                                if (type != Short.TYPE) {
                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                } else {
                                                    if (arg0.getClass() == Short.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        if (arg0.getClass() == Integer.class) {
                                            value = arg0;
                                            return;
                                        } else {
                                            if (type != Long.TYPE) {
                                                if (type != Short.TYPE) {
                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                } else {
                                                    if (arg0.getClass() == Short.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    }
                                                }
                                            } else {
                                                if (arg0.getClass() == Long.class) {
                                                    value = arg0;
                                                    return;
                                                } else {
                                                    if (type != Short.TYPE) {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    } else {
                                                        if (arg0.getClass() == Short.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (arg0.getClass() == Float.class) {
                                        value = arg0;
                                        return;
                                    } else {
                                        if (type != Integer.TYPE) {
                                            if (type != Long.TYPE) {
                                                if (type != Short.TYPE) {
                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                } else {
                                                    if (arg0.getClass() == Short.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    }
                                                }
                                            } else {
                                                if (arg0.getClass() == Long.class) {
                                                    value = arg0;
                                                    return;
                                                } else {
                                                    if (type != Short.TYPE) {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    } else {
                                                        if (arg0.getClass() == Short.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            if (arg0.getClass() == Integer.class) {
                                                value = arg0;
                                                return;
                                            } else {
                                                if (type != Long.TYPE) {
                                                    if (type != Short.TYPE) {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    } else {
                                                        if (arg0.getClass() == Short.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Long.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (arg0.getClass() == Double.class) {
                                    value = arg0;
                                    return;
                                } else {
                                    if (type != Float.TYPE) {
                                        if (type != Integer.TYPE) {
                                            if (type != Long.TYPE) {
                                                if (type != Short.TYPE) {
                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                } else {
                                                    if (arg0.getClass() == Short.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    }
                                                }
                                            } else {
                                                if (arg0.getClass() == Long.class) {
                                                    value = arg0;
                                                    return;
                                                } else {
                                                    if (type != Short.TYPE) {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    } else {
                                                        if (arg0.getClass() == Short.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            if (arg0.getClass() == Integer.class) {
                                                value = arg0;
                                                return;
                                            } else {
                                                if (type != Long.TYPE) {
                                                    if (type != Short.TYPE) {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    } else {
                                                        if (arg0.getClass() == Short.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Long.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        if (arg0.getClass() == Float.class) {
                                            value = arg0;
                                            return;
                                        } else {
                                            if (type != Integer.TYPE) {
                                                if (type != Long.TYPE) {
                                                    if (type != Short.TYPE) {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    } else {
                                                        if (arg0.getClass() == Short.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Long.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (arg0.getClass() == Integer.class) {
                                                    value = arg0;
                                                    return;
                                                } else {
                                                    if (type != Long.TYPE) {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    } else {
                                                        if (arg0.getClass() == Long.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (arg0.getClass() == Character.class) {
                                value = arg0;
                                return;
                            } else {
                                if (type != Double.TYPE) {
                                    if (type != Float.TYPE) {
                                        if (type != Integer.TYPE) {
                                            if (type != Long.TYPE) {
                                                if (type != Short.TYPE) {
                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                } else {
                                                    if (arg0.getClass() == Short.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    }
                                                }
                                            } else {
                                                if (arg0.getClass() == Long.class) {
                                                    value = arg0;
                                                    return;
                                                } else {
                                                    if (type != Short.TYPE) {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    } else {
                                                        if (arg0.getClass() == Short.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            if (arg0.getClass() == Integer.class) {
                                                value = arg0;
                                                return;
                                            } else {
                                                if (type != Long.TYPE) {
                                                    if (type != Short.TYPE) {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    } else {
                                                        if (arg0.getClass() == Short.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Long.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        if (arg0.getClass() == Float.class) {
                                            value = arg0;
                                            return;
                                        } else {
                                            if (type != Integer.TYPE) {
                                                if (type != Long.TYPE) {
                                                    if (type != Short.TYPE) {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    } else {
                                                        if (arg0.getClass() == Short.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Long.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (arg0.getClass() == Integer.class) {
                                                    value = arg0;
                                                    return;
                                                } else {
                                                    if (type != Long.TYPE) {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    } else {
                                                        if (arg0.getClass() == Long.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (arg0.getClass() == Double.class) {
                                        value = arg0;
                                        return;
                                    } else {
                                        if (type != Float.TYPE) {
                                            if (type != Integer.TYPE) {
                                                if (type != Long.TYPE) {
                                                    if (type != Short.TYPE) {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    } else {
                                                        if (arg0.getClass() == Short.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Long.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (arg0.getClass() == Integer.class) {
                                                    value = arg0;
                                                    return;
                                                } else {
                                                    if (type != Long.TYPE) {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    } else {
                                                        if (arg0.getClass() == Long.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            if (arg0.getClass() == Float.class) {
                                                value = arg0;
                                                return;
                                            } else {
                                                if (type != Integer.TYPE) {
                                                    if (type != Long.TYPE) {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    } else {
                                                        if (arg0.getClass() == Long.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Integer.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Long.TYPE) {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        } else {
                                                            if (arg0.getClass() == Long.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                if (type != Short.TYPE) {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                } else {
                                                                    if (arg0.getClass() == Short.class) {
                                                                        value = arg0;
                                                                        return;
                                                                    } else {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (arg0.getClass() == Byte.class) {
                            value = arg0;
                            return;
                        } else {
                            if (type != Character.TYPE) {
                                if (type != Double.TYPE) {
                                    if (type != Float.TYPE) {
                                        if (type != Integer.TYPE) {
                                            if (type != Long.TYPE) {
                                                if (type != Short.TYPE) {
                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                } else {
                                                    if (arg0.getClass() == Short.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    }
                                                }
                                            } else {
                                                if (arg0.getClass() == Long.class) {
                                                    value = arg0;
                                                    return;
                                                } else {
                                                    if (type != Short.TYPE) {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    } else {
                                                        if (arg0.getClass() == Short.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            if (arg0.getClass() == Integer.class) {
                                                value = arg0;
                                                return;
                                            } else {
                                                if (type != Long.TYPE) {
                                                    if (type != Short.TYPE) {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    } else {
                                                        if (arg0.getClass() == Short.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Long.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        if (arg0.getClass() == Float.class) {
                                            value = arg0;
                                            return;
                                        } else {
                                            if (type != Integer.TYPE) {
                                                if (type != Long.TYPE) {
                                                    if (type != Short.TYPE) {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    } else {
                                                        if (arg0.getClass() == Short.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Long.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (arg0.getClass() == Integer.class) {
                                                    value = arg0;
                                                    return;
                                                } else {
                                                    if (type != Long.TYPE) {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    } else {
                                                        if (arg0.getClass() == Long.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (arg0.getClass() == Double.class) {
                                        value = arg0;
                                        return;
                                    } else {
                                        if (type != Float.TYPE) {
                                            if (type != Integer.TYPE) {
                                                if (type != Long.TYPE) {
                                                    if (type != Short.TYPE) {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    } else {
                                                        if (arg0.getClass() == Short.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Long.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (arg0.getClass() == Integer.class) {
                                                    value = arg0;
                                                    return;
                                                } else {
                                                    if (type != Long.TYPE) {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    } else {
                                                        if (arg0.getClass() == Long.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            if (arg0.getClass() == Float.class) {
                                                value = arg0;
                                                return;
                                            } else {
                                                if (type != Integer.TYPE) {
                                                    if (type != Long.TYPE) {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    } else {
                                                        if (arg0.getClass() == Long.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Integer.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Long.TYPE) {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        } else {
                                                            if (arg0.getClass() == Long.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                if (type != Short.TYPE) {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                } else {
                                                                    if (arg0.getClass() == Short.class) {
                                                                        value = arg0;
                                                                        return;
                                                                    } else {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (arg0.getClass() == Character.class) {
                                    value = arg0;
                                    return;
                                } else {
                                    if (type != Double.TYPE) {
                                        if (type != Float.TYPE) {
                                            if (type != Integer.TYPE) {
                                                if (type != Long.TYPE) {
                                                    if (type != Short.TYPE) {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    } else {
                                                        if (arg0.getClass() == Short.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Long.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (arg0.getClass() == Integer.class) {
                                                    value = arg0;
                                                    return;
                                                } else {
                                                    if (type != Long.TYPE) {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    } else {
                                                        if (arg0.getClass() == Long.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            if (arg0.getClass() == Float.class) {
                                                value = arg0;
                                                return;
                                            } else {
                                                if (type != Integer.TYPE) {
                                                    if (type != Long.TYPE) {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    } else {
                                                        if (arg0.getClass() == Long.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Integer.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Long.TYPE) {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        } else {
                                                            if (arg0.getClass() == Long.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                if (type != Short.TYPE) {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                } else {
                                                                    if (arg0.getClass() == Short.class) {
                                                                        value = arg0;
                                                                        return;
                                                                    } else {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        if (arg0.getClass() == Double.class) {
                                            value = arg0;
                                            return;
                                        } else {
                                            if (type != Float.TYPE) {
                                                if (type != Integer.TYPE) {
                                                    if (type != Long.TYPE) {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    } else {
                                                        if (arg0.getClass() == Long.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Integer.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Long.TYPE) {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        } else {
                                                            if (arg0.getClass() == Long.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                if (type != Short.TYPE) {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                } else {
                                                                    if (arg0.getClass() == Short.class) {
                                                                        value = arg0;
                                                                        return;
                                                                    } else {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (arg0.getClass() == Float.class) {
                                                    value = arg0;
                                                    return;
                                                } else {
                                                    if (type != Integer.TYPE) {
                                                        if (type != Long.TYPE) {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        } else {
                                                            if (arg0.getClass() == Long.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                if (type != Short.TYPE) {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                } else {
                                                                    if (arg0.getClass() == Short.class) {
                                                                        value = arg0;
                                                                        return;
                                                                    } else {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        if (arg0.getClass() == Integer.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            if (type != Long.TYPE) {
                                                                if (type != Short.TYPE) {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                } else {
                                                                    if (arg0.getClass() == Short.class) {
                                                                        value = arg0;
                                                                        return;
                                                                    } else {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    }
                                                                }
                                                            } else {
                                                                if (arg0.getClass() == Long.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    if (type != Short.TYPE) {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    } else {
                                                                        if (arg0.getClass() == Short.class) {
                                                                            value = arg0;
                                                                            return;
                                                                        } else {
                                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (arg0.getClass() == Boolean.class) {
                        value = arg0;
                        return;
                    } else {
                        if (type != Byte.TYPE) {
                            if (type != Character.TYPE) {
                                if (type != Double.TYPE) {
                                    if (type != Float.TYPE) {
                                        if (type != Integer.TYPE) {
                                            if (type != Long.TYPE) {
                                                if (type != Short.TYPE) {
                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                } else {
                                                    if (arg0.getClass() == Short.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    }
                                                }
                                            } else {
                                                if (arg0.getClass() == Long.class) {
                                                    value = arg0;
                                                    return;
                                                } else {
                                                    if (type != Short.TYPE) {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    } else {
                                                        if (arg0.getClass() == Short.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            if (arg0.getClass() == Integer.class) {
                                                value = arg0;
                                                return;
                                            } else {
                                                if (type != Long.TYPE) {
                                                    if (type != Short.TYPE) {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    } else {
                                                        if (arg0.getClass() == Short.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Long.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        if (arg0.getClass() == Float.class) {
                                            value = arg0;
                                            return;
                                        } else {
                                            if (type != Integer.TYPE) {
                                                if (type != Long.TYPE) {
                                                    if (type != Short.TYPE) {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    } else {
                                                        if (arg0.getClass() == Short.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Long.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (arg0.getClass() == Integer.class) {
                                                    value = arg0;
                                                    return;
                                                } else {
                                                    if (type != Long.TYPE) {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    } else {
                                                        if (arg0.getClass() == Long.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (arg0.getClass() == Double.class) {
                                        value = arg0;
                                        return;
                                    } else {
                                        if (type != Float.TYPE) {
                                            if (type != Integer.TYPE) {
                                                if (type != Long.TYPE) {
                                                    if (type != Short.TYPE) {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    } else {
                                                        if (arg0.getClass() == Short.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Long.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (arg0.getClass() == Integer.class) {
                                                    value = arg0;
                                                    return;
                                                } else {
                                                    if (type != Long.TYPE) {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    } else {
                                                        if (arg0.getClass() == Long.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            if (arg0.getClass() == Float.class) {
                                                value = arg0;
                                                return;
                                            } else {
                                                if (type != Integer.TYPE) {
                                                    if (type != Long.TYPE) {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    } else {
                                                        if (arg0.getClass() == Long.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Integer.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Long.TYPE) {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        } else {
                                                            if (arg0.getClass() == Long.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                if (type != Short.TYPE) {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                } else {
                                                                    if (arg0.getClass() == Short.class) {
                                                                        value = arg0;
                                                                        return;
                                                                    } else {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (arg0.getClass() == Character.class) {
                                    value = arg0;
                                    return;
                                } else {
                                    if (type != Double.TYPE) {
                                        if (type != Float.TYPE) {
                                            if (type != Integer.TYPE) {
                                                if (type != Long.TYPE) {
                                                    if (type != Short.TYPE) {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    } else {
                                                        if (arg0.getClass() == Short.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Long.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (arg0.getClass() == Integer.class) {
                                                    value = arg0;
                                                    return;
                                                } else {
                                                    if (type != Long.TYPE) {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    } else {
                                                        if (arg0.getClass() == Long.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            if (arg0.getClass() == Float.class) {
                                                value = arg0;
                                                return;
                                            } else {
                                                if (type != Integer.TYPE) {
                                                    if (type != Long.TYPE) {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    } else {
                                                        if (arg0.getClass() == Long.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Integer.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Long.TYPE) {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        } else {
                                                            if (arg0.getClass() == Long.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                if (type != Short.TYPE) {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                } else {
                                                                    if (arg0.getClass() == Short.class) {
                                                                        value = arg0;
                                                                        return;
                                                                    } else {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        if (arg0.getClass() == Double.class) {
                                            value = arg0;
                                            return;
                                        } else {
                                            if (type != Float.TYPE) {
                                                if (type != Integer.TYPE) {
                                                    if (type != Long.TYPE) {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    } else {
                                                        if (arg0.getClass() == Long.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Integer.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Long.TYPE) {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        } else {
                                                            if (arg0.getClass() == Long.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                if (type != Short.TYPE) {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                } else {
                                                                    if (arg0.getClass() == Short.class) {
                                                                        value = arg0;
                                                                        return;
                                                                    } else {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (arg0.getClass() == Float.class) {
                                                    value = arg0;
                                                    return;
                                                } else {
                                                    if (type != Integer.TYPE) {
                                                        if (type != Long.TYPE) {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        } else {
                                                            if (arg0.getClass() == Long.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                if (type != Short.TYPE) {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                } else {
                                                                    if (arg0.getClass() == Short.class) {
                                                                        value = arg0;
                                                                        return;
                                                                    } else {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        if (arg0.getClass() == Integer.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            if (type != Long.TYPE) {
                                                                if (type != Short.TYPE) {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                } else {
                                                                    if (arg0.getClass() == Short.class) {
                                                                        value = arg0;
                                                                        return;
                                                                    } else {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    }
                                                                }
                                                            } else {
                                                                if (arg0.getClass() == Long.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    if (type != Short.TYPE) {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    } else {
                                                                        if (arg0.getClass() == Short.class) {
                                                                            value = arg0;
                                                                            return;
                                                                        } else {
                                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (arg0.getClass() == Byte.class) {
                                value = arg0;
                                return;
                            } else {
                                if (type != Character.TYPE) {
                                    if (type != Double.TYPE) {
                                        if (type != Float.TYPE) {
                                            if (type != Integer.TYPE) {
                                                if (type != Long.TYPE) {
                                                    if (type != Short.TYPE) {
                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                    } else {
                                                        if (arg0.getClass() == Short.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Long.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (arg0.getClass() == Integer.class) {
                                                    value = arg0;
                                                    return;
                                                } else {
                                                    if (type != Long.TYPE) {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    } else {
                                                        if (arg0.getClass() == Long.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            if (arg0.getClass() == Float.class) {
                                                value = arg0;
                                                return;
                                            } else {
                                                if (type != Integer.TYPE) {
                                                    if (type != Long.TYPE) {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    } else {
                                                        if (arg0.getClass() == Long.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Integer.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Long.TYPE) {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        } else {
                                                            if (arg0.getClass() == Long.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                if (type != Short.TYPE) {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                } else {
                                                                    if (arg0.getClass() == Short.class) {
                                                                        value = arg0;
                                                                        return;
                                                                    } else {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        if (arg0.getClass() == Double.class) {
                                            value = arg0;
                                            return;
                                        } else {
                                            if (type != Float.TYPE) {
                                                if (type != Integer.TYPE) {
                                                    if (type != Long.TYPE) {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    } else {
                                                        if (arg0.getClass() == Long.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Integer.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Long.TYPE) {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        } else {
                                                            if (arg0.getClass() == Long.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                if (type != Short.TYPE) {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                } else {
                                                                    if (arg0.getClass() == Short.class) {
                                                                        value = arg0;
                                                                        return;
                                                                    } else {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (arg0.getClass() == Float.class) {
                                                    value = arg0;
                                                    return;
                                                } else {
                                                    if (type != Integer.TYPE) {
                                                        if (type != Long.TYPE) {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        } else {
                                                            if (arg0.getClass() == Long.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                if (type != Short.TYPE) {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                } else {
                                                                    if (arg0.getClass() == Short.class) {
                                                                        value = arg0;
                                                                        return;
                                                                    } else {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        if (arg0.getClass() == Integer.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            if (type != Long.TYPE) {
                                                                if (type != Short.TYPE) {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                } else {
                                                                    if (arg0.getClass() == Short.class) {
                                                                        value = arg0;
                                                                        return;
                                                                    } else {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    }
                                                                }
                                                            } else {
                                                                if (arg0.getClass() == Long.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    if (type != Short.TYPE) {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    } else {
                                                                        if (arg0.getClass() == Short.class) {
                                                                            value = arg0;
                                                                            return;
                                                                        } else {
                                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (arg0.getClass() == Character.class) {
                                        value = arg0;
                                        return;
                                    } else {
                                        if (type != Double.TYPE) {
                                            if (type != Float.TYPE) {
                                                if (type != Integer.TYPE) {
                                                    if (type != Long.TYPE) {
                                                        if (type != Short.TYPE) {
                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                        } else {
                                                            if (arg0.getClass() == Short.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            }
                                                        }
                                                    } else {
                                                        if (arg0.getClass() == Long.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Integer.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Long.TYPE) {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        } else {
                                                            if (arg0.getClass() == Long.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                if (type != Short.TYPE) {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                } else {
                                                                    if (arg0.getClass() == Short.class) {
                                                                        value = arg0;
                                                                        return;
                                                                    } else {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (arg0.getClass() == Float.class) {
                                                    value = arg0;
                                                    return;
                                                } else {
                                                    if (type != Integer.TYPE) {
                                                        if (type != Long.TYPE) {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        } else {
                                                            if (arg0.getClass() == Long.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                if (type != Short.TYPE) {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                } else {
                                                                    if (arg0.getClass() == Short.class) {
                                                                        value = arg0;
                                                                        return;
                                                                    } else {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        if (arg0.getClass() == Integer.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            if (type != Long.TYPE) {
                                                                if (type != Short.TYPE) {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                } else {
                                                                    if (arg0.getClass() == Short.class) {
                                                                        value = arg0;
                                                                        return;
                                                                    } else {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    }
                                                                }
                                                            } else {
                                                                if (arg0.getClass() == Long.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    if (type != Short.TYPE) {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    } else {
                                                                        if (arg0.getClass() == Short.class) {
                                                                            value = arg0;
                                                                            return;
                                                                        } else {
                                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            if (arg0.getClass() == Double.class) {
                                                value = arg0;
                                                return;
                                            } else {
                                                if (type != Float.TYPE) {
                                                    if (type != Integer.TYPE) {
                                                        if (type != Long.TYPE) {
                                                            if (type != Short.TYPE) {
                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                            } else {
                                                                if (arg0.getClass() == Short.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                }
                                                            }
                                                        } else {
                                                            if (arg0.getClass() == Long.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                if (type != Short.TYPE) {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                } else {
                                                                    if (arg0.getClass() == Short.class) {
                                                                        value = arg0;
                                                                        return;
                                                                    } else {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        if (arg0.getClass() == Integer.class) {
                                                            value = arg0;
                                                            return;
                                                        } else {
                                                            if (type != Long.TYPE) {
                                                                if (type != Short.TYPE) {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                } else {
                                                                    if (arg0.getClass() == Short.class) {
                                                                        value = arg0;
                                                                        return;
                                                                    } else {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    }
                                                                }
                                                            } else {
                                                                if (arg0.getClass() == Long.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    if (type != Short.TYPE) {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    } else {
                                                                        if (arg0.getClass() == Short.class) {
                                                                            value = arg0;
                                                                            return;
                                                                        } else {
                                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    if (arg0.getClass() == Float.class) {
                                                        value = arg0;
                                                        return;
                                                    } else {
                                                        if (type != Integer.TYPE) {
                                                            if (type != Long.TYPE) {
                                                                if (type != Short.TYPE) {
                                                                    throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                } else {
                                                                    if (arg0.getClass() == Short.class) {
                                                                        value = arg0;
                                                                        return;
                                                                    } else {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    }
                                                                }
                                                            } else {
                                                                if (arg0.getClass() == Long.class) {
                                                                    value = arg0;
                                                                    return;
                                                                } else {
                                                                    if (type != Short.TYPE) {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    } else {
                                                                        if (arg0.getClass() == Short.class) {
                                                                            value = arg0;
                                                                            return;
                                                                        } else {
                                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            if (arg0.getClass() == Integer.class) {
                                                                value = arg0;
                                                                return;
                                                            } else {
                                                                if (type != Long.TYPE) {
                                                                    if (type != Short.TYPE) {
                                                                        throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                    } else {
                                                                        if (arg0.getClass() == Short.class) {
                                                                            value = arg0;
                                                                            return;
                                                                        } else {
                                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                        }
                                                                    }
                                                                } else {
                                                                    if (arg0.getClass() == Long.class) {
                                                                        value = arg0;
                                                                        return;
                                                                    } else {
                                                                        if (type != Short.TYPE) {
                                                                            throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                        } else {
                                                                            if (arg0.getClass() == Short.class) {
                                                                                value = arg0;
                                                                                return;
                                                                            } else {
                                                                                throw new IllegalArgumentException(new StringBuilder().append("Cannot assign value of type '").append(arg0.getClass().getName()).append("' to property '").append(name).append("' of type '").append(type.getName()).append("'").toString());
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

  public int hashCode() {
        int var1 = 31;
        int var2 = 1;
        var2 = 31 * var2 + name.hashCode();
        var2 = 31 * var2 + type.hashCode();
        var2 = 31 * var2 + getValueHashCode();
        return var2;
    }

  protected int getValueHashCode() {
        if (value != null) {
            if (type.isArray()) {
                if (type != byte[].class) {
                    if (type != char[].class) {
                        if (type != double[].class) {
                            if (type != float[].class) {
                                if (type != int[].class) {
                                    if (type != long[].class) {
                                        if (type != short[].class) {
                                            if (type != boolean[].class) {
                                                return Arrays.hashCode(((Object[]) value));
                                            } else {
                                                return Arrays.hashCode(((boolean[]) value));
                                            }
                                        } else {
                                            return Arrays.hashCode(((short[]) value));
                                        }
                                    } else {
                                        return Arrays.hashCode(((long[]) value));
                                    }
                                } else {
                                    return Arrays.hashCode(((int[]) value));
                                }
                            } else {
                                return Arrays.hashCode(((float[]) value));
                            }
                        } else {
                            return Arrays.hashCode(((double[]) value));
                        }
                    } else {
                        return Arrays.hashCode(((char[]) value));
                    }
                } else {
                    return Arrays.hashCode(((byte[]) value));
                }
            } else {
                return value.hashCode();
            }
        } else {
            return 0;
        }
    }

  public boolean equals(Object arg0) {
        if (this != arg0) {
            if (arg0 != null) {
                if (getClass() == arg0.getClass()) {
                    AnnotationProperty var2 = ((AnnotationProperty) arg0);
                    if (name != null) {
                        if (name.equals(var2.getName())) {
                            if (type != null) {
                                if (type.equals(var2.getType())) {
                                    if (value != null) {
                                        if (type.isArray()) {
                                            if (value instanceof Object[]) {
                                                if (var2.getValue() instanceof Object[]) {
                                                    Arrays.equals(((Object[]) value), ((Object[]) var2.getValue()));
                                                }
                                            }
                                            if (type != byte[].class) {
                                                if (type != char[].class) {
                                                    if (type != double[].class) {
                                                        if (type != float[].class) {
                                                            if (type != int[].class) {
                                                                if (type != long[].class) {
                                                                    if (type != short[].class) {
                                                                        if (type != boolean[].class) {
                                                                            return false;
                                                                        } else {
                                                                            return Arrays.equals(((boolean[]) value), ((boolean[]) var2.getValue()));
                                                                        }
                                                                    } else {
                                                                        return Arrays.equals(((short[]) value), ((short[]) var2.getValue()));
                                                                    }
                                                                } else {
                                                                    return Arrays.equals(((long[]) value), ((long[]) var2.getValue()));
                                                                }
                                                            } else {
                                                                return Arrays.equals(((int[]) value), ((int[]) var2.getValue()));
                                                            }
                                                        } else {
                                                            return Arrays.equals(((float[]) value), ((float[]) var2.getValue()));
                                                        }
                                                    } else {
                                                        return Arrays.equals(((double[]) value), ((double[]) var2.getValue()));
                                                    }
                                                } else {
                                                    return Arrays.equals(((char[]) value), ((char[]) var2.getValue()));
                                                }
                                            } else {
                                                return Arrays.equals(((byte[]) value), ((byte[]) var2.getValue()));
                                            }
                                        } else {
                                            return value.equals(var2.getValue());
                                        }
                                    } else {
                                        if (var2.getValue() == null) {
                                            return false;
                                        } else {
                                            return false;
                                        }
                                    }
                                } else {
                                    return false;
                                }
                            } else {
                                if (var2.getType() == null) {
                                    if (value != null) {
                                        if (type.isArray()) {
                                            if (value instanceof Object[]) {
                                                if (var2.getValue() instanceof Object[]) {
                                                    Arrays.equals(((Object[]) value), ((Object[]) var2.getValue()));
                                                }
                                            }
                                            if (type != byte[].class) {
                                                if (type != char[].class) {
                                                    if (type != double[].class) {
                                                        if (type != float[].class) {
                                                            if (type != int[].class) {
                                                                if (type != long[].class) {
                                                                    if (type != short[].class) {
                                                                        if (type != boolean[].class) {
                                                                            return false;
                                                                        } else {
                                                                            return Arrays.equals(((boolean[]) value), ((boolean[]) var2.getValue()));
                                                                        }
                                                                    } else {
                                                                        return Arrays.equals(((short[]) value), ((short[]) var2.getValue()));
                                                                    }
                                                                } else {
                                                                    return Arrays.equals(((long[]) value), ((long[]) var2.getValue()));
                                                                }
                                                            } else {
                                                                return Arrays.equals(((int[]) value), ((int[]) var2.getValue()));
                                                            }
                                                        } else {
                                                            return Arrays.equals(((float[]) value), ((float[]) var2.getValue()));
                                                        }
                                                    } else {
                                                        return Arrays.equals(((double[]) value), ((double[]) var2.getValue()));
                                                    }
                                                } else {
                                                    return Arrays.equals(((char[]) value), ((char[]) var2.getValue()));
                                                }
                                            } else {
                                                return Arrays.equals(((byte[]) value), ((byte[]) var2.getValue()));
                                            }
                                        } else {
                                            return value.equals(var2.getValue());
                                        }
                                    } else {
                                        if (var2.getValue() == null) {
                                            return false;
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
                        if (var2.getName() == null) {
                            if (type != null) {
                                if (type.equals(var2.getType())) {
                                    if (value != null) {
                                        if (type.isArray()) {
                                            if (value instanceof Object[]) {
                                                if (var2.getValue() instanceof Object[]) {
                                                    Arrays.equals(((Object[]) value), ((Object[]) var2.getValue()));
                                                }
                                            }
                                            if (type != byte[].class) {
                                                if (type != char[].class) {
                                                    if (type != double[].class) {
                                                        if (type != float[].class) {
                                                            if (type != int[].class) {
                                                                if (type != long[].class) {
                                                                    if (type != short[].class) {
                                                                        if (type != boolean[].class) {
                                                                            return false;
                                                                        } else {
                                                                            return Arrays.equals(((boolean[]) value), ((boolean[]) var2.getValue()));
                                                                        }
                                                                    } else {
                                                                        return Arrays.equals(((short[]) value), ((short[]) var2.getValue()));
                                                                    }
                                                                } else {
                                                                    return Arrays.equals(((long[]) value), ((long[]) var2.getValue()));
                                                                }
                                                            } else {
                                                                return Arrays.equals(((int[]) value), ((int[]) var2.getValue()));
                                                            }
                                                        } else {
                                                            return Arrays.equals(((float[]) value), ((float[]) var2.getValue()));
                                                        }
                                                    } else {
                                                        return Arrays.equals(((double[]) value), ((double[]) var2.getValue()));
                                                    }
                                                } else {
                                                    return Arrays.equals(((char[]) value), ((char[]) var2.getValue()));
                                                }
                                            } else {
                                                return Arrays.equals(((byte[]) value), ((byte[]) var2.getValue()));
                                            }
                                        } else {
                                            return value.equals(var2.getValue());
                                        }
                                    } else {
                                        if (var2.getValue() == null) {
                                            return false;
                                        } else {
                                            return false;
                                        }
                                    }
                                } else {
                                    return false;
                                }
                            } else {
                                if (var2.getType() == null) {
                                    if (value != null) {
                                        if (type.isArray()) {
                                            if (value instanceof Object[]) {
                                                if (var2.getValue() instanceof Object[]) {
                                                    Arrays.equals(((Object[]) value), ((Object[]) var2.getValue()));
                                                }
                                            }
                                            if (type != byte[].class) {
                                                if (type != char[].class) {
                                                    if (type != double[].class) {
                                                        if (type != float[].class) {
                                                            if (type != int[].class) {
                                                                if (type != long[].class) {
                                                                    if (type != short[].class) {
                                                                        if (type != boolean[].class) {
                                                                            return false;
                                                                        } else {
                                                                            return Arrays.equals(((boolean[]) value), ((boolean[]) var2.getValue()));
                                                                        }
                                                                    } else {
                                                                        return Arrays.equals(((short[]) value), ((short[]) var2.getValue()));
                                                                    }
                                                                } else {
                                                                    return Arrays.equals(((long[]) value), ((long[]) var2.getValue()));
                                                                }
                                                            } else {
                                                                return Arrays.equals(((int[]) value), ((int[]) var2.getValue()));
                                                            }
                                                        } else {
                                                            return Arrays.equals(((float[]) value), ((float[]) var2.getValue()));
                                                        }
                                                    } else {
                                                        return Arrays.equals(((double[]) value), ((double[]) var2.getValue()));
                                                    }
                                                } else {
                                                    return Arrays.equals(((char[]) value), ((char[]) var2.getValue()));
                                                }
                                            } else {
                                                return Arrays.equals(((byte[]) value), ((byte[]) var2.getValue()));
                                            }
                                        } else {
                                            return value.equals(var2.getValue());
                                        }
                                    } else {
                                        if (var2.getValue() == null) {
                                            return false;
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
        } else {
            return true;
        }
    }

  public String toString() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: new  #31 // java.lang.StringBuilder
        //      3: dup
        //      4: invokespecial  #57 // java.lang.StringBuilder.<init>:()V
        //      7: ldc  #4 // '(name='
        //      9: invokevirtual  #58 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     12: aload_0
        //     13: getfield  #42 // jnr.ffi.util.AnnotationProperty.name:Ljava/lang/String;
        //     16: invokevirtual  #58 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     19: ldc  #6 // ', type='
        //     21: invokevirtual  #58 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     24: aload_0
        //     25: getfield  #43 // jnr.ffi.util.AnnotationProperty.type:Ljava/lang/Class;
        //     28: invokevirtual  #47 // java.lang.Class.isArray:()Z
        //     31: ifeq  65 (offset +34)
        //     34: new  #31 // java.lang.StringBuilder
        //     37: dup
        //     38: invokespecial  #57 // java.lang.StringBuilder.<init>:()V
        //     41: aload_0
        //     42: getfield  #43 // jnr.ffi.util.AnnotationProperty.type:Ljava/lang/Class;
        //     45: invokevirtual  #45 // java.lang.Class.getComponentType:()Ljava/lang/Class;
        //     48: invokevirtual  #46 // java.lang.Class.getName:()Ljava/lang/String;
        //     51: invokevirtual  #58 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     54: ldc  #9 // '[]'
        //     56: invokevirtual  #58 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     59: invokevirtual  #59 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //     62: goto  72 (offset +10)
        //     65: aload_0
        //     66: getfield  #43 // jnr.ffi.util.AnnotationProperty.type:Ljava/lang/Class;
        //     69: invokevirtual  #46 // java.lang.Class.getName:()Ljava/lang/String;
        //     72: invokevirtual  #58 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     75: ldc  #7 // ', value='
        //     77: invokevirtual  #58 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     80: aload_0
        //     81: invokevirtual  #91 // jnr.ffi.util.AnnotationProperty.valueToString:()Ljava/lang/String;
        //     84: invokevirtual  #58 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     87: ldc  #5 // ')'
        //     89: invokevirtual  #58 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     92: invokevirtual  #59 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //     95: areturn
    }

  protected String valueToString() {
        if (type.isArray()) {
            Class var1 = type.getComponentType();
            if (var1 != Boolean.TYPE) {
                if (var1 != Byte.TYPE) {
                    if (var1 != Character.TYPE) {
                        if (var1 != Double.TYPE) {
                            if (var1 != Float.TYPE) {
                                if (var1 != Integer.TYPE) {
                                    if (var1 != Long.TYPE) {
                                        if (var1 != Short.TYPE) {
                                            return Arrays.toString(((Object[]) value));
                                        } else {
                                            return Arrays.toString(((short[]) value));
                                        }
                                    } else {
                                        return Arrays.toString(((long[]) value));
                                    }
                                } else {
                                    return Arrays.toString(((int[]) value));
                                }
                            } else {
                                return Arrays.toString(((float[]) value));
                            }
                        } else {
                            return Arrays.toString(((double[]) value));
                        }
                    } else {
                        return Arrays.toString(((char[]) value));
                    }
                } else {
                    return Arrays.toString(((byte[]) value));
                }
            } else {
                return Arrays.toString(((boolean[]) value));
            }
        } else {
            return String.valueOf(value);
        }
    }

}