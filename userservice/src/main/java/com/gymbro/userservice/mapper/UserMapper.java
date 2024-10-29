package com.gymbro.userservice.mapper;

import com.gymbro.userservice.DTO.UserDto;
import com.gymbro.userservice.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);
}
