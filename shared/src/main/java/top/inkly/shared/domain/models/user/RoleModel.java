package top.inkly.shared.domain.models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleModel {
    private Integer roleId;
    private RoleNames roleName;
}
