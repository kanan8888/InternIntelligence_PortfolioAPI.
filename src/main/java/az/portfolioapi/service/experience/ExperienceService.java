package az.portfolioapi.service.experience;

import az.portfolioapi.dto.Education.EducationRequest;
import az.portfolioapi.dto.Education.EducationResponse;
import az.portfolioapi.dto.Experience.ExperienceRequest;
import az.portfolioapi.dto.Experience.ExperienceResponse;
import az.portfolioapi.entity.enums.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExperienceService {

    ExperienceResponse createExperience(Long userId, ExperienceRequest request);
    ExperienceResponse updateExperience(Long experienceId, Long userId, ExperienceRequest request);
    ExperienceResponse getExperienceById(Long experienceId, Long userId, UserRole role);
    Page<ExperienceResponse> getExperiencesByPortfolioId(Long portfolioId, Long userId, UserRole role, Pageable pageable);
    Page<ExperienceResponse> getExperiencesByUserId(Long userId, Pageable pageable);
    Page<ExperienceResponse> getAllExperiences(Pageable pageable);
    void deleteExperience(Long experienceId, Long userId, UserRole role);
}
