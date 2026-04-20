package top.inkly.user_service.domain.ports.output.keycloak;

import top.inkly.user_service.domain.exceptions.keycloak.FailedKeycloakOperationException;
import top.inkly.user_service.domain.exceptions.keycloak.NotFoundKeycloakRoleException;
import top.inkly.user_service.domain.exceptions.keycloak.NotFoundKeycloakUserException;
import top.inkly.user_service.domain.models.UserModel;

public interface KeycloakConnectorPort {

    String saveKeycloakUser(UserModel user) throws FailedKeycloakOperationException, NotFoundKeycloakRoleException;

    void updateKeycloakUser(String userId, UserModel user) throws FailedKeycloakOperationException;

    UserModel getKeycloakUserById(String userId) throws FailedKeycloakOperationException, NotFoundKeycloakUserException;

    UserModel getKeycloakUserByUsername(String username) throws FailedKeycloakOperationException, NotFoundKeycloakUserException;

    UserModel getKeycloakUserByEmail(String email) throws FailedKeycloakOperationException, NotFoundKeycloakUserException;

    void changeKeycloakUserStatus(String userId) throws FailedKeycloakOperationException;

    void rollbackKeycloakUserCreation(String userId) throws FailedKeycloakOperationException;

}
