package top.inkly.verification_service.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.inkly.verification_service.domain.models.enums.VerificationType;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerificationModel {
    private UUID verificationId;
    private UUID userId;
    private String code;
    private Integer attempts;
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;
    private VerificationType verificationType;

    public void generateCode() {
        SecureRandom random = new SecureRandom();
        int number = random.nextInt(900000) + 100000;
        this.code = String.valueOf(number);
    }

    public void loadDates() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationDate = now.plusMinutes(5);
        this.creationDate = now;
        this.expirationDate = expirationDate;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expirationDate);
    }

    public boolean isValidCode(String code) {
        return this.code.equals(code);
    }
}
