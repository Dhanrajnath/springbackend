package com.greencommute.backend.exceptionTests;

import com.greencommute.backend.exception.DataNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DataNotFoundExceptionTests {

    DataNotFoundException dataNotFoundException = new DataNotFoundException("Data not found!");

    @Test
    void dataNotFoundExceptionTests() {
        Assertions.assertEquals("Data not found!",dataNotFoundException.getMessage());
    }
}
