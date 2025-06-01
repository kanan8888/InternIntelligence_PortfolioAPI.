package az.portfolioapi.controller;

import az.portfolioapi.dto.Experience.ExperienceRequest;
import az.portfolioapi.dto.Experience.ExperienceResponse;
import az.portfolioapi.security.CustomUserDetails;
import az.portfolioapi.service.experience.ExperienceService;
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
public class ExperienceController {

    private final ExperienceService experienceService;

    @PostMapping("/experiences")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<ExperienceResponse> createExperience(@AuthenticationPrincipal CustomUserDetails user,
                                                               @RequestBody @Valid ExperienceRequest request) {
        return ResponseEntity.ok(
                experienceService.createExperience(user.getId(), request)
        );
    }

    @PutMapping("/experiences/{experienceId}")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<ExperienceResponse> updateExperience(@PathVariable Long experienceId,
                                                               @AuthenticationPrincipal CustomUserDetails user,
                                                               @RequestBody @Valid ExperienceRequest request) {
        return ResponseEntity.ok(
                experienceService.updateExperience(experienceId, user.getId(), request)
        );
    }

    @GetMapping("/experiences/{experienceId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<ExperienceResponse> getExperienceById(@PathVariable Long experienceId,
                                                                @AuthenticationPrincipal CustomUserDetails user) {
        return ResponseEntity.ok(
                experienceService.getExperienceById(experienceId, user.getId(), user.getRole())
        );
    }

    @GetMapping("/portfolios/{portfolioId}/experiences")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<Page<ExperienceResponse>> getExperiencesByPortfolioId(@PathVariable Long portfolioId,
                                                                                @AuthenticationPrincipal CustomUserDetails user,
                                                                                Pageable pageable) {
        Page<ExperienceResponse> experiences = experienceService.getExperiencesByPortfolioId(
                portfolioId, user.getId(), user.getRole(), pageable);
        return ResponseEntity.ok(experiences);
    }

    @GetMapping("/users/{userId}/experiences")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<ExperienceResponse>> getExperiencesByUserId(@PathVariable Long userId,
                                                                           Pageable pageable) {
        Page<ExperienceResponse> experiences = experienceService.getExperiencesByUserId(userId, pageable);
        return ResponseEntity.ok(experiences);
    }

    @GetMapping("/users/me/experiences")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<Page<ExperienceResponse>> getMyExperiences(@AuthenticationPrincipal CustomUserDetails user,
                                                                     Pageable pageable) {
        Page<ExperienceResponse> experiences = experienceService.getExperiencesByUserId(user.getId(), pageable);
        return ResponseEntity.ok(experiences);
    }

    @GetMapping("/experiences")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<ExperienceResponse>> getAllExperiences(Pageable pageable) {
        Page<ExperienceResponse> experiences = experienceService.getAllExperiences(pageable);
        return ResponseEntity.ok(experiences);
    }

    @DeleteMapping("/experiences/{experienceId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long experienceId,
                                                 @AuthenticationPrincipal CustomUserDetails user) {
        experienceService.deleteExperience(experienceId, user.getId(), user.getRole());
        return ResponseEntity.noContent().build();
    }
}
