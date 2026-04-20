package top.inkly.user_service.infrastructure.input.rest.controller.impl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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
import top.inkly.user_service.domain.shared.Constants;
import top.inkly.user_service.infrastructure.input.rest.dtos.auth.LoginRequest;
import top.inkly.user_service.infrastructure.input.rest.dtos.auth.LoginResponse;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final IAuthService authService;

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
            @CookieValue(name = Constants.BODY_REFRESH_TOKEN) String refreshToken,
            HttpServletResponse response) {
        authService.logout(refreshToken);

        //response.addHeader(HttpHeaders.SET_COOKIE,
        //        buildCookie(Constants.BODY_ACCESS_TOKEN, Constants.BLANK, Constants.ZERO)
        //);

        //response.addHeader(HttpHeaders.SET_COOKIE,
        //        buildCookie(Constants.BODY_REFRESH_TOKEN, Constants.BLANK, Constants.ZERO)
        //);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/validate/access")
    ResponseEntity<Void> validateAccess(
            @CookieValue(name = Constants.BODY_ACCESS_TOKEN) String accessToken
            ) {
        authService.validateToken(accessToken);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/validate/session")
    ResponseEntity<Void> validateSession(
            @CookieValue(name = Constants.BODY_REFRESH_TOKEN) String refreshToken
    ) {
        authService.validateToken(refreshToken);

        return ResponseEntity.ok().build();
    }

    private String buildCookie(String cookieName, String cookieValue, Integer cookieTime) {
        return ResponseCookie.from(cookieName, cookieValue)
                .httpOnly(Constants.HTTP_ONLY)
                .sameSite(Constants.SAME_SITE_DEV)
                .secure(Constants.COOKIE_SECURE_DEV)
                .path(Constants.COOKIE_PATH)
                .maxAge(cookieTime)
                .build().toString();
    }

}
