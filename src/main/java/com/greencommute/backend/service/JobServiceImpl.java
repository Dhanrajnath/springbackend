package com.greencommute.backend.service;

import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.repository.JobsJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    private final JobsJpa jobsJpa;

    @Autowired
    public JobServiceImpl(JobsJpa jobsJpa) {
        this.jobsJpa = jobsJpa;
    }

    @Override
    public Optional<Jobs> getJobById(int id) {
        return jobsJpa.findById(id);
    }

    @Override
    public List<Jobs> getAllJobs() {
        return jobsJpa.findAll();
    }

    @Override
    public List<Jobs> getJobsSearchByLocation(String location){
        return jobsJpa.getJobsByLocation(location);
    }
}
