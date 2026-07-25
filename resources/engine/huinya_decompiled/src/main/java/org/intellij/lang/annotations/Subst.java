// исходный (обфусцированный) внутренний класс: org.intellij.lang.annotations.Subst
package org.intellij.lang.annotations;

import java.lang.annotation.Annotation;

public @interface Subst extends Annotation {

  public abstract String value();

}