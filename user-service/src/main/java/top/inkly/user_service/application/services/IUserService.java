package top.inkly.user_service.application.services;

import top.inkly.shared.domain.pagination.PageResponse;
import top.inkly.shared.domain.pagination.PaginationRequest;
import top.inkly.user_service.application.services.filters.UserFilters;
import top.inkly.user_service.domain.models.UserModel;

import java.util.UUID;

public interface IUserService {
    PageResponse<UserModel> findUsers(PaginationRequest request, UserFilters filters);
    UserModel findUser(UUID userId);
    void createUser(UserModel user);
    void updateUser(UUID userId, UserModel userUpdated);
    void toggleUserStatus(UUID userId);
}
