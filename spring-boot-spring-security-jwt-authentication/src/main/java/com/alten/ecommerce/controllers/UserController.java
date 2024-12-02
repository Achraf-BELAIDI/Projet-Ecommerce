package com.alten.ecommerce.controllers;

import com.alten.ecommerce.dto.req.UserRequestDto;
import com.alten.ecommerce.dto.resp.UserResponseDto;
import com.alten.ecommerce.model.UserEntity;
import com.alten.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUser(){
        return new ResponseEntity<>( userService.getAllUser(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto userRequestDto) {
        try {
            // Appel à la méthode createUser qui crée un nouvel utilisateur
            UserResponseDto userResponseDto = userService.createUser(userRequestDto);
            return ResponseEntity.ok(userResponseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
