package com.intern.ambassador.data.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpRequestDto {
    @NotBlank(message = "아이디는 필수로 입력해주셔야 합니다.")
    private String uid;

    @NotBlank(message = "비밀번호를 입력하세요")
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
            message = "8~16 characters consisting of letters(A-Z, a-z), numbers, or special characters.")
    private String password;

    @Email
    private String email;

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @Min(value = 20, message = "성인부터 서비스 이용 가능합니다") @Max(value = 90)
    private int age;

    @NotBlank(message = "연락을 위한 휴대폰 번호는 필수입니다")
    @Pattern(regexp = "01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
    private String phoneNumber;

    private String roles;
}
