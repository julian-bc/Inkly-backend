package top.inkly.verification_service.infrastructure.output.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.inkly.verification_service.domain.models.enums.VerificationType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "verifications")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID verificationId;
    private UUID userId;
    private String code;
    private Integer attempts;
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;
    private VerificationType verificationType;
}
