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
    var user = this.service.getUser(userId);
    return ResponseEntity.ok(
        this.mapper.convert(user)
    );
  }

  @Override
  public ResponseEntity<UserModel> updateUser(Long userId, UserModel userModel) {
    var user = this.mapper.convert(userModel);
    this.service.updateUser(user);
    return ResponseEntity.ok().build();
  }
}
