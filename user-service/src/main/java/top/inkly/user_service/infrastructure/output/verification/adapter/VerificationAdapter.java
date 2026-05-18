package top.inkly.user_service.infrastructure.output.verification.adapter;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.inkly.user_service.domain.ports.output.verification.VerificationConnectorPort;
import top.inkly.user_service.infrastructure.output.verification.client.VerificationClient;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VerificationAdapter implements VerificationConnectorPort {
    private final VerificationClient verificationClient;

    @Override
    public boolean existsByUserIdAndVerificationStatus(UUID userId) {
        try {
            return verificationClient.existsVerifyCodeForgottenPasswordByUserId(userId);
        } catch (FeignException ex) {
            throw new RuntimeException("Error en el llamado al servicio de verification-service.");
        }
    }
}
