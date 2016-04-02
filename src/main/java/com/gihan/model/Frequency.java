package com.gihan.model;

public enum Frequency {
    ONCE_OFF ("Once Off"),
    WEEKLY ("Weekly"),
    MONTHLY ("Monthly"),
    YEARLY ("Yearly");

    // TODO: Ability to add description for last business day of month? / first Sunday of month etc
    private final String name;

    private Frequency(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    public String toString() {
        switch (this) {
            case ONCE_OFF: return ONCE_OFF.name;
            case WEEKLY: return WEEKLY.name;
            case MONTHLY: return MONTHLY.name;
            case YEARLY: return YEARLY.name;
            default: throw new IllegalArgumentException();
        }
    }

}
