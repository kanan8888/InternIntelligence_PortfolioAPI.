package az.portfolioapi.service.experience;

import az.portfolioapi.dto.request.ExperienceRequest;
import az.portfolioapi.dto.response.ExperienceResponse;

import java.util.List;

public interface ExperienceService {

    ExperienceResponse createExperience(ExperienceRequest experienceRequest);
    ExperienceResponse updateExperience(Long id, ExperienceRequest experienceRequest);
    ExperienceResponse getExperienceById(Long id);
    List<ExperienceResponse> getExperiencesByPortfolioId(Long portfolioId);
    List<ExperienceResponse> getAllExperiences();
    void deleteExperience(Long id);
}
