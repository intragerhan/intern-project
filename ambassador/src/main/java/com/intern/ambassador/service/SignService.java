package com.intern.ambassador.service;

import com.intern.ambassador.data.dto.LoginResultDto;
import com.intern.ambassador.data.dto.UserResultDto;

public interface SignService {
    UserResultDto signUp(String id, String password, String email, String name, int age, String phone);
    LoginResultDto signIn(String id, String password) throws RuntimeException;
}
