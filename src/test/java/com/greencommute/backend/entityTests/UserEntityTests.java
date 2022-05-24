package com.greencommute.backend.entityTests;

import com.greencommute.backend.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserEntityTests {

    @Test
    void userEntityTests(){

        User user = new User(1,"user1",null);
        Assertions.assertEquals(1,user.getUserId());
        Assertions.assertEquals("user1",user.getUserName());
        Assertions.assertNull(user.getSavedJobsList());

        User user1 = new User();
        user1.setUserId(2);
        user1.setUserName("user2");
        user1.setSavedJobsList(null);

        Assertions.assertEquals(2,user1.getUserId());
        Assertions.assertEquals("user2",user1.getUserName());
        Assertions.assertNull(user1.getSavedJobsList());

    }
}
