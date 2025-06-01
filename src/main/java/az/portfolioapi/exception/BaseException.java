package az.portfolioapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {

    private final String messageKey;
    private final String logMessage;
    private final HttpStatus httpStatus;

    public BaseException(String messageKey, String logMessage, HttpStatus httpStatus) {
        super(logMessage);
        this.messageKey = messageKey;
        this.logMessage = logMessage;
        this.httpStatus = httpStatus;
    }
}