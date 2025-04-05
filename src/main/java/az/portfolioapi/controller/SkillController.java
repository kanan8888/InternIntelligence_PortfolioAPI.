package az.portfolioapi.controller;

import az.portfolioapi.dto.request.SkillRequestDTO;
import az.portfolioapi.dto.response.SkillResponseDTO;
import az.portfolioapi.service.skill.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SkillResponseDTO> createSkill(@RequestBody SkillRequestDTO skillRequestDTO) {
        return ResponseEntity.ok(skillService.createSkill(skillRequestDTO));
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SkillResponseDTO> updateSkill(@PathVariable Long id, @RequestBody SkillRequestDTO skillRequestDTO) {
        return ResponseEntity.ok(skillService.updateSkill(id, skillRequestDTO));
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<SkillResponseDTO> getSkillById(@PathVariable Long id) {
        return ResponseEntity.ok(skillService.getSkillById(id));
    }

    @GetMapping("/portfolio/{portfolioId}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<List<SkillResponseDTO>> getSkillsByPortfolioId(@PathVariable Long portfolioId) {
        return ResponseEntity.ok(skillService.getSkillsByPortfolioId(portfolioId));
    }

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<SkillResponseDTO>> getAllSkills() {
        return ResponseEntity.ok(skillService.getAllSkills());
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }
}
