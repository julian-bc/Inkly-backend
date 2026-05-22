package top.inkly.shared.infrastructure.output.user.adapter;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.inkly.shared.domain.exceptions.user.UserNotFoundException;
import top.inkly.shared.domain.ports.output.user.UserConnectorPort;
import top.inkly.shared.infrastructure.input.rest.dtos.user.UserResponse;
import top.inkly.shared.infrastructure.output.user.client.UserClient;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserAdapter implements UserConnectorPort {
    private final UserClient userClient;

    @Override
    public UserResponse findUserById(UUID userId) {
        try {
            return userClient.getUserById(userId);
        } catch (FeignException ex) {
           throw new UserNotFoundException("Usuario con ID: " + userId + " no encontrado.");
        }
    }

    @Override
    public UserResponse findUserByUsernameOrEmail(String usernameOrEmail) {
        try {
            return userClient.getUserByUsernameOrEmail(usernameOrEmail);
        } catch (FeignException ex) {
            System.out.println(ex.getMessage());
            throw new UserNotFoundException("Usuario con email/username no encontrado.");
        }
    }
}
