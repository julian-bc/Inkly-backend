package top.inkly.user_service.infrastructure.output.verification.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "verification-service")
public interface VerificationClient {
    @GetMapping(value = "/available/{userId}")
    boolean existsVerifyCodeForgottenPasswordByUserId(@PathVariable UUID userId);
}
