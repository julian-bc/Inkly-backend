package top.inkly.notification_service.infrastructure.input.dto.union;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.inkly.notification_service.infrastructure.input.dto.union.base.NotificationDataDTO;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class InAppNotificationRequestDTO implements NotificationDataDTO {

    private String userId;
    private String notificationText;

}
