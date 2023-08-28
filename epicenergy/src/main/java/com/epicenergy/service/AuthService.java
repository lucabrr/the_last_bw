package com.epicenergy.service;

import com.epicenergy.payload.LoginDto;
import com.epicenergy.payload.RegisterDto;

public interface AuthService {
    
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    
}
