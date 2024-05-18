package com.example.demo.users.service;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.auth.enums.RoleEnum;
import com.example.demo.users.dtos.UserDto;
import com.example.demo.users.repository.UserRepository;
import com.example.demo.users.schema.User;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public ResponseEntity<HashMap<String, String>> createUser(UserDto userDto) {

    String name = userDto.getName();
    String email = userDto.getEmail();
    String password = this.getEncriptPassword(userDto.getPassword());
    Date createAt = new Date();
    RoleEnum role = userDto.getRole();

    User user = User
        .builder()
        .name(name)
        .username(email)
        .password(password)
        .createAt(createAt)
        .role(role)
        .enabled(true)
        .build();

    User userSaved = userRepository.save(user);

    HashMap<String, String> response = new HashMap<>();
    response.put("id", userSaved.getId());
    response.put("name", userSaved.getName());
    response.put("email", userSaved.getUsername());
    response.put("role", userSaved.getRole().name());
    response.put("createAt", userSaved.getCreateAt().toString());
    

    return ResponseEntity.ok(response);
  }

  public String getEncriptPassword(String password) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder.encode(password);
  }
  
  
}
