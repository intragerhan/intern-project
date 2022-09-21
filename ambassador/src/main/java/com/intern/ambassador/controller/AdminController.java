package com.intern.ambassador.controller;

import com.intern.ambassador.data.dto.ApplyResponseDto;
import com.intern.ambassador.data.dto.ChangeUserDto;
import com.intern.ambassador.data.dto.UserResponseDto;
import com.intern.ambassador.service.ApplyService;
import com.intern.ambassador.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token", required = true,
                dataType = "String", paramType = "header")
})
@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AdminController {

    private final ApplyService applyService;
    private final UserService userService;

    /**
     * 유저 목록 조회
     */
    @GetMapping("/admin")
    public ResponseEntity<List<UserResponseDto>> getUserList() {
        long currentTime = System.currentTimeMillis();
        List<UserResponseDto> userListDto = userService.getUserList();
        log.info("[getUserList] Response Time : {}ms", System.currentTimeMillis() - currentTime);
        return ResponseEntity.status(HttpStatus.OK).body(userListDto);
    }
    /**
     * 관리자가 유저 정보 확인
     */
    @GetMapping("/admin/{id}")
    public ResponseEntity<UserResponseDto> getUserByAdmin(@PathVariable Long id) {
        long currentTime = System.currentTimeMillis();
        UserResponseDto userResponseDto = userService.getUserInfo(id);
        log.info("[getUserByAdmin] Response Time : {}ms", System.currentTimeMillis() - currentTime);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }
    /**
     * 관리자가 유저 정보 변경
     */
    @PutMapping("/admin/{id}")
    public ResponseEntity<UserResponseDto> updateUserByAdmin(@PathVariable Long id, ChangeUserDto changeUserDto) {
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
     * 관리자가 신청서 확인
     */
    @GetMapping("/admin/{id}/application")
    public ResponseEntity<ApplyResponseDto> inspectApplication(@PathVariable("id") Long ano) {
        ApplyResponseDto applyResponseDto = applyService.getApplication(ano);
        return ResponseEntity.status(HttpStatus.OK).body(applyResponseDto);
    }
    /**
     * 관리자가 신청서 삭제
     */
    @DeleteMapping("/admin/{id}/application/{ano}")
    public ResponseEntity<String> deleteApplication(@PathVariable Long id, @PathVariable Long ano) throws Exception {
        applyService.deleteApplication(ano);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }
}
