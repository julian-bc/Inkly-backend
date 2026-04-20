package top.inkly.user_service.application.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.inkly.user_service.application.services.IAuthService;
import top.inkly.user_service.application.services.IUserService;
import top.inkly.user_service.domain.exceptions.keycloak.NotFoundKeycloakRoleException;
import top.inkly.user_service.domain.exceptions.keycloak.NotFoundKeycloakUserException;
import top.inkly.user_service.domain.models.UserModel;
import top.inkly.user_service.domain.models.auth.LoginRequestModel;
import top.inkly.user_service.domain.models.auth.LoginResponseModel;
import top.inkly.user_service.domain.ports.output.keycloak.AuthConnectorPort;
import top.inkly.user_service.domain.ports.output.keycloak.KeycloakConnectorPort;
import top.inkly.user_service.domain.shared.Constants;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final AuthConnectorPort auth;
    private final KeycloakConnectorPort keycloak;

    @Override
    public LoginResponseModel login(LoginRequestModel loginRequest) {
        Map<String, String> loginResponse = auth.login(loginRequest);

        UserModel authUser = keycloak.getKeycloakUserByUsername(loginRequest.getUsername());
        if (authUser == null) {
            authUser = keycloak.getKeycloakUserByEmail(loginRequest.getUsername());
        }

        if (authUser == null) {
            throw new NotFoundKeycloakUserException(loginRequest.getUsername());
        }

        return new LoginResponseModel(
                authUser.getUserId().toString(),
                loginResponse.get(Constants.ACCESS_TOKEN),
                loginResponse.get(Constants.REFRESH_TOKEN)
        );
    }

    @Override
    public void logout(String refreshToken) {

    }

    @Override
    public Map<String, String> tokenValidate(String accessToken) {
        return Map.of();
    }
}
