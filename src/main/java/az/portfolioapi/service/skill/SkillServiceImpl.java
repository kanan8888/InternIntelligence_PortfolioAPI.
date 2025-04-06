package az.portfolioapi.service.skill;

import az.portfolioapi.dto.request.SkillRequestDTO;
import az.portfolioapi.dto.response.SkillResponseDTO;
import az.portfolioapi.entity.SkillEntity;
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
                .map(skillEntity -> modelMapper.map(skillEntity, SkillResponseDTO.class))
                .orElseThrow(()-> new SkillNotFoundException("skill not found with id " + id));
    }

    @Override
    public List<SkillResponseDTO> getSkillsByPortfolioId(Long portfolioId) {
        return List.of();
    }

    @Override
    public List<SkillResponseDTO> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(skillEntity -> modelMapper.map(skillEntity, SkillResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSkill(Long id) {
        SkillEntity skillEntity = skillRepository.findById(id)
                .orElseThrow(()-> new SkillNotFoundException("skill not found with id " + id));
        skillRepository.delete(skillEntity);
    }
}
