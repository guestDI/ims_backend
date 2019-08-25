package com.ims.qa.dto;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewCandidateDTO {
    private ZonedDateTime date;
}
