package top.inkly.notification_service.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class InAppNotificationModel {

    private String userId;
    private String notificationText;
    private boolean shown;

    private LocalDateTime createdAt;
    private LocalDateTime shownAt;

}
