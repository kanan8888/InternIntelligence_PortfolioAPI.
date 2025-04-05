package az.portfolioapi.service.portfolio;

import az.portfolioapi.dto.request.PortfolioRequestDTO;
import az.portfolioapi.dto.response.PortfolioResponseDTO;

import java.util.List;

public interface PortfolioService {
    PortfolioResponseDTO createPortfolio(PortfolioRequestDTO portfolioRequestDTO);
    PortfolioResponseDTO updatePortfolio(Long id, PortfolioRequestDTO portfolioRequestDTO);
    PortfolioResponseDTO getPortfolioById(Long id);
    List<PortfolioResponseDTO> getPortfoliosByUserId(Long userId);
    List<PortfolioResponseDTO> getAllPortfolios();
    void deletePortfolio(Long id);
}
