package top.inkly.notification_service.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.inkly.notification_service.domain.models.union.NotificationData;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationModel {

    private String notificationType;
    private NotificationData notificationData;

}
