package top.inkly.user_service.infrastructure.restapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.inkly.user_service.domain.models.Role;

import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private UUID userId;
    private String userName;
    private String email;
    private Role role;
}
