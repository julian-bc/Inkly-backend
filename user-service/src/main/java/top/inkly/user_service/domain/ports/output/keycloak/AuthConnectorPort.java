package top.inkly.user_service.domain.ports.output.keycloak;

import top.inkly.user_service.domain.models.auth.LoginRequestModel;

import java.util.Map;

public interface AuthConnectorPort {

    Map<String, String> login(LoginRequestModel loginRequest);

    void logout(String refreshToken);

    Map<String, Object> validateToken(String token);

    Map<String, String> refresh(String refreshToken);

}
