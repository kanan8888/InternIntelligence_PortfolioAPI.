package az.portfolioapi.controller;

import az.portfolioapi.dto.request.EducationRequest;
import az.portfolioapi.dto.response.EducationResponse;
import az.portfolioapi.service.education.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/educations")
public class EducationController {

    private final EducationService educationService;

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EducationResponse> create(@RequestBody EducationRequest request) {
        return ResponseEntity.ok(educationService.createEducation(request));
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EducationResponse> updateEducation(@PathVariable Long id, @RequestBody EducationRequest request) {
        return ResponseEntity.ok(educationService.updateEducation(id, request));
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<EducationResponse> getEducationById(@PathVariable Long id) {
        return ResponseEntity.ok(educationService.getEducationById(id));
    }

    @GetMapping("/portfolio/{portfolioId}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<List<EducationResponse>> getEducationsByPortfolioId(@PathVariable Long portfolioId) {
        return ResponseEntity.ok(educationService.getEducationsByPortfolioId(portfolioId));
    }

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<EducationResponse>> getAllEducations() {
        return ResponseEntity.ok(educationService.getAllEducations());
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long id) {
        educationService.deleteEducation(id);
        return ResponseEntity.noContent().build();
    }
}
