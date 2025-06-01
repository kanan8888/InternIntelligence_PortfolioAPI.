package az.portfolioapi.exception.project;

import az.portfolioapi.exception.BaseException;
import org.springframework.http.HttpStatus;

public class ProjectNotFoundException extends BaseException {

    public ProjectNotFoundException(Long projectId) {
        super(
                "error.project.not.found",
                String.format("Project not found with id: %d", projectId),
                HttpStatus.NOT_FOUND
        );
    }

    public ProjectNotFoundException(Long projectId, Long userId) {
        super(
                "error.project.not.found",
                String.format("Project not found with id: %d for user with id: %d", projectId, userId),
                HttpStatus.NOT_FOUND
        );
    }
}
