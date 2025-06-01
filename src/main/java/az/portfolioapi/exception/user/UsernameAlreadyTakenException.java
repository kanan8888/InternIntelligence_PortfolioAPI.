package az.portfolioapi.exception.user;

import az.portfolioapi.exception.BaseException;
import org.springframework.http.HttpStatus;

public class UsernameAlreadyTakenException extends BaseException {

    public UsernameAlreadyTakenException() {
        super("Username is already taken", HttpStatus.CONFLICT);
    }
}
