// исходный (обфусцированный) внутренний класс: org.intellij.lang.annotations.PrintFormat
package org.intellij.lang.annotations;

import java.lang.annotation.Annotation;
import org.intellij.lang.annotations.Pattern;

@Pattern("(?:[^%]|%%|(?:%(?:\\d+\\$)?(?:[-#+ 0,(<]*)?(?:\\d+)?(?:\\.\\d+)?(?:[tT])?(?:[a-zA-Z%])))*")
public @interface PrintFormat extends Annotation {

}