package top.inkly.user_service.infrastructure.restapi.mapper;

import org.mapstruct.Mapper;
import top.inkly.shared.domain.PageResponse;
import top.inkly.user_service.domain.models.User;
import top.inkly.user_service.infrastructure.restapi.dtos.CreateUser;
import top.inkly.user_service.infrastructure.restapi.dtos.PatchUser;
import top.inkly.user_service.infrastructure.restapi.dtos.UserResponse;

@Mapper(componentModel = "spring")
public interface UserRestMapper {
    PageResponse<UserResponse> toUserResponse(PageResponse<User> userPageResponse);
    UserResponse toUserResponse(User user);
    User toDomain(CreateUser createUser);
    User toDomain(PatchUser patchUser);
}
