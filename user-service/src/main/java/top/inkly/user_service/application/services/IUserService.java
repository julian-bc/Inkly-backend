package top.inkly.user_service.application.services;

import top.inkly.shared.domain.PageResponse;
import top.inkly.shared.domain.PaginationRequest;
import top.inkly.user_service.domain.models.User;

import java.util.UUID;

public interface IUserService {
    PageResponse<User> findUsers(PaginationRequest request);
    User findUser(UUID userId);
    void createUser(User user);
    void updateUser(UUID userId, User userUpdated);
    void disableUser(UUID userId);
}
