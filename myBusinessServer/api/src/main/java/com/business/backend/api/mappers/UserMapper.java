package com.business.backend.api.mappers;

import com.business.backend.api.generated.model.NewUserModel;
import com.business.backend.api.generated.model.UserModel;
import com.business.backend.domain.dtos.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserDTO convert(NewUserModel newUserModel);

  UserDTO convert(UserModel userModel);

  UserModel convert(UserDTO userDTO);
}
