package top.inkly.user_service.infrastructure.database.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import top.inkly.user_service.domain.models.User;
import top.inkly.user_service.domain.repositories.UserRepository;
import top.inkly.user_service.infrastructure.database.mapper.UserMapperInfra;
import top.inkly.user_service.infrastructure.database.repositories.UserJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class MySQLUserRepository implements UserRepository {
    private final UserJpaRepository jpaRepository;
    private final UserMapperInfra mapper;

    @Override
    public List<User> findAll() {
        return mapper.toDomain(jpaRepository.findAll());
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public void save(User user) {
        jpaRepository.save(mapper.toInfra(user));
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}
