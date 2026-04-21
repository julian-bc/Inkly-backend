package top.inkly.shared.infrastructure.input.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class InAppNotificationRequestDTO {

    private String userId;
    private String notificationText;

}
