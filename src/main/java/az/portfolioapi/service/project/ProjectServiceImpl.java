package az.portfolioapi.service.project;

import az.portfolioapi.dto.request.ProjectRequestDTO;
import az.portfolioapi.dto.response.ProjectResponseDTO;
import az.portfolioapi.entity.Project;
import az.portfolioapi.exception.ProjectNotFoundException;
import az.portfolioapi.exception.SkillNotFoundException;
import az.portfolioapi.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO) {
        return null;
    }

    @Override
    public ProjectResponseDTO updateProject(Long id, ProjectRequestDTO projectRequestDTO) {
        return null;
    }

    @Override
    public ProjectResponseDTO getProjectById(Long id) {
        return projectRepository.findById(id)
                .map(project -> modelMapper.map(project, ProjectResponseDTO.class))
                .orElseThrow(()-> new SkillNotFoundException("project not found with id " + id));
    }

    @Override
    public List<ProjectResponseDTO> getProjectsByPortfolioId(Long portfolioId) {
        return List.of();
    }

    @Override
    public List<ProjectResponseDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(project -> modelMapper.map(project, ProjectResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("project not found with id " + id));
        projectRepository.delete(project);
    }
}
