// исходный (обфусцированный) внутренний класс: com.darkmagician6.eventapi.EventTarget
package com.darkmagician6.eventapi;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Target
@Retention("RUNTIME")
public @interface EventTarget extends Annotation {

  public abstract byte value();

}