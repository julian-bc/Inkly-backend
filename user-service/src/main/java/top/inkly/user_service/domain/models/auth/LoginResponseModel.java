package top.inkly.user_service.domain.models.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class LoginResponseModel extends SessionResponseModel {

    public LoginResponseModel(String userId, String accessToken, String refreshToken) {
        super(accessToken);
        this.refreshToken = refreshToken;
        this.userId = userId;
    }

    private String refreshToken;
    private String userId;

}
