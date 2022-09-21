package com.intern.ambassador.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeUserDto {

    private Long uno;
    private String uid;
    private String password;
    private String email;
    private String name;
    private int age;
    private String phoneNumber;

}
