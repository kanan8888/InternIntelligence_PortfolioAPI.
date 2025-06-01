package az.portfolioapi.service.project;

import az.portfolioapi.entity.enums.UserRole;
import az.portfolioapi.exception.AccessDeniedException;
import az.portfolioapi.exception.portfolio.PortfolioNotFoundException;
import az.portfolioapi.exception.project.ProjectNotFoundException;
import az.portfolioapi.mapper.ProjectMapper;
import az.portfolioapi.dto.Project.ProjectRequest;
import az.portfolioapi.dto.Project.ProjectResponse;
import az.portfolioapi.entity.PortfolioEntity;
import az.portfolioapi.entity.ProjectEntity;
import az.portfolioapi.repository.PortfolioRepository;
import az.portfolioapi.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final PortfolioRepository portfolioRepository;
    private final ProjectMapper projectMapper;

    @Override
    public ProjectResponse createProject(Long userId, ProjectRequest request) {
        PortfolioEntity portfolio = portfolioRepository.findByIdAndUser_Id(request.getPortfolioId(), userId)
                .orElseThrow(PortfolioNotFoundException::new);
        ProjectEntity project = projectMapper.toEntity(request, portfolio);
        return projectMapper.toResponse(
                projectRepository.save(project)
        );
    }

    @Override
    public ProjectResponse updateProject(Long projectId, Long userId, ProjectRequest request) {
        ProjectEntity project = projectRepository.findByIdAndPortfolio_User_Id(projectId, userId)
                .orElseThrow(ProjectNotFoundException::new);
        projectMapper.updateEntity(request, project);
        return projectMapper.toResponse(
                projectRepository.save(project)
        );
    }

    @Override
    public ProjectResponse getProjectById(Long projectId, Long userId, UserRole role) {
        ProjectEntity project = getProjectByRole(projectId, userId, role);
        return projectMapper.toResponse(project);
    }

    @Override
    public Page<ProjectResponse> getProjectsByPortfolioId(Long portfolioId, Long userId, UserRole role, Pageable pageable) {
        Page<ProjectResponse> projects = role == UserRole.ADMIN
                ? projectRepository.findByPortfolio_Id(portfolioId, pageable)
                        .map(projectMapper::toResponse)
                : projectRepository.findByPortfolio_IdAndPortfolio_User_Id(portfolioId, userId, pageable)
                        .map(projectMapper::toResponse);
        if(projects.isEmpty()){
            throw new ProjectNotFoundException();
        }
        return projects;
    }

    @Override
    public Page<ProjectResponse> getProjectsByUserId(Long userId, Pageable pageable) {
        return projectRepository.findByPortfolio_User_Id(userId, pageable)
                .map(projectMapper::toResponse);
    }

    @Override
    public Page<ProjectResponse> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable)
                .map(projectMapper::toResponse);
    }

    @Override
    public void deleteProject(Long projectId, Long userId, UserRole role) {
        ProjectEntity project = getProjectByRole(projectId, userId, role);
        projectRepository.delete(project);
    }


    private ProjectEntity getProjectByRole(Long projectId, Long userId, UserRole role) {
        return role == UserRole.ADMIN
                ? projectRepository.findById(projectId)
                        .orElseThrow(() -> new ProjectNotFoundException(projectId))
                : projectRepository.findByIdAndPortfolio_User_Id(projectId, userId)
                        .orElseThrow(() -> new AccessDeniedException("You do not have Project with id: " + projectId));
    }
}
