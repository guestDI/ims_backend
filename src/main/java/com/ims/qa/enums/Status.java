package com.ims.qa.enums;

public enum Status {
    TODO("To Do"),
    DONE("Done"),
    REJECTED("Rejected"),
    JO_MADE("JO Made"),
    JO_REJECTED("JO Rejected"),
    JO_ACCEPTED("JO Accepted");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
