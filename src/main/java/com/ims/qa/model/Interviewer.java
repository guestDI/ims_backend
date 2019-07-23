package com.ims.qa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ims.qa.dto.InterviewerDTO;
import com.ims.qa.enums.Level;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "interviewer")
@Builder
@SqlResultSetMapping(name="Interviewer", classes = {
        @ConstructorResult(targetClass = InterviewerDTO.class,
                columns = {
                        @ColumnResult(name="id", type = Long.class),
                        @ColumnResult(name="firstname", type = String.class),
                        @ColumnResult(name="lastname", type = String.class),
                        @ColumnResult(name="email", type = String.class),
                        @ColumnResult(name="level", type = String.class),
                        @ColumnResult(name="location", type = String.class),
                        @ColumnResult(name="active", type = Boolean.class),
                        @ColumnResult(name="profile_photo", type = String.class),
                        @ColumnResult(name="count", type = Integer.class),
                        @ColumnResult(name="lastInterviewDate", type = Date.class)
                })
})
@NamedNativeQuery(
        name = "InterviewerQuery",
        query = "SELECT t1.*, max(t3.date) as lastInterviewDate, count(t3.id)" +
                "from interviewer t1 left join interview_interviewer t2 on t1.id = t2.interviewer_id left join interviews t3\n" +
                "on t2.interview_id = t3.id group by t1.id",
        resultSetMapping = "Interviewer")
public class Interviewer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 21, nullable = false)
    private String firstname;
    @Column(length = 21, nullable = false)
    private String lastname;
    @Enumerated(EnumType.STRING)
    private Level level;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private boolean active;
    @Column
    private String profilePhoto;

    @JsonIgnore
    @ManyToMany(mappedBy = "interviewers", cascade = {CascadeType.ALL})
    private List<Interview> interviews;

    @PrePersist
    public void prePersist() {
        this.active = true;
    }

    public Interviewer(String firstname, String lastname, String email, String location, Level level){
        this.firstname = firstname;
        this.lastname = lastname;
        this.level = level;
        this.email = email;
        this.location = location;
    }

    public Interviewer(String firstname, String lastname, String email, String location, Level level, String photoUrl){
        this.firstname = firstname;
        this.lastname = lastname;
        this.level = level;
        this.email = email;
        this.location = location;
        this.profilePhoto = photoUrl;
    }
}
