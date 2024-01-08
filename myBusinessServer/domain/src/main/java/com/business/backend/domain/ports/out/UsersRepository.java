package com.business.backend.domain.ports.out;

import com.business.backend.domain.dtos.UserDTO;

public interface UsersRepository {

  UserDTO findUserByEmail(String email);

  Long save(UserDTO user);

  UserDTO findUserById(long id);

  void deleteUser(UserDTO user);
}
