package com.greencommute.backend.dtoTests;

import com.greencommute.backend.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserDtoTests {

    UserDto userDto = new UserDto(1,"userDTO");


    @Test
    void userDtoEntityTest(){
        Assertions.assertEquals(1,userDto.getUserId());
        Assertions.assertEquals("userDTO",userDto.getUserName());
        userDto = new UserDto();
        userDto.setUserId(2);
        userDto.setUserName("user2");

        Assertions.assertEquals(2,userDto.getUserId());
        Assertions.assertEquals("user2",userDto.getUserName());
    }
}
