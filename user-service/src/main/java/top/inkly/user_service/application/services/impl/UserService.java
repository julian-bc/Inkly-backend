package top.inkly.user_service.application.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.inkly.user_service.application.services.IUserService;
import top.inkly.user_service.domain.exceptions.UserNotFoundException;
import top.inkly.user_service.domain.models.User;
import top.inkly.user_service.domain.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository repository;

    @Override
    public List<User> findUsers() {
        return repository.findAll();
    }

    @Override
    public User findUser(UUID userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuario con " + userId + " no encontrado!"));
    }

    @Override
    public void createUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        repository.save(user);
    }

    @Override
    public void updateUser(UUID userId, User userUpdated) {
        User userSaved = this.findUser(userId);

        userSaved.setUserName(userUpdated.getUserName());
        userSaved.setEmail(userUpdated.getEmail());
        userSaved.setRoles(userUpdated.getRoles());
        userSaved.setUpdatedAt(LocalDateTime.now());

        repository.save(userSaved);
    }

    @Override
    public void resetPassword(User userUpdated) {
        userUpdated.setUpdatedAt(LocalDateTime.now());
        repository.save(userUpdated);
    }

    @Override
    public void deleteUser(UUID userId) {
        repository.deleteById(userId);
    }
}
