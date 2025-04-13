package az.portfolioapi.service.user;

import az.portfolioapi.dto.request.UserRequest;
import az.portfolioapi.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(Long id, UserRequest userRequest);
    UserResponse getUserById(Long id);
    List<UserResponse> getAllUsers();
    void deleteUser(Long id);
}
