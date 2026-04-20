package top.inkly.user_service.domain.models.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseModel {

    private String userId;
    private String accessToken;
    private String refreshToken;

}
