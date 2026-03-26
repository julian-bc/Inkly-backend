package top.inkly.user_service.application.services;

import top.inkly.user_service.domain.models.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findRoles();
}
