package top.inkly.verification_service.infrastructure.output.database.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import top.inkly.verification_service.domain.models.VerificationModel;
import top.inkly.verification_service.domain.models.enums.VerificationStatus;
import top.inkly.shared.domain.models.verification.VerificationType;
import top.inkly.verification_service.domain.ports.output.repository.VerificationRepository;
import top.inkly.verification_service.infrastructure.output.database.mapper.VerificationInfraMapper;
import top.inkly.verification_service.infrastructure.output.database.repository.VerificationJpaRepository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class MySQLVerificationRepository implements VerificationRepository {
    private final VerificationJpaRepository jpaRepository;
    private final VerificationInfraMapper mapper;

    @Override
    public VerificationModel findByUserIdAndVerificationType(UUID userId, VerificationType verificationType) {
        return mapper.toDomain(jpaRepository
                .findByUserIdAndVerificationType(userId, verificationType));
    }

    @Override
    public void save(VerificationModel verificationModel) {
        jpaRepository.save(mapper.toInfra(verificationModel));
    }

    @Override
    public void deleteById(UUID verificationId) {
        jpaRepository.deleteById(verificationId);
    }

    @Override
    public void deleteByUserId(UUID userId) {
        jpaRepository.deleteByUserId((userId));
    }

    @Override
    @Transactional
    public boolean existsByUserIdWithStatusVerifiedAndType(UUID userId, VerificationType verificationType) {
        return jpaRepository.existsByUserIdAndVerificationStatusAndVerificationType(
                userId,
                VerificationStatus.VERIFIED,
                verificationType
        );
    }
}
