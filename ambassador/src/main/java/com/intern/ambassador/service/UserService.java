package com.intern.ambassador.service;

import com.intern.ambassador.data.dto.*;
import com.intern.ambassador.data.entity.Application;
import com.intern.ambassador.data.entity.User;

public interface UserService {
    UserResponseDto getUserInfo(Long uno);
    UserResponseDto changeUserInfo(ChangeUserInfoDto changeUserInfoDto) throws Exception;
    void withdrawUser(String uid, String password) throws Exception;

    default UserResponseDto getUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setUid(user.getUid());
        userResponseDto.setPassword(user.getPassword());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setName(user.getName());
        userResponseDto.setAge(user.getAge());
        userResponseDto.setPhoneNumber(user.getPhoneNumber());

        return userResponseDto;
    }

    default User getUserEntity(UserDto userDto) {
        User user = new User();

        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        user.setPhoneNumber(userDto.getPhoneNumber());

        return user;
    }
}
