package top.inkly.user_service.domain.repositories;

import top.inkly.user_service.domain.models.Role;

import java.util.List;

public interface RoleRepository {
    List<Role> findAll();
}
