package com.ims.qa.dto;

import com.ims.qa.enums.CandidateStatus;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewcomerDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private Date startDate;
//    private CandidateStatus candidateStatus;

    public NewcomerDTO(String firstname, String lastname, Date startDate){
        this.firstname = firstname;
        this.lastname = lastname;
        this.startDate = startDate;
    }
}


