package com.ims.qa.service;

import com.ims.qa.dto.CandidateLevelDTO;
import com.ims.qa.enums.CandidateStatus;
import com.ims.qa.model.Candidate;
import com.ims.qa.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public Iterable<Candidate> getAll(int page, int size){
        Pageable pageWithSize = PageRequest.of(page, size, Sort.by("startDate").ascending().and(Sort.by("lastname").ascending()));
        return candidateRepository.findAllByActiveTrue(pageWithSize);
    }

    public Integer getNumberOfAllCandidates(){
        return candidateRepository.countAllByActiveTrue();
    }

    public Iterable<CandidateLevelDTO> getLevelsWithNumber(){
        return candidateRepository.findLevelsWithNumber();
    }

    public Integer getNumberOfCandidatesByStatus(CandidateStatus candidateStatus){
        return candidateRepository.countAllByActiveTrueAndCandidateStatusEquals(candidateStatus);
    }
}
