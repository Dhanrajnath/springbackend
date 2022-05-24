package com.greencommute.backend.serviceTests;

import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.repository.JobsJpa;
import com.greencommute.backend.service.JobService;
import com.greencommute.backend.service.JobServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class JobTests {

    @Autowired
    private JobService jobService;

    @Mock
    private JobsJpa jobsJpa;

    @BeforeEach
    void initUseCase(){
        jobService = new JobServiceImpl(jobsJpa);
    }

    @Test
    void getJobByIdTest() {
        Jobs job = new Jobs(1,"Software Engineer","Developer","Hyderabad",null,null);
        Optional<Jobs> jobsOptional = Optional.of(job);
        Mockito.when(jobsJpa.findById(1)).thenReturn(jobsOptional);
        Assertions.assertEquals(jobsOptional, jobService.getJobById(1));
        Mockito.verify(jobsJpa).findById(1);
    }

    @Test
    void getAllJobsTest() {
        List<Jobs> jobsList = new ArrayList<>();
        Jobs job1 = new Jobs(1,"Software Engineer","Developer","Hyderabad",null,null);
        Jobs job2 = new Jobs(2,"Software Engineer","Developer","Hyderabad",null,null);
        jobsList.add(job1);
        jobsList.add(job2);
        Mockito.when(jobsJpa.findAll()).thenReturn(jobsList);
        Assertions.assertEquals(jobsList, jobService.getAllJobs());
        Mockito.verify(jobsJpa).findAll();
    }

    @Test
    void getJobsSearchByLocationTest() {
        List<Jobs> jobsList = new ArrayList<>();
        Jobs job = new Jobs(1,"Software Engineer","Developer","Hyderabad",null,null);
        jobsList.add(job);
        Mockito.when(jobsJpa.getJobsByLocation("Hyderabad")).thenReturn(jobsList);
        Assertions.assertEquals(jobsList, jobService.getJobsSearchByLocation("Hyderabad"));
        Mockito.verify(jobsJpa).getJobsByLocation("Hyderabad");
    }
}
