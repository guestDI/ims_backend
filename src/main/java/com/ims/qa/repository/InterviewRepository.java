package com.ims.qa.repository;

import com.ims.qa.enums.Status;
import com.ims.qa.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
    List<Interview> findAllByActiveTrue(Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("Update Interview i SET i.active = false WHERE i.id = :id")
    int updateInterviewToInactive(@Param("id") Long id);

    int countAllByActiveTrue();

    int countAllByActiveTrueAndStatusEquals(@Param("status")Status status);

}
