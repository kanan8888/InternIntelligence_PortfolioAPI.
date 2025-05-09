package az.portfolioapi.service.education;

import az.portfolioapi.mapper.EducationMapper;
import az.portfolioapi.dto.Education.EducationRequest;
import az.portfolioapi.dto.Education.EducationResponse;
import az.portfolioapi.entity.EducationEntity;
import az.portfolioapi.entity.PortfolioEntity;
import az.portfolioapi.exception.EducationNotFoundException;
import az.portfolioapi.exception.PortfolioNotFoundException;
import az.portfolioapi.repository.EducationRepository;
import az.portfolioapi.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;
    private final PortfolioRepository portfolioRepository;
    private final EducationMapper educationMapper;

    @Override
    public EducationResponse createEducation(EducationRequest educationRequest) {
        PortfolioEntity portfolio = portfolioRepository.findById(educationRequest.getPortfolioId())
                .orElseThrow(()-> new PortfolioNotFoundException("portfolio not found"));
        EducationEntity education = educationMapper.toEntity(educationRequest,portfolio);
        return educationMapper.toResponse(educationRepository.save(education));
    }

    @Override
    public EducationResponse updateEducation(Long id, EducationRequest educationRequest) {
        EducationEntity education = educationRepository.findById(id)
                .orElseThrow(()-> new EducationNotFoundException("Education not found"));
        educationMapper.updateEntity(educationRequest,education);
        return educationMapper.toResponse(educationRepository.save(education));
    }

    @Override
    public EducationResponse getEducationById(Long id) {
        return educationRepository.findById(id)
                .map(educationMapper::toResponse)
                .orElseThrow(()->new EducationNotFoundException("Education not found"));
    }

    @Override
    public List<EducationResponse> getEducationsByPortfolioId(Long portfolioId) {
        PortfolioEntity portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(()-> new PortfolioNotFoundException("Portfolio not found"));
        return portfolio.getEducations().stream()
                .map(educationMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<EducationResponse> getAllEducations() {
        return educationRepository.findAll().stream()
                .map(educationMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEducation(Long id) {
        EducationEntity education = educationRepository.findById(id)
                .orElseThrow(()-> new EducationNotFoundException("No education found with id: " + id));
        educationRepository.delete(education);
    }
}
