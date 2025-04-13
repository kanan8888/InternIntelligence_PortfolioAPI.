package az.portfolioapi.service.education;

import az.portfolioapi.dto.request.EducationRequest;
import az.portfolioapi.dto.response.EducationResponse;

import java.util.List;

public interface EducationService {

    EducationResponse createEducation(EducationRequest educationRequest);
    EducationResponse updateEducation(Long id, EducationRequest educationRequest);
    EducationResponse getEducationById(Long id);
    List<EducationResponse> getEducationsByPortfolioId(Long portfolioId);
    List<EducationResponse> getAllEducations();
    void deleteEducation(Long id);
}
