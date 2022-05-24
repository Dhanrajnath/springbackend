package com.greencommute.backend.controller;


import com.greencommute.backend.dto.JobsDto;
import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.exception.DataNotFoundException;
import com.greencommute.backend.helper.Helper;
import com.greencommute.backend.mapper.JobMapper;
import com.greencommute.backend.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/v1/jobs")
public class JobsController {

    @Autowired
    private JobService jobService;

    @Autowired
    public Helper helper;

    @Autowired
    public JobMapper jobMapper;

    @GetMapping
    public List<JobsDto> getAllJobs(@RequestParam(value="location",required = false) String loc, @RequestParam(value="skill",required = false) String[] skill) {
        List<Jobs> jobsList = jobService.getAllJobs();
        if(loc==null && skill ==null){
            return jobMapper.toJobDtoList(jobsList);
        } else if (loc==null) {
            List<Jobs> jobsList1 = helper.getJobsSearchBySkills(jobsList,skill);
            return jobMapper.toJobDtoList(jobsList1);
        } else if (skill==null) {
            List<Jobs> jobsList2=jobService.getJobsSearchByLocation(loc);
           return jobMapper.toJobDtoList(jobsList2);
        } else{
            List<Jobs> jobsByLoc =jobService.getJobsSearchByLocation(loc);
            List<Jobs> jobsByLocSkill=helper.getJobsSearchBySkills(jobsByLoc,skill);
            return jobMapper.toJobDtoList(jobsByLocSkill);
        }
    }

    @GetMapping("/{id}")
    public JobsDto getJobById(@PathVariable(value="id") int id){
        Optional<Jobs> job = jobService.getJobById(id);
        if (job.isEmpty()){
            throw new DataNotFoundException("No job found with id: "+id);
        }
        return jobMapper.toJobsDto(job.get());
    }
}