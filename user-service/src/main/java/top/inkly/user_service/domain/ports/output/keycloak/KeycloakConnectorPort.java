package top.inkly.user_service.domain.ports.output.keycloak;

import top.inkly.user_service.domain.exceptions.business.RoleNotFoundException;
import top.inkly.user_service.domain.exceptions.business.UserNotFoundException;
import top.inkly.user_service.domain.exceptions.keycloak.FailedKeycloakOperationException;
import top.inkly.user_service.domain.models.UserModel;

public interface KeycloakConnectorPort {

    void saveKeycloakUser(UserModel user) throws FailedKeycloakOperationException, RoleNotFoundException;

    void updateKeycloakUser(String userId, UserModel user) throws FailedKeycloakOperationException;

    UserModel getKeycloakUserById(String userId) throws FailedKeycloakOperationException, UserNotFoundException;

    UserModel getKeycloakUserByUsername(String username) throws FailedKeycloakOperationException, UserNotFoundException;

    void changeKeycloakUserStatus(String userId) throws FailedKeycloakOperationException;

    void rollbackKeycloakUserCreation(String userId) throws FailedKeycloakOperationException;

}
