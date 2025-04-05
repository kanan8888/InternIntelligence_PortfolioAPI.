package az.portfolioapi.service.project;

import az.portfolioapi.dto.request.ProjectRequestDTO;
import az.portfolioapi.dto.response.ProjectResponseDTO;

import java.util.List;

public interface ProjectService {
    ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO);
    ProjectResponseDTO updateProject(Long id, ProjectRequestDTO projectRequestDTO);
    ProjectResponseDTO getProjectById(Long id);
    List<ProjectResponseDTO> getProjectsByPortfolioId(Long portfolioId);
    List<ProjectResponseDTO> getAllProjects();
    void deleteProject(Long id);
}
