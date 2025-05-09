package az.portfolioapi.service.project;

import az.portfolioapi.mapper.ProjectMapper;
import az.portfolioapi.dto.Project.ProjectRequest;
import az.portfolioapi.dto.Project.ProjectResponse;
import az.portfolioapi.entity.PortfolioEntity;
import az.portfolioapi.entity.ProjectEntity;
import az.portfolioapi.exception.PortfolioNotFoundException;
import az.portfolioapi.exception.ProjectNotFoundException;
import az.portfolioapi.exception.SkillNotFoundException;
import az.portfolioapi.repository.PortfolioRepository;
import az.portfolioapi.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final PortfolioRepository portfolioRepository;
    private final ProjectMapper projectMapper;

    @Override
    public ProjectResponse createProject(ProjectRequest projectRequest) {
        PortfolioEntity portfolio = portfolioRepository.findById(projectRequest.getPortfolioId())
                .orElseThrow(()-> new PortfolioNotFoundException("portfolio not found with id " + projectRequest.getPortfolioId()));
        ProjectEntity project = projectMapper.toEntity(projectRequest,portfolio);
        return projectMapper.toResponse(projectRepository.save(project));
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest projectRequest) {
        ProjectEntity project = projectRepository.findById(id)
                .orElseThrow(()-> new ProjectNotFoundException("project not found with id" + id));
        projectMapper.updateEntity(projectRequest,project);
        return projectMapper.toResponse(projectRepository.save(project));
    }

    @Override
    public ProjectResponse getProjectById(Long id) {
        return projectRepository.findById(id)
                .map(projectMapper::toResponse)
                .orElseThrow(()-> new SkillNotFoundException("project not found with id " + id));
    }

    @Override
    public List<ProjectResponse> getProjectsByPortfolioId(Long portfolioId) {
        PortfolioEntity portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new PortfolioNotFoundException("Portfolio not found with id " + portfolioId));
        return portfolio.getProjects().stream()
                .map(projectMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectResponse> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(projectMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProject(Long id) {
        ProjectEntity project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("project not found with id " + id));
        projectRepository.delete(project);
    }
}
