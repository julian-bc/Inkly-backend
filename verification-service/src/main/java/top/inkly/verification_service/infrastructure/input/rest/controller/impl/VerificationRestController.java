package top.inkly.verification_service.infrastructure.input.rest.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import top.inkly.verification_service.application.service.IVerificationService;
import top.inkly.verification_service.domain.models.VerificationModel;
import top.inkly.verification_service.infrastructure.input.rest.controller.IVerificationRestController;

import java.util.UUID;

@RestController
@RequestMapping("/verification-codes")
@RequiredArgsConstructor
public class VerificationRestController implements IVerificationRestController {
    private final IVerificationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createVerificationCode(@RequestBody VerificationModel verificationModel) {
        service.saveVerificationRecord(verificationModel);
    }

    @PostMapping("/verify")
    public void verifyCode(@RequestBody VerificationModel verificationModel) {
        service.verifyCode(verificationModel);
    }

    @GetMapping("/available/{userId}")
    public boolean existsVerifyCodeForgottenPasswordByUserId(@PathVariable UUID userId) {
        return service.existsVerifyCodeForgottenPasswordByUserId(userId);
    }
}
