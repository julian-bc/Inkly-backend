package top.inkly.notification_service.domain.models.union;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class InAppNotificationModel implements NotificationData {

    private String notificationId;
    private String userId;
    private String notificationContent;
    private boolean shown;

    @DateTimeFormat(pattern = "yyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime createdAt;
    @DateTimeFormat(pattern = "yyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime shownAt;

}
