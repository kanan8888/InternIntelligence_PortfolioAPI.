package az.portfolioapi.controller;

import az.portfolioapi.dto.Project.ProjectRequest;
import az.portfolioapi.dto.Project.ProjectResponse;
import az.portfolioapi.security.CustomUserDetails;
import az.portfolioapi.service.project.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/projects")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<ProjectResponse> createProject(@AuthenticationPrincipal CustomUserDetails user,
                                                         @RequestBody @Valid ProjectRequest request) {
        return ResponseEntity.ok(
                projectService.createProject(user.getId(), request)
        );
    }

    @PutMapping("/projects/{projectId}")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long projectId,
                                                         @AuthenticationPrincipal CustomUserDetails user,
                                                         @RequestBody @Valid ProjectRequest request) {
        return ResponseEntity.ok(
                projectService.updateProject(projectId, user.getId(), request)
        );
    }

    @GetMapping("/projects/{projectId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long projectId,
                                                          @AuthenticationPrincipal CustomUserDetails user) {
        return ResponseEntity.ok(
                projectService.getProjectById(projectId, user.getId(), user.getRole())
        );
    }

    @GetMapping("/portfolios/{portfolioId}/projects")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<Page<ProjectResponse>> getProjectsByPortfolioId(@PathVariable Long portfolioId,
                                                                          @AuthenticationPrincipal CustomUserDetails user,
                                                                          Pageable pageable) {
        Page<ProjectResponse> projects = projectService.getProjectsByPortfolioId
                (portfolioId, user.getId(), user.getRole(), pageable);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/users/{userId}/projects")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<ProjectResponse>> getProjectsByUserId(@PathVariable Long userId,
                                                                     Pageable pageable) {
        Page<ProjectResponse> projects = projectService.getProjectsByUserId(userId, pageable);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/users/me/projects")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<Page<ProjectResponse>> getMyProjects(@AuthenticationPrincipal CustomUserDetails user,
                                                               Pageable pageable) {
        Page<ProjectResponse> projects = projectService.getProjectsByUserId(user.getId(), pageable);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/projects")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<ProjectResponse>> getAllProjects(Pageable pageable) {
        Page<ProjectResponse> projects = projectService.getAllProjects(pageable);
        return ResponseEntity.ok(projects);
    }

    @DeleteMapping("/projects/{projectId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId,
                                              @AuthenticationPrincipal CustomUserDetails user) {
        projectService.deleteProject(projectId, user.getId(), user.getRole());
        return ResponseEntity.noContent().build();
    }
}
