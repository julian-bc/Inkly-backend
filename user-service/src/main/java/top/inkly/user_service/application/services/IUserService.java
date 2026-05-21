package top.inkly.user_service.application.services;

import org.springframework.web.multipart.MultipartFile;
import top.inkly.shared.domain.pagination.PageResponse;
import top.inkly.shared.domain.pagination.PaginationRequest;
import top.inkly.user_service.domain.filters.UserFilters;
import top.inkly.user_service.domain.models.UserModel;

import java.util.UUID;

public interface IUserService {
    PageResponse<UserModel> findUsers(PaginationRequest request, UserFilters filters);
    UserModel findUser(UUID userId);
    UserModel findUser(String usernameOrEmail);
    void createUser(UserModel user);
    void updateUsername(UUID userId, String username);
    void toggleUserStatus(UUID userId);
    void updateProfileImage(UUID userId, MultipartFile profileImage);
    void updateForgottenPassword(String usernameOrEmail, String passwordUpdated);
    void updateEmail(String oldEmail, String newEmail);
    void deleteProfileImage(UUID userId);
}
