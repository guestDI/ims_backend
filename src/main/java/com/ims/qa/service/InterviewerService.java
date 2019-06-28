package com.ims.qa.service;

import com.ims.qa.converter.InterviewerConverter;
import com.ims.qa.dto.InterviewerDTO;
import com.ims.qa.model.Interviewer;
import com.ims.qa.repository.InterviewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InterviewerService {
    @Autowired
    private InterviewerRepository interviewerRepository;

    @Autowired
    private InterviewerConverter interviewerConverter;

    public Interviewer addInterviewer(Interviewer interviewer){
        if(interviewerRepository.checkInterviewerExists(interviewer.getEmail()) > 0){
            throw new RuntimeException("Interviewer already exists");
        }
        return interviewerRepository.save(interviewer);
    }

    public void updateInterviewerProfile(InterviewerDTO interviewerDTO){
        interviewerRepository.update(interviewerDTO);
    }

    public Integer updateInterviewerStatus(Long id, boolean status){
        return interviewerRepository.updateInterviewerStatus(id, status);
    }


    public Iterable<Interviewer> getAll(int page, int size){
        Pageable pageWithSize = PageRequest.of(page, size, Sort.by("firstname").ascending().and(Sort.by("lastname").ascending()));
        return interviewerRepository.findAllByActiveTrue(pageWithSize);
    }

    public Iterable<Interviewer> getTopInterviewers(){
        return interviewerRepository.findTop5ByActiveTrue();
    }

    public InterviewerDTO getInterviewerProfile(Long id){
        return interviewerConverter.convertInterviewerInfo(interviewerRepository.getOne(id));
    }
}
