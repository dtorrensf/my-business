package com.business.backend.api.controllers;


import com.business.backend.api.generated.api.UsersApi;
import com.business.backend.api.generated.model.ErrorModel;
import com.business.backend.api.generated.model.IdModel;
import com.business.backend.api.generated.model.NewUserModel;
import com.business.backend.api.mappers.UserDTOMapper;
import com.business.backend.domain.exceptions.MyBusinessException;
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
    try {
      this.service.createUser(user);
    } catch (MyBusinessException e) {
      var error = new ErrorModel();
      error.message(e.getMessage());
      return ResponseEntity.badRequest().body(error);
    }
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
