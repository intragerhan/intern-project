package com.intern.ambassador.controller;

import com.intern.ambassador.data.dto.ChangeUserDto;
import com.intern.ambassador.data.dto.UserResponseDto;
import com.intern.ambassador.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token", required = true,
                dataType = "String", paramType = "header")
})
@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 유저가 본인 정보 확인
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserInfo(@PathVariable Long id) {
        long currentTime = System.currentTimeMillis();
        UserResponseDto userResponseDto = userService.getUserInfo(id);
        log.info("[getUserByAdmin] Response Time : {}ms", System.currentTimeMillis() - currentTime);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    /**
     * 유저가 본인의 정보 수정
     */
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> patchUserInfo(@PathVariable Long id, ChangeUserDto changeUserDto) {
        long currentTime = System.currentTimeMillis();
        UserResponseDto userResponseDto = null;
        try {
            userResponseDto = userService.changeUserInfo(changeUserDto);
            log.info("[updateUserByAdmin] Response Time : {}ms", System.currentTimeMillis() - currentTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    /**
     * 회원 탈퇴
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> withDrawService(@PathVariable Long id, String uid, String password) throws Exception {
        userService.withdrawUser(id, uid, password);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 탈퇴되었습니다.");
    }
}
