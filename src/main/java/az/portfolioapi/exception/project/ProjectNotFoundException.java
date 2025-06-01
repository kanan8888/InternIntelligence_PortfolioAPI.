package az.portfolioapi.exception.project;

import az.portfolioapi.exception.BaseException;
import org.springframework.http.HttpStatus;

public class ProjectNotFoundException extends BaseException {

    public ProjectNotFoundException() {
        super("Project not found", HttpStatus.NOT_FOUND);
    }

    public ProjectNotFoundException(Long projectId) {
        super("Project not found with id: " + projectId, HttpStatus.NOT_FOUND);
    }
}
