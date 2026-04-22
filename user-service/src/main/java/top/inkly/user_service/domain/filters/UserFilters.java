package top.inkly.user_service.domain.filters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
@AllArgsConstructor
public class UserFilters {
    private String userName;
    private String email;
    private Boolean enable;
}
