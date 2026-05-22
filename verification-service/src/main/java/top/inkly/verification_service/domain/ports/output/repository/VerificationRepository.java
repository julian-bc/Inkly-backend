package top.inkly.verification_service.domain.ports.output.repository;

import top.inkly.verification_service.domain.models.VerificationModel;
import top.inkly.shared.domain.models.verification.VerificationType;

import java.util.UUID;

public interface VerificationRepository {
    VerificationModel findByUserIdAndVerificationType(UUID userId, VerificationType verificationType);
    void save(VerificationModel verificationModel);
    void deleteById(UUID verificationId);
    void deleteByUserId(UUID userId);
    boolean existsByUserIdWithStatusVerifiedAndType(UUID userId, VerificationType verificationType);
}
