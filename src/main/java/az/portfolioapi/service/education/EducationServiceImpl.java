package az.portfolioapi.service.education;

import az.portfolioapi.dto.request.EducationRequestDTO;
import az.portfolioapi.dto.response.EducationResponseDTO;
import az.portfolioapi.entity.Education;
import az.portfolioapi.exception.EducationNotFoundException;
import az.portfolioapi.repository.EducationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;

    @Override
    public EducationResponseDTO createEducation(EducationRequestDTO educationRequestDTO) {
        return null;
    }

    @Override
    public EducationResponseDTO updateEducation(Long id, EducationRequestDTO educationRequestDTO) {
        return null;
    }

    @Override
    public EducationResponseDTO getEducationById(Long id) {
        return null;
    }

    @Override
    public List<EducationResponseDTO> getEducationsByPortfolioId(Long portfolioId) {
        return List.of();
    }

    @Override
    public List<EducationResponseDTO> getAllEducations() {
        return List.of();
    }

    @Override
    public void deleteEducation(Long id) {
        Education education = educationRepository.findById(id)
                .orElseThrow(()-> new EducationNotFoundException("No education found with id: " + id));
        educationRepository.delete(education);
    }
}
