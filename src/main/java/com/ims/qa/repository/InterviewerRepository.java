package com.ims.qa.repository;

import com.ims.qa.dto.InterviewerDTO;
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

    int countAllByActiveTrue();

    List<Interviewer> findAllByActiveTrue();

    List<Interviewer> findTop5ByActiveTrue();

    @Modifying(clearAutomatically = true)
    @Query("Update Interviewer i SET i.active = :status WHERE i.id = :id")
    int updateInterviewerStatus(@Param("id") Long id, @Param("status") boolean status);

    @Modifying
    @Query("UPDATE Interviewer i SET i.firstname = :#{#dto.firstname}, i.lastname = :#{#dto.lastname}, i.email = :#{#dto.email} " +
            "WHERE i.id = :#{#dto.id}")
    int update(@Param("dto") InterviewerDTO dto);

}
