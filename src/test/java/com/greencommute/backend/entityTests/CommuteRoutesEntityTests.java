package com.greencommute.backend.entityTests;

import com.greencommute.backend.entity.CommuteRoutes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommuteRoutesEntityTests {

    CommuteRoutes commuteRoutes = new CommuteRoutes(1,"Bike","2min",null);

    @Test
    void commuteRoutesEntityTests() {
        Assertions.assertEquals(1,commuteRoutes.getCommuteId());
        Assertions.assertEquals("Bike",commuteRoutes.getCommuteName());
        Assertions.assertEquals("2min",commuteRoutes.getCommuteTime());
        Assertions.assertNull(commuteRoutes.getJobsList());

        commuteRoutes = new CommuteRoutes();
        commuteRoutes.setCommuteId(2);
        commuteRoutes.setCommuteName("Car");
        commuteRoutes.setCommuteTime("20min");
        commuteRoutes.setJobsList(null);

        Assertions.assertEquals(2,commuteRoutes.getCommuteId());
        Assertions.assertEquals("Car",commuteRoutes.getCommuteName());
        Assertions.assertEquals("20min",commuteRoutes.getCommuteTime());
        Assertions.assertNull(commuteRoutes.getJobsList());
    }
}
