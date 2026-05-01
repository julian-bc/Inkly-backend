package top.inkly.shared.domain.models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.inkly.user_service.domain.models.enums.RoleNames;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleModel {
    private Integer roleId;
    private RoleNames roleName;
}
