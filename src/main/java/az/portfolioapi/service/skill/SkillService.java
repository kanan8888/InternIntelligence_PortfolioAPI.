package az.portfolioapi.service.skill;

import az.portfolioapi.dto.Skill.SkillRequest;
import az.portfolioapi.dto.Skill.SkillResponse;

import java.util.List;

public interface SkillService {

    SkillResponse createSkill(SkillRequest skillRequest);
    SkillResponse updateSkill(Long id, SkillRequest skillRequest);
    SkillResponse getSkillById(Long id);
    List<SkillResponse> getSkillsByPortfolioId(Long portfolioId);
    List<SkillResponse> getAllSkills();
    void deleteSkill(Long id);
}
