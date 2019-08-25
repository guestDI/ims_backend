package com.ims.qa.service;

import com.ims.qa.dto.*;
import com.ims.qa.enums.CandidateStatus;
import com.ims.qa.model.Candidate;
import com.ims.qa.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ims.qa.enums.CandidateStatus.JO_ACCEPTED;
import static com.ims.qa.enums.CandidateStatus.STARTED;

@Service
@Transactional
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public Iterable<Candidate> getAll(int page, int size){
        Pageable pageWithSize = PageRequest.of(page, size, Sort.by("id").descending().and(Sort.by("lastname").ascending()));
        return candidateRepository.findAllByActiveTrue(pageWithSize);
    }

    public Integer getNumberOfAllCandidates(){
        return candidateRepository.countAllByActiveTrue();
    }

    public Iterable<CandidateLevelDTO> getLevelsWithNumberForDate(String date){
        switch (date) {
            case "currentMonth":
                return candidateRepository.findLevelsWithNumberCurrentMonth();
            case "prevMonth":
                return candidateRepository.findLevelsWithNumberPrevMonth();
            case "currentYear":
                return candidateRepository.findLevelsWithNumberCurrentYear();
            case "prevYear":
                return candidateRepository.findLevelsWithNumberPrevYear();
        }

        return null;
    }

    public Integer getNumberOfCandidatesByStatus(CandidateStatus candidateStatus){
        return candidateRepository.countAllByActiveTrueAndCandidateStatusEquals(candidateStatus);
    }

    public Iterable<CandidateLocationDTO> getLocationWithNumberForDate(String date){
        switch (date) {
            case "currentMonth":
                return candidateRepository.findLocationsWithNumberCurrentMonth();
            case "prevMonth":
                return candidateRepository.findLocationsWithNumberPrevMonth();
            case "currentYear":
                return candidateRepository.findLocationsWithNumberCurrentYear();
            case "prevYear":
                return candidateRepository.findLocationsWithNumberPrevYear();
        }

        return null;
    }

    public Candidate addCandidate(CandidateDetailsDTO candidateDetailsDTO){
        Candidate candidate;
        if(candidateRepository.checkCandidateExists(candidateDetailsDTO.getFirstname(), candidateDetailsDTO.getLastname()) > 0){
            throw new RuntimeException("Candidate already exists in database");
        }

        candidate = new Candidate();
        candidate.setFirstname(candidateDetailsDTO.getFirstname());
        candidate.setLastname(candidateDetailsDTO.getLastname());
        candidate.setLevel(candidateDetailsDTO.getLevel());
        candidate.setLocation(candidateDetailsDTO.getLocation());
        candidate.setCandidateStatus(candidateDetailsDTO.getCandidateStatus());
        candidate.setSkills(candidateDetailsDTO.getSkills());
        candidate.setComment(candidateDetailsDTO.getComment());

        return candidateRepository.save(candidate);
    }

    public Candidate updateCandidateProfile(UpdateCandidateDTO updateCandidateDTO){
        Candidate candidate = candidateRepository.findById(updateCandidateDTO.getId())
                .orElseThrow(NullPointerException::new);

        candidate.setFirstname(updateCandidateDTO.getFirstname());
        candidate.setLastname(updateCandidateDTO.getLastname());
        candidate.setLocation(updateCandidateDTO.getLocation());
        candidate.setLevel(updateCandidateDTO.getLevel());
        candidate.setSkills(updateCandidateDTO.getSkills());
        candidate.setComment(updateCandidateDTO.getComment());

        return candidateRepository.save(candidate);

    }

    public Iterable<NewcomerDTO> getCandidatesWithStartDate() {
//        statuses.forEach(System.out::println);

        return candidateRepository.getNewcomers();
    }
}
