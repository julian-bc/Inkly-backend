package top.inkly.verification_service.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.inkly.verification_service.application.service.IVerificationService;
import top.inkly.verification_service.domain.exceptions.business.NoAttemptsAvailableException;
import top.inkly.verification_service.domain.exceptions.business.VerificationCodeIsExpiredException;
import top.inkly.verification_service.domain.exceptions.business.VerificationNotFoundException;
import top.inkly.verification_service.domain.models.VerificationModel;
import top.inkly.verification_service.domain.ports.out.repository.VerificationRepository;

@Service
@RequiredArgsConstructor
public class VerificationService implements IVerificationService {
    private final VerificationRepository repository;

    @Override
    public void saveVerificationRecord(VerificationModel verificationModel) {
        // open feign user-service exists userById and active account?

        VerificationModel existingVerification = repository.findByUserIdAndVerificationType(
                verificationModel.getUserId(),
                verificationModel.getVerificationType()
        );

       if (existingVerification != null) {
           repository.deleteById(existingVerification.getVerificationId());
       }

       verificationModel.generateCode();
       verificationModel.setAttempts(5);
       verificationModel.loadDates();
       repository.save(verificationModel);
    }

    @Override
    public void verifyCode(VerificationModel verificationModel) {
        VerificationModel existingVerification = repository.findByUserIdAndVerificationType(
                verificationModel.getUserId(),
                verificationModel.getVerificationType()
        );

        if (existingVerification == null) {
            throw new VerificationNotFoundException("Código de verificación no existente.");
        }

        if (verificationModel.isExpired()) {
            repository.deleteById(existingVerification.getVerificationId());
            throw new VerificationCodeIsExpiredException("El código de verificación ha caducado.");
        }

        if (verificationModel.getAttempts() == 0) {
            repository.deleteById(existingVerification.getVerificationId());
            throw new NoAttemptsAvailableException("Número máximo de intentos alcanzado, el código ya no está disponible.");
        }

        if (!verificationModel.isValidCode(verificationModel.getCode())) {
            Integer attempts = existingVerification.getAttempts() - 1;
            existingVerification.setAttempts(attempts);
        }
    }
}
