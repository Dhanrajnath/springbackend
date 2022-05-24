package com.greencommute.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {

    private int jobId;

    private int userId;

    private String message;

}
