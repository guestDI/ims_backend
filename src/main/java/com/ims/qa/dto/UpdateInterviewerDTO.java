package com.ims.qa.dto;

import com.ims.qa.enums.Level;
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
    private Level level;
    private String email;
    private String skills;

    public UpdateInterviewerDTO(Long id, String firstname, String lastname, String email){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public UpdateInterviewerDTO(Long id, String firstname, String lastname){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

}
