package com.intern.ambassador.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ApplicationDto {

    private String reason;
    private String feedback;
    private String activity;
    private String advantage;
    private String lastWord;
}
