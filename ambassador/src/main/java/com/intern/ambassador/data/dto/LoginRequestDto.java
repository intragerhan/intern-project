package com.intern.ambassador.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LoginRequestDto {
    private String id;
    private String password;
}
