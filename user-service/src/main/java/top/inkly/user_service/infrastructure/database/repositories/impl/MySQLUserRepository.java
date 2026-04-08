package top.inkly.user_service.infrastructure.database.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import top.inkly.shared.domain.PaginationRequest;
import top.inkly.shared.domain.PaginationResult;
import top.inkly.user_service.domain.models.User;
import top.inkly.user_service.domain.repositories.UserRepository;
import top.inkly.user_service.infrastructure.database.entities.UserEntity;
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
    public PaginationResult<User> findAll(PaginationRequest request) {
        Pageable pageable = PageRequest.of(
                request.getPageNumber(),
                request.getPageSize()
        );

        Page<UserEntity> page = jpaRepository.findAll(pageable);

        List<User> users = mapper.toDomain(page.getContent());

        return PaginationResult.<User>builder()
                .content(users)
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
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
}
