package az.portfolioapi.service.portfolio;

import az.portfolioapi.dto.request.PortfolioRequestDTO;
import az.portfolioapi.dto.response.PortfolioResponseDTO;
import az.portfolioapi.entity.Portfolio;
import az.portfolioapi.exception.PortfolioNotFoundException;
import az.portfolioapi.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;

    @Override
    public PortfolioResponseDTO createPortfolio(PortfolioRequestDTO portfolioRequestDTO) {
        return null;
    }

    @Override
    public PortfolioResponseDTO updatePortfolio(Long id, PortfolioRequestDTO portfolioRequestDTO) {
        return null;
    }

    @Override
    public PortfolioResponseDTO getPortfolioById(Long id) {
        return null;
    }

    @Override
    public List<PortfolioResponseDTO> getPortfoliosByUserId(Long userId) {
        return List.of();
    }

    @Override
    public List<PortfolioResponseDTO> getAllPortfolios() {
        return List.of();
    }

    @Override
    public void deletePortfolio(Long id) {
        Portfolio portfolio = portfolioRepository.findById(id)
                .orElseThrow(()-> new PortfolioNotFoundException("Portfolio not found with id: " + id));
        portfolioRepository.delete(portfolio);
    }
}
