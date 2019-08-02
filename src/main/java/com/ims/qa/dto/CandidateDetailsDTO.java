package com.ims.qa.dto;

import com.ims.qa.enums.CandidateStatus;
import com.ims.qa.enums.Level;
import com.ims.qa.enums.Location;
import com.ims.qa.model.Candidate;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateDetailsDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private Level level;
    private Location location;
    private CandidateStatus candidateStatus;
    private String skills;
    private String comment;
    private Date startDate;

    public CandidateDetailsDTO(String firstname, String lastname, Level level, Location location, CandidateStatus candidateStatus){
        this.firstname = firstname;
        this.lastname = lastname;
        this.level = level;
        this.location = location;
        this.candidateStatus = candidateStatus;
    }
}

