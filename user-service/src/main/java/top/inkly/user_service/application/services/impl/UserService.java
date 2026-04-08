package top.inkly.user_service.application.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import top.inkly.shared.domain.MetaData;
import top.inkly.shared.domain.PageResponse;
import top.inkly.shared.domain.PaginationRequest;
import top.inkly.shared.domain.PaginationResult;
import top.inkly.user_service.application.services.IUserService;
import top.inkly.user_service.domain.exceptions.UserNotFoundException;
import top.inkly.user_service.domain.models.User;
import top.inkly.user_service.domain.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository repository;

    public PageResponse<User> findUsers(PaginationRequest request) {
        PaginationResult<User> pagination = repository.findAll(request);

        return PageResponse.<User>builder()
                .data(pagination.getContent())
                .meta(pagination.toMetaData())
                .build();
    }

    @Override
    public User findUser(UUID userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuario con " + userId + " no encontrado!"));
    }

    @Override
    public void createUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setEmailVerified(false);
        user.setPasswordVerified(true);
        user.setEnable(true);
        repository.save(user);
    }

    @Override
    public void updateUser(UUID userId, User userUpdated) {
        User userSaved = this.findUser(userId);
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
        User userSaved = this.findUser(userId);

        userSaved.setEnable(false);
        userSaved.setUpdatedAt(LocalDateTime.now());

        repository.save(userSaved);
    }
}
