package az.portfolioapi.exception.user;

import az.portfolioapi.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException(Long userId) {
        super(
                "error.user.not.found", // i18n key
                String.format("User not found with id: %d", userId),
                HttpStatus.NOT_FOUND
        );
    }

    public UserNotFoundException(String username) {
        super(
                "error.user.not.found",
                String.format("User not found with username: %s", username),
                HttpStatus.NOT_FOUND
        );
    }

    public UserNotFoundException(Long userId, String username) {
        super(
                "error.user.not.found",
                String.format("User not found with id: %d and username: %s", userId, username),
                HttpStatus.NOT_FOUND
        );
    }
}
