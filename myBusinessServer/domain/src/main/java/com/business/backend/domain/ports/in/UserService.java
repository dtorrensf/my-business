package com.business.backend.domain.ports.in;

import com.business.backend.domain.dtos.UserDTO;
import com.business.backend.domain.exceptions.MyBusinessException;

public interface UserService {

  /**
   * Creates a new user on Database
   *
   * @param user the user details
   * @return The user ID
   * @throws MyBusinessException if any validation fails
   */
  Long createUser(UserDTO user) throws MyBusinessException;

  void deleteUser(Long id) throws MyBusinessException;

  void updateUser(UserDTO user) throws MyBusinessException;

  UserDTO getUser(Long id) throws MyBusinessException;
}
