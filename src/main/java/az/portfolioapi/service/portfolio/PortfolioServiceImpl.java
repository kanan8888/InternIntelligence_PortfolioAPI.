package az.portfolioapi.service.portfolio;

import az.portfolioapi.entity.enums.UserRole;
import az.portfolioapi.exception.AccessDeniedException;
import az.portfolioapi.exception.portfolio.PortfolioNotFoundException;
import az.portfolioapi.exception.user.UserNotFoundException;
import az.portfolioapi.mapper.PortfolioMapper;
import az.portfolioapi.dto.Portfolio.PortfolioRequest;
import az.portfolioapi.dto.Portfolio.PortfolioResponse;
import az.portfolioapi.entity.PortfolioEntity;
import az.portfolioapi.entity.UserEntity;
import az.portfolioapi.repository.PortfolioRepository;
import az.portfolioapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final UserRepository userRepository;
    private final PortfolioMapper portfolioMapper;

    @Override
    public PortfolioResponse createPortfolio(Long userId, PortfolioRequest request) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException(userId));   /* bu error hec zaman atilmayacaq  */
        PortfolioEntity portfolio = portfolioMapper.toEntity(request, user);
        return portfolioMapper.toResponse(
                portfolioRepository.save(portfolio)
        );
    }

    @Override
    public PortfolioResponse updatePortfolio(Long portfolioId, Long userId, PortfolioRequest request) {
        PortfolioEntity portfolio = portfolioRepository.findByIdAndUser_Id(portfolioId, userId)
                .orElseThrow(PortfolioNotFoundException::new);
        portfolioMapper.updateEntity(request, portfolio);
        return portfolioMapper.toResponse(
                portfolioRepository.save(portfolio)
        );
    }

    @Override
    public PortfolioResponse getPortfolioById(Long portfolioId, Long userId, UserRole role) {
        PortfolioEntity portfolio = getPortfolioByRole(portfolioId, userId, role);
        return portfolioMapper.toResponse(portfolio);
    }

    @Override
    public Page<PortfolioResponse> getPortfoliosByUserId(Long userId, Pageable pageable) {
        return portfolioRepository.findByUser_Id(userId, pageable)
                .map(portfolioMapper::toResponse);
    }

    @Override
    public Page<PortfolioResponse> getAllPortfolios(Pageable pageable) {
        return portfolioRepository.findAll(pageable)
                .map(portfolioMapper::toResponse);
    }

    @Override
    public void deletePortfolio(Long portfolioId, Long userId, UserRole role) {
        PortfolioEntity portfolio = getPortfolioByRole(portfolioId, userId, role);
        portfolioRepository.delete(portfolio);
    }


    private PortfolioEntity getPortfolioByRole(Long portfolioId, Long userId, UserRole role) {
        return role == UserRole.ADMIN
                ? portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new PortfolioNotFoundException(portfolioId))
                : portfolioRepository.findByIdAndUser_Id(portfolioId, userId)
                .orElseThrow(() -> new AccessDeniedException("You do not have portfolio with id: " + portfolioId));
    }
}
