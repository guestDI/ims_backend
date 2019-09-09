package com.ims.qa.controller;

import com.ims.qa.dto.InterviewDTO;
import com.ims.qa.dto.InterviewStatisticDTO;
import com.ims.qa.dto.UpdateInterviewDTO;
import com.ims.qa.enums.CandidateStatus;
import com.ims.qa.enums.InterviewStatus;
import com.ims.qa.model.Interview;
import com.ims.qa.repository.InterviewRepository;
import com.ims.qa.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/interviews")
public class InterviewController {
    @Autowired
    InterviewService interviewService;

    private InterviewRepository interviewRepository;

    @RequestMapping(value = "/getAllInterviews/{page}/{size}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Interview> findAll(@PathVariable("page") int page, @PathVariable("size") int size) {
        return interviewService.getAll(page, size);
    }

    @RequestMapping(value = "/updateInterviewToInactive/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer updateInterviewToInactive(@PathVariable("id") Long id) {
        return interviewService.setInterviewerAsInactive(id);
    }

    @RequestMapping(value = "/getNumberOfAllInterviews", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer getNumberOfInterviews(){
        return interviewService.getNumberOfAllInterviews();
    }

    @RequestMapping(value = "/getNumberOfInterviewsByDate/{date}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<InterviewStatisticDTO> getNumberOfInterviewsByDate(@PathVariable ("date") String date ){
        return interviewService.getNumberOfInterviewsByDate(date);
    }

    @RequestMapping(value = "/getInterviewByCandidateId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ZonedDateTime getInterviewDate(@PathVariable ("id") Long id ){
        return interviewService.getInterviewDateForCandidate(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public InterviewDTO create(@RequestBody InterviewDTO dto) {
        //validator
        return interviewService.create(dto);
    }

    @RequestMapping(value = "/updateInterview", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public InterviewDTO update(@RequestBody InterviewDTO dto) {
        //validator
        return interviewService.update(dto);
    }

    @RequestMapping(value = "/deleteInterview/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable ("id") Long id){
        interviewService.delete(id);
    }
//    @RequestMapping(value = "/getNumberOfAllInterviewsByStatus/{interviewStatus}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Integer getNumberOfInterviewsByStatus(@PathVariable("interviewStatus") InterviewStatus interviewStatus){
//        return interviewService.getNumberOfInterviewsByStatus(interviewStatus);
//    }
}
