package az.portfolioapi.service.skill;

import az.portfolioapi.dto.request.SkillRequestDTO;
import az.portfolioapi.dto.response.SkillResponseDTO;
import az.portfolioapi.entity.Skill;
import az.portfolioapi.exception.SkillNotFoundException;
import az.portfolioapi.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final ModelMapper modelMapper;

    @Override
    public SkillResponseDTO createSkill(SkillRequestDTO skillRequestDTO) {
        return null;
    }

    @Override
    public SkillResponseDTO updateSkill(Long id, SkillRequestDTO skillRequestDTO) {
        return null;
    }

    @Override
    public SkillResponseDTO getSkillById(Long id) {
        return skillRepository.findById(id)
                .map(skill -> modelMapper.map(skill, SkillResponseDTO.class))
                .orElseThrow(()-> new SkillNotFoundException("skill not found with id " + id));
    }

    @Override
    public List<SkillResponseDTO> getSkillsByPortfolioId(Long portfolioId) {
        return List.of();
    }

    @Override
    public List<SkillResponseDTO> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(skill -> modelMapper.map(skill, SkillResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSkill(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(()-> new SkillNotFoundException("skill not found with id " + id));
        skillRepository.delete(skill);
    }
}
