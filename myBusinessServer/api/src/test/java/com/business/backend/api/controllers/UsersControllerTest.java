package com.business.backend.api.controllers;


import com.business.backend.api.generated.model.NewUserModel;
import com.business.backend.api.mappers.UserDTOMapper;
import com.business.backend.domain.dtos.UserDTO;
import com.business.backend.domain.exceptions.MyBusinessException;
import com.business.backend.domain.ports.in.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UsersController.class)
class UsersControllerTest {

  private static final String BASE_URL = "/users";
  private static final String USER = "{\n" +
      "  \"username\": \"username\",\n" +
      "  \"firstName\": \"John\",\n" +
      "  \"lastName\": \"James\",\n" +
      "  \"email\": \"john@email.com\",\n" +
      "  \"phone\": \"12345\",\n" +
      "  \"password\": \"12345\"\n" +
      "}";

  @MockBean
  private UserDTOMapper userMapper;

  @MockBean
  private UserService userService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("Should return Bad Request due to no request body")
  void shouldCreateUserReturnBadRequest() throws Exception {
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
  @DisplayName("Should return Bad Request due to existing User")
  void shouldCreateUserReturnBadRequestForExistingUser() throws Exception {
    // Given
    var expectedMessage = "The user already exists!";
    var user = mock(UserDTO.class);

    doReturn(user)
        .when(this.userMapper)
        .convert(any(NewUserModel.class));

    var expectedException = new MyBusinessException("User Creation", "The user user@mail.com already exists!");
    doThrow(expectedException)
        .when(userService)
        .createUser(user);

    // When
    mockMvc.perform(
            post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(USER)
        )
        // Then
        .andExpect(status().is(400))
        .andExpect(content()
            .string(containsString(expectedMessage)));
  }

  @Test
  @DisplayName("Should return Created due to valid body")
  void shouldCreateUserReturnCreated() throws Exception {
    // Given

    // When
    mockMvc.perform(
            post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(USER)
        )
        // Then
        .andExpect(status().is(201));
  }
}
