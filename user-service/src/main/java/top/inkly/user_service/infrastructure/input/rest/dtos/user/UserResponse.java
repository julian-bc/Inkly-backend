package top.inkly.user_service.infrastructure.input.rest.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.inkly.user_service.domain.models.RoleModel;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private UUID userId;
    private String userName;
    private String email;
    private boolean emailVerified;
    private boolean passwordVerified;
    private boolean enable;
    private RoleModel role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
