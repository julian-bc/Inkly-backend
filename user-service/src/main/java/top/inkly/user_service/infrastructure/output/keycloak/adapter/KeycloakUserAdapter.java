package top.inkly.user_service.infrastructure.output.keycloak.adapter;

import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;
import top.inkly.user_service.domain.exceptions.keycloak.FailedKeycloakOperationException;
import top.inkly.user_service.domain.exceptions.keycloak.NotFoundKeycloakRoleException;
import top.inkly.user_service.domain.exceptions.keycloak.NotFoundKeycloakUserException;
import top.inkly.user_service.domain.models.UserModel;
import top.inkly.user_service.domain.models.enums.RoleNames;
import top.inkly.user_service.domain.ports.output.keycloak.KeycloakConnectorPort;
import top.inkly.user_service.infrastructure.output.keycloak.mapper.KeycloakMapperInfra;
import top.inkly.user_service.infrastructure.output.keycloak.repository.KeycloakUserRepository;

import java.util.Collections;

@RequiredArgsConstructor
@Component
public class KeycloakUserAdapter implements KeycloakConnectorPort {

    private final KeycloakUserRepository keycloakRepository;
    private final KeycloakMapperInfra mapper;

    @Override
    public String saveKeycloakUser(UserModel user) throws FailedKeycloakOperationException, NotFoundKeycloakRoleException {
        UserRepresentation userToKeycloak;
        userToKeycloak = mapper.toRepresentation(user);
        userToKeycloak.setEnabled(true);
        assignCredentials(userToKeycloak, user.getPassword());

        String userId;
        try {
            userId = keycloakRepository.save(userToKeycloak);
        } catch(RuntimeException e) {
            throw new FailedKeycloakOperationException("Error Creando Usuario en Keycloak: " + e.getMessage());
        }

        try {
            RoleRepresentation inklyUserRole;
            try {
                inklyUserRole = keycloakRepository.getRoleByName(RoleNames.INKLY_USER.name());
            } catch (RuntimeException e) {
                throw new NotFoundKeycloakRoleException("Error Consultando Rol en Keycloak: " + e.getMessage());
            }

            keycloakRepository.assignRole(
                    inklyUserRole,
                    userId
            );
        } catch(RuntimeException e) {
            keycloakRepository.delete(userId);
            throw new FailedKeycloakOperationException("Error Asignando Roles en Keycloak: " + e.getMessage());
        }

        return userId;
    }

    @Override
    public void updateKeycloakUser(String userId, UserModel user) throws FailedKeycloakOperationException {
        try {
            UserRepresentation userToUpdateKeycloak = keycloakRepository.getById(userId);
            userToUpdateKeycloak = mapper.toRepresentation(user);
            keycloakRepository.update(userId, userToUpdateKeycloak);
        } catch(RuntimeException e) {
            throw new FailedKeycloakOperationException("Error Actualizando Usuario en Keycloak: " + e.getMessage());
        }

    }

    @Override
    public UserModel getKeycloakUserById(String userId) throws FailedKeycloakOperationException, NotFoundKeycloakUserException {
        UserRepresentation userFromKeycloak = keycloakRepository.getById(userId);
        if (userFromKeycloak == null) {
            throw new NotFoundKeycloakUserException(userId);
        }
        return mapper.toModel(userFromKeycloak);
    }

    @Override
    public UserModel getKeycloakUserByUsername(String username) throws FailedKeycloakOperationException, NotFoundKeycloakUserException {
        UserRepresentation userFromKeycloak = keycloakRepository.getByUsername(username);
        if (userFromKeycloak == null) {
            throw new NotFoundKeycloakUserException(username);
        }
        return mapper.toModel(userFromKeycloak);
    }

    @Override
    public UserModel getKeycloakUserByEmail(String email) throws FailedKeycloakOperationException, NotFoundKeycloakUserException {
        UserRepresentation userFromKeycloak = keycloakRepository.getByEmail(email);
        if (userFromKeycloak == null) {
            throw new NotFoundKeycloakUserException(email);
        }
        return mapper.toModel(userFromKeycloak);
    }

    @Override
    public void changeKeycloakUserStatus(String userId) throws FailedKeycloakOperationException {
        try {
            UserRepresentation userToKeycloak = keycloakRepository.getById(userId);
            userToKeycloak.setEnabled(!userToKeycloak.isEnabled());
            keycloakRepository.update(userId, userToKeycloak);
        } catch(RuntimeException e) {
            throw new FailedKeycloakOperationException("Error Cambiando Estado de Usuario en Keycloak: " + e.getMessage());
        }
    }

    @Override
    public void rollbackKeycloakUserCreation(String userId) throws FailedKeycloakOperationException {
        try {
            keycloakRepository.delete(userId);
        } catch (RuntimeException e) {
            throw new FailedKeycloakOperationException(e.getMessage());
        }
    }

    private void assignCredentials(UserRepresentation user, String password) {
        user.setCredentials(Collections.emptyList());

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(false);

        user.setCredentials(Collections.singletonList(credential));
    }
}
