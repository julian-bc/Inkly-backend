package top.inkly.user_service.application.services;

import top.inkly.user_service.domain.models.auth.LoginRequestModel;
import top.inkly.user_service.domain.models.auth.LoginResponseModel;

import java.util.Map;

public interface IAuthService {

    LoginResponseModel login(LoginRequestModel loginRequest);

    void logout(String refreshToken);

    Map<String, String> tokenValidate(String accessToken);

}
