package com.ims.qa.controller;

import com.ims.qa.dto.InterviewerDTO;
import com.ims.qa.model.Interviewer;
import com.ims.qa.repository.InterviewerRepository;
import com.ims.qa.service.InterviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/interviewers")
public class InterviewerController {
    @Autowired
    private InterviewerService interviewerService;

    @Autowired
    private InterviewerRepository interviewerRepository;

    @RequestMapping(value = "/addInterviewer", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Interviewer addInterviewer(@RequestBody Interviewer interviewer) {
        return interviewerService.addInterviewer(interviewer);
    }

    @RequestMapping(value = "/updateInterviewerProfile", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Interviewer updateUser(@RequestBody InterviewerDTO interviewerDTO) {
        return interviewerService.updateInterviewerProfile(interviewerDTO);
    }

    @RequestMapping(value = "/updateInterviewerStatus/{id}/{status}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer updateUserStatus(@PathVariable("id") Long id, @PathVariable("status") boolean status) {
        return interviewerService.updateInterviewerStatus(id, status);
    }

    @RequestMapping(value = "/getAllInterviewers/{page}/{size}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Interviewer> findAll(@PathVariable("page") int page, @PathVariable("size") int size) {
        return interviewerService.getAll(page, size);
    }

    @RequestMapping(value = "/getCountOfActiveInterviewers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public int findCountOfActiveInterviewers() {
        return interviewerRepository.countAllByActiveTrue();
    }

    @RequestMapping(value = "/getTopInterviewers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Interviewer> findTopInterviewers() {
        return interviewerService.getTopInterviewers();
    }

    @RequestMapping(value = "/getInterviewerInfo/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterviewerDTO> getUserInfo(@PathVariable("id") Long id) {
        return new ResponseEntity<>(interviewerService.getInterviewerProfile(id), HttpStatus.OK);

    }
}
