package top.inkly.user_service.infrastructure.database.mapper;

import org.mapstruct.Mapper;
import top.inkly.user_service.domain.models.User;
import top.inkly.user_service.infrastructure.database.entities.UserEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RoleMapperInfra.class})
public interface UserMapperInfra {
    List<User> toDomain(List<UserEntity> userEntityList);
    User toDomain(UserEntity userEntity);
    UserEntity toInfra(User user);
}
