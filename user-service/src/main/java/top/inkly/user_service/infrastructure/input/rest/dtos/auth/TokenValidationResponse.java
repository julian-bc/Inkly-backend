package top.inkly.user_service.infrastructure.input.rest.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenValidationResponse {

    private boolean active;

}
