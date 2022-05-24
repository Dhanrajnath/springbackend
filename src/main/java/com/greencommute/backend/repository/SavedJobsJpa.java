package com.greencommute.backend.repository;

import com.greencommute.backend.entity.SavedJobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SavedJobsJpa extends JpaRepository<SavedJobs,Integer> {
    @Query(value = "select * from saved_job where user_id=?1 and job_id=?2",nativeQuery = true)
    SavedJobs findByUserAndJobId(int userId, int jobId);

}
