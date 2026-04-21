package top.inkly.notification_service.infrastructure.input.mapper;

import org.mapstruct.Mapper;
import top.inkly.notification_service.domain.exceptions.NotFoundMapperException;
import top.inkly.notification_service.domain.models.EmailNotificationModel;
import top.inkly.notification_service.domain.models.InAppNotificationModel;
import top.inkly.notification_service.domain.models.NotificationData;
import top.inkly.notification_service.domain.models.NotificationModel;
import top.inkly.notification_service.infrastructure.input.dto.NotificationRequestDTO;
import top.inkly.notification_service.infrastructure.input.dto.union.EmailNotificationRequestDTO;
import top.inkly.notification_service.infrastructure.input.dto.union.InAppNotificationRequestDTO;
import top.inkly.notification_service.infrastructure.input.dto.union.base.NotificationDataDTO;

@Mapper(componentModel = "spring")
public interface NotificationInputMapper {

    NotificationModel toModel(NotificationRequestDTO notificationDTO);

    EmailNotificationModel toEmailModel(EmailNotificationRequestDTO notificationDTO);
    InAppNotificationModel toInAppModel(InAppNotificationRequestDTO notificationDTO);

    default NotificationData map(NotificationDataDTO  notificationDataDTO) {
        if (notificationDataDTO instanceof EmailNotificationRequestDTO email) {
            return toEmailModel(email);
        }
        if (notificationDataDTO instanceof InAppNotificationRequestDTO inApp) {
            return toInAppModel(inApp);
        }
        throw new NotFoundMapperException(notificationDataDTO.getClass().getSimpleName());
    }

}
