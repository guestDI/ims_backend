package com.ims.qa.enums;

public enum Level {
    INTERN("Intern QA"),
    JUNIOR("Junior QA"),
    MIDDLE("Middle QA"),
    SENIOR("Senior QA"),
    LEAD("Lead QA");

    private String name;

    Level(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
