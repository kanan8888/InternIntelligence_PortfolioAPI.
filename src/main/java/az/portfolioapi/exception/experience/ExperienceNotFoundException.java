package az.portfolioapi.exception.experience;

import az.portfolioapi.exception.BaseException;
import org.springframework.http.HttpStatus;

public class ExperienceNotFoundException extends BaseException {

    public ExperienceNotFoundException(Long experienceId) {
        super(
                "error.experience.not.found",
                String.format("Experience not found with id: %d", experienceId),
                HttpStatus.NOT_FOUND
        );
    }

    public ExperienceNotFoundException(Long experienceId, Long userId) {
        super(
                "error.experience.not.found",
                String.format("Experience not found with id: %d for user with id: %d", experienceId, userId),
                HttpStatus.NOT_FOUND
        );
    }
}
