package top.inkly.user_service.domain.ports.output.verification;

import top.inkly.shared.domain.models.verification.VerificationType;

import java.util.UUID;

public interface VerificationConnectorPort {
    boolean existsVerifyCodeByUserIdAndVerificationType(UUID userId, VerificationType verificationType);
}
