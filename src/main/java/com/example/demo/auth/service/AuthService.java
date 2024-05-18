package com.example.demo.auth.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.auth.dtos.AuthResponse;
import com.example.demo.auth.dtos.LoginDto;
import com.example.demo.users.repository.UserRepository;
import com.example.demo.users.schema.User;

@Service
public class AuthService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private  JwtService jwtService;
  
  @Autowired
  private AuthenticationManager authenticationManager;

  public AuthResponse login(LoginDto request) {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
      UserDetails user=userRepository.findByUsername(request.getEmail()).orElseThrow();
      String token=jwtService.getToken(user);

      User userSaved = userRepository.findByUsername(request.getEmail()).orElseThrow();

      HashMap<String, String> response = new HashMap<>();
      response.put("id", userSaved.getId());
      response.put("name", userSaved.getName());
      response.put("email", userSaved.getUsername());
      response.put("role", userSaved.getRole().name());
      response.put("createAt", userSaved.getCreateAt().toString());
      response.put("enabled", userSaved.getEnabled().toString());
    
      return AuthResponse.builder()
          .token(token)
          .user(response)
          .build();

  }

  
}
