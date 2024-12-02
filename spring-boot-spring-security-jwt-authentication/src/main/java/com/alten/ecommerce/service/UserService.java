package com.alten.ecommerce.service;

import com.alten.ecommerce.dto.req.UserRequestDto;
import com.alten.ecommerce.dto.resp.UserResponseDto;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserResponseDto> getAllUser();

    public UserResponseDto createUser(UserRequestDto userRequestDto);

}
