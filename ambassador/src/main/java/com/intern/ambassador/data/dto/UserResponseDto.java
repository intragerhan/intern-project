package com.intern.ambassador.data.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    /**
     * Entity 에서 DTO로 변환할 때 사용
     */
    private Long uno;
    private String uid;
    private String password;
    private String email;
    private String name;
    private int age;
    private String phoneNumber;

}
