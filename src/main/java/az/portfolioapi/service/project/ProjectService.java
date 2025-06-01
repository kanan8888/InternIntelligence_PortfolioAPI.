package az.portfolioapi.service.project;

import az.portfolioapi.dto.Experience.ExperienceRequest;
import az.portfolioapi.dto.Experience.ExperienceResponse;
import az.portfolioapi.dto.Project.ProjectRequest;
import az.portfolioapi.dto.Project.ProjectResponse;
import az.portfolioapi.entity.enums.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {

    ProjectResponse createProject(Long userId, ProjectRequest request);
    ProjectResponse updateProject(Long projectId, Long userId, ProjectRequest request);
    ProjectResponse getProjectById(Long projectId, Long userId, UserRole role);
    Page<ProjectResponse> getProjectsByPortfolioId(Long portfolioId, Long userId, UserRole role, Pageable pageable);
    Page<ProjectResponse> getProjectsByUserId(Long userId, Pageable pageable);
    Page<ProjectResponse> getAllProjects(Pageable pageable);
    void deleteProject(Long projectId, Long userId, UserRole role);
}
