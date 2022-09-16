package com.intern.ambassador.data.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private Long uno;
    private String uid;
    private String password;
    private String email;
    private String name;
    private int age;
    private String phoneNumber;

}
