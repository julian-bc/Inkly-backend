package top.inkly.user_service.domain.ports.output.queues;

import top.inkly.shared.infrastructure.input.dto.notification.EmailNotificationRequestDTO;
import top.inkly.shared.infrastructure.input.dto.notification.NotificationRequestDTO;

public interface NotificationPublisherPort {

    void publishNotificationMessage(NotificationRequestDTO<EmailNotificationRequestDTO> requestDTO);

}
