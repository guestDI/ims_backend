package com.ims.qa.enums;

public enum Location {
    MINSK("Minsk"),
    BREST("Brest"),
    GRODNO("Grodno"),
    GOMEL("Gomel"),
    MOGILEV("Mogilev");

    private String name;

    Location(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
