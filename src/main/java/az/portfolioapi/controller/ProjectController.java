package az.portfolioapi.controller;

import az.portfolioapi.dto.request.ProjectRequest;
import az.portfolioapi.dto.response.ProjectResponse;
import az.portfolioapi.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectRequest request) {
        return ResponseEntity.ok(projectService.createProject(request));
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id, @RequestBody ProjectRequest request) {
        return ResponseEntity.ok(projectService.updateProject(id, request));
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @GetMapping("/portfolio/{portfolioId}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<List<ProjectResponse>> getProjectsByPortfolioId(@PathVariable Long portfolioId) {
        return ResponseEntity.ok(projectService.getProjectsByPortfolioId(portfolioId));
    }

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
