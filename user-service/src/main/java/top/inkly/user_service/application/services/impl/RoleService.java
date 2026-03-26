package top.inkly.user_service.application.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.inkly.user_service.application.services.IRoleService;
import top.inkly.user_service.domain.models.Role;
import top.inkly.user_service.domain.repositories.RoleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final RoleRepository repository;

    @Override
    public List<Role> findRoles() {
        return repository.findAll();
    }
}
