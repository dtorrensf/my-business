package com.business.backend.api.controllers;


import com.business.backend.api.generated.api.UsersApi;
import com.business.backend.api.generated.model.NewUserModel;
import com.business.backend.api.mappers.UserDTOMapper;
import com.business.backend.domain.ports.in.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsersController implements UsersApi {

  private final UserDTOMapper mapper;

  private final UserService service;

  @Override
  public ResponseEntity<Void> createUser(NewUserModel newUserModel) {
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
