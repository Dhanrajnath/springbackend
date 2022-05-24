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
@Table(name = "commute_routes")
public class CommuteRoutes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commuteId;

    @Column(name = "commute_name")
    private String commuteName;

    @Column(name="commute_time")
    private String commuteTime;

    @ToString.Exclude
    @ManyToMany(mappedBy = "commuteList", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Jobs> jobsList;
}