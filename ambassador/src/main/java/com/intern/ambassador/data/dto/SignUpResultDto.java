package com.intern.ambassador.data.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SignUpResultDto {
    private boolean success;
    private int code;
    private String msg;
}
