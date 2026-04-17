package top.inkly.user_service.application.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.inkly.user_service.application.services.IRoleService;
import top.inkly.user_service.domain.exceptions.RoleNotFoundException;
import top.inkly.user_service.domain.models.Role;
import top.inkly.user_service.domain.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final RoleRepository repository;

    @Override
    public Role findRole(Integer roleId) {
        return repository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Role con id " + roleId + " no encontrado!"));
    }
}
