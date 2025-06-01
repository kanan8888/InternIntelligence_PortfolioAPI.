package az.portfolioapi.exception.user;

import az.portfolioapi.exception.BaseException;
import org.springframework.http.HttpStatus;

public class EmailAlreadyTakenException extends BaseException {

    public EmailAlreadyTakenException(String email) {
        super(
                "error.user.email.already.taken",
                String.format("The email '%s' is already taken", email),
                HttpStatus.CONFLICT
        );
    }
}
