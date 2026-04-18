package top.inkly.user_service.infrastructure.output.database.mapper;

import org.mapstruct.Mapper;
import top.inkly.user_service.domain.models.UserModel;
import top.inkly.user_service.infrastructure.output.database.entities.UserEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RoleMapperInfra.class})
public interface UserMapperInfra {
    List<UserModel> toDomain(List<UserEntity> userEntityList);
    UserModel toDomain(UserEntity userEntity);
    UserEntity toInfra(UserModel user);
}
