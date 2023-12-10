package com.business.backend.api.controllers;


import com.business.backend.api.UsersApi;
import com.business.backend.model.IdModel;
import com.business.backend.model.NewUserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class UsersController implements UsersApi {
    @Override
    public ResponseEntity<IdModel> createUser(NewUserModel newUserModel) {
        return ResponseEntity.ok().build();
    }
}
