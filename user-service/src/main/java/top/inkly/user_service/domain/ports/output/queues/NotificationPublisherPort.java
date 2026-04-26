package top.inkly.user_service.domain.ports.output.queues;

import top.inkly.shared.infrastructure.input.dto.notification.EmailNotificationRequestDTO;

public interface NotificationPublisherPort {

    void publishNotificationMessage(EmailNotificationRequestDTO requestDTO);

}
