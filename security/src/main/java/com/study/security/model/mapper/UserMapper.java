package com.study.security.model.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.study.security.model.dto.UserDTO;
import com.study.security.model.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);

    default List<UserDTO> userListToUserDTOlist(List<User> users){

        if(users == null){

            return new ArrayList<UserDTO>();

        }

        return users.stream().map(this::userToUserDTO).collect(Collectors.toList());
        
    }
}
