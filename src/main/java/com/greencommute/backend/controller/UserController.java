package com.greencommute.backend.controller;

import com.greencommute.backend.dto.ResponseDto;
import com.greencommute.backend.dto.UserDto;
import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.entity.SavedJobs;
import com.greencommute.backend.entity.User;
import com.greencommute.backend.exception.DataNotFoundException;
import com.greencommute.backend.mapper.UserMapper;
import com.greencommute.backend.repository.SavedJobsJpa;
import com.greencommute.backend.service.JobServiceImpl;
import com.greencommute.backend.service.SavedJobServiceImpl;
import com.greencommute.backend.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
     private UserServiceImpl userService;

    @Autowired
     private SavedJobServiceImpl savedJobService;

    @Autowired
     private JobServiceImpl jobService;

    @Autowired
    private SavedJobsJpa savedJobsJpa;

    @Autowired
    public UserMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(value = "id") int id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isEmpty()) {
            throw new DataNotFoundException("No user with id: " + id);
        }
        UserDto userDto = userMapper.toUserDto(user.get());
        return ResponseEntity.ok().body(userDto);
    }

    @GetMapping("/{id}/savedJobs")
    public List<Jobs> getSavedJobsOfUser(@PathVariable(value = "id") int id) {
        return savedJobService.getSavedJobsByUserId(id);
    }

    @PostMapping("/{id}/savedJobs")
    public ResponseEntity<ResponseDto> saveJobsForUser(@PathVariable(value = "id") int id, @RequestBody Map<String,Integer> reqPayload) {
        final String jobId = "jobId";
        List<Jobs> savedJobList= savedJobService.getSavedJobsByUserId(id).stream().filter(jobs -> {
            int savedJobId=jobs.getJobId();
            return savedJobId == reqPayload.get(jobId);
        }).collect(Collectors.toList());
        if(!savedJobList.isEmpty()){
            ResponseDto responseDto = new ResponseDto();
            responseDto.setUserId(id);
            responseDto.setJobId(reqPayload.get(jobId));
            responseDto.setMessage("Job already added to saved jobs");
            return ResponseEntity.badRequest().body(responseDto);
        }
        else {
            Optional<User> user = userService.getUserById(id);
            Optional<Jobs> job = jobService.getJobById(reqPayload.get(jobId));
            if(user.isPresent()&& job.isPresent())
                savedJobService.saveToSavedJobs(user.get(), job.get());
            ResponseDto responseDto = new ResponseDto();
            responseDto.setUserId(id);
            responseDto.setJobId(reqPayload.get(jobId));
            responseDto.setMessage("Successfully added to saved jobs");
            return ResponseEntity.ok().body(responseDto);
        }
    }

    @DeleteMapping("/{id}/savedJobs")
    public ResponseEntity<ResponseDto> deleteSavedJobsOfUser(@PathVariable(value="id") int id, @RequestBody Map<String,Integer> reqPayload)  throws DataNotFoundException  {
        final String jobId = "jobId";
        SavedJobs savedJob = savedJobsJpa.findByUserAndJobId(id, reqPayload.get(jobId));
        if(savedJob == null){
            throw  new DataNotFoundException("No saved job found with user id and job id");
        }
        savedJobService.deleteSavedJobs(savedJob);
        ResponseDto responseDto=new ResponseDto();
        responseDto.setUserId(id);
        responseDto.setJobId(reqPayload.get(jobId));
        responseDto.setMessage("Successfully deleted saved job");
        return ResponseEntity.ok().body(responseDto);
    }
}