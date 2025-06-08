package az.portfolioapi.exception.user;

import az.portfolioapi.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class AdminUserDeleteForbiddenException extends BaseException {

    public AdminUserDeleteForbiddenException(Long userId) {
        super(
                "error.user.admin.delete.forbidden",
                String.format("Attempt to delete admin user with id: %d", userId),
                HttpStatus.FORBIDDEN
        );
    }
}
