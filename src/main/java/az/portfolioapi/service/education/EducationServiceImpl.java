package az.portfolioapi.service.education;

import az.portfolioapi.entity.enums.UserRole;
import az.portfolioapi.exception.AccessDeniedException;
import az.portfolioapi.exception.education.EducationNotFoundException;
import az.portfolioapi.exception.portfolio.PortfolioNotFoundException;
import az.portfolioapi.mapper.EducationMapper;
import az.portfolioapi.dto.Education.EducationRequest;
import az.portfolioapi.dto.Education.EducationResponse;
import az.portfolioapi.entity.EducationEntity;
import az.portfolioapi.entity.PortfolioEntity;
import az.portfolioapi.repository.EducationRepository;
import az.portfolioapi.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;
    private final PortfolioRepository portfolioRepository;
    private final EducationMapper educationMapper;

    @Override
    public EducationResponse createEducation(Long userId, EducationRequest request) {
        PortfolioEntity portfolio = portfolioRepository.findByIdAndUser_Id(request.getPortfolioId(), userId)
                .orElseThrow(PortfolioNotFoundException::new);
        EducationEntity education = educationMapper.toEntity(request, portfolio);
        return educationMapper.toResponse(
                educationRepository.save(education)
        );
    }

    @Override
    public EducationResponse updateEducation(Long educationId, Long userId, EducationRequest request) {
        EducationEntity education = educationRepository.findByIdAndPortfolio_User_Id(educationId, userId)
                .orElseThrow(EducationNotFoundException::new);
        educationMapper.updateEntity(request, education);
        return educationMapper.toResponse(
                educationRepository.save(education)
        );
    }

    @Override
    public EducationResponse getEducationById(Long educationId, Long userId, UserRole role) {
        EducationEntity education = getEducationByRole(educationId, userId, role);
        return educationMapper.toResponse(education);
    }

    @Override
    public Page<EducationResponse> getEducationsByPortfolioId(Long portfolioId, Long userId, UserRole role, Pageable pageable) {
        Page<EducationResponse> educations = role == UserRole.ADMIN
                ? educationRepository.findByPortfolio_Id(portfolioId, pageable)
                        .map(educationMapper::toResponse)
                : educationRepository.findByPortfolio_IdAndPortfolio_User_Id(portfolioId, userId, pageable)
                        .map(educationMapper::toResponse);
        if(educations.isEmpty()){
            throw new EducationNotFoundException();
        }
        return educations;
    }

    @Override
    public Page<EducationResponse> getEducationsByUserId(Long userId, Pageable pageable) {
        return educationRepository.findByPortfolio_User_Id(userId, pageable)
                .map(educationMapper::toResponse);
    }

    @Override
    public Page<EducationResponse> getAllEducations(Pageable pageable) {
        return educationRepository.findAll(pageable)
                .map(educationMapper::toResponse);
    }

    @Override
    public void deleteEducation(Long educationId, Long userId, UserRole role) {
        EducationEntity education = getEducationByRole(educationId, userId, role);
        educationRepository.delete(education);
    }


    private EducationEntity getEducationByRole(Long educationId, Long userId, UserRole role) {
        return role == UserRole.ADMIN
                ? educationRepository.findById(educationId)
                        .orElseThrow(() -> new EducationNotFoundException(educationId))
                : educationRepository.findByIdAndPortfolio_User_Id(educationId, userId)
                        .orElseThrow(() -> new AccessDeniedException("You do not have education with id: " + educationId));
    }
}
