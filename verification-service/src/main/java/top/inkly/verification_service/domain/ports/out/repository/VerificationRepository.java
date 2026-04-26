package top.inkly.verification_service.domain.ports.out.repository;

import top.inkly.verification_service.domain.models.VerificationModel;
import top.inkly.verification_service.domain.models.enums.VerificationType;

import java.util.UUID;

public interface VerificationRepository {
    VerificationModel findByUserIdAndVerificationType(UUID userId, VerificationType verificationType);
    void save(VerificationModel verificationModel);
    void deleteById(UUID verificationId);
}
