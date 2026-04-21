package top.inkly.notification_service.infrastructure.input.dto.union;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.inkly.notification_service.infrastructure.input.dto.union.base.NotificationDataDTO;

import java.util.Map;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotificationRequestDTO implements NotificationDataDTO {

    private String emailReceiver;
    private String notificationTemplateId;
    private Map<String, String> dataValues;

}
