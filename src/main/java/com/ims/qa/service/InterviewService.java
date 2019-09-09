package com.ims.qa.service;

import com.ims.qa.converter.InterviewConverter;
import com.ims.qa.dto.InterviewDTO;
import com.ims.qa.dto.InterviewStatisticDTO;
import com.ims.qa.dto.UpdateInterviewDTO;
import com.ims.qa.enums.CandidateStatus;
import com.ims.qa.enums.InterviewStatus;
import com.ims.qa.model.Candidate;
import com.ims.qa.model.Interview;
import com.ims.qa.model.Interviewer;
import com.ims.qa.repository.CandidateRepository;
import com.ims.qa.repository.InterviewRepository;
import com.ims.qa.repository.InterviewerRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InterviewService {
    @Autowired
    private InterviewRepository interviewRepository;

    @Autowired
    private InterviewerRepository interviewerRepository;

    @Autowired
    private CandidateRepository candidateRepository;


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

    public ZonedDateTime getInterviewDateForCandidate(Long id){
        return interviewRepository.getInterviewDateByCandidateId(id);
    }

    public InterviewDTO create(InterviewDTO dto) {
        Interview entity = interviewConverter.convert(dto);
        entity = interviewRepository.save(entity);

        return interviewConverter.convert(entity);
    }

    public void delete(Long id){
        interviewRepository.deleteById(id);
    }

    public InterviewDTO update(InterviewDTO dto) throws RuntimeException {
        Interview entity = interviewRepository.findById(dto.getId())
                .orElseThrow(()-> new RuntimeException("Interview with id=" + dto.getId() + " not found"));

        entity = interviewConverter.convert(dto, entity);

        interviewRepository.save(entity);

        return interviewConverter.convert(entity);
    }
}
