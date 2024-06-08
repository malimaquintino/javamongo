package com.malimaquintino.javamongo.enums;

public enum Status {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
