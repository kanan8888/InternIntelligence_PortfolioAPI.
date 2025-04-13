package az.portfolioapi.service.portfolio;

import az.portfolioapi.configuration.mapper.PortfolioMapper;
import az.portfolioapi.dto.request.PortfolioRequest;
import az.portfolioapi.dto.response.PortfolioResponse;
import az.portfolioapi.entity.PortfolioEntity;
import az.portfolioapi.entity.UserEntity;
import az.portfolioapi.entity.enums.UserRole;
import az.portfolioapi.exception.PortfolioNotFoundException;
import az.portfolioapi.repository.PortfolioRepository;
import az.portfolioapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final UserRepository userRepository;
    private final PortfolioMapper portfolioMapper;

    @Override
    public PortfolioResponse createPortfolio(PortfolioRequest request) {
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new RuntimeException("UserNotFound : " + request.getUserId()));
        PortfolioEntity portfolio = portfolioMapper.toEntity(request,user);
        return portfolioMapper.toResponse(portfolioRepository.save(portfolio));
    }

    @Override
    public PortfolioResponse updatePortfolio(Long id, PortfolioRequest request) {
        PortfolioEntity portfolio = portfolioRepository.findById(id)
                .orElseThrow(()-> new PortfolioNotFoundException("Portfolio not found with id: " + id));
        portfolioMapper.updateEntity(request, portfolio);
        portfolio = portfolioRepository.save(portfolio);
        return portfolioMapper.toResponse(portfolio);
    }

    @Override
    public PortfolioResponse getPortfolioById(Long id) {
        return portfolioRepository.findById(id)
                .map(portfolioMapper::toResponse)
                .orElseThrow(()-> new PortfolioNotFoundException("Portfolio not found with id: " + id));
    }

    @Override
    public List<PortfolioResponse> getPortfoliosByUserId(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("user not found"));
        return user.getPortfolios().stream()
                .map(portfolioMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PortfolioResponse> getAllPortfolios() {
        return portfolioRepository.findAll().stream()
                .map(portfolioMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePortfolio(Long id) {
        PortfolioEntity portfolioEntity = portfolioRepository.findById(id)
                .orElseThrow(()-> new PortfolioNotFoundException("Portfolio not found with id: " + id));
        portfolioRepository.delete(portfolioEntity);
    }
}
