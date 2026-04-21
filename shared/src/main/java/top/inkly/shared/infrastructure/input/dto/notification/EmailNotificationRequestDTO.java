package top.inkly.shared.infrastructure.input.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotificationRequestDTO {

    private String emailReceiver;
    private String notificationTemplateId;
    private Map<String, String> dataValues;

}
