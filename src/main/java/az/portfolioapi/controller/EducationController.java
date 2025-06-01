package az.portfolioapi.controller;

import az.portfolioapi.dto.Education.EducationRequest;
import az.portfolioapi.dto.Education.EducationResponse;
import az.portfolioapi.security.CustomUserDetails;
import az.portfolioapi.service.education.EducationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class EducationController {

    private final EducationService educationService;

    @PostMapping("/educations")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<EducationResponse> createEducation(@AuthenticationPrincipal CustomUserDetails user,
                                                             @RequestBody @Valid EducationRequest request) {
        return ResponseEntity.ok(
                educationService.createEducation(user.getId(), request)
        );
    }

    @PutMapping("/educations/{educationId}")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<EducationResponse> updateEducation(@PathVariable Long educationId,
                                                             @AuthenticationPrincipal CustomUserDetails user,
                                                             @RequestBody @Valid EducationRequest request) {
        return ResponseEntity.ok(
                educationService.updateEducation(educationId, user.getId(), request)
        );
    }

    @GetMapping("/educations/{educationId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<EducationResponse> getEducationById(@PathVariable Long educationId,
                                                              @AuthenticationPrincipal CustomUserDetails user) {
        return ResponseEntity.ok(
                educationService.getEducationById(educationId, user.getId(), user.getRole())
        );
    }

    @GetMapping("/portfolios/{portfolioId}/educations")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<Page<EducationResponse>> getEducationsByPortfolioId(@PathVariable Long portfolioId,
                                                                              @AuthenticationPrincipal CustomUserDetails user,
                                                                              Pageable pageable) {
        Page<EducationResponse> educations = educationService.getEducationsByPortfolioId(
                portfolioId, user.getId(), user.getRole(), pageable);
        return ResponseEntity.ok(educations);
    }

    @GetMapping("/users/{userId}/educations")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<EducationResponse>> getEducationsByUserId(@PathVariable Long userId,
                                                                         Pageable pageable) {
        Page<EducationResponse> educations = educationService.getEducationsByUserId(userId, pageable);
        return ResponseEntity.ok(educations);
    }

    @GetMapping("users/me/educations")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<Page<EducationResponse>> getMyEducations(@AuthenticationPrincipal CustomUserDetails user,
                                                                   Pageable pageable) {
        Page<EducationResponse> educations = educationService.getEducationsByUserId(user.getId(), pageable);
        return ResponseEntity.ok(educations);
    }

    @GetMapping("/educations")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<EducationResponse>> getAllEducations(Pageable pageable) {
        Page<EducationResponse> educations = educationService.getAllEducations(pageable);
        return ResponseEntity.ok(educations);
    }

    @DeleteMapping("/educations/{educationId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long educationId,
                                                @AuthenticationPrincipal CustomUserDetails user) {
        educationService.deleteEducation(educationId, user.getId(), user.getRole());
        return ResponseEntity.noContent().build();
    }
}

