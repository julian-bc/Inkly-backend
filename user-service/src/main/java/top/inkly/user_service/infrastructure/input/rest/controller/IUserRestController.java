package top.inkly.user_service.infrastructure.input.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import top.inkly.shared.domain.PageResponse;
import top.inkly.user_service.infrastructure.input.rest.dtos.user.CreateUser;
import top.inkly.user_service.infrastructure.input.rest.dtos.user.PatchUser;
import top.inkly.user_service.infrastructure.input.rest.dtos.user.UserResponse;

import java.util.UUID;

@Tag(name = "Usuarios", description = "Operaciones relacionadas con la gestión de usuarios del sistema")
public interface IUserRestController {
    @Operation(summary = "Listar usuarios con paginación", description = "Permite filtrar usuarios por nombre, email y estado.")
    PageResponse<UserResponse> getUsers(
            @Parameter(description = "Número de página (0..N)", example = "0")
            int page,
            @Parameter(description = "Tamaño de la página", example = "10")
            int size,
            @Parameter(description = "Filtrar por nombre de usuario")
            String userName,
            @Parameter(description = "Filtrar por correo electrónico")
            String email,
            @Parameter(description = "Filtrar por estado activo/inactivo")
            Boolean enable);

    @Operation(summary = "Obtener un usuario por ID", description = "Devuelve el perfil detallado del usuario solicitado.")
    UserResponse getUserById(UUID id);

    @Operation(summary = "Crear un nuevo usuario")
    void createUser(CreateUser user);

    @Operation(summary = "Actualizar datos parciales", description = "Permite modificar campos específicos de un usuario existente.")
    void updateUser(UUID id, PatchUser userUpdated);

    @Operation(summary = "Deshabilitar usuario", description = "Cambia el estado del usuario a inactivo.")
    void disableUser(UUID id);
}
