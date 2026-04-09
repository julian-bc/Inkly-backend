package top.inkly.user_service.infrastructure.restapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUser {
    private String userName;
    private String email;
    private String password;
}
