package com.business.backend.api.controllers;


import com.business.backend.api.generated.model.NewUserModel;
import com.business.backend.api.generated.model.UserModel;
import com.business.backend.api.mappers.UserDTOMapper;
import com.business.backend.domain.dtos.UserDTO;
import com.business.backend.domain.exceptions.DuplicatedItemException;
import com.business.backend.domain.exceptions.ErrorItemEnum;
import com.business.backend.domain.exceptions.NotFoundItemException;
import com.business.backend.domain.ports.in.UserService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UsersController.class)
class UsersControllerTest {

  private static final String BASE_URL = "/users";
  private static final String USER = """
      {
        "username": "username",
        "firstName": "John",
        "lastName": "James",
        "email": "john@email.com",
        "phone": "12345",
        "password": "12345"
      }""";
  private static final String USER_NOT_FOUND = "{\"error\":\"NOT_FOUND-USER\",\"message\":\"The user with id: 1 doesn't exists!\"}";
  private static final Long ID = 1L;

  @MockBean
  private UserDTOMapper userMapper;

  @MockBean
  private UserService userService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  @SneakyThrows
  @DisplayName("Should return Bad Request due to no request body")
  void shouldCreateUserReturnBadRequest() {
    // Given

    // When
    mockMvc.perform(
            post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
        )
        // Then
        .andExpect(status().is(400));
  }

  @Test
  @SneakyThrows
  @DisplayName("Should return Bad Request due to existing User")
  void shouldCreateUserReturnBadRequestForExistingUser() {
    // Given
    var user = mock(UserDTO.class);

    doReturn(user)
        .when(this.userMapper)
        .convert(any(NewUserModel.class));

    var expectedException = new DuplicatedItemException(ErrorItemEnum.USER, "The user user@mail.com already exists!");
    doThrow(expectedException)
        .when(userService)
        .createUser(user);

    var expectedContent = "{\"error\":\"DUPLICATED-USER\",\"message\":\"The user user@mail.com already exists!\"}";

    // When
    mockMvc.perform(
            post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(USER)
        )
        // Then
        .andExpect(status().is(400))
        .andExpect(content().json(expectedContent));
  }

  @Test
  @SneakyThrows
  @DisplayName("Should return Created due to valid body")
  void shouldCreateUserReturnCreated() {
    // Given
    var user = mock(UserDTO.class);
    doReturn(user)
        .when(this.userMapper)
        .convert(any(NewUserModel.class));

    doReturn(ID)
        .when(userService)
        .createUser(user);

    var expectedId = "{\"id\": 1}";

    // When
    mockMvc.perform(
            post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(USER)
        )
        // Then
        .andExpect(status().is(201))
        .andExpect(content().json(expectedId));
  }

  @Test
  @SneakyThrows
  @DisplayName("Should Return NO_CONTENT due to valid user")
  void shouldDeleteUser() {
    // Given

    doNothing()
        .when(userService)
        .deleteUser(ID);

    // When
    mockMvc.perform(
            delete(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
        // Then
        .andExpect(status().is(204));
  }

  @Test
  @SneakyThrows
  @DisplayName("Should Return BAD_REQUEST due to invalid input")
  void shouldDeleteUserReturnBadRequest() {
    // Given

    // When
    mockMvc.perform(
            delete(BASE_URL + "/other_id")
                .contentType(MediaType.APPLICATION_JSON)
        )
        // Then
        .andExpect(status().is(400));
  }

  @Test
  @SneakyThrows
  @DisplayName("Should Return NOT_FOUND due to not existent user")
  void shouldDeleteUserReturnNotFound() {
    // Given
    var exception = new NotFoundItemException(ErrorItemEnum.USER, "The user with id: 1 doesn't exists!");
    doThrow(exception)
        .when(userService)
        .deleteUser(ID);

    // When
    mockMvc.perform(
            delete(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
        // Then
        .andExpect(status().is(404))
        .andExpect(content().json(USER_NOT_FOUND));
  }

  @Test
  @SneakyThrows
  @DisplayName("Should Return OK due to valid user")
  void shouldUpdateUserDetails() {
    // Given
    var user = mock(UserDTO.class);

    doReturn(user)
        .when(userMapper)
        .convert(any(UserModel.class));

    doNothing()
        .when(userService)
        .updateUser(user);

    // When
    mockMvc.perform(
            patch(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(USER)
        )
        // Then
        .andExpect(status().is(200));
  }

  @Test
  @SneakyThrows
  @DisplayName("Should Return NOT_FOUND due to not existent user")
  void shouldUpdateUserReturnNotFound() {
    // Given
    var user = mock(UserDTO.class);

    doReturn(user)
        .when(userMapper)
        .convert(any(UserModel.class));

    var exception = new NotFoundItemException(ErrorItemEnum.USER, "The user with id: 1 doesn't exists!");
    doThrow(exception)
        .when(userService)
        .updateUser(user);

    // When
    mockMvc.perform(
            patch(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(USER)
        )
        // Then
        .andExpect(status().is(404))
        .andExpect(content().json(USER_NOT_FOUND));
  }

  @Test
  @SneakyThrows
  @DisplayName("Should Return user")
  void shouldGetUserReturnUser() {
    // Given
    var user = mock(UserDTO.class);
    doReturn(user)
        .when(userService)
        .getUser(ID);

    var userModel = new UserModel();
    userModel.email("john@email.com");
    userModel.firstName("John");
    userModel.lastName("James");
    userModel.phone("12345");
    userModel.username("username");

    doReturn(userModel)
        .when(userMapper)
        .convert(user);

    var expectedContent = "{\"username\": \"username\", \"firstName\": \"John\", \"lastName\": \"James\", \"email\": \"john@email.com\", \"phone\": \"12345\"}";

    // When
    mockMvc.perform(
            get(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
        // Then
        .andExpect(status().is(200))
        .andExpect(content().json(expectedContent));
  }

  @Test
  @SneakyThrows
  @DisplayName("Should Return user NOT_FOUND")
  void shouldGetUserReturnNotFound() {
    // Given
    var exception = new NotFoundItemException(ErrorItemEnum.USER, "The user with id: 1 doesn't exists!");
    doThrow(exception)
        .when(userService)
        .getUser(ID);

    // When
    mockMvc.perform(
            get(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
        // Then
        .andExpect(status().is(404))
        .andExpect(content().json(USER_NOT_FOUND));
  }
}
