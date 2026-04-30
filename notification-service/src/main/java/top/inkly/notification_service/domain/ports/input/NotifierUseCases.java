package top.inkly.notification_service.domain.ports.input;


import top.inkly.notification_service.domain.models.NotificationModel;

public interface NotifierUseCases {

    void executeSendNotification(NotificationModel notification);

}
