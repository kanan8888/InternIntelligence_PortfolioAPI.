package az.portfolioapi.service.skill;

import az.portfolioapi.configuration.mapper.SkillMapper;
import az.portfolioapi.dto.request.SkillRequest;
import az.portfolioapi.dto.response.SkillResponse;
import az.portfolioapi.entity.PortfolioEntity;
import az.portfolioapi.entity.SkillEntity;
import az.portfolioapi.exception.PortfolioNotFoundException;
import az.portfolioapi.exception.SkillNotFoundException;
import az.portfolioapi.repository.PortfolioRepository;
import az.portfolioapi.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final PortfolioRepository portfolioRepository;
    private final SkillMapper skillMapper;

    @Override
    public SkillResponse createSkill(SkillRequest skillRequest) {
        PortfolioEntity portfolio = portfolioRepository.findById(skillRequest.getPortfolioId())
                .orElseThrow(() -> new PortfolioNotFoundException("Portfolio not found with id: " + skillRequest.getPortfolioId()));
        SkillEntity skill = skillMapper.toEntity(skillRequest, portfolio);
        return skillMapper.toResponse(skillRepository.save(skill));
    }

    @Override
    public SkillResponse updateSkill(Long id, SkillRequest skillRequest) {
        SkillEntity skill = skillRepository.findById(id)
                .orElseThrow(()-> new SkillNotFoundException("Skill not found with id " + id));
        skillMapper.update(skillRequest, skill);
        return skillMapper.toResponse(skillRepository.save(skill));
    }

    @Override
    public SkillResponse getSkillById(Long id) {
        return skillRepository.findById(id)
                .map(skillMapper::toResponse)
                .orElseThrow(()-> new SkillNotFoundException("skill not found with id " + id));
    }

    @Override
    public List<SkillResponse> getSkillsByPortfolioId(Long portfolioId) {
        PortfolioEntity portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new PortfolioNotFoundException("Portfolio not found with id " + portfolioId));
        return portfolio.getSkills().stream()
                .map(skillMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<SkillResponse> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(skillMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSkill(Long id) {
        SkillEntity skillEntity = skillRepository.findById(id)
                .orElseThrow(()-> new SkillNotFoundException("skill not found with id " + id));
        skillRepository.delete(skillEntity);
    }
}
