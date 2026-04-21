package top.inkly.notification_service.infrastructure.input.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.inkly.notification_service.domain.ports.input.NotifierUseCases;
import top.inkly.shared.infrastructure.input.dto.notification.NotificationRequestDTO;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationRestController {

    private final NotifierUseCases notificationService;

    @PostMapping
    public void testNotification(@RequestBody NotificationRequestDTO notificationDTO) {

    }


}
