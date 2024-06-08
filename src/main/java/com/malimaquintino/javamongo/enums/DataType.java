package com.malimaquintino.javamongo.enums;

public enum DataType {
    DATABASE("DATABASE"),
    TABLE("TABLE"),
    VIEW("VIEW"),
    COLUMN("COLUMN");

    private final String type;

    DataType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
