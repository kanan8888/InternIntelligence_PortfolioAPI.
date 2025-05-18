package az.portfolioapi.mapper;

import az.portfolioapi.dto.User.UserRequest;
import az.portfolioapi.dto.User.UserResponse;
import az.portfolioapi.dto.auth.request.RegisterRequest;
import az.portfolioapi.dto.auth.response.RegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AuthMapper {

    UserRequest registerRequestToUserRequest(RegisterRequest request);

    RegisterResponse userResponseToRegisterResponse(UserResponse userResponse);
}
