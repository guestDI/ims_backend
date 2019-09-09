package com.ims.qa.converter;

import com.ims.qa.dto.InterviewDTO;
import com.ims.qa.dto.InterviewerDTO;
import com.ims.qa.dto.UpdateInterviewDTO;
import com.ims.qa.model.Candidate;
import com.ims.qa.model.Interview;
import com.ims.qa.model.Interviewer;
import com.ims.qa.repository.CandidateRepository;
import com.ims.qa.repository.InterviewerRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InterviewConverter {

    private final CandidateRepository candidateRepository;
    private final InterviewerRepository interviewerRepository;

    public Interview convert(InterviewDTO dto) {
        Candidate candidate = candidateRepository.findById(dto.getCandidateId())
                .orElseThrow(()-> new RuntimeException("Candidate with id=" + dto.getCandidateId() + " not found"));
        List<Interviewer> interviewers = dto.getInterviewers().stream()
                .map(interviewerDTO -> interviewerRepository.getOne(interviewerDTO.getId()))
                .collect(Collectors.toList());

        return Interview.builder()
                .id(dto.getId())
                .candidate(candidate)
                .date(dto.getDate())
                .interviewers(interviewers)
                .interviewStatus(dto.getStatus())
                .comment(dto.getComment())
                .build();

    }

    public Interview convert(InterviewDTO dto, Interview interview){
        if (dto == null) {
            return null;
        }

            List<Interviewer> interviewers = dto.getInterviewers().stream()
            .map(interviewerDTO -> interviewerRepository.getOne(interviewerDTO.getId()))
            .collect(Collectors.toList());

        return interview.toBuilder()
                .date(dto.getDate())
                .interviewers(interviewers)
                .interviewStatus(dto.getStatus())
                .comment(dto.getComment())
                .build();
    }

    public InterviewDTO convert(Interview entity) {
        List<InterviewerDTO> interviewers = entity.getInterviewers().stream()
                .map(this::convertInterviewer)
                .collect(Collectors.toList());

        return InterviewDTO.builder()
                .id(entity.getId())
                .candidateId(entity.getCandidate().getId())
                .date(entity.getDate())
                .interviewers(interviewers)
                .status(entity.getInterviewStatus())
                .comment(entity.getComment())
                .build();
    }

    private InterviewerDTO convertInterviewer(Interviewer interviewer) {
        return InterviewerDTO.builder()
                .id(interviewer.getId())
                .firstname(interviewer.getFirstname())
                .lastname(interviewer.getLastname())
                .build();
    }

}
