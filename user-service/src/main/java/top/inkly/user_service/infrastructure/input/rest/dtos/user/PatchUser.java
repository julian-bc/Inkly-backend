package top.inkly.user_service.infrastructure.input.rest.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatchUser {
    private String userName;
    private String email;
}
