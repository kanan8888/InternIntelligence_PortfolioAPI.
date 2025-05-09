package az.portfolioapi.service.portfolio;

import az.portfolioapi.dto.Portfolio.PortfolioRequest;
import az.portfolioapi.dto.Portfolio.PortfolioResponse;

import java.util.List;

public interface PortfolioService {

    PortfolioResponse createPortfolio(PortfolioRequest portfolioRequest);
    PortfolioResponse updatePortfolio(Long id, PortfolioRequest portfolioRequest);
    PortfolioResponse getPortfolioById(Long id);
    List<PortfolioResponse> getPortfoliosByUserId(Long userId);
    List<PortfolioResponse> getAllPortfolios();
    void deletePortfolio(Long id);
}
