package top.inkly.notification_service.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotificationModel {

    private String emailReceiver;
    private String notificationTemplateId;
    private Map<String, String> dataValues;

}
