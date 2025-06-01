package az.portfolioapi.exception.refreshToken;

import az.portfolioapi.exception.BaseException;
import org.springframework.http.HttpStatus;

public class RefreshTokenNotFoundException extends BaseException {

    public RefreshTokenNotFoundException() {
        super("Refresh token not found", HttpStatus.UNAUTHORIZED);
    }
}
