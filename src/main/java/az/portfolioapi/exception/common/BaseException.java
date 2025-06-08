package az.portfolioapi.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
//@RequiredArgsConstructor
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