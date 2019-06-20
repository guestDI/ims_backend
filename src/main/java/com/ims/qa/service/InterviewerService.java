package com.ims.qa.service;

import com.ims.qa.model.Interviewer;
import com.ims.qa.repository.InterviewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InterviewerService {
    @Autowired
    private InterviewerRepository interviewerRepository;

    public Interviewer addInterviewer(Interviewer interviewer){
        if(interviewerRepository.checkInterviewerExists(interviewer.getEmail()) > 0){
            throw new RuntimeException("Interviewer already exists");
        }
        return interviewerRepository.save(interviewer);
    }

    public Integer updateInterviewerStatus(Long id, boolean status){
        return interviewerRepository.updateInterviewerStatus(id, status);
    }


    public Iterable<Interviewer> getAll(){
        return interviewerRepository.findAll();
    }

    public Iterable<Interviewer> getTopInterviewers(){
        return interviewerRepository.findTop5ByActiveTrueOrderByNumberOfInterviewsDesc();
    }
}
