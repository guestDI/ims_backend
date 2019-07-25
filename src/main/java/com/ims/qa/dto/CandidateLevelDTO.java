package com.ims.qa.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateLevelDTO {
    private String level;
    private int count;
}
