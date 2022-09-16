package com.intern.ambassador.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeContentsDto {

    private Long ano;
    private String reason;
    private String feedback;
    private String activity;
    private String advantage;
    private String lastWord;
}
