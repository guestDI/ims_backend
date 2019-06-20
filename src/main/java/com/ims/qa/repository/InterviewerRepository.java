package com.ims.qa.repository;

import com.ims.qa.model.Interviewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewerRepository extends JpaRepository<Interviewer, Long> {

    @Query("Select COUNT(*) from Interviewer ir where ir.email = :email")
    int checkInterviewerExists(@Param("email") String email);

    List<Interviewer> findTop5ByActiveTrueOrderByNumberOfInterviewsDesc();
//
    @Modifying(clearAutomatically = true)
    @Query("Update Interviewer i SET i.active = :status WHERE i.id = :id")
    int updateInterviewerStatus(@Param("id") Long id, @Param("status") boolean status);

}
