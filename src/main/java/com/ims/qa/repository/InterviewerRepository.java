package com.ims.qa.repository;

import com.ims.qa.dto.InterviewerDTO;
import com.ims.qa.dto.InterviewerStatisticDTO;
import com.ims.qa.dto.TopInterviewerDTO;
import com.ims.qa.dto.UpdateInterviewerDTO;
import com.ims.qa.model.Interviewer;
import com.ims.qa.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.lang.annotation.Native;
import java.util.List;

@Repository
public interface InterviewerRepository extends JpaRepository<Interviewer, Long> {

    @Query("Select COUNT(*) from Interviewer ir where ir.email = :email")
    int checkInterviewerExists(@Param("email") String email);

    int countAllByActiveTrue();

    List<Interviewer> findAllByActiveTrue(Pageable pageable);

    List<Interviewer> findTop5ByActiveTrue();
    @Query(
            name = "TopInterviewerQuery",
            nativeQuery = true)
    List<TopInterviewerDTO> findTopWithInterviewsNumber();

    @Query(
            name = "getInterviewerStatistic",
            nativeQuery = true)
    List<InterviewerStatisticDTO> getInterviewerStatistic(int id);

    @Modifying(clearAutomatically = true)
    @Query("Update Interviewer i SET i.active = :status WHERE i.id = :id")
    int updateInterviewerStatus(@Param("id") Long id, @Param("status") boolean status);

    @Modifying
    @Query("UPDATE Interviewer i SET i.firstname = :#{#dto.firstname}, i.lastname = :#{#dto.lastname}, i.email = :#{#dto.email} " +
            "WHERE i.id = :#{#dto.id}")
    int update(@Param("dto") UpdateInterviewerDTO dto);

    @Query(
            name = "InterviewerQuery",
            nativeQuery = true)
    List<InterviewerDTO> findAllWithInterviews();

}
