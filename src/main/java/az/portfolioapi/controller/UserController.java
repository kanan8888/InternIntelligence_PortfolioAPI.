package az.portfolioapi.controller;

import az.portfolioapi.dto.User.ResetPasswordRequest;
import az.portfolioapi.dto.User.UserFilterRequest;
import az.portfolioapi.dto.User.UserRequest;
import az.portfolioapi.dto.User.UserResponse;
import az.portfolioapi.entity.enums.DegreeLevel;
import az.portfolioapi.entity.enums.UserRole;
import az.portfolioapi.security.CustomUserDetails;
import az.portfolioapi.service.user.UserService;
import az.portfolioapi.service.user.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/users/create-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> createAdmin(@RequestBody @Valid UserRequest request) {
        return ResponseEntity.ok(
                userService.createUser(request)
        );
    } /**/

    @PutMapping("/users/me")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')") /**/
    public ResponseEntity<UserResponse> updateUser(@AuthenticationPrincipal CustomUserDetails user,
                                                   @RequestBody @Valid UserRequest request) {
        return ResponseEntity.ok(
                userService.updateUser(user.getId(), request)
        );
    } /**/

    @PatchMapping("/users/me/change-password")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<String> resetPassword(@AuthenticationPrincipal CustomUserDetails user,
                                                @RequestBody @Valid ResetPasswordRequest request) {
        userService.resetPassword(user.getUsername(), request);
        return ResponseEntity.ok("Password has been reset successfully.");
    } /**/

    @GetMapping("/users/check-email")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')") /**/
    public ResponseEntity<Boolean> checkEmailExists(@RequestParam String email) {
        return ResponseEntity.ok(
                userService.existsByEmail(email)
        );
    } /**/

    @GetMapping("/users/check-username")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')") /**/
    public ResponseEntity<Boolean> checkUsernameExists(@RequestParam String username) {
        return ResponseEntity.ok(
                userService.existsByUsername(username)
        );
    } /**/

    @GetMapping("/users/me")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<UserResponse> getCurrentUser(@AuthenticationPrincipal CustomUserDetails user) {
        return ResponseEntity.ok(
                userService.getUserById(user.getId())
        );
    }

    @GetMapping("/users/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(
                userService.getUserById(userId)
        );
    }

    @GetMapping("/users/filter")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserResponse>> filterUsers(@Valid @RequestBody UserFilterRequest request, Pageable pageable) {
        return ResponseEntity.ok(
                userService.filterUsers(request, pageable)
        );
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserResponse>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(
                userService.getAllUsers(pageable)
        );
    }

    @DeleteMapping("/users/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}

/*

    @PostMapping("/users-forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        userService.sendPasswordResetLink(email);
        return ResponseEntity.ok("Password reset link sent to your email.");
    }


@PatchMapping("/users/{userId}/deactivate")
public ResponseEntity<Void> deactivateUser(@PathVariable Long userId) {
    userService.deactivateUser(userId);
    return ResponseEntity.noContent().build();
}

 */