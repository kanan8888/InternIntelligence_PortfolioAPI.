package az.portfolioapi.controller;

import az.portfolioapi.dto.auth.request.LoginRequest;
import az.portfolioapi.dto.auth.request.RegisterRequest;
import az.portfolioapi.dto.auth.response.TokenResponse;
import az.portfolioapi.dto.auth.response.RegisterResponse;
import az.portfolioapi.service.auth.AuthService;
import az.portfolioapi.util.cookie.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest request) {
        RegisterResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest request,
                                                      HttpServletResponse response) {
        TokenResponse tokenResponse = authService.login(request);
        CookieUtil.addRefreshToken(response, tokenResponse.getRefreshToken());
        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(HttpServletRequest request,
                                                 HttpServletResponse response) {
        TokenResponse tokenResponse = authService.refresh(CookieUtil.getRefreshToken(request));
        CookieUtil.deleteRefreshToken(response);
        CookieUtil.addRefreshToken(response, tokenResponse.getRefreshToken());
        return ResponseEntity.ok(tokenResponse);
    } /* delete + add = update ? */

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {                  //acces token üçün blackList məntiqi də tətbiq etmək olar, bəlkə edərəm nəvaxtsa...
        CookieUtil.deleteRefreshToken(response);
        return ResponseEntity.ok().build();
    }
}



/*

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest request) {
        RegisterResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request, HttpServletResponse response) {
        LoginResponse loginResponse = authService.login(request, response);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        authService.logout(request, response);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/refresh")                  // bu methodu gələcəkdə istifadə edəcəm hazırki refresh methodunun yerinə
    public ResponseEntity<TokenResponse> refresh(HttpServletRequest request, HttpServletResponse response) {
        String oldRefreshToken = CookieUtil.getRefreshToken(request);
        TokenResponse tokenResponse = authService.refresh(oldRefreshToken);
        CookieUtil.deleteRefreshToken(response);
        CookieUtil.addRefreshToken(response, tokenResponse.getRefreshToken());
        return ResponseEntity.ok(tokenResponse);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser() {
        UserResponse user = authService.getCurrentUser();
        return ResponseEntity.ok(user);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        authService.verifyEmail(token);
        return ResponseEntity.ok("Email successfully verified.");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        authService.sendPasswordResetLink(email);
        return ResponseEntity.ok("Password reset link sent to your email.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody @Valid ResetPasswordRequest request) {
        authService.resetPassword(request);
        return ResponseEntity.ok("Password has been reset successfully.");
    }
}


 */


