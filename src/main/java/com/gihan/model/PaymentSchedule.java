package com.gihan.model;

public enum PaymentSchedule {
    NOW ("Now"),
    LATER ("Later"),
    RECURRING ("Recurring");

    private final String name;

    private PaymentSchedule(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    public String toString() {
        switch (this) {
            case NOW: return NOW.name;
            case LATER: return LATER.name;
            case RECURRING: return RECURRING.name;
            default: throw new IllegalArgumentException();
        }
    }
}
