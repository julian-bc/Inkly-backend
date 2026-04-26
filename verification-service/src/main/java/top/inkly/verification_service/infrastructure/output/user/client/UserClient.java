package top.inkly.verification_service.infrastructure.output.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.inkly.shared.infrastructure.input.rest.dtos.user.UserResponse;

import java.util.UUID;

@FeignClient(name = "user", url = "${user.server.url}")
public interface UserClient {
    @GetMapping(value = "${user.server.url}/{userId}")
    UserResponse getUserById(@PathVariable UUID userId);
}
