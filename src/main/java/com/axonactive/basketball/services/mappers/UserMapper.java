package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.User;
import com.axonactive.basketball.services.dtos.UserDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "ownerName", expression = "java(user.getOwner().getFirstName() + \"\" + user.getOwner().getLastName())")
    UserDTO toDTO (User user);
    List<UserDTO> toDTOs(List<User> users);
    @AfterMapping
    default void setOwnerName(User user,@MappingTarget UserDTO target){
        target.setOwnerName(user.getOwner().getFirstName()+ " " + user.getOwner().getLastName());
    }
}
