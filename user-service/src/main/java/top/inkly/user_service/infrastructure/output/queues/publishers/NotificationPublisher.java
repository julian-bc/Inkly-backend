package top.inkly.user_service.infrastructure.output.queues.publishers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import top.inkly.shared.infrastructure.input.dto.notification.EmailNotificationRequestDTO;
import top.inkly.user_service.domain.ports.output.queues.NotificationPublisherPort;
import top.inkly.user_service.infrastructure.output.queues.config.RabbitMQConfig;

@Component
public class NotificationPublisher implements NotificationPublisherPort {

    RabbitTemplate rabbitTemplate;

    @Override
    public void publishNotificationMessage(EmailNotificationRequestDTO requestDTO) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.NOTIFICATION_QUEUE, requestDTO);
    }

}
