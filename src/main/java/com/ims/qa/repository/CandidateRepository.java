package com.ims.qa.repository;

import com.ims.qa.dto.CandidateLevelDTO;
import com.ims.qa.dto.CandidateLocationDTO;
import com.ims.qa.dto.TopInterviewerDTO;
import com.ims.qa.enums.CandidateStatus;
import com.ims.qa.model.Candidate;
import com.ims.qa.model.Candidate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    List<Candidate> findAllByActiveTrue(Pageable pageable);

    int countAllByActiveTrue();

    @Query(
            name = "LevelsCurrentYearCountQuery",
            nativeQuery = true)
    List<CandidateLevelDTO> findLevelsWithNumberCurrentYear();

    @Query(
            name = "LevelsPrevYearCountQuery",
            nativeQuery = true)
    List<CandidateLevelDTO> findLevelsWithNumberPrevYear();

    @Query(
            name = "LevelsCurrentMonthCountQuery",
            nativeQuery = true)
    List<CandidateLevelDTO> findLevelsWithNumberCurrentMonth();

    @Query(
            name = "LevelsPrevMonthCountQuery",
            nativeQuery = true)
    List<CandidateLevelDTO> findLevelsWithNumberPrevMonth();

    @Query(
            name = "LocationsCurrentYearCountQuery",
            nativeQuery = true)
    List<CandidateLocationDTO> findLocationsWithNumberCurrentYear();

    @Query(
            name = "LocationsPrevYearCountQuery",
            nativeQuery = true)
    List<CandidateLocationDTO> findLocationsWithNumberPrevYear();

    @Query(
            name = "LocationsCurrentMonthCountQuery",
            nativeQuery = true)
    List<CandidateLocationDTO> findLocationsWithNumberCurrentMonth();

    @Query(
            name = "LocationsPrevMonthCountQuery",
            nativeQuery = true)
    List<CandidateLocationDTO> findLocationsWithNumberPrevMonth();

    int countAllByActiveTrueAndCandidateStatusEquals(@Param("candidateStatus") CandidateStatus candidateStatus);

    @Query(name = "CheckCandidateExists",
            nativeQuery = true)
    int checkCandidateExists(@Param("firstname") String firstname, @Param("lastname") String lastname);
}
