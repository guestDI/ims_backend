package com.ims.qa.controller;

import com.ims.qa.model.Interview;
import com.ims.qa.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/interviews")
public class InterviewController {
    @Autowired
    InterviewService interviewService;

    @RequestMapping(value = "/getAllInterviews", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Interview> findAll() {
        return interviewService.getAll();
    }

    @RequestMapping(value = "/updateInterviewToInactive/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer updateInterviewToInactive(@PathVariable("id") Long id) {
        return interviewService.setInterviewerAsInactive(id);
    }
}
