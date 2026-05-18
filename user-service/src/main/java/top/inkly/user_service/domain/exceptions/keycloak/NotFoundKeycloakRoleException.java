package top.inkly.user_service.domain.exceptions.keycloak;

public class NotFoundKeycloakRoleException extends RuntimeException {
    public NotFoundKeycloakRoleException(String message) {
        super(message);
    }
}
