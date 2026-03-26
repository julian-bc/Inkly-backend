package top.inkly.user_service.infrastructure.database.mapper;

import org.mapstruct.Mapper;
import top.inkly.user_service.domain.models.Role;
import top.inkly.user_service.infrastructure.database.entities.RoleEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapperInfra {
    List<Role> toDomain(List<RoleEntity> roleEntityList);
    Role toDomain(RoleEntity roleEntity);
}
