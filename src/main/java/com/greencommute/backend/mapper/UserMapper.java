package com.greencommute.backend.mapper;

import com.greencommute.backend.dto.UserDto;
import com.greencommute.backend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "userId", target = "userId")
    UserDto toUserDto(User optionalUser);

}
