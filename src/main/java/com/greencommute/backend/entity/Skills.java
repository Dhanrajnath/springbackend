package com.greencommute.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "skills")
public class Skills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int skillId;

    @Column(name = "skill_name")
    private String skillName;

    @ToString.Exclude
    @ManyToMany(mappedBy = "skillList", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Jobs> jobsList;
}