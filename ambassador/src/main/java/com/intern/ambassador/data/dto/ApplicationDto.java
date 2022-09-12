package com.intern.ambassador.data.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class ApplicationDto {

    private String reason;
    private String feedback;
    private String activity;
    private String advantage;
    private String lastWord;
}
