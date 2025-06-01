package az.portfolioapi.exception.refreshToken;

import az.portfolioapi.exception.BaseException;
import org.springframework.http.HttpStatus;

public class RefreshTokenExpiredException extends BaseException {

    public RefreshTokenExpiredException() {
        super("Refresh token expired", HttpStatus.UNAUTHORIZED);
    }
}
