package top.inkly.user_service.infrastructure.output.keycloak.adapter;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import top.inkly.user_service.domain.exceptions.auth.IncorrectCredentialsException;
import top.inkly.user_service.domain.exceptions.keycloak.FailedKeycloakOperationException;
import top.inkly.user_service.domain.models.auth.LoginRequestModel;
import top.inkly.user_service.domain.ports.output.keycloak.AuthConnectorPort;
import top.inkly.user_service.domain.shared.Constants;
import top.inkly.user_service.infrastructure.output.keycloak.client.KeycloakAuthClient;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class KeycloakAuthAdapter implements AuthConnectorPort {

    private final KeycloakAuthClient authClient;

    @Value("${keycloak.client.id}")
    private String clientId;

    @Value("${keycloak.client.secret}")
    private String clientSecret;

    @Override
    public Map<String, String> login(LoginRequestModel loginRequest) {
        Map<String, String> authData = Map.of(
                Constants.GRANT_TYPE, Constants.PASSWORD,
                Constants.CLIENT_ID, clientId,
                Constants.CLIENT_SECRET, clientSecret,
                Constants.USERNAME, loginRequest.getUsername(),
                Constants.PASSWORD, loginRequest.getPassword(),
                Constants.SCOPE, Constants.OPEN_ID
        );

        Map<String, String> loginResponseData = new HashMap<>();

        try {
             Map<String, Object> bodyResponse = authClient.login(authData).getBody();
             if (bodyResponse != null) {
                 loginResponseData = Map.of(
                         Constants.ACCESS_TOKEN, bodyResponse.get(Constants.BODY_ACCESS_TOKEN).toString(),
                         Constants.REFRESH_TOKEN, bodyResponse.get(Constants.BODY_REFRESH_TOKEN).toString()
                 );
             }

        } catch (FeignException e) {
            if (e.status() == HttpStatus.BAD_REQUEST.value()) {
                throw new IncorrectCredentialsException("Usuario o Contraseña Incorrectos");
            } else {
                throw new FailedKeycloakOperationException(e.getMessage());
            }
        }

        return loginResponseData;
    }

    @Override
    public void logout(String refreshToken) {
        Map<String, String> logoutData = Map.of(
                Constants.CLIENT_ID, clientId,
                Constants.CLIENT_SECRET, clientSecret,
                Constants.BODY_REFRESH_TOKEN, refreshToken
        );

        try {
            authClient.logout(logoutData);
        } catch (Exception e) {
            throw new FailedKeycloakOperationException(e.getMessage());
        }
    }

    @Override
    public Map<String, Object> validateToken(String token) {
        Map<String, String> validationData = Map.of(
                Constants.CLIENT_ID, clientId,
                Constants.CLIENT_SECRET, clientSecret,
                Constants.BODY_TOKEN, token
        );

        Map<String, Object> validationResponseData = new HashMap<>();

        try {
            Map<String, Object> bodyResponse = authClient.introspect(validationData).getBody();
            if (bodyResponse != null) {
                validationResponseData.put(Constants.ACTIVE, bodyResponse.get(Constants.ACTIVE));
                validationResponseData.put(Constants.SUB, bodyResponse.get(Constants.SUB));
            }
        } catch (Exception e) {
            throw new FailedKeycloakOperationException(e.getMessage());
        }

        return validationResponseData;
    }

    @Override
    public Map<String, String> refresh(String refreshToken) {
        Map<String, String> refreshData = Map.of(
                Constants.GRANT_TYPE, Constants.BODY_REFRESH_TOKEN,
                Constants.CLIENT_ID, clientId,
                Constants.CLIENT_SECRET, clientSecret,
                Constants.BODY_REFRESH_TOKEN, refreshToken
        );

        Map<String, String> refreshResponseData = new HashMap<>();

        try {
            Map<String, Object> bodyResponse = authClient.login(refreshData).getBody();
            if (bodyResponse != null) {
                refreshResponseData = Map.of(
                        Constants.ACCESS_TOKEN, bodyResponse.get(Constants.BODY_ACCESS_TOKEN).toString()
                );
            }

        } catch (Exception e) {
            throw new FailedKeycloakOperationException(e.getMessage());
        }

        return refreshResponseData;
    }

}
