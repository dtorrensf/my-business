package com.business.backend.api.controllers;


import com.business.backend.api.generated.api.UsersApi;
import com.business.backend.api.generated.model.IdModel;
import com.business.backend.api.generated.model.NewUserModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class UsersController implements UsersApi {
    @Override
    public ResponseEntity<IdModel> createUser(NewUserModel newUserModel) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
