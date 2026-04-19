package top.inkly.user_service.infrastructure.output.database.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    private UUID userId;
    private String userName;
    private String email;
    private String password;
    private Boolean emailVerified;
    private Boolean passwordVerified;
    private Boolean enable;
    @ManyToOne()
    @JoinColumn(name = "role_id")
    private RoleEntity role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
