package az.portfolioapi.service.experience;

import az.portfolioapi.configuration.mapper.ExperienceMapper;
import az.portfolioapi.dto.request.ExperienceRequestDTO;
import az.portfolioapi.dto.response.ExperienceResponseDTO;
import az.portfolioapi.entity.ExperienceEntity;
import az.portfolioapi.entity.PortfolioEntity;
import az.portfolioapi.exception.ExperienceNotFoundException;
import az.portfolioapi.exception.PortfolioNotFoundException;
import az.portfolioapi.repository.ExperienceRepository;
import az.portfolioapi.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final PortfolioRepository portfolioRepository;
    private final ExperienceMapper experienceMapper;

    @Override
    public ExperienceResponseDTO createExperience(ExperienceRequestDTO experienceRequest) {
        PortfolioEntity portfolio = portfolioRepository.findById(experienceRequest.getPortfolioId())
                .orElseThrow(()-> new ExperienceNotFoundException("Experience not found with id " + experienceRequest.getPortfolioId()));
        ExperienceEntity experience = experienceMapper.toEntity(experienceRequest,portfolio);
        return experienceMapper.toResponse(experienceRepository.save(experience));
    }

    @Override
    public ExperienceResponseDTO updateExperience(Long id, ExperienceRequestDTO experienceRequest) {
        ExperienceEntity experience = experienceRepository.findById(id)
                .orElseThrow(()-> new ExperienceNotFoundException("Experience not found"));
        experienceMapper.updateEntity(experienceRequest,experience);
        return experienceMapper.toResponse(experienceRepository.save(experience));
    }

    @Override
    public ExperienceResponseDTO getExperienceById(Long id) {
        return experienceRepository.findById(id)
                .map(experienceMapper::toResponse)
                .orElseThrow(()-> new ExperienceNotFoundException("Experience not found"));
    }

    @Override
    public List<ExperienceResponseDTO> getExperiencesByPortfolioId(Long portfolioId) {
        PortfolioEntity portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(()-> new PortfolioNotFoundException("Portfolio not found"));
        return portfolio.getExperiences().stream()
                .map(experienceMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExperienceResponseDTO> getAllExperiences() {
        return experienceRepository.findAll().stream()
                .map(experienceMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteExperience(Long id) {
        ExperienceEntity experience = experienceRepository.findById(id)
                .orElseThrow(()->new ExperienceNotFoundException("Experience not found with id: " + id));
        experienceRepository.delete(experience);
    }
}
