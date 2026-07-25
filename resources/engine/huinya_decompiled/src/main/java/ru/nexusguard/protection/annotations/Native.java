// исходный (обфусцированный) внутренний класс: ru.nexusguard.protection.annotations.Native
package ru.nexusguard.protection.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention("CLASS")
@Target
public @interface Native extends Annotation {

}