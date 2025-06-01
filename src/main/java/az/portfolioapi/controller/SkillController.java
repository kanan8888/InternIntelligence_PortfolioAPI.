package az.portfolioapi.controller;

import az.portfolioapi.dto.Skill.SkillRequest;
import az.portfolioapi.dto.Skill.SkillResponse;
import az.portfolioapi.security.CustomUserDetails;
import az.portfolioapi.service.skill.SkillService;
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
public class SkillController {

    private final SkillService skillService;

    @PostMapping("/skills")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<SkillResponse> createSkill(@AuthenticationPrincipal CustomUserDetails user,
                                                     @RequestBody @Valid SkillRequest request) {
        return ResponseEntity.ok(
                skillService.createSkill(user.getId(), request)
        );
    }

    @PutMapping("/skills/{skillId}")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<SkillResponse> updateSkill(@PathVariable Long skillId,
                                                     @AuthenticationPrincipal CustomUserDetails user,
                                                     @RequestBody @Valid SkillRequest request) {
        return ResponseEntity.ok(
                skillService.updateSkill(skillId, user.getId(), request)
        );
    }

    @GetMapping("/skills/{skillId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<SkillResponse> getSkillById(@PathVariable Long skillId,
                                                      @AuthenticationPrincipal CustomUserDetails user) {
        return ResponseEntity.ok(
                skillService.getSkillById(skillId, user.getId(), user.getRole())
        );
    }

    @GetMapping("/portfolios/{portfolioId}/skills")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<Page<SkillResponse>> getSkillsByPortfolioId(@PathVariable Long portfolioId,
                                                                      @AuthenticationPrincipal CustomUserDetails user,
                                                                      Pageable pageable) {
        Page<SkillResponse> skills = skillService.getSkillsByPortfolioId
                (portfolioId, user.getId(), user.getRole(), pageable);
        return ResponseEntity.ok(skills);
    }

    @GetMapping("/users/{userId}/skills")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<SkillResponse>> getSkillByUserId(@PathVariable Long userId,
                                                                Pageable pageable) {
        Page<SkillResponse> skills = skillService.getSkillsByUserId(userId, pageable);
        return ResponseEntity.ok(skills);
    }

    @GetMapping("users/me/skills")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<Page<SkillResponse>> getMySkills(@AuthenticationPrincipal CustomUserDetails user,
                                                           Pageable pageable) {
        Page<SkillResponse> skills = skillService.getSkillsByUserId(user.getId(), pageable);
        return ResponseEntity.ok(skills);
    }

    @GetMapping("/skills")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<SkillResponse>> getAllSkills(Pageable pageable) {
        Page<SkillResponse> skills = skillService.getAllSkills(pageable);
        return ResponseEntity.ok(skills);
    }

    @DeleteMapping("/skills/{skillId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long skillId,
                                            @AuthenticationPrincipal CustomUserDetails user) {
        skillService.deleteSkill(skillId, user.getId(), user.getRole());
        return ResponseEntity.noContent().build();
    }
}
