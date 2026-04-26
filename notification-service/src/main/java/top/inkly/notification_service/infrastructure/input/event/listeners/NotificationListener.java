package top.inkly.notification_service.infrastructure.input.event.listeners;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import top.inkly.notification_service.domain.ports.input.NotifierUseCases;
import top.inkly.notification_service.infrastructure.input.dto.NotificationRequestDTO;
import top.inkly.notification_service.infrastructure.input.mapper.NotificationInputMapper;

import static top.inkly.shared.infrastructure.output.queues.config.RabbitMQConfig.NOTIFICATION_QUEUE;

@RequiredArgsConstructor
@Service
public class NotificationListener {

    private final NotifierUseCases notificationService;
    private final NotificationInputMapper mapper;

    @RabbitListener(queues = NOTIFICATION_QUEUE)
    public void listenQueueNotifications(NotificationRequestDTO notificationDTO) {
        notificationService.executeSendNotification(mapper.toModel(notificationDTO));
    }

}
