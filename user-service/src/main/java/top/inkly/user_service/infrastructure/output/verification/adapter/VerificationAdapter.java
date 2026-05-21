package top.inkly.user_service.infrastructure.output.verification.adapter;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.inkly.shared.domain.models.verification.VerificationType;
import top.inkly.user_service.domain.ports.output.verification.VerificationConnectorPort;
import top.inkly.user_service.infrastructure.output.verification.client.VerificationClient;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VerificationAdapter implements VerificationConnectorPort {
    private final VerificationClient verificationClient;

    @Override
    public boolean existsVerifyCodeByUserIdAndVerificationType(UUID userId, VerificationType verificationType) {
        try {
            return verificationClient.existsVerifyCodeByUserIdAndVerificationType(userId, verificationType);
        } catch (FeignException ex) {
            throw new RuntimeException("Error en el llamado al servicio de verification-service.");
        }
    }
}
