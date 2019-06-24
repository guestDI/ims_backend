package com.ims.qa.model;

import com.ims.qa.enums.Level;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidate")
@Builder
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 21, nullable = false)
    private String firstname;
    @Column(length = 21, nullable = false)
    private String lastname;
    @Column
    @Enumerated(EnumType.STRING)
    private Level level;
    @Column(nullable = false)
    private String location;
    @Column(columnDefinition = "text")
    private String comment;
    private Date startDate = new Date();
    @Column(nullable = false)
    private boolean active;

    @PrePersist
    public void prePersist() {
        this.active = true;
    }

    public Candidate(String firstname, String lastname, String location, Level level) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.location = location;
        this.level = level;
    }

}
