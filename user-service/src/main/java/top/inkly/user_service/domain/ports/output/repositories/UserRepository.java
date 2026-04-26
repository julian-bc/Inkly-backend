package top.inkly.user_service.domain.ports.output.repositories;

import top.inkly.shared.domain.pagination.PaginationRequest;
import top.inkly.shared.domain.pagination.PaginationResult;
import top.inkly.user_service.domain.filters.UserFilters;
import top.inkly.user_service.domain.models.UserModel;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    PaginationResult<UserModel> findAll(PaginationRequest request, UserFilters filters);
    Optional<UserModel> findById(UUID id);
    void save(UserModel user);
}
