package az.portfolioapi.service.skill;

import az.portfolioapi.dto.Experience.ExperienceRequest;
import az.portfolioapi.dto.Experience.ExperienceResponse;
import az.portfolioapi.dto.Skill.SkillRequest;
import az.portfolioapi.dto.Skill.SkillResponse;
import az.portfolioapi.entity.enums.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SkillService {

    SkillResponse createSkill(Long userId, SkillRequest request);
    SkillResponse updateSkill(Long skillId, Long userId, SkillRequest request);
    SkillResponse getSkillById(Long skillId, Long userId, UserRole role);
    Page<SkillResponse> getSkillsByPortfolioId(Long portfolioId, Long userId, UserRole role, Pageable pageable);
    Page<SkillResponse> getSkillsByUserId(Long userId, Pageable pageable);
    Page<SkillResponse> getAllSkills(Pageable pageable);
    void deleteSkill(Long skillId, Long userId, UserRole role);
}
