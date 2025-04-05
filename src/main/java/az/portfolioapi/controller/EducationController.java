package az.portfolioapi.controller;

import az.portfolioapi.dto.request.EducationRequestDTO;
import az.portfolioapi.dto.response.EducationResponseDTO;
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
    public ResponseEntity<EducationResponseDTO> createEducation(@RequestBody EducationRequestDTO educationRequestDTO) {
        return ResponseEntity.ok(educationService.createEducation(educationRequestDTO));
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EducationResponseDTO> updateEducation(@PathVariable Long id, @RequestBody EducationRequestDTO educationRequestDTO) {
        return ResponseEntity.ok(educationService.updateEducation(id, educationRequestDTO));
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<EducationResponseDTO> getEducationById(@PathVariable Long id) {
        return ResponseEntity.ok(educationService.getEducationById(id));
    }

    @GetMapping("/portfolio/{portfolioId}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<List<EducationResponseDTO>> getEducationsByPortfolioId(@PathVariable Long portfolioId) {
        return ResponseEntity.ok(educationService.getEducationsByPortfolioId(portfolioId));
    }

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<EducationResponseDTO>> getAllEducations() {
        return ResponseEntity.ok(educationService.getAllEducations());
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long id) {
        educationService.deleteEducation(id);
        return ResponseEntity.noContent().build();
    }
}
