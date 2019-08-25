package com.ims.qa.model;

import com.ims.qa.dto.CandidateLevelDTO;
import com.ims.qa.dto.InterviewStatisticDTO;
import com.ims.qa.enums.InterviewStatus;
import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;
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
@SqlResultSetMapping(name="InterviewsByYear", classes = {
        @ConstructorResult(targetClass = InterviewStatisticDTO.class,
                columns = {
                        @ColumnResult(name="month", type = Date.class),
                        @ColumnResult(name="count", type = Integer.class),
                })
})
@NamedNativeQuery(
        name = "InterviewsCurrentYearCountQuery",
        query = "select date_trunc( 'month', date ) as month , count(*) from interviews where date_part('year', date) = date_part('year', CURRENT_DATE)\n" +
                "group by date_trunc( 'month', date ) order by date_trunc( 'month', date ) asc",
        resultSetMapping = "InterviewsByYear")
@NamedNativeQuery(
        name = "InterviewsPrevYearCountQuery",
        query = "select date_trunc( 'month', date ) as month , count(*) from interviews WHERE date >= date_trunc('year', current_date - interval '1' month)\n" +
                "and date < date_trunc('year', current_date) group by date_trunc( 'month', date ) order by date_trunc( 'month', date ) asc",
        resultSetMapping = "InterviewsByYear")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="candidate_id")
    private Candidate candidate;

    @Column
    private ZonedDateTime date;

    @Column(columnDefinition = "text")
    private String comment;

    @ManyToMany
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
    private InterviewStatus interviewStatus;

    public Interview(Candidate candidate, ArrayList<Interviewer> interviewers, InterviewStatus interviewStatus) {
        this.candidate = candidate;
        this.interviewers = interviewers;
        this.interviewStatus = interviewStatus;
    }

    public Interview(Candidate candidate, ArrayList<Interviewer> interviewers, InterviewStatus interviewStatus, ZonedDateTime date) {
        this.candidate = candidate;
        this.interviewers = interviewers;
        this.interviewStatus = interviewStatus;
        this.date = date;
    }

    @PrePersist
    public void prePersist() {
        this.active = true;
    }
}
