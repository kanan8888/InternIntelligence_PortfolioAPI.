package az.portfolioapi.exception.refreshToken;

import az.portfolioapi.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class RefreshTokenExpiredException extends BaseException {

    public RefreshTokenExpiredException() {
        super(
                "error.refresh.token.expired",
                "Refresh token expired",
                HttpStatus.UNAUTHORIZED
        );
    }

    public RefreshTokenExpiredException(String token) {
        super(
                "error.refresh.token.expired",
                String.format("Refresh token expired: %s", maskToken(token)),
                HttpStatus.UNAUTHORIZED
        );
    }

    private static String maskToken(String token) {
        if (token == null || token.length() < 8) return "*****";
        return token.substring(0, 4) + "..." + token.substring(token.length() - 4);
    }
}
