package com.business.backend.domain.services;

import com.business.backend.domain.dtos.UserDTO;
import com.business.backend.domain.exceptions.DuplicatedItemException;
import com.business.backend.domain.exceptions.ErrorItemEnum;
import com.business.backend.domain.ports.out.UsersRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @Mock
  private UsersRepository repository;

  @InjectMocks
  private UserServiceImpl service;

  @Test
  @DisplayName("Should throw exception due to existing user")
  void shouldCreateUserTrowException() {
    // Given
    var email = "user@mail.com";
    var user = mock(UserDTO.class);
    doReturn(email).when(user).getEmail();
    var foundUser = mock(UserDTO.class);

    doReturn(foundUser)
        .when(repository)
        .findUserByEmail(email);

    var expectedException = new DuplicatedItemException(
        ErrorItemEnum.USER,
        "The user user@mail.com already exists!"
    );

    // When
    try {
      service.createUser(user);
      fail("An exception was expected");
    } catch (DuplicatedItemException e) {
      // Then
      assertThat("The exception is the expected one", e, equalTo(expectedException));
    }
  }

  @Test
  @DisplayName("Should return new user id")
  void shouldCreateUserReturnIId() {
    // Given
    var email = "user@mail.com";
    var user = mock(UserDTO.class);
    doReturn(email).when(user).getEmail();

    doReturn(null)
        .when(repository)
        .findUserByEmail(email);

    var expectedId = 1L;
    doReturn(expectedId)
        .when(repository)
        .save(user);

    // When
    var output = service.createUser(user);

    // Then
    assertThat("The output must be the expected ID", output, equalTo(expectedId));
  }
}

