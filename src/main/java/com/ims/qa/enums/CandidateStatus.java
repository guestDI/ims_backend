package com.ims.qa.enums;

public enum CandidateStatus {
    IN_REVIEW("In Review"),
    REJECTED("Rejected"),
    JO_MADE("JO Made"),
    JO_REJECTED("JO Rejected"),
    JO_ACCEPTED("JO Accepted"),
    STARTED("Started");

    private String name;

    CandidateStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
