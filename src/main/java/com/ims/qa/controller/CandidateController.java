package com.ims.qa.controller;

import com.ims.qa.dto.*;
import com.ims.qa.enums.CandidateStatus;
import com.ims.qa.model.Candidate;
import com.ims.qa.repository.CandidateRepository;
import com.ims.qa.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CandidateRepository candidateRepository;

    @RequestMapping(value = "/getAllCandidates/{page}/{size}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Candidate> findAll(@PathVariable("page") int page, @PathVariable("size") int size) {
        return candidateService.getAll(page, size);
    }

    @RequestMapping(value = "/getNumberOfAllCandidates", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer getNumberOfCandidates(){
        return candidateService.getNumberOfAllCandidates();
    }

    @RequestMapping(value = "/getNumberOfLevels/{date}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<CandidateLevelDTO> countLevels(@PathVariable("date") String date){
        return candidateService.getLevelsWithNumberForDate(date);
    }

    @RequestMapping(value = "/getNumberOfAllCandidatesByStatus/{candidateStatus}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer getNumberOfInterviewsByStatus(@PathVariable("candidateStatus") CandidateStatus candidateStatus){
        return candidateService.getNumberOfCandidatesByStatus(candidateStatus);
    }

    @RequestMapping(value = "/getNumberOfLocations/{date}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<CandidateLocationDTO> countLocations(@PathVariable("date") String date)
    {
        return candidateService.getLocationWithNumberForDate(date);
    }

    @RequestMapping(value = "/addCandidate", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Candidate addCandidate(@RequestBody CandidateDetailsDTO candidate) {
        return candidateService.addCandidate(candidate);
    }

    @RequestMapping(value = "/updateCandidateProfile", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Candidate updateCandidate(@RequestBody UpdateCandidateDTO updateCandidateDTO) {
        return candidateService.updateCandidateProfile(updateCandidateDTO);
    }

    @RequestMapping(value = "/getCandidateInfo/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Candidate getCandidateInfoById(@PathVariable("id") Long id){
        return candidateRepository.findCandidateByActiveTrueAndId(id);
    }

    @RequestMapping(value = "/getNewcomers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<NewcomerDTO> getCandidatesAndStartDate(){
        return candidateService.getCandidatesWithStartDate();
    }

    @PostMapping(value = "/getCandidatesByName", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<CandidateDTO> getCandidatesByQuery(@RequestBody CandidateSearchDTO candidateSearchDTO) {
        return candidateService.getCandidateWithLocationAndLevel(candidateSearchDTO);
    }
}
