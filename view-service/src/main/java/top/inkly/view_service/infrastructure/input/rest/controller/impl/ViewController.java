package top.inkly.view_service.infrastructure.input.rest.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import top.inkly.view_service.application.service.IViewService;
import top.inkly.view_service.domain.models.ViewModel;
import top.inkly.view_service.infrastructure.input.rest.controller.IViewController;


@RestController
@RequestMapping("/views")
@RequiredArgsConstructor
public class ViewController implements IViewController {
    private final IViewService service;

    @PostMapping
    public void counter(@RequestBody ViewModel viewModel) {
        service.count(viewModel);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createView(@RequestBody ViewModel viewModel) {
        service.createView(viewModel);
    }

    @DeleteMapping("/{bookId}")
    public void deleteView(@PathVariable String bookId) {
        service.deleteView(bookId);
    }
}
