package top.inkly.verification_service.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.inkly.verification_service.application.service.IVerificationService;
import top.inkly.verification_service.domain.exceptions.business.NoAttemptsAvailableException;
import top.inkly.verification_service.domain.exceptions.business.VerificationCodeIsExpiredException;
import top.inkly.verification_service.domain.exceptions.business.VerificationNotFoundException;
import top.inkly.verification_service.domain.models.VerificationModel;
import top.inkly.verification_service.domain.ports.output.repository.VerificationRepository;
import top.inkly.verification_service.domain.ports.output.user.UserConnectorPort;

@Service
@RequiredArgsConstructor
public class VerificationService implements IVerificationService {
    private final VerificationRepository repository;
    private final UserConnectorPort userConnectorPort;

    @Override
    public void saveVerificationRecord(VerificationModel verificationModel) {
        // open feign user-service exists userById
        userConnectorPort.existsUserById(verificationModel.getUserId());

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

        if (existingVerification.isExpired()) {
            repository.deleteById(existingVerification.getVerificationId());
            throw new VerificationCodeIsExpiredException("El código de verificación ha caducado.");
        }

        if (existingVerification.getAttempts() == 0) {
            repository.deleteById(existingVerification.getVerificationId());
            throw new NoAttemptsAvailableException("Número máximo de intentos alcanzado, el código ya no está disponible.");
        }

        if (!existingVerification.isValidCode(verificationModel.getCode())) {
            Integer attempts = existingVerification.getAttempts() - 1;
            existingVerification.setAttempts(attempts);
        }
    }
}
