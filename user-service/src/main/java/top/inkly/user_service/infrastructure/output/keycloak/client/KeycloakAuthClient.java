package top.inkly.user_service.infrastructure.output.keycloak.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient(name = "keycloak-auth", url = "${keycloak.server.url}")
public interface KeycloakAuthClient {

    @PostMapping(value = "${keycloak.auth.url.login}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<Map<String, Object>> login(Map<String, ?> loginData);

    @PostMapping(value = "${keycloak.auth.url.logout}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    void logout(Map<String, ?> logoutData);

    @PostMapping(value = "${keycloak.auth.url.introspection}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<Map<String, Object>> introspect(Map<String, ?> introspectionData);

    @PostMapping(value = "${keycloak.auth.url.login}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<Map<String, Object>> refresh(Map<String, ?> refreshData);

}
