package top.inkly.api_gateway.config.security;

import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final KeycloakAuthConverter authConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers(
                                "/actuator/health",
                                "/auth/login",
                                "/auth/validate/access",
                                "/auth/validate/session",
                                "/auth/refresh-session",
                                "/auth/logout"
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
                                "/api/story/id/{storyId}",
                                "/api/genre",
                                "/api/chapter/{storyId}",
                                "/api/tag/search",
                                "/verification-codes/available/{userId}"
                        ).permitAll()
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/story/create",
                                "/api/chapter/create",
                                "/api/view/create",
                                "/api/rating/create",
                                "/api/favorite/add",
                                "/api/comment/create"
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
                                "/api/chapter/update/{id}",
                                "/api/comment/update/{commentId}",
                                "/api/comment/toggle-like/{commentId}/{userId}"
                        ).authenticated()
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/users/{id}/delete-image",
                                "/api/story/delete/{id}",
                                "/api/story/cover/{id}",
                                "/api/chapter/{storyId}/chapter/{chapterId}",
                                "/api/favorite",
                                "/api/favorite/remove/{userId}/{storyId}",
                                "/api/comment/{commentId}"
                        ).authenticated()
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/story/search/{userId}",
                                "/api/story/last-modified/{userId}",
                                "/api/chapter/my-chapters/{storyId}",
                                "/api/favorite/search/{userId}",
                                "/api/tag/search",
                                "/api/comment/search/story/{storyId}",
                                "/api/comment/search/chapter/{chapterId}"
                        ).authenticated()
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/story/update/{id}"
                        ).authenticated()
                        // Secure by Role Endpoints
                        .requestMatchers(
                                "/templates/**"
                        ).hasRole("INKLY_ADMIN")
                        .requestMatchers(
                                HttpMethod.POST,
                                "/notifications"
                        ).hasRole("INKLY_ADMIN")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth -> oauth
                        .bearerTokenResolver(cookieAccessTokenResolver())
                        .jwt(jwtConfigurer -> jwtConfigurer
                                .jwtAuthenticationConverter(jwtToken -> new JwtAuthenticationToken(jwtToken, authConverter.convert(jwtToken))))
                )
                .build();
    }

    private BearerTokenResolver cookieAccessTokenResolver() {
        return request -> {
            Cookie[] cookies = request.getCookies();
            if (cookies == null) return null;

            return Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals("access_token"))
                    .map(Cookie::getValue)
                    .findAny().orElse(null);
        };
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
