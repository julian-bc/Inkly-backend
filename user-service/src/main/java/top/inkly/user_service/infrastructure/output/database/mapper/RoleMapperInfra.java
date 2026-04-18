package top.inkly.user_service.infrastructure.output.database.mapper;

import org.mapstruct.Mapper;
import top.inkly.user_service.domain.models.RoleModel;
import top.inkly.user_service.infrastructure.output.database.entities.RoleEntity;

@Mapper(componentModel = "spring")
public interface RoleMapperInfra {
    RoleModel toDomain(RoleEntity roleEntity);
}
