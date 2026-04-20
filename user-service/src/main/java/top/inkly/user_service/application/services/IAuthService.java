package top.inkly.user_service.application.services;

import top.inkly.user_service.domain.models.auth.LoginRequestModel;
import top.inkly.user_service.domain.models.auth.LoginResponseModel;
import top.inkly.user_service.domain.models.auth.TokenValidationModel;

import java.util.Map;

public interface IAuthService {

    LoginResponseModel login(LoginRequestModel loginRequest);

    void logout(String refreshToken);

   TokenValidationModel validateToken(String token);

}
