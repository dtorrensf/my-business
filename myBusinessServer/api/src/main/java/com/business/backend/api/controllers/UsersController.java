package com.business.backend.api.controllers;


import com.business.backend.api.generated.api.UsersApi;
import com.business.backend.api.generated.model.IdModel;
import com.business.backend.api.generated.model.NewUserModel;
import com.business.backend.api.generated.model.UserModel;
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


  // Todo: add exception handler class to manage all MyBusinessException
  @Override
  public ResponseEntity<IdModel> createUser(NewUserModel newUserModel) {
    var user = this.mapper.convert(newUserModel);

    this.service.createUser(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(new IdModel());
  }

  @Override
  public ResponseEntity<Void> deleteUser(Long userId) {
    return UsersApi.super.deleteUser(userId);
  }

  @Override
  public ResponseEntity<UserModel> getUser(Long userId) {
    return UsersApi.super.getUser(userId);
  }

  @Override
  public ResponseEntity<UserModel> updateUser(Long userId, UserModel userModel) {
    return UsersApi.super.updateUser(userId, userModel);
  }
}
