package top.inkly.verification_service.infrastructure.input.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.inkly.shared.domain.models.verification.VerificationType;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerificationRequest {
    private String userNameOrEmail;
    private VerificationType verificationType;
    private String code;
}
