package com.intern.ambassador.service.impl;

import com.intern.ambassador.common.CommonResponse;
import com.intern.ambassador.config.security.JwtTokenProvider;
import com.intern.ambassador.data.dto.LoginResultDto;
import com.intern.ambassador.data.dto.UserRequestDto;
import com.intern.ambassador.data.dto.UserResultDto;
import com.intern.ambassador.data.entity.User;
import com.intern.ambassador.data.repository.UserRepository;
import com.intern.ambassador.service.SignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SignServiceImpl implements SignService {
    private final Logger LOGGER = LoggerFactory.getLogger(SignServiceImpl.class);

    public UserRepository userRepository;
    public JwtTokenProvider jwtTokenProvider;
    public PasswordEncoder passwordEncoder;

    @Autowired
    public SignServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResultDto signUp(String id, String password, String email,
                                String name, int age, String phone) {
        LOGGER.info("[getSignUpResult] 회원 가입 정보 전달");
        User converted = convertDtoToEntity(gatherInfo(id, password, email, name, age, phone));
        User user = User.builder()
                    .uid(converted.getUid())
                    .email(converted.getEmail())
                    .name(converted.getName())
                    .age(converted.getAge())
                    .phoneNumber(converted.getPhoneNumber())
                    .password(passwordEncoder.encode(converted.getPassword()))
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build();
        User savedUser = userRepository.save(user);
        UserResultDto userResultDto = new LoginResultDto();

        LOGGER.info("[getSignUpResult] userEntity 값이 들어왔는지 확인 후 결과값 주입");
        if(!savedUser.getName().isEmpty()) {
            LOGGER.info("[getSignUpResult] 정상 처리 완료");
            setSuccessResult(userResultDto);
        } else {
            LOGGER.info("[getSignUpResult] 실패 처리 완료");
            setFailResult(userResultDto);
        }
        return userResultDto;
    }

    @Override
    public LoginResultDto signIn(String id, String password) throws RuntimeException {
        LOGGER.info("[getSignInResult] signDataHandler로 회원 정보 요청");
        User user = userRepository.getByUid(id);
        LOGGER.info("[getSignInResult] Id : {}", id);

        LOGGER.info("[getSignInResult] 패스워드 비교 수행");
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException();
        }
        LOGGER.info("[getSignInResult] 패스워드 일치");

        LOGGER.info("[getSignInResult] LoginResultDto 객체 생성");
        LoginResultDto loginResultDto = LoginResultDto.builder()
                .token(jwtTokenProvider.createToken(String.valueOf(user.getUid()), user.getRoles()))
                .build();

        LOGGER.info("[getSignInResult] LoginResultDto 객체에 값 주입");
        setSuccessResult(loginResultDto);

        return loginResultDto;
    }

    private void setSuccessResult(UserResultDto result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    private void setFailResult(UserResultDto result) {
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
    }

    private UserRequestDto gatherInfo(String id, String password, String email,
                                      String name, int age, String phone) {
        UserRequestDto userRequestDto = new UserRequestDto();

        userRequestDto.setUid(id);
        userRequestDto.setPassword(password);
        userRequestDto.setEmail(email);
        userRequestDto.setName(name);
        userRequestDto.setAge(age);
        userRequestDto.setPhoneNumber(phone);

        return userRequestDto;
    }

    private User convertDtoToEntity(UserRequestDto userRequestDto) {
        User user = new User();

        user.setUid(userRequestDto.getUid());
        user.setPassword(userRequestDto.getPassword());
        user.setEmail(userRequestDto.getEmail());
        user.setName(userRequestDto.getName());
        user.setAge(userRequestDto.getAge());
        user.setPhoneNumber(userRequestDto.getPhoneNumber());

        return user;
    }
}
