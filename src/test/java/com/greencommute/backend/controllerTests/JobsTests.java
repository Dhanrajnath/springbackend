package com.greencommute.backend.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greencommute.backend.controller.JobsController;
import com.greencommute.backend.dto.JobsDto;
import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.entity.Skills;
import com.greencommute.backend.helper.Helper;
import com.greencommute.backend.mapper.JobMapper;
import com.greencommute.backend.service.JobService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobsTests {

    @Mock
    JobService jobService;

    @InjectMocks
    JobsController jobsController;

    @Mock
    Helper helper;

    @Mock
    JobMapper jobMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(jobsController).build();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getJobByIdTest() throws Exception {
        Jobs job = new Jobs(1, "Software Engineer", "Developer", "Hyderabad", null, null);
        JobsDto jobDto = jobMapper.toJobsDto(job);
        Optional<Jobs> jobsOptional = Optional.of(job);

        when(jobService.getJobById(1)).thenReturn(jobsOptional);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/jobs/1").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(jobDto))).
                andDo(MockMvcResultHandlers.print());
        verify(jobService).getJobById(1);
        verify(jobService, times(1)).getJobById(1);
    }


    @Test
    void getAllJobsTest() throws Exception {
        Jobs job = new Jobs(1,"Software Engineer","Developer","Hyderabad",null,null);
        Skills skills = new Skills(1,"c",null);
        List<Skills> skillsList = new ArrayList<>();
        skillsList.add(skills);
        job.setSkillList(skillsList);
        JobsDto jobDto = jobMapper.toJobsDto(job);
        Optional<Jobs> jobsOptional = Optional.of(job);
        List<Jobs> jobsList = new ArrayList<>();
        jobsList.add(job);
        List<JobsDto> jobsDtoList = jobMapper.toJobDtoList(jobsList);
        when(jobService.getAllJobs()).thenReturn(jobsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/jobs").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(jobsDtoList))).
                andDo(MockMvcResultHandlers.print());
        verify(jobService).getAllJobs();
        verify(jobService,times(1)).getAllJobs();

        when(jobService.getJobsSearchByLocation("Hyderabad")).thenReturn(jobsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/jobs?location=Hyderabad").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(jobsDtoList))).
                andDo(MockMvcResultHandlers.print());
        verify(jobService).getJobsSearchByLocation("Hyderabad");
        verify(jobService,times(1)).getJobsSearchByLocation("Hyderabad");


        String[] searchSkill = new String[]{"c"};
        when(helper.getJobsSearchBySkills(jobsList,searchSkill)).thenReturn(jobsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/jobs?skill=c").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(jobsDtoList))).
                andDo(MockMvcResultHandlers.print());
        verify(helper).getJobsSearchBySkills(jobsList,searchSkill);
        verify(helper,times(1)).getJobsSearchBySkills(jobsList,searchSkill);


        when(helper.getJobsSearchBySkills(jobsList,searchSkill)).thenReturn(jobsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/jobs?location=Hyderabad&skill=c").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(jobsDtoList))).
                andDo(MockMvcResultHandlers.print());
        verify(helper,times(2)).getJobsSearchBySkills(jobsList,searchSkill);
    }

}
