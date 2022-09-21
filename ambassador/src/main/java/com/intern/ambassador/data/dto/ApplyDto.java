package com.intern.ambassador.data.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplyDto {

    private String reason;
    private String feedback;
    private String activity;
    private String advantage;
    private String lastWord;
}
