package top.inkly.user_service.domain.exceptions.keycloak;

public class NotFoundKeycloakUserException extends RuntimeException {
    public NotFoundKeycloakUserException(String message) {
        super(message);
    }
}
