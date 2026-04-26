package top.inkly.user_service.infrastructure.input.rest.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import top.inkly.shared.domain.pagination.PageResponse;
import top.inkly.shared.domain.pagination.PaginationRequest;
import top.inkly.user_service.application.services.IUserService;
import top.inkly.user_service.domain.filters.UserFilters;
import top.inkly.user_service.infrastructure.input.rest.controller.IUserRestController;
import top.inkly.user_service.infrastructure.input.rest.dtos.user.CreateUser;
import top.inkly.user_service.infrastructure.input.rest.dtos.user.PatchUser;
import top.inkly.user_service.infrastructure.input.rest.dtos.user.UserResponse;
import top.inkly.user_service.infrastructure.input.rest.mapper.UserRestMapper;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRestController implements IUserRestController {
    private final IUserService service;
    private final UserRestMapper mapper;

    @GetMapping
    public PageResponse<UserResponse> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Boolean enable
    ) {
        UserFilters filters = UserFilters.builder()
                .userName(userName)
                .email(email)
                .enable(enable)
                .build();
        return mapper.toUserResponse(service.findUsers(new PaginationRequest(page, size), filters));
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable UUID id) {
        return mapper.toUserResponse(service.findUser(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody CreateUser user) {
        service.createUser(mapper.toDomain(user));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable UUID id, @RequestBody PatchUser userUpdated) {
        service.updateUser(id, mapper.toDomain(userUpdated));
    }

    @PatchMapping("/{id}/toggle-status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void toggleUserStatus(@PathVariable UUID id) {
        service.toggleUserStatus(id);
    }
}
