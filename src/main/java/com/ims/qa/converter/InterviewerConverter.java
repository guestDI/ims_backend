package com.ims.qa.converter;

import com.ims.qa.dto.InterviewerDTO;
import com.ims.qa.dto.UpdateInterviewerDTO;
import com.ims.qa.model.Interviewer;
import com.ims.qa.repository.InterviewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InterviewerConverter {
    @Autowired
    private InterviewerRepository interviewerRepository;

    public UpdateInterviewerDTO convertInterviewerInfo(Interviewer interviewer) {
        return new UpdateInterviewerDTO(interviewer.getId(), interviewer.getFirstname(), interviewer.getLastname(),
                interviewer.getEmail());

    }

    public Interviewer convertInterviewerInfo(UpdateInterviewerDTO updateInterviewerDTO){
        Interviewer interviewer = interviewerRepository.getOne(updateInterviewerDTO.getId());

        return new Interviewer(updateInterviewerDTO.getFirstname(), updateInterviewerDTO.getLastname(),
                updateInterviewerDTO.getEmail(), interviewer.getLocation(), interviewer.getLevel());
    }

}
