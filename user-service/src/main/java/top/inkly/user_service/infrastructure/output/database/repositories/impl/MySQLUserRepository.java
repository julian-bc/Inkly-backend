package top.inkly.user_service.infrastructure.output.database.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import top.inkly.shared.domain.PaginationRequest;
import top.inkly.shared.domain.PaginationResult;
import top.inkly.user_service.domain.filters.UserFilters;
import top.inkly.user_service.domain.models.UserModel;
import top.inkly.user_service.domain.ports.output.repositories.UserRepository;
import top.inkly.user_service.infrastructure.output.database.entities.UserEntity;
import top.inkly.user_service.infrastructure.output.database.mapper.UserMapperInfra;
import top.inkly.user_service.infrastructure.output.database.repositories.UserJpaRepository;
import top.inkly.user_service.infrastructure.output.database.specification.UserSpecification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class MySQLUserRepository implements UserRepository {
    private final UserJpaRepository jpaRepository;
    private final UserMapperInfra mapper;

    @Override
    public PaginationResult<UserModel> findAll(PaginationRequest request, UserFilters filters) {
        Pageable pageable = PageRequest.of(
                request.getPageNumber(),
                request.getPageSize()
        );

        Page<UserEntity> page = jpaRepository.findAll(UserSpecification.withFilters(filters), pageable);

        List<UserModel> users = mapper.toDomain(page.getContent());

        return PaginationResult.<UserModel>builder()
                .content(users)
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    @Override
    public Optional<UserModel> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public void save(UserModel user) {
        jpaRepository.save(mapper.toInfra(user));
    }
}
