package top.inkly.verification_service.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.inkly.shared.infrastructure.input.rest.dtos.user.UserResponse;
import top.inkly.verification_service.application.service.IVerificationService;
import static top.inkly.verification_service.application.service.utils.OTPNotificationBuilder.buildOtpNotification;
import top.inkly.verification_service.domain.exceptions.business.NoAttemptsAvailableException;
import top.inkly.verification_service.domain.exceptions.business.VerificationCodeIsExpiredException;
import top.inkly.verification_service.domain.exceptions.business.VerificationInvalidCodeException;
import top.inkly.verification_service.domain.exceptions.business.VerificationNotFoundException;
import top.inkly.verification_service.domain.models.VerificationModel;
import top.inkly.verification_service.domain.models.enums.VerificationStatus;
import top.inkly.shared.domain.models.verification.VerificationType;
import top.inkly.verification_service.domain.ports.output.queues.NotificationPublisherPort;
import top.inkly.verification_service.domain.ports.output.repository.VerificationRepository;
import top.inkly.shared.domain.ports.output.user.UserConnectorPort;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerificationService implements IVerificationService {
    private final VerificationRepository repository;
    private final UserConnectorPort userConnectorPort;
    private final NotificationPublisherPort notificationPublisher;

    @Override
    public void saveVerificationRecord(String usernameOrEmail, VerificationType verificationType) {
        UserResponse userResponse = userConnectorPort.findUserByUsernameOrEmail(usernameOrEmail);

        VerificationModel verificationModel = new VerificationModel();

        VerificationModel existingVerification = repository.findByUserIdAndVerificationType(
                userResponse.getUserId(),
                verificationType
        );

       if (existingVerification != null) {
           repository.deleteById(existingVerification.getVerificationId());
       }

       verificationModel.generateCode();
       verificationModel.setAttempts(5);
       verificationModel.loadDates();
       verificationModel.setVerificationType(verificationType);
       verificationModel.setVerificationStatus(VerificationStatus.WAITING);
       repository.save(verificationModel);

       notificationPublisher.publishNotificationMessage(buildOtpNotification(
               userResponse.getEmail(),
               userResponse.getUserName(),
               verificationModel.getCode()
       ));
    }

    @Override
    public void verifyCode(String usernameOrEmail, VerificationType verificationType, String code) {
        UserResponse userResponse = userConnectorPort.findUserByUsernameOrEmail(usernameOrEmail);

        VerificationModel existingVerification = repository.findByUserIdAndVerificationType(
                userResponse.getUserId(),
                verificationType
        );

        if (existingVerification == null ||
                !existingVerification.getVerificationStatus().name().equals(VerificationStatus.WAITING.name())) {
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

        if (!existingVerification.isValidCode(code)) {
            Integer attempts = existingVerification.getAttempts() - 1;
            existingVerification.setAttempts(attempts);
            repository.save(existingVerification);
            throw new VerificationInvalidCodeException("Código Invalido");
        } else {
            existingVerification.setVerificationStatus(VerificationStatus.VERIFIED);
            repository.save(existingVerification);
        }
    }

    @Override
    public boolean existsVerifyCodeByUserIdAndVerificationType(UUID userId, VerificationType verificationType) {
        return repository.existsByUserIdWithStatusVerifiedAndType(userId, verificationType);
    }

}
