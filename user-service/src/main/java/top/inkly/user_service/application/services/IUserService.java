package top.inkly.user_service.application.services;

import top.inkly.user_service.domain.models.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    List<User> findUsers();
    User findUser(UUID userId);
    void createUser(User user);
    void updateUser(UUID userId, User userUpdated);
    void resetPassword(User userUpdated);
    void deleteUser(UUID userId);
}
