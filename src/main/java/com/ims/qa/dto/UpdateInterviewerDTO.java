package com.ims.qa.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateInterviewerDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
}
