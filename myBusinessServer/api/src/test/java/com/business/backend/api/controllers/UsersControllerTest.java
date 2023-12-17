package com.business.backend.api.controllers;

import com.business.backend.api.generated.model.IdModel;
import com.business.backend.api.generated.model.NewUserModel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class UsersControllerTest {

  private final UsersController controller = new UsersController();

  @Test
  @DisplayName("Should return created 201 http status")
  @Disabled("Not implemented yet")
  void shouldCreateUserAndReturnCreatedStatus(){
    // Given
    var newUser = mock(NewUserModel.class);
    var id = mock(IdModel.class);
    var expectedOutput = ResponseEntity.status(HttpStatus.CREATED).body(id);

    // When
    var output= this.controller.createUser(newUser);

    // Then
    assertThat("The output must be the expected one",output,equalTo(expectedOutput));
  }
}
