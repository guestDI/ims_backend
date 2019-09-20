package com.ims.qa.dto;

import com.ims.qa.enums.Level;
import com.ims.qa.enums.Location;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String level;
    private String location;

}
