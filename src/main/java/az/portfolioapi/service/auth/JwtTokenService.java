package az.portfolioapi.service.auth;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtTokenService {

    String generateAccessToken(UserDetails userDetails);
    String generateRefreshToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
    String extractUsername(String token);
}
