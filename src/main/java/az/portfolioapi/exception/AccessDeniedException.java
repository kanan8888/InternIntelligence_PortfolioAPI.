package az.portfolioapi.exception;

import org.springframework.http.HttpStatus;

public class AccessDeniedException extends BaseException {

    public AccessDeniedException() {
        super("Access denied", HttpStatus.FORBIDDEN);
    }

    public AccessDeniedException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
