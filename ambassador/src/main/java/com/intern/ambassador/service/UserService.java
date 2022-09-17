package com.intern.ambassador.service;

import com.intern.ambassador.data.dto.ChangeUserInfoDto;
import com.intern.ambassador.data.dto.UserResponseDto;
import com.intern.ambassador.data.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public interface UserService {
    /** 사용자 사용 가능 */
    UserResponseDto getUserInfo(Long uno);
    UserResponseDto changeUserInfo(ChangeUserInfoDto changeUserInfoDto) throws Exception;
    void withdrawUser(Long id, String uid, String password) throws Exception;
    /** 관리자 전용 */
    List<UserResponseDto> getUserList();

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

    default List<UserResponseDto> getUserListResponseDto(List<User> entityList) {
        List<UserResponseDto> userResponseDtoList = entityList.stream().map(entity -> {
            return UserResponseDto.builder()
                    .uno(entity.getId())
                    .uid(entity.getUid())
                    .password(entity.getPassword())
                    .email(entity.getEmail())
                    .name(entity.getName())
                    .age(entity.getAge())
                    .phoneNumber(entity.getPhoneNumber())
                    .build();
        }).collect(Collectors.toList());

        return userResponseDtoList;
    }
}
