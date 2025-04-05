package az.portfolioapi.service.education;

import az.portfolioapi.dto.request.EducationRequestDTO;
import az.portfolioapi.dto.response.EducationResponseDTO;

import java.util.List;

public interface EducationService {
    EducationResponseDTO createEducation(EducationRequestDTO educationRequestDTO);
    EducationResponseDTO updateEducation(Long id, EducationRequestDTO educationRequestDTO);
    EducationResponseDTO getEducationById(Long id);
    List<EducationResponseDTO> getEducationsByPortfolioId(Long portfolioId);
    List<EducationResponseDTO> getAllEducations();
    void deleteEducation(Long id);
}
