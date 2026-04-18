package top.inkly.user_service.application.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.inkly.shared.domain.PageResponse;
import top.inkly.shared.domain.PaginationRequest;
import top.inkly.shared.domain.PaginationResult;
import top.inkly.user_service.application.services.IRoleService;
import top.inkly.user_service.application.services.IUserService;
import top.inkly.user_service.application.services.filters.UserFilters;
import top.inkly.user_service.domain.exceptions.business.UserNotFoundException;
import top.inkly.user_service.domain.models.RoleModel;
import top.inkly.user_service.domain.models.UserModel;
import top.inkly.user_service.domain.ports.output.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository repository;
    private final IRoleService roleService;

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
    public void createUser(UserModel user) {
        RoleModel userRole = roleService.findRole(2);

        user.setCreatedAt(LocalDateTime.now());
        user.setEmailVerified(false);
        user.setPasswordVerified(true);
        user.setEnable(true);
        user.setRole(userRole);
        repository.save(user);
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

        repository.save(userSaved);
    }

    @Override
    public void disableUser(UUID userId) {
        UserModel userSaved = this.findUser(userId);

        userSaved.setEnable(false);
        userSaved.setUpdatedAt(LocalDateTime.now());

        repository.save(userSaved);
    }
}
