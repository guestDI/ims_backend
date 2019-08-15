package com.ims.qa.dto;

import com.ims.qa.enums.InterviewStatus;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewDTO {
    private Long id;
    private Long candidateId;
    private List<InterviewerDTO> interviewers;
    private Date date;
    private InterviewStatus status;
}
