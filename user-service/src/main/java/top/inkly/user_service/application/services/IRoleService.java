package top.inkly.user_service.application.services;

import top.inkly.user_service.domain.models.RoleModel;

public interface IRoleService {
    RoleModel findRole(Integer roleId);
}
