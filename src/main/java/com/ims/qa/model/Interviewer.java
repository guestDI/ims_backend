package com.ims.qa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Interviewer")
@Builder
public class Interviewer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column(length = 21, nullable = false)
    private String firstname;
    @Column(length = 21, nullable = false)
    private String lastname;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private Integer numberOfInterviews;
    @Column(nullable = false)
    private boolean active;
    @Column(nullable = false)
    private Date lastInterviewDate = new Date();
    @Column
    private String profilePhoto;

//    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private List<Item> items;

    @PrePersist
    public void prePersist() {
        this.lastInterviewDate = new Date();
        this.active = true;
    }

    public Interviewer(String firstname, String lastname, String email, String location, Integer numberOfInterviews){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.location = location;
        this.numberOfInterviews = numberOfInterviews;
    }
}
