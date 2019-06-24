package com.ims.qa.converter;

import com.ims.qa.dto.InterviewerDTO;
import com.ims.qa.model.Interview;
import com.ims.qa.model.Interviewer;
import org.springframework.stereotype.Component;

@Component
public class InterviewerConverter {
    public InterviewerDTO convertInterviewerInfo(Interviewer interviewer) {
        return new InterviewerDTO(interviewer.getId(), interviewer.getFirstname(), interviewer.getLastname(),
                interviewer.getEmail());

    }
}
