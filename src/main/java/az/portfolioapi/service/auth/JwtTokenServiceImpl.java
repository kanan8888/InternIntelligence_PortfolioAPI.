package az.portfolioapi.service.auth;

import az.portfolioapi.config.properties.JwtProperties;
import az.portfolioapi.entity.RefreshTokenEntity;
import az.portfolioapi.entity.UserEntity;
import az.portfolioapi.repository.RefreshTokenRepository;
import az.portfolioapi.repository.UserRepository;
import az.portfolioapi.security.CustomUserDetails;
import az.portfolioapi.security.CustomUserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {

    private final JwtProperties jwtProperties;

//    private String secretKey = "RXlKbGJXRnBiQ0k2SW5SaGRHVXRjR3hsYmlJNklqSTBOakE0TkRBek5UVTRPU3dpYVdGMElqb3hOVEl6TXpFd016TTNOakFpTENKcFlYUWlPakV3TnpNek5qTTNNakU1Z3lJc0ltVjRjQ0k2TVRVMk16YzBNR0k1WlRReE1HTmtNbUl4TVRJMlpHVTFNVEF6T1RVNFlpSXNJbWx1ZEdWNGRDSTZJbXhwYm1WamRDOTJhV0YwSWl3aWFXRjBJam94TmpneE1ETXdNakl3TENKcFlYUWlPakV3TWpJM01EQXdPRGcwTENKcFlYUWlPakV3TXpBek56ZzNORE0xTENKcFlYUWlPakV3TURjeE1EQXdOamczZ2lKOS5BbU5hbEdZeTVHRUhvYURVUlVKRXlDaThVZldDb29zeEM=";
//
//    private long accessTokenExpiration = 86400000;
//
//    private long refreshTokenExpiration = 604800000;

    private final RefreshTokenRepository refreshTokenRepository;
    private final CustomUserDetailsServiceImpl userDetailsService;
    private final UserRepository userRepository;

    public String generateAccessToken(CustomUserDetails userDetails) {
        return buildAccessToken(userDetails, jwtProperties.getAccessTokenExpiration());
    }

    public String generateRefreshToken(UserEntity user) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[64]; // 512 bit
        secureRandom.nextBytes(bytes);
        String token = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);

        RefreshTokenEntity refreshToken = RefreshTokenEntity.builder()
                .token(token)
                .user(user)
                .expiryDate(Instant.now().plus(Duration.ofMillis(jwtProperties.getRefreshTokenExpiration())))
                .build();

        refreshTokenRepository.save(refreshToken);
        return token;
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    private String buildAccessToken(UserDetails userDetails, long expiration) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
