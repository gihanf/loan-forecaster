package com.gihan.model;

public enum PaymentScheduleOption {
    NOW ("Now"),
    LATER ("Later");

    private final String name;

    private PaymentScheduleOption(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    public String toString() {
        switch (this) {
            case NOW: return NOW.name;
            case LATER: return LATER.name;
            default: throw new IllegalArgumentException();
        }
    }
}
