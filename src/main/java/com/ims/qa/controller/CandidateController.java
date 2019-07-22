package com.ims.qa.controller;

import com.ims.qa.model.Candidate;
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

    @RequestMapping(value = "/getAllCandidates/{page}/{size}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Candidate> findAll(@PathVariable("page") int page, @PathVariable("size") int size) {
        return candidateService.getAll(page, size);
    }
}
