// исходный (обфусцированный) внутренний класс: ru.nexusguard.protection.annotations.Compile
package ru.nexusguard.protection.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import ru.nexusguard.protection.annotations.PackerType;

@Retention("CLASS")
@Target
public @interface Compile extends Annotation {

  public abstract PackerType packer();

}