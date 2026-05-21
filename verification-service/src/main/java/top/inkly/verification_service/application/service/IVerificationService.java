package top.inkly.verification_service.application.service;

import top.inkly.verification_service.domain.models.enums.VerificationType;

import java.util.UUID;

public interface IVerificationService {
    void saveVerificationRecord(String usernameOrEmail, VerificationType verificationType);
    void verifyCode(String usernameOrEmail, VerificationType verificationType, String code);
    boolean existsVerifyCodeForgottenPasswordByUserId(UUID userId);
}
