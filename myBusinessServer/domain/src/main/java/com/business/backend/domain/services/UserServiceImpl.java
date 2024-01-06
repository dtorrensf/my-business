package com.business.backend.domain.services;

import com.business.backend.domain.dtos.UserDTO;
import com.business.backend.domain.exceptions.MyBusinessException;
import com.business.backend.domain.ports.in.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  @Override
  public Long createUser(UserDTO user) throws MyBusinessException {
    return null;
  }

  @Override
  public void deleteUser(Long id) throws MyBusinessException {

  }

  @Override
  public void updateUser(UserDTO user) {

  }

  @Override
  public UserDTO getUser(Long id) {
    return null;
  }
}
