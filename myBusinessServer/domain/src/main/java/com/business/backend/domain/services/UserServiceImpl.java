package com.business.backend.domain.services;

import com.business.backend.domain.dtos.UserDTO;
import com.business.backend.domain.exceptions.DuplicatedItemException;
import com.business.backend.domain.exceptions.ErrorItemEnum;
import com.business.backend.domain.exceptions.MyBusinessException;
import com.business.backend.domain.ports.in.UserService;
import com.business.backend.domain.ports.out.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UsersRepository repository;

  @Override
  public Long createUser(UserDTO user) throws MyBusinessException {
    this.validateDuplicatedUser(user.getEmail());
    return this.repository.save(user);
  }

  @Override
  public void deleteUser(Long id) throws MyBusinessException {

  }

  @Override
  public void updateUser(UserDTO user) throws MyBusinessException {

  }

  @Override
  public UserDTO getUser(Long id) throws MyBusinessException {
    return null;
  }

  /**
   * Checks if a user email exists on Database
   *
   * @param email the user email
   * @throws DuplicatedItemException if the user email exists on Database
   */
  private void validateDuplicatedUser(String email) throws DuplicatedItemException {
    var user = this.repository.findUserByEmail(email);
    if (user != null)
      throw new DuplicatedItemException(
          ErrorItemEnum.USER,
          String.format("The user %s already exists!", email)
      );
  }
}
