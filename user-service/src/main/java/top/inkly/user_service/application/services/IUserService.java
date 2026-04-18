package top.inkly.user_service.application.services;

import top.inkly.shared.domain.PageResponse;
import top.inkly.shared.domain.PaginationRequest;
import top.inkly.user_service.application.services.filters.UserFilters;
import top.inkly.user_service.domain.models.UserModel;

import java.util.UUID;

public interface IUserService {
    PageResponse<UserModel> findUsers(PaginationRequest request, UserFilters filters);
    UserModel findUser(UUID userId);
    void createUser(UserModel user);
    void updateUser(UUID userId, UserModel userUpdated);
    void disableUser(UUID userId);
}
