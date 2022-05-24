package com.greencommute.backend.entityTests;

import com.greencommute.backend.entity.Skills;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SkillsEntityTests {

    Skills skills = new Skills(1,"c",null);

    @Test
    void skillsEntityTests() {
        Assertions.assertEquals(1,skills.getSkillId());
        Assertions.assertEquals("c",skills.getSkillName());
        Assertions.assertNull(skills.getJobsList());

        skills = new Skills();
        skills.setSkillId(2);
        skills.setSkillName("go");
        skills.setJobsList(null);

        Assertions.assertEquals(2,skills.getSkillId());
        Assertions.assertEquals("go",skills.getSkillName());
        Assertions.assertNull(skills.getJobsList());
    }

}
