package top.inkly.user_service.domain.exceptions.keycloak;

public class FailedKeycloakOperationException extends RuntimeException {
    public FailedKeycloakOperationException(String message) {
        super(message);
    }
}
