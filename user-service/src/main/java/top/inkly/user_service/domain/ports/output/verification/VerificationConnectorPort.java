package top.inkly.user_service.domain.ports.output.verification;

import java.util.UUID;

public interface VerificationConnectorPort {
    boolean existsByUserIdAndVerificationStatus(UUID userId);
}
