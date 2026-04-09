package top.inkly.user_service.domain.repositories;

import top.inkly.shared.domain.PaginationRequest;
import top.inkly.shared.domain.PaginationResult;
import top.inkly.user_service.application.services.filters.UserFilters;
import top.inkly.user_service.domain.models.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    PaginationResult<User> findAll(PaginationRequest request, UserFilters filters);
    Optional<User> findById(UUID id);
    void save(User user);
}
