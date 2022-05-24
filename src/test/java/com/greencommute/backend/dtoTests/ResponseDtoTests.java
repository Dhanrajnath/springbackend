package com.greencommute.backend.dtoTests;

import com.greencommute.backend.dto.ResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ResponseDtoTests {

    ResponseDto responseDto = new ResponseDto(1,1,"success");

    @Test
    void ResponseDtoEntityTest(){
        Assertions.assertEquals(1,responseDto.getJobId());
        Assertions.assertEquals(1,responseDto.getUserId());
        Assertions.assertEquals("success",responseDto.getMessage());
        responseDto=new ResponseDto();
        responseDto.setJobId(2);
        responseDto.setUserId(2);
        responseDto.setMessage("Failed");

        Assertions.assertEquals(2,responseDto.getJobId());
        Assertions.assertEquals(2,responseDto.getUserId());
        Assertions.assertEquals("Failed",responseDto.getMessage());
    }
}
