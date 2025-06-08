package az.portfolioapi.service.auth;

import az.portfolioapi.entity.RefreshTokenEntity;
import az.portfolioapi.exception.refreshToken.RefreshTokenExpiredException;
import az.portfolioapi.exception.refreshToken.RefreshTokenNotFoundException;
import az.portfolioapi.exception.user.UserNotFoundException;
import az.portfolioapi.mapper.AuthMapper;
import az.portfolioapi.dto.auth.request.LoginRequest;
import az.portfolioapi.dto.auth.request.RegisterRequest;
import az.portfolioapi.dto.auth.response.TokenResponse;
import az.portfolioapi.dto.auth.response.RegisterResponse;
import az.portfolioapi.entity.UserEntity;
import az.portfolioapi.repository.RefreshTokenRepository;
import az.portfolioapi.repository.UserRepository;
import az.portfolioapi.security.CustomUserDetails;
import az.portfolioapi.security.CustomUserDetailsServiceImpl;
import az.portfolioapi.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenServiceImpl jwtService;
    private final UserServiceImpl userService;
    private final UserRepository userRepository;
    private final AuthMapper authMapper;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        return authMapper.userResponseToRegisterResponse(
                userService.createUser(
                        authMapper.registerRequestToUserRequest(request)
                )
        );
    }

    @Override/**/
    public TokenResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(()-> new UserNotFoundException(request.getUsername()));

        String jwtAccessToken = jwtService.generateAccessToken(new CustomUserDetails(user));
        String jwtRefreshToken = jwtService.generateRefreshToken(user);

        return TokenResponse.builder()
                .accessToken(jwtAccessToken)
                .refreshToken(jwtRefreshToken)
                .build();
    }    /**/

    @Override
    public TokenResponse refresh(String oldRefreshToken) {
        RefreshTokenEntity refreshToken = refreshTokenRepository.findByToken(oldRefreshToken)
                .orElseThrow(()-> new RefreshTokenNotFoundException(oldRefreshToken));

        if (refreshToken.isExpired()) {
            refreshTokenRepository.delete(refreshToken);
            throw new RefreshTokenExpiredException(oldRefreshToken);
        }

        UserEntity user = refreshToken.getUser();

        String newAccessToken = jwtService.generateAccessToken(new CustomUserDetails(user));
        String newRefreshToken = jwtService.generateRefreshToken(user);

        return TokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }
}
