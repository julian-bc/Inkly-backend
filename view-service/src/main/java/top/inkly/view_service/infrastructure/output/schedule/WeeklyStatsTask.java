package top.inkly.view_service.infrastructure.output.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import top.inkly.shared.domain.notification.types.NotificationTypes;
import top.inkly.shared.domain.pagination.PageResponse;
import top.inkly.shared.domain.pagination.PaginationRequest;
import top.inkly.shared.infrastructure.input.dto.notification.EmailNotificationRequestDTO;
import top.inkly.shared.infrastructure.input.dto.notification.NotificationRequestDTO;
import top.inkly.view_service.application.service.IViewService;
import top.inkly.view_service.domain.models.ViewModel;
import top.inkly.view_service.domain.ports.output.queues.NotificationPublisherPort;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class WeeklyStatsTask {
    private final IViewService service;
    private final NotificationPublisherPort notificationPublisher;

    @Scheduled(cron = "0 30 22 * * MON")
    public void processSendingMassEmails() {
        int batchSize = 400;
        int page = 0;
        int pageLimit;

        do {
            PageResponse<ViewModel> pageResponse = service
                    .findViewsWithNewReaders(new PaginationRequest(page, batchSize));
            List<ViewModel> views = pageResponse.getData();
            pageLimit = pageResponse.getMeta().getTotalPages();

            for (ViewModel view : views) {
                // send email
                processEmail(view.preparedData(), view.getEmailReceiver());

                // update readerCounter += newReaders, newReaders = 0
                Long readerCounter = view.getReaderCounter() + view.getNewReaders();
                view.setReaderCounter(readerCounter);
                view.setNewReaders(0L);
            }
        } while (page < pageLimit);

        // update all register, a single write process
        service.updateViewsCounters();
    }

    private void processEmail(Map<String, String> data, String emailReceiver) {
        NotificationRequestDTO<EmailNotificationRequestDTO> request = NotificationRequestDTO.<EmailNotificationRequestDTO>builder()
                .notificationType(NotificationTypes.EMAIL)
                .notificationData(
                        EmailNotificationRequestDTO.builder()
                                .emailReceiver(emailReceiver)
                                .notificationTemplateId("WEEKLY_UPDATE")
                                .dataValues(data)
                                .build()
                )
                .build();

        // sending email to notification-service rabbitmq
        notificationPublisher.publishNotificationMessage(request);
    }
}
