package top.inkly.view_service.infrastructure.output.event.publishers;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import top.inkly.shared.infrastructure.input.dto.notification.EmailNotificationRequestDTO;
import top.inkly.shared.infrastructure.input.dto.notification.NotificationRequestDTO;
import top.inkly.shared.infrastructure.output.queues.config.RabbitMQConfig;
import top.inkly.view_service.domain.ports.output.queues.NotificationPublisherPort;

@Component
@RequiredArgsConstructor
public class ViewWeeklyNotificationPublisher implements NotificationPublisherPort {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publishNotificationMessage(NotificationRequestDTO<EmailNotificationRequestDTO> requestDTO) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.NOTIFICATION_QUEUE, requestDTO);
    }
}
