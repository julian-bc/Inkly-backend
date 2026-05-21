package top.inkly.verification_service.infrastructure.input.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import top.inkly.verification_service.domain.models.VerificationModel;
import top.inkly.verification_service.infrastructure.input.rest.dtos.VerificationRequest;

@Tag(name = "Verificación", description = "Operaciones relacionadas con códigos de verificación")
public interface IVerificationRestController {
    @Operation(summary = "Crear un nuevo código de verificación", description = "Genera y almacena un código de verificación asociado a un usuario.")
    void createVerificationCode(VerificationRequest request);

    @Operation(summary = "Verificar un código de verificación", description = "Valida si el código ingresado es correcto y está vigente.")
    void verifyCode(VerificationRequest request);
}
