package az.portfolioapi.service.user;

import az.portfolioapi.dto.User.ResetPasswordRequest;
import az.portfolioapi.dto.User.UserFilterRequest;
import az.portfolioapi.dto.User.UserRequest;
import az.portfolioapi.dto.User.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserResponse createUser(UserRequest request);
    UserResponse updateUser(Long userId, UserRequest request);
    void resetPassword(String username, ResetPasswordRequest request);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    UserResponse getUserById(Long userId);
    Page<UserResponse> getAllUsers(Pageable pageable);
    Page<UserResponse>filterUsers(UserFilterRequest request, Pageable pageable);
    void deleteUser(Long userId);
}
