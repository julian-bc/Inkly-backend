package top.inkly.verification_service.infrastructure.input.rest.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import top.inkly.shared.domain.models.verification.VerificationType;
import top.inkly.verification_service.application.service.IVerificationService;
import top.inkly.verification_service.infrastructure.input.rest.controller.IVerificationRestController;
import top.inkly.verification_service.infrastructure.input.rest.dtos.VerificationRequest;

import java.util.UUID;

@RestController
@RequestMapping("/verification-codes")
@RequiredArgsConstructor
public class VerificationRestController implements IVerificationRestController {
    private final IVerificationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createVerificationCode(@RequestBody VerificationRequest request) {
        service.saveVerificationRecord(request.getUserNameOrEmail(), request.getVerificationType());
    }

    @PostMapping("/verify")
    public void verifyCode(@RequestBody VerificationRequest request) {
        service.verifyCode(request.getUserNameOrEmail(), request.getVerificationType(), request.getCode());
    }

    @GetMapping("/available/{userId}")
    public boolean existsVerifyCodeByUserIdAndVerificationType(@PathVariable UUID userId, @RequestParam VerificationType verificationType) {
        return service.existsVerifyCodeByUserIdAndVerificationType(userId, verificationType);
    }
}
