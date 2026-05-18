package top.inkly.notification_service.infrastructure.output.persistence.mapper;

import org.mapstruct.Mapper;
import top.inkly.notification_service.domain.models.EmailTemplateModel;
import top.inkly.notification_service.domain.models.union.InAppNotificationModel;
import top.inkly.notification_service.infrastructure.output.persistence.entity.EmailTemplateEntity;
import top.inkly.notification_service.infrastructure.output.persistence.entity.SentNotificationEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationPersistenceMapper {

    EmailTemplateEntity toEntity(EmailTemplateModel emailTemplateModel);
    SentNotificationEntity toEntity(InAppNotificationModel inAppNotificationModel);

    EmailTemplateModel toModel(EmailTemplateEntity emailTemplateEntity);
    InAppNotificationModel toModel(SentNotificationEntity sentNotificationEntity);

    List<EmailTemplateModel> toModel(List<EmailTemplateEntity> emailTemplateEntities);

}
