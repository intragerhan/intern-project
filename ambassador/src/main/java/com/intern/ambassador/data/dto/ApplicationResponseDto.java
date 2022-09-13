package com.intern.ambassador.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResponseDto {

    private Long ano;
    private String reason;
    private String feedback;
    private String activity;
    private String advantage;
    private String lastWord;
}
