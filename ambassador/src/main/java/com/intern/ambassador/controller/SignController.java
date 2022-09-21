package com.intern.ambassador.controller;

import com.intern.ambassador.data.dto.LoginResultDto;
import com.intern.ambassador.data.dto.UserResultDto;
import com.intern.ambassador.service.SignService;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sign-api")
public class SignController {
    private final Logger LOGGER = LoggerFactory.getLogger(SignController.class);
    private final SignService signService;

    @PostMapping(value = "/sign-in")
    public LoginResultDto signIn(
            @ApiParam(value = "ID", required = true) @RequestParam String id,
            @ApiParam(value = "password", required = true) @RequestParam String password) throws RuntimeException {
        LOGGER.info("[signIn] 로그인을 시도하고 있습니다. id = {}, pw = ****", id);
        LoginResultDto loginResultDto = signService.signIn(id, password);

        if(loginResultDto.getCode() == 0) {
            LOGGER.info("[signIn] 정상적으로 로그인되었습니다. id : {}, token : {}", id, loginResultDto.getToken());
        }
        return loginResultDto;
    }

    @PostMapping(value = "/sign-up")
    public UserResultDto signUp(
            @Validated @ApiParam(value = "ID", required = true) @RequestParam String id,
            @Validated @ApiParam(value = "비밀번호", required = true) @RequestParam String password,
            @Validated @ApiParam(value = "이메일", required = true) @RequestParam String email,
            @Validated @ApiParam(value = "이름", required = true) @RequestParam String name,
            @Validated @ApiParam(value = "나이", required = true) @RequestParam int age,
            @Validated @ApiParam(value = "핸드폰 번호", required = true) @RequestParam String phone) {
        LOGGER.info("[signUp] 회원가입을 수행합니다. id : {}, password: ****, email : {}," +
                " name : {}, age : {}, phone : {}", id, email, name, age, phone);
        UserResultDto userResultDto = signService.signUp(id, password, email, name, age, phone);

        LOGGER.info("[signUp] 회원가입이 완료되었습니다. id : {}", id);
        return userResultDto;
    }

    @GetMapping(value = "/exception")
    public void exceptionTest() throws RuntimeException {
        throw new RuntimeException("접근이 금지되었습니다.");
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(RuntimeException e) {
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        LOGGER.error("ExceptionHandler 호출, {}, {}", e.getCause(), e.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "에러 발생");

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }
}
