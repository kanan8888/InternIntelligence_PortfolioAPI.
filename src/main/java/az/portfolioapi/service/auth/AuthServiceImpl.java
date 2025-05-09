package az.portfolioapi.service.auth;

import az.portfolioapi.entity.RefreshTokenEntity;
import az.portfolioapi.mapper.UserMapper;
import az.portfolioapi.dto.auth.request.LoginRequest;
import az.portfolioapi.dto.auth.request.RegisterRequest;
import az.portfolioapi.dto.auth.response.TokenResponse;
import az.portfolioapi.dto.auth.response.RegisterResponse;
import az.portfolioapi.entity.UserEntity;
import az.portfolioapi.repository.RefreshTokenRepository;
import az.portfolioapi.repository.UserRepository;
import az.portfolioapi.security.CustomUserDetails;
import az.portfolioapi.security.CustomUserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenServiceImpl jwtService;
    private final UserMapper userMapper;

    private final CustomUserDetailsServiceImpl customUserDetailsService;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("User with email " + request.getEmail() + " already exists.");

        if (userRepository.existsByUserName(request.getUserName()))
            throw new RuntimeException("User with userName " + request.getUserName() + " already exists.");

        UserEntity user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        UserEntity savedUser = userRepository.save(user);
        return userMapper.toRegisterResponse(savedUser);
    }

    @Override
    public TokenResponse login(LoginRequest request) {
            // İstifadəçi adı və şifrəni yoxla
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
            );

//            // İstifadəçi məlumatlarını əldə et
//            UserEntity user = userRepository.findByUserName(request.getUserName())
//                    .orElseThrow(() -> new RuntimeException("İstifadəçi adı və ya şifrə yalnışdır!"));

            UserDetails user = customUserDetailsService.loadUserByUsername(request.getUserName());

            // Token yaradılır (istifadəçi məlumatları daxil edilir)
            String jwtAccessToken = jwtService.generateAccessToken(user);
            String jwtRefreshToken = jwtService.generateRefreshToken(user);

            return TokenResponse.builder()
                    .accessToken(jwtAccessToken)
                    .refreshToken(jwtRefreshToken)
                    .build();
    }

    @Override
    public TokenResponse refresh(String oldRefreshToken) {
        RefreshTokenEntity refreshToken = refreshTokenRepository.findByToken(oldRefreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));

        if (refreshToken.isExpired()) {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh token expired");
        }

        UserEntity user = refreshToken.getUser();

        String newAccessToken = jwtService.generateAccessToken(new CustomUserDetails(user));

        return TokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(oldRefreshToken)
                .build();
    }
}
