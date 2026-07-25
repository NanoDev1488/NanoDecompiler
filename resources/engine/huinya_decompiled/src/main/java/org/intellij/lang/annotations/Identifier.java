// исходный (обфусцированный) внутренний класс: org.intellij.lang.annotations.Identifier
package org.intellij.lang.annotations;

import java.lang.annotation.Annotation;
import org.intellij.lang.annotations.Pattern;

@Pattern("\\p{javaJavaIdentifierStart}\\p{javaJavaIdentifierPart}*")
public @interface Identifier extends Annotation {

}