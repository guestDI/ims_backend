package com.ims.qa.repository;

import com.ims.qa.dto.CandidateLevelDTO;
import com.ims.qa.dto.TopInterviewerDTO;
import com.ims.qa.model.Candidate;
import com.ims.qa.model.Candidate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    List<Candidate> findAllByActiveTrue(Pageable pageable);

    @Query(
            name = "LevelsCountQuery",
            nativeQuery = true)
    List<CandidateLevelDTO> findLevelsWithNumber();
}
