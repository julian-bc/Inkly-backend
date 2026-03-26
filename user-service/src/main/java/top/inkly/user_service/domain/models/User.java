package top.inkly.user_service.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private UUID userId;
    private String userName;
    private String email;
    private String password;
    private List<Role> roles = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
