package az.portfolioapi.exception.user;

import az.portfolioapi.exception.BaseException;
import org.springframework.http.HttpStatus;

public class EmailAlreadyTakenException extends BaseException {

    public EmailAlreadyTakenException() {
        super("Email is already taken", HttpStatus.CONFLICT);
    }
}
