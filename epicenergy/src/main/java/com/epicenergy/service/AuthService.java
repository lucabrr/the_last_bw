package com.epicenergy.service;

import com.epicenergy.entity.User;
import com.epicenergy.payload.LoginDto;
import com.epicenergy.payload.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto);

    User register(RegisterDto registerDto);
}
