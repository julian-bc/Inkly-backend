package top.inkly.notification_service.domain.ports.output;

import top.inkly.notification_service.domain.models.union.InAppNotificationModel;

import java.util.List;

public interface SentNotificationRepositoryPort {

    void saveSentNotification(InAppNotificationModel notification);

    List<InAppNotificationModel> findNotificationByUserId(String userId);

    Integer countByShownState(boolean shown, String userId);

}
