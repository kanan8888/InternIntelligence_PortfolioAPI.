package az.portfolioapi.service.experience;

import az.portfolioapi.dto.Experience.ExperienceRequest;
import az.portfolioapi.dto.Experience.ExperienceResponse;

import java.util.List;

public interface ExperienceService {

    ExperienceResponse createExperience(ExperienceRequest experienceRequest);
    ExperienceResponse updateExperience(Long id, ExperienceRequest experienceRequest);
    ExperienceResponse getExperienceById(Long id);
    List<ExperienceResponse> getExperiencesByPortfolioId(Long portfolioId);
    List<ExperienceResponse> getAllExperiences();
    void deleteExperience(Long id);
}
