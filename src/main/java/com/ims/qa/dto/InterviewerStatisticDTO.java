package com.ims.qa.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewerStatisticDTO {
    private Date month;
    private int count;
}
