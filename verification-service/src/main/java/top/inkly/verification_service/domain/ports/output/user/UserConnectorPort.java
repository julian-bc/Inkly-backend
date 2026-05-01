package top.inkly.verification_service.domain.ports.output.user;

import java.util.UUID;

public interface UserConnectorPort {
    void existsUserById(UUID userId);
}
