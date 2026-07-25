// исходный (обфусцированный) внутренний класс: org.intellij.lang.annotations.MagicConstant
package org.intellij.lang.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention("SOURCE")
@Target
public @interface MagicConstant extends Annotation {

  public abstract long[] intValues();

  public abstract String[] stringValues();

  public abstract long[] flags();

  public abstract Class valuesFromClass();

  public abstract Class flagsFromClass();

}