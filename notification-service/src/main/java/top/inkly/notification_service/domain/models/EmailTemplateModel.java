package top.inkly.notification_service.domain.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class EmailTemplateModel {

    private String notificationTemplateId;
    private String templateName;
    private String templateSubject;
    private String templateLocation;

    @DateTimeFormat(pattern = "yyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime createdAt;
    @DateTimeFormat(pattern = "yyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime updatedAt;

}
