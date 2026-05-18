package top.inkly.user_service.infrastructure.input.rest.controller.impl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.inkly.user_service.application.services.IAuthService;
import top.inkly.user_service.domain.models.auth.LoginRequestModel;
import top.inkly.user_service.domain.models.auth.LoginResponseModel;
import top.inkly.user_service.domain.models.auth.SessionResponseModel;
import top.inkly.user_service.domain.models.auth.TokenValidationModel;
import top.inkly.user_service.domain.shared.Constants;
import top.inkly.user_service.infrastructure.input.rest.dtos.auth.LoginRequest;
import top.inkly.user_service.infrastructure.input.rest.dtos.auth.LoginResponse;
import top.inkly.user_service.infrastructure.input.rest.dtos.auth.TokenValidationResponse;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final IAuthService authService;

    @Value("${environment}")
    private String environment;

    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        LoginResponseModel authResponse = authService.login(
                new LoginRequestModel(loginRequest.getUsername(), loginRequest.getPassword())
        );

        response.addHeader(HttpHeaders.SET_COOKIE,
                buildCookie(Constants.BODY_ACCESS_TOKEN, authResponse.getAccessToken(),
                        Constants.COOKIE_ACCESS_TOKEN_DURATION * 60)
        );

        response.addHeader(HttpHeaders.SET_COOKIE,
                buildCookie(Constants.BODY_REFRESH_TOKEN, authResponse.getRefreshToken(),
                        Constants.COOKIE_REFRESH_TOKEN_DURATION * 24 * 3600)
        );

        return ResponseEntity.ok(new LoginResponse(authResponse.getUserId()));
    }

    @PostMapping("/logout")
    ResponseEntity<Void> logout(
            @CookieValue(name = Constants.BODY_REFRESH_TOKEN, required = false) String refreshToken,
            HttpServletResponse response) {
        if (refreshToken != null) {
            authService.logout(refreshToken);

        }

        response.addHeader(HttpHeaders.SET_COOKIE,
                buildCookie(Constants.BODY_ACCESS_TOKEN, Constants.BLANK, Constants.ZERO)
        );

        response.addHeader(HttpHeaders.SET_COOKIE,
                buildCookie(Constants.BODY_REFRESH_TOKEN, Constants.BLANK, Constants.ZERO)
        );

        return ResponseEntity.ok().build();
    }

    @PostMapping("/validate/access")
    ResponseEntity<TokenValidationResponse> validateAccess(
            @CookieValue(name = Constants.BODY_ACCESS_TOKEN, required = false) String accessToken
            ) {
        TokenValidationResponse tokenValidation = new TokenValidationResponse();

        if (accessToken != null) {
            TokenValidationModel validationResponse = authService.validateToken(accessToken);
            tokenValidation.setActive(validationResponse.isActive());
            tokenValidation.setUserId(validationResponse.getUserId());
        } else {
            tokenValidation.setActive(false);
        }

        return ResponseEntity.ok().body(tokenValidation);
    }

    @PostMapping("/validate/session")
    ResponseEntity<TokenValidationResponse> validateSession(
            @CookieValue(name = Constants.BODY_REFRESH_TOKEN, required = false) String refreshToken
    ) {
        TokenValidationResponse tokenValidation = new TokenValidationResponse();

        if (refreshToken != null) {
            TokenValidationModel validationResponse = authService.validateToken(refreshToken);
            tokenValidation.setActive(validationResponse.isActive());
            tokenValidation.setUserId(validationResponse.getUserId());
        } else {
            tokenValidation.setActive(false);
        }

        return ResponseEntity.ok().body(tokenValidation);
    }

    @PostMapping("/refresh-session")
    ResponseEntity<Void> refreshSession(
            @CookieValue(name = Constants.BODY_REFRESH_TOKEN, required = false) String refreshToken,
            HttpServletResponse response
    ) {
        SessionResponseModel refreshResponse = authService.refreshSession(refreshToken);

        response.addHeader(HttpHeaders.SET_COOKIE,
                buildCookie(Constants.BODY_ACCESS_TOKEN, refreshResponse.getAccessToken(),
                        Constants.COOKIE_ACCESS_TOKEN_DURATION * 60)
        );

        return ResponseEntity.ok().build();
    }

    private String buildCookie(String cookieName, String cookieValue, Integer cookieTime) {
        return ResponseCookie.from(cookieName, cookieValue)
                .httpOnly(Constants.HTTP_ONLY)
                .sameSite(
                        (environment.equalsIgnoreCase(Constants.DEV) || environment.equalsIgnoreCase(Constants.INT)) ? Constants.SAME_SITE_DEV :
                                (environment.equalsIgnoreCase(Constants.AUS) || environment.equalsIgnoreCase(Constants.PROD)) ? Constants.NONE_SITE_PROD : Constants.BLANK
                )
                .secure(
                        (environment.equalsIgnoreCase(Constants.DEV) || environment.equalsIgnoreCase(Constants.INT)) ? Constants.COOKIE_SECURE_DEV :
                                (environment.equalsIgnoreCase(Constants.AUS) || environment.equalsIgnoreCase(Constants.PROD)) && Constants.COOKIE_SECURE_DEV
                )
                .path(Constants.COOKIE_PATH)
                .maxAge(cookieTime)
                .build().toString();
    }

}
