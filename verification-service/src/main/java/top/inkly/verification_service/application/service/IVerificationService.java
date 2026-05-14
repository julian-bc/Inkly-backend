package top.inkly.verification_service.application.service;

import top.inkly.verification_service.domain.models.VerificationModel;

import java.util.UUID;

public interface IVerificationService {
    void saveVerificationRecord(VerificationModel verificationModel);
    void verifyCode(VerificationModel verificationModel);
    boolean existsVerifyCodeForgottenPasswordByUserId(UUID userId);
}
