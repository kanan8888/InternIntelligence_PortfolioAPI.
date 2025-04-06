package az.portfolioapi.service.experience;

import az.portfolioapi.dto.request.ExperienceRequestDTO;
import az.portfolioapi.dto.response.ExperienceResponseDTO;
import az.portfolioapi.entity.ExperienceEntity;
import az.portfolioapi.exception.ExperienceNotFoundException;
import az.portfolioapi.repository.ExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;

    @Override
    public ExperienceResponseDTO createExperience(ExperienceRequestDTO experienceRequestDTO) {
        return null;
    }

    @Override
    public ExperienceResponseDTO updateExperience(Long id, ExperienceRequestDTO experienceRequestDTO) {
        return null;
    }

    @Override
    public ExperienceResponseDTO getExperienceById(Long id) {
        return null;
    }

    @Override
    public List<ExperienceResponseDTO> getExperiencesByPortfolioId(Long portfolioId) {
        return List.of();
    }

    @Override
    public List<ExperienceResponseDTO> getAllExperiences() {
        return List.of();
    }

    @Override
    public void deleteExperience(Long id) {
        ExperienceEntity experience = experienceRepository.findById(id)
                .orElseThrow(()->new ExperienceNotFoundException("Experience not found with id: " + id));
        experienceRepository.delete(experience);
    }
}
