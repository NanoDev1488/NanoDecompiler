// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.AsmBuilder.ObjectNameGenerator
package jnr.ffi.provider.jffi;

final class AsmBuilder_ObjectNameGenerator {

    // ---- поля ----
  private final String baseName;
  private int value;

   AsmBuilder_ObjectNameGenerator(String arg0) { // было: <init>
        super();
        baseName = arg0;
        value = 0;
    }

   String generateName() {
        value = value + 1;
        return new StringBuilder().append(baseName).append("_").append(value + 1).toString();
    }

}