package com.business.backend.domain.ports.out;

import com.business.backend.domain.dtos.UserDTO;

public interface UsersRepository {
  
  UserDTO findUserByEmail(String email);

  Long save(UserDTO user);
}
