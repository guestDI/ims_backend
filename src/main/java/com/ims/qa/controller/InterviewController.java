package com.ims.qa.controller;

import com.ims.qa.model.Interview;
import com.ims.qa.model.Interviewer;
import com.ims.qa.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
