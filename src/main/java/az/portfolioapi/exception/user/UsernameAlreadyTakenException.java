package az.portfolioapi.exception.user;

import az.portfolioapi.exception.BaseException;
import org.springframework.http.HttpStatus;

public class UsernameAlreadyTakenException extends BaseException {

    public UsernameAlreadyTakenException(String username) {
        super(
                "error.user.username.already.taken",
                String.format("The username '%s' is already taken", username),
                HttpStatus.CONFLICT
        );
    }
}
