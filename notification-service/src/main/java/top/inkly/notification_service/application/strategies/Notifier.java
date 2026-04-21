package top.inkly.notification_service.application.strategies;

public interface Notifier<T> {

    void sendNotification(T notification);

}
