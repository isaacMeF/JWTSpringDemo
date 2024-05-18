package com.example.demo.users.dtos;

import com.example.demo.auth.enums.RoleEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  @NotBlank(message = "Name is required")
  @NotNull(message = "Name is required")
  private String name;

  @NotBlank(message = "Email is required")
  @NotNull(message = "Email is required")
  @Email(message = "Email is invalid")
  private String email;

  @NotBlank(message = "Password is required")
  @NotNull(message = "Password is required")
  @Size(min = 8, message = "Password must be at least 8 characters")
  private String password;

  private RoleEnum role;

}
