package top.inkly.user_service.application.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.inkly.shared.domain.pagination.PageResponse;
import top.inkly.shared.domain.pagination.PaginationRequest;
import top.inkly.shared.domain.pagination.PaginationResult;
import top.inkly.user_service.application.services.IRoleService;
import top.inkly.user_service.application.services.IUserService;
import top.inkly.user_service.domain.exceptions.verification.NotFoundVerificationAvailable;
import top.inkly.user_service.domain.filters.UserFilters;
import top.inkly.user_service.domain.exceptions.business.FailedDatabaseOperation;
import top.inkly.user_service.domain.exceptions.business.UserNotFoundException;
import top.inkly.shared.domain.models.user.RoleModel;
import top.inkly.user_service.domain.models.UserModel;
import top.inkly.user_service.domain.ports.output.cloudinary.CloudinaryConnectorPort;
import top.inkly.user_service.domain.ports.output.keycloak.KeycloakConnectorPort;
import top.inkly.user_service.domain.ports.output.queues.NotificationPublisherPort;
import top.inkly.user_service.domain.ports.output.repositories.UserRepository;
import top.inkly.user_service.domain.ports.output.verification.VerificationConnectorPort;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import static top.inkly.user_service.application.services.utils.UserNotificationsBuilder.buildWelcomeNotification;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository repository;
    private final IRoleService roleService;
    private final KeycloakConnectorPort keycloak;
    private final NotificationPublisherPort notificationPublisher;
    private final CloudinaryConnectorPort cloudinaryConnectorPort;
    private final VerificationConnectorPort verificationConnectorPort;

    @Override
    public PageResponse<UserModel> findUsers(PaginationRequest request, UserFilters filters) {
        PaginationResult<UserModel> pagination = repository.findAll(request, filters);

        return PageResponse.<UserModel>builder()
                .data(pagination.getContent())
                .meta(pagination.toMetaData())
                .build();
    }

    @Override
    public UserModel findUser(UUID userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuario con id " + userId + " no encontrado!"));
    }

    @Override
    @SneakyThrows
    public void createUser(UserModel user) {
        if (Objects.isNull(user.getRole())) {
            RoleModel userRole = roleService.findRole(2);
            user.setRole(userRole);
        }

        user.setCreatedAt(LocalDateTime.now());
        user.setEmailVerified(user.getEmailVerified());
        user.setPasswordVerified(true);
        user.setEnable(true);

        String userId = keycloak.saveKeycloakUser(user);
        user.setUserId(UUID.fromString(userId));

        try {
            repository.save(user);
        } catch (Exception e) {
            keycloak.rollbackKeycloakUserCreation(userId);
            throw new FailedDatabaseOperation("Error al crear usuario en Base de Datos: " + e.getMessage());
        }

        notificationPublisher.publishNotificationMessage(buildWelcomeNotification(user));
    }

    @Override
    public void updateUser(UUID userId, UserModel userUpdated) {
        UserModel userSaved = this.findUser(userId);
        String email = userSaved.getEmail();

        userSaved.setUserName(userUpdated.getUserName());
        userSaved.setEmail(userUpdated.getEmail());
        userSaved.setUpdatedAt(LocalDateTime.now());

        if (!email.equals(userUpdated.getEmail())) {
            userSaved.setEmailVerified(false);
        }

        keycloak.updateKeycloakUser(userId.toString(), userSaved);
        try {
            repository.save(userSaved);
        } catch (Exception e) {
            keycloak.updateKeycloakUser(userId.toString(), this.findUser(userId));
            throw new FailedDatabaseOperation("Error al actualizar usuario en Base de Datos: " + e.getMessage());
        }
    }

    @Override
    public void toggleUserStatus(UUID userId) {
        UserModel userSaved = this.findUser(userId);

        userSaved.setEnable(!userSaved.getEnable());
        userSaved.setUpdatedAt(LocalDateTime.now());

        keycloak.changeKeycloakUserStatus(userId.toString());
        repository.save(userSaved);
    }

    @Override
    public void updateProfileImage(UUID userId, MultipartFile profileImage) {
        UserModel userSaved = this.findUser(userId);
        String profileImageUrl = userSaved.getProfileImageUrl();

        if (profileImageUrl != null) {
            cloudinaryConnectorPort.deleteFile(profileImageUrl);
        }

        profileImageUrl = cloudinaryConnectorPort.uploadFile(profileImage);
        userSaved.setProfileImageUrl(profileImageUrl);
        userSaved.setUpdatedAt(LocalDateTime.now());

        repository.save(userSaved);
    }

    @Override
    public void updateForgottenPassword(UUID userId, String passwordUpdated) {
        UserModel userSaved = this.findUser(userId);

        if (!verificationConnectorPort.existsByUserIdAndVerificationStatus(userId)) {
            throw new NotFoundVerificationAvailable("No se encontró una verificación disponible con estado VERIFICADO para el usuario especificado.");
        }

        keycloak.updateForgottenPassword(userId.toString(), passwordUpdated);

        userSaved.setUpdatedAt(LocalDateTime.now());
        repository.save(userSaved);
    }

    @Override
    public void deleteProfileImage(UUID userId) {
        UserModel userSaved = this.findUser(userId);

        cloudinaryConnectorPort.deleteFile(userSaved.getProfileImageUrl());
        userSaved.setProfileImageUrl(null);
        userSaved.setUpdatedAt(LocalDateTime.now());

        repository.save(userSaved);
    }
}
