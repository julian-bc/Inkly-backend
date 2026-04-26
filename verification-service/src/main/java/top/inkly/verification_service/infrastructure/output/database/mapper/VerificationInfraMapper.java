package top.inkly.verification_service.infrastructure.output.database.mapper;

import org.mapstruct.Mapper;
import top.inkly.verification_service.domain.models.VerificationModel;
import top.inkly.verification_service.infrastructure.output.database.entity.VerificationEntity;

@Mapper(componentModel = "spring")
public interface VerificationInfraMapper {
    VerificationModel toDomain(VerificationEntity verificationEntity);
    VerificationEntity toInfra(VerificationModel verificationModel);
}
