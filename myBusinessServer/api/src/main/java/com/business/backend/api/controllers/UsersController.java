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

  @Override
  public ResponseEntity<IdModel> createUser(NewUserModel newUserModel) {
    var user = this.mapper.convert(newUserModel);
    var id = this.service.createUser(user);

    var idModel = new IdModel();
    idModel.setId(id);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(idModel);
  }

  @Override
  public ResponseEntity<Void> deleteUser(Long userId) {
    this.service.deleteUser(userId);
    return ResponseEntity.noContent().build();
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
