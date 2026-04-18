package top.inkly.user_service.domain.ports.output.repositories;

import top.inkly.user_service.domain.models.RoleModel;

import java.util.Optional;

public interface RoleRepository {
    Optional<RoleModel> findById(Integer id);
}
