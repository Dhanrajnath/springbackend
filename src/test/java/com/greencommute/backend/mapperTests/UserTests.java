package com.greencommute.backend.mapperTests;

import com.greencommute.backend.dto.UserDto;
import com.greencommute.backend.entity.User;
import com.greencommute.backend.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserTests {

    @Mock
    UserMapper userMapper;

    @Test
    void toUserDtoTest(){
        User user = new User(1, "user1",null);
        UserDto userDto = new UserDto(1,"user1");
        Mockito.when(userMapper.toUserDto(user)).thenReturn(userDto);
        Assertions.assertEquals(userDto,userMapper.toUserDto(user));
        Mockito.verify(userMapper).toUserDto(user);
    }

}
