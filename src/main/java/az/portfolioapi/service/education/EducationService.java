package az.portfolioapi.service.education;

import az.portfolioapi.dto.Education.EducationRequest;
import az.portfolioapi.dto.Education.EducationResponse;
import az.portfolioapi.entity.enums.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EducationService {

    EducationResponse createEducation(Long userId, EducationRequest request);
    EducationResponse updateEducation(Long educationId, Long userId, EducationRequest request);
    EducationResponse getEducationById(Long educationId, Long userId, UserRole role);
    Page<EducationResponse> getEducationsByPortfolioId(Long portfolioId, Long userId, UserRole role, Pageable pageable);
    Page<EducationResponse> getEducationsByUserId(Long userId, Pageable pageable);
    Page<EducationResponse> getAllEducations(Pageable pageable);
    void deleteEducation(Long educationId, Long userId, UserRole role);
}
