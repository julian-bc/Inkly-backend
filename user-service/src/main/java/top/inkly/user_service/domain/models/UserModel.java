package top.inkly.user_service.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.inkly.shared.domain.models.user.RoleModel;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private UUID userId;
    private String userName;
    private String email;
    private String password;
    private Boolean emailVerified;
    private Boolean passwordVerified;
    private Boolean enable;
    private RoleModel role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
