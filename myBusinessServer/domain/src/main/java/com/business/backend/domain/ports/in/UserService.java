package com.business.backend.domain.ports.in;

import com.business.backend.domain.dtos.UserDTO;

public interface UserService {
  void createUser(UserDTO user);
}
