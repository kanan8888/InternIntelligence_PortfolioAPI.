package az.portfolioapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {

    private final HttpStatus status;

    public BaseException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}

/*

@Getter
public class BaseException extends RuntimeException {

    private final String publicMessage;
    private final String logMessage;
    private final HttpStatus status;

    public BaseException(String publicMessage, String logMessage, HttpStatus status) {
        super(logMessage);
        this.publicMessage = publicMessage;
        this.logMessage = logMessage;
        this.status = status;
    }
}

 */