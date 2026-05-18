package top.inkly.verification_service.infrastructure.output.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.inkly.verification_service.domain.models.enums.VerificationStatus;
import top.inkly.verification_service.domain.models.enums.VerificationType;
import top.inkly.verification_service.infrastructure.output.database.entity.VerificationEntity;

import java.util.UUID;

public interface VerificationJpaRepository extends JpaRepository<VerificationEntity, UUID> {
    VerificationEntity findByUserIdAndVerificationType(UUID userId, VerificationType verificationType);
    boolean existsByUserIdAndVerificationStatusAndVerificationType(UUID userId, VerificationStatus verificationStatus, VerificationType verificationType);
}
