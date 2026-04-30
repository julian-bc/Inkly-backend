package top.inkly.view_service.application.service;

import top.inkly.shared.domain.pagination.PageResponse;
import top.inkly.shared.domain.pagination.PaginationRequest;
import top.inkly.view_service.domain.models.ViewModel;

public interface IViewService {
    PageResponse<ViewModel> findViewsWithNewReaders(PaginationRequest request);
    void count(ViewModel view);
    void createView(ViewModel newView);
    void deleteView(String bookId);
    void updateViewsCounters();
}
