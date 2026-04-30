package top.inkly.notification_service.application.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.inkly.notification_service.application.strategies.Notifier;
import top.inkly.notification_service.domain.exceptions.NotFoundNotifierException;

import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class NotifierFactory {

    /**
     * Spring Boot's dependency injection will construct this map for us
     * and include all available implementations in them map, with the key
     * as the bean name. <br/>
     * Logically, the map will look something like below:
     * <ul>
     *     <li><b>"IN_APP":</b> ApplicationNotifier</li>
     *     <li><b>"EMAIL":</b> EmailNotifier</li>
     * </ul>
     */
    private final Map<String, Notifier> notifiers;

    public Notifier getNotifier(String notifierName) {
        Notifier notifier = notifiers.get(notifierName);
        if (Objects.isNull(notifier)) {
            throw new NotFoundNotifierException(notifierName);
        }
        return notifier;
    }

}
