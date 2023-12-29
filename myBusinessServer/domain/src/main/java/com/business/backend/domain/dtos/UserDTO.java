package com.business.backend.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private String phone;
  private String username;

}
