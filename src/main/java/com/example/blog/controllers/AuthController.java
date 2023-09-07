package com.example.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.dtos.AuthenticationDto;
import com.example.blog.dtos.LoginResponseDto;
import com.example.blog.dtos.UserRecordDto;
import com.example.blog.models.user.Role;
import com.example.blog.models.user.UserModel;
import com.example.blog.repositories.userRepository.UserRepository;
import com.example.blog.services.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDto authenticationDto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDto.username(), authenticationDto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserModel) auth.getPrincipal());
        var username = tokenService.validateToken(token);
        var email = tokenService.extractEmailByToken(token);
        UserDetails user = userRepository.findByUsername(username);
        return ResponseEntity.ok(new LoginResponseDto(username, token));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid UserRecordDto userRecordDto){
        if(userRepository.existsByEmail(userRecordDto.email())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use!");
        }
        if(userRepository.existsByUsername(userRecordDto.username())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already in use!");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(userRecordDto.password());
        var user = new UserModel(userRecordDto.username(), encryptedPassword, userRecordDto.email(), Role.USER);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));

    }

}