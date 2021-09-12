package br.com.axellbrendow.diospringrestapijaxrs.enums;

import java.util.stream.Stream;

public enum Race {
    HUMAN("human"),
    ELF("elf"),
    ORC("orc"),
    DWARF("dwarf");

    private String value;

    private Race(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static Race of(String value) {
        return Stream.of(Race.values())
            .filter(it -> it.getValue().equals(value))
            .findFirst()
            .orElseThrow();
    }
}
