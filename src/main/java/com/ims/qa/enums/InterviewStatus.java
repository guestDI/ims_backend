package com.ims.qa.enums;

public enum InterviewStatus {
    SCHEDULED("Scheduled"),
    DONE("Done"),
    IFU_SENT("IFU Sent"),
    DECISION_MADE("Decision made"),
    CANCELED("Canceled");

    private String name;

    InterviewStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
