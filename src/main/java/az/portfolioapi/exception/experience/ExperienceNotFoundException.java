package az.portfolioapi.exception.experience;

import az.portfolioapi.exception.BaseException;
import org.springframework.http.HttpStatus;

public class ExperienceNotFoundException extends BaseException {

    public ExperienceNotFoundException() {
        super("Experience not found", HttpStatus.NOT_FOUND);
    }

    public ExperienceNotFoundException(Long experienceId) {
        super("Experience not found with id: " + experienceId, HttpStatus.NOT_FOUND);
    }
}
