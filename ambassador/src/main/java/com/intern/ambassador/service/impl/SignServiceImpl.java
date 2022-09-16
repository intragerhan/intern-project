package com.intern.ambassador.service.impl;

import com.intern.ambassador.common.CommonResponse;
import com.intern.ambassador.config.security.JwtTokenProvider;
import com.intern.ambassador.data.dto.SignInResultDto;
import com.intern.ambassador.data.dto.SignUpRequestDto;
import com.intern.ambassador.data.dto.SignUpResultDto;
import com.intern.ambassador.data.dto.UserDto;
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

    // todo 구현 다 하면 이거 entity가 아닌 dto로 받을 수 있게 변경하기
    @Override
    public SignUpResultDto signUp(String id, String password, String email,
                                  String name, int age, String phone, String role) {
        LOGGER.info("[getSignUpResult] 회원 가입 정보 전달");
        SignUpRequestDto signUpRequestDto = gatherInfo(id, password, email, name, age, phone);
        User convertedInfo = convertDtoToEntity(signUpRequestDto);
        User user;
        if(role.equalsIgnoreCase("admin")) {
            user = User.builder()
                    .uid(convertedInfo.getUid())
                    .email(convertedInfo.getEmail())
                    .name(convertedInfo.getName())
                    .age(convertedInfo.getAge())
                    .phoneNumber(convertedInfo.getPhoneNumber())
                    .password(passwordEncoder.encode(convertedInfo.getPassword()))
                    .roles(Collections.singletonList("ROLE_ADMIN"))
                    .build();
        } else {
            user = User.builder()
                    .uid(convertedInfo.getUid())
                    .email(convertedInfo.getEmail())
                    .name(convertedInfo.getName())
                    .age(convertedInfo.getAge())
                    .phoneNumber(convertedInfo.getPhoneNumber())
                    .password(passwordEncoder.encode(convertedInfo.getPassword()))
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build();
        }
        User savedUser = userRepository.save(user);
        SignUpResultDto signUpResultDto = new SignInResultDto();

        LOGGER.info("[getSignUpResult] userEntity 값이 들어왔는지 확인 후 결과값 주입");
        if(!savedUser.getName().isEmpty()) {
            LOGGER.info("[getSignUpResult] 정상 처리 완료");
            setSuccessResult(signUpResultDto);
        } else {
            LOGGER.info("[getSignUpResult] 실패 처리 완료");
            setFailResult(signUpResultDto);
        }
        return signUpResultDto;
    }

    @Override
    public SignInResultDto signIn(String id, String password) throws RuntimeException {
        LOGGER.info("[getSignInResult] signDataHandler로 회원 정보 요청");
        User user = userRepository.getByUid(id);
        LOGGER.info("[getSignInResult] Id : {}", id);

        LOGGER.info("[getSignInResult] 패스워드 비교 수행");
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException();
        }
        LOGGER.info("[getSignInResult] 패스워드 일치");

        LOGGER.info("[getSignInResult] SignInResultDto 객체 생성");
        SignInResultDto signInResultDto = SignInResultDto.builder()
                .token(jwtTokenProvider.createToken(String.valueOf(user.getUid()), user.getRoles()))
                .build();

        LOGGER.info("[getSignInResult] SignInResultDto 객체에 값 주입");
        setSuccessResult(signInResultDto);

        return signInResultDto;
    }

    private void setSuccessResult(SignUpResultDto result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    private void setFailResult(SignUpResultDto result) {
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
    }

    private SignUpRequestDto gatherInfo(String id, String password, String email,
                                        String name, int age, String phone) {
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();

        signUpRequestDto.setUid(id);
        signUpRequestDto.setPassword(password);
        signUpRequestDto.setEmail(email);
        signUpRequestDto.setName(name);
        signUpRequestDto.setAge(age);
        signUpRequestDto.setPhoneNumber(phone);

        return signUpRequestDto;
    }

    private User convertDtoToEntity(SignUpRequestDto signUpRequestDto) {
        User user = new User();

        user.setUid(signUpRequestDto.getUid());
        user.setPassword(signUpRequestDto.getPassword());
        user.setEmail(signUpRequestDto.getEmail());
        user.setName(signUpRequestDto.getName());
        user.setAge(signUpRequestDto.getAge());
        user.setPhoneNumber(signUpRequestDto.getPhoneNumber());

        return user;
    }
}
