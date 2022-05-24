package com.greencommute.backend.dtoTests;

import com.greencommute.backend.dto.JobsDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JobsDtoTests {
    JobsDto jobsDto = new JobsDto(1,"software engineer","developer","hyderabad",null,null);

    @Test
    void JobsDtoEntityTest(){
        Assertions.assertEquals(1, jobsDto.getJobId());
        Assertions.assertEquals("software engineer", jobsDto.getJobTitle());
        Assertions.assertEquals("developer",jobsDto.getJobDescription());
        Assertions.assertEquals("hyderabad",jobsDto.getJobLocation());
        Assertions.assertNull(jobsDto.getSkillList());
        Assertions.assertNull(jobsDto.getCommuteList());
        jobsDto=new JobsDto();
        jobsDto.setJobId(2);
        jobsDto.setJobTitle("a");
        jobsDto.setJobDescription("b");
        jobsDto.setJobLocation("c");
        jobsDto.setSkillList(null);
        jobsDto.setCommuteList(null);

        Assertions.assertEquals(2, jobsDto.getJobId());
        Assertions.assertEquals("a", jobsDto.getJobTitle());
        Assertions.assertEquals("b",jobsDto.getJobDescription());
        Assertions.assertEquals("c",jobsDto.getJobLocation());
        Assertions.assertNull(jobsDto.getSkillList());
        Assertions.assertNull(jobsDto.getCommuteList());
    }

}
