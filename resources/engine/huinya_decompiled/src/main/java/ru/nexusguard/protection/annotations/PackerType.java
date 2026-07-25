// исходный (обфусцированный) внутренний класс: ru.nexusguard.protection.annotations.PackerType
package ru.nexusguard.protection.annotations;

public enum PackerType {

    NONE,
    VIRTUALIZATION,
    MUTATION,
    ULTRA;

  private PackerType() { // было: <init>
        // (пустое тело)
    }

  private static PackerType[] $values() {
        return new PackerType[]{NONE, VIRTUALIZATION, MUTATION, ULTRA};
    }

  private static PackerType[] $values$() {
        return new PackerType[]{NONE, VIRTUALIZATION, MUTATION, ULTRA};
    }

}