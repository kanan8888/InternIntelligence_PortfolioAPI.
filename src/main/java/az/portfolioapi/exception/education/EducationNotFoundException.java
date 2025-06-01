package az.portfolioapi.exception.education;

import az.portfolioapi.exception.BaseException;
import org.springframework.http.HttpStatus;

public class EducationNotFoundException extends BaseException {

    public EducationNotFoundException() {
        super("Education not found", HttpStatus.NOT_FOUND);
    }

    public EducationNotFoundException(Long educationId) {
        super("Education not found with id: " + educationId, HttpStatus.NOT_FOUND);
    }
}
