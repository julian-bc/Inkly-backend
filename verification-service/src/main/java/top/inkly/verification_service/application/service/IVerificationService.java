package top.inkly.verification_service.application.service;

import top.inkly.verification_service.domain.models.VerificationModel;
import top.inkly.verification_service.domain.models.enums.VerificationType;

import java.util.UUID;

public interface IVerificationService {
    void saveVerificationRecord(VerificationModel verificationModel);
    void verifyCode(VerificationModel verificationModel);
}
