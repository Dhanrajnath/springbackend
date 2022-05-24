package com.greencommute.backend.dto;

import com.greencommute.backend.entity.CommuteRoutes;
import com.greencommute.backend.entity.Skills;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobsDto {

    private int jobId;

    private String jobTitle;

    private String jobDescription;

    private String jobLocation;

    private List<Skills> skillList;

    private List<CommuteRoutes> commuteList;
}
