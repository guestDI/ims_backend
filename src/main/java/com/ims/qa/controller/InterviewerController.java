package com.ims.qa.controller;

import com.ims.qa.model.Interviewer;
import com.ims.qa.service.InterviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/interviewers")
public class InterviewerController {
    @Autowired
    private InterviewerService interviewerService;

    @RequestMapping(value = "/addInterviewer", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Interviewer addInterviewer(@RequestBody Interviewer interviewer) {
        return interviewerService.addInterviewer(interviewer);
    }

    @RequestMapping(value = "/updateInterviewerStatus/{id}/{status}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer updateUserStatus(@PathVariable("id") Long id, @PathVariable("status") boolean status) {
        return interviewerService.updateInterviewerStatus(id, status);
    }

    @RequestMapping(value = "/getAllInterviewers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Interviewer> findAll() {
        return interviewerService.getAll();
    }

    @RequestMapping(value = "/getTopInterviewers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Interviewer> findTopInterviewers() {
        return interviewerService.getTopInterviewers();
    }
}
