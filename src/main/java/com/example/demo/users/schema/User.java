package com.example.demo.users.schema;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.auth.enums.RoleEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

  @Id
  private String id;

  private String name;

  @Indexed(unique = true)
  private String username;

  private String password;

  private Date createAt;

  private Boolean enabled;

  private RoleEnum role;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority((role.name())));
  }
  @Override
  public boolean isAccountNonExpired() {
      return true;
  }
  @Override
  public boolean isAccountNonLocked() {
      return true;
  }
  @Override
  public boolean isCredentialsNonExpired() {
      return true;
  }
  @Override
  public boolean isEnabled() {
      return enabled;
  }  
}
