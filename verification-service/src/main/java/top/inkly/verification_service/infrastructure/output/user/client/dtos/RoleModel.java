package top.inkly.verification_service.infrastructure.output.user.client.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleModel {
    private Integer roleId;
    private RoleNames roleName;
}
