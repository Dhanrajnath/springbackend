package com.greencommute.backend.service;

import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.entity.SavedJobs;
import com.greencommute.backend.entity.User;

import java.util.List;

public interface SavedJobService {
    void saveToSavedJobs(User user, Jobs jobs);
    List<Jobs> getSavedJobsByUserId(int userId);

    void deleteSavedJobs(SavedJobs savedJobs);

}
