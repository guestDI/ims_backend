package com.ims.qa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ims.qa.enums.Level;
import com.ims.qa.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "interviews")
@Builder
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="candidate_id")
    private Candidate candidate;

    @Column
    private Date date = new Date();

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "interview_interviewer",
            joinColumns = { @JoinColumn(name = "interview_id") },
            inverseJoinColumns = { @JoinColumn(name = "interviewer_id") }
    )
    private List<Interviewer> interviewers;
    @Column(nullable = false)
    private boolean active;
    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    public Interview(Candidate candidate, ArrayList<Interviewer> interviewers, Status status) {
        this.candidate = candidate;
        this.interviewers = interviewers;
        this.status = status;
    }

    @PrePersist
    public void prePersist() {
        this.active = true;
    }
}
