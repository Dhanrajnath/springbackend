package com.greencommute.backend.service;

import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.entity.SavedJobs;
import com.greencommute.backend.entity.User;
import com.greencommute.backend.repository.SavedJobsJpa;
import com.greencommute.backend.repository.UserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SavedJobServiceImpl implements  SavedJobService {

    UserJpa userJpa;

    SavedJobsJpa savedJobsJpa;

    @Autowired
    public SavedJobServiceImpl(UserJpa userJpa, SavedJobsJpa savedJobsJpa) {
        this.userJpa = userJpa;
        this.savedJobsJpa = savedJobsJpa;
    }

    @Override
    public void saveToSavedJobs(User user, Jobs job) {
        SavedJobs savedJobs = new SavedJobs(new Timestamp(System.currentTimeMillis()), user , job);
        savedJobsJpa.save(savedJobs);
    }

    @Override
    public List<Jobs> getSavedJobsByUserId(int userId) {
        Optional<User> user = userJpa.findById(userId);
        List<Jobs> jobList = new ArrayList<>();
        if(user.isPresent()) {
            List<SavedJobs> savedJobsList = user.get().getSavedJobsList();
            for (SavedJobs savedJobs : savedJobsList) {
                jobList.add(savedJobs.getJobs());
            }
            return jobList;
        }
        return jobList;
    }

    @Override
    public void deleteSavedJobs(SavedJobs savedJob)  {
        savedJobsJpa.delete(savedJob);
    }
}