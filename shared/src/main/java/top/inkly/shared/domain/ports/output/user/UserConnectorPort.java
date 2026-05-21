package top.inkly.shared.domain.ports.output.user;

import top.inkly.shared.infrastructure.input.rest.dtos.user.UserResponse;

import java.util.UUID;

public interface UserConnectorPort {
    UserResponse findUserById(UUID userId);
    UserResponse findUserByUsernameOrEmail(String usernameOrEmail);
}
