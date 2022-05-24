package com.greencommute.backend.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greencommute.backend.controller.UserController;
import com.greencommute.backend.dto.ResponseDto;
import com.greencommute.backend.dto.UserDto;
import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.entity.SavedJobs;
import com.greencommute.backend.entity.User;
import com.greencommute.backend.mapper.UserMapper;
import com.greencommute.backend.repository.SavedJobsJpa;
import com.greencommute.backend.service.JobServiceImpl;
import com.greencommute.backend.service.SavedJobServiceImpl;
import com.greencommute.backend.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.util.*;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserTests {

    @Mock
    UserServiceImpl userService;

    @Mock
    SavedJobServiceImpl savedJobService;

    @Mock
    JobServiceImpl jobService;

    @InjectMocks
    UserController userController;

    @Mock
    UserMapper userMapper;

    @Mock
    SavedJobsJpa savedJobsJpa;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getUserByIdTest() throws Exception{
        Optional<User> user = Optional.of(new User(1, "Dhanrajnath", null));
        UserDto userDto = userMapper.toUserDto(user.get());
        ResponseEntity<UserDto> responseEntity = new ResponseEntity<>(userDto,HttpStatus.OK);
        Mockito.when(userService.getUserById(1)).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/1").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(responseEntity))).
                andDo(MockMvcResultHandlers.print());
        verify(userService).getUserById(1);
        verify(userService, times(1)).getUserById(1);
    }

    @Test
    void getSavedJobsOfUserTest() throws Exception {
        Jobs job = new Jobs(1,"Software Engineer","Developer","Hyderabad",null,null);
        List<Jobs> jobList=new ArrayList<>();
        jobList.add(job);

        Mockito.when(savedJobService.getSavedJobsByUserId(1)).thenReturn(jobList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/1/savedJobs").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(jobList))).
                andDo(MockMvcResultHandlers.print());
        verify(savedJobService).getSavedJobsByUserId(1);
        verify(savedJobService, times(1)).getSavedJobsByUserId(1);
    }

    @Test
    void saveJobForUserTest1() throws Exception {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setUserId(1);
        responseDto.setJobId(1);
        responseDto.setMessage("Successfully added to saved jobs");
        ResponseEntity<String> responseEntity = new ResponseEntity<>(responseDto.getMessage(),HttpStatus.OK);
        Optional<User> user = Optional.of(new User(1, "Dhanrajnath", null));
        Optional<Jobs> job = Optional.of(new Jobs(1, "Software Engineer", "Developer", "Hyderabad", null, null));

        List<Jobs> jobsList = new ArrayList<>();
        jobsList.add(job.get());

        Map<String,Integer> reqPayload = new HashMap<>();
        reqPayload.put("jobId",6);
        Mockito.doNothing().when(savedJobService).saveToSavedJobs(user.get(),job.get());
        savedJobService.saveToSavedJobs(user.get(),job.get());
        Assertions.assertEquals(responseEntity.getBody(),userController.saveJobsForUser(1,reqPayload).getBody().getMessage());
        verify(savedJobService).saveToSavedJobs(user.get(),job.get());
        verify(savedJobService, times(1)).saveToSavedJobs(user.get(),job.get());

    }

    @Test
    void saveJobForUserTest2() throws Exception {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setUserId(1);
        responseDto.setJobId(1);
        responseDto.setMessage("Job already added to saved jobs");

        ResponseEntity<String> responseEntity = new ResponseEntity<>(responseDto.getMessage(),HttpStatus.OK);
        Optional<User> user = Optional.of(new User(1, "Dhanrajnath", null));
        Optional<Jobs> job = Optional.of(new Jobs(1, "Software Engineer", "Developer", "Hyderabad", null, null));

        List<Jobs> jobsList = new ArrayList<>();
        jobsList.add(job.get());

        Map<String,Integer> reqPayload = new HashMap<>();
        reqPayload.put("jobId",1);
        Mockito.doNothing().when(savedJobService).saveToSavedJobs(user.get(),job.get());
        Mockito.when(savedJobService.getSavedJobsByUserId(1)).thenReturn(jobsList);
        savedJobService.saveToSavedJobs(user.get(),job.get());
        Assertions.assertEquals(responseEntity.getBody(),userController.saveJobsForUser(1,reqPayload).getBody().getMessage());
        verify(savedJobService).saveToSavedJobs(user.get(),job.get());
        verify(savedJobService, times(1)).saveToSavedJobs(user.get(),job.get());

    }

    @Test
    void deleteSavedJobsTest() throws Exception {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setUserId(1);
        responseDto.setJobId(1);
        responseDto.setMessage("Successfully deleted saved job");

        ResponseEntity<String> responseEntity = new ResponseEntity<>(responseDto.getMessage(),HttpStatus.OK);
        Optional<User> user = Optional.of(new User(1, "Dhanrajnath", null));
        Optional<Jobs> job = Optional.of(new Jobs(1, "Software Engineer", "Developer", "Hyderabad", null, null));

        List<Jobs> jobsList = new ArrayList<>();
        jobsList.add(job.get());

        SavedJobs savedJob = new SavedJobs(new Timestamp(System.currentTimeMillis()),user.get(),job.get());

        Map<String,Integer> reqPayload = new HashMap<>();
        reqPayload.put("jobId",1);
        Mockito.when(savedJobsJpa.findByUserAndJobId(1,1)).thenReturn(savedJob);
        Mockito.doNothing().when(savedJobService).deleteSavedJobs(savedJob);
        Assertions.assertEquals(responseEntity.getBody(),userController.deleteSavedJobsOfUser(1,reqPayload).getBody().getMessage());
        verify(savedJobService).deleteSavedJobs(savedJob);
        verify(savedJobService, times(1)).deleteSavedJobs(savedJob);

    }

}
