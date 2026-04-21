package top.inkly.notification_service.domain.ports.input;


import top.inkly.notification_service.domain.models.NotificationModel;

public interface NotifierUseCases<T> {

    void executeSendNotification(NotificationModel<T> notification);

}
