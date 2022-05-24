package com.greencommute.backend.mapperTests;

import com.greencommute.backend.dto.JobsDto;
import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.mapper.JobMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class JobsTests {

    @Mock
    JobMapper jobMapper;

    @Test
    void toJobsDtoTest() {
        Jobs jobs = new Jobs(1,"software engineer","developer","hyderabad",null,null);
        JobsDto jobsDto = new JobsDto(1,"software engineer","developer","hyderabad",null,null);
        Mockito.when(jobMapper.toJobsDto(jobs)).thenReturn(jobsDto);
        Assertions.assertEquals(jobsDto,jobMapper.toJobsDto(jobs));
        Mockito.verify(jobMapper).toJobsDto(jobs);
    }

    @Test
    void toJobsDtoListTest() {
        Jobs jobs = new Jobs(1,"software engineer","developer","hyderabad",null,null);
        JobsDto jobsDto = new JobsDto(1,"software engineer","developer","hyderabad",null,null);
        List<Jobs> jobsList = new ArrayList<>();
        List<JobsDto> jobsDtoList = new ArrayList<>();
        jobsList.add(jobs);
        jobsDtoList.add(jobsDto);
        Mockito.when(jobMapper.toJobDtoList(jobsList)).thenReturn(jobsDtoList);
        Assertions.assertEquals(jobsDtoList,jobMapper.toJobDtoList(jobsList));
        Mockito.verify(jobMapper).toJobDtoList(jobsList);

    }
}
