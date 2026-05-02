package top.inkly.view_service.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.inkly.shared.domain.pagination.PageResponse;
import top.inkly.shared.domain.pagination.PaginationRequest;
import top.inkly.shared.domain.pagination.PaginationResult;
import top.inkly.view_service.application.service.IViewService;
import top.inkly.view_service.domain.exceptions.ViewNotFoundException;
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
    public void count(String bookId) {
        ViewModel viewSaved = repository.findByBookId(bookId);

        if (viewSaved == null) {
            throw new ViewNotFoundException("No se pudo cargar la vista de seguimiento del libro.");
        } else {
            Long oldCounter = viewSaved.getNewReaders();
            viewSaved.setNewReaders(oldCounter + 1);
            repository.save(viewSaved);
        }
    }

    @Override
    public void createView(ViewModel newView) {
        newView.setViewId(UUID.randomUUID().toString());
        newView.setReaderCounter(0L);
        newView.setNewReaders(0L);
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
