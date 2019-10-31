package com.ims.qa.dto;

import com.ims.qa.enums.CandidateStatus;
import com.ims.qa.enums.Level;
import com.ims.qa.enums.Location;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateFilterDTO {
    private List<Location> locations;
    private List<Level> levels;
    private List<CandidateStatus> candidateStatuses;
    private Integer page;
    private Integer size;
}
