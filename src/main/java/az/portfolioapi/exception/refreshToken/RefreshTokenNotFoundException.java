package az.portfolioapi.exception.refreshToken;

import az.portfolioapi.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class RefreshTokenNotFoundException extends BaseException {

    public RefreshTokenNotFoundException() {
        super(
                "error.refresh.token.not.found",
                "Refresh token not found in database",
                HttpStatus.NOT_FOUND
        );
    }

    public RefreshTokenNotFoundException(String token) {
        super(
                "error.refresh.token.not.found",
                String.format("Refresh token not found: %s", maskToken(token)),
                HttpStatus.NOT_FOUND
        );
    }


    private static String maskToken(String token) {
        if (token == null || token.length() < 8) return "*****";
        return token.substring(0, 4) + "..." + token.substring(token.length() - 4);
    }
}
