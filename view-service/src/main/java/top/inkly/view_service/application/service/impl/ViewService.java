package top.inkly.view_service.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.inkly.shared.domain.pagination.PageResponse;
import top.inkly.shared.domain.pagination.PaginationRequest;
import top.inkly.shared.domain.pagination.PaginationResult;
import top.inkly.view_service.application.service.IViewService;
import top.inkly.view_service.domain.models.ViewModel;
import top.inkly.view_service.domain.repository.ViewRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ViewService implements IViewService {
    private final ViewRepository repository;

    @Override
    public PageResponse<ViewModel> findViewsWithNewReaders(PaginationRequest request) {
        PaginationResult<ViewModel> pagination = repository.findAllWithNewsReaders(request);

        return PageResponse.<ViewModel>builder()
                .data(pagination.getContent())
                .meta(pagination.toMetaData())
                .build();
    }

    @Override
    public void count(ViewModel view) {
        String bookTitle = view.getBook().getTitle();
        ViewModel viewSaved = repository.findByBookTitle(bookTitle);

        if (viewSaved == null) {
            String viewId = UUID.randomUUID().toString();
            view.setViewId(viewId);
            view.setReaderCounter(0L);
            repository.save(view);
        } else {
            Long newsReaders = view.getNewReaders();
            Long oldCounter = viewSaved.getNewReaders();
            Long newReadersCounter = oldCounter + newsReaders;

            viewSaved.setNewReaders(newReadersCounter);
            repository.save(viewSaved);
        }
    }

    @Override
    public void createView(ViewModel newView) {
        repository.save(newView);
    }

    @Override
    public void deleteView(String bookId) {
        repository.deleteByBookId(bookId);
    }

    @Override
    public void updateViewsCounters() {
        repository.updateAllCounters();
    }
}
