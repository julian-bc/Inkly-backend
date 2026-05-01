package top.inkly.user_service.application.services;

import top.inkly.shared.domain.models.user.RoleModel;

public interface IRoleService {
    RoleModel findRole(Integer roleId);
}
