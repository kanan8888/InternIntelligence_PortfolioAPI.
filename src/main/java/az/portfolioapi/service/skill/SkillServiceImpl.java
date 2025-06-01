package az.portfolioapi.service.skill;

import az.portfolioapi.entity.enums.UserRole;
import az.portfolioapi.exception.portfolio.PortfolioNotFoundException;
import az.portfolioapi.exception.skill.SkillNotFoundException;
import az.portfolioapi.mapper.SkillMapper;
import az.portfolioapi.dto.Skill.SkillRequest;
import az.portfolioapi.dto.Skill.SkillResponse;
import az.portfolioapi.entity.PortfolioEntity;
import az.portfolioapi.entity.SkillEntity;
import az.portfolioapi.repository.PortfolioRepository;
import az.portfolioapi.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final PortfolioRepository portfolioRepository;
    private final SkillMapper skillMapper;

    @Override
    public SkillResponse createSkill(Long userId, SkillRequest request) {
        Long portfolioId = request.getPortfolioId();
        PortfolioEntity portfolio = portfolioRepository.findByIdAndUser_Id(portfolioId, userId)
                .orElseThrow(()-> new PortfolioNotFoundException(portfolioId, userId));
        SkillEntity skill = skillMapper.toEntity(request, portfolio);
        return skillMapper.toResponse(
                skillRepository.save(skill)
        );
    }

    @Override
    public SkillResponse updateSkill(Long skillId, Long userId, SkillRequest request) {
        SkillEntity skill = skillRepository.findByIdAndPortfolio_User_Id(skillId, userId)
                .orElseThrow(()-> new SkillNotFoundException(skillId, userId));
        skillMapper.updateEntity(request, skill);
        return skillMapper.toResponse(
                skillRepository.save(skill)
        );
    }

    @Override
    public SkillResponse getSkillById(Long skillId, Long userId, UserRole role) {
        SkillEntity skill = getSkillByRole(skillId, userId, role);
        return skillMapper.toResponse(skill);
    }

    @Override
    public Page<SkillResponse> getSkillsByPortfolioId(Long portfolioId, Long userId, UserRole role, Pageable pageable) {
        return role == UserRole.ADMIN
                ? skillRepository.findByPortfolio_Id(portfolioId, pageable)
                        .map(skillMapper::toResponse)
                : skillRepository.findByPortfolio_IdAndPortfolio_User_Id(portfolioId, userId, pageable)
                        .map(skillMapper::toResponse);
    }

    @Override
    public Page<SkillResponse> getSkillsByUserId(Long userId, Pageable pageable) {
        return skillRepository.findByPortfolio_User_Id(userId, pageable)
                .map(skillMapper::toResponse);
    }

    @Override
    public Page<SkillResponse> getAllSkills(Pageable pageable) {
        return skillRepository.findAll(pageable)
                .map(skillMapper::toResponse);
    }

    @Override
    public void deleteSkill(Long skillId, Long userId, UserRole role) {
        SkillEntity skill = getSkillByRole(skillId, userId, role);
        skillRepository.delete(skill);
    }


    private SkillEntity getSkillByRole(Long skillId, Long userId, UserRole role) {
        return role == UserRole.ADMIN
                ? skillRepository.findById(skillId)
                        .orElseThrow(() -> new SkillNotFoundException(skillId))
                : skillRepository.findByIdAndPortfolio_User_Id(skillId, userId)
                        .orElseThrow(() -> new SkillNotFoundException(skillId, userId));
    }
}
