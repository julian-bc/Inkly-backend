package top.inkly.user_service.application.services.utils;

import top.inkly.shared.infrastructure.input.dto.notification.EmailNotificationRequestDTO;
import top.inkly.user_service.domain.models.UserModel;

import java.util.Map;

public class UserNotificationsBuilder {

    public static EmailNotificationRequestDTO buildWelcomeNotification(UserModel user) {
        EmailNotificationRequestDTO notificationRequestDTO = new EmailNotificationRequestDTO();
        notificationRequestDTO.setEmailReceiver(user.getEmail());
        notificationRequestDTO.setNotificationTemplateId("WELCOME");
        notificationRequestDTO.setDataValues(
                Map.of("username", user.getUserName())
        );
        return notificationRequestDTO;
    }

}
