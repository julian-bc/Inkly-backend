package top.inkly.view_service.infrastructure.output.event.publishers;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VIewWeeklyNotificationPublisher {
    private final RabbitTemplate rabbitTemplate;
}
