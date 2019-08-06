package com.ims.qa.dto;

import com.ims.qa.enums.CandidateStatus;
import com.ims.qa.enums.Level;
import com.ims.qa.enums.Location;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCandidateDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private Level level;
    private Location location;
    private String skills;
    private String comment;
    private CandidateStatus candidateStatus;
    private Date startDate;
}
