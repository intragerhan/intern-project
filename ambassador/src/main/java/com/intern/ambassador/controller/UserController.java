package com.intern.ambassador.controller;

import com.intern.ambassador.data.dto.UserResponseDto;
import com.intern.ambassador.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    /** 유저가 본인 정보 확인 */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token", required = true,
                    dataType = "String", paramType = "header")
    })
    @GetMapping("/{id}")
    public String getUserInformation(@PathVariable Long id) {
        // todo 유저가 본인 정보 확인
        return "";
    }

    /** 유저가 본인의 정보 수정 */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token", required = true,
                    dataType = "String", paramType = "header")
    })
    @PatchMapping("/{id}")
    public String patchUserInfo(@PathVariable Long id) {
        // todo 유저가 본인의 정보 수정
        return "";
    }

    /** 회원 탈퇴 */
    @DeleteMapping("/{id}")
    public String withDrawService(@PathVariable Long id, String uid, String password) throws Exception {
        // todo 회원탈퇴, 신청서와 설문지는 삭제 안 함
        userService.withdrawUser(id, uid, password);
        return "";
    }

    /** 유저 목록 조회 */
    @GetMapping("/admin")
    public String getUserList(Model model) {
        // todo 관리자가 유저의 목록을 확인
        List<UserResponseDto> userResponseDto = userService.getUserList();
        model.addAttribute("userDto", userResponseDto);
        return "";
    }

    /** 관리자가 유저 정보 확인 */
    @GetMapping("/admin/{id}")
    public String getUserByAdmin(@PathVariable Long id) {
        // todo 관리자가 유저의 정보를 확인
        return "";
    }
}
