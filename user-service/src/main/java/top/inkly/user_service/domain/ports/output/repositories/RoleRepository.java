package top.inkly.user_service.domain.ports.output.repositories;

import top.inkly.shared.domain.models.user.RoleModel;

import java.util.Optional;

public interface RoleRepository {
    Optional<RoleModel> findById(Integer id);
}
