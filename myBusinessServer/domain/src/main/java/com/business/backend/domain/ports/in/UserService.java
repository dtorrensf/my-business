package com.business.backend.domain.ports.in;

import com.business.backend.domain.dtos.UserDTO;
import com.business.backend.domain.exceptions.MyBusinessException;

public interface UserService {
  void createUser(UserDTO user) throws MyBusinessException;
}
