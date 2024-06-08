package com.malimaquintino.javamongo.enums;

public enum Environment {
    PROD("PROD"),
    DEV("DEV"),
    QA("QA");

    private final String env;

    Environment(String env) {
        this.env = env;
    }

    @Override
    public String toString() {
        return env;
    }
}
