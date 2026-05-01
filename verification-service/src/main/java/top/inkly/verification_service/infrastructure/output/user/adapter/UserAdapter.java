package top.inkly.verification_service.infrastructure.output.user.adapter;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.inkly.verification_service.domain.exceptions.user.UserNotFoundException;
import top.inkly.verification_service.domain.ports.output.user.UserConnectorPort;
import top.inkly.verification_service.infrastructure.output.user.client.UserClient;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserAdapter implements UserConnectorPort {
    private final UserClient userClient;

    @Override
    public void existsUserById(UUID userId) {
        try {
            userClient.getUserById(userId);
        } catch (FeignException ex) {
            throw new UserNotFoundException("Usuario con ID: " + userId + " no encontrado.");
        }
    }
}
