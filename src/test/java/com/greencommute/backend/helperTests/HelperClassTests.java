package com.greencommute.backend.helperTests;

import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.entity.Skills;
import com.greencommute.backend.helper.Helper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class HelperClassTests {

    @Autowired
    Helper helper;

    @Test
    void getSkillsTest(){
        List<Skills> skillList = new ArrayList<>();
        Skills skill = new Skills(1,"a",null);
        Skills skill2 = new Skills();
        skill2.setSkillId(2);
        skill2.setSkillName("b");
        skill2.setJobsList(null);
        skillList.add(skill);
        skillList.add(skill2);
        List<String> skillNames = new ArrayList<>();
        skillNames.add(skill.getSkillName());
        skillNames.add(skill2.getSkillName());

        Assertions.assertNotNull(helper);
        Assertions.assertEquals(skillNames,helper.getSkills(skillList));

        skillNames = new ArrayList<>();
        List<Skills> skillsList1 = new ArrayList<>();
        Assertions.assertEquals(skillNames,helper.getSkills(skillsList1));
    }

    @Test
    void getJobsSearchBySkillsTest() {
        List<Jobs> jobsList = new ArrayList<>();
        List<Jobs> jobsList1 = new ArrayList<>();
        Jobs job1 = new Jobs(1,"Software Engineer","Developer","Hyderabad",null,null);
        Jobs job2 = new Jobs(2,"Software Engineer","Developer","Hyderabad",null,null);
        List<Skills> skillList = new ArrayList<>();
        Skills skill = new Skills(1,"a",null);
        skillList.add(skill);
        job1.setSkillList(skillList);
        job2.setSkillList(skillList);
        jobsList.add(job1);
        jobsList.add(job2);

        String[] skillSearch = new String[]{"a"};
        Assertions.assertEquals(jobsList,helper.getJobsSearchBySkills(jobsList,skillSearch));

        String[] skillSearch2 = new String[]{"abb"};
        Assertions.assertEquals(jobsList1,helper.getJobsSearchBySkills(jobsList,skillSearch2));
    }
}
