package com.greencommute.backend.entityTests;

import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.entity.SavedJobs;
import com.greencommute.backend.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
class SavedJobsEntityTests {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    User user = new User(1,"user1",null);
    Jobs jobs = new Jobs(1,"software engineer","developer","hyderabad",null,null);

    SavedJobs savedJobs = new SavedJobs(timestamp,user,jobs);

    @Test
    void savedJobsEntityTests() {
        Assertions.assertEquals(timestamp,savedJobs.getCreatedAt());
        Assertions.assertEquals(user,savedJobs.getUser());
        Assertions.assertEquals(jobs,savedJobs.getJobs());
        Assertions.assertEquals(0,savedJobs.getSavedJobId());

        savedJobs = new SavedJobs();
        savedJobs.setCreatedAt(timestamp);
        savedJobs.setUser(user);
        savedJobs.setJobs(jobs);
        savedJobs.setSavedJobId(1);

        Assertions.assertEquals(timestamp,savedJobs.getCreatedAt());
        Assertions.assertEquals(user,savedJobs.getUser());
        Assertions.assertEquals(jobs,savedJobs.getJobs());
        Assertions.assertEquals(1,savedJobs.getSavedJobId());


    }

}
