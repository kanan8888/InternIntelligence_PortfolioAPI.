package az.portfolioapi.service.project;

import az.portfolioapi.dto.request.ProjectRequest;
import az.portfolioapi.dto.response.ProjectResponse;

import java.util.List;

public interface ProjectService {

    ProjectResponse createProject(ProjectRequest projectRequest);
    ProjectResponse updateProject(Long id, ProjectRequest projectRequest);
    ProjectResponse getProjectById(Long id);
    List<ProjectResponse> getProjectsByPortfolioId(Long portfolioId);
    List<ProjectResponse> getAllProjects();
    void deleteProject(Long id);
}
