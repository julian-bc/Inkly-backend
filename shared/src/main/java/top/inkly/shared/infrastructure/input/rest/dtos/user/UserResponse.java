package top.inkly.shared.infrastructure.input.rest.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.inkly.shared.domain.models.user.RoleModel;

import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private UUID userId;
    private String userName;
    private String email;
    private RoleModel role;
}
