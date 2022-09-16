package com.intern.ambassador.service.impl;

import com.intern.ambassador.data.dto.ApplicationResponseDto;
import com.intern.ambassador.data.dto.ChangeUserInfoDto;
import com.intern.ambassador.data.dto.UserDto;
import com.intern.ambassador.data.dto.UserResponseDto;
import com.intern.ambassador.data.entity.Application;
import com.intern.ambassador.data.entity.User;
import com.intern.ambassador.data.repository.UserRepository;
import com.intern.ambassador.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    @Override
    public UserResponseDto getUserInfo(Long uno) {
        LOGGER.info("[getUserInfo] input number : {}", uno);
        User user = userRepository.findById(uno).get();
        LOGGER.info("[getUserInfo] input user : {}", user);
        UserResponseDto userResponseDto = getUserResponseDto(user);
        return userResponseDto;
    }

    @Override
    public UserResponseDto changeUserInfo(ChangeUserInfoDto changeUserInfoDto) throws Exception {
        User foundUser = userRepository.findById(changeUserInfoDto.getUno()).get();
        getUserResponseDto(foundUser);
        User changedUserInfo = userRepository.save(foundUser);

        UserResponseDto userResponseDto = getUserResponseDto(changedUserInfo);
        return userResponseDto;
    }

    @Override
    public void withdrawUser(String uid, String password) throws Exception {
        userRepository.deleteUserByUidAndPassword(uid, password);
    }
}
