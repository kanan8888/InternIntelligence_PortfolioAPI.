package az.portfolioapi.service.skill;

import az.portfolioapi.dto.request.SkillRequestDTO;
import az.portfolioapi.dto.response.SkillResponseDTO;

import java.util.List;

public interface SkillService {
    SkillResponseDTO createSkill(SkillRequestDTO skillRequestDTO);
    SkillResponseDTO updateSkill(Long id, SkillRequestDTO skillRequestDTO);
    SkillResponseDTO getSkillById(Long id);
    List<SkillResponseDTO> getSkillsByPortfolioId(Long portfolioId);
    List<SkillResponseDTO> getAllSkills();
    void deleteSkill(Long id);
}
