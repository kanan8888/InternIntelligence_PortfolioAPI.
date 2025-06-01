package az.portfolioapi.exception.education;

import az.portfolioapi.exception.BaseException;
import org.springframework.http.HttpStatus;

public class EducationNotFoundException extends BaseException {

    public EducationNotFoundException(Long educationId) {
        super(
                "error.education.not.found",
                String.format("Education not found with id: %d", educationId),
                HttpStatus.NOT_FOUND
        );
    }

    public EducationNotFoundException(Long educationId, Long userId) {
        super(
                "error.education.not.found",
                String.format("Education not found with id: %d for user with id: %d", educationId, userId),
                HttpStatus.NOT_FOUND
        );
    }
}
