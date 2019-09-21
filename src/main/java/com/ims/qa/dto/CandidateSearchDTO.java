package com.ims.qa.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateSearchDTO {
    private String name;
    private Integer page;
    private Integer size;
}
