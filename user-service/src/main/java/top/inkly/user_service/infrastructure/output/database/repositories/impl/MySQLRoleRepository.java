package top.inkly.user_service.infrastructure.output.database.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import top.inkly.user_service.domain.models.RoleModel;
import top.inkly.user_service.domain.ports.output.repositories.RoleRepository;
import top.inkly.user_service.infrastructure.output.database.mapper.RoleMapperInfra;
import top.inkly.user_service.infrastructure.output.database.repositories.RoleJpaRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MySQLRoleRepository implements RoleRepository {
    private final RoleJpaRepository jpaRepository;
    private final RoleMapperInfra mapper;

    @Override
    public Optional<RoleModel> findById(Integer id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
}
