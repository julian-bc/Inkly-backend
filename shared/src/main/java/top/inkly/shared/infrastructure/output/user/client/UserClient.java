package top.inkly.shared.infrastructure.output.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.inkly.shared.infrastructure.input.rest.dtos.user.UserResponse;

import java.util.UUID;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping(value = "/users/{userId}")
    UserResponse getUserById(@PathVariable UUID userId);

    @GetMapping(value = "/users/username-email/{usernameOrEmail}")
    UserResponse getUserByUsernameOrEmail(@PathVariable String usernameOrEmail);
}
