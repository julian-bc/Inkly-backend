package top.inkly.verification_service.application.service;

import top.inkly.shared.domain.models.verification.VerificationType;

import java.util.UUID;

public interface IVerificationService {
    void saveVerificationRecord(String usernameOrEmail, VerificationType verificationType);
    void verifyCode(String usernameOrEmail, VerificationType verificationType, String code);
    boolean existsVerifyCodeByUserIdAndVerificationType(UUID userId, VerificationType verificationType);
}
