package com.ims.qa.converter;

import com.ims.qa.dto.InterviewerDTO;
import com.ims.qa.model.Interview;
import com.ims.qa.model.Interviewer;
import com.ims.qa.repository.InterviewRepository;
import com.ims.qa.repository.InterviewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InterviewerConverter {
    @Autowired
    private InterviewerRepository interviewerRepository;

    public InterviewerDTO convertInterviewerInfo(Interviewer interviewer) {
        return new InterviewerDTO(interviewer.getId(), interviewer.getFirstname(), interviewer.getLastname(),
                interviewer.getEmail());

    }

    public Interviewer convertInterviewerInfo(InterviewerDTO interviewerDTO){
        Interviewer interviewer = interviewerRepository.getOne(interviewerDTO.getId());

        return new Interviewer(interviewerDTO.getFirstname(), interviewerDTO.getLastname(),
                interviewerDTO.getEmail(), interviewer.getLocation(), interviewer.getLevel());
    }
}
