package top.inkly.user_service.infrastructure.database.mapper;

import org.mapstruct.Mapper;
import top.inkly.user_service.domain.models.Role;
import top.inkly.user_service.infrastructure.database.entities.RoleEntity;

@Mapper(componentModel = "spring")
public interface RoleMapperInfra {
    Role toDomain(RoleEntity roleEntity);
}
