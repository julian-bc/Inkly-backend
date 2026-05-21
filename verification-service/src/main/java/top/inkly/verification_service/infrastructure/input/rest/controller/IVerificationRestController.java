package top.inkly.verification_service.infrastructure.input.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import top.inkly.shared.domain.models.verification.VerificationType;
import top.inkly.verification_service.infrastructure.input.rest.dtos.VerificationRequest;

import java.util.UUID;

@Tag(name = "Verificación", description = "Operaciones relacionadas con códigos de verificación")
public interface IVerificationRestController {
    @Operation(summary = "Crear un nuevo código de verificación", description = "Genera y almacena un código de verificación asociado a un usuario.")
    void createVerificationCode(VerificationRequest request);

    @Operation(summary = "Verificar un código de verificación", description = "Valida si el código ingresado es correcto y está vigente.")
    void verifyCode(VerificationRequest request);

    @Operation(summary = "Validar si existe", description = "Valida si existe un codigo verificado con el userId y verificationType proporcionado.")
    boolean existsVerifyCodeByUserIdAndVerificationType(UUID userId, VerificationType verificationType);
}
