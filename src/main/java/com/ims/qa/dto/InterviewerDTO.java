package com.ims.qa.dto;

import com.ims.qa.enums.Level;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewerDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String level;
    private String location;
    private boolean active;
    private String profilePhoto;
    private int totalInterviews;
    private Date lastInterviewDate;
}
