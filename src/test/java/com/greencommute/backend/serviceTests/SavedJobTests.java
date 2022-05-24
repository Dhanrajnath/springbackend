package com.greencommute.backend.serviceTests;

import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.entity.SavedJobs;
import com.greencommute.backend.entity.User;
import com.greencommute.backend.repository.SavedJobsJpa;
import com.greencommute.backend.repository.UserJpa;
import com.greencommute.backend.service.SavedJobServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;


@RunWith(SpringRunner.class)
@SpringBootTest
class SavedJobTests {

    @Autowired
    private SavedJobServiceImpl savedJobService;

    @Mock
    SavedJobsJpa savedJobsJpa;

    @Mock
    UserJpa userJpa;

    @BeforeEach
    void initUseCase(){
        savedJobService = new SavedJobServiceImpl(userJpa,savedJobsJpa);
    }


    @Test
    void saveJobTest() {
        User user = new User(1,"user",null);
        Jobs job =  new Jobs(1, "Software Engineer", "Developer", "Hyderabad", null, null);

        SavedJobs savedJobs = new SavedJobs(new Timestamp(System.currentTimeMillis()), user, job);
        Mockito.when(savedJobsJpa.save(savedJobs)).thenReturn(savedJobs);
        savedJobService.saveToSavedJobs(user,job);
        Assertions.assertEquals(savedJobs,savedJobsJpa.save(savedJobs));
        Mockito.verify(savedJobsJpa).save(savedJobs);
    }

    @Test
    void getSavedJobByUserIdTest() {
        User user = new User(1,"user1",null);
        Jobs job = new Jobs(1,"Software Engineer","Developer","Hyderabad",null,null);
        List<Jobs> jobsList = new ArrayList<>();
        jobsList.add(job);
        SavedJobs savedJobs = new SavedJobs(new Timestamp(System.currentTimeMillis()),user,job);
        List<SavedJobs> savedJobsList = new ArrayList<>();
        savedJobsList.add(savedJobs);
        Optional<User> userOptional = Optional.of(new User(1,"user1",savedJobsList));
        Mockito.when(userJpa.findById(1)).thenReturn(userOptional);
        List<Jobs> jobsList1 = savedJobService.getSavedJobsByUserId(1);
        Assertions.assertEquals(savedJobsList.get(0).getJobs().getJobId(),jobsList1.get(0).getJobId());
        Mockito.verify(userJpa).findById(1);

    }

    @Test
    void getSavedJobByUserIdTest1() {
       User user = new User();
        List<Jobs> jobList = new ArrayList<>();

       Mockito.when(userJpa.findById(1)).thenReturn(Optional.empty());
       Assertions.assertEquals(jobList,savedJobService.getSavedJobsByUserId(1));
       Mockito.verify(userJpa).findById(1);
    }

    @Test
    void deleteSavedJobTest1() {
        User user = new User(1,"user",null);
        Jobs job =  new Jobs(1, "Software Engineer", "Developer", "Hyderabad", null, null);
        SavedJobs savedJobs = new SavedJobs(new Timestamp(System.currentTimeMillis()), user, job);

        Mockito.when(savedJobsJpa.findByUserAndJobId(1,1)).thenReturn(savedJobs);
        doNothing().when(savedJobsJpa).delete(savedJobs);
        savedJobService.deleteSavedJobs(savedJobs);
        Mockito.verify(savedJobsJpa).delete(savedJobs);
    }

}