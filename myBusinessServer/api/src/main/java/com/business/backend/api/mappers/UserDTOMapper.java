package com.business.backend.api.mappers;

import com.business.backend.api.generated.model.NewUserModel;
import com.business.backend.domain.dtos.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserDTOMapper {
  UserDTO convert(NewUserModel any);
}
