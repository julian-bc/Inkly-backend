package top.inkly.user_service.infrastructure.database.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import top.inkly.user_service.domain.models.Role;
import top.inkly.user_service.domain.repositories.RoleRepository;
import top.inkly.user_service.infrastructure.database.mapper.RoleMapperInfra;
import top.inkly.user_service.infrastructure.database.repositories.RoleJpaRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MySQLRoleRepository implements RoleRepository {
    private final RoleJpaRepository jpaRepository;
    private final RoleMapperInfra mapper;

    @Override
    public Optional<Role> findById(Integer id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
}
