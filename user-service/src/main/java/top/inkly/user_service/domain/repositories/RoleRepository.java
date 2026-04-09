package top.inkly.user_service.domain.repositories;

import top.inkly.user_service.domain.models.Role;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findById(Integer id);
}
