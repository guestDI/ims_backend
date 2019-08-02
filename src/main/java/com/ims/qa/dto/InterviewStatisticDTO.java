package com.ims.qa.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewStatisticDTO {
    private Date month;
    private int count;
}
