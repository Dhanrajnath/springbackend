
package com.greencommute.backend.helper;

import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.entity.Skills;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Helper {
    public List<String> getSkills(List<Skills> skillsList) {
        List<String> jobSkills = new ArrayList<>();
        for (Skills skills : skillsList) {
            jobSkills.add(skills.getSkillName());
        }
        return jobSkills;
    }

    public List<Jobs> getJobsSearchBySkills(List<Jobs> jobsList, String[] skillToSearch){
        return jobsList.stream().filter(job -> {
            List<Skills> skill = job.getSkillList();
            List<String> skillNames = getSkills(skill);
            for (String skillName : skillToSearch) {
                if (skillNames.contains(skillName))
                    return true;
            }
            return false;
        }).collect(Collectors.toList());
    }
}