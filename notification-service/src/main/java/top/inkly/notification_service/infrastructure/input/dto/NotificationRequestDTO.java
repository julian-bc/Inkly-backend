package top.inkly.notification_service.infrastructure.input.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.inkly.notification_service.infrastructure.input.dto.union.EmailNotificationRequestDTO;
import top.inkly.notification_service.infrastructure.input.dto.union.InAppNotificationRequestDTO;
import top.inkly.notification_service.infrastructure.input.dto.union.base.NotificationDataDTO;

import static top.inkly.shared.domain.notification.types.NotificationTypes.EMAIL;
import static top.inkly.shared.domain.notification.types.NotificationTypes.IN_APP;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequestDTO {

    private String notificationType;

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
            property = "notificationType"
    )
    @JsonSubTypes({
            @JsonSubTypes.Type(value = EmailNotificationRequestDTO.class, name = EMAIL),
            @JsonSubTypes.Type(value = InAppNotificationRequestDTO.class, name = IN_APP)
    })
    private NotificationDataDTO notificationData;

}
