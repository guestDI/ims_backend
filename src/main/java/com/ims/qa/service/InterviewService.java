package com.ims.qa.service;

import com.ims.qa.model.Interview;
import com.ims.qa.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InterviewService {
    @Autowired
    private InterviewRepository interviewRepository;

    public Iterable<Interview> getAll(){
        return interviewRepository.findAllByActiveTrue();
    }

    public Integer setInterviewerAsInactive(Long id){
        return interviewRepository.updateInterviewToInactive(id);
    }
}
