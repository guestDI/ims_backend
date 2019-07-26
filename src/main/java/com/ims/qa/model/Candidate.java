package com.ims.qa.model;

import com.ims.qa.dto.CandidateLevelDTO;
import com.ims.qa.enums.Level;
import com.ims.qa.enums.Location;
import com.ims.qa.enums.CandidateStatus;
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
@SqlResultSetMapping(name="LevelsCount", classes = {
        @ConstructorResult(targetClass = CandidateLevelDTO.class,
                columns = {
                        @ColumnResult(name="level", type = String.class),
                        @ColumnResult(name="count", type = Integer.class),
                })
})
@NamedNativeQuery(
        name = "LevelsCountQuery",
        query = "select level, count(*) from candidate group by level order by level",
        resultSetMapping = "LevelsCount")
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
    @Enumerated(EnumType.STRING)
    private Location location;
    @Column(columnDefinition = "text")
    private String comment;
    private Date startDate = new Date();
    @Column(nullable = false)
    private boolean active;
    @Column
    @Enumerated(EnumType.STRING)
    private CandidateStatus candidateStatus;

    @PrePersist
    public void prePersist() {
        this.active = true;
    }

    public Candidate(String firstname, String lastname, Location location, Level level) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.location = location;
        this.level = level;
    }

    public Candidate(String firstname, String lastname, Location location, Level level, CandidateStatus candidateStatus) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.location = location;
        this.level = level;
        this.candidateStatus = candidateStatus;
    }

}
