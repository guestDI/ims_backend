package com.ims.qa.model;

import com.ims.qa.dto.CandidateLevelDTO;
import com.ims.qa.dto.CandidateLocationDTO;
import com.ims.qa.dto.NewcomerDTO;
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
@SqlResultSetMapping(name="LocationsCount", classes = {
        @ConstructorResult(targetClass = CandidateLocationDTO.class,
                columns = {
                        @ColumnResult(name="location", type = String.class),
                        @ColumnResult(name="count", type = Integer.class),
                })
})
@SqlResultSetMapping(name="Newcomers", classes = {
        @ConstructorResult(targetClass = NewcomerDTO.class,
                columns = {
                        @ColumnResult(name="id", type = Long.class),
                        @ColumnResult(name="firstname", type = String.class),
                        @ColumnResult(name="lastname", type = String.class),
                        @ColumnResult(name="startDate", type = Date.class),
                })
})
@NamedNativeQuery(
        name = "LevelsCurrentYearCountQuery",
        query = "select level, count(*) from candidate join interviews i on candidate.id = i.candidate_id\n" +
                "where date_part('year', i.date) = date_part('year', CURRENT_DATE)\n" +
                "group by level order by level",
        resultSetMapping = "LevelsCount")
@NamedNativeQuery(
        name = "LevelsPrevYearCountQuery",
        query = "select level, count(*) from candidate join interviews i on candidate.id = i.candidate_id\n" +
                "WHERE i.date >= date_trunc('year', current_date - interval '1' month)\n" +
                "and i.date < date_trunc('year', current_date) group by level order by level",
        resultSetMapping = "LevelsCount")
@NamedNativeQuery(
        name = "LevelsCurrentMonthCountQuery",
        query = "select level, count(*) from candidate join interviews i on candidate.id = i.candidate_id\n" +
                "where date_part('month', i.date) = date_part('month', CURRENT_DATE)\n" +
                "group by level order by level",
        resultSetMapping = "LevelsCount")
@NamedNativeQuery(
        name = "LevelsPrevMonthCountQuery",
        query = "select level, count(*) from candidate join interviews i on candidate.id = i.candidate_id\n" +
                "WHERE i.date >= date_trunc('month', current_date - interval '1' month)\n" +
                "and i.date < date_trunc('month', current_date) group by level order by level",
        resultSetMapping = "LevelsCount")
@NamedNativeQuery(
        name = "LocationsCurrentYearCountQuery",
        query = "Select location, count(*) from candidate join interviews i on candidate.id = i.candidate_id\n" +
                "where date_part('year', i.date) = date_part('year', CURRENT_DATE) group by location;",
        resultSetMapping = "LocationsCount")
@NamedNativeQuery(
        name = "LocationsPrevYearCountQuery",
        query = "Select location, count(*) from candidate join interviews i on candidate.id = i.candidate_id\n" +
                "WHERE i.date >= date_trunc('year', current_date - interval '1' month)\n" +
                "  and i.date < date_trunc('year', current_date) group by location;",
        resultSetMapping = "LocationsCount")
@NamedNativeQuery(
        name = "LocationsCurrentMonthCountQuery",
        query = "Select location, count(*) from candidate join interviews i on candidate.id = i.candidate_id\n" +
                "where date_part('month', i.date) = date_part('month', CURRENT_DATE) group by location;",
        resultSetMapping = "LocationsCount")
@NamedNativeQuery(
        name = "LocationsPrevMonthCountQuery",
        query = "Select location, count(*) from candidate join interviews i on candidate.id = i.candidate_id\n" +
                "WHERE i.date >= date_trunc('month', current_date - interval '1' month)\n" +
                "and i.date < date_trunc('month', current_date) group by location;",
        resultSetMapping = "LocationsCount")
@NamedNativeQuery(
        name="CheckCandidateExists",
        query = "SELECT count(*) from candidate c where c.firstname=?1 and c.lastname=?2"
)
@NamedNativeQuery(
        name="SelectNewcomersQuery",
        query = "select id, firstname, lastname, start_date as startDate from candidate\n" +
                "where active=true and start_date NOTNULL and start_date > CURRENT_DATE and candidate_status in ('STARTED', 'JO_MADE')\n" +
                "order by start_date asc limit 5",
        resultSetMapping = "Newcomers"
)
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
    private String skills;
    @Column(columnDefinition = "text")
    private String comment;
    private Date startDate;
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

    public Candidate(String firstname, String lastname, Location location, Level level, CandidateStatus candidateStatus,
                     String comment) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.location = location;
        this.level = level;
        this.candidateStatus = candidateStatus;
        this.comment = comment;
    }

}
