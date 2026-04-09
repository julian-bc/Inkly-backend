package top.inkly.user_service.application.services;

import top.inkly.user_service.domain.models.Role;

public interface IRoleService {
    Role findRole(Integer roleId);
}
