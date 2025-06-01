package az.portfolioapi.service.experience;

import az.portfolioapi.entity.enums.UserRole;
import az.portfolioapi.exception.AccessDeniedException;
import az.portfolioapi.exception.experience.ExperienceNotFoundException;
import az.portfolioapi.exception.portfolio.PortfolioNotFoundException;
import az.portfolioapi.mapper.ExperienceMapper;
import az.portfolioapi.dto.Experience.ExperienceRequest;
import az.portfolioapi.dto.Experience.ExperienceResponse;
import az.portfolioapi.entity.ExperienceEntity;
import az.portfolioapi.entity.PortfolioEntity;
import az.portfolioapi.repository.ExperienceRepository;
import az.portfolioapi.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final PortfolioRepository portfolioRepository;
    private final ExperienceMapper experienceMapper;

    @Override
    public ExperienceResponse createExperience(Long userId, ExperienceRequest request) {
        PortfolioEntity portfolio = portfolioRepository.findByIdAndUser_Id(request.getPortfolioId(), userId)
                .orElseThrow(PortfolioNotFoundException::new);
        ExperienceEntity experience = experienceMapper.toEntity(request, portfolio);
        return experienceMapper.toResponse(
            experienceRepository.save(experience)
        );
    }

    @Override
    public ExperienceResponse updateExperience(Long experienceId, Long userId, ExperienceRequest request) {
        ExperienceEntity experience = experienceRepository.findByIdAndPortfolio_User_Id(experienceId, userId)
                .orElseThrow(ExperienceNotFoundException::new);
        experienceMapper.updateEntity(request, experience);
        return experienceMapper.toResponse(
                experienceRepository.save(experience)
        );
    }

    @Override
    public ExperienceResponse getExperienceById(Long experienceId, Long userId, UserRole role) {
        ExperienceEntity experience = getExperienceByRole(experienceId, userId, role);
        return experienceMapper.toResponse(experience);
    }

    @Override/**/
    public Page<ExperienceResponse> getExperiencesByPortfolioId(Long portfolioId, Long userId, UserRole role, Pageable pageable) {
        Page<ExperienceResponse> experiences = role == UserRole.ADMIN
                ? experienceRepository.findByPortfolio_Id(portfolioId, pageable)
                        .map(experienceMapper::toResponse)
                : experienceRepository.findByPortfolio_IdAndPortfolio_User_Id(portfolioId, userId, pageable)
                        .map(experienceMapper::toResponse);
        if(experiences.isEmpty()){
            throw new ExperienceNotFoundException();
        }
        return experiences;
    }

    @Override
    public Page<ExperienceResponse> getExperiencesByUserId(Long userId, Pageable pageable) {
        return experienceRepository.findByPortfolio_User_Id(userId, pageable)
                .map(experienceMapper::toResponse);
    }

    @Override
    public Page<ExperienceResponse> getAllExperiences(Pageable pageable) {
        return experienceRepository.findAll(pageable)
                .map(experienceMapper::toResponse);
    }

    @Override
    public void deleteExperience(Long experienceId, Long userId, UserRole role) {
        ExperienceEntity experience = getExperienceByRole(experienceId, userId, role);
        experienceRepository.delete(experience);
    }


    private ExperienceEntity getExperienceByRole(Long experienceId, Long userId, UserRole role) {
        return role == UserRole.ADMIN
                ? experienceRepository.findById(experienceId)
                        .orElseThrow(() -> new ExperienceNotFoundException(experienceId))
                : experienceRepository.findByIdAndPortfolio_User_Id(experienceId, userId)
                        .orElseThrow(() -> new AccessDeniedException("You do not have experience with id: " + experienceId));
    }
}
