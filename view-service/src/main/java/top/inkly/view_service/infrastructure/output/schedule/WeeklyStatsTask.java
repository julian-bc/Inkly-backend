package top.inkly.view_service.infrastructure.output.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import top.inkly.shared.domain.PageResponse;
import top.inkly.shared.domain.PaginationRequest;
import top.inkly.view_service.application.service.IViewService;
import top.inkly.view_service.domain.models.ViewModel;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class WeeklyStatsTask {
    private final IViewService service;

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
                processEmail(view.preparedData());

                // update readerCounter += newReaders, newReaders = 0
                Long readerCounter = view.getReaderCounter() + view.getNewReaders();
                view.setReaderCounter(readerCounter);
                view.setNewReaders(0L);
            }
        } while (page < pageLimit);

        // update all register, a single write process
        service.updateViewsCounters();
    }

    private void processEmail(Map<String, String> data) {
        // sending email to notification-service
    }
}
