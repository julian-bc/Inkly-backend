package top.inkly.notification_service.infrastructure.input.mapper;

import org.mapstruct.Mapper;
import top.inkly.notification_service.domain.models.NotificationModel;
import top.inkly.shared.infrastructure.input.dto.notification.NotificationRequestDTO;

@Mapper(componentModel = "spring")
public interface NotificationRestMapper {

    NotificationModel toModel(NotificationRequestDTO notificationDTO);

}
