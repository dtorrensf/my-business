package com.business.backend.api.mappers;

import com.business.backend.api.generated.model.NewUserModel;
import com.business.backend.api.generated.model.UserModel;
import com.business.backend.domain.dtos.UserDTO;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * A test class for {@link UserMapper}
 */
class UserMapperTest {

  private static final String EMAIL = "user@mail.com";
  private static final String PASSWORD = "password";
  private static final String FIRST_NAME = "John";
  private static final String LAST_NAME = "Smith";
  private static final String PHONE = "555555";
  private static final String USERNAME = "John35";

  private final UserMapper mapper = new UserMapperImpl();


  @Test
  void shouldConvertNewUserToNull() {

    // Given
    NewUserModel input = null;

    // When
    var output = this.mapper.convert(input);

    // Then
    assertThat("The output is null", output, nullValue());
  }

  @Test
  void shouldConvertNewUserToUserDTO() {

    // Given
    NewUserModel input = new NewUserModel();
    input.email(EMAIL);
    input.password(PASSWORD);
    input.firstName(FIRST_NAME);
    input.lastName(LAST_NAME);
    input.phone(PHONE);
    input.username(USERNAME);

    UserDTO expectedOutput = this.buildUserDTO();
    expectedOutput.setPassword(PASSWORD);

    // When
    var output = this.mapper.convert(input);

    // Then
    assertThat("The output is the expected one", output, equalTo(expectedOutput));
  }

  @Test
  void shouldConvertUserToNull() {

    // Given
    UserModel input = null;

    // When
    var output = this.mapper.convert(input);

    // Then
    assertThat("The output is null", output, nullValue());
  }

  @Test
  void shouldConvertUserToUserDTO() {

    // Given
    UserModel input = this.buildUserModel();
    UserDTO expectedOutput = this.buildUserDTO();

    // When
    var output = this.mapper.convert(input);

    // Then
    assertThat("The output is the expected one", output, equalTo(expectedOutput));
  }

  @Test
  void shouldConvertUserDTOToNull() {

    // Given
    UserDTO input = null;

    // When
    var output = this.mapper.convert(input);

    // Then
    assertThat("The output is null", output, nullValue());
  }

  @Test
  void shouldConvertUserDTOToUserModel() {

    // Given
    UserModel expectedOutput = this.buildUserModel();
    UserDTO input = this.buildUserDTO();

    // When
    var output = this.mapper.convert(input);

    // Then
    assertThat("The output is the expected one", output, equalTo(expectedOutput));
  }

  private UserDTO buildUserDTO() {
    var userDto = new UserDTO();
    userDto.setEmail(EMAIL);
    userDto.setFirstName(FIRST_NAME);
    userDto.setLastName(LAST_NAME);
    userDto.setPhone(PHONE);
    userDto.setUsername(USERNAME);
    return userDto;
  }

  private UserModel buildUserModel() {
    var userModel = new UserModel();
    userModel.email(EMAIL);
    userModel.firstName(FIRST_NAME);
    userModel.lastName(LAST_NAME);
    userModel.phone(PHONE);
    userModel.username(USERNAME);
    return userModel;
  }
}
