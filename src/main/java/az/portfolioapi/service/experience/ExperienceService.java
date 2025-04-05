package az.portfolioapi.service.experience;

import az.portfolioapi.dto.request.ExperienceRequestDTO;
import az.portfolioapi.dto.response.ExperienceResponseDTO;

import java.util.List;

public interface ExperienceService {
    ExperienceResponseDTO createExperience(ExperienceRequestDTO experienceRequestDTO);
    ExperienceResponseDTO updateExperience(Long id, ExperienceRequestDTO experienceRequestDTO);
    ExperienceResponseDTO getExperienceById(Long id);
    List<ExperienceResponseDTO> getExperiencesByPortfolioId(Long portfolioId);
    List<ExperienceResponseDTO> getAllExperiences();
    void deleteExperience(Long id);
}
