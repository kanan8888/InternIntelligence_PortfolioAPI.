package az.portfolioapi.exception.user;

import az.portfolioapi.exception.BaseException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException() {
        super("User not found", HttpStatus.NOT_FOUND);
    }

    public UserNotFoundException(Long userId) {
        super("User not found with id: " + userId, HttpStatus.NOT_FOUND);
    }
}
