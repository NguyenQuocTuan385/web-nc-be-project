package com.group6.ads.enums;

public enum ERoles {
    WARD("WARD"),
    DISTRICT("DISTRICT"),
    DEPARTMENT("DEPARTMENT"),
    GUEST("GUEST");


    final String label;

    ERoles(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
