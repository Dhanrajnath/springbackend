package com.greencommute.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "saved_job")
public class SavedJobs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "saved_job_id")
    private int savedJobId;

    @Column(name = "created")
    private Timestamp createdAt;

    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "job_id")
    @JsonBackReference
    private Jobs jobs;

    public SavedJobs(Timestamp createdAt, User user, Jobs jobs) {
        this.createdAt = createdAt;
        this.user = user;
        this.jobs = jobs;
    }


}