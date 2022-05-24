package com.greencommute.backend.entityTests;

import com.greencommute.backend.entity.Jobs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JobsEntityTests {

    Jobs jobs = new Jobs(1,"software engineer","developer","hyderabad",null,null);
    @Test
    void jobsEntityTests(){
        Assertions.assertEquals(1, jobs.getJobId());
        Assertions.assertEquals("software engineer", jobs.getJobTitle());
        Assertions.assertEquals("developer",jobs.getJobDescription());
        Assertions.assertEquals("hyderabad",jobs.getJobLocation());
        Assertions.assertNull(jobs.getSkillList());
        Assertions.assertNull(jobs.getCommuteList());
        jobs=new Jobs();
        jobs.setJobId(2);
        jobs.setJobTitle("a");
        jobs.setJobDescription("b");
        jobs.setJobLocation("c");
        jobs.setSkillList(null);
        jobs.setCommuteList(null);

        Assertions.assertEquals(2, jobs.getJobId());
        Assertions.assertEquals("a", jobs.getJobTitle());
        Assertions.assertEquals("b",jobs.getJobDescription());
        Assertions.assertEquals("c",jobs.getJobLocation());
        Assertions.assertNull(jobs.getSkillList());
        Assertions.assertNull(jobs.getCommuteList());
    }

}
