package az.portfolioapi.controller;

import az.portfolioapi.dto.Experience.ExperienceRequest;
import az.portfolioapi.dto.Experience.ExperienceResponse;
import az.portfolioapi.service.experience.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/experiences")
public class ExperienceController {

    private final ExperienceService experienceService;

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ExperienceResponse> createExperience(@RequestBody ExperienceRequest request) {
        return ResponseEntity.ok(experienceService.createExperience(request));
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ExperienceResponse> updateExperience(@PathVariable Long id, @RequestBody ExperienceRequest request) {
        return ResponseEntity.ok(experienceService.updateExperience(id, request));
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<ExperienceResponse> getExperienceById(@PathVariable Long id) {
        return ResponseEntity.ok(experienceService.getExperienceById(id));
    }

    @GetMapping("/portfolio/{portfolioId}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<List<ExperienceResponse>> getExperiencesByPortfolioId(@PathVariable Long portfolioId) {
        return ResponseEntity.ok(experienceService.getExperiencesByPortfolioId(portfolioId));
    }

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ExperienceResponse>> getAllExperiences() {
        return ResponseEntity.ok(experienceService.getAllExperiences());
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        experienceService.deleteExperience(id);
        return ResponseEntity.noContent().build();
    }
}
