package top.inkly.user_service.infrastructure.database.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import top.inkly.user_service.domain.models.Role;
import top.inkly.user_service.domain.repositories.RoleRepository;
import top.inkly.user_service.infrastructure.database.mapper.RoleMapperInfra;
import top.inkly.user_service.infrastructure.database.repositories.RoleJpaRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MySQLRoleRepository implements RoleRepository {
    private final RoleJpaRepository jpaRepository;
    private final RoleMapperInfra mapper;

    @Override
    public List<Role> findAll() {
        return mapper.toDomain(jpaRepository.findAll());
    }
}
