package az.portfolioapi.service.auth;

import az.portfolioapi.entity.UserEntity;
import az.portfolioapi.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtTokenService {

    String generateAccessToken(CustomUserDetails userDetails);
    String generateRefreshToken(UserEntity user);
    boolean isTokenValid(String token, UserDetails userDetails);
    String extractUsername(String token);
}
