package com.example.demo.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.dtos.AuthResponse;
import com.example.demo.auth.dtos.LoginDto;
import com.example.demo.auth.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping("login")
  public ResponseEntity<AuthResponse> postMethodName(@Valid @RequestBody LoginDto loginRequest) {
      return ResponseEntity.ok(authService.login(loginRequest));   
  }

  
  
}
