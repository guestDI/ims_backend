package com.ims.qa.service;

import com.ims.qa.converter.InterviewConverter;
import com.ims.qa.dto.InterviewDTO;
import com.ims.qa.dto.InterviewStatisticDTO;
import com.ims.qa.enums.CandidateStatus;
import com.ims.qa.enums.InterviewStatus;
import com.ims.qa.model.Interview;
import com.ims.qa.repository.InterviewRepository;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;

@Service
@Transactional
public class InterviewService {
    @Autowired
    private InterviewRepository interviewRepository;

    @Autowired
    private InterviewConverter interviewConverter;

    public Iterable<Interview> getAll(int page, int size){
        Pageable pageWithSize = PageRequest.of(page, size, Sort.by("date").descending());
        return interviewRepository.findAllByActiveTrue(pageWithSize);
    }

    public Integer setInterviewerAsInactive(Long id){
        return interviewRepository.updateInterviewToInactive(id);
    }

    public Integer getNumberOfAllInterviews(){
        return interviewRepository.countAllByActiveTrue();
    }

//    public Integer getNumberOfInterviewsByStatus(InterviewStatus interviewStatus){
//        return interviewRepository.countAllByActiveTrueAndStatusEquals(interviewStatus);
//    }

    public Iterable<InterviewStatisticDTO> getNumberOfInterviewsByDate(String date){
        try{
            if(date.equals("prevYear")){
                return interviewRepository.getDateWithNumberPrevYear();
            } else if(date.equals("currentYear")){
                return interviewRepository.getDateWithNumberCurrentYear();
            }
        } catch (SQLGrammarException se){
            se.getCause();

        }

        return null;
    }

    public Date getInterviewDateForCandidate(Long id){
        return interviewRepository.getInterviewDateByCandidateId(id);
    }

    public InterviewDTO create(InterviewDTO dto) {
        Interview entity = interviewConverter.convert(dto);
        entity = interviewRepository.save(entity);

        return interviewConverter.convert(entity);
    }

//    public Interview addInterviewRecord()
}
