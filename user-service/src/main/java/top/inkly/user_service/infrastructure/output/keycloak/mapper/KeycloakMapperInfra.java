package top.inkly.user_service.infrastructure.output.keycloak.mapper;

import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import top.inkly.user_service.domain.models.UserModel;

@Mapper(componentModel = "spring")
public interface KeycloakMapperInfra {

    @Mapping(source = "userId", target = "id")
    @Mapping(source = "userName", target = "username")
    @Mapping(source = "email", target = "email")
    UserRepresentation toRepresentation(UserModel user);

    @Mapping(source = "id", target = "userId")
    @Mapping(source = "username", target = "userName")
    @Mapping(source = "email", target = "email")
    UserModel toModel(UserRepresentation user);

}
