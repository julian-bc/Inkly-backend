package top.inkly.shared.infrastructure.input.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationRequestDTO<T> {

    private String notificationType;
    private T notificationData;

}
