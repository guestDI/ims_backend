package com.ims.qa.enums;

public enum Location {
    MINSK("Minsk"),
    MINSK_RUBIN("Minsk(Rubin)"),
    MINSK_SILVER("Minsk(Silver)"),
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
