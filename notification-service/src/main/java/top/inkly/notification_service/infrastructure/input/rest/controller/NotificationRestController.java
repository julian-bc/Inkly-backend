package top.inkly.notification_service.infrastructure.input.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.inkly.notification_service.domain.ports.input.NotifierUseCases;
import top.inkly.notification_service.infrastructure.input.dto.NotificationRequestDTO;
import top.inkly.notification_service.infrastructure.input.mapper.NotificationInputMapper;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationRestController {

    private final NotifierUseCases notificationService;
    private final NotificationInputMapper mapper;

    @PostMapping
    public ResponseEntity<Void> testNotification(@RequestBody NotificationRequestDTO notificationDTO) {
        notificationService.executeSendNotification(mapper.toModel(notificationDTO));
        return ResponseEntity.ok().build();
    }


}
