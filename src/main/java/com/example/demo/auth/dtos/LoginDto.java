package com.example.demo.auth.dtos;

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
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
  @NotBlank(message = "Email is required")
  @Email(message = "Email is invalid")
  @NotNull(message = "Email is required")
  private String email;

  @NotBlank(message = "Password is required")
  @NotNull(message = "Password is required")
  @Size(min = 8, message = "Password must be at least 8 characters")
  private String password;

}
