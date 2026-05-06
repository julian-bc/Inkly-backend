package top.inkly.shared.domain.ports.output.user;

import java.util.UUID;

public interface UserConnectorPort {
    void existsUserById(UUID userId);
}
