package org.example.ex4.mapper;

import org.example.ex4.dto.request.UserCreationRequest;
import org.example.ex4.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface Mapping {
    User toUser (UserCreationRequest request);
}
