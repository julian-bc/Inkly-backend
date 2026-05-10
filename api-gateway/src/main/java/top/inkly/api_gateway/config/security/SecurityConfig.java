package top.inkly.api_gateway.config.security;

import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final KeycloakAuthConverter authConverter;

    @Order(1)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers(
                                "/auth/login",
                                "/auth/validate/access",
                                "/auth/validate/session",
                                "/auth/refresh-session"
                        ).permitAll()
                        .requestMatchers(
                                HttpMethod.POST,
                                "/users",
                                "/verification-codes",
                                "/verification-codes/verify",
                                "/views/**"
                        ).permitAll()
                        .requestMatchers(
                                HttpMethod.GET,
                                "/users",
                                "/users/{id}",
                                "/api/story/search",
                                "/api/genre",
                                "/api/",
                                "/api/chapter/{storyId}",
                                "/api/tag/search"
                        ).permitAll()
                ).build();
    }

    @Order(2)
    @Bean
    public SecurityFilterChain securityFilterChain2(HttpSecurity http) {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers(
                                "/auth/logout"
                        ).authenticated()
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/story/create",
                                "/api/chapter/create",
                                "/api/view/create",
                                "/api/rating/create",
                                "/api/favorite/add"
                        )
                        .authenticated()
                        .requestMatchers(
                                HttpMethod.PATCH,
                                "/users/{id}",
                                "/users/{id}/toggle-status",
                                "/users/{id}/update-image",
                                "/api/story/cover/{id}",
                                "/api/story/toggle-hidden/{id}",
                                "/api/chapter/toggle-hidden/{id}",
                                "/api/chapter/update/{id}"
                        ).authenticated()
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/users/{id}/delete-image",
                                "/api/story/delete/{id}",
                                "/api/story/cover/{id}",
                                "/api/chapter/{storyId}/chapter/{chapterId}",
                                "/api/favorite",
                                "/api/favorite/remove/{userId}/{storyId}"
                        ).authenticated()
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/story/search/{userId}",
                                "/api/story/last-modified/{userId}",
                                "/api/chapter/my-chapters/{storyId}",
                                "/api/favorite/search/{userId}",
                                "/api/tag/search"
                        ).authenticated()
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/story/update/{id}"
                        )
                )
                .oauth2ResourceServer(oauth -> oauth
                        .bearerTokenResolver(cookieAccessTokenResolver())
                        .jwt(jwtConfigurer -> jwtConfigurer
                                .jwtAuthenticationConverter(jwtToken -> {
                                    return new JwtAuthenticationToken(jwtToken, authConverter.convert(jwtToken));
                                })))
                .build();
    }

    private BearerTokenResolver cookieAccessTokenResolver() {
        return request -> {
            List<Cookie> cookies = Arrays.asList(request.getCookies());
            return cookies.stream()
                    .filter(cookie -> cookie.getName().equals("access_token"))
                    .map(Cookie::getValue)
                    .findAny().orElse(null);
        };
    }

}
