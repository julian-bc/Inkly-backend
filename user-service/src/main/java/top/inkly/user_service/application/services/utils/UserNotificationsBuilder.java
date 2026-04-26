package top.inkly.user_service.application.services.utils;

import top.inkly.shared.infrastructure.input.dto.notification.EmailNotificationRequestDTO;
import top.inkly.shared.infrastructure.input.dto.notification.NotificationRequestDTO;
import top.inkly.user_service.domain.models.UserModel;

import java.util.Map;

public class UserNotificationsBuilder {

    public static NotificationRequestDTO<EmailNotificationRequestDTO> buildWelcomeNotification(UserModel user) {
        NotificationRequestDTO<EmailNotificationRequestDTO> notificationRequestDTO = new NotificationRequestDTO<>();
        EmailNotificationRequestDTO emailRequestDTO = new EmailNotificationRequestDTO();

        emailRequestDTO.setEmailReceiver(user.getEmail());
        emailRequestDTO.setNotificationTemplateId("WELCOME");
        emailRequestDTO.setDataValues(
                Map.of("username", user.getUserName())
        );
        notificationRequestDTO.setNotificationType("EMAIL");
        notificationRequestDTO.setNotificationData(emailRequestDTO);

        return notificationRequestDTO;
    }

}
