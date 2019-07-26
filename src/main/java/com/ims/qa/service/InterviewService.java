package com.ims.qa.service;

import com.ims.qa.enums.CandidateStatus;
import com.ims.qa.enums.InterviewStatus;
import com.ims.qa.model.Interview;
import com.ims.qa.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
@Transactional
public class InterviewService {
    @Autowired
    private InterviewRepository interviewRepository;

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
}
